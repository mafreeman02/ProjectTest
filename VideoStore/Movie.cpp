/*
  Movie.cpp

  Definition file for Movie class.

  John I. Doe
  jdoe@cs.kent.edu
*/

#include "Movie.hpp"

// constructor
Movie::Movie(const std::string& title, int price_code)
      : title(title), price_code(price_code)
{ }

// movie title
const std::string& Movie::getTitle() const {

  return title;
}

// movie price code
int Movie::getPriceCode() const {

  return price_code;
}

// set movie price code
void Movie::setPriceCode(int new_price_code) {

  price_code = new_price_code;
}
