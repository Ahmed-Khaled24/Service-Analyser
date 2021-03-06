package components;

import java.util.ArrayList;

public final class ObjectField extends Field {
    private ArrayList<Field> childrenFields;

    // ------------------------ Constructors ------------------------
    public ObjectField(){
    }

    public ObjectField(String type, boolean mandatory, ArrayList<String> allowedValues, String name, ArrayList<String> ancestors, char io) {
        super(type, mandatory, allowedValues, name, ancestors, io);
        this.childrenFields = new ArrayList<>();
    }

    // ------------------------ Getters ------------------------
    public ArrayList<Field> getChildrenFields() {
        return childrenFields;
    }

    // ------------------------ Setters ------------------------
    public void setChildrenFields(ArrayList<Field> childrenFields) {
        this.childrenFields = childrenFields;
    }

    // ------------------------ Methods ------------------------
    public void addChildField(Field field) {
        this.childrenFields.add(field);
    }

    public ObjectField find(String name){
        for(Field f: this.childrenFields){
            if(f.getName().equals(name))
                return (ObjectField) f;
        }
        return null;
    }

}
