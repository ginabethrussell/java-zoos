package com.lambdaschool.javazoos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="animals")
public class Animal extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long animalid;

    private String animaltype;

    // a set for one to many relationship with ZooAnimal Join Table
    @OneToMany(mappedBy="animal",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "animal", allowSetters = true)
    private Set<ZooAnimal> zoos = new HashSet<>();

    public Animal()
    {
        // default constructor
    }

    public Animal(
        long animalid,
        String animaltype)
    {
        this.animalid = animalid;
        this.animaltype = animaltype;
    }

    public long getAnimalid()
    {
        return animalid;
    }

    public void setAnimalid(long animalid)
    {
        this.animalid = animalid;
    }

    public String getAnimaltype()
    {
        return animaltype;
    }

    public void setAnimaltype(String animaltype)
    {
        this.animaltype = animaltype;
    }

    public Set<ZooAnimal> getZoos()
    {
        return zoos;
    }

    public void setZoos(Set<ZooAnimal> zoos)
    {
        this.zoos = zoos;
    }
}
