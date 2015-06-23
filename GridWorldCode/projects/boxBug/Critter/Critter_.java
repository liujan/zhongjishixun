import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;
import info.gridworld.grid.*;

public class Critter_ extends Actor
{
	public Critter_(Color color)
	{
		setColor(color);
	}
	public Critter_(){}

	public void act()
	{
		Grid<Actor> gr = getGrid();
		ArrayList<Actor> actors = gr.getNeighbors(getLocation());
		removeActors(actors);
		ArrayList<Location> locations = gr.getEmptyAdjacentLocations(getLocation());
		Location newLoc = selectLocation(locations);
		moveTo(newLoc);
	}
	public void removeActors(ArrayList<Actor> actors)
	{
		if (null == actors)
			return;
		for (Actor a : actors)
		{
			if (!(a instanceof Rock) && !(a instanceof Critter))
			{
				a.removeSelfFromGrid();
			}
		}
	}
	public Location selectLocation(ArrayList<Location> locations)
	{
		if (null == locations)
			return getLocation();
		int size = locations.size();
		int r = (int)(Math.random() * size);
		return locations.get(r);
	}
}
