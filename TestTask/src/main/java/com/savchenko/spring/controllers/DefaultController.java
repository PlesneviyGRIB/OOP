package com.savchenko.spring.controllers;

import com.savchenko.spring.DAO.BannersDAO;
import com.savchenko.spring.DAO.CategoriesDAO;
import com.savchenko.spring.DAO.LogsDAO;
import com.savchenko.spring.models.Banner;
import com.savchenko.spring.models.LogRecord;
import com.savchenko.spring.supportive.BannerForResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/bid")
public class DefaultController {

    private final BannersDAO bannersDAO;
    private final CategoriesDAO categoriesDAO;
    private final LogsDAO logsDAO;
    private final BannerForResponse bannerForResponse;

    @Autowired
    public DefaultController(BannersDAO bannersDAO, CategoriesDAO categoriesDAO, BannerForResponse bannerForResponse, LogsDAO logsDAO){
        this.bannersDAO = bannersDAO;
        this.categoriesDAO = categoriesDAO;
        this.logsDAO = logsDAO;
        this.bannerForResponse = bannerForResponse;
    }

    @GetMapping()
    public String bid(@RequestParam(value = "cat", required = false) String[] params,
                      Model model,
                      HttpServletRequest request,
                      @RequestHeader(value = "User-Agent") String userAgent){
        if(params != null) {
            Banner banner = bannerForResponse.next(params, userAgent, request.getRemoteAddr());
            if(banner != null){
                logsDAO.add(new LogRecord(request.getRemoteAddr(),userAgent, banner.getId(), banner.getCategories(), banner.getPrice()));
                model.addAttribute(banner);
            }
            else {
                logsDAO.add(new LogRecord());
                model.addAttribute("error", "No content available according to such request params");
            }
        }
        model.addAttribute("categories", categoriesDAO.getAll(null));
        return "bid";
    }

    @GetMapping("/logs")
    public String logs(Model model){
        List<LogRecord> logs = logsDAO.getAll();
        Collections.reverse(logs);
        model.addAttribute("logs", logs);
        return "logs";
    }

    @DeleteMapping("/logs")
    public String deleteLogs(){
        logsDAO.delete();
        return "redirect:logs";
    }
}
