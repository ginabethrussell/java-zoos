package com.lambdaschool.javazoos.repository;

import com.lambdaschool.javazoos.models.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{
}
