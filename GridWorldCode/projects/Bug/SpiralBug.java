package info.gridworld.actor;
import info.gridworld.actor.Bug;

public class SpiralBug extends Bug
{
	private int steps;
	private int sideLength;

	public SpiralBug(int length)
	{
		sideLength = length;
		steps = 0;
	}
	public void act()
	{
		if (steps <= sideLength && canMove())
		{
			move();
			steps++;
		}
		else 
		{
			turn();
			turn();
			sideLength ++;
			steps = 0;
		}
	
	}
}
