#include <iostream>
#include "Menu.h"

using namespace std;

int main() {    
    Menu* menu = new Menu();
    menu->initialiseData();

    menu->start();    
    while (menu->isRunning()) {
        menu->displayMenu();
    }
}