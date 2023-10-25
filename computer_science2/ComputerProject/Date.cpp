#include "Date.h"

Date::Date() {
	this->year = "2001";
	this->month = "02";
	this->day = "25";
}

Date::Date(string year, string month, string day) {
	this->year = year;
	this->month = month;
	this->day = day;
}

string Date::print() {
	return year+'-'+month+'-'+day;
}



