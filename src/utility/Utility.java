package utility;
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

    public static void constructAPI(XSSFRow nameRow, XSSFRow theOtherRow){ // theOtherRow = nameRow + 2
        // ---- Indices of each property ----
        final int name_index = 0; // in nameRow
        final int HTTP_index = 0; // in theOtherRow
        final int URL_index  = 1; // in theOtherRow
        // -------------------------------------------------

        // To do: complete the function
    }

}
