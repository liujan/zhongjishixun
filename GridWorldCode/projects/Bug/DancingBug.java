package info.gridworld.actor;
import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
	private int[] arr;
	private int count;
	private int length;
	public DancingBug(int[] arr)
    {
		count = 0;
		length = arr.length;
		this.arr = new int[length];
		for (int i = 0; i < length; i++)
		{
			this.arr[i] = arr[i];
		}
	}

	public void act()
	{
		for (int i = 0; i < arr[count]; i++)
		{
				turn();
		}
		count = (++count) % length;
		if (canMove())
		{
			move();
		}
		else
		{
			turn();
		}
	}
}
