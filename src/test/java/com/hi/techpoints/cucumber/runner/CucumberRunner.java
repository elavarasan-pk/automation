package com.hi.techpoints.cucumber.runner;

import java.util.List;

import org.junit.runner.RunWith;

import com.hi.techpoints.cucumber.exception.CucumberException;
import com.hi.techpoints.cucumber.util.TestRepository;


/**
 * @author Elavarasan
 * 
 */
@RunWith(CreateCucumberRuntime.class)
public class CucumberRunner   {

	public List<String> getAllFeatures() throws  CucumberException {
		return TestRepository.getAllFeatures();
	} 
	
}
