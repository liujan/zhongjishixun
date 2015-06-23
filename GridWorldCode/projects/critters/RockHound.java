import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
//RockHound eat only rock neighborint tot it
public class RockHound extends Critter
{
	//remvoe the neighborint rock from the grid
	public void processActors(ArrayList<Actor> actors)
	{
		//travels the actors
		for (Actor a : actors)
		{
			//if a is not null and is a Rock, then remove it from the grid
			if (a instanceof Rock)
			{
				//remove a from the grid
				a.removeSelfFromGrid();
			}
		}
	}
}
