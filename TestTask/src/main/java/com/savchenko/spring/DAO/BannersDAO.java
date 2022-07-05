package com.savchenko.spring.DAO;

import com.savchenko.spring.models.Banner;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class BannersDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BannersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Banner> getAll(String filter){
        List<Banner> banners = sessionFactory.getCurrentSession().createCriteria(Banner.class).list();
        banners = banners.stream().filter(banner -> !banner.isDeleted()).toList();
        return filter==null? banners: banners.stream().filter(banner -> banner.getName().toUpperCase().contains(filter.toUpperCase())).toList();
    }
    public Banner getById(int id){
        Banner banner = sessionFactory.getCurrentSession().get(Banner.class, id);
        return banner.isDeleted() ? null : banner;
    }

    public void add(Banner banner){
        sessionFactory.getCurrentSession().persist(banner);
    }

    public void update(int id, Banner newBanner){
        Banner banner = sessionFactory.getCurrentSession().get(Banner.class, id);
        if(banner != null) {
            banner.setName(newBanner.getName());
            banner.setText(newBanner.getText());
            banner.setCategories(newBanner.getCategories());
            banner.setPrice(newBanner.getPrice());
            sessionFactory.getCurrentSession().update(banner);
        }
    }

    public void updateLinkedCategory(String oldName, String categoryName){
        getAll(null).stream().filter(ban-> ban.getCategories().contains(oldName)).forEach(ban -> {
            ban.setCategories(ban.getCategories().replace(oldName,categoryName));
            update(ban.getId(),ban);
        });
    }

    public boolean hasNameCollision(Banner banner){
        return getAll(null).stream().filter(ban -> ban.getName().equalsIgnoreCase(banner.getName()) && (ban.getId() != banner.getId())).toList().size() != 0;
    }

    public void delete(int id){
        Banner banner = sessionFactory.getCurrentSession().get(Banner.class, id);
        if(banner != null) {
            banner.delete();
            sessionFactory.getCurrentSession().update(banner);
        }
    }
}
