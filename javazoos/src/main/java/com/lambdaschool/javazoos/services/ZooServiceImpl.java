package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Animal;
import com.lambdaschool.javazoos.models.Telephone;
import com.lambdaschool.javazoos.models.Zoo;
import com.lambdaschool.javazoos.models.ZooAnimal;
import com.lambdaschool.javazoos.repository.AnimalRepository;
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

    @Autowired
    AnimalRepository animalRepository;

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

    // Handles PUT and POST
    @Transactional
    @Override
    public Zoo save(Zoo zoo)
    {
        Zoo newZoo = new Zoo();

        if(zoo.getZooid() != 0)
        {
            zooRepository.findById(zoo.getZooid())
                .orElseThrow(() -> new EntityNotFoundException("Zoo " + zoo.getZooid() + " Not Found"));
            newZoo.setZooid(zoo.getZooid());
        }
        newZoo.setZooname(zoo.getZooname());

        newZoo.getTelephones().clear();
        for (Telephone t : zoo.getTelephones())
        {
            Telephone newTelephone = new Telephone();
            newTelephone.setPhonetype(t.getPhonetype());
            newTelephone.setPhonenumber(t.getPhonenumber());
            newTelephone.setZoo(newZoo);
            newZoo.getTelephones().add(newTelephone);
        }

        for (ZooAnimal a : zoo.getAnimals())
        {
            Animal newAnimal = animalRepository.findAnimalByAnimaltype(a.getAnimal().getAnimaltype());
            if (newAnimal == null)
            {
                throw new EntityNotFoundException("Animal type not found");
            }
            newAnimal.setAnimaltype(a.getAnimal().getAnimaltype());
            ZooAnimal newZooAnimal = new ZooAnimal();
            newZooAnimal.setAnimal(newAnimal);
            newZooAnimal.setIncomingzoo(a.getIncomingzoo());
            newZoo.getAnimals().add(newZooAnimal);
        }

        return zooRepository.save(newZoo);
    }

    @Transactional
    @Override
    public void delete(long zooid)
    {
        if(zooRepository.findById(zooid).isPresent())
        {
            zooRepository.deleteById(zooid);
        } else
        {
            throw new EntityNotFoundException("Zoo " + zooid + "Not Found");
        }
    }
}
