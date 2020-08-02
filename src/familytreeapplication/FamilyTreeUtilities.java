/*
 * Title: FamilyTreeUtilities class 
 * Author: Tan Zhi Guang 
 * Date: 20/03/2020
 * File name: FamilyTreeUtilities.java
 * Purpose: The purpose of this class is to display a GUI interface that allows the user to add 
 *          family members into a family tree. The tree created can be saved and loaded into the 
 *          program. 
 * Assumptions/Conditions: - Root person cannot add any siblings. 
                           - There can only be one root person for a tree. 
                           - A person can have a maximum of 2 parents. 
                           - The root person can have a minimum of 0 parent as the user can choose not to add them. 
                           - All person, other than the root person, can have a minimum of 1 parent as the parents might be divorced. 
                           - A couple must be made up of a male and female pair. 
                           - A person cannot have more than 1 spouse. 
                           - Parents of root person cannot add their own relatives. 
                           - Parents of root person can only add spouse. 
                           - A spouse cannot add their own relatives. 
                           - A spouse can only add children. 
                           - A couple have the same children. 
                           - First and last names can only comprise of characters and space. 
                           - Gender can only be male or female. 
                           - Life description can contain anything or nothing. 
                           - Street number and postcode must be numbers. 
                           - Street name and suburb must be at least 2 characters long, and 
                             can only comprise of characters and space. 
                           - Name, surname, gender, street number, street name, suburb and 
                             postcode cannot be null. 
                           - When filling up the form to add a person, all personal particulars 
                             must be filled, except for life description. 
                           - Clicking on names on tree will display personal information of the 
                             selected person. 
                           - Clicking on names on tree when editing personal information or 
                             adding relative will not show the corresponding personal information. 
                           - Personal information shown when selecting a person on the tree includes 
                             person's personal information, address information and relative information. 
                           - Relative information includes father, mother, children and grandchildren. 
                           - An empty family tree cannot be saved. 
                           - The program can only load and save .dat files. 
                           - The user can determine the name of the file when saving a tree. 
 */
package familytreeapplication;
import java.util.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.*;

/**
 * The purpose of this class is to display a GUI interface that allows the user to add 
 * family members into a family tree. The tree created can be saved and loaded into the 
 * program. 
 * 
 * @author Zhiguang
 */
public class FamilyTreeUtilities implements Serializable
{
    // Declaring static variables so that any edit is done on the instance created 
    private static FlowPane fpane;
    private static VBox appBox, infoBox;
    private static HBox selection, detailBox; 
    private static ScrollPane treePane, infoPane;
    private static Label heading, footnote;
    private static Button load, save, create, add, saveForm, discardForm;
    private static Text infoText, selectText;
    private static HBox typeBox, nameBox, sNameBox, GenderBox, lifeDescBox, stNoBox, stNameBox, subBox, postBox, fatherBox, motherBox, spouseBox, childBox, gChildBox, formBtnBox;
    private static TextField nameField, sNameField, stNoField, stNameField, subField, postField;
    private static Text typeText, personHeading, nameText, sNameText, genderText, lifeDescText, addHeading, stNoText, stNameText, subText, postText, relativeHeading, fatherText, motherText, spouseText, childText, gChildText;
    private static ComboBox<String> genderComboBox, typeComboBox;
    private static TextArea lifeDescField;
    private static Person rootPerson;
    private static TreeView<Person> famTree;
    private static Alert alert;
    private static Text infoFather, infoMother, infoSpouse, infoChild, infoGrandChild;
    private static TextField infoGend;
    private static VBox childVBox, gChildVBox;
    private static Button editDetailBtn, addRelativeBtn;
    private static HBox infoBtnBox;
    private static FileChooser chooseFile;
    
    /**
     * Default constructor 
     */
    public FamilyTreeUtilities()
    {}
    
