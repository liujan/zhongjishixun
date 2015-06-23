import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class ZBugRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		ZBug zb = new ZBug(5);
		world.add(new Location(1,1), zb);
		world.show();
	}
}
