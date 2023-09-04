package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.repository.KommuneRepository;
import com.example.jpamanytoone.service.ApiServiceGetKommune;
import com.example.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommune apiServiceGetKommune;

    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/getkommunerapi")
    public List<Kommune>getKommuneAPIr() {
        List<Kommune> lstKommuner = apiServiceGetKommune.getKommuner();
        return lstKommuner;
    }

    @GetMapping ("/kommuner")
    public List<Kommune> getKommuner() {
        List<Kommune> kommuneList = kommuneRepository.findAll();
        return kommuneList;
    }
    @PostMapping("/kommune")
    @ResponseStatus(HttpStatus.CREATED)
    public Kommune postKommune(@RequestBody Kommune kommune) {
        System.out.println(kommune);
        return kommuneRepository.save(kommune);
    }
    @PutMapping ("/kommune/{id}")
    public ResponseEntity<Kommune> putKommune(@PathVariable String id, @RequestBody Kommune kommune) {
        Optional<Kommune> orgKommune = kommuneRepository.findById(id);
        if(orgKommune.isPresent()) {
            kommuneRepository.save(kommune);
            return new ResponseEntity<>(kommune, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Kommune(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/kommune/{id}")
    public ResponseEntity<String> deleteKommune(@PathVariable String id) {
        Optional<Kommune> orgKommune = kommuneRepository.findById(id);
        if(orgKommune.isPresent()) {
            kommuneRepository.deleteById(id);
            return  ResponseEntity.ok("Kommune deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommune not found");
        }
    }
}
