package info.gridworld.actor;
import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
	private int length;
	private int steps;
	private int action;

	public ZBug(int length)
	{
		this.length = length;
		steps = 0;
		action = 0;
		turn();
		turn();
	}
	public void act()
	{
		if (action == 0)
		{
			if (steps <= length && canMove())
			{
				move();
				steps ++;
			}
			else 
			{
				turn();
				turn();
				turn();
				action ++;
				steps = 0;

			}

		}
		else if (1 == action)
		{
			if (steps <= length && canMove())
			{
				move();
				steps ++;
			}
			else 
			{
				turnZ(5);
				action ++;
				steps = 0;
			}
		}
		else if (2 == action && steps <= length && canMove())
		{
			move();
			steps ++;
		}
	}
    private void turnZ(int t)
	{
		for (int i = 0; i < t; i++)
		{
			turn();
		}
	}
}
