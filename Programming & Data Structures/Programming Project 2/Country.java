/***
 * Class to model the entity Country
 * Caterina Black
 * version 0.1
 * Date of creation: April 13, 2023
 * Last Date Modified: April 21, 2023
 */

import java.util.Iterator;
import java.util.ListIterator;

public class Country{
    private String name;
    private ArrayList<Pair<Integer, Double>> carbonEmission;
    private ArrayList<Pair<Integer, Double>> carbonCapita;

    /**
     * Constructor with one parameter and sets array of carbonEmission and carbonCapita to size 100
     * @param name value of the country name
     */
    public Country(String name){
        this.name = name;
        carbonEmission = new ArrayList<>(100);
        carbonCapita = new ArrayList<>(100);
    }
    /**
     * Getter for country name
     * @param no
     * @return name
     */
    public String getName(){ return name;}
    /**
     * Setter for country name
     * @param name value of the country name
     * @return  none
     */
    public void setName(String name){ this.name = name;}
    /**
     * method that adds to double list with values year and tons
     * @param year value representing year of recorded carbon emission
     * @param tons value representing tons in carbon emissions
     */
    public void addCarbonEmission(int year, double tons){
        Pair<Integer,Double> carbon = new Pair<>(year, tons); //creates pair to add to list
        carbonEmission.add(carbon);
    }
    /**
     * method that adds to double list with values year and tons
     * @param year value representing year of recorded carbon capita
     * @param tons value representing tons in carbon capita
     */
    public void addCarbonCapita(int year, double tons){
        Pair<Integer,Double> carbon = new Pair<>(year, tons); //creates pair to add to list
        carbonCapita.add(carbon);
    }
    /**
     * List Iterator that runs through carbonEmissions to find values of a certain year 
     * @param year being searched for
     * @return iter if found and null otherwise
     */
    public ListIterator<Pair<Integer, Double>> getEmission(int year){
        ListIterator<Pair<Integer, Double>> iter = carbonEmission.listIterator();
        
        while(iter.hasNext()){
            Pair<Integer, Double> p = iter.next();
            if(p.getFirst() == year){
                iter.previous();
                return iter;
            }
        }
        return null; 
    }
    /**
     * List Iterator that runs through carbonCapita to find values of a certain year 
     * @param year being searched for
     * @return iter if found and null otherwise
     */
    public ListIterator<Pair<Integer, Double>> getCapita(int year){
        ListIterator<Pair<Integer, Double>> iter = carbonCapita.listIterator();
        while(iter.hasNext()){
            Pair<Integer, Double> p = iter.next();
            if(p.getFirst() == year){
                iter.previous();
                return iter;
            }
        }

        return null;
    }
    /**
     * Method that returns string values of name, carbonEmission, and carbonCapita of a country
     * @param no
     * @return string values of country
     */
    public String toString(){
        return name + " " + carbonEmission + " " + carbonCapita;
    }
    /**
     * Method that checks if 2 objects have the same name
     * @param o object being compared
     * @return true or false based on comparison
     */
    public boolean equals(Object o) {
		if(o instanceof Country) {
			Country c = (Country) o;
			return name.equals(c.name);
		}
		return false;
	}
}