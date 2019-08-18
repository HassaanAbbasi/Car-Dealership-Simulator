//HASSAAN ABBASI

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
   Works with Transaction objects to simulate an accounting system
 */
public class AccountingSystem 
{
	//Instance Variable
	private Map<Integer, Transaction> transactions;
	
	/**
	   A constructor method to have transactions refer to an empty
	   array list.
	 */
	public AccountingSystem()
	{
		transactions = new HashMap<Integer, Transaction>();
	}
	
	/**
	   Adds transaction to hash map
	   @param date Date of transaction
	   @param car Car being bought or returned
	   @param salesPerson Name of the sales person
	   @param type Type of transaction (BUY or RETURN)
       @param salePrice Price the car was sold for
	 */
	public String add(Calendar date, Car car, String salesPerson, String type, double salePrice)
	{
		int id = (int) (Math.random() * 100);
		Transaction tran = new Transaction(id, (GregorianCalendar) date, car, salesPerson, type, salePrice);
		transactions.put(id, tran);
		return tran.display();
	}
	
	/**
	   Finds a transaction based on transaction ID
	   @param id The ID of the car to retrieve
	 */
	public Transaction getTransaction(int id)
	{
		for(Integer trID : transactions.keySet())
		{
			if(trID == id) {return transactions.get(trID);}
		}
		return null;
	}
	
	/**
	   Returns the whole list of transactions that occurred.
	 */
	public void getTransactionList()
	{
		for(Integer trID : transactions.keySet())
		{
			if(transactions.get(trID).getDate().get(Calendar.YEAR) == 2019) {System.out.println(transactions.get(trID).display());}
		}
	}
	
	/**
	   Returns the top sales person
	 */
	public String topSalesPerson()
	{
		SalesTeam teamObj = new SalesTeam();
		Map<String, Integer> salesPeopleSells = new TreeMap<String,Integer>();
		Set<String> keys = salesPeopleSells.keySet();
		String topSP =""; //Top sales person
		String topSPTie = ""; //Top sales person string for tied sales
		int top = 0; //Number for top sales
		
		//Mapping the sales team members to initial sales of 0
		for(int i = 0; i < teamObj.salesTeamSize(); i++)
		{
			salesPeopleSells.put(teamObj.returnIPerson(i), 0);
		}
		
		//Updating the sales for every team member based on BUY and RETURN
		for(Integer trID : transactions.keySet())
		{
			if(transactions.get(trID).getType().equals("BUY"))
			{
				String person = transactions.get(trID).getSalesPerson();
				int sales = salesPeopleSells.get(person) + 1;
				salesPeopleSells.put(person, sales);
			}
			
			else if(transactions.get(trID).getType().equals("RETURN"))
			{
				String person = transactions.get(trID).getSalesPerson();
				int sales = salesPeopleSells.get(person) - 1;
				salesPeopleSells.put(person, sales);
			}
		}
		
		//Finding the top sales person
		for(String key : keys)
		{
			if(salesPeopleSells.get(key) > top)
			{
				topSP = key;
				top = salesPeopleSells.get(key);
			}
			
		}
		
		//Finding another top sales person if there's a tie
		for(String key : keys)
		{
			if(salesPeopleSells.get(key) == top && !key.equals(topSP))
			{
				topSPTie = topSPTie + " " + key; 
			}
		}
		
		if(top == 0) {return "No top sales person currently.";}
		else if(!topSPTie.equals("")) {return "Team members with the most sales (" + top + " sales): " + topSPTie + " " + topSP;}
		else {return "Top Sales Person: " + topSP + " with " + top + " sales!";}
	}
	
	/**
	   Returns the transactions that occurred in a given month.
	   @param i The month number
	 */
	public void getTransactionListM(int i)
	{
		for(Integer trID : transactions.keySet())
		{
			if(transactions.get(trID).getDate().get(Calendar.MONTH) == i) {System.out.println(transactions.get(trID).display());}
		}
	}
	
	/**
	   Returns the statistics of transactions in a year.
	 */
	public void getStats()
	{
		int totalSales = 0; //Total number of sales
		int averageSales = 0; //Average sales
		int carsSold = 0; //Number of cars sold
		int highestSalesMNum = 0; //Highest sales month in number format
		int mostSales = 0; //The highest number of sales
		String highestSalesM = ""; //Highest sales month in string format
		String highestSalesMTie = ""; //Highest sales month string in case there's a tie
		int carsRet = 0; //Number of cars returned
		
		//Total sales function
		for (Integer trID : transactions.keySet())
		{
			if(transactions.get(trID).getType().equals("BUY"))
			{
				totalSales += transactions.get(trID).getSalePrice();
			}
			else
			{
				totalSales -= transactions.get(trID).getSalePrice();
			}
		}
		
		//Average sales function
		averageSales = totalSales / 12;
		
		//Cars sold/returned function
		for (Integer trID : transactions.keySet())
		{
			if(transactions.get(trID).getType().equals("BUY"))
			{
				carsSold++;
			}
			else
			{
				carsSold--;
				carsRet++;
			}
		}
		
		//Highest sales month function
		Map<Integer, Integer> highestM = new TreeMap<Integer,Integer>(); //Mapping month number to sales
		Set<Integer> keys = highestM.keySet();
		//SimpleDateFormat sdf = new SimpleDateFormat("MMM");
		
		for(Integer trID : transactions.keySet())
		{
			highestM.put(transactions.get(trID).getDate().get(Calendar.MONTH), 0);
		}
		
		for(Integer trID : transactions.keySet())
		{
			if(transactions.get(trID).getType().equals("BUY"))
			{
				int sales = highestM.get(transactions.get(trID).getDate().get(Calendar.MONTH)) + 1;
				highestM.put(transactions.get(trID).getDate().get(Calendar.MONTH), sales);
			}
			
			else if(transactions.get(trID).getType().equals("RETURN"))
			{
				int sales = highestM.get(transactions.get(trID).getDate().get(Calendar.MONTH)) - 1;
				highestM.put(transactions.get(trID).getDate().get(Calendar.MONTH), sales);
			}
		}
		
		for(Integer key : keys)
		{
			if(highestM.get(key) > mostSales)
			{
				mostSales = highestM.get(key);
				highestSalesMNum = key;
			}
			
		}
		
		for(Integer month : keys)
		{
			if(highestM.get(month) == mostSales)
			{
				switch(month)
				{
					case 0:
						highestSalesMTie += " Jan ";
						break;
					case 1:
						highestSalesMTie += " Feb ";
						break;
					case 2:
						highestSalesMTie += " Mar ";
						break;
					case 3:
						highestSalesMTie += " Apr ";
						break;
					case 4:
						highestSalesMTie += " May ";
						break;
					case 5:
						highestSalesMTie += " Jun ";
						break;
					case 6:
						highestSalesM += " Jul ";
						break;
					case 7:
						highestSalesMTie += " Aug ";
						break;
					case 8:
						highestSalesMTie += " Sep ";
						break;
					case 9:
						highestSalesMTie += " Oct ";
						break;
					case 10:
						highestSalesMTie += " Nov ";
						break;
					case 11:
						highestSalesMTie += " Dec ";
						break;
				}
			}
			
		}
		
		switch(highestSalesMNum)
		{
			case 0:
				if(totalSales == 0)
				{
					highestSalesM = "No highest sales month";
					break;
				}
				highestSalesM = "Jan";
				break;
			case 1:
				highestSalesM = "Feb";
				break;
			case 2:
				highestSalesM = "Mar";
				break;
			case 3:
				highestSalesM = "Apr";
				break;
			case 4:
				highestSalesM = "May";
				break;
			case 5:
				highestSalesM = "Jun";
				break;
			case 6:
				highestSalesM = "Jul";
				break;
			case 7:
				highestSalesM = "Aug";
				break;
			case 8:
				highestSalesM = "Sep";
				break;
			case 9:
				highestSalesM = "Oct";
				break;
			case 10:
				highestSalesM = "Nov";
				break;
			case 11:
				highestSalesM = "Dec";
				break;
		}
		
		if(mostSales == 0 || carsSold == 0) {System.out.println("Total Sales: " + totalSales + " - Average Sales: " + averageSales + " - Cars Sold: " + carsSold + " - There is no highest sales month - " + "Cars Returned: " + carsRet);}
		else  {System.out.println("Total Sales: " + totalSales + " - Average Sales: " + averageSales + " - Cars Sold: " + carsSold + " - Highest Sales Month(s): " + highestSalesMTie + " - Cars Returned: " + carsRet);}
	}
}
