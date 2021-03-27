package com.hi.techpoints.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.hi.techpoints.cucumber.util.Storage;

import cucumber.api.java.en.But;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CricketSteps {
	
	private Map<String, String> timeSlot = new HashMap<>();
	private Map<String, String> opponentMap = new HashMap<>();
	private Map<String, String> groundMap = new HashMap<>();
	
	public CricketSteps() {
		timeSlot.put("9:30 AM", "Booked");
		timeSlot.put("12:30 PM", "Booked");
		timeSlot.put("3:30 PM", "Available");
		opponentMap.put("Chennai", "Booked");
		opponentMap.put("Trichy", "Available");
		groundMap.put("NA Ground", "Available");
		groundMap.put("Chennai Ground", "Available");
	}
	
	@When("I am going to play cricket at (.+) in the ground (.+)")
	public void checkGroundAvailability(String playTime, String groundName) {
		String time = String.valueOf(Storage.get(playTime));
	    String name = String.valueOf(Storage.get(groundName));
	    log.info("Play time = {}, Ground name = {}", time, name);
	    assertTrue("Check play time", timeSlot.containsKey(time));
	    assertTrue("Check ground name", groundMap.containsKey(name));
	}
	
	@Then("Can you find opponent team (.+)")
	public void findOpponentTeam(String outOpponentTeam) {
		String opponentName = null;
		for (String key:opponentMap.keySet()) {
			if("Available".equalsIgnoreCase(opponentMap.get(key))) {
              opponentName = key;
              break;
			}
		}
		log.info("Opponent Name = {}", opponentName);
		Storage.put(outOpponentTeam, opponentName); 
		assertNotNull("Check opponent team is found", opponentName);
	}
	
	@But("Is there ground available (.+) at (.+)")
	public void isSlotAvailable(String groundName, String playTime) {
		String time = String.valueOf(Storage.get(playTime));
	    String ground = String.valueOf(Storage.get(groundName));
	    
	    assertEquals("Check play time is available", "Available", timeSlot.get(time));
	    assertEquals("Check ground is available", "Available", groundMap.get(ground));
	}
}
