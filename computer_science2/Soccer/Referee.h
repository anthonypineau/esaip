#pragma once

#include <iostream>
#include "Person.h"
#include "Player.h"

using namespace std;

class Referee : Person {
	public :
		void giveYellowCard(Player player);
		void giveRedCard(Player player);
	private:
		string role;
};

