//HASSAAN ABBASI

import java.util.LinkedList;
import java.util.ListIterator;

/**
   Simulates the group of people that are selling cars.
 */
public class SalesTeam 
{
	//Instance Variables
	private LinkedList<String> team = new LinkedList<String>();
	
	/**
	   Constructor for the sales team. A group of 6.
	 */
	public SalesTeam()
	{
		team.add("Jimbo");
		team.add("Moby");
		team.add("Harley");
		team.add("Santa");
		team.add("Elon");
		team.add("Gates");
	}
	
	/**
	   Returns a random member from the team.
	   @param team The list of team members.
	 */
	public String getTeamMember()
	{
		int rand = (int) (Math.random()*5);
		ListIterator<String> iter = team.listIterator();
		for(int i = 0; i < rand; i++)
		{
			iter.next();
		}
		return iter.next();
	}
	
	/**
	   Returns the whole team.
	   @param team The list of team members.
	 */
	public String teamCombine()
	{
		ListIterator<String> iter = team.listIterator();
		String totalTeam = "";
		while(iter.hasNext())
		{
			totalTeam = totalTeam + " " + iter.next();
		}
		return "Team:" + totalTeam;
	}
	
	/**
	   Returns the i person of the sales team
	   @param i The member to return.
	 */
	public String returnIPerson(int i)
	{
		ListIterator<String> iter = team.listIterator();
		for(int x = 0; x < i; x++)
		{
			iter.next();
			
		}
		return iter.next();
	}
	
	/**
	   Returns the sales team size
	 */
	public int salesTeamSize()
	{
		return team.size();
	}
}
