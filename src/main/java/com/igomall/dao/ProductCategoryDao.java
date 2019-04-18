
package com.igomall.dao;

import com.igomall.common.Filter;
import com.igomall.common.Order;
import com.igomall.entity.ProductCategory;

import java.util.List;

/**
 * Dao - 商品分类
 * 
 * @author IGOMALL Team
 * @version 5.0
 */
public interface ProductCategoryDao extends BaseDao<ProductCategory, Long> {

    /**
     * 查找商品分类
     *
     * @param count
     *            数量
     * @param filters
     *            筛选
     * @param orders
     *            排序
     * @return 商品分类
     */
    List<ProductCategory> findList(Integer count, List<Filter> filters, List<Order> orders);

    /**
     * 查找顶级商品分类
     *
     * @param count
     *            数量
     * @return 顶级商品分类
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
     *            商品分类
     * @param recursive
     *            是否递归
     * @param count
     *            数量
     * @return 下级商品分类
     */
    List<ProductCategory> findChildren(ProductCategory productCategory, boolean recursive, Integer count);
}