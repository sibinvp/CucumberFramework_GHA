package Utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public WebDriver driver;
	public List<HashMap<String, String>> dataRecord;
	public HashMap<String, String> dataSet;
	int rowIndex;

	public WebDriver webDriverManager() throws IOException {
		Properties pi = new Properties();
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//Global.properties");
		pi.load(fi);
		String url = pi.getProperty("url");
		if (driver == null) {

//	/**	if(driver==null) {
//		String browserName=pi.getProperty("browser");
//			if(browserName.equalsIgnoreCase("edge")) {
//		System.setProperty("webdriver.msedge.driver", System.getProperty("user.dir")+"//src//test//resources//msedgedriver.exe");
//		EdgeOptions opt = new EdgeOptions();
//		opt.addArguments("-guest");
//		driver=new EdgeDriver(opt);
//		driver.manage().window().maximize();
//			}
//		else
//		{
//		System.out.println("Please select browser as edge");
//		}
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
//		driver.get(url);
//		System.out.println("browser is: "+browserName);
//		
//			
//			}
//		
//		return driver;
//	}
//	**/

			// below code will first check if any global system paramenter send via MVN call
			// from command

			String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: pi.getProperty("browser");

			if (browserName.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.msedge.driver", "C:\\Selenium\\msedgedriver.exe");

				EdgeOptions opt = new EdgeOptions();
				opt.addArguments("-guest");
				driver = new EdgeDriver(opt);
				driver.manage().window().setSize(new Dimension(1440, 900));
			}

			else if (browserName.contains("chrome")) {

				WebDriverManager.chromedriver().setup();
				ChromeOptions opt = new ChromeOptions();
				// opt.addArguments("-guest");

				System.out.println("chrome browser selected: " + browserName);

				if (browserName.contains("headless")) {
					opt.addArguments("headless");
					// opt.addArguments("--headless"); // Run in headless mode
					opt.addArguments("--disable-gpu"); // Disable GPU hardware acceleration (useful in headless)
					opt.addArguments("--window-size=1920x1080"); // Set window size (required for headless mode)
					System.out.println("headless");
				}

				driver = new ChromeDriver(opt);
				driver.manage().window().setSize(new Dimension(1440, 900));

			}
			driver.manage().window().maximize();
			// Implicit wait initialized
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
			driver.get(url);

		}

		return driver;
	}

	// To read data from CSV

	// Method to convert CSV to List<HashMap<String, String>>
	public List<HashMap<String, String>> convertCSVToList(String filePath) throws IOException {
		List<HashMap<String, String>> dataList = new ArrayList<>();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line;
			String[] headers = null;

			// Read the header line (first line), which contains the column names
			if ((line = reader.readLine()) != null) {
				headers = line.split(","); // Split headers by comma
			}

			// Read the rest of the data lines
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(","); // Split each line by comma

				// Create a HashMap to hold the row data
				HashMap<String, String> rowData = new HashMap<>();

				// Populate the HashMap with header-value pairs
				for (int i = 0; i < headers.length; i++) {
					rowData.put(headers[i], values.length > i ? values[i] : ""); // Handle missing data
				}

				dataList.add(rowData); // Add the row data to the list
			}
		} finally {
			if (reader != null) {
				reader.close(); // Ensure the reader is closed after processing
			}
		}

		return dataList;
	}

	// Method to convert Excel sheet to List<HashMap<String, String>>
	public HashMap<String, String> convertExcelToListOfHashMaps(String filePath, String tcID) throws IOException {
		List<HashMap<String, String>> data = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream((filePath)); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			// Get the first sheet
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Get header row (first row in the sheet)
			XSSFRow headerRow = sheet.getRow(0);
			int numColumns = headerRow.getPhysicalNumberOfCells();

			// Iterate through rows starting from row 1 (skip header)
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				HashMap<String, String> rowData = new HashMap<>();

				// Loop through each column in the row
				for (int j = 0; j < numColumns; j++) {
					// Get header for the column
					String header = headerRow.getCell(j).getStringCellValue();

					// Get the cell value (if the cell is empty, use an empty string)
					XSSFCell cell = row.getCell(j);
					String cellValue = (cell != null) ? getCellValue(cell) : "";

					// Put the value in the row data map
					rowData.put(header, cellValue);
				}

				// Add the row data to the list
				data.add(rowData);

			}
			rowIndex = getRowIndex(filePath, tcID);
			System.out.println("Pass TC ID is: " + tcID);
			System.out.println(rowIndex);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// dataRecord = data;
		dataSet = data.get(rowIndex-1);
		// return data;
		return dataSet;
	}

	// Helper method to get cell value based on the cell type
	private static String getCellValue(XSSFCell cell) {
		String cellValue = "";

		switch (cell.getCellType()) {
		case NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		default:
			cellValue = "";
		}

		return cellValue;
	}

	// Helper method to get the row number of given Test Case ID
	public static int getRowIndex(String filePath, String tcID) throws IOException {
		try (FileInputStream fis = new FileInputStream(filePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheet("TestData"); // Assume the sheet is named "TestData"
			Iterator<Row> rows = sheet.iterator();
			Row firstRow = rows.next(); // Get the first row (header row)
			Iterator<Cell> cells = firstRow.cellIterator();

			int column = -1;
			int k = 0;
			while (cells.hasNext()) {
				Cell columnname = cells.next();
				if (columnname.getStringCellValue().equalsIgnoreCase("TestCase")) {
					System.out.println("found test case COLUMN");
					column = k;
					break;
				}
				k++;
			}

			// Scan the "TestCase" column for the specific test case ID
			if (column != -1) {
				while (rows.hasNext()) {
					Row row = rows.next();
					String cellValue = row.getCell(column).getStringCellValue().trim();
					if (cellValue.equalsIgnoreCase(tcID)) {
						System.out.println(row.getRowNum());
						return row.getRowNum(); // Return the row index where the test case is found
					}
				}
			}
		}

		return -1; // Return -1 if test case not found
	}

}
