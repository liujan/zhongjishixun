import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class RockHoundRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		
		world.add(new RockHound());
		world.add(new Rock());
		world.add(new Rock());
		world.add(new Flower());
		world.show();
	}
}
