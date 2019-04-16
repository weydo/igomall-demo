
package com.igomall.service;

import com.igomall.entity.ProductCategory;

import java.util.List;

/**
 * Service - 商品分类
 * 
 * @author IGOMALL Team
 * @version 5.0
 */
public interface ProductCategoryService extends BaseService<ProductCategory, Long> {

    /**
     * 查找顶级商品分类
     *
     * @return 顶级商品分类
     */
    List<ProductCategory> findRoots();

    /**
     * 查找顶级地区
     *
     * @param count
     *            数量
     * @return 顶级地区
     */
    List<ProductCategory> findRoots(Integer count);

    /**
     * 查找上级商品分类
     *
     * @param productCategory
     *            商品分类
     * @param recursive
     *            是否递归
     * @param count
     *            数量
     * @return 上级商品分类
     */
    List<ProductCategory> findParents(ProductCategory productCategory, boolean recursive, Integer count);

    /**
     * 查找下级商品分类
     *
     * @param productCategory
     *            地区
     * @param recursive
     *            是否递归
     * @param count
     *            数量
     * @return 下级商品分类
     */
    List<ProductCategory> findChildren(ProductCategory productCategory, boolean recursive, Integer count);

}