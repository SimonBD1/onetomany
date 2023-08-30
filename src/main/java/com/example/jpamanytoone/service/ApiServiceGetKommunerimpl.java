package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.repository.KommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetKommunerimpl implements ApiServiceGetKommune{
    private final RestTemplate restTemplate;

    public ApiServiceGetKommunerimpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String regionUrl = "https://api.dataforsyningen.dk/kommuner";

    @Autowired
    KommuneRepository kommuneRepository;

    private void saveKommuner(List<Kommune> kommuner) {
        kommuner.forEach(reg -> kommuneRepository.save(reg));
    }

    @Override
    public List<Kommune> getKommuner() {
        List<Kommune> lst = new ArrayList<>();
        ResponseEntity<List<Kommune>> regionResponse = restTemplate.exchange(regionUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Kommune>>() {
        });
        List<Kommune> kommuner = regionResponse.getBody();
        saveKommuner(kommuner);
        return kommuner;
    }
}
