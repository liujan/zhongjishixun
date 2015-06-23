import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class KingCrabRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();

		world.add(new Location(4,4),new KingCrab());
		world.add(new Location(2,2),new Rock());

		world.add(new Location(2,3),new Rock());
		world.add(new Location(3,2),new Rock());
		world.add(new Location(4,2),new Rock());
		world.add(new Location(2,4),new Rock());
		world.add(new Location(3,3),new Flower());
		world.show();
	}
}
