package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.AdImage;
import com.codeup.codeupspringblog.repositories.AdRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {
    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        model.addAttribute("ads", adDao.findAll());
        return "ads/ad";
    }

    @GetMapping("/ads/by/{title}")
    public String adShow(@PathVariable String title, Model model) {
        model.addAttribute("title", adDao.findByTitle(title));
        return"ads/adsShow";
    }


    @GetMapping("/ads/{id}")
    public String singleAd(@PathVariable long id, Model model){
        Ad ad = adDao.findById(id).get();

        // if no images, assign a placeholder
        if(ad.getImages().isEmpty()) {
            // create new list of AdImage objects to assign to the Ad
            List<AdImage> images = new ArrayList<>();
            //
            images.add(new AdImage("https://via.placeholder.com/150", ad));

            ad.setImages(images);
            adDao.save(ad);
        }

        model.addAttribute("ad", ad);

        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String adCreateForm(){
        return "ads/create";
    }

    @PostMapping(path = "/ads/create")
    public String adCreateSubmit(@RequestParam String title, @RequestParam String description){
        Ad newAd = new Ad(1, title, description);

        adDao.save(newAd);

        return "redirect:/ads";
    }




}
