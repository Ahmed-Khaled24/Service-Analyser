package components;

import java.util.ArrayList;

public final class ObjectField extends Field {
    private ArrayList<Field> childrenFields;

    public ObjectField(ArrayList<Field> childrenFields) {
        this.childrenFields = childrenFields;
    }

    public ObjectField(String type, boolean mandatory, ArrayList<String> allowedValues, String name, String parentFieldName, char IO) {
        super(type, mandatory, allowedValues, name, parentFieldName, IO);
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

    // This method to search and find the index of subField in childrenFields ArrayList.
    private int find(String subFieldName) {
        for (int i = 0; i < this.childrenFields.size(); i++) {
            Field temp = childrenFields.get(i);
            if(temp.getName().equals(subFieldName))
                return i;
        }
        return -1;

    }
}
