package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.views.AnimalZooCount;

import java.util.List;

public interface AnimalService
{
    List<AnimalZooCount> getAnimalZooList();
}
