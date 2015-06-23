/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.*;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.gui.*;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonCritter extends Critter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
		//判断周围可处理的Actor数目是否为零
        if (0 == n)
		{
			//如果Actor数目为零，则令ChameleonCritter颜色变暗
			double darkingFactor = 0.05;
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - darkingFactor));
			int green = (int) (c.getGreen() * (1 - darkingFactor));
			int blue = (int) (c.getBlue() * (1 - darkingFactor));

	    	setColor(new Color(red, green, blue));
		}
		else 
		{        
			//如果Actor数目不为零，则改变ChameleonCritter颜色
			 int r = (int) (Math.random() * n);

        		Actor other = actors.get(r);
        		setColor(other.getColor());
		}
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
		Grid<Actor> gr = super.getGrid();
		if (null == gr || !gr.isValid(loc))
		{
			return;
		}
		//move to the new location
		super.makeMove(loc);
    }
}
