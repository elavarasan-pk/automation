package com.hi.techpoints.cucumber.runner;

import java.util.List;

import org.junit.runner.RunWith;

import com.hi.techpoints.cucumber.exception.OneviewException;
import com.hi.techpoints.cucumber.util.TestRepository;


/**
 * @author Elavarasan
 * 
 */
@RunWith(OneviewCucumber.class)
//@ContextConfiguration(locations = "classpath:oneview-cucumber.xml")
public class OneviewCucumberRunner   {

	public List<String> getAllFeatures() throws  OneviewException {
		return TestRepository.getAllFeatures();
	} 
	
}
