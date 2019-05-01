
package com.igomall.controller;

import com.aliyun.oss.common.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.igomall.common.Message;
import com.igomall.common.Page;
import com.igomall.common.Pageable;
import com.igomall.entity.Product;
import com.igomall.service.ProductCategoryService;
import com.igomall.service.ProductService;
import com.igomall.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Controller - 商品
 * 
 * @author blackboy
 * @version 1.0
 */
@RestController("adminProductController")
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/list")
    @JsonView(Product.ListView.class)
    public Page<Product> page(Pageable pageable, HttpServletRequest request,String name){
        System.out.println(WebUtils.getCookie(request,"userId"));
        if(StringUtils.isNotEmpty(name)){
            pageable.setSearchProperty("name");
            pageable.setSearchValue(name);
        }


        return productService.findPage(pageable);
    }


    @PostMapping("/save")
    public Message save(Product product){
        product.setLastModifiedDate(new Date());
        if(product.isNew()){
            product.setCreatedDate(new Date());
            product.setSn(System.currentTimeMillis()+"");
            productService.save(product);
        }else{
            product.setProductCategory(productCategoryService.find(2157L));
            productService.update(product);
        }


        return SUCCESS_MESSAGE;
    }

    @PostMapping("/edit")
    public Product edit(Long id){
        return productService.find(id);
    }

}