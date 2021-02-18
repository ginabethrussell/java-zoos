package com.lambdaschool.javazoos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="telephones")
public class Telephone extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long phoneid;

    private String phonetype;

    private String phonenumber;

    @ManyToOne
    @JoinColumn(name = "zooid", nullable = false)
    @JsonIgnoreProperties(value = "telephones", allowSetters = true)
    private Zoo zoo;

    public Telephone()
    {
        // default constructor
    }

    // constructor for data.sql
    public Telephone(
        String phonetype,
        String phonenumber,
        Zoo zoo)
    {
        this.phonetype = phonetype;
        this.phonenumber = phonenumber;
        this.zoo = zoo;
    }
}
