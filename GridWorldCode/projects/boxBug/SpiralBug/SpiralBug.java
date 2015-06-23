package info.gridworld.actor;
import info.gridworld.actor.Bug;

public class SpiralBug extends Bug
{
	//表示移动的步数
	private int steps;
	//表示移动的边长
	private int sideLength;

	public SpiralBug(int length)
	{
		//初始化计数
		sideLength = length;
		steps = 0;
	}

	public void act()
	{
		//如果还在当前边上并且可以移动，就移动
		if (steps <= sideLength && canMove())
		{
			move();
			steps++;
		}
		//否则向右转90度，边长加1,再继续移动
		else 
		{
			turn();
			turn();
			sideLength ++;
			steps = 0;
		}
	
	}
}
