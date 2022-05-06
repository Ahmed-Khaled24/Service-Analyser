package utility;
import components.API;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utility {

    public static XSSFWorkbook GetExcelObject(String FilePath) throws IOException {

        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(FilePath);
            return new XSSFWorkbook(inputFile);
        }
        catch(FileNotFoundException e){
            throw new IOException("File not found.") ;
        }
    }

    public static void constructFieldObject(XSSFRow thisRow, API currentAPI){
        // ---- Indices of each property ----
        final int IO_index = 0;
        final int fieldName_index = 1;
        final int type_index = 2;
        final int allowedValues_index = 3;
        final int mandatory_index = 4;
        // -------------------------------------------------

        // To do: complete the function
    }

    // Construct an API object using the given two rows and return reference to the object.
    public static API constructAPI(XSSFRow nameRow, XSSFRow propertiesRow){ // propertiesRow = nameRow + 2
        // ---- Index of each property ----
        final int name_index = 0; // in nameRow
        final int HTTP_index = 0; // in propertiesRow
        final int URL_index  = 1; // in propertiesRow
        // -------------------------------------------------

        // Hold the wanted cells in temporary variables.
        XSSFCell nameCell = nameRow.getCell(name_index);
        XSSFCell HTTPCell = propertiesRow.getCell(HTTP_index);
        XSSFCell URLCell = propertiesRow.getCell(URL_index);

        // Extract the data from the cells into strings.
        String name = nameCell.toString();
        String operation = HTTPCell.toString();
        String REST_URL = URLCell.toString();

        // Construct and return the API.
        return new API(name, operation, REST_URL);
    }

}
