#############################################################################
# VideoStore
#

OBJS=VideoStore.o Movie.o Rental.o Customer.o
TOBJS=Movie_t.o Rental_t.o Customer_t.o
EXE=VideoStore Movie_t Rental_t Customer_t

GCC= g++
CFLAGS=-Wall

.PHONY : all
all : $(EXE) test

# VideoStore
.PHONY : run
run : VideoStore
	@./VideoStore

VideoStore : $(OBJS)
	$(GCC) $^ -o $@

VideoStore.o: VideoStore.cpp Movie.hpp Rental.hpp Customer.hpp
	$(GCC) $(CFLAGS) -c $<

# Movie
Movie.o: Movie.cpp Movie.hpp
	$(GCC) $(CFLAGS) -c $<

Movie_t : Movie_t.o Movie.o
	$(GCC) $^ -o $@

Movie_t.o: Movie_t.cpp Movie.hpp
	$(GCC) $(CFLAGS) -c $<

# Rental
Rental.o: Rental.cpp Rental.hpp Movie.hpp
	$(GCC) $(CFLAGS) -c $<

Rental_t : Rental_t.o Rental.o Movie.o
	$(GCC) $^ -o $@

Rental_t.o: Rental_t.cpp Rental.hpp
	$(GCC) $(CFLAGS) -c $<

# Customer
Customer.o: Customer.cpp Customer.hpp Movie.hpp Rental.hpp
	$(GCC) $(CFLAGS) -c $<

Customer_t : Customer_t.o Customer.o Rental.o Movie.o
	$(GCC) $^ -o $@

Customer_t.o: Customer_t.cpp Customer.hpp
	$(GCC) $(CFLAGS) -c $<

# clean
.PHONY : clean
clean : cleanobjects
	rm -f $(EXE)

.PHONY : cleanobjects
cleanobjects :
	rm -f $(OBJS) $(TOBJS)

# unit testing
.PHONY : test
test : Movie_t Rental_t Customer_t
	@./Movie_t
	@./Rental_t
	@./Customer_t
