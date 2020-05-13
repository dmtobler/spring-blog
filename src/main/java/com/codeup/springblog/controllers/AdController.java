//package com.codeup.springblog.controllers;
//
//import com.codeup.springblog.model.Ad;
//import com.codeup.springblog.repositories.AdRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class AdController {
//
//    // Dependency injection
//    private final AdRepository adDao;
//
//    public AdController(AdRepository adDao) {
//        this.adDao = adDao;
//    }
//
//    @GetMapping("/ads")
//    @ResponseBody
//    public String getAds() {
//        String ads = "<ul>";
//        for (Ad ad : this.adDao.findAll()) {
//            ads += "<li><p>" + ad.getTitle() + "<br>" + ad.getDescription() + "</p></li>";
//        }
//        ads += "</ul>";
//        return ads;
//    }
//}
