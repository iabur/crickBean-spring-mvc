package com.crickbean.application.controllers;

import com.crickbean.application.dto.CountryDto;
import com.crickbean.application.exceptions.ResourceNotFoundException;
import com.crickbean.application.model.Country;
import com.crickbean.application.service.CountryService;
import com.crickbean.application.service.UserService;
import com.crickbean.application.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private ServletContext context;

    @Autowired
    private UserService userService;

    @GetMapping("/country/add")
    public String addCountry(Model model, Authentication authentication) {
        model.addAttribute("country", new CountryDto());
        model.addAttribute("allCountry", countryService.getAllCountry());
        model.addAttribute("user", userService.getLoggedUser(authentication));
        model.addAttribute("message", "Insert a new country");
        return "admin/country/countries";
    }

    @PostMapping("/country/add")
    public String addCountry(@ModelAttribute(name = "country") CountryDto countryDto, @RequestParam("file") MultipartFile file, Model model) {
        if (countryDto.getCountryName() == null || countryDto.getCountryName().trim().isEmpty()) {
            throw new ResourceNotFoundException("Country not found");
        }
        if (file.isEmpty()) {
            throw new ResourceNotFoundException("Country flag not found");
        }
        try {
            byte[] bytes = file.getBytes();
            String getAbsoluteFilePath = Constants.UPLOADED_FOLDER;
            Path path = Paths.get(getAbsoluteFilePath + file.getOriginalFilename());
            File dir = Paths.get(getAbsoluteFilePath).toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Files.write(path, bytes);
            countryDto.setCountryLogo("/images/" + file.getOriginalFilename());
            countryService.save(countryDto);
            model.addAttribute("message", "Country added successfully");
            return "redirect:/country/add";
        } catch (IOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    @GetMapping("/country/update")
    public String updateCountry(@RequestParam(name = "countryId") Long id, Model model, Authentication authentication) {
        model.addAttribute("countryId", id);
        model.addAttribute("country", new CountryDto());
        model.addAttribute("user", userService.getLoggedUser(authentication));
        return "admin/country/editCountries";
    }

    @PostMapping("/country/update")
    public String updateCountry(@ModelAttribute(name = "country") CountryDto countryDto, Model model, @RequestParam("file") MultipartFile file, Authentication authentication) {
        if (countryDto.getCountryName() == null || countryDto.getCountryName().trim().isEmpty()) {
            throw new ResourceNotFoundException("Country not found");
        }
        if (file.isEmpty()) {
            throw new ResourceNotFoundException("Country flag not found");
        }
        try {
            byte[] bytes = file.getBytes();
            String getAbsoluteFilePath = Constants.UPLOADED_FOLDER;
            Path path = Paths.get(getAbsoluteFilePath + file.getOriginalFilename());
            File dir = Paths.get(getAbsoluteFilePath).toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Files.write(path, bytes);
            countryDto.setCountryLogo("/images/" + file.getOriginalFilename());
            System.out.println(countryDto);
            countryService.saveUpdate(countryDto, authentication);
            model.addAttribute("message", "Country updated successfully");
            return "redirect:/country/add";
        } catch (IOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/country/delete")
    public String deleteCountry(@RequestParam(name = "countryId") Long id) {
        countryService.deleteCountryById(id);
        return "redirect:/country/add";
    }

    @GetMapping("/country/show-all")
    public String showAllCountry(Model model, @RequestParam(name = "queryText") String queryText, Authentication authentication) {
        model.addAttribute("country", countryService.searchCountry(queryText));
        model.addAttribute("user", userService.getLoggedUser(authentication));
        return "/country/countries";
    }

}
