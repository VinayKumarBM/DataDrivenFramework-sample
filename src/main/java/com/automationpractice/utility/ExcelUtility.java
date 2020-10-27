package com.automationpractice.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	private XSSFSheet excelSheet;
	private XSSFWorkbook excelWorkbook;
	private XSSFCell cell;
	private XSSFRow row;

	public void setExcelFile(String sheetPath,String sheetName) throws Exception {
		try{
			//Log.info("Getting sheets from the workbook.");
			FileInputStream excelFile = new FileInputStream(sheetPath);
			excelWorkbook = new XSSFWorkbook(excelFile);
			excelSheet = excelWorkbook.getSheet(sheetName);
		}		catch(Exception exp){
			Log.error("Exception occured in setExcelFile: "+exp.getMessage());
			throw(exp);
		}
	}

	private int getTestCaseRow(String testCaseName, int testCaseColumn) throws Exception{
		int row;
		try{
			int rowCount = excelSheet.getLastRowNum();
			for(row=0; row< rowCount; row++){
				if(getCellData(row, testCaseColumn).equalsIgnoreCase(testCaseName)){
					break;
				}
			}
		}
		catch(Exception exp){
			Log.error("Exception occured in getTestCaseRow: "+exp.getMessage());
			throw(exp);
		}

		return row;
	}

	public String getCellData(int rowNumb, int colNumb) throws Exception{
		try{
			cell = excelSheet.getRow(rowNumb).getCell(colNumb);
			//Log.info("Getting cell data.");
			if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			}
			String cellData = cell.getStringCellValue();
			return cellData;
		}
		catch(Exception exp){
			return "";
		}
	}

	public void setCellData(String result, int rowNumb, int colNumb, String excelFilePath) throws Exception{
		try{
			row = excelSheet.getRow(rowNumb);
			cell = row.getCell(colNumb);
			Log.info("Setting results into the excel sheet.");
			if(cell==null){
				cell = row.createCell(colNumb);
				cell.setCellValue(result);
			}
			else{
				cell.setCellValue(result);
			}

			Log.info("Creating file output stream.");
			FileOutputStream fileOut = new FileOutputStream(excelFilePath);
			excelWorkbook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		}catch(Exception exp){
			Log.error("Exception occured in setCellData: "+exp.getMessage());
			throw (exp);
		}
	}

	public Map getData(String testCaseName, String sheetPath,String sheetName) {
		Map dataMap = new HashMap<String, String>();
		try {
			setExcelFile(sheetPath, sheetName);
			int dataRow = getTestCaseRow(testCaseName.trim(), 0);
			int columnCount = excelSheet.getRow(dataRow).getLastCellNum();
			for(int i=0;i<columnCount;i++) {
				String cellData = null;
				cell = excelSheet.getRow(dataRow).getCell(i);
				if(cell != null && cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				}
				if(cell != null) {
					cellData = cell.getStringCellValue();
				}
				dataMap.put(excelSheet.getRow(0).getCell(i).getStringCellValue(), cellData);
			}
		}catch (Exception e) {
			System.out.println("Exeception Occured while adding data to Map:\n");
			e.printStackTrace();
		}
		return dataMap;
	}
}
