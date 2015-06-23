import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class BlusterCritterRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();

		world.add(new BlusterCritter(2));
		world.add(new Flower());
		world.add(new Rock());
		world.show();
	}
}
