#pragma once

#include <iostream>

using namespace std;

class Person
{
	private:
		string lastname;
		string firstname;
		bool married=false;
	public:
		Person();
		Person(string firstname, string lastname);

		string getLastname();

		void printName();

		const Person operator+ (Person& p) const;

		const bool operator== (const Person& p) const;

		//const ostream& operator<<(ostream& os) const;
};

