/*
 * Title: Family Tree Application
 * Author: Tan Zhi Guang 
 * Date: 20/03/2020
 * File name: FamilyTreeApplication.java
 * Purpose: The purpose of this program is to launch a GUI class. 
 * Assumptions/Conditions: - This program does not need to know the specifics of 
                             the GUI class that it is launching as they are taken 
                             care of by the class itself. 
 */
package familytreeapplication;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The purpose of this program is to launch a GUI class. 
 * 
 * @author Zhiguang
 */
public class FamilyTreeApplication extends Application
{
    /**
     * Method to set primary stage of this application 
     * 
     * @param topView stage of GUI application 
     */
    @Override
    public void start(Stage topView)
    {
        FamilyTreeUtilities U = new FamilyTreeUtilities();
        U.setLayout(topView);
        
        topView.show();
    }
    
    /**
     * Main method to launch application 
     * 
     * @param args command line arguments 
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