    /**
     * Set base GUI layout for application 
     * 
     * @param topView stage of the application 
     */
    public void setLayout(Stage topView)
    {
        // Setting scene 
        fpane = new FlowPane();
        fpane.setOrientation(Orientation.VERTICAL);
        topView.setScene(new Scene(fpane,450, 495));
        topView.setResizable(false);
        
        // Setting title of application 
        topView.setTitle("Family Tree");
        
        // VBox to contain everything in app 
        appBox = new VBox(10);
        appBox.setPrefSize(460, 490);
        appBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        // Label for welcome message 
        heading = new Label("Welcome to the Family Tree Application");
        heading.setFont(Font.font(20));
        heading.setPadding(new Insets(0, 0, 0, 5));
        
        // Adding heading into appBox
        appBox.getChildren().add(heading);
        
        // HBox to contain load, save and create buttons 
        selection = new HBox();
        
        // Buttons for load, save and create 
        load = new Button("Load Tree");
        save = new Button("Save Tree");
        create = new Button("Create New Tree");
        
        // Adding buttons into selection 
        selection.getChildren().addAll(load, save, create);
        selection.setSpacing(5);
        selection.setPadding(new Insets(0, 0, 0, 10));
        
        // Adding selection into appBox 
        appBox.getChildren().add(selection);
        
        // HBox for tree and info 
        detailBox = new HBox();
        detailBox.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
        detailBox.setPrefSize(450, 470);
        
        // ScrollPane to contain family tree 
        famTree = new TreeView<>();
        treePane = new ScrollPane(famTree);
        treePane.setPrefSize(210, 470);
        detailBox.getChildren().add(treePane);
        
        infoText = new Text("Load a tree or add a new root \nperson");
        infoText.setFont(Font.font(15));
        
        // Button to add root person 
        add = new Button("Add root person");
        
        // VBox to contain information
        infoBox = new VBox(5);
        infoBox.setPadding(new Insets(5));
        infoBox.getChildren().addAll(add, infoText);
        
        // ScrollPane to contain infoBox so that if information in infoBox 
        // exceeds height of application, it can still be seen by scrolling down 
        infoPane = new ScrollPane(infoBox);
        infoPane.setPrefSize(260, 470);
        detailBox.getChildren().add(infoPane);
        
        appBox.getChildren().add(detailBox);
        fpane.getChildren().add(appBox);
        
        // Footnote reflects changes in program to user 
        footnote = new Label("Program loaded");
        footnote.setFont(Font.font(10));
        fpane.getChildren().add(footnote);
        
        // Initialise the following variables for later use
        selectText = new Text("Select a family member to view \ninformation");
        selectText.setFont(Font.font(15));
        
        personHeading = new Text("Person Info:");
        personHeading.setFont(Font.font(15));
        
        nameBox = new HBox(50);
        nameText = new Text("Name");
        nameField = new TextField();
        nameBox.getChildren().addAll(nameText, nameField);
        
        sNameBox = new HBox(35);
        sNameText = new Text("Surname");
        sNameField = new TextField();
        sNameBox.getChildren().addAll(sNameText, sNameField);
        
        GenderBox = new HBox(43);
        genderText = new Text("Gender");
        genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male","Female");
        GenderBox.getChildren().addAll(genderText, genderComboBox);
        
        lifeDescBox = new HBox();
        lifeDescText = new Text("Life Description");
        lifeDescField = new TextArea();
        lifeDescField.setPrefSize(150, 70);
        lifeDescBox.getChildren().addAll(lifeDescText, lifeDescField);
        
        addHeading = new Text("Address info:");
        addHeading.setFont(Font.font(15));
        
        stNoBox = new HBox(5);
        stNoText = new Text("Street Number");
        stNoField = new TextField();
        stNoBox.getChildren().addAll(stNoText, stNoField);
        
        stNameBox = new HBox(17);
        stNameText = new Text("Street Name");
        stNameField = new TextField();
        stNameBox.getChildren().addAll(stNameText, stNameField);
        
        subBox = new HBox(45);
        subText = new Text("Suburb");
        subField = new TextField();
        subBox.getChildren().addAll(subText, subField);
        
        postBox = new HBox(35);
        postText = new Text("Postcode");
        postField = new TextField();
        postBox.getChildren().addAll(postText, postField);
        
        formBtnBox = new HBox(10);
        saveForm = new Button("Save");
        discardForm = new Button("Discard");
        formBtnBox.setAlignment(Pos.CENTER_RIGHT);
        formBtnBox.getChildren().addAll(saveForm, discardForm);
        
        infoBtnBox = new HBox(5);
        editDetailBtn = new Button("Edit Details");
        addRelativeBtn = new Button("Add Relative");
        infoBtnBox.getChildren().addAll(editDetailBtn, addRelativeBtn);
        
        typeBox = new HBox(56);
        typeText = new Text("Type");
        typeComboBox = new ComboBox<>();
        
        relativeHeading = new Text("Relative info:");
        relativeHeading.setFont(Font.font(15));
        
        infoGend = new TextField();
        
        formBtnBox = new HBox(10);
        saveForm = new Button("Save");
        discardForm = new Button("Discard");
        formBtnBox.setAlignment(Pos.CENTER_RIGHT);
        formBtnBox.getChildren().addAll(saveForm, discardForm);
        
        fatherBox = new HBox(50);
        fatherText = new Text("Father");
        infoFather = new Text();
        fatherBox.getChildren().addAll(fatherText, infoFather);
        
        motherBox = new HBox(44);
        motherText = new Text("Mother");
        infoMother = new Text();
        motherBox.getChildren().addAll(motherText, infoMother);
        
        spouseBox = new HBox(45);
        spouseText = new Text("Spouse");
        infoSpouse = new Text();
        spouseBox.getChildren().addAll(spouseText, infoSpouse);
        
        childBox = new HBox(39);
        childText = new Text("Children");
        childVBox = new VBox(5);
        childBox.getChildren().addAll(childText, childVBox);
        
        gChildBox = new HBox(4);
        gChildText = new Text("Grand Children");
        gChildVBox = new VBox(5);
        gChildBox.getChildren().addAll(gChildText,gChildVBox);
        
        infoBtnBox = new HBox(5);
        editDetailBtn = new Button("Edit Details");
        addRelativeBtn = new Button("Add Relative");
        infoBtnBox.getChildren().addAll(editDetailBtn, addRelativeBtn);
        
        // Event handling 
        create.setOnMouseClicked(evt -> createNewPrompt());
        add.setOnMouseClicked(evt -> showForm("empty", rootPerson));
        save.setOnMouseClicked(evt -> saveToFile());
        load.setOnMouseClicked(evt -> loadTree());
    }
    
