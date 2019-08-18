//HASSAAN ABBASI

/**
   Vehicle -- This will be our basic blueprint for a vehicle object. From
   this, we shall form more detailed vehicles such as electric cars.
 */
public class Vehicle 
{
	//Enum for power source
	public static enum powerSource
	{
		ELECTRIC_MOTOR, GAS_ENGINE;
	}
	
	//Instance variables
	public powerSource power;
	private String mfr;
	private String color;
	private int VIN;
	
	/**
    A constructor method to initialize the properties of a vehicle with the given
    parameters.
    @param mfr The manufacturer of the vehicle.
    @param color The color of the vehicle.
    @param power The power of the vehicle.
	*/
	public Vehicle(String mfr, String color, powerSource power)
	{
		this.mfr = mfr;
		this.color = color;
		this.power = power;
		VIN = (int)(Math.random()*400 + 100);
	}
	
	/**
	   Return the manufacturer of the vehicle.
	 */
	public String getMfr()
	{
		return mfr;
	}
	
	/**
	   Set the manufacturer of the vehicle.
	   @param name1 Name of the manufacturer to set.
	 */
	public void setMfr(String name1)
	{
		mfr = name1;
	}
	
	/**
	   Return the color of the vehicle.
	 */
	public String getColor()
	{
		return color;
	}
	
	/**
	   Set the color of the vehicle.
	   @param color1 Color of the vehicle to set.
	 */
	public void setColor(String color1)
	{
		color = color1;
	}
	
	/**
	   Return the power of the vehicle.
	 */
	public powerSource getPower()
	{
		return power;
	}
	
	/**
	   Set the power of the vehicle.
	   @param power1 Power of the vehicle to set.
	 */
	public void setPower(powerSource power1)
	{
		power = power1;
	}
	
	/**
	   Get the VIN
	 */
	public int getVIN() 
	{
		return VIN;
	}
	
	/**
	   Check if two objects have the same manufacturer, power.
	   @param other The object you want to compare to.
	 */
	public boolean equals(Object other)
	{
		Vehicle otherObject = (Vehicle) other;
		return mfr.equals(otherObject.mfr) && power == otherObject.power;
	}
	
	/**
	   Returns a string containing the manufacturer name and color.
	 */
	public String display()
	{
		return "VIN: " + VIN + " - " + mfr + ", " + color;
	}
}
