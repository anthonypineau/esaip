#include "Expense.h"

Expense::Expense(Date date, double amount, string type) {
	this->date = date;
	this->amount = amount;
	if (type == "groceries") {
		this->expenseType = this->GROCERIES;
	}
	else if (type == "shoes") {
		this->expenseType = this->SHOES;
	}
	else if (type == "clothes") {
		this->expenseType = this->CLOTHES;
	}
	else {
		this->expenseType = this->MISCELLANEOUS;
	}
}

void Expense::print() {
	cout << date.print() << "\t" << setiosflags(ios::left) << setw(20) << printExpenseType() << amount << endl;
}

double Expense::getAmount() {
	return this->amount;
}

int Expense::getExpenseType() {
	return this->expenseType;
}

string Expense::printExpenseType() {
	string stringExpenseType = "";

	switch (expenseType) {
		case GROCERIES:
			stringExpenseType = "GROCERIES";
			break;
		case CLOTHES:
			stringExpenseType = "CLOTHES";
			break;
		case SHOES:
			stringExpenseType = "SHOES";
			break;
		case MISCELLANEOUS:
			stringExpenseType = "MISCELLANEOUS";
			break;
	}

	return stringExpenseType;
}