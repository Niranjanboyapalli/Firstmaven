package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	
	Workbook wb;
	
	public ExcelFileUtil(String Excelpath) throws Throwable
	{
		FileInputStream fi=new FileInputStream(Excelpath);
		wb=WorkbookFactory.create(fi);
	}
	public int rowCount(String Sheetname)
	{
		return wb.getSheet(Sheetname).getLastRowNum();
		
	}
	public int colCount(String Sheetname,int row)
	{
		return wb.getSheet(Sheetname).getRow(row).getLastCellNum();
	}
	
	public String getCelldata(String Sheetname,int row,int col)
	{
		String data="";
		if(wb.getSheet(Sheetname).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			//double celldata=wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
			int celldata=(int)wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		
		return data;
	}
	
	public void setCelldata(String sheetname,int row,int col,String status,String writeexcel) throws Throwable
	{
		Sheet ws=wb.getSheet(sheetname);
		Row rownum=ws.getRow(row);
		Cell cell1=rownum.createCell(col);
		cell1.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			CellStyle style=wb.createCellStyle();
			Font font1=wb.createFont();
			font1.setColor(IndexedColors.GREEN.index);
			font1.setBold(true);
			font1.getBoldweight();
			style.setFont(font1);
			rownum.getCell(col).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style=wb.createCellStyle();
			Font font1=wb.createFont();
			font1.setColor(IndexedColors.RED.index);
			font1.setBold(true);
			font1.getBoldweight();
			style.setFont(font1);
			rownum.getCell(col).setCellStyle(style);
	
			
		}
		
		else if(status.equalsIgnoreCase("Not executed"))
		{
			CellStyle style=wb.createCellStyle();
			Font font1=wb.createFont();
			font1.setColor(IndexedColors.BLUE.index);
			font1.setBold(true);
			font1.getBoldweight();
			style.setFont(font1);
			rownum.getCell(col).setCellStyle(style);
	
		}
		
		FileOutputStream fo=new FileOutputStream(writeexcel);
		wb.write(fo);
		
		
	}
	
	
	
	
}
