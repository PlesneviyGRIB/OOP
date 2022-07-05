package com.savchenko.spring.supportive;

import com.savchenko.spring.DAO.BannersDAO;
import com.savchenko.spring.DAO.CategoriesDAO;
import com.savchenko.spring.DAO.LogsDAO;
import com.savchenko.spring.models.Banner;
import com.savchenko.spring.models.Category;
import com.savchenko.spring.models.LogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

@Component
public class BannerForResponse {
    private final BannersDAO bannersDAO;
    private final CategoriesDAO categoriesDAO;
    private final LogsDAO logsDAO;

    @Autowired
    public BannerForResponse(BannersDAO bannersDAO, CategoriesDAO categoriesDAO, LogsDAO logsDAO){
        this.bannersDAO = bannersDAO;
        this.categoriesDAO = categoriesDAO;
        this.logsDAO = logsDAO;
    }

    public Banner next(String[] params, String userAgent, String ip){
        return highestPrice(matcher(params, userAgent, ip));
    }

    private Banner highestPrice(List<Banner> banners){
        Comparator<Banner> comparator = (o1, o2) -> {
            Double d1 = o1.getPrice();
            Double d2 = o2.getPrice();
            return d1.compareTo(d2);
        };
        return banners.stream().max(comparator).orElse(null);
    }

    private List<Banner> matcher(String[] params, String userAgent, String ip){
        List<Banner> banners = bannersDAO.getAll(null);
        List<Banner> shownBanners = shownTodayBanners(userAgent, ip).stream().map(id -> bannersDAO.getById(id)).toList();
        List<Banner> res = new ArrayList<>(banners);
        res.removeAll(shownBanners);
        return res.stream().filter(ban -> matchParams(ban.getCategories(), params)).toList();
    }

    private List<Integer> shownTodayBanners(String userAgent, String ip){
        List<LogRecord> logRecords = logsDAO.getAll().stream().filter(record -> record.getIp() != null).filter(record -> record.getUserAgent().equals(userAgent) && record.getIp().equals(ip)).toList();
        return logRecords.stream().filter(record -> checkDate(record.getTimeStamp())).map(record -> record.getBannerId()).toList();
    }

    private boolean checkDate(Timestamp timestamp){
        Date date = java.sql.Date.valueOf(LocalDate.now());
        Date logDate = new Date(timestamp.getTime());
        return date.toString().equals(logDate.toString());
    }

    private boolean matchParams(String categories, String[] params){
        String[] categoriesNames = categories.split(",");
        List<Category> cat = categoriesDAO.getAll(null).stream().filter(category -> Arrays.stream(categoriesNames).anyMatch(name -> name.equals(category.getName()))).toList();
        return Arrays.stream(params).anyMatch(param -> cat.stream().anyMatch(category -> category.getRequestId().equals(param)));
    }
}