    /**
     * Save root person to the folder Family Tree Data, or any chosen directory. 
     * <p>File will be saved in .dat format. 
     */
    private void saveToFile()
    {
        try
        {
            if(rootPerson == null)
            {
                throw(null);
            }
            
            chooseFile = new FileChooser();
            chooseFile.setTitle("Save Family Tree");

            // Setting initial directory 
            chooseFile.setInitialDirectory(new File("Family Tree Data/"));

            // Setting filter so only .dat files are displayed 
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("DAT File", "*.dat");
            chooseFile.getExtensionFilters().add(filter);

            File selectedFile = chooseFile.showSaveDialog(null);

            if(selectedFile != null)
            {
                try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(selectedFile)))
                {
                    // Writing rootPerson into file 
                    os.writeObject(rootPerson);

                    // Popup to inform user of successful save 
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Save Successful");
                    alert.setContentText("File " + selectedFile.getName() + " saved successfully!\n"
                                         + "File is saved into the directory: " + selectedFile.getPath());
                    alert.showAndWait();

                    footnote.setText("File saved");
                }
                catch(FileNotFoundException e)
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Save Error");
                    alert.setContentText("File not found!");
                    alert.showAndWait();
                }
                catch(IOException e)
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Save Error");
                    alert.setContentText("Unable to save file!");
                    alert.showAndWait();
                }
            }
        }
        catch(NullPointerException e)
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Save Error");
            alert.setContentText("No root person to save!");
            alert.showAndWait();
        }
    }
    
    /**
     * Load root person of selected file into program 
     */
    private void loadTree()
    {
        chooseFile = new FileChooser();
        chooseFile.setTitle("Choose Family Tree to Load");
        
        // Setting initial directory 
        chooseFile.setInitialDirectory(new File("Family Tree Data/"));
        
        // Setting filter so only .dat files are displayed 
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("DAT File", "*.dat");
        chooseFile.getExtensionFilters().add(filter);
        
        File selectedFile = chooseFile.showOpenDialog(null);
        
        if(selectedFile != null)
        {
            try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(selectedFile)))
            {
                // Read file and store into root person 
                Object data = is.readObject();
                rootPerson = (Person)data;
                
                createTree(true);
                
                footnote.setText("File opened from: " + selectedFile);
            }
            catch(FileNotFoundException e)
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Load Error");
                alert.setContentText("File not found!");
                alert.showAndWait();
            }
            catch(IOException | ClassNotFoundException e)
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Load Error");
                alert.setContentText("Unable to load file!");
                alert.showAndWait();
            }
        }
    }
    
    /**
     * Prompt user for confirmation when create new tree button is pressed. 
     * <p>If ok is selected, clear root person and change user interface to 
     * default screen.
     * <p>If cancel is selected, do nothing. 
     */
    private void createNewPrompt()
    {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Create New Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to create new family tree?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            createNew();
        }
    }
    
    /**
     * Create new family tree. 
     * <p>Clear existing root person and reset user interface. 
     */
    private void createNew()
    {
        rootPerson = null;
        
        famTree = new TreeView<>();
        treePane.setContent(famTree);

        infoBox.getChildren().clear();
        infoBox.getChildren().addAll(add, infoText);

        footnote.setText("Program loaded");
    }
    
    /**
     * Display a form for user to fill in personal information 
     * 
     * @param indicator indicates the type of form to show
     * @param p Person to show the form for 
     */
    private void showForm(String indicator, Person p)
    {
        // Change footnote information 
        if(indicator.equalsIgnoreCase("empty"))
        {
            footnote.setText("Adding root person");
        }
        else
        {
            footnote.setText("Adding relative for: " + p.getFullName());
        }

        infoBox.getChildren().clear();
        
        // Add type of relative selection box if root person exists 
        if(!indicator.equalsIgnoreCase("empty"))
        {
            createTree(false);
            infoBox.getChildren().clear();
            typeBox.getChildren().clear();
            //typeComboBox = new ComboBox<>();
            typeComboBox.getItems().clear();
            
            if(indicator.equalsIgnoreCase("root"))
            {
                typeComboBox.getItems().addAll("Father", "Mother", "Spouse", "Child");
            }
            else if(indicator.equalsIgnoreCase("father") || indicator.equalsIgnoreCase("mother"))
            {
                typeComboBox.getItems().addAll("Spouse");
            }
            else if(indicator.equalsIgnoreCase("spouse"))
            {
                typeComboBox.getItems().addAll("Child");
            }
            else
            {
                typeComboBox.getItems().addAll("Spouse", "Child");
            }
            
            typeBox.getChildren().addAll(typeText, typeComboBox);
            
            infoBox.getChildren().add(typeBox);
        }
        
        // Clear all TextFields and set editable to true to allow user input 
        nameField.clear();
        nameField.setEditable(true);
        
        sNameField.clear();
        sNameField.setEditable(true);
        
        GenderBox.getChildren().clear();
        GenderBox.getChildren().addAll(genderText, genderComboBox);
        genderComboBox.getSelectionModel().clearSelection();
        
        lifeDescField.clear();
        lifeDescField.setEditable(true);
        
        stNoField.clear();
        stNoField.setEditable(true);
        
        stNameField.clear();
        stNameField.setEditable(true);
        
        subField.clear();
        subField.setEditable(true);
        
        postField.clear();
        postField.setEditable(true);
        
        infoBox.getChildren().addAll(personHeading, nameBox, sNameBox, GenderBox, lifeDescBox, addHeading, stNoBox, stNameBox, subBox, postBox, formBtnBox);
        
        // Event handling 
        if(indicator.equalsIgnoreCase("empty"))
        {
            saveForm.setOnMouseClicked(evt -> addPerson(p, "empty"));
        }
        else
        {
            saveForm.setOnMouseClicked(evt -> addPerson(p, typeComboBox.getValue()));
        }
        
        discardForm.setOnMouseClicked(evt -> discard(indicator));
    }
    
    /**
     * Discard any changes made to form. 
     * <p>Passing the string "empty" into the parameter indicates a discard for create root person form. 
     * <p>Otherwise, discard for edit details or add relative. 
     * 
     * @param s indicates type of discard to execute 
     */
    private void discard(String s)
    {
        // Confirmation popup 
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Discard Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to discard the information entered?");
        
        // Only discard if ok is selected 
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            if(s.equalsIgnoreCase("empty"))
            {
                createNew();
            }
            else
            {
                createTree(true);
                
                infoBox.getChildren().clear();
                infoBox.getChildren().add(selectText);
            }
            
            footnote.setText("Program loaded");
        }
    }
    
    /**
     * Add person or relative to tree. 
     * <p>This function also edits personal information of person if type 
     * passed into parameter is the string "edit". 
     * 
     * @param target person to add relative to 
     * @param type type of person to add 
     */
    private void addPerson(Person target, String type)
    {
        // Getting user input 
        String name = nameField.getText();
        String sName = sNameField.getText();
        String gender = null;
        String life = lifeDescField.getText();
        String stAddr = stNameField.getText();
        String sub = subField.getText();
        int stNo = -1, pCode = -1;
        
        // Checking user input 
        boolean check = true;
        
        // Initialise alert type 
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        
        // Checking for any invalid input 
        try
        {
            if(!type.equalsIgnoreCase("edit"))
            {
                gender = genderComboBox.getValue();
            }

            // Check if name entered by user is valid 
            if(checkName(name) == false || checkName(sName) == false)
            {
                alert.setTitle("Invalid Name");
                alert.setContentText("Name can only contain alphabets and space!\nName must be at least 2 characters long.");
                check = false;
            }
            // Check for valid gender. If editing information, gender will be be null as user cannot change gender when editing 
            else if(gender == null && !type.equalsIgnoreCase("edit"))
            {
                alert.setTitle("Invalid Gender");
                alert.setContentText("Please select a gender!");
                check = false;
            }
            // Check for valid address
            else if(checkName(stAddr) == false || checkName(sub) == false)
            {
                alert.setTitle("Invalid Address");
                alert.setContentText("Address can only contain alphabets and space!\nAddress must be at least 2 characters long.");
                check = false;
            }
            // No need to check type if adding rootPerson or editing personal information
            else if(!type.equalsIgnoreCase("empty") && !type.equalsIgnoreCase("edit"))
            {
                // If type is null, will throw NullPointerException and corresponding error will be shown
                // If father or mother is chosen as the type, check if gender is correct
                if(type.equalsIgnoreCase("father") && !gender.equalsIgnoreCase("male"))
                {
                    alert.setTitle("Invalid gender");
                    alert.setContentText("Wrong gender selected for father!");
                    check = false;
                }
                else if(type.equalsIgnoreCase("mother") && !gender.equalsIgnoreCase("female"))
                {
                    alert.setTitle("Invalid gender");
                    alert.setContentText("Wrong gender selected for mother!");
                    check = false;
                }
                // If adding spouse, check if the specified person already has a spouse
                else if(type.equalsIgnoreCase("spouse") && !target.noSpouse())
                {
                    alert.setTitle("Invalid spouse");
                    alert.setContentText("Spouse already exist!\nA person can only have one spouse.");
                    check = false;
                }
                // Check if gender of spouse is valid. 
                // Eg. male must have female spouse, vice versa. 
                else if(type.equalsIgnoreCase("spouse") && target.getGender().equalsIgnoreCase(gender))
                {
                    alert.setTitle("Invalid spouse");
                    alert.setContentText("Spouse must be of opposite gender!");
                    check = false;
                }
                // If rootPerson already has parents, check if that parent already exists 
                // This means that there can only be one father and one mother 
                else if(!target.getParentList().isEmpty() && (type.equalsIgnoreCase("father") || type.equalsIgnoreCase("mother")))
                {
                    for(Person pItem:target.getParentList())
                    {
                        if(gender.equalsIgnoreCase(pItem.getGender()))
                        {
                            alert.setTitle("Invalid parent");
                            alert.setContentText(type + " already exists!\nA person can only have 1 father and 1 mother.");
                            check = false;
                        }
                    }
                }
            }

            // Assign street number and post code, and check if user input is an integer 
            stNo = Integer.parseInt(stNoField.getText());
            pCode = Integer.parseInt(postField.getText());
        }
        catch(NullPointerException e)
        {
            alert.setTitle("Invalid Type");
            alert.setContentText("Select a type!");
            check = false;
        }
        catch(NumberFormatException e)
        {
            alert.setTitle("Invalid Address");
            alert.setContentText("Street number and Postcode can only contain number!");
            check = false;
        }
        
        // Display corresponding error message if any of the checks fail 
        if(!check)
        {
            alert.showAndWait();
        }
        // Only save person if all inputs are correct
        else
        {
            // If adding rootPerson
            if(type.equalsIgnoreCase("empty"))
            {
                rootPerson = new Person(name, sName, gender, life, stNo, stAddr, sub, pCode, "root");
            }
            // If editing personal information 
            // Gender remains the same 
            else if(type.equalsIgnoreCase("edit"))
            {
                Person temp = new Person(name, sName, target.getGender(), life, stNo, stAddr, sub, pCode, type);
                target.setDetails(temp);
            }
            // If adding parents 
            else if(type.equalsIgnoreCase("father") || type.equalsIgnoreCase("mother"))
            {
                Person temp = new Person(name, sName, gender, life, stNo, stAddr, sub, pCode, type);
                
                // Add target as parent's child 
                temp.getChildList().add(target);
                
                // Adding father and mother as each other's spouse
                if(!target.getParentList().isEmpty())
                {
                    temp.setSpouse(target.getParentList().get(0));
                    target.getParentList().get(0).setSpouse(temp);
                }
                
                // Add parent as target's parent 
                target.getParentList().add(temp);
            }
            // If adding spouse
            else if(type.equalsIgnoreCase("spouse"))
            {
                //1 If adding spouse to father or mother, change type to corresponding value 
                if(!target.getChildList().isEmpty() && target.getChildList().get(0).getType().equalsIgnoreCase("root"))
                {
                    type = gender.equalsIgnoreCase("male")?"Father":"Mother";
                }
                
                Person temp = new Person(name, sName, gender, life, stNo, stAddr, sub, pCode, type);
                
                // Set target as spouse's spouse 
                temp.setSpouse(target);
                
                // Add spouse as all children's parent 
                if(!target.getChildList().isEmpty())
                {
                    temp.setChildList(target.getChildList());
                    
                    for(Person child:target.getChildList())
                    {
                        child.getParentList().add(temp);
                    }
                }
                
                // Add temp as taget's spouse 
                target.setSpouse(temp);
            }
            // If adding children
            else
            {
                Person temp = new Person(name, sName, gender, life, stNo, stAddr, sub, pCode, type);
                
                // Add target to child's parentList
                temp.getParentList().add(target);
                
                // if target has a spouse, add relationship 
                if(!target.noSpouse())
                {
                    temp.getParentList().add(target.getSpouse());
                    target.getSpouse().getChildList().add(temp);
                }
                
                // Add child to target's childList
                target.getChildList().add(temp);
            }
            
            // Create family tree that is selectable 
            createTree(true);
            
            // Change footnote to corresponding message 
            if(type.equalsIgnoreCase("empty"))
            {
                footnote.setText("Root person added");
            }
            else if(type.equalsIgnoreCase("edit"))
            {
                footnote.setText("Personal information of " + target.getFullName() + " edited");
            }
            else
            {
                footnote.setText("Relative added");
            }
        }
    }
    
    /**
     * Checks if name comprises of only alphabets and space 
     * 
     * @param name name to check
     * @return true if name is valid; false otherwise 
     */
    private boolean checkName(String name)
    {
        // Name length must be at least 2 characters long 
        if(name.length() < 2)
        {
            return false;
        }
        // First 2 characters must be alphabets 
        else if(Character.isLetter(name.charAt(0)) == false || Character.isLetter(name.charAt(1)) == false)
        {
            return false;
        }
        else 
        {
            // If name is not composed of alphabets and blank space
            for(int i=0; i<name.length(); i++)
            {
                if(Character.isLetter(name.charAt(i)) == false && name.charAt(i) != ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Create family tree. 
     * 
     * @param bool true if TreeItems can be selected; false otherwise 
     */
    private void createTree(boolean bool)
    {
        // Initialise tree 
        famTree = new TreeView<>();
        TreeItem<Person> root = new TreeItem<>(rootPerson);
        famTree.setRoot(root);
        
        // Add parent 
        if(!rootPerson.getParentList().isEmpty())
        {
            Person pNode = new Person();
            pNode.setName("Parents");
            TreeItem<Person> p = new TreeItem<>(pNode);
            root.getChildren().add(p);
            
            for(Person parent:rootPerson.getParentList())
            {
                TreeItem<Person> pItem = new TreeItem<>(parent);
                p.getChildren().add(pItem);
            }   
        }
        
        // Recursion function to add spouse and children 
        addTreeRecur(root);
        
        // Show tree 
        treePane.setContent(famTree);
        infoBox.getChildren().clear();
        infoBox.getChildren().add(selectText);
        
        // Expand all nodes on family tree 
        expandTreeView(root);
        
        // Event handling
        // Allow tree item to show personal information when clicked if bool is true 
        if(bool)
        {
            famTree.setOnMouseClicked(evt -> showSelection(evt));
        }
    }
    
    /**
     * Recursive method that sets every TreeItem in tree to expanded form 
     * 
     * @param item TreeItem of any type 
     */
    private void expandTreeView(TreeItem<?> item)
    {
        if(item != null && !item.isLeaf())
        {
            item.setExpanded(true);
            
            for(TreeItem<?> child:item.getChildren())
            {
                expandTreeView(child);
            }
        }
    }
    
    /**
     * Recursive method to add spouse and children to tree 
     * 
     * @param parent TreeItem to add children to 
     * @param p Person to add TreeItem for 
     */
    private void addTreeRecur(TreeItem<Person> parent)
    {
        Person p = parent.getValue();
        
        if(!p.noSpouse())
        {
            // Creating spouse heading (Person with spouse as the name)
            Person sNode = new Person();
            sNode.setName("Spouse");
            TreeItem<Person> spouse = new TreeItem<>(sNode);
            
            // Adding heading to tree 
            parent.getChildren().add(spouse);
            
            // Creating TreeItem of parent's spouse 
            TreeItem<Person> sp = new TreeItem<>(p.getSpouse());
            
            // Adding spouse under spouse heading 
            spouse.getChildren().add(sp);
        }
        
        if(!p.getChildList().isEmpty())
        {
            // Creating children heading 
            Person cNode = new Person();
            cNode.setName("Children");
            TreeItem<Person> child = new TreeItem<>(cNode);
            
            // Adding heading to tree  
            parent.getChildren().add(child);
            
            for(Person c:p.getChildList())
            {
                // Add child to tree 
                TreeItem<Person> cItem = new TreeItem<>(c);
                child.getChildren().add(cItem);
                
                // Call recursive method on the children of every child 
                addTreeRecur(cItem);
            }
        }
    }
    
    /**
     * Get TreeItem selected by user 
     * 
     * @param evt event that triggered the method 
     */
    private void showSelection(Event evt)
    {
        // Casting object to correct type
        TreeView<Person> tree = (TreeView<Person>)evt.getSource();
        
        ObservableList<TreeItem<Person>> selectedItem = tree.getSelectionModel().getSelectedItems();
        
        for(TreeItem<Person> item:selectedItem)
        {
            // Only display personal information for people, and not for the various headings created 
            if(!item.getValue().getName().equalsIgnoreCase("parents") && !item.getValue().getName().equalsIgnoreCase("Spouse") && !item.getValue().getName().equalsIgnoreCase("Children"))
            {
                personalInfo(item.getValue());
                footnote.setText("Display details for: " + item.getValue());
            }
        }
    }
    
    /**
     * Display personal information of selected person 
     * 
     * @param p person selected 
     */
    private void personalInfo(Person p)
    {
        infoBox.getChildren().clear();
        footnote.setText("Personal information of: " + p.getFullName());
        
        // Set the various TextFields to contain the relevant information, and set the TextFields to not editable 
        nameField.setText(p.getName());
        nameField.setEditable(false);
        
        sNameField.setText(p.getSurname());
        sNameField.setEditable(false);
        
        GenderBox.getChildren().clear();
        infoGend.setText(p.getGender());
        infoGend.setEditable(false);
        GenderBox.getChildren().addAll(genderText, infoGend);
        
        lifeDescField.setText(p.getLifeDesc());
        lifeDescField.setEditable(false);
        
        stNoField.setText(Integer.toString(p.getStreetNo()));
        stNoField.setEditable(false);
        
        stNameField.setText(p.getStreetName());
        stNameField.setEditable(false);
        
        subField.setText(p.getSuburb());
        subField.setEditable(false);
        
        postField.setText(Integer.toString(p.getPostcode()));
        postField.setEditable(false);
        
        // Set father and mother to not provided 
        infoFather.setText("Not provided");
        infoMother.setText("Not provided");
        
        // If father and/or mother exists, replace text with full name of parent 
        if(!p.getParentList().isEmpty())
        {
            for(Person parent:p.getParentList())
            {
                if(parent.getGender().equalsIgnoreCase("male"))
                {
                    infoFather.setText(parent.getFullName());
                }
                else
                {
                    infoMother.setText(parent.getFullName());
                }
            }
        }
        
        // Set spouse to none 
        infoSpouse.setText("None");
        
        // if spouse exists, replace text with spouse's full name 
        if(!p.noSpouse())
        {
            infoSpouse.setText(p.getSpouse().getFullName());
        }
        
        // Clear childVBox, which is used to store child's full name
        childVBox.getChildren().clear();
        
        // If person does not have child, set text to none 
        if(p.getChildList().isEmpty())
        {
            infoChild = new Text("None");
            childVBox.getChildren().add(infoChild);
        }
        else
        {
            // If there is child, add all children into childVBox
            for(Person child:p.getChildList())
            {
                infoChild = new Text(child.getFullName());
                childVBox.getChildren().add(infoChild);
            }
        }
        
        // Clear gChildVBox, which is used to store grandchild's full name
        gChildVBox.getChildren().clear();
        
        // If no child, set grandchild to none 
        if(p.getChildList().isEmpty())
        {
            infoGrandChild = new Text("None");
            gChildVBox.getChildren().add(infoGrandChild);
        }
        else
        {
            // Counter to count number of grandchildren 
            int count = 0;
            
            // Add every child's child 
            for(Person child:p.getChildList())
            {
                if(!child.getChildList().isEmpty())
                {
                    for(Person gchild:child.getChildList())
                    {
                        infoGrandChild = new Text(gchild.getFullName());
                        gChildVBox.getChildren().add(infoGrandChild);
                        count++;
                    }
                }
            }
            
            // If there is no grand child, set text to none 
            if(count == 0)
            {
                infoGrandChild = new Text("None");
                gChildVBox.getChildren().add(infoGrandChild);
            }
        }
        
        infoBox.getChildren().addAll(personHeading, nameBox, sNameBox, GenderBox, lifeDescBox, addHeading, stNoBox, stNameBox, subBox, postBox, relativeHeading, fatherBox, motherBox, spouseBox, childBox, gChildBox, infoBtnBox);
        
        // Event handling
        editDetailBtn.setOnMouseClicked(evt -> editDetail(p));
        addRelativeBtn.setOnMouseClicked(evt -> showForm(p.getType(), p));
    }
    
    /**
     * Edit personal information of selected person 
     * 
     * @param target Person selected 
     */
    private void editDetail(Person target)
    {
        footnote.setText("Editing details of: " + target.getFullName());
        
        // Set all fields to editable 
        nameField.setText(target.getName());
        nameField.setEditable(true);
        
        sNameField.setText(target.getSurname());
        sNameField.setEditable(true);
        
        // Gender cannot be edited to reduce complication
        GenderBox.getChildren().clear();
        infoGend.setText(target.getGender());
        infoGend.setEditable(false);
        GenderBox.getChildren().addAll(genderText, infoGend);
        
        lifeDescField.setText(target.getLifeDesc());
        lifeDescField.setEditable(true);
        
        stNoField.setText(Integer.toString(target.getStreetNo()));
        stNoField.setEditable(true);
        
        stNameField.setText(target.getStreetName());
        stNameField.setEditable(true);
        
        subField.setText(target.getSuburb());
        subField.setEditable(true);
        
        postField.setText(Integer.toString(target.getPostcode()));
        postField.setEditable(true);
        
        // Create a tree that does not show details when clicked 
        createTree(false);
        
        // Show personal information 
        infoBox.getChildren().clear();
        infoBox.getChildren().addAll(personHeading, nameBox, sNameBox, GenderBox, lifeDescBox, addHeading, stNoBox, stNameBox, subBox, postBox, formBtnBox);
        
        // Event handling 
        saveForm.setOnMouseClicked(evt -> addPerson(target, "edit"));
        discardForm.setOnMouseClicked(evt -> discard(target.getType()));
    }
}
