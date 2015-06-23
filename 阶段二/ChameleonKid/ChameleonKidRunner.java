import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class ChameleonKidRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		world.add(new ChameleonKid());
		world.add(new Flower());
		world.add(new Rock());

		world.show();
	}
}
