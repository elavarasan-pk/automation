package com.hi.techpoints.cucumber.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.hi.techpoints.cucumber.exception.OneviewException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Elavarasan
 *
 *Read a feature file from Repository.xls.
 */
@Slf4j
public class TestRepository {

	private static String INPUT_REPOSITORY = "src/test/resources/Repository.xls";

	private static String FEATURE_FILE_LOCATION= "src/test/resources/features/";

	private static int FEATURE_COLUMN_INDEX = 3;
	private static int EXECUTION_STATUS_COLUMN_INDEX = 4;
	private static String EXECUTION_STATUS = "YES";

	/**Read  future files from Repository.xls
	 * 
	 * @return
	 * @throws OneviewException 
	 */
	public static Map<String, List<String>> getModuleFeatures() throws OneviewException {

		try (Workbook workbook = WorkbookFactory.create(new File(INPUT_REPOSITORY));) {

			Sheet sheet = workbook.getSheet("REPOSITORY");
			int totalRows = sheet.getPhysicalNumberOfRows();
			String key = null;

			Map<String, List<String>> testCases = new HashMap<>();
			List<String> features = null;
			for (int i = 2; i < totalRows; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(FEATURE_COLUMN_INDEX);
				String value = cell.getStringCellValue();

				if (value != null && value.endsWith("/")) {
					key = FEATURE_FILE_LOCATION+value;
					continue;
				}

				if (key == null)
					continue;

				if (testCases.get(key) == null) {
					features = new ArrayList<>();
				}

				cell = row.getCell(EXECUTION_STATUS_COLUMN_INDEX);
				String status = cell.getStringCellValue();
				if (EXECUTION_STATUS.equalsIgnoreCase(status)) {
					value = key+value;
					features.add(value);
				}

				testCases.put(key, features);
			}
			
           return testCases;
		} catch (Exception e) {
			log.error("Repository.xsl not found on the location : [{}]",INPUT_REPOSITORY);
			throw new OneviewException("Repository.xsl not found on the location :["+INPUT_REPOSITORY+"]",e);
		}
	}
	
	/** Get respective future file value from Repository.xls
	 * 
	 * @param sheetName
	 * @return
	 * @throws OneviewException 
	 */
	public static Map<String, Object> getFeatureFileValue(String sheetName) throws OneviewException {

		try (Workbook workbook = WorkbookFactory.create(new File(INPUT_REPOSITORY));) {
			Sheet sheet = workbook.getSheet(sheetName);
			int totalRows = sheet.getPhysicalNumberOfRows();

			Map<String, Object> featureParamValue = new HashMap<>();
			for (int i = 1; i < totalRows; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);
				String key = cell.getStringCellValue();
				cell = row.getCell(1);
				String value = cell.getStringCellValue();
				if(key != null) {
					key = key.toUpperCase();
				}
				featureParamValue.put(key, value);
			}
			
           return featureParamValue;
		} catch (Exception e) {
			log.error("Repository.xsl not found on the location : [{}]",INPUT_REPOSITORY);
			throw new OneviewException("Repository.xsl not found on the location :["+INPUT_REPOSITORY+"]",e);
		}
	}
	
	/** Getting all feature files
	 * 
	 * @return
	 * @throws IOException
	 */
	public static  List<String> getAllFeatures() throws OneviewException {
		 Map<String, List<String>> moduleFeatures = getModuleFeatures();
		 List<String> features = new ArrayList<>();
		 
		 for(List<String> val: moduleFeatures.values()) {
			 features.addAll(val);
		 }
		
		 return features;
	}

}
