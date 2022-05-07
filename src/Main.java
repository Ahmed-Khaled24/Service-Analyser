import components.*;
import org.apache.poi.xssf.usermodel.*;
import utility.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // Open the Excel file.
        String filePath = "../Example.xlsx";
        XSSFWorkbook ExcelFile;
        try {
            ExcelFile = Utility.constructExcelObject(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting the program now.");
            return;
        }

        // Initialize a Service object.
        Service service = new Service("ExampleService");

        // Initialize a pointer to the current API.
        API currentAPI = new API();

        // -------------------- Analyze the Excel file --------------------
        // ----------------------------------------------------------------

        // ----- Sheet level -----
        int numberOfSheets = ExcelFile.getNumberOfSheets();
        for (int currentSheetIndex = 0; currentSheetIndex < numberOfSheets; currentSheetIndex++) {
            XSSFSheet currentSheet = ExcelFile.getSheetAt(currentSheetIndex);

            // ----- Row level -----
            boolean IsObjectRow = false;
            int numberOfRows = currentSheet.getLastRowNum();
            for (int currentRowIndex = 0; currentRowIndex <= numberOfRows; currentRowIndex++) {

                // The first cell is unique for each type of row (object row, object header row, API header row, API properties row)
                // so the first cell will be checked to determine what should be done.

                XSSFRow currentRow = currentSheet.getRow(currentRowIndex);
                XSSFCell firstCell = null;
                if(currentRow != null){
                    firstCell = currentRow.getCell(0);
                }

                // ---------- Blank Row ----------
                // If the first cell is null so this row is empty.
                if (firstCell == null) {
                    IsObjectRow = false;
                }

                // ---------- API Row ----------
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

                // ---------- Objects header Row ----------
                // If the first cell contains "I/O" so this row is the header row for the objects
                else if (firstCell.toString().equalsIgnoreCase("i/o")) {
                    // For the next set of rows it will be considered object rows until a new API row found.
                    IsObjectRow = true;
                }

                // ---------- Object Row ----------
                else if (IsObjectRow) {
                    // Construct Field object.
                    Field tempField = Utility.constructFieldObject(currentRow);

                    // Store the objects in ArrayList for easy traverse without recursion.
                    if(tempField.getName().contains("object"))
                        currentAPI.addObject(tempField);

                    Utility.storeField(currentAPI, tempField);
                }
            }
        }
        System.out.println("Fine");
    }

}
