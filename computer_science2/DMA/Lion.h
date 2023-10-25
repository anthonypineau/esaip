#pragma once

#include "Animal.h"

class Lion : public Animal {
	private :
		int hungerLevel;
	public :
		Lion();
		Lion(string name);
		//Lion(string name, int age);
		Lion(string name, int hungerLevel);
		void makeSound();
};

