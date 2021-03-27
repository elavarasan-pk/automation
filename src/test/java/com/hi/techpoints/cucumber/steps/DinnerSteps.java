package com.hi.techpoints.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DinnerSteps {
	
	public DinnerSteps() {
		
	}
	
	@When("I am going to have dinner at (.+) in the restaurant (.+)")
	public void checkRestaurantAvailability(String breakfastTime, String restaurantName) {
		
	}
	
	@Then("Hi Waiter, Can you get these items (.+)")
	public void getTheItems(String items) {
		
	}
	
	@Then("Can you bring bill (.+) for the items (.+)")
	public void getTheBill(String outBillAmount, String items) {
		//Storage.put(outBillAmount, "Rs.100");
	}
	
	@And("Can I do payment (.+), (.+)")
	public void doPayment(String billAmount, String cardDetails) {
		
	}

}
