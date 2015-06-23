package info.gridworld.actor;

import info.gridworld.*;
import info.gridworld.grid.*;
import java.awt.Color;

public class Jumper extends Actor
{
	private Location newLoc;
	private Location oldLoc;
	public Jumper()
	{
		setColor(Color.ORANGE);
	}

	public Jumper(Color color)
	{
		setColor(color);
	}

	public void act()
	{
		if (canMove())
			move();
		else
			turn();
	}
	
	public void turn()
	{
		setDirection(getDirection() + Location.HALF_RIGHT);
	}
	public void move()
	{
		Grid<Actor> gr = getGrid();
		moveTo(newLoc);
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, oldLoc);
	}
	public boolean canMove()
	{
		Grid<Actor>	gr = getGrid();
		if (null == gr)
			return false;
		oldLoc = getLocation();
		int direction = getDirection();
		newLoc = oldLoc.getAddjacentLocation(direction);
		newLoc = newLoc.getAddjacentLocation(direction);

		if (!gr.isValid(newLoc))
			removeSelfFromGrid();
			return false;
		Actor neighbor = gr.get(newLoc);
		return (neighbor == null) || (neighbor instanceof Flower);
	}
}
