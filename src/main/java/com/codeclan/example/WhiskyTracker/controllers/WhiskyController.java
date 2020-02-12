package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(){
        List<Whisky> whiskies = whiskyRepository.findAll();
        return new ResponseEntity<>(whiskies, HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhiskyById(@PathVariable Long id){
        Optional<Whisky> whisky = whiskyRepository.findById(id);
        return new ResponseEntity<>(whisky, HttpStatus.OK);
    }

    @PostMapping(value = "/whiskies")
    public ResponseEntity<Whisky> createNewWhisky(@RequestBody Whisky whisky){
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/whiskies")
    public ResponseEntity<HttpStatus> deleteAllWhiskies(){
        whiskyRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/whiskies/{id}")
    public ResponseEntity<Optional> deleteWhiskyById(@PathVariable Long id){
        Optional<Whisky> whisky = whiskyRepository.findById(id);
        whiskyRepository.deleteById(id);
        return new ResponseEntity<>(whisky, HttpStatus.OK);
    }

    @PatchMapping(value = "/whiskies/{id}")
    public ResponseEntity<Whisky> editWhisky(@PathVariable Long id, @RequestBody Whisky whisky){
        if (whisky.getId() == null){
            whisky.setId(id);
        } else if (whisky.getId() != id){
            return new ResponseEntity<>(whisky, HttpStatus.BAD_REQUEST);
        }
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.OK);
    }

    //Custom Paths

    @GetMapping(value = "/whiskies/year")
    public ResponseEntity<List<Whisky>> findWhiskyFromYear(@RequestParam(name = "year") int year){
        List<Whisky> whiskies = whiskyRepository.findByYear(year);
        return new ResponseEntity<>(whiskies, HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/distilleries/{name}")
    public ResponseEntity<List<Whisky>> findAllWhiskiesFromDistillery(@PathVariable String name, @RequestParam(name = "age") int age){
        List<Whisky> whiskies = whiskyRepository.findByAgeAndDistilleryName(age, name);
        return new ResponseEntity<>(whiskies, HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/distilleries/region")
    public ResponseEntity<List<Whisky>> findAllWhiskiesFromDistilleryRegion(@RequestParam(name = "region") String region){
        List<Whisky> whiskies = whiskyRepository.findByDistilleryRegionIgnoreCase(region);
        return new ResponseEntity<>(whiskies, HttpStatus.OK);
    }
}
