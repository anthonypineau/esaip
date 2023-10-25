#include "ExpenseContainer.h"

void ExpenseContainer::setExpenses(list<Expense> expenses) {
	this->expenses = expenses;
}

void ExpenseContainer::addExpense(Expense expense) {
	this->expenses.push_back(expense);
}

void ExpenseContainer::printExpenses() {
	//for_each(this->expenses.begin(), this->expenses.end(), [](Expense expense) { cout << expense.getAmount() << endl; });

	for (Expense expense : expenses) {
		expense.print();
	}
}

double ExpenseContainer::meanAmount() {
	double mean = 0.0;

	for (Expense expense : expenses) {
		mean += expense.getAmount();
	}

	mean = mean / this->expenses.size();
	
	return mean;
}
double ExpenseContainer::amountForType(int expenseType) {
	double mean = 0.0;
	int count = 0;

	for (Expense expense : expenses) {
		if (expense.getExpenseType() == expenseType) {
			mean += expense.getAmount();
			count += 1;
		}
	}

	mean = mean / count;

	return mean;
}
Expense ExpenseContainer::miniumExpense() {
	Expense min = expenses.front();
	for (Expense expense : expenses) {
		if (expense.getAmount() < min.getAmount()) {
			min = expense;
		}
	}

	return min;
}
Expense ExpenseContainer::maximumExpense() {
	Expense max(Date("2022","01","01"), 0.0, "misc");
	for (Expense expense : expenses) {
		if (expense.getAmount() > max.getAmount()) {
			max = expense;
		}
	}

	return max;
}