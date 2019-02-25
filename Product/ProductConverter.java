package com.cbx.tby.converter.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import com.amazonaws.util.CollectionUtils;
import com.cbx.tby.entity.product.ProductAggregate;
import com.cbx.tby.entity.product.ProductDO;
import com.cbx.tby.entity.product.ProductRequest;
import com.cbx.tby.entity.product.ProductResponse;
import com.cbx.tby.entity.publication.PublicationType;

public class ProductConverter {

    private ProductConverter() { }

    public static ProductDO toProductDO(ProductRequest productRequest) {
        final ProductDO productDO = new ProductDO();
        productDO.setId(productRequest.getId());
        productDO.setItemNo(productRequest.getItemNo());
        productDO.setDescription(productRequest.getDescription());
        productDO.setItemName(productRequest.getItemName());
        productDO.setPriceFrom(productRequest.getPriceFrom());
        productDO.setPriceTo(productRequest.getPriceTo());
        productDO.setCurrency(productRequest.getCurrency());
        productDO.setMoq(productRequest.getMoq());
        productDO.setMoqUnit(productRequest.getMoqUnit());
        productDO.setCategories(productRequest.getCategories());
        productDO.setOwner(productRequest.getOwner());
        productDO.setCreator(productRequest.getCreator());
        productDO.setCountryOfOrigin(productRequest.getCountryOfOrigin());
        productDO.setSize(productRequest.getSize());
        productDO.setImages(productRequest.getImages());
        productDO.setAttachment(productRequest.getAttachment());
        productDO.setCreated(productRequest.getCreated());
        productDO.setColors(productRequest.getColors());
        productDO.setSellingPoints(productRequest.getSellingPoints());
        productDO.setMaterial(productRequest.getMaterial());
        productDO.setCopyInfo(productRequest.getCopyInfo());
        productDO.setCreatorRole(productRequest.getCreatorRole());

        return productDO;
    }

    public static List<ProductResponse> toProductResponses(List<ProductAggregate> productAggs) {
        return productAggs.stream()
                .map(ProductConverter::toProductResponse).collect(Collectors.toList());
    }

    public static ProductResponse toProductResponse(ProductAggregate productAgg) {
        ProductDO productDo = new ProductDO();
        BeanUtils.copyProperties(productAgg, productDo);
        ProductResponse response = new ProductResponse(productDo);
        response.setOwner(productAgg.getCompanyName());
        response.setOwnerId(productAgg.getOwner());
        response.setFavorite(!CollectionUtils.isNullOrEmpty(productAgg.getFavoriteInfo()));
        response.setPublicationId(productAgg.getPublicationId());
        response.setPublicationType(productAgg.getPublicationType());
        response.setShareType(productAgg.getShareType());

        if (ObjectUtils.isEmpty(productAgg.getCopyInfo())) {
            response.setOwner(productAgg.getCompanyName());
            response.setOwnerId(productAgg.getOwner());
        } else {
            response.setOwner(productAgg.getCopyInfo().getOwner());
            response.setOwnerId(productAgg.getCopyInfo().getOwnerId());
        }

        if (StringUtils.isEmpty(productAgg.getPublicationType())) {
            response.setPublicationType(PublicationType.UNPUBLISH.toString());
        }

        return response;
    }

}
