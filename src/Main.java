import components.API;
import components.Service;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utility.Utility;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        // Open the Excel file
        String filePath = "../Example.xlsx";
        XSSFWorkbook ExcelFile;
        try {
            ExcelFile = Utility.GetExcelObject(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting the program now.");
            return;
        }

        // Initialize a Service object
        Service service = new Service("ExampleService");

        // Initialize a pointer to the current API
        API currentAPI;

        // -------------------- Analyze the Excel file --------------------
        // ----------------------------------------------------------------

        // ----- Sheet level -----
        int numberOfSheets = ExcelFile.getNumberOfSheets();
        for (int currentSheetIndex = 0; currentSheetIndex < numberOfSheets; currentSheetIndex++) {
            XSSFSheet currentSheet = ExcelFile.getSheetAt(currentSheetIndex);

            // ----- Row level -----
            boolean IsObjectRow = false;
            int numberOfRows = currentSheet.getPhysicalNumberOfRows();
            for (int currentRowIndex = 0; currentRowIndex < numberOfRows; currentRowIndex++) {

                // The first cell is unique for each type of row (object row, object header row, API header row, API properties row)
                // so the first cell will be checked to determine what should be done.

                XSSFRow currentRow = currentSheet.getRow(currentRowIndex);
                XSSFCell firstCell = currentRow.getCell(0);

                // If the first cell is null so this row is empty.
                if (firstCell == null) {
                    IsObjectRow = false;
                }

                // If the first cell contains API name so this row and the next two rows represents the API.
                else if (firstCell.toString().contains("API_NAME")) {
                    // To stop processing the rows as object rows
                    IsObjectRow = false;
                    // Hold properties row in temporary variable and  currentRow is the name row.
                    XSSFRow propertiesRow = currentSheet.getRow(currentRowIndex += 2);
                    // Create the API
                    currentAPI = Utility.constructAPI(currentRow, propertiesRow);
                    // Store the API in the Service
                    service.addAPI(currentAPI);
                }

                // If the first cell contains "I/O" so this row is the header row for the objects
                else if (firstCell.toString().equals("I/O")) {
                    // For the next set of rows it we be considered object rows until a new API row found.
                    IsObjectRow = true;
                }

                else if (IsObjectRow) {
                    // To do: construct Field object.
                    // To do: store the constructed object in its right place in the API.
                }
            }
        }
    }
}
