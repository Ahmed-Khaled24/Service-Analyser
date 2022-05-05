package utility;
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

}
