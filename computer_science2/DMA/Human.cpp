#include "Human.h"

Human::Human() {

}

Human::Human(string name) {
	this->name = name;
}

void Human::petSounds() {
	for (int i = 0; i < countPet; i++) {
		pets[i]->makeSound();
	}
}

string Human::getName() {
	return this->name;
}

void Human::addPet(Animal* pet) {
	pets[countPet++] = pet;
}