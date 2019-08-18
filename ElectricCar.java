//HASSAAN ABBASI

/**
   ElectricCar -- This will be a type of car. 
 */
public class ElectricCar extends Car
{
	//Instance variables
	private int rechargeTime;
	private String batteryType;
	
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
    @param rechargeTime The recharge time in minutes.
	*/
	public ElectricCar(String mfr, String color, Model model, powerSource power, double safetyRating, 
			int maxRange, boolean AWD, double price, String batteryType,
			int rechargeTime) 
	{
		super(mfr, color, model, power,  safetyRating, maxRange, AWD, price);
		this.setRechargeTime(rechargeTime);
		this.batteryType = batteryType;
	}

	/**
	   Return the recharge time for the car.
	 */
	public int getRechargeTime() 
	{
		return rechargeTime;
	}

	/**
	   Set the recharge time for the car.
	   @param rechargeTime The rechargeTime to set for the car.
	 */
	public void setRechargeTime(int rechargeTime) 
	{
		this.rechargeTime = rechargeTime;
	}
	
	public String display()
	{
		return super.display() + batteryType + ", " + rechargeTime;
	}

}
