#include "Lion.h"
Lion::Lion() {

}

Lion::Lion(string name) : Animal(name) {

}

/*
Lion::Lion(string name, int age) : Animal(name, age) {

}*/

Lion::Lion(string name, int hungerLevel) : Animal(name) {
	this->hungerLevel = hungerLevel;
}

void Lion::makeSound() {
	cout << "grrr" << endl;
}