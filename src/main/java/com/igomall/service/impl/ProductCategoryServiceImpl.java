
package com.igomall.service.impl;

import com.igomall.dao.ProductCategoryDao;
import com.igomall.entity.ProductCategory;
import com.igomall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Service - 商品分类
 * 
 * @author IGOMALL Team
 * @version 5.0
 */
@Service
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory, Long> implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findRoots() {
        return productCategoryDao.findRoots(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findRoots(Integer count) {
        return productCategoryDao.findRoots(count);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findParents(ProductCategory area, boolean recursive, Integer count) {
        return productCategoryDao.findParents(area, recursive, count);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findChildren(ProductCategory area, boolean recursive, Integer count) {
        return productCategoryDao.findChildren(area, recursive, count);
    }

    @Override
    @Transactional
    @CacheEvict(value = "productCategory", allEntries = true)
    public ProductCategory save(ProductCategory area) {
        Assert.notNull(area,"");

        setValue(area);
        return super.save(area);
    }

    @Override
    @Transactional
    @CacheEvict(value = "productCategory", allEntries = true)
    public ProductCategory update(ProductCategory area) {
        Assert.notNull(area,"");

        setValue(area);
        for (ProductCategory children : productCategoryDao.findChildren(area, true, null)) {
            setValue(children);
        }
        return super.update(area);
    }

    @Override
    @Transactional
    @CacheEvict(value = "productCategory", allEntries = true)
    public ProductCategory update(ProductCategory area, String... ignoreProperties) {
        return super.update(area, ignoreProperties);
    }

    @Override
    @Transactional
    @CacheEvict(value = "productCategory", allEntries = true)
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "productCategory", allEntries = true)
    public void delete(Long... ids) {
        super.delete(ids);
    }

    @Override
    @Transactional
    @CacheEvict(value = "productCategory", allEntries = true)
    public void delete(ProductCategory area) {
        super.delete(area);
    }

    /**
     * 设置值
     *
     * @param productCategory
     *            地区
     */
    private void setValue(ProductCategory productCategory) {
        if (productCategory == null) {
            return;
        }
        ProductCategory parent = productCategory.getParent();
        if (parent != null) {
            productCategory.setTreePath(parent.getTreePath() + parent.getId() + ProductCategory.TREE_PATH_SEPARATOR);
        } else {
            productCategory.setTreePath(ProductCategory.TREE_PATH_SEPARATOR);
        }
        productCategory.setGrade(productCategory.getParentIds().length);
    }

}