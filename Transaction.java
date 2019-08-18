//HASSAAN ABBASI

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
   Objects of this class store information about the buying or returning of a car.
 */
public class Transaction 
{
	//Instance Variables
	private int id;
	private GregorianCalendar date;
	private Car car;
	private String salesPerson;
	private String type;
	private double salePrice;
	
	/**
	   Constructor for the Transaction class
	   @param id ID of the transaction
	   @param date Date of transaction
	   @param car Car being bought or returned
	   @param salesPerson Name of the sales person
	   @param type Type of transaction (BUY or RETURN)
	   @param salePrice Price the car was sold for
	 */
	public Transaction(int id, GregorianCalendar date, Car car, String salesPerson, String type, double salePrice)
	{
		this.id = id;
		this.date = date;
		this.car = car;
		this.salesPerson = salesPerson;
		this.type = type;
		this.salePrice = salePrice;
	}
	
	/**
	   Returns a string describing the transaction
	 */
	public String display()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
		return "ID: " + id + " - Date: " + sdf.format(date.getTime()) + " - " + type + " - Sales Person: " + 
		salesPerson + " - " + car.display() + " Sold for: " + salePrice;
	}

	/**
	   Get the date.
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**
	   Get the ID
	 */
	public int getId() 
	{
		return id;
	}
	
	/**
	   Get the car
	 */
	public Car getCar() 
	{
		return car;
	}
	
	/**
	   Get the sales person
	 */
	public String getSalesPerson() 
	{
		return salesPerson;
	}
	
	/**
	   Get the sale price
	 */
	public Double getSalePrice() 
	{
		return salePrice;
	}

	/**
	   Get the type of transaction
	 */
	public String getType() {
		return type;
	}
	
}
