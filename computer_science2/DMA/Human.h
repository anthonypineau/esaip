#pragma once

#include <iostream>
#include "Animal.h"

using namespace std;

class Human
{
	private :
		string name;
		Animal* pets[10];
		int countPet = 0;
	public :
		Human();
		Human(string name);
		void petSounds();
		string getName();
		void addPet(Animal* pet);
};

