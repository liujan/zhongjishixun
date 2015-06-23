

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	int left, right, up, down;
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		left = 1;
		right = 1;
		up = 1;
		down = 1;
		setColor(Color.ORANGE);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		

		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			last = getLocation();
			int direction = getLocation().getDirectionToward(next);
			switch(direction)
			{
			case 0:
				up ++;
				break;
			case 90:
				right ++;
				break;
			case 180:
				down ++;
				break;
			case 270:
				left ++;
				break;
			}
			last = getLocation();
			move();
			//increase step count when move 
			stepCount++;
		}
		else
		{			
			if (!crossLocation.isEmpty())
			{
				ArrayList<Location> turnPoint = crossLocation.peek();
				Location turnLast = turnPoint.get(0);
				Location turnLoc = turnPoint.get(1);
				if (getLocation().getRow() == turnLoc.getRow() && getLocation().getCol() == turnLoc.getCol())
				{
					next = turnLast;
					crossLocation.pop();
					move();;
					last = getLocation().getAdjacentLocation(getDirection());
				}
				else
				{
					next = last;
					if (getGrid().isValid(next) && !(getGrid().get(next) instanceof Rock))
					{
						move();
					}
					last = getLocation().getAdjacentLocation(getDirection());
				}

				switch(getDirection())
				{
				case 0:
					up --;
					break;
				case 90:
					right --;
					break;
				case 180:
					down --;
					break;
				case 270:
					left --;
					break;
				}
				stepCount++;
			}
			else
			{
				last = getLocation();
				next = last.getAdjacentLocation(getDirection());
				if (getGrid().isValid(next) && getGrid().get(next) instanceof Rock)
				{
					next = last.getAdjacentLocation(getDirection() + 180);
				}
				if (getGrid().isValid(next) && !(getGrid().get(next) instanceof Rock))
				{
					move();
					stepCount ++;
				}			
			}
			
		}
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs)
	{
		TreeMap<Integer,Location> t = new TreeMap<Integer,Location>();
		Location currentLoc = getLocation();
		
		for (int i = 0; i < locs.size(); i++)
		{
			Location loc = locs.get(i);
			int tmp = currentLoc.getDirectionToward(loc);
			if (tmp == 0)
			{
				t.put(up, loc);
			}
			else if (tmp == 90)
			{
				t.put(right, loc);
			}
			else if (tmp == 180)
			{
				t.put(down, loc);
			}
			else if (tmp == 270)
			{
				t.put(left, loc);
			}
		}
		int key = t.lastKey();
		switch(key)
		{
		case 0:
			up ++;
			break;
		case 90:
			right ++;
			break;
		case 180:
			down ++;
			break;
		case 270:
			left ++;
			break;
		}
		Location l = t.get(t.lastKey());

		if (!crossLocation.isEmpty())
		{
			Location p = crossLocation.peek().get(1);
			if ((p.getRow() != currentLoc.getRow() || p.getCol() != currentLoc.getCol())
					&& (t.size() > 1 || (t.size() == 1 && (currentLoc.getDirectionToward(l)- getDirection()) != 0)))
			{
				ArrayList<Location> turnPoint = new ArrayList<Location>();
				turnPoint.add(last);
				turnPoint.add(getLocation());
				turnPoint.add(l);
				crossLocation.push(turnPoint);
			}
		}
		else
		{
			if (t.size() > 1 || (t.size() == 1 && (currentLoc.getDirectionToward(l)- getDirection()) != 0))
			{
				ArrayList<Location> turnPoint = new ArrayList<Location>();
				turnPoint.add(last);
				turnPoint.add(getLocation());
				turnPoint.add(l);
				crossLocation.push(turnPoint);
			}
		}
		
		return t.get(t.lastKey());	
	}
	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();

		Location leftLoc = loc.getAdjacentLocation((getDirection() + 270) % 360);
		Location rightLoc = loc.getAdjacentLocation((getDirection() + 90) % 360);
		Location aheadLoc = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(leftLoc) && gr.get(leftLoc) == null)
			valid.add(leftLoc);
		if (gr.isValid(rightLoc) && gr.get(rightLoc) == null)
			valid.add(rightLoc);
		if (gr.isValid(aheadLoc) && gr.get(aheadLoc) == null)
			valid.add(aheadLoc);
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Grid<Actor> grid = getGrid();
		if (null == grid)
			return false;
		Location loc = getLocation();		
		Location aheadLoc = loc.getAdjacentLocation(getDirection());			
		Location leftLoc = loc.getAdjacentLocation((getDirection() + 270) % 360);
		Location rightLoc = loc.getAdjacentLocation((getDirection() + 90) % 360);
		
		if (grid.isValid(aheadLoc) && grid.get(aheadLoc) instanceof Rock && grid.get(aheadLoc).getColor().getRGB() == Color.RED.getRGB())
		{
			isEnd = true;
		}
		else if (grid.isValid(leftLoc) && grid.get(leftLoc) instanceof Rock && grid.get(leftLoc).getColor().getRGB() == Color.RED.getRGB())
		{
			isEnd = true;
		}
		else if (grid.isValid(rightLoc) && grid.get(rightLoc) instanceof Rock && grid.get(rightLoc).getColor().getRGB() == Color.RED.getRGB())
		{
			isEnd = true;
		}
		ArrayList<Location> locs = getValid(getLocation());
		if (!locs.isEmpty())
		{
			next = selectMoveLocation(locs);
		}
		return !locs.isEmpty();
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
		{
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}