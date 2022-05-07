package components;

import java.util.ArrayList;

public final class StringField extends Field{

    public StringField(String type, boolean mandatory, ArrayList<String> allowedValues, String name, ArrayList<String> ancestors, char io){
        super(type, mandatory, allowedValues, name, ancestors, io);
    }

}
