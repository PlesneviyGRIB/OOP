package com.savchenko.testProject.dao;

import com.savchenko.testProject.models.Item;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ItemsDAO {

    private final SessionFactory sessionFactory;

    public ItemsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Item> showAll(){
        return sessionFactory.getCurrentSession().createCriteria(Item.class).list();
    }

    public Item showItem(int id){
        return sessionFactory.getCurrentSession().get(Item.class, id);
    }

    public void addItem(Item item){
        sessionFactory.getCurrentSession().persist(item);
    }

    public void update(int id, Item item){
        Item renewableItem = sessionFactory.getCurrentSession().get(Item.class, id);
        renewableItem.setOnlineOrder(item.getOnlineOrder());
        renewableItem.setManufacture(item.getManufacture());
        renewableItem.setCredit(item.getCredit());
        renewableItem.setCountry(item.getCountry());
        renewableItem.setType(item.getType());
        sessionFactory.getCurrentSession().update(renewableItem);
    }

    public void delete(int id){
        Query query = sessionFactory.getCurrentSession().createSQLQuery("delete from item where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
