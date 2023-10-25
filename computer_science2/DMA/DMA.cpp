#include <iostream>

#include "Animal.h"
#include "Lion.h"
#include "Dog.h"
#include "Human.h"

int main()
{
    //Lion* lion = new Lion("Simba", 5);
    //lion->makeSound();

    Animal* leo = new Lion("Leo", 2);
    Animal* poodle = new Dog("Poodie", false);
    leo->makeSound();
    
    Human petra("Petra");
    petra.addPet(leo);
    petra.addPet(poodle);
    petra.petSounds();
}
