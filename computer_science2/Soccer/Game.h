#pragma once

#include <iostream>
#include "Team.h"
#include "Referee.h"

using namespace std;

class Game {
	public :
		void start();
		void finish();
	private :
		string score;
		Team teams[2];
		Referee referees[4];
};

