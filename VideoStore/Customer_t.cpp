/*
  Customer_t.cpp

  Test program for class Customer.

  John I. Doe
  jdoe@cs.kent.edu
*/

#include <iostream>
#include <fstream>
#include <sstream>
#include <cassert>
#include <string>

#include "Customer.hpp"

int main() {

  // no rental
  {
    Customer customer("Fred");

    assert(customer.getName() == "Fred");

    // test using literal string
    assert(customer.statement() == "Rental Record for Fred\nAmount owed is: 0\nYou earned: 0 frequent renter points\n");
  }

  // no rental
  {
    Customer customer("Fred");

    assert(customer.getName() == "Fred");

    // test using external file
    std::ifstream finput("Customer_t.norental.txt");
    std::ostringstream out;
    out << finput.rdbuf();
    
    assert(customer.statement() == out.str());
  }

  // one rental
  {
    Customer customer("Fred");

    assert(customer.getName() == "Fred");

    // one rental
    customer.addRental(Rental(Movie("A", Movie::REGULAR), 1));

    // test using external file
    std::ifstream finput("Customer_t.onerental.txt");
    std::ostringstream out;
    out << finput.rdbuf();
    
    assert(customer.statement() == out.str());
  }

  // two rentals
  {
    Customer customer("Fred");

    // Create movies
    Movie lotr("Lord of the Rings", Movie::REGULAR);
    Movie hp("Harry Potter", Movie::CHILDRENS);

    // Create rentals of these movies
    Rental r1(lotr, 10);
    Rental r2(hp, 5);

    // add two rentals
    customer.addRental(r1);
    customer.addRental(r2);

    // test using external file
    std::ifstream finput("Customer_t.tworental.txt");
    std::ostringstream out;
    out << finput.rdbuf();
    assert(customer.statement() == out.str());
  }    

  return 0;
}
