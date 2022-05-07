package components;
import java.util.ArrayList;

public class Field {
    private String type;
    private boolean isMandatory;
    private ArrayList<String> allowedValues;
    private String name;
    private char io;
    private ArrayList<String> ancestors;

    // ------------------------ Constructors ------------------------
    public Field() {
    }

    public Field(String type, boolean mandatory, ArrayList<String> allowedValues, String name, ArrayList<String> ancestors, char io) {
        this.type = type;
        this.isMandatory = mandatory;
        this.allowedValues = allowedValues;
        this.name = name;
        this.io = io;
        this.ancestors = ancestors;
    }

    // ------------------------ Getters ------------------------
    public String getType() {
        return type;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public ArrayList<String> getAllowedValues() {
        return allowedValues;
    }

    public String getName() {
        return name;
    }

    public char getIo() {
        return io;
    }

    public ArrayList<String> getAncestors() {
        return ancestors;
    }

    // ------------------------ Setters ------------------------
    public void setType(String type) {
        this.type = type;
    }

    public void setMandatory(boolean mandatory) {
        this.isMandatory = mandatory;
    }

    public void setAllowedValues(ArrayList<String> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIo(char io) {
        this.io = io;
    }

    public void setAncestors(ArrayList<String> ancestors) {
        this.ancestors = ancestors;
    }
}
