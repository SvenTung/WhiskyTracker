package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/distilleries")
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(){
        List<Distillery> distilleries = distilleryRepository.findAll();
        return new ResponseEntity<>(distilleries, HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity getDistilleryById(@PathVariable Long id){
        Optional<Distillery> distillery = distilleryRepository.findById(id);
        return new ResponseEntity<>(distillery, HttpStatus.OK);
    }

    @PostMapping(value = "distilleries")
    public ResponseEntity<Distillery> createNewDistillery(@RequestBody Distillery distillery){
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.OK);
    }

    @DeleteMapping(value = "distilleries")
    public ResponseEntity<HttpStatus> deleteAllDistilleries(){
        distilleryRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "distilleries/{id}")
    public ResponseEntity<Optional> deleteDistilleryById(@PathVariable Long id){
        Optional<Distillery> distillery = distilleryRepository.findById(id);
        distilleryRepository.deleteById(id);
        return new ResponseEntity<>(distillery, HttpStatus.OK);
    }

    @PatchMapping(value = "distilleries/{id}")
    public ResponseEntity<Distillery> updateDistillery(@PathVariable Long id, @RequestBody Distillery distillery){
        if (distillery.getId() == null){
            distillery.setId(id);
        } else if (distillery.getId() != id){
            return new ResponseEntity<>(distillery, HttpStatus.BAD_REQUEST);
        }
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.OK);
    }

    //Custom Paths

    @GetMapping(value = "/distilleries/region")
    public ResponseEntity<List<Distillery>> findAllDistilleriesByRegion(@RequestParam(name = "region") String region){
        List<Distillery> distilleries = distilleryRepository.findByRegionIgnoreCase(region);
        return new ResponseEntity<>(distilleries, HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/whiskies")
    public ResponseEntity<List<Distillery>> findAllDistilleriesByWhiskyAge(@RequestParam(name = "age") int age){
        List<Distillery> distilleries = distilleryRepository.findByWhiskiesAge(age);
        return new ResponseEntity<>(distilleries, HttpStatus.OK);
    }

}
