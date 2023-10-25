#pragma once

#include <iostream>
#include <list>
#include <fstream>
#include <sstream>
#include "Expense.h"
#include "Date.h"

using namespace std;

class ReadCSV
{
	public:
		static list<Expense> readCSV(string filename);
};

