import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class QuickCrabRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		world.add(new QuickCrab());
		world.add(new Flower());
		world.add(new Rock());

		world.show();
	}
}
