package com.lambdaschool.javazoos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="zoos")
public class Zoo extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zooid;

    @Column(nullable = false, unique = true)
    private String zooname;


    // a list of telephone numbers for the zoo
    @OneToMany(mappedBy="zoo",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value="zoo", allowSetters = true)
    private List<Telephone> telephones = new ArrayList<>();

    // a set for one to many relationship with ZooAnimal Join Table
    @OneToMany(mappedBy="zoo",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnoreProperties(value = "zoo", allowSetters = true)
    private Set<ZooAnimal> animals = new HashSet<>();

    public Zoo()
    {
        // default constructor
    }

    // constructor for seed data
    public Zoo(
        long zooid,
        String zooname)
    {
        this.zooid = zooid;
        this.zooname = zooname;
    }

    public List<Telephone> getTelephones()
    {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones)
    {
        this.telephones = telephones;
    }

    public long getZooid()
    {
        return zooid;
    }

    public void setZooid(long zooid)
    {
        this.zooid = zooid;
    }

    public String getZooname()
    {
        return zooname;
    }

    public void setZooname(String zooname)
    {
        this.zooname = zooname;
    }

    public Set<ZooAnimal> getAnimals()
    {
        return animals;
    }

    public void setAnimals(Set<ZooAnimal> animals)
    {
        this.animals = animals;
    }
}
