import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.ActorWorld;

public class JumperRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		world.add(new Jumper());
		world.add(new Rock());
		world.show();
	}
}
