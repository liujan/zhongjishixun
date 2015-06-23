
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class NewCritterRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		world.add(new Rock());
		world.add(new Bug());
		world.add(new Flower());
		world.add(new NewCritter());
		world.show();
	}
}
