package com.lambdaschool.javazoos.controllers;

import com.lambdaschool.javazoos.models.Zoo;
import com.lambdaschool.javazoos.services.ZooService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zoos")
public class ZoosController
{
    @Autowired
    private ZooService zooService;

    // Return a list of all zoos with their teleohone numbers and animals
    // http://localhost:2019/zoos/zoos
    @GetMapping(value="/zoos", produces = "application/json")
    public ResponseEntity<?> listAllZoos()
    {
        List<Zoo> zoos = zooService.findAll();
        return new ResponseEntity<>(zoos, HttpStatus.OK);
    }

    // Return a zoo by id
    // http://localhost:2019/zoos/zoo/{zooid}
    @GetMapping(value="/zoo/{zooid}", produces = "application/json")
    public ResponseEntity<?> getZooById(@PathVariable long zooid)
    {
        Zoo zoo = zooService.findZooById(zooid);
        return new ResponseEntity<>(zoo, HttpStatus.OK);
    }
}
