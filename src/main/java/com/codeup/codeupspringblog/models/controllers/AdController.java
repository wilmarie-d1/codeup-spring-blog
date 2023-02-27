package com.codeup.codeupspringblog.models.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.AdImage;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.AdRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {   // These two next steps are often called dependency injection, where we create a Repository instance and initialize it in the controller class constructor.
    private final AdRepository adDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public AdController(AdRepository adDao, UserRepository userDao, EmailService emailService) {
        this.adDao = adDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String adCreateForm(Model model){
        model.addAttribute("newAd", new Ad());
        return "ads/create";
    }

    @PostMapping(path = "/ads/create")
    public String adCreateSubmit(@ModelAttribute Ad ad){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ad.setOwner(loggedInUser);
        adDao.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // if the logged in user is not the owner of the ad, redirect to /ads
        if(loggedInUser.getId() != adDao.findById(id).get().getOwner().getId()) {
            return "redirect:/ads";
        } else {
            // only the owner of the ad can edit the ad
            model.addAttribute("ad", adDao.findById(id).get());
            return "ads/edit";
        }
    }

    @PostMapping("/ads/{id}/edit")
    public String editAd(@PathVariable long id, @ModelAttribute Ad ad) {
        adDao.save(ad);
        return "redirect:/ads";
    }

    @PostMapping("/ads/{id}/delete")
    public String deleteAd(@PathVariable long id) {
        adDao.deleteById(id);
        return "redirect:/ads";
    }



}