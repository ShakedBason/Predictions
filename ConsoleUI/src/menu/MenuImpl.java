package menu;

import java.awt.*;
import java.util.Scanner;

public class MenuImpl implements MenuItem {

    @Override
    public void printLoadDataMessage(){
        System.out.println("Please enter a full XML file path: (enter 0 to get back to the menu)");
    }
    @Override
    public void showMenu() {
        System.out.println("Please Choose your choice number:");
        System.out.println("1. Load an xml file");
        System.out.println("2. Show simulation details");
        System.out.println("3. Run a simulation");
        System.out.println("4. Show past simulation details: ");
        System.out.println("5. Exit");
    }

}
