#pragma once

#include <list>
#include<algorithm>
#include "Expense.h"

class ExpenseContainer
{
public:
	void setExpenses(list<Expense> expenses);
	void addExpense(Expense expense);
	void printExpenses();
	double meanAmount();
	double amountForType(int expenseType);
	Expense miniumExpense();
	Expense maximumExpense();
private:
	list<Expense> expenses;

};

