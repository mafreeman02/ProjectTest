/*
  VideoStore.cpp

  Basic operations of a video store.

  John I. Doe
  jdoe@cs.kent.edu
*/

#include <iostream>

#include "Movie.hpp"
#include "Rental.hpp"
#include "Customer.hpp"

int main() {

  // Create movies
  Movie lotr("Lord of the Rings", Movie::REGULAR);
  Movie hp("Harry Potter", Movie::CHILDRENS);

  // Create rentals of these movies
  Rental r1(lotr, 10);
  Rental r2(hp, 5);

  // Create a customer with some rentals
  Customer customer("Fred");
  customer.addRental(r1);
  customer.addRental(r2);

  // Output Fred's statement
  std::cout << customer.statement();

  return 0;
}
