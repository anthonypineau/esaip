#pragma once

#include <iostream>

using namespace std;

class Animal
{
	protected:
		string name;
		int age;
	public :
		Animal();
		Animal(string name);
		Animal(string name, int age);
		virtual void makeSound();
		string getName();
		int getAge();
};

