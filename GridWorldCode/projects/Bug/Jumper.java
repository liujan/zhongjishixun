package info.gridworld.actor;

//import info.gridworld.*;
import info.gridworld.grid.*;
import java.awt.Color;
import info.gridworld.actor.*;

public class Jumper extends Bug
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
	//@Override
	public void act()
	{
		if (canMove())
			move();
		else
			turn();
	}
	//@Override
	public void turn()
	{
		setDirection(getDirection() + Location.HALF_RIGHT);
	}
	//@Override
	public void move()
	{
		Grid<Actor> gr = getGrid();
		moveTo(newLoc);
	}
	//@Override
	public boolean canMove()
	{
		Grid<Actor>	gr = getGrid();
		if (null == gr)
			return false;
		oldLoc = getLocation();
		int direction = getDirection();
		newLoc = oldLoc.getAdjacentLocation(direction);
		newLoc = newLoc.getAdjacentLocation(direction);

		if (!gr.isValid(newLoc))
			return false;
		Actor neighbor = gr.get(newLoc);
		return (neighbor == null) || (neighbor instanceof Flower);
	}
}
