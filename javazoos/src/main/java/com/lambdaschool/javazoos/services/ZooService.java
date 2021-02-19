package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Zoo;

import java.util.List;

public interface ZooService
{
    List<Zoo> findAll();

    Zoo findZooById(long zooid);

    Zoo save(Zoo zoo);

    void delete(long zooid);
}
