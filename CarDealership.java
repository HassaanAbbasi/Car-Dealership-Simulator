//HASSAAN ABBASI

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

/**
   CarDealership -- This will be a car dealership. 
 */
public class CarDealership 
{
	//Objects for use
	SalesTeam sTeam = new SalesTeam();
	AccountingSystem aSys = new AccountingSystem();
	
	//Instance variable
	private ArrayList<Car> cars;
	
	//Filter variables
	public boolean ele = false;
	public boolean AWD = false;
	public boolean price = false;
	public double minPrice = 0.0;
	public double maxPrice = 0.0;
	
	/**
	   safeSort -- Special inner class to sort by safety rating.
	 */
	class safeSort implements Comparator<Car>
	{
		/**
		   @param car1 First car
		   @param car2 Second car
		 */
		public int compare(Car car1, Car car2) 
		{
			if(car1.getSafetyRating() < car2.getSafetyRating()) {return -1;}
			else if(car1.getSafetyRating() > car2.getSafetyRating()) {return 1;}
			return 0;
		}
	}
	
	/**
	   maxRangeSort -- Special inner class to sort by max range.
	 */
	class maxRangeSort implements Comparator<Car>
	{
		/**
		   @param car1 First car
		   @param car2 Second car
		 */
		public int compare(Car car1, Car car2) 
		{
			if(car1.getMaxRange() < car2.getMaxRange()) {return -1;}
			else if(car1.getMaxRange() > car2.getMaxRange()) {return 1;}
			return 0;
		}
	}
	
	/**
	   A constructor method to have cars refer to an empty
	   array list.
	 */
	public CarDealership()
	{
		cars = new ArrayList<Car>();
	}
	
	/**
	   Add the cars from the given array list to the 
	   instance array list.
	   @param newCars A list of car objects.
	 */
	public void addCars(ArrayList<Car> newCars)
	{
		for(Car x : newCars)
		{
			cars.add(x);
		}
	}
	
	/**
	   Buy a car based on the VIN given
	   @param VIN The VIN of the car you want to buy.
	 */
	public String buyCar(int VIN)
	{
		Car car = null;
		if(cars.size() == 0) {throw new IndexOutOfBoundsException();}
		
		for(int i = 0; i < cars.size(); i++) //Find a car object with the VIN
		{
			if(cars.get(i).getVIN() == VIN) 
			{
				car = cars.get(i);
				cars.remove(i);
			}
		}
		if(car == null) {throw new NullPointerException();}
		
		String sMember = sTeam.getTeamMember(); //Get a random sales team member
		
		Calendar date;
		int month = (int) (Math.random() * 12);
		int day = 1;
		
		if(month == 2) //Case for February
		{
			day = (int) (Math.random() * 29);
		}
		
		else if(month % 2 != 0) //Case for odd-numbered months
		{
			day = (int) (Math.random() * 32);
		}
		
		else
		{
			day = (int) (Math.random() * 31); //Case for even-numbered days
		}
		
		date = new GregorianCalendar(2019, month, day);
		
		String receipt = aSys.add(date, car, sMember, "BUY", car.getPrice());
		return receipt;
	}
	
	/**
	   Returns a car based on the transaction ID
	   @param transaction The transaction receipt
	 */
	public void returnCar(int transaction) throws Exception
	{
		Transaction trans = aSys.getTransaction(transaction);
		
		if(cars.contains(trans.getCar())) {throw new Exception();}
		//Info about date bought
		int dayOfBuy = trans.getDate().get(Calendar.DAY_OF_MONTH);
		int monthOfBuy = trans.getDate().get(Calendar.MONTH);
		int maxDay = trans.getDate().getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//Info about date return
		Calendar dateOfRet;
		int dayOfRet = (int) (Math.random() * ((maxDay - dayOfBuy) + 1)) + dayOfBuy;
		
		dateOfRet = new GregorianCalendar(2019, monthOfBuy, dayOfRet);
		
		aSys.add(dateOfRet, trans.getCar(), trans.getSalesPerson(), "RETURN", trans.getSalePrice());
		cars.add(trans.getCar());
		String reciept = trans.display();
		System.out.println(reciept);
	}
	
	/**
	   Shows the car list based on filters.
	 */
	public void displayInventory()
	{
		if(!ele && !AWD && !price) 
		{
			for(int i = 0; i < cars.size(); i++)
			{
				System.out.println(cars.get(i).display());
			}
		}
		
		else if(ele && AWD && price)
		{
			for(int i = 0; i < cars.size(); i++)
			{
				if(cars.get(i).getPower().equals(Vehicle.powerSource.ELECTRIC_MOTOR) && 
					cars.get(i).getAWD() == true && cars.get(i).getPrice() >= minPrice
					&& cars.get(i).getPrice() <= maxPrice)
				{
					System.out.println(cars.get(i).display());
				}
			}
		}
		
		else if(ele && AWD)
		{
			for(int i = 0; i < cars.size(); i++)
			{
				if(cars.get(i).getPower().equals(Vehicle.powerSource.ELECTRIC_MOTOR) && 
					cars.get(i).getAWD() == true)
				{
					System.out.println(cars.get(i).display());
				}
			}
		}
		
		else if(AWD)
		{
			for(int i = 0; i < cars.size(); i++)
			{
				if(cars.get(i).getAWD() == true)
				{
					System.out.println(cars.get(i).display());
				}
			}
		}
		
		else if(ele && price)
		{
			for(int i = 0; i < cars.size(); i++)
			{
				if(cars.get(i).getPower().equals(Vehicle.powerSource.ELECTRIC_MOTOR) && 
					cars.get(i).getPrice() >= minPrice && cars.get(i).getPrice() <= maxPrice)
				{
					System.out.println(cars.get(i).display());
				}
			}
		}
		
		else if(ele)
		{
			for(int i = 0; i < cars.size(); i++)
			{
				if(cars.get(i).getPower().equals(Vehicle.powerSource.ELECTRIC_MOTOR))
				{
					System.out.println(cars.get(i).display());
				}
			}
		}
		
		else if(price)
		{
			for(int i = 0; i < cars.size(); i++)
			{
				if(cars.get(i).getPrice() >= minPrice && cars.get(i).getPrice() <= maxPrice)
				{
					System.out.println(cars.get(i).display());
				}
			}
		}
	}
	
	/**
	   Filter the car list by electric cars.
	 */
	public void filterByElectric()
	{
		ele = true;
	}
	
	/**
	   Filter the car list by AWD cars.
	 */
	public void filterByAWD()
	{
		AWD = true;
	}
	
	/**
	   Filter the car list by given price range.
	   @param min The minimum price.
	   @param max The maximum price.
	 */
	public void filterByPrice(double min, double max)
	{
		price = true;
		minPrice = min;
		maxPrice = max;
	}
	
	/**
	   Clear all filters.
	 */
	public void filtersClear()
	{
		ele = false;
		AWD = false;
		price = false;
	}
	
	/**
	   Sort by the price.
	 */
	public void sortByPrice()
	{
		Collections.sort(cars);
	}
	
	/**
	   Sort by the safety rating.
	 */
	public void sortBySafetyRating()
	{
		Collections.sort(cars, new safeSort());
	}
	
	/**
	   Sort by the max range.
	 */
	public void sortByMaxRange()
	{
		Collections.sort(cars, new maxRangeSort());
	}
}
