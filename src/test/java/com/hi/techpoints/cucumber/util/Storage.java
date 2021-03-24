package com.hi.techpoints.cucumber.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hi.techpoints.cucumber.exception.OneviewException;

import cucumber.runtime.junit.FeatureRunner;
import cucumber.runtime.model.CucumberFeature;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Elavarasan
 *
 */
@Slf4j
public class Storage {

	public static Map<String,Object> featureStorage = new HashMap<String,Object>();

	private static String OUT_REGEX = "<OUT:(.+?)>";
	private static String IN_REGEX = "<IN:(.+?)>";
	   
	/**Set key & value.
	 * 
	 * @param key
	 * @param value
	 */
	public static void put(String key,Object value) {
		Pattern pattern = Pattern.compile(OUT_REGEX);
	    Matcher matcher = pattern.matcher(key.toUpperCase());
	    matcher.find();
	    key = matcher.group(1);
		featureStorage.put(key, value);
	}
	
	/**Get input parameter value.
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		Pattern pattern = Pattern.compile(IN_REGEX);
		Matcher matcher = pattern.matcher(key.toUpperCase());
		matcher.find();
		key = matcher.group(1);
		return featureStorage.get(key);
	}
	
	/** Set respective future file input value on storage. 
	 * 
	 * @param featureRunner
	 * @throws OneviewException
	 */
	public static void setupFeatureParam(FeatureRunner featureRunner) throws OneviewException {
		
    	try {

    		Field cfField = FeatureRunner.class.getDeclaredField("cucumberFeature");
    		cfField.setAccessible(true);
			CucumberFeature  cucumberFeature= (CucumberFeature) cfField.get(featureRunner);
			String path = cucumberFeature.getPath();
			String sheetName = path.substring(path.lastIndexOf("/")+1);
			featureStorage.clear();
			
		    featureStorage.putAll(TestRepository.getFeatureFileValue(sheetName));
						
		} catch (Exception e) {
			log.error("'cucumberFeature' f not found on FeatureRunner class");
			throw new OneviewException("'cucumberFeature' f not found on FeatureRunner class",e);
		}
	}
}
