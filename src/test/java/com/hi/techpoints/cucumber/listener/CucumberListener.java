package com.hi.techpoints.cucumber.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.hi.techpoints.cucumber.exception.CucumberException;
import com.hi.techpoints.cucumber.util.Status;
import com.hi.techpoints.cucumber.util.Storage;
import com.hi.techpoints.cucumber.util.UpdateTestResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Elavarasan
 *
 *OneviewListener used to capture the status of before 
 *and after execution of each step definition.
 */
@Slf4j
public class CucumberListener extends RunListener{

	private int count = 0;
	private String excutionScenario = null;
	private String description = "";
	private Date startDate = null;
	private String errorMessage = null;
	private Map<Integer,List<Status>> results = new HashMap<>();
	private List<Status> statusList = null;
	private List<String> inputParam = new ArrayList<>();
    private List<String> outputParam = new ArrayList<>();
    private static String OUT_REGEX = "<OUT:(.+?)>";
	private static String IN_REGEX = "<IN:(.+?)>";
    
	@Override
	public void testRunFinished(Result result) throws Exception {
		UpdateTestResult.updateStatusToExcel(results);
	}
	
    @Override
	public void testIgnored(Description description) throws Exception {
    	errorMessage = "IGNORED STEP DEFINITION EXECUTION";
    	
        if(this.description.equals(excutionScenario)) {
    		results.put(count, statusList);
     	} else {
     		inputParam.clear();
        	outputParam.clear();
     		updateStatus(description);
     	}
	}

    @Override
    public void testFailure(Failure failure) throws Exception {
    	errorMessage = failure.getException().getMessage();
    	if(errorMessage.isEmpty()) {
    		errorMessage = "FAILED STEP DEFINITION EXECUTION";
    	}
    	/*inputParam.clear();
    	outputParam.clear();*/
    	
    }
    
    @Override
    public void testStarted(Description description) throws Exception {
    	this.description = description.getDisplayName().toUpperCase();
    	errorMessage = "";
    	
    	if(this.description.startsWith("SCENARIO")) {
    		count++;
    	  excutionScenario = description.getDisplayName();
    	  statusList = new ArrayList<>();
    	} else {
    		startDate = new Date();
    		inputParam.clear();
        	outputParam.clear();
        	setInOutParam(description.getDisplayName());
    	}
    	
    }
    
    
    @Override
    public void testFinished(Description description) throws CucumberException {
    	this.description = description.getDisplayName();
    	
    	if(this.description.equals(excutionScenario)) {
    		results.put(count, statusList);
     	} else {
     		updateStatus(description);
     		
     	}
    }

    /** Getting all <in:#####>/<out:####> parameter from current running parameter
     * 
     * @param step
     */
    private void setInOutParam(String step) {
    	
    	 Pattern pattern = Pattern.compile(IN_REGEX);
	      Matcher matcher = pattern.matcher(step.toUpperCase());
	      while(matcher.find()){
	    	inputParam.add(matcher.group(1));
	      }
	      
	      pattern = Pattern.compile(OUT_REGEX);
	       matcher = pattern.matcher(step.toUpperCase());
	      while(matcher.find()){
	    	outputParam.add(matcher.group(1));
	      }
    	
    }
    
    /**Collecting status of each step definition.
     * 
     * @param description
     * @throws CucumberException
     */
    private void updateStatus(Description description) throws CucumberException {
    	Status status = new Status();
 		status.setComment(errorMessage);
 		status.setStep(description.getMethodName());
 		status.setEndDate(new Date());
 		status.setScenario(excutionScenario);
 		status.setStatus(errorMessage.isEmpty()?true:false);
 		status.setStartDate(startDate);
 		
        if(!outputParam.isEmpty()) {
        	StringBuilder sb = new StringBuilder();
        	for(String out:outputParam) {
        		
                if(!Storage.featureStorage.containsKey(out)) {
 					log.error("Value is not setting for output param: [{}]",out);
 					status.setComment("Value is not setting for output param: [{"+out+"}]");
 					status.setStatus(false);
 					break;
 				}

        		sb.append(out).append("=").append(Storage.featureStorage.get(out)).append("\n");
 			}
        	status.setOutput(sb.toString());
 		}
        
        if(!inputParam.isEmpty()) {
 			StringBuilder sb = new StringBuilder();
 			for(String in:inputParam) {
 				sb.append(in).append("=").append(Storage.featureStorage.get(in)).append("\n");
 			}
 			status.setInput(sb.toString());
 		}
 		statusList.add(status);
    }

}
