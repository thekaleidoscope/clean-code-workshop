package com.movierental;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CustomerTest {


    @Test
    public void newCustomerShouldGetZeroTotalAmount(){

        Customer customer = new Customer("newCustomer");
        String result = customer.statement();
        String[] results = result.split("\n");

        assertTrue(results.length >=3 );

        boolean hasCustomerNameInStatement = results[0].matches(".*\\bnewCustomer\\b");
        assertTrue(hasCustomerNameInStatement);


        boolean totalAmountIs0 = results[results.length - 2].matches(".*\\b0.0\\b");
        assertTrue(totalAmountIs0);

        boolean frequentRenterPointsIs0 = results[results.length - 1].matches(".*\\b0\\b.*");
        assertTrue(frequentRenterPointsIs0);

    }

    @Test
    public void newCustomerWithRentalOneMovieFor1DayShouldGetAppropriateRental(){

        Customer customer = new Customer("newCustomer");
        Movie firstMovie = new Movie("firstMovie",Movie.REGULAR);
        Rental rental = new Rental(firstMovie, 1);
        customer.addRental(rental);


        String result = customer.statement();
        System.out.println(result);
        assertEquals("Rental Record for newCustomer\n" +
                "\tfirstMovie\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points",result);

    }

}