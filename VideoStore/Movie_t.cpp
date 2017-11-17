/*
  Movie_t.cpp

  Test program for class Movie.

  John I. Doe
  jdoe@cs.kent.edu
*/

#include <cassert>

#include "Movie.hpp"

int main() {

  // regular movie
  {
    Movie movie("A", Movie::REGULAR);

    assert(movie.getTitle() == "A");
    assert(movie.getPriceCode() == Movie::REGULAR);
  }

  // new release
  {
    Movie movie("A", Movie::NEW_RELEASE);

    assert(movie.getTitle() == "A");
    assert(movie.getPriceCode() == Movie::NEW_RELEASE);
  }

  // childrens
  {
    Movie movie("A", Movie::CHILDRENS);

    assert(movie.getTitle() == "A");
    assert(movie.getPriceCode() == Movie::CHILDRENS);
  }

  // longer title
  {
    Movie movie("A B", Movie::REGULAR);

    assert(movie.getTitle() == "A B");
    assert(movie.getPriceCode() == Movie::REGULAR);
  }

  // change price
  {
    Movie movie("A", Movie::NEW_RELEASE);

    assert(movie.getTitle() == "A");
    assert(movie.getPriceCode() == Movie::NEW_RELEASE);

    movie.setPriceCode(Movie::REGULAR);
    assert(movie.getPriceCode() == Movie::REGULAR);
  }

  return 0;
}
