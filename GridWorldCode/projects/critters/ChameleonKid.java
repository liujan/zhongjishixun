import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter
{
	public ArrayList<Actor> getActors()
	{
		//store the actor ahead and behind
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Location loc = getLocation();
		//get the locaiotn ahead
		Location aheadLoc = loc.getAdjacentLocation(getDirection() + Location.AHEAD);
		//get the location behind
		Location behindLoc = loc.getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);
		Grid<Actor> gr = getGrid();
		//actor ahead
		Actor aheadActor = null;
		//actor behind
		Actor behindActor = null;
		//get the actor ahead
		if (gr.isValid(aheadLoc))
		{
			aheadActor = gr.get(aheadLoc);
		}
		//get the actor behind
		if (gr.isValid(behindLoc))
		{
			behindActor= gr.get(behindLoc);
		}
		if (null != aheadActor)
		{
			actors.add(aheadActor);
		}
		if (null != behindActor)
		{
			actors.add(behindActor);
		}

		return actors;
	}
	
}
