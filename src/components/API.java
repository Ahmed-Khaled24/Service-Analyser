package components;
import java.util.ArrayList;

public class API {
    private String name;
    private String operation;
    private String URL;
    private ArrayList<Field> fields;
    private ArrayList<Field> allObjects;

    // ------------------------ Constructors ------------------------
    public API() {
    }
    public API(String name, String operation, String REST_URL) {
        this.name = name;
        this.operation = operation;
        this.URL = REST_URL;
        this.fields = new ArrayList<>();
        this.allObjects = new ArrayList<>();
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

    public ArrayList<Field> getAllObjects() {
        return allObjects;
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

    public void setAllObjects(ArrayList<Field> allObjects) {
        this.allObjects = allObjects;
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
        this.allObjects.add(field);
    }
}
