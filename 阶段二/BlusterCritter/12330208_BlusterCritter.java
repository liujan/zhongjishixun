import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;

public class BlusterCritter extends Critter
{
	private int c;
	public BlusterCritter(int c)
	{
		//set the courage of BlusterCritter
		this.c = c;
	}
	public ArrayList<Actor> getActors()
	{
		Grid<Actor> gr = getGrid();

		//to store the actors two steps within the BlusterCritter
		ArrayList<Actor> all_actors = new ArrayList<Actor>();
		//actors which is neiboring to BlusterCritter
		ArrayList<Actor> actors = gr.getNeighbors(getLocation());

		for (Actor a : actors)
		{
			if (!all_actors.contains(a))
			{
					all_actors.add(a);
			}
			Location loc = a.getLocation();
			if (gr.isValid(loc))
			{
				//actors which is neibofing to the neighbors
				ArrayList<Actor> tmp = gr.getNeighbors(loc);
				for (Actor b: tmp)
				{
					if (!all_actors.contains(b))
					{
						all_actors.add(b);
					}
				}
			}
		}
		return all_actors;
	}

	public void processActors(ArrayList<Actor> actors)
	{


		//size of the actors two steps within BlusterCritter
		int n = actors.size();
		double DARKENING_FACTOR = 0.05;
		double BRIGHTING_FACTOR = 0.08;
		//current color
		Color color = getColor();
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();

		if (n < c)
		{
			//brights the BlusterCritter
			red = (int) (red * (1 + BRIGHTING_FACTOR) + 1);
			green = (int) (green * (1 + BRIGHTING_FACTOR) + 1);
		    blue = (int) (blue * (1 + BRIGHTING_FACTOR) + 1);
		}
		else
		{
			//darken the BlusterCritter
			red = (int) (red * (1 - DARKENING_FACTOR) + 1);
			green = (int) (green * (1 - DARKENING_FACTOR) + 1);
		    blue = (int) (blue * (1 - DARKENING_FACTOR) + 1);
		}
		//if the red is over
		if (red >= 255)
		{
			red = 255;
		}
		//if the green is over 
		if (green >= 255)
		{
			green = 255;
		}
		//if blue is over
		if (blue >= 255)
		{
			blue = 255;
		}
		setColor(new Color(red, green, blue));
	}
}
