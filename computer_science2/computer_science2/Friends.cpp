#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

int main() {
    std::string strings[10];
    int count = 0;
    int max = 5;
    ifstream in1("friends.txt");
    std::string row;
    cout << "These are your friends:" << endl;
    while (getline(in1, row)) {
        cout << row << endl;
        strings[count] = row;
        count++;
    }
    in1.close();

    cout << "Enter some new friends!" << endl;
    for (int i = count; i < max; i++) {
        cin >> strings[i];
        count++;
    }

    ofstream o1("friends.txt");
    for (int i = 0; i < count; i++) {
        o1 << strings[i] << endl;
    }
    o1.close();
}
