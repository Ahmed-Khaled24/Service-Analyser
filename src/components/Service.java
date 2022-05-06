package components;
import java.util.ArrayList;

public class Service {
    private String name;
    private ArrayList<API> APIs;

    public Service() {
        this("noName");
    }

    public Service(String name) {
        this.name = name;
        this.APIs = new ArrayList<>();
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

    public void addAPI(API api){
        this.APIs.add(api);
    }
}
