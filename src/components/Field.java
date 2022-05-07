package components;
import java.util.ArrayList;

public class Field {
    private String type;
    private boolean mandatory;
    private ArrayList<String> allowedValues;
    private String name;
    private char IO;
    private ArrayList<String> ancestors;

    public Field() {
    }

    public Field(String type, boolean mandatory, ArrayList<String> allowedValues, String name, ArrayList<String> ancestors, char io) {
        this.type = type;
        this.mandatory = mandatory;
        this.allowedValues = allowedValues;
        this.name = name;
        this.IO = io;
        this.ancestors = ancestors;
    }

    public boolean equals(Field other) {
        return this.name.equals(other.getName());
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

    public ArrayList<String> getAncestors(){
        return this.ancestors;
    }

    public void setAncestors(ArrayList<String> ancestors){
        this.ancestors = ancestors;
    }

}
