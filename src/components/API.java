package components;
import java.util.ArrayList;

public class API {
    private String name;
    private String operation;
    private String REST_URL;
    private ArrayList<Field> fields;
    private ArrayList<Field> AllObjects;

    // ------------------------ Constructors ------------------------
    public API() {
    }
    public API(String name, String operation, String REST_URL) {
        this.name = name;
        this.operation = operation;
        this.REST_URL = REST_URL;
        this.fields = new ArrayList<>();
        this.AllObjects = new ArrayList<>();
    }

    // ------------------------ Getters ------------------------
    public String getName() {
        return name;
    }

    public String getOperation() {
        return operation;
    }

    public String getREST_URL() {
        return REST_URL;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public ArrayList<Field> getAllObjects() {
        return AllObjects;
    }

    // ------------------------ Setters ------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setREST_URL(String REST_URL) {
        this.REST_URL = REST_URL;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void setAllObjects(ArrayList<Field> allObjects) {
        AllObjects = allObjects;
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

    public void addToAllObject(Field field){
        this.AllObjects.add(field);
    }
}
