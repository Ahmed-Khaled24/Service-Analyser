package components;
import java.util.ArrayList;

public class Service {
    private String name;
    private ArrayList<API> APIs;

    public Service() {
    }

    public Service(String name, ArrayList<API> APIs) {
        this.name = name;
        this.APIs = APIs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<API> getAPIs() {
        return APIs;
    }

    public void setAPIs(ArrayList<API> APIs) {
        this.APIs = APIs;
    }
}
