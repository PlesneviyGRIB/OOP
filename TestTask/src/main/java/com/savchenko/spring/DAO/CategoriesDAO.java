package com.savchenko.spring.DAO;

import com.savchenko.spring.models.Banner;
import com.savchenko.spring.models.Category;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class CategoriesDAO {
    private final BannersDAO bannersDAO;
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoriesDAO(SessionFactory sessionFactory, BannersDAO bannersDAO) {
        this.sessionFactory = sessionFactory;
        this.bannersDAO = bannersDAO;
    }

    public List<Category> getAll(String filter){
        List<Category> categories = sessionFactory.getCurrentSession().createCriteria(Category.class).list();
        categories = categories.stream().filter(category -> !category.isDeleted()).toList();
        return filter==null? categories: categories.stream().filter(category -> category.getName().toUpperCase().contains(filter.toUpperCase())).toList();
    }

    public Category getById(int id){
        Category category = sessionFactory.getCurrentSession().get(Category.class, id);
        return category.isDeleted() ? null : category;
    }

    public void add(Category category){
        sessionFactory.getCurrentSession().persist(category);
    }

    public void update(int id, Category newCategory){
        Category category = getById(id);
        if(category != null){
            bannersDAO.updateLinkedCategory(category.getName(), newCategory.getName());
            category.setName(newCategory.getName());
            category.setRequestId(newCategory.getRequestId());
            sessionFactory.getCurrentSession().update(category);
        }
    }

    public boolean hasCollision(Category category){
        return getAll(null).stream().filter(cat -> (cat.getName().equalsIgnoreCase(category.getName()) || cat.getRequestId().equalsIgnoreCase(category.getRequestId())) && (cat.getId() != category.getId())).toList().size() != 0;
    }

    public boolean delete(int id){
        if(bannersDAO.getAll(null).stream().anyMatch(banner -> banner.getCategories().contains(getById(id).getName()))) return false;
        Category category = sessionFactory.getCurrentSession().get(Category.class, id);
        if(category != null) {
            category.delete();
            sessionFactory.getCurrentSession().update(category);
        }
        return true;
    }
}


