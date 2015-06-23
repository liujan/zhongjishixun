//package info.gridworld.actor;
import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
	//表示移动的长度
	private int length; 
	//表示移动的步伐
	private int steps;
        //表示移动到哪一边
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
		//移动到第一边
		if (action == 0)
		{
 			//如果还在第一边，继续移动
			if (steps <= length && canMove())
			{
				move();
				steps ++;
			}
                        //否则就转向到第二边
			else 
			{
				//转向45 × 3
				turn();
				turn();
				turn();
				action ++;
				//重新基数
				steps = 0;

			}

		}
                  //移动到第二边
		else if (1 == action)
		{
			//如果还在第二边，就继续移动
			if (steps <= length && canMove())
			{
				move();
				steps ++;
			}
			//否则移动到第三边
			else 
			{
				//向右转45 × 5
				turnZ(5);
				action ++;
				//重新计数
				steps = 0;
			}
		}
		//如果在第三边，并且还没结束，就继续在第三边上移动
		//否则就停止移动
		else if (2 == action && steps <= length && canMove())
		{
			move();
			steps ++;
		}
	}
//转向函数
    private void turnZ(int t)
	{
		for (int i = 0; i < t; i++)
		{
			turn();
		}
	}
}
