import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;

public class QuickCrab extends CrabCritter
{
	public void act()
	{
		if (getGrid() == null)
		{
			//if the grid is invalid, end the function
			return;
		}
		//get the actors neighboring and process them
		ArrayList<Actor> actors= getActors();
		processActors(actors);
		//get the possible locations to move and selece one of them to move to.
		ArrayList<Location> moveLocs = getMoveLocations();
		Location loc = selectMoveLocation(moveLocs);
		//move to the new location
		makeMove(loc);
	}
	public ArrayList<Location> getMoveLocations()
	{
		//to store the locations 
		ArrayList<Location> locs = new ArrayList<Location>();
		//direction on the left and right of QuickCrab
		int[] dirs = {Location.LEFT, Location.RIGHT};
		//get the locations two steps away from QuickCrab
		for (Location loc : getLocationsInDirections(dirs))
		{
			if (getGrid().isValid(loc) && getGrid().get(loc) == null)
			{
				locs.add(loc);
			}
		}
		//if there are empty locations in the right of left
		if (locs.size() != 0)
		{
			return locs;
		}
		else
		{
			//if there are no empty locations two steps in the rihgt or left
			//call the getLocationInDirections of super class and move likes a CrabCritter
			for (Location loc : super.getLocationsInDirections(dirs))
			{
				if (getGrid().isValid(loc) && getGrid().get(loc) == null)
				{
					locs.add(loc);
				}
			}
			return locs;
		}
	}
	
	//get the locaitons two steps away from QuickCritter
	public ArrayList<Location> getLocationsInDirections(int[] dirs)
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		Location loc = getLocation();
		for (int d : dirs)
		{
			Location newLoc =loc;
			//two steps away
			for (int j = 0; j < 2; j++)
			{
				if (getGrid().isValid(newLoc))
				{
					//get the location two steps away
					newLoc = newLoc.getAdjacentLocation(getDirection() + d);
				}
			}
			//add the new location the locs
			if (getGrid().isValid(newLoc))
			{
				locs.add(newLoc);
			}
		}
		return locs;
	}

	//get the actors in front of ,left,right of the QuickCritter
	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> actors = new ArrayList<Actor>();
		int[] dirs={Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT};
		for(Location loc : super.getLocationsInDirections(dirs))
		{
			Actor a = getGrid().get(loc);
			if (a != null)
			{
				actors.add(a);
			}
		}
		return actors;
	}
}
