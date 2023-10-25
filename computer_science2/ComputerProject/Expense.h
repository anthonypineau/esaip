#pragma once

#include "Date.h"
#include <iostream>
#include <iomanip>

using namespace std;

class Expense
{
	public:
		Expense();
		Expense(Date date, double amount, string expenseType);
		void print();

		double getAmount();
		
		enum ExpenseType {
			GROCERIES,
			CLOTHES,
			SHOES,
			MISCELLANEOUS
		};

		int getExpenseType();
	private:
		Date date;
		double amount;
		ExpenseType expenseType;

		string printExpenseType();
};

