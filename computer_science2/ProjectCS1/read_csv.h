#include <iostream> 
#include <fstream>
#include <sstream>

using namespace std;

void read_csv(string filename, string data[]) {
	ifstream in1(filename);
	string row;

	int i = 0;

	while (getline(in1, row)) {
		if (i != 0) {
			data[i-1] = row;
		}
		i += 1;
	}
	in1.close();
}
