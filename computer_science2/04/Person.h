#pragma once
#include <iostream>

using namespace std;

class Person {
	public :
		void setName(string name);
		string getName();
	private :
		string name;
};