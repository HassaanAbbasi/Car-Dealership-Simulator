//HASSAAN ABBASI

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CarDealershipSimulator 
{
  public static void main(String[] args)
  {
	  // Create a CarDealership object
	  CarDealership simulate = new CarDealership();
	  
	  // Then create an (initially empty) array list of type Car
	  ArrayList<Car> cars = new ArrayList<Car>();
	  
      // Then create some new car objects of different types
	  // See the cars file for car object details
	  // Add the car objects to the array list
	  // The ADD command should hand this array list to CarDealership object via the addCars() method
	  
	  //Greet the user
	  System.out.println("This is a Car Dealership simulation! Type ADD to begin. Type HELP at anytime for a list of commands.");
	  System.out.println();
	  
	  try 
	  {
		Scanner scanner = new Scanner(new File("cars.txt"));
		while(scanner.hasNextLine())
		{
			//Read info from the txt file and initialize variables 
			String mfr = scanner.next();
			String color = scanner.next();
			Car.Model model = Car.Model.valueOf(scanner.next());
			String powerCheck = scanner.next();
			Vehicle.powerSource power = Vehicle.powerSource.valueOf(powerCheck);
			double safety = scanner.nextDouble();
			int maxRange = scanner.nextInt();
			String awd = scanner.next();
			double price = scanner.nextInt();
			String battery = "Lithium-ion";
			boolean awdCheck = false;
			  
			if(awd.equals("AWD")) {awdCheck = true;}
			  
			if(powerCheck.equals("ELECTRIC_MOTOR"))
			{
				int rechargeTime = scanner.nextInt();
				cars.add(new ElectricCar(mfr, color, model, power, safety, maxRange, awdCheck, price, battery, rechargeTime));
			}
			  
			else
			{
				cars.add(new Car(mfr, color, model, power, safety, maxRange, awdCheck, price));
			}
		}
	  }
	  
	  catch (FileNotFoundException e) 
	  {
		System.out.println("File not found!");
	  }
	  
	  // Create a scanner object
	  Scanner in = new Scanner(System.in);
	  
	  // while the scanner has another line
	  while(in.hasNextLine())
	  {
	  //    read the input line
		  String line = in.nextLine();
		  
	  //    create another scanner object (call it "commandLine" or something) using the input line instead of System.in
		  Scanner commandLine = new Scanner(line);
	  
	  //    read the next word from the commandLine scanner
		  if(commandLine.hasNext())
		  {
			  String command = commandLine.next();
		  
      //	check if the word (i.e. string) is equal to one of the commands and if so, call the appropriate method via the CarDealership object  
			  if(command.equals("L")) 
			  {
				  System.out.println();
				  simulate.displayInventory();
				  System.out.println();
			  }
			  
			  else if(command.equals("Q"))
			  {
				  commandLine.close();
				  return;
			  }
			  
			  else if(command.equals("BUY"))
			  {
				  System.out.println();
				  int i = 0;
				  
				  try 
				  {
					  i = commandLine.nextInt();
					  String bought = simulate.buyCar(i);
					  System.out.println(bought);
				  }
				  
				  catch(NullPointerException e)
				  {
					  System.out.println("No car exists at: " + i);
				  }

				  catch(InputMismatchException e)
				  {
					  System.out.println("BUY must be followed by an INTEGER.");
				  }
				  
				  catch(IndexOutOfBoundsException e)
				  {
					  System.out.println("No car exists with VIN: " + i);
				  }
				  
				  catch(NoSuchElementException e)
				  {
					  System.out.println("BUY must be followed by an INTEGER.");
				  }
				  
				  System.out.println();
			  }
			  
			  else if(command.equals("RET"))
			  {
				  System.out.println();
				  int i = 0;
				  try
				  {
					  i = commandLine.nextInt();
					  simulate.returnCar(i);
				  }
				  
				  catch(NullPointerException e)
				  {
					  System.out.println("No car exists to return.");
				  }
				  
				  catch(Exception e)
				  {
					  System.out.println("This car was already returned.");
				  }
				  
				  System.out.println();
			  }
			  
			  else if(command.equals("ADD"))
			  {
				  System.out.println();
				  simulate.addCars(cars);
				  System.out.println();
			  }
			  
			  else if(command.equals("SPR"))
			  {
				  System.out.println();
				  simulate.sortByPrice();
				  System.out.println();
			  }
			  
			  else if(command.equals("SSR"))
			  {
				  System.out.println();
				  simulate.sortBySafetyRating();
				  System.out.println();
			  }
			  
			  else if(command.equals("SMR"))
			  {
				  System.out.println();
				  simulate.sortByMaxRange();
				  System.out.println();
			  }
			  
			  else if(command.equals("FCL"))
			  {
				  System.out.println();
				  simulate.filtersClear();
				  System.out.println();
			  }
			  
			  else if(command.equals("FAW"))
			  {
				  System.out.println();
				  simulate.filterByAWD();
				  System.out.println();
			  }
			  
			  else if(command.equals("FEL"))
			  {
				  System.out.println();
				  simulate.filterByElectric();
				  System.out.println();
			  }
			  
			  else if(command.equals("FPR"))
			  {
				  System.out.println();
				  if(commandLine.hasNextDouble())
				  {
					  double min = commandLine.nextDouble();
					  if(commandLine.hasNextDouble())
					  {
						  double max = commandLine.nextDouble();
						  simulate.filterByPrice(min, max);
						  System.out.println();
					  }
				  }
				  else
				  {
				  System.out.println("FPR must be followed by TWO DOUBLES, where the first double is smaller than the second.");
				  System.out.println();
				  }
			  }
			  
			  else if(command.equals("HELP"))
			  {
				  System.out.println();
				  System.out.println("L - Lists the cars in the dealership.");
				  System.out.println("Q - Quit the program.");
				  System.out.println("BUY [integer] - Buy a car in the dealership with a matching VIN integer.");
				  System.out.println("RET [integer] - Return the car that with an ID number as the integer.");
				  System.out.println("ADD - Add cars to the dealership.");
				  System.out.println("SPR - Sort the cars in the list from lowest price to highest price.");
				  System.out.println("SSR - Sort the cars in the list from lowest safety rating to highest safety rating.");
				  System.out.println("SMR - Sort the cars in the list from lowest max range to highest max range.");
				  System.out.println("FCL - Clear all filters applied.");
				  System.out.println("FAW - Filter the list by cars with all-wheel-drive.");
				  System.out.println("FEL - Filter the list by cars that are electric powered.");
				  System.out.println("FPR [integer1] [integer2] - Filter the list by cars whose price is between integer1 and integer2.");
				  System.out.println("SALES - Return a list of all transactions.");
				  System.out.println("SALES TEAM - Returns the current sales team.");
				  System.out.println("SALES TOPSP - Returns the sales person( or people if there is a tie) with the most sales.");
				  System.out.println("SALES STATS - Returns the sales statistics.");
				  System.out.println("SALES [integer] - Returns the sales made in the month represented by the integer.");
				  System.out.println();
			  }
			  
			  else if(command.equals("SALES"))
			  {
				  if(commandLine.hasNextInt())
				  {
					  System.out.println();
					  int afterInt = commandLine.nextInt();
					  simulate.aSys.getTransactionListM(afterInt);
					  System.out.println();
				  }
				  
				  else if(commandLine.hasNext())
				  {
					  String after = commandLine.next();
					  
					  if(after.equals("TEAM"))
					  {
						  System.out.println();
						  System.out.println(simulate.sTeam.teamCombine());
						  System.out.println();
					  }
					  
					  else if(after.equals("TOPSP"))
					  {
						  System.out.println();
						  System.out.println(simulate.aSys.topSalesPerson());
						  System.out.println();
					  }
					  
					  else if(after.equals("STATS"))
					  {
						  System.out.println();
						  simulate.aSys.getStats();
						  System.out.println();
					  }
					  
					  else
					  {
						  System.out.println();
						  System.out.println("SALES must be followed by a keyword. Type HELP to see the keywords that SALES may be followed by.");
						  System.out.println();
					  }
				  }
				  
				  else if(!commandLine.hasNext())
				  {
					  System.out.println();
					  simulate.aSys.getTransactionList();
					  System.out.println();
				  }
			  }
			  
			  
			  else
			  {
				  System.out.println();
				  System.out.println("Enter a valid command. Type HELP for commands.");
				  System.out.println();
			  }
		  }
		  else
		  {
			  System.out.println();
			  System.out.println("Enter a valid command. Type HELP for commands.");
			  System.out.println();
		  }
	  }
  }
}