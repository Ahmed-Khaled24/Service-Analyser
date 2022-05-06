import java.util.ArrayList;

public final class ObjectField extends Field {
    private ArrayList<Field> childrenFields;

    public ObjectField(ArrayList<Field> childrenFields) {
        this.childrenFields = childrenFields;
    }

    public ObjectField(String type, boolean mandatory, ArrayList<String> allowedValues,
                       String name, char IO, ArrayList<Field> childrenFields) {
        super(type, mandatory, allowedValues, name, IO);
        this.childrenFields = childrenFields;
    }

    public ArrayList<Field> getChildrenFields() {
        return childrenFields;
    }

    public void setChildrenFields(ArrayList<Field> childrenFields) {
        this.childrenFields = childrenFields;
    }
//    private int findObjectField(String objectName) { // this method to search and find the index of ****** in ***** array list
//        for (int i = 0; i < this.childrenFields.size(); i++) {
//            childrenFields childrenFields = this.childrenFields.get(i);
//            if (childrenFields.getobjectName().equals(objectName)) {
//                return i;
//            }
//        }
//        return -1;
//    }
}
