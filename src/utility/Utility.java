package utility;

import components.API;
import components.Field;
import components.ObjectField;
import components.StringField;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Utility {

    public static XSSFWorkbook GetExcelObject(String FilePath) throws IOException {

        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(FilePath);
            return new XSSFWorkbook(inputFile);
        } catch (FileNotFoundException e) {
            throw new IOException("File not found.");
        }
    }

    // -------------------------------------------------------------------------------------
    // This function is used to store a field into its parent field if it is a nested field.
    public static void storeField(API thisAPI, Field theField) {
        // Check if the Object has ancestors.
        // If not store it in the API as an ancestor.
        if(theField.getAncestors().isEmpty()){
            thisAPI.addField(theField);
        }
        // If it has ancestors, loop through the objects to find its direct parent.
        else {
            ObjectField parentPtr = null;
            ArrayList<String> ancestors = theField.getAncestors();
            for(int i = 0 ; i< ancestors.size(); i++){

                // In the first loop search in the API.
                if(i == 0 )
                    parentPtr = thisAPI.find(ancestors.get(i));
                // In all other loops search in subFields.
                else
                    parentPtr = parentPtr.find(ancestors.get(i));
            }

            // Add theField to its direct parent childrenFields.
            parentPtr.addSubField(theField);
        }
    }

    // --------------------------------------------------
    // This function is used to get the name of the field.
    public static String getName(String fullName){
        // Split the fullName string into its components.
        String [] ArrOfNames = fullName.split("/");
        // Return its name.
        return ArrOfNames[ArrOfNames.length-1];
    }

    // --------------------------------------------------
    // This function is used to get the list of ancestors.
    public static ArrayList<String> getAncestors(String fullName){
        String [] ArrOfNames = fullName.split("/");
        ArrayList<String> ancestors = new ArrayList<>(Arrays.asList(ArrOfNames));
        ancestors.remove(ancestors.size()-1);
        return ancestors;
    }

    // -------------------------------------------
    // Construct Field object using the given row.
    public static Field constructFieldObject(XSSFRow thisRow) {
        // ---- Indices of each property ----
        final int IO_index = 0;
        final int type_index = 2;
        final int mandatory_index = 4;
        final int fullName_index = 1;
        final int allowedValues_index = 3;
        // -------------------------------

        // Hold the wanted cells in temporary variables.
        XSSFCell ioCell = thisRow.getCell(IO_index);
        XSSFCell typeCell = thisRow.getCell(type_index);
        XSSFCell fullNameCell = thisRow.getCell(fullName_index);
        XSSFCell mandatoryCell = thisRow.getCell(mandatory_index);
        XSSFCell allowedValuesCell = thisRow.getCell(allowedValues_index);

        // Extract the data from the cells into strings.
        String ioStr = ioCell.toString();
        String type = typeCell.toString();
        String fullName = fullNameCell.toString();
        String mandatoryStr = mandatoryCell.toString();
        String allowedValuesStr = allowedValuesCell.toString();

        // Get the name and ancestors list
        String name = getName(fullName);
        ArrayList<String> ancestors = getAncestors(fullName);

        // Convert mandatoryStr to Boolean
        boolean mandatory = mandatoryStr.equals("Y");

        // Convert ioStr to char
        char io = ioStr.charAt(0);

        // Convert allowedValuesStr to ArrayList.
        String[] tempArr = allowedValuesStr.split(",");
        ArrayList<String> allowedValues = new ArrayList<>(Arrays.asList(tempArr));

        // check if it is ObjectField or StringField to construct an object.
        Field field;
        if (type.equalsIgnoreCase("string"))
            field = new StringField(type, mandatory, allowedValues, name, ancestors, io);
        else
            field = new ObjectField(type, mandatory, allowedValues, name, ancestors, io);

        // return the constructed field
        return field;

    }

    // -----------------------------------------------------------------------------------
    // Construct an API object using the given two rows and return reference to the object.
    public static API constructAPI(XSSFRow nameRow, XSSFRow propertiesRow) { // propertiesRow = nameRow + 2
        // ---- Index of each property ----
        final int name_index = 0; // in nameRow
        final int HTTP_index = 0; // in propertiesRow
        final int URL_index = 1; // in propertiesRow
        // ------------------------------------------

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
