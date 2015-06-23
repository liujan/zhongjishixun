import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;

public class KingCrab extends CrabCritter
{
	//push the actors neighboring to KingCrab away
	public void processActors(ArrayList<Actor> actors)
	{
		Grid<Actor> gr = getGrid();
		//get the empty neighboring location of KingCrab
		ArrayList<Location> locsNew = gr.getEmptyAdjacentLocations(getLocation());
		for (Actor actor : actors)
		{
			Location loc = actor.getLocation();
			//if location of an actor is invalid, then ignore this actor
			if (!gr.isValid(loc))
			{
				continue;
			}
			//get the empty neighboring location of the actor
			ArrayList<Location> locs = gr.getEmptyAdjacentLocations(loc);
			//find the cross the empty neiboring location of KingCrab and actor
			for (int i = 0 ;i < locs.size(); i++)
			{
				Location locNew = locs.get(i);
				for (int j = 0; j < locsNew.size(); j++)
				{
					Location l = locsNew.get(j);
					//remove the cross neighboring empty location from the actor's empty neighboring locations
					if (locNew.getRow() == l.getRow() && l.getCol() == locNew.getCol())
					{
						locs.remove(locNew);
						i--;
						break;
					}
				}
			}
			//if the actor has no place to move, then remove itself from the grid
			if (locs.size() == 0)
			{
				actor.removeSelfFromGrid();
			}
			//else move to the new location
			else
			{
				actor.moveTo(locs.get(0));
			}
		}
	}
}
