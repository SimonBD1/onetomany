package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import com.example.jpamanytoone.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;


    @GetMapping("/getregioner")
    public List<Region> getRegioner() {
        List<Region> lstRegioner = apiServiceGetRegioner.getRegioner();
        return lstRegioner;
    }
    @GetMapping("/kommuneInRegion/{id}")
    public ResponseEntity<Set<Kommune>> getKommunerInRegion(@PathVariable String id) {
        Optional<Region> orgRegion = regionRepository.findById(id);

        if (orgRegion.isPresent()) {
            Region region = orgRegion.get();
            Set<Kommune> kommuner = region.getKommuner();
            return ResponseEntity.ok(kommuner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping ("/region")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region) {
        System.out.println(region);
        return regionRepository.save(region);
    }

    @PutMapping ("/region/{id}")
    public ResponseEntity<Region> putRegion(@PathVariable String id, @RequestBody Region region) {
        Optional<Region> orgRegion = regionRepository.findById(id);
        if(orgRegion.isPresent()) {
            regionRepository.save(region);
            return new ResponseEntity<>(region, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Region(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/region/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable String id) {
        Optional<Region> orgRegion = regionRepository.findById(id);
        if(orgRegion.isPresent()) {
            regionRepository.deleteById(id);
            return  ResponseEntity.ok("Region deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        }
    }
}
