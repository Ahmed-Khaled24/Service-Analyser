package components;
import java.util.ArrayList;

public class Field {
    private String type;
    private boolean mandatory;
    private ArrayList<String> allowedValues;
    private String name;
    private char IO;
    private String parentFieldName;

    public Field() {
    }

    public Field(String type, boolean mandatory, ArrayList<String> allowedValues, String name, String parentFieldName, char IO) {
        this.type = type;
        this.mandatory = mandatory;
        this.allowedValues = allowedValues;
        this.name = name;
        this.IO = IO;
        this.parentFieldName = parentFieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public ArrayList<String> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(ArrayList<String> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getIO() {
        return IO;
    }

    public void setIO(char IO) {
        this.IO = IO;
    }

    public String getParentFieldName(){
        return this.parentFieldName;
    }

    public void setParentFieldName(String name){
        this.parentFieldName = name;
    }
}
