/*
  Rental.cpp

  Definition file for Rental class.

  John I. Doe
  jdoe@cs.kent.edu
*/

#include "Rental.hpp"

// constructor
Rental::Rental(const Movie& movie, int days_rented)
      : movie(movie), days(days_rented)
{ }

// days rented
int Rental::getDaysRented() const {

  return days;
}

// movie rented
const Movie& Rental::getMovie() const {

  return movie;
}

double Rental::getCharge() const {

      double thisAmount = 0;
      switch(getMovie().getPriceCode()) {

        case Movie::REGULAR:
                thisAmount += 2;
                if (getDaysRented() > 2)
                    thisAmount += (getDaysRented() - 2) * 1.5;
                break;

        case Movie::NEW_RELEASE:
                thisAmount += getDaysRented() * 3;
                break;

        case Movie::CHILDRENS:
                thisAmount += 1.5;
                if (getDaysRented() > 3)
                    thisAmount += (getDaysRented() - 3) * 1.5;
                break;
      }

  return thisAmount;
}

