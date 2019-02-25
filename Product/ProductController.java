package com.cbx.tby.controller;

import com.cbx.tby.component.query.entity.ProductQueryParameter;
import com.cbx.tby.constants.GeneralConstants;
import com.cbx.tby.converter.entity.ProductConverter;
import com.cbx.tby.entity.product.ProductDO;
import com.cbx.tby.entity.product.ProductRequest;
import com.cbx.tby.entity.product.ProductResponse;
import com.cbx.tby.service.ProductService;
import com.cbx.tby.utils.ResponseContent2;
import com.cbx.tby.utils.URIHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(GeneralConstants.API_SUPPLIER_PRODUCT)
@Api(value = "Operation pertaining to products in TradeBeyond")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Load a product")
    @GetMapping(value = "/{id}", produces = GeneralConstants.REQUEST_BODY_TYPE_APP_JSON)
    public ResponseEntity<ResponseContent2> loadProduct(@PathVariable("id") String id) {
        final ProductResponse productResponse = productService.loadProduct(id);
        return ResponseEntity.ok(new ResponseContent2<>(productResponse));
    }

    @ApiOperation(value = "View a list of products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "Not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 423, message = "Cannot create a product with an existing id")
    })
    @GetMapping(produces = GeneralConstants.REQUEST_BODY_TYPE_APP_JSON)
    public ResponseEntity<ResponseContent2> loadProducts(@ModelAttribute ProductQueryParameter productQueryParameter) {
        List<ProductResponse> result = productService.loadProducts(productQueryParameter);
        return ResponseEntity.ok(new ResponseContent2<>(result));
    }

    @ApiOperation(value = "Add a product")
    @PostMapping(produces = GeneralConstants.REQUEST_BODY_TYPE_APP_JSON)
    public ResponseEntity<ResponseContent2> createProduct(@Valid @RequestBody final ProductRequest productRequest) {
        final ProductResponse createdProduct = productService.createProduct(
                ProductConverter.toProductDO(productRequest)
        );

        return ResponseEntity
                .created(URIHandler.getURI("/{id}", createdProduct.getItemId()))
                .body(new ResponseContent2<>(createdProduct));
    }

    @ApiOperation(value = "Update products")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseContent2> replaceProduct(@PathVariable("id") String id,
                                                           @Valid @RequestBody final ProductRequest productRequest) {
        final ProductDO savedProduct = productService.replaceProduct(id, productRequest);
        return ResponseEntity.ok(new ResponseContent2<>(GeneralConstants.MESSAGE_UPDATE_SUCCESS, savedProduct.getId()));
    }

}
