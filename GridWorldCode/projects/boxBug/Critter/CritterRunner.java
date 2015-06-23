import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;
public class CritterRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		Critter_ ct = new Critter_();
		ct.setColor(Color.ORANGE);
		world.add(new Rock());
		world.add(ct);
		world.show();
	}
}
