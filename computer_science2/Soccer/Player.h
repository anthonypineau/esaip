#pragma once

#include <iostream>
#include "Person.h"

using namespace std;

class Player : Person {
	public :
		void shoot();
		void save();
	private :
		int number;
		string role;
};

