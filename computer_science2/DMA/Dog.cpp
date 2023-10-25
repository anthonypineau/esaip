#include "Dog.h"

Dog::Dog() {

}

Dog::Dog(string name) : Animal(name) {

}

Dog::Dog(string name, int age) : Animal(name, age) {

}

Dog::Dog(string name, bool isWearingCollar) : Animal(name) {
	this->isWearingCollar = isWearingCollar;
}

void Dog::makeSound() {
	cout << "woof" << endl;
}