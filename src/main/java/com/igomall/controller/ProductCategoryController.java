
package com.igomall.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.igomall.common.Page;
import com.igomall.common.Pageable;
import com.igomall.entity.ProductCategory;
import com.igomall.service.AreaService;
import com.igomall.service.ProductCategoryService;
import com.igomall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller - 商品分类
 * 
 * @author IGOMALL Team
 * @version 5.0
 */
@RestController("adminProductCategoryController")
@RequestMapping("/product_category")
@CrossOrigin
public class ProductCategoryController extends BaseController {

	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AreaService areaService;

	@GetMapping("/list")
	@JsonView(ProductCategory.ListView.class)
	public List<ProductCategory> list(){
		return productCategoryService.findRoots();
	}


	@GetMapping("/page")
	@JsonView(ProductCategory.PageView.class)
	public ResponseEntity<?> page(Pageable pageable,Integer key){
		if(key==1){//商品分类
			return ResponseEntity.ok(productCategoryService.findPage(pageable));
		}else if(key==2){
			return ResponseEntity.ok(productService.findPage(pageable));
		}else if(key==3){
			return ResponseEntity.ok(areaService.findPage(pageable));
		}

		return ResponseEntity.ok(new Page<ProductCategory>());
	}


}