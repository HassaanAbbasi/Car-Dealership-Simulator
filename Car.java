//HASSAAN ABBASI

/**
   Car -- This will be a type of vehicle. 
 */
public class Car extends Vehicle implements Comparable<Car>
{
	//Enum for model
	public static enum Model
	{
		SEDAN, SUV, SPORTS, MINIVAN;
	}
	
	//Instance variables
	public Model model;
	private int maxRange;
	private double safetyRating;
	private boolean AWD;
	private double price;
	
	/**
    A constructor method to initialize the properties of a car with the given
    parameters.
    @param mfr The manufacturer of the vehicle.
    @param color The color of the vehicle.
    @param power The power of the vehicle.
    @param model The model of the car.
    @param maxRange Mileage of the car
    @param safetyRating The safety rating of the car.
    @param AWD The All-Wheel-Drive status of the car.
    @param price The cost to buy the car.
	*/
	public Car(String mfr, String color, Model model, powerSource power, double safetyRating, int maxRange,
			boolean AWD, double price)
	{
		super(mfr, color, power);
		this.model = model;
		this.maxRange = maxRange;
		this.safetyRating = safetyRating;
		this.AWD = AWD;
		this.price = price;
	}
	
	/**
	   Returns a string describing the car.
	 */
	public String display()
	{
		return super.display() + ", " + model + ", " + power + 
			", " + safetyRating + ", " + maxRange + ", " + AWD + ", " + price + ", ";
	}
	
	/**
	   Return the max range the car has.
	 */
	public int getMaxRange()
	{
		return maxRange;
	}
	
	/**
	   Set the max range for the car.
	   @param max The max range to set.
	 */
	public void setMaxRange(int max)
	{
		maxRange = max;
	}
	
	/**
	   Return the safety rating for the car.
	 */
	public double getSafetyRating()
	{
		return safetyRating;
	}
	
	/**
	   Set the safety rating for the car.
	   @param safety The safety rating to set.
	 */
	public void setSafetyRating(double safety)
	{
		safetyRating = safety;
	}
	
	/**
	   Return the AWD status of the car.
	 */
	public boolean getAWD()
	{
		return AWD;
	}
	
	/**
	   Set the AWD for the car.
	   @param awd The max range to set.
	 */
	public void setAWD(boolean awd)
	{
		AWD = awd;
	}
	
	/**
	   Return the price for the car.
	 */
	public double getPrice()
	{
		return price;
	}
	
	/**
	   Set the price for the car.
	   @param pri The price to set.
	 */
	public void setPrice(double pri)
	{
		price = pri;
	}
	
	/**
	   Return the model of the car.
	 */
	public Model getModel()
	{
		return model;
	}
	
	/**
	   Set the model of the vehicle.
	   @param mod The model of the car to set.
	 */
	public void setModel(Model mod)
	{
		model = mod;
	}
	
	public boolean equals(Object other)
	{
		Car otherObj = (Car) other;
		if(super.equals(otherObj))
			{
				return model == otherObj.model && AWD == otherObj.AWD;
			}
		return false;
	}
	
	/**
	   Compares car objects based on price.
	   @param other The Car object you want to compare to.
	 */
	public int compareTo(Car other)
	{
		if(price < other.price) {return -1;}
		else if(price > other.price) {return 1;}
		else {return 0;}
	}
}
