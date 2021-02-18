package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.repository.AnimalRepository;
import com.lambdaschool.javazoos.views.AnimalZooCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value="animalService")
public class AnimalServiceImpl implements AnimalService
{
    @Autowired
    AnimalRepository animalRepository;

    @Override
    public List<AnimalZooCount> getAnimalZooList()
    {
       List<AnimalZooCount> animalZooCountList = animalRepository.getAnimalZooList();
       return animalZooCountList;
    }
}
