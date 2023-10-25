#include <stdio.h>
#include <stdlib.h>
#include "read_csv.h"

const int COLUMNS = 5; // adapt the number of columns
const int ROWS = 7; // adapt the number of rows in your data file

enum ExpenseType {
    GROCERIES,
    CLOTHES,
    SHOES,
    MISCELLANEOUS
};

typedef struct T_Expense {
    int year;
    int month;
    int day;
    enum ExpenseType type;
    double amount;
} Expense;

void printExpenseType(enum ExpenseType type) {
    switch(type) {
        case GROCERIES:
            printf("Groceries\t");
            break;
        case SHOES:
            printf("Shoes\t\t");
            break;
        case CLOTHES:
            printf("Clothes\t\t");
            break;
        default:
            printf("Miscellaneous\t");
    }
}

void printExpenses(Expense* array, int entries) {
    printf("Expenses List\n");
    printf("========================================\n");
    for(int i=0; i < entries; i++) {
        printf("%02i.%02i.%i\t",array[i].day, array[i].month, array[i].year);
        printExpenseType(array[i].type);
        printf("%.2f\n", array[i].amount);
    }
}

double meanAmount(Expense* array, int entries) {
    double sum = 0;
    for(int i=0; i < entries; i++) {
        sum += array[i].amount;
    }
    return sum / entries;
}

double amountForType(Expense* array, int entries, enum ExpenseType type) {
    double sum = 0;
    for(int i=0; i < entries; i++) {
        if(array[i].type == type)
            sum += array[i].amount;
    }
    return sum;
}

double maximumExpense(Expense* array, int entries) {
    double max = 0;
    for(int i=0; i < entries; i++) {
        if(array[i].amount > max)
            max = array[i].amount;
    }
    return max;
}

double minimumExpense(Expense* array, int entries) {
    double min = array[0].amount;
    for(int i=0; i < entries; i++) {
        if(array[i].amount < min)
            min = array[i].amount;
    }
    return min;
}

int main() {
    char* data[COLUMNS][100];
    Expense* expenses = (Expense*)malloc(sizeof(Expense) * ROWS);

    read_csv("expenses.csv", data);

    for (int i = 0; i < ROWS; i++) {
        expenses[i].year = atoi(data[0][i]);
        expenses[i].month = atoi(data[1][i]);
        expenses[i].day = atoi(data[2][i]);
        expenses[i].amount = atof(data[4][i]);
        if(strcmp(data[3][i], "groceries") == 0) {
            expenses[i].type = GROCERIES;
        } else if(strcmp(data[3][i], "shoes") == 0) {
            expenses[i].type = SHOES;
        } else if(strcmp(data[3][i], "clothes") == 0) {
            expenses[i].type = CLOTHES;
        } else {
            expenses[i].type = MISCELLANEOUS;
        }
    }

    printExpenses(expenses, ROWS);
    printf("========================================\n");
    printf("Mean amount spent: %.2f\n", meanAmount(expenses, ROWS));
    printf("Amount spent on groceries: %.2f\n", amountForType(expenses, ROWS, GROCERIES));
    printf("Amount spent on shoes: %.2f\n", amountForType(expenses, ROWS, SHOES));
    printf("Amount spent on clothes: %.2f\n", amountForType(expenses, ROWS, CLOTHES));
    printf("Maximum amount spent: %.2f\n", maximumExpense(expenses, ROWS));
    printf("Minimum amount spent: %.2f\n", minimumExpense(expenses, ROWS));

    return 0;
}
