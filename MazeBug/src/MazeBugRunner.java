//package info.gridworld.maze;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
//import info.gridworld.maze.MazeBug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class MazeBugRunner
{
    public static void main(String[] args)
    {
        //UnboundedGrid ugr=new UnboundedGrid();
        ActorWorld world = new ActorWorld(); 
        /*world.add(new Location(-1,-1),new MazeBug());
        for(int i=0;i<=40;i++){
        	for(int j=0;j<=40;j+=40){
        		world.add(new Location(i,j),new Rock());
        	}        	
        }
        for(int i=0;i<=40;i+=40){
        	for(int j=0;j<=40;j++){
        		world.add(new Location(i,j),new Rock());
        	}        	
        }*/
        world.add(new Flower());
        world.add(new Location(6,4), new MazeBug());
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
        world.show();
    }
}