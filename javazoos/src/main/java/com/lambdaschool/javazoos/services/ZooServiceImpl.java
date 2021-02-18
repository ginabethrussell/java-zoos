package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Zoo;
import com.lambdaschool.javazoos.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "zooService")
public class ZooServiceImpl implements ZooService
{
    @Autowired
    ZooRepository zooRepository;

    @Override
    public List<Zoo> findAll()
    {
        List<Zoo> zoos = new ArrayList<>();
        zooRepository.findAll()
            .iterator()
            .forEachRemaining(zoos::add);
        return zoos;
    }

    @Override
    public Zoo findZooById(long zooid)
    {
        return zooRepository.findById(zooid)
        .orElseThrow(() -> new EntityNotFoundException("Zoo " + zooid + " Not Found"));
    }
}
