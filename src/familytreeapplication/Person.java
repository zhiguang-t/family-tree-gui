/*
 * Title: Person class 
 * Author: Tan Zhi Guang 
 * Date: 20/03/2020
 * File name: Person.java
 * Purpose: The purpose of this class is to store a person's personal information. 
 *          The class also stores a person's parents, spouse and children. 
 * Assumptions/Conditions: - A person has a type variable to indicate the type of 
                             person added to the family tree. 
                           - A person can only have one spouse. 
                           - Spouse must be of opposite gender. 
 */
package familytreeapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Person class stores a person's personal information. 
 * <p>The class also stores a person's parents, spouse and children. 
 * 
 * @author Zhiguang
 */
public class Person implements Serializable
{
    // Declaring private variables so that variables cannot be accessed directly 
    private String name, surname, gender, lifeDesc; 
    private String streetName, suburb, type; 
    private int streetNo, postCode; 
    private Person spouse; 
    private ArrayList<Person> parentList, childrenList;
    
    /**
     * Default constructor 
     */
    public Person()
    {
        parentList = new ArrayList<>();
        childrenList = new ArrayList<>();
    }
    
    /**
     * Constructor which sets all personal information of person 
     * 
     * @param fname first name
     * @param lname surname 
     * @param sex gender 
     * @param desc life description 
     * @param sNo street number 
     * @param street street name 
     * @param sub suburb 
     * @param post postcode 
     * @param t type of person 
     */
    public Person(String fname, String lname, String sex, String desc, int sNo, String street, String sub, int post, String t)
    {
        name = fname;
        surname = lname;
        gender = sex;
        lifeDesc = desc; 
        streetNo = sNo;
        streetName = street;
        suburb = sub;
        postCode = post; 
        type = t;
        
        parentList = new ArrayList<>();
        childrenList = new ArrayList<>();
    }
    
    /**
     * Sets first name of person 
     * 
     * @param fname first name 
     */
    public void setName(String fname)
    {
        name = fname;
    }
    
    /**
     * Returns first name of person 
     * 
     * @return first name 
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets surname of person 
     * 
     * @param lname surname
     */
    public void setSurname(String lname)
    {
        surname = lname;
    }
    
    /**
     * Returns surname of person 
     * 
     * @return surname 
     */
    public String getSurname()
    {
        return surname;
    }
    
    /**
     * Returns full name of person. 
     * <p>Full name is returned in the following format: 
     * <ul>
     * <li>surname + first name </li>
     * </ul>
     * 
     * @return full name
     */
    public String getFullName()
    {
        return surname + " " + name;
    }
    
    /**
     * Sets gender of person 
     * 
     * @param sex gender
     */
    public void setGender(String sex)
    {
        gender = sex;
    }
    
    /**
     * Returns gender of person 
     * 
     * @return gender 
     */
    public String getGender()
    {
        return gender;
    }
    
    /**
     * Set life description of person
     * 
     * @param desc life description 
     */
    public void setLifeDesc(String desc)
    {
        lifeDesc = desc;
    }
    
    /**
     * Returns life description of person 
     * 
     * @return life description 
     */
    public String getLifeDesc()
    {
        return lifeDesc;
    }
    
    /**
     * Set street name of address 
     * 
     * @param sName street name 
     */
    public void setStreetName(String sName)
    {
        streetName = sName;
    }
    
    /**
     * Returns street name of address 
     * 
     * @return street name 
     */
    public String getStreetName()
    {
        return streetName;
    }
    
    /**
     * Set suburb of address 
     * 
     * @param sub suburb 
     */
    public void setSuburb(String sub)
    {
        suburb = sub;
    }
    
    /**
     * Returns suburb of address 
     * 
     * @return suburb 
     */
    public String getSuburb()
    {
        return suburb;
    }
    
    /**
     * Set street number of address 
     * 
     * @param sNo street number 
     */
    public void setStreetNo(int sNo)
    {
        streetNo = sNo;
    }
    
    /**
     * Returns street number of address 
     * 
     * @return street number 
     */
    public int getStreetNo()
    {
        return streetNo;
    }
    
    /**
     * Set postcode of address 
     * 
     * @param post postcode 
     */
    public void setPostcode(int post)
    {
        postCode = post;
    }
    
    /**
     * Returns postcode of address 
     * 
     * @return postcode
     */
    public int getPostcode()
    {
        return postCode;
    }
    
    /**
     * Set spouse of person 
     * 
     * @param partner spouse 
     */
    public void setSpouse(Person partner)
    {
        spouse = partner;
    }
    
    /**
     * Return spouse of person
     * 
     * @return spouse
     */
    public Person getSpouse()
    {
        return spouse;
    }
    
    /**
     * Set person's list of children 
     * 
     * @param children ArrayList of person's children 
     */
    public void setChildList(ArrayList<Person> children)
    {
        childrenList = children;
    }
    
    /**
     * Return person's list of children 
     * 
     * @return person's list of children
     */
    public ArrayList<Person> getChildList()
    {
        return childrenList;
    }
    
    /**
     * Set person's list of parent 
     * 
     * @param parent person's list of parent 
     */
    public void setParentList(ArrayList<Person> parent)
    {
        parentList = parent;
    }
    
    /**
     * Return person's list of parent 
     * 
     * @return person's list of parent 
     */
    public ArrayList<Person> getParentList()
    {
        return parentList;
    }
    
    /**
     * Returns true if person does not have a spouse 
     * 
     * @return true if spouse is null; false otherwise 
     */
    public boolean noSpouse()
    {
        return spouse == null;
    }
    
    /**
     * Set type of person 
     * 
     * @param t type 
     */
    public void setType(String t)
    {
        type = t;
    }
    
    /**
     * Returns type of person 
     * 
     * @return type 
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Sets personal information of person to that of person passed into parameter 
     * 
     * @param p person with desired personal information to set to 
     */
    public void setDetails(Person p)
    {
        this.name = p.getName();
        this.surname = p.getSurname();
        this.gender = p.getGender();
        this.lifeDesc = p.getLifeDesc();
        this.streetNo = p.getStreetNo();
        this.streetName = p.getStreetName();
        this.suburb = p.getSuburb();
        this.postCode = p.getPostcode(); 
    }
    
    /**
     * Returns full name of person 
     * <p>If the person's name is parents, spouse or children, return the name instead. </p>
     * <ul type="circle">
     * <li>Used to show desired result on TreeView</li>
     * </ul>
     * 
     * @return name if name is parents, spouse or children; full name otherwise 
     */
    @Override
    public String toString()
    {
        return (name.equalsIgnoreCase("parents") || name.equalsIgnoreCase("spouse") || name.equalsIgnoreCase("children"))?getName():getFullName();
    }
}
