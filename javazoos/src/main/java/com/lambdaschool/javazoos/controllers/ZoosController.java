package com.lambdaschool.javazoos.controllers;

import com.lambdaschool.javazoos.models.Zoo;
import com.lambdaschool.javazoos.services.ZooService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    // http://localhost:2019/zoos/zoo
    // Post route to create a new zoo, no body data returned, status Created, return location in header
    @PostMapping(value="/zoo", consumes="application/json")
    public ResponseEntity<?> addZoo(@Valid @RequestBody Zoo newZoo)
    {
        newZoo.setZooid(0);
        newZoo= zooService.save(newZoo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newZooURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zooid}")
            .buildAndExpand(newZoo.getZooid())
            .toUri();
        responseHeaders.setLocation(newZooURI);

        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/zoos/zoo
    // Post route to create a new zoo, no body data returned, status Created, return location in header
    @PutMapping(value="/zoo/{zooid}", consumes="application/json")
    public ResponseEntity<?> updateZoo(@Valid @RequestBody Zoo updatedZoo, @PathVariable long zooid)
    {
        updatedZoo.setZooid(zooid);
        updatedZoo =  zooService.save(updatedZoo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/zoo/{zooid}")
    public ResponseEntity<?> deleteZoo(@PathVariable long zooid)
    {
        zooService.delete(zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

