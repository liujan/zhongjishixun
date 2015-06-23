import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class CrabCritterRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		world.add(new Flower());
		world.add(new CrabCritter());
		world.add(new Rock());
		world.show();
	}
}
