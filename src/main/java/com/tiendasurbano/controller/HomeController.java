package com.tiendasurbano.controller;

import com.tiendasurbano.dto.*;
import com.tiendasurbano.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final CatalogService catalogService;

    public HomeController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/catalog")
    public String catalog(@RequestParam(required = false) String q,
                          @RequestParam(required = false) String category,
                          @RequestParam(required = false) Boolean available,
                          Model model) {
        model.addAttribute("products", catalogService.findPublicProducts(new CatalogFilter(q, category, available)));
        model.addAttribute("query", q == null ? "" : q);
        model.addAttribute("category", category == null ? "" : category);
        return "catalog";
    }

    @GetMapping("/product")
    public String productDetail(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("product", catalogService.findPublicProduct(id));
        }
        return "product-detail";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin/dashboard";
    }
}
