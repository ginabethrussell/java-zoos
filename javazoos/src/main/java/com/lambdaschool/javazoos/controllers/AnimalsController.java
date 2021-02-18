package com.lambdaschool.javazoos.controllers;

import com.lambdaschool.javazoos.services.AnimalService;
import com.lambdaschool.javazoos.views.AnimalZooCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalsController
{
    @Autowired
    AnimalService animalService;

    // Returns animal type and id and count of zoos where animals are found
    @GetMapping(value = "/count", produces = "application/json")
    public ResponseEntity<?> getAnimalZooCount()
    {
        List<AnimalZooCount> animalZooCountList = animalService.getAnimalZooList();
        return new ResponseEntity<>(animalZooCountList, HttpStatus.OK);
    }
}
