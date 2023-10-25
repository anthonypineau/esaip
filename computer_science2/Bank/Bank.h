#pragma once

#include "Customer.h"

class Bank
{
	private:
		Customer customers[100];
	public:
		virtual void worth() = 0;
};

