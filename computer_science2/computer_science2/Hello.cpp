#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;
int main() {
	ifstream in1("friends.txt");
	std::string row;
	while (getline(in1, row)) {
		cout << "Friend: " << row << endl;
	}
	in1.close();
}

#include <iostream>
#include <fstream>
using namespace std;
int main() {
	ofstream o1("friends.txt");
	o1 << "Monica" << endl;
	o1 << "Ross" << endl;
	o1.close();
}

#include <iostream>
using namespace std;

class Vehicle {
public:
	int passengers;
	int fuelcapacity;
	int mpg;
	int range();
};
int Vehicle::range() {
	return mpg * fuelcapacity;
}

int main() {
	Vehicle minivan; // creates a Vehicle object
	// called minivan
	minivan.passengers = 7; // assign values to members
	minivan.fuelcapacity = 16;
	minivan.mpg = 21;
	cout << "Minivan can carry " << minivan.passengers <<
		" with a range of " << minivan.range() << endl;
	return 0;
}
