package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.service.ApiServiceGetKommune;
import com.example.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommune apiServiceGetKommune;

    @GetMapping("/getkommuner")
    public List<Kommune>getKommuner() {
        List<Kommune> lstKommuner = apiServiceGetKommune.getKommuner();
        return lstKommuner;
    }
}
