#pragma once

#include <iostream>

using namespace std;

class Date
{
	public:
		Date();
		Date(string year, string month, string day);
		string print();
	private:
		string year;
		string month;
		string day;
};

