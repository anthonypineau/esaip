#include "Animal.h"

Animal::Animal() {

}

Animal::Animal(string name) {
	this->name = name;
}

Animal::Animal(string name, int age) {
	this->name = name;
	this->age = age;
}

void Animal::makeSound() {
	cout << "Animal makes some sound" << endl;
}

string Animal::getName() {
	return this->name;
}

int Animal::getAge() {
	return this->age;
}