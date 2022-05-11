package JAVAFX_Controllers;

import components.*;
import utility.*;

public class ProjectApplication {
    public static void main(String[] args) {
        String filePath = "../Example.xlsx";
        Service s1 = Utility.constructService(filePath);
        System.out.println("All Ok.");
    }
}
