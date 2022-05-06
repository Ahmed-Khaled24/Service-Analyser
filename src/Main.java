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

        // The service
/*
        Service service = new Service("excel file name");
*/

        // Pointer to the current API
/*
        API currentAPI;
*/

        // ----- Sheet level -----
        int numberOfSheets = ExcelFile.getNumberOfSheets();
        for (int currentSheetIndex = 0; currentSheetIndex < numberOfSheets; currentSheetIndex++) {
            XSSFSheet currentSheet = ExcelFile.getSheetAt(currentSheetIndex);

            // ----- Row level -----
            boolean IsObjectRow = false;
            int numberOfRows = currentSheet.getPhysicalNumberOfRows();
            for (int currentRowIndex = 0; currentRowIndex < numberOfRows; currentRowIndex++) {

                XSSFRow currentRow = currentSheet.getRow(currentRowIndex);
                XSSFCell firstCell = currentRow.getCell(0);

                if (firstCell == null){
                    IsObjectRow = false;
                }
                else if (firstCell.toString().contains("API_NAME")){
                    IsObjectRow = false;
                    // To do: construct API object.
                    // To do: return the api to current api pointer.
                    // To do: store the constructed api in the service's ArrayList.
                }
                else if(firstCell.toString().equals("I/O")){
                    IsObjectRow = true;
                }
                else if(IsObjectRow){
                    // To do: construct Field object.
                    // To do: store the constructed object in its right place in the API.
                }
            }
        }
    }
}
