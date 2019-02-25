package com.cbx.tby.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.cbx.tby.component.auth.AuthenticationFacade;
import com.cbx.tby.component.query.AggregateQueryer;
import com.cbx.tby.component.query.Queryer;
import com.cbx.tby.component.query.entity.ProductQueryParameter;
import com.cbx.tby.component.query.interpreter.CompanyNameBlurSearchInterpreter;
import com.cbx.tby.component.query.interpreter.CompanyProfileRelatedProductsInterpreter;
import com.cbx.tby.component.query.interpreter.KeyMappingParameterInterpreter;
import com.cbx.tby.component.query.interpreter.ShowRoomSpaceInterpreter;
import com.cbx.tby.converter.entity.ProductConverter;
import com.cbx.tby.entity.company.CompanyProfileDO;
import com.cbx.tby.entity.product.ProductAggregate;
import com.cbx.tby.entity.product.ProductDO;
import com.cbx.tby.entity.product.ProductRequest;
import com.cbx.tby.entity.product.ProductResponse;
import com.cbx.tby.exception.DuplicatedKeyException;
import com.cbx.tby.exception.NotFoundException;
import com.cbx.tby.repository.mongo.ProductRepository;

import static com.cbx.tby.constants.GeneralConstants.*;

@Service
public class ProductService implements IS3LinkUpdater {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyProfileService companyProfileService;

    @Autowired
    private AuthenticationFacade authFacade;
    
    @Autowired
    private AggregateQueryer aggQueryer;
    
    @Autowired
    private Queryer queryer;

    public ProductResponse loadProduct(String productId) {
        ProductAggregate productAgg = aggQueryer
                .aggregateById(productId, ProductDO.class, ProductAggregate.class)
                .orElseThrow(() -> new NotFoundException(ERROR_CANNOT_FIND_PRODUCT));

        if (productAgg.getColors() == null) {
            productAgg.setColors(Lists.newArrayList());
        }

        return ProductConverter.toProductResponse(productAgg);
    }

    public List<ProductResponse> loadProducts(ProductQueryParameter productQueryParameter) {
        List<ProductAggregate> result = aggQueryer
                .appendQueryFieldInterpreter(REQUEST_PARAM_SPACE,
                        new ShowRoomSpaceInterpreter(authFacade.owner()))
                .appendQueryFieldInterpreter(CompanyProfileDO.COMPANY_PROFILE_ID,
                        new CompanyProfileRelatedProductsInterpreter(companyProfileService))
                .appendQueryFieldInterpreter(REQUEST_PARAM_MOQ_UNIT,
                        new KeyMappingParameterInterpreter(REQUEST_PARAM_MOQ_UNIT, PROPERTY_MOQ_UNIT))
                .appendQueryFieldInterpreter(REQUEST_PARAM_COUNTRY,
                        new KeyMappingParameterInterpreter(REQUEST_PARAM_COUNTRY, PROPERTY_COUNTRY_OF_ORIGIN))
                .appendQueryFieldInterpreter(REQUEST_PARAM_COMPANY_NAME,
                        new CompanyNameBlurSearchInterpreter(REQUEST_PARAM_COMPANY_NAME))
                .aggregate(productQueryParameter, ProductDO.class, ProductAggregate.class);

        return ProductConverter.toProductResponses(result);
    }

    public List<ProductResponse> loadProducts(List<String> productIds) {
        ProductQueryParameter parameter = new ProductQueryParameter();
        parameter.setId(productIds);
        parameter.setSortField(PROPERTY_ID);
        parameter.setOrderBy(SORTING_RULE_DESC);

        return loadProducts(parameter);
    }

    public ProductResponse createProduct(final ProductDO productDO) {
        productDO.setOwner(authFacade.owner());
        productDO.setCreator(authFacade.creator());
        productDO.setCreated(Calendar.getInstance().getTime());

        /*
           This statements is in #loadProduct for preventing NullPointerException.
           After checking db (Demo and dev collection in .8.33) all documents are saved with colors field.
           However, this kind of data check should be refactoring.
        */
        if (productDO.getColors() == null) {
            productDO.setColors(Lists.newArrayList());
        }

        ProductDO createdProduct;
        try {
            createdProduct = productRepository.insert(productDO);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            throw new DuplicatedKeyException(e);
        }

        return loadProduct(createdProduct.getId());
    }

    public ProductDO replaceProduct(String id, final ProductRequest productRequest) {
        if (!isProductExist(id)) {
            throw new NotFoundException(ERROR_CANNOT_FIND_PRODUCT);
        }

        ProductDO productDO = ProductConverter.toProductDO(productRequest);

        productDO.setId(id);
        productDO.setOwner(authFacade.owner());
        productDO.setCreator(authFacade.creator());

        return productRepository.save(productDO);
    }

    public boolean isMyItem(String id) {
        ProductDO product = queryer.findById(id, ProductDO.class)
                .orElseThrow(() -> new NotFoundException(ERROR_CANNOT_FIND_PRODUCT));
        return StringUtils.equals(authFacade.owner(), product.getOwner());
    }

    private boolean isProductExist(String productId) {
        ProductQueryParameter parameter = new ProductQueryParameter();
        parameter.setId(productId);
        parameter.setOwner(authFacade.owner());
        parameter.setCreator(authFacade.creator());

        return queryer.findOne(parameter, ProductDO.class).isPresent();
    }

    public ProductDO findProductDOById(String productId) {
        return queryer.findById(productId, ProductDO.class)
                .orElseThrow(() -> new NotFoundException(ERROR_CANNOT_FIND_PRODUCT));
    }

    @Override
    public boolean updateImageUri(String imageId, String uri) {
        List<ProductDO> products = productRepository.findByImageId(imageId);
        if (CollectionUtils.isEmpty(products)) {
            return false;
        }
        products.forEach(productDO -> {
            Optional.ofNullable(productDO.getImages()).orElse(Collections.emptyList())
                    .forEach(image -> {
                        if (StringUtils.equals(imageId, image.getId())) {
                            image.setFileDownloadUri(uri);
                        }
                    });
        });
        productRepository.saveAll(products);

        return true;
    }
}

