package components;
import java.util.ArrayList;

public class API {
    private String name;
    private String operation;
    private String URL;
    private ArrayList<Field> fields;
    private ArrayList<Field> RequestObjects;
    private ArrayList<Field> ResponseObjects;

    // ------------------------ Constructors ------------------------
    public API() {}
    public API(String name, String operation, String REST_URL) {
        this.name = name;
        this.operation = operation;
        this.URL = REST_URL;
        this.fields = new ArrayList<>();
        this.RequestObjects = new ArrayList<>();
        this.ResponseObjects = new ArrayList<>();
    }

    // ------------------------ Getters ------------------------
    public String getName() {
        return name;
    }

    public String getOperation() {
        return operation;
    }

    public String getURL() {
        return URL;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public ArrayList<Field> getRequestObjects() {
        return RequestObjects;
    }

    public ArrayList<Field> getResponseObjects() {
        return ResponseObjects;
    }

    // ------------------------ Setters ------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void setRequestObjects(ArrayList<Field> requestObjects) {
        RequestObjects = requestObjects;
    }

    public void setResponseObjects(ArrayList<Field> responseObjects) {
        ResponseObjects = responseObjects;
    }

    // ------------------------ Methods ------------------------
    public void addField(Field field){
        fields.add(field);
    }

    public ObjectField find(String name){
        for(Field f: this.fields){
            if(f.getName().equals(name))
                return (ObjectField) f;
        }
        return null;
    }

    public void addRequestObject(Field field){
        this.RequestObjects.add(field);
    }

    public void addResponseObject(Field field){
        this.ResponseObjects.add(field);
    }

    @Override
    public String toString() {
        return name ;
    }
}
