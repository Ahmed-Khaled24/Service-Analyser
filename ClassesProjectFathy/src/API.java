import java.util.ArrayList;

public class API {
    private String name;
    private String operation;
    private String REST_URL;
    private ArrayList<Field> fields;

    public API() {
    }

    public API(String name, String operation, String REST_URL, ArrayList<Field> fields) {
        this.name = name;
        this.operation = operation;
        this.REST_URL = REST_URL;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getREST_URL() {
        return REST_URL;
    }

    public void setREST_URL(String REST_URL) {
        this.REST_URL = REST_URL;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }
}
