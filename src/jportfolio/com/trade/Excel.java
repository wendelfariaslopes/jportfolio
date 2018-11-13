package jportfolio.com.trade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jportfolio.com.model.Asset2;

public class Excel {

	private static String file = "/Users/wendellopes/downloads/excel-db.xlsx";
	
	
	public static boolean createTable(String path,String nameFile, String nameSheet, int numberRows, List<String> objects,String... nameCols ) {

		boolean status = false;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(nameSheet);
		
		int numberCols = nameCols.length;
	
		//first row created
		Row firstRow = sheet.createRow(0);
		
		//Create in first row all name for header jumping first column
		for(int c = 0; c < numberCols; c++) {
			firstRow.createCell(c).setCellValue(nameCols[c]);
		}
		
		//start creat the sequency of creation lines after first row already created
		//or one sequence data set
		for(int r = 1; r < numberRows; r++) {
			Row row = sheet.createRow(r);
			for(int c = 0;c < numberCols; c++) {
				//row.createCell(c).setCellValue(value);
			}
		}
		
		try {
			FileOutputStream outputStream = new FileOutputStream(path+nameFile);

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

	public static boolean createTable() {

		boolean status = false;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheetDataSet = workbook.createSheet("DataSet");
		XSSFSheet sheet = workbook.createSheet("DataGroup");
		
		int numberCols = 100;
		int numberRows = 200;
		
		//first row created
		Row firstRow = sheet.createRow(0);
		
		//Create in first row all name for header jumping first column
		for(int c = 1; c < numberCols; c++) {
			firstRow.createCell(c).setCellValue("Column "+c);
		}
		
		
		//start creat the sequency of creation lines after first row already created
		for(int r = 1; r < numberRows; r++) {
			Row row = sheet.createRow(r);
			row.createCell(0).setCellValue("Row "+r);
		
			double value = 10;
			double increment = 0.06;
	
			row.createCell(1).setCellValue(value);
			
			
			for(int c = 2;c < numberCols; c++) {
				value+=value*increment;
				row.createCell(c).setCellValue(value);
				System.out.println(value);

			}
			
			//row.createCell(i).setCellValue(2*i*1.0);
			//row.createCell(1).setCellValue(i*1.0);
		}
		
		//sheet.createRow(100).createCell(0).setCellFormula("SUM(A2:A100)");
		//sheet.createRow(101).createCell(0).setCellFormula("COVARIANCE.P(A2:A100,B2:B100)");
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
	       System.out.println("FORMULA = "+sheet.getRow(100).getCell(0));
	        
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			
			CellReference cellReference = new CellReference("A101");
			
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

	public static void ss(String[] args) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

		try {
			File excel = new File("/Users/wendellopes/downloads/cs-rsi.xlsx");
			FileInputStream fis = new FileInputStream(excel);
			XSSFWorkbook book = new XSSFWorkbook(fis);
			// 0 == primeira planilha
			XSSFSheet sheet = book.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			// Iterating over Excel file in Java
			// while (itr.hasNext()) {
			// Row row = itr.next();
			// // Iterating over each column of Excel file
			// Iterator<Cell> cellIterator = row.cellIterator();
			// while (cellIterator.hasNext()) {
			// Cell cell = cellIterator.next();
			//
			// switch (cell.getCellType()) {
			//
			// case Cell.CELL_TYPE_STRING:
			// System.out.print(cell.getStringCellValue() + "\t");
			// break;
			// case Cell.CELL_TYPE_NUMERIC:
			// System.out.print(cell.getNumericCellValue() + "\t");
			// break;
			// case Cell.CELL_TYPE_BOOLEAN:
			// System.out.print(cell.getBooleanCellValue() + "\t");
			// break; default:
			// }
			// }
			// System.out.println("");
			// }

			Map<Timestamp, Double> map = new HashMap<>();

			while (itr.hasNext()) {
				Row row = itr.next();

				if (row != null) {

					Cell cellDate = row.getCell(2);
					Cell cell = row.getCell(3);
					if (cell != null) {

						switch (cell.getCellType()) {

						case Cell.CELL_TYPE_STRING:
							System.out.print(cellDate + ":" + cell.getStringCellValue() + "\t");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							Double d = cellDate.getNumericCellValue();
							System.out.print("num -> " + cellDate + ":" + cell.getNumericCellValue() + "\t");
							System.out.println(d);
							String strDate = cellDate + "";
							Date date;
							try {
								date = formatter.parse(strDate);
								Timestamp ts = new Timestamp(date.getTime());
								map.put(ts, cell.getNumericCellValue());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							break;
						case Cell.CELL_TYPE_BOOLEAN:
							System.out.print(cellDate + ":" + cell.getBooleanCellValue() + "\t");
							break;
						default:

						}
					}
				}

				System.out.println();

			}

			Asset2 a = new Asset2("Appl", "apple", map);
			/*
			 * // writing data into XLSX file Map<String, Object[]> newData = new
			 * HashMap<String, Object[]>(); newData.put("7", new Object[] { 7d, "Sonya",
			 * "75K", "SALES", "Rupert" }); newData.put("8", new Object[] { 8d, "Kris",
			 * "85K", "SALES", "Rupert" }); newData.put("9", new Object[] { 9d, "Dave",
			 * "90K", "SALES", "Rupert" }); Set<String> newRows = newData.keySet(); int
			 * rownum = sheet.getLastRowNum();
			 * 
			 * for (String key : newRows) { Row row = sheet.createRow(rownum++); Object[]
			 * objArr = newData.get(key); int cellnum = 0; for (Object obj : objArr) { Cell
			 * cell = row.createCell(cellnum++); if (obj instanceof String) {
			 * cell.setCellValue((String) obj); } else if (obj instanceof Boolean) {
			 * cell.setCellValue((Boolean) obj); } else if (obj instanceof Date) {
			 * cell.setCellValue((Date) obj); } else if (obj instanceof Double) {
			 * cell.setCellValue((Double) obj); } } } // open an OutputStream to save
			 * written data into Excel file FileOutputStream os = new
			 * FileOutputStream(excel); book.write(os);
			 * System.out.println("Writing on Excel file Finished ..."); // Close workbook,
			 * OutputStream and Excel file to prevent leak os.close(); fis.close();
			 */
			book.close();
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

}
