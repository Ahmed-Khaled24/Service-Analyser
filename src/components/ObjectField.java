package components;

import java.util.ArrayList;

public final class ObjectField extends Field {
    private ArrayList<Field> childrenFields;

    public ObjectField(){
    }

    public ObjectField(String type, boolean mandatory, ArrayList<String> allowedValues, String name, ArrayList<String> ancestors, char IO) {
        super(type, mandatory, allowedValues, name, ancestors, IO);
        this.childrenFields = new ArrayList<>();
    }

    public ArrayList<Field> getChildrenFields() {
        return childrenFields;
    }

    public void setChildrenFields(ArrayList<Field> childrenFields) {
        this.childrenFields = childrenFields;
    }

    public void addSubField(Field field) {
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
