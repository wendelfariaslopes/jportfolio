package jportfolio.com.trade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSLX {

	private static String file = "/Users/wendellopes/downloads/excel-db.xlsx";

	public static boolean createTable() {

		boolean status = false;
		XSSFWorkbook workbook = new XSSFWorkbook();
		//XSSFSheet sheetDataSet = workbook.createSheet("DataSet");
		XSSFSheet sheet = workbook.createSheet("DataGroup");
		
		Row firstRow = sheet.createRow(0);

		firstRow.createCell(0).setCellValue("Column 1");
		firstRow.createCell(1).setCellValue("Column 2");
		
		for(int i=1; i< 100; i++) {
			Row row = sheet.createRow(i);
			
			for(int j=0; j > 10;i++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(i*j);
			}
			
			//row.createCell(0).setCellValue(2*i*1.0);
			//row.createCell(1).setCellValue(i*1.0);
		}
		
		//sheet.createRow(100).createCell(0).setCellFormula("SUM(A2:A100)");
		//sheet.createRow(101).createCell(0).setCellFormula("PEARSON(A2:A100,B2:B100)");
		//sheet.createRow(102).createCell(0).setCellFormula("A102");
		
		try {
			FileOutputStream outputStream = new FileOutputStream(file);

			workbook.write(outputStream);
			workbook.close();
			status = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return status;
	}

	public static void main(String[] args) {
		if (createTable()) {
			System.out.println("Criada");
			readTable();
		} else {
			System.out.println("noooooo");
		}
	}

	public static void readTable() {
		
		try {
			FileInputStream excelFile = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(excelFile);
			
			Sheet sheet =workbook.getSheet("DataGroup");
	        int n = workbook.getNumberOfSheets();
	        for (int i = 0; i < n; i++) {
				System.out.println(workbook.getSheetName(i));
				//workbook.setSheetName(i, "sheet"+i);	
			}
	        System.out.println("FORMULA VALUE= "+sheet.getRow(99).getCell(0).getNumericCellValue());
	       System.out.println("FORMULA = "+sheet.getRow(101).getCell(0));

	       
	       
	        
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			
			CellReference cellReference = new CellReference("A99");
			
			Row row = sheet.getRow(cellReference.getRow());
			Cell cell = row.getCell(cellReference.getCol());
			CellValue cellValue = evaluator.evaluate(cell);
			
			System.out.println(cell.getCellType()+ " cellvalue= "+cellValue.getCellType());
			
			switch (cellValue.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				System.out.print("Calculeted Value = "+cellValue.getStringValue());
			break;
			case Cell.CELL_TYPE_BOOLEAN:
				System.out.print("Calculeted Value = "+cellValue.getBooleanValue());
			break;
			case Cell.CELL_TYPE_NUMERIC:
				System.out.print("Calculeted Value = "+cellValue.getNumberValue());
			break;
			
			}
	        
	        excelFile.close();
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
