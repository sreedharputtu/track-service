package com.trackservice.controller.catalog;

import com.trackservice.dto.catalog.CatalogDto;
import com.trackservice.service.catalog.CatalogService;
import com.trackservice.util.Validator;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/catalogs")
public class CatalogController {
    private CatalogService catalogService;

    @RequestMapping("/list")
    public String getCatalogs(Model model, @PathParam("storeId") Long storeID) {
        log.info("Store ID: {}", storeID);
        Validator.isStoreIdValid(storeID);
        List<CatalogDto> catalogs = catalogService.getCatalogs(storeID);
        log.info("Catalogs: {}", catalogs);
        model.addAttribute("catalogs", catalogs);
        return "html/catalog_list.html";
    }

    @RequestMapping("/{id}")
    public String getCatalogById(@PathParam("id") Long id, Model model) {
        model.addAttribute("catalog_id", id);
        return "html/catalog.html";
    }

    @RequestMapping("/create")
    public String createCatalog() {
        return "html/catalog.html";
    }

}
