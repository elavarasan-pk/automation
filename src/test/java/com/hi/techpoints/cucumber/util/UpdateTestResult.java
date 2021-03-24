package com.hi.techpoints.cucumber.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hi.techpoints.cucumber.exception.OneviewException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Elavarasan
 *
 */
@Slf4j
public class UpdateTestResult {

	private static String RESULT_DIR = "src/test/resources/result/Result.xls";

	private static int SCENARIO_COLUMN_INDEX = 0;
	private static int STEP_COLUMN_INDEX = 1;
	private static int INPUT_COLUMN_INDEX = 2;
	private static int OUTPUT_COLUMN_INDEX = 3;
	private static int EXECUTION_STATUS_COLUMN_INDEX = 4;
	private static int COMMENT_COLUMN_INDEX = 5;
	private static int START_DATE_COLUMN_INDEX = 6;
	private static int END_DATE_COLUMN_INDEX = 7;
	
	/*private static int FUNCTION_NAME_COLUMN_INDEX = 2;
	private static int TEST_RESULTS_INDEX = 4;
	private static int DATE_AND_TIME_COLUMN_INDEX = 5;*/
	/*public static HashMap<String, int[]> resultMap = new HashMap<String, int[]>();
	public static HashMap<String, int[]> dateandTimeMap = new HashMap<String, int[]>();*/

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm:ss a");

	/** Updating step definition status to result.xls
	 * 
	 * @param results
	 * @throws OneviewException 
	 */
	public static void updateStatusToExcel(Map<Integer,List<Status>> results) throws OneviewException {
		try {
			
			HSSFWorkbook workbook = new HSSFWorkbook(); 
	        HSSFSheet sheet = workbook.createSheet("Result");
	        
	        CellStyle style = createStyle(workbook,HSSFColor.BLUE.index);
			Row row = sheet.createRow(0);
         	
         	Cell cell = row.createCell(SCENARIO_COLUMN_INDEX);
         	cell.setCellValue("SCENARIOS");
         	cell.setCellStyle(style);
         	
            cell = row.createCell(STEP_COLUMN_INDEX);
            cell.setCellValue("STEP DEFINITION");
            cell.setCellStyle(style);
            
            cell = row.createCell(EXECUTION_STATUS_COLUMN_INDEX);
            cell.setCellValue("STATUS");
            cell.setCellStyle(style);
             
            cell = row.createCell(START_DATE_COLUMN_INDEX);
            cell.setCellValue("START TIME");
            cell.setCellStyle(style);
            
            cell = row.createCell(END_DATE_COLUMN_INDEX);
            cell.setCellValue("END TIME");
            cell.setCellStyle(style);
            
            cell = row.createCell(COMMENT_COLUMN_INDEX);
            cell.setCellValue("COMMENT");
            cell.setCellStyle(style);
            
            cell = row.createCell(INPUT_COLUMN_INDEX);
            cell.setCellValue("INPUT VALUE");
            cell.setCellStyle(style);
            
            cell = row.createCell(OUTPUT_COLUMN_INDEX);
            cell.setCellValue("OUTPUT VALUE");
            cell.setCellStyle(style);
            
            int count = 1;
	        for(Integer key:results.keySet()) {
	        	List<Status> statuses = results.get(key);
	        	
	        	if(statuses == null || statuses.size() == 0)
	        		continue;
	        	
	        	int size = statuses.size();
	        	
	        	for(Status status:statuses) {
	        		
	        		Row row1 = sheet.createRow(count);
	        		
	        		Cell inCell = row1.createCell(SCENARIO_COLUMN_INDEX);
	        		inCell.setCellValue(status.getScenario());
	            	
	        		inCell = row1.createCell(STEP_COLUMN_INDEX);
	        		inCell.setCellValue(status.getStep());
	     	        style.setFillBackgroundColor(HSSFColor.RED.index);
	                
	                inCell = row1.createCell(EXECUTION_STATUS_COLUMN_INDEX);
	                inCell.setCellValue(status.isStatus()?"PASS":"FAIL");
	                
	                if(status.isStatus()) {
	                	inCell.setCellStyle(createStyle(workbook,HSSFColor.GREEN.index));
	                } else {
	                	inCell.setCellStyle(createStyle(workbook,HSSFColor.RED.index));
	                }
	                
	                inCell = row1.createCell(START_DATE_COLUMN_INDEX);
	                inCell.setCellValue(dateFormat.format(status.getStartDate()));
	                
	                inCell = row1.createCell(END_DATE_COLUMN_INDEX);
	                inCell.setCellValue(dateFormat.format(status.getEndDate()));
	                
	                inCell = row1.createCell(COMMENT_COLUMN_INDEX);
	                inCell.setCellValue(status.getComment());
	                
	                inCell = row1.createCell(INPUT_COLUMN_INDEX);
	                inCell.setCellValue(status.getInput());
	                CellStyle cs = workbook.createCellStyle();
	                //cs.setWrapText(true);
	                inCell.setCellStyle(cs);
	                
	                inCell = row1.createCell(OUTPUT_COLUMN_INDEX);
	                inCell.setCellValue(status.getOutput());
	                cs = workbook.createCellStyle();
	                //cs.setWrapText(true);
	                inCell.setCellStyle(cs);
	                ++count;
	                
	        	}

	        	int startRow = count-size;
	        	int endRow = count-1;
	        	
	        	if(endRow>startRow) {
	        	sheet.addMergedRegion(new CellRangeAddress(
	        			startRow, 
	        			endRow,
	        	        SCENARIO_COLUMN_INDEX, 
	        	        SCENARIO_COLUMN_INDEX  
	        	));
	        	}
	        	
	        }
        
			FileOutputStream outputFile = new FileOutputStream(RESULT_DIR);
			workbook.write(outputFile); 
	        outputFile.close();
		} catch (Exception e) {
			log.error("Result.xsl not found on the location : [{}]",RESULT_DIR);
			throw new OneviewException("Result.xsl not found on the location :["+RESULT_DIR+"]",e);
		}
         
        
	}
	
	/**createStyle method used to create excel's cell style
	 * 
	 * @param workbook
	 * @param color
	 * @return
	 */
	private static CellStyle  createStyle(HSSFWorkbook workbook,short color) {
		CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(color);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setBold(true);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
		
		return style;
	}
		
}
