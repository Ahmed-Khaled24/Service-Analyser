package utility;

import components.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Utility {

    public static Service constructService(String filePath){
/*
    This function takes Excel file path and return a Service object.
*/
        // Open Excel file.
        XSSFWorkbook ExcelFile;
        try {
            ExcelFile = constructExcelObject(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting the program now.");
            return null;
        }

        // Initialize Service object
        Service service = new Service("testService");

        // Loop through Excel file sheets to construct the service object.
        int numberOfSheets = ExcelFile.getNumberOfSheets();
        for (int currentSheetIndex = 0; currentSheetIndex < numberOfSheets; currentSheetIndex++) {
            XSSFSheet currentSheet = ExcelFile.getSheetAt(currentSheetIndex);
            analyseExcelSheet(currentSheet, service);
        }
        return service;
    }

    private static void analyseExcelSheet(XSSFSheet sheet, Service service){
/*
    This function takes Excel sheet and Service object to analyse the sheet to get the contained APIs and
    store them in the given service object.
*/
        // Loop through the sheet rows to extract the data and construct API object(s) and add them to the service object.
        boolean isObjectRow = false;
        API currentAPI = null;
        int numberOfRows = sheet.getLastRowNum();
        for (int currentRowIndex = 0; currentRowIndex <= numberOfRows; currentRowIndex++) {
        /*
            The first cell is unique for each type of row (object row, object header row, API header row, API properties row)
            so the first cell will be checked to determine what should be done.
        */
            XSSFRow currentRow = sheet.getRow(currentRowIndex);
            // Make sure that the row is defined.
            if(currentRow!=null) {
                XSSFCell firstCell = currentRow.getCell(0);

                if(firstCell == null)                                           // If the row is defined put empty.
                    isObjectRow = false;

                else if (firstCell.toString().contains("API_NAME")) {           // If the row contains an API definition.
                    isObjectRow = false;                                        // Stop processing the rows as Field rows.
                    XSSFRow propertiesRow = sheet.getRow(currentRowIndex += 2); // Hold properties row in temporary variable and  currentRow is the name row.
                    currentAPI = constructAPI(currentRow, propertiesRow);       // Create an API.
                    service.addAPI(currentAPI);                                 // Store the API in the service
                }

                else if(firstCell.toString().equalsIgnoreCase("i/o"))
                    isObjectRow = true;                                         // Start processing as Field row.

                else if(isObjectRow){
                    Field field = constructField(currentRow);                   // Construct Field object.

                    // Store the object in its belonged ArrayList for easy traverse without recursion.
                    if(field instanceof ObjectField) {                          // If it is ObjectFiled it should be stored.
                        assert currentAPI != null;
                        if(field.getIo() == 'I')
                            currentAPI.addRequestObject(field);
                        else if(field.getIo() == 'O')
                            currentAPI.addResponseObject(field);
                    }
                    else if(field instanceof StringField && field.getAncestors().isEmpty()){
                        assert currentAPI != null;                              // If it is StringField and directChild to the API it should be stored.
                        if(field.getIo() == 'I')
                            currentAPI.addRequestObject(field);
                        else if(field.getIo() == 'O')
                            currentAPI.addResponseObject(field);
                    }

                    // Store the filed in its right position in the API.
                    storeField(currentAPI, field);
                }
            }
            else isObjectRow = false;                                           // If empty row stop processing as Field row.
        }
    }

    private static XSSFWorkbook constructExcelObject(String FilePath) throws IOException {
/*
    This function takes Excel file path and return the Excel file as an object.
*/
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(FilePath);
            return new XSSFWorkbook(inputFile);
        } catch (FileNotFoundException e) {
            throw new IOException("File not found.");
        }
    }

    private static API constructAPI(XSSFRow nameRow, XSSFRow propertiesRow) {
/*
    This function takes two rows from the Excel file and return an API object.
*/
        // ---- Index of each property ----
        final int NAME_INDEX = 0; // in nameRow
        final int OPERATION_INDEX = 0; // in propertiesRow
        final int URL_INDEX = 1; // in propertiesRow

        // Hold the wanted cells in temporary variables.
        XSSFCell nameCell = nameRow.getCell(NAME_INDEX);
        XSSFCell operationCell = propertiesRow.getCell(OPERATION_INDEX);
        XSSFCell URLCell = propertiesRow.getCell(URL_INDEX);

        // Extract the data from the cells into strings.
        String name = nameCell.toString().replace("REST Operation Mapping", "");
        String operation = operationCell.toString();
        String URL = URLCell.toString();

        // Construct and return the API.
        return new API(name, operation, URL);
    }

    private static String getName(String fullName) {
/*
    This function takes the fullName (Example: object1/object2/field3), and
    returns only the name (Example: field3) to construct Field object.
*/
        // Split the fullName string into its components.
        String[] ArrOfNames = fullName.split("/");
        // Return its name.
        return ArrOfNames[ArrOfNames.length - 1];
    }

    private static ArrayList<String> getAncestors(String fullName) {
/*
    This function is takes the fullName (Example: object1/object2/field3), and returns
    a list of ancestors names (Example: [object1, object2]).
*/
        String[] ArrOfNames = fullName.split("/");
        ArrayList<String> ancestors = new ArrayList<>(Arrays.asList(ArrOfNames));
        ancestors.remove(ancestors.size() - 1);
        return ancestors;
    }

    private static Field constructField(XSSFRow thisRow) {
/*
    This function takes Excel row and return a Field Object (StringObject or ObjectField depends on the type column).
*/
        // ---- Index of each property ----
        final int IO_INDEX = 0;
        final int TYPE_INDEX = 2;
        final int FULL_NAME_INDEX = 1;
        final int IS_MANDATORY_INDEX = 4;
        final int ALLOWED_VALUES_INDEX = 3;

        // Hold the wanted cells in temporary variables.
        XSSFCell ioCell = thisRow.getCell(IO_INDEX);
        XSSFCell typeCell = thisRow.getCell(TYPE_INDEX);
        XSSFCell fullNameCell = thisRow.getCell(FULL_NAME_INDEX);
        XSSFCell isMandatoryCell = thisRow.getCell(IS_MANDATORY_INDEX);
        XSSFCell allowedValuesCell = thisRow.getCell(ALLOWED_VALUES_INDEX);

        // Extract the data from the cells into strings.
        String ioStr = ioCell.toString();
        String type = typeCell.toString();
        String fullName = fullNameCell.toString();
        String mandatoryStr = isMandatoryCell.toString();
        String allowedValuesStr = allowedValuesCell.toString();

        // Get the name and ancestors list.
        String name = getName(fullName);
        ArrayList<String> ancestors = getAncestors(fullName);
        ancestors.remove("");

        // Convert mandatoryStr to Boolean.
        boolean mandatory = mandatoryStr.equals("Y");

        // Convert ioStr to char.
        char io = ioStr.charAt(0);

        // Convert allowedValuesStr to ArrayList.
        String[] tempArr = allowedValuesStr.split(",");
        ArrayList<String> allowedValues = new ArrayList<>(Arrays.asList(tempArr));
        allowedValues.remove("");

        // Check if it is ObjectField or StringField to construct an object.
        Field field;
        if (type.equalsIgnoreCase("string"))
            field = new StringField(type, mandatory, allowedValues, name, ancestors, io);
        else
            field = new ObjectField(type, mandatory, allowedValues, name, ancestors, io);

        // Return the constructed field
        return field;
    }

    private static void storeField(API api, Field field) {
/*
    This function takes API and Filed objects and store the Filed in its right position in The API object.
*/
        // If the Field object has no ancestors.
        if (field.getAncestors().isEmpty()) {
            api.addField(field);
        }
        // If the Field object has ancestors.
        else {
            // Get the list of ancestors.
            ArrayList<String> ancestors = field.getAncestors();
            ObjectField directParent = api.find(ancestors.get(0));

            // Loop through the list of ancestors until find the directParent.
            for (int i = 1; i < ancestors.size(); i++)
                if (directParent != null) directParent = directParent.find(ancestors.get(i));

            // Add field to its directParent childrenFields ArrayList.
            if (directParent != null) directParent.addChildField(field);
        }
    }

}


