import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.awt.Color;
/*
 *NewCritter is class extending Critter.It get the same actors as Critter does, but it has the different process 
 *among actors.NewCritter push away a rock, eat a flower and change it's color  to be the same as the rest actors
 * */
public class NewCritter extends Critter
{

	public NewCritter()
	{
		//set the default be orange
		setColor(Color.ORANGE);
	}
	public NewCritter(Color color)
	{
			setColor(color);
	}
	public void processActors(ArrayList<Actor> actors)
	{
		for (Actor a : actors)
		{
			//push away a rock
			if (a instanceof Rock)
			{
				pushAwayRock(a);
			}
			else if (a instanceof Flower)
			{
				//eat a flower
				a.removeSelfFromGrid();
			}
			//do nothing if it is NewCritter too
			else if (a instanceof NewCritter){}
			else
			{
				//set the color to be the same as the actors'
				a.setColor(getColor());
			}
		}
	}
	public void pushAwayRock(Actor rock)
	{
		Location rockLoc = rock.getLocation();
		Grid<Actor> gr = getGrid();
		if (!gr.isValid(rockLoc))
		{
			return;
		}
		//store the empty neighbors of rock
		ArrayList<Location> rockLocs = gr.getEmptyAdjacentLocations(rockLoc);
		//store the empty neighbors of NewCritter
		ArrayList<Location> locs = gr.getEmptyAdjacentLocations(getLocation());
		
		//get the across empty neighbors of rock and NewCritter
		for (int i = 0; i < rockLocs.size(); i++)
		{
			Location tmploc = rockLocs.get(i);
			for (int j = 0; j < locs.size(); j++)
			{
				Location tmploc1 = locs.get(j);
				if (tmploc.getRow() == tmploc1.getRow() && tmploc.getCol() == tmploc1.getCol())
				{
					//remove the across empty neighbors from rockLocs
					rockLocs.remove(tmploc);
					i--;
					break;
				}
			}
		}

		if (rockLocs.size() == 0)
		{
			//if the rock has no place to move, it will remove itself from the grid
			rock.removeSelfFromGrid();
		}
		else
		{
			//move to the new place
			rock.moveTo(rockLocs.get(0));
		}
	}
}
