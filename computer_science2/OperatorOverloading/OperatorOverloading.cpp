#include <iostream>

#include "Person.h"

using namespace std;

ostream& operator<<(ostream& os, Person& p) {
	return os << p.getLastname();
}

int main() {
	Person p1("Hans", "Meier");
	Person p2("Hilda", "Henzen");
	p2 = p1 + p2;
	p2.printName();

	if (p1 == p2) {
		cout << "The " << p1 << "'s are married!" << endl;
	}





	return 0;
}