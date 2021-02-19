package com.lambdaschool.javazoos.repository;

import com.lambdaschool.javazoos.models.Animal;
import com.lambdaschool.javazoos.views.AnimalZooCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{

    @Query(value="SELECT a.animaltype AS animaltype, a.animalid AS animalid, count(z.zooid) AS countzoos " +
        "FROM animals a LEFT JOIN zooanimals z " +
        "ON a.animalid = z.animalid " +
        "GROUP BY a.animalid " +
        "ORDER BY animaltype", nativeQuery = true)
    List<AnimalZooCount> getAnimalZooList();

    Animal findAnimalByAnimaltype(String type);


}
