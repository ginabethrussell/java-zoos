package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value="animalService")
public class AnimalServiceImpl implements AnimalService
{
    @Autowired
    AnimalRepository animalRepository;
}
