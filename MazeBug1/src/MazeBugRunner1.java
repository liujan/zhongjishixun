
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class MazeBugRunner1
{
    public static void main(String[] args)
    {

        ActorWorld world = new ActorWorld(); 
        // 在world中添加actors
        world.add(new Flower());
        world.add(new Location(4,4), new MazeBug1());
        world.add(new Location(5,3),new Rock());
        world.add(new Location(4,3),new Rock());
        world.add(new Location(3,3),new Rock());
        world.add(new Location(2,4),new Rock());
        world.add(new Location(6,5),new Rock());
        world.add(new Location(5,5),new Rock());
        world.add(new Location(5,6),new Rock());
        world.add(new Location(3,5),new Rock());
        world.add(new Location(3,6),new Rock());
        world.add(new Location(4,7),new Rock());
        world.add(new Location(7,4),new Rock());
        world.add(new Location(6,3),new Rock());
        //显示world
        world.show();
    }
}