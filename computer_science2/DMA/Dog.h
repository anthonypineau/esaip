#pragma once

#include "Animal.h"

class Dog : public Animal
{
	private :
		bool isWearingCollar;
	public:
		Dog();
		Dog(string name);
		Dog(string name, int age);
		Dog(string name, bool isWearingCollar);
		void makeSound();
};

