#include "Person.h"

Person::Person() {

}

Person::Person(string firstname, string lastname) {
	this->firstname = firstname;
	this->lastname = lastname;
}

string Person::getLastname() {
	return lastname;
}

void Person::printName() {
	cout << firstname << " " << lastname << endl;
}

const Person Person::operator+ (Person& p) const {
		/*Person person;
		person.firstname = this->firstname;
		person.lastname = p.lastname;*/

		Person person(p.firstname, this->lastname);
		person.married = true;

		p.married = true;

		return person;
}

const bool Person::operator== (const Person& p) const {
	return lastname == p.lastname;
}