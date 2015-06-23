package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

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

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
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
			move();
			//increase step count when move 
			stepCount++;
		}
		else
		{
			ArrayList<Location> valid = getValid(getLocation());
			int n = valid.size();
			if (n > 0)
			{
				int r = (int)(Math.random() * n);
				next = valid.get(r);
				
				ArrayList<Location> turnPoint = new ArrayList<Location>();
				turnPoint.add(last);
				turnPoint.add(getLocation());
				turnPoint.add(next);
				crossLocation.push(turnPoint);
				
				last = getLocation();
				move();
				stepCount++;
			}
			else
			{
				ArrayList<Location> turnPoint = crossLocation.peek();
				Location turnLast = turnPoint.get(0);
				Location turnLoc = turnPoint.get(1);
				if (getLocation().getRow() == turnLoc.getRow() && getLocation().getCol() == turnLoc.getCol())
				{
					next = turnLast;
					crossLocation.pop();
					move();
					last = getLocation().getAdjacentLocation(getDirection());
				}
				//else if (getLocation().getRow() == 1 && getLocation().getCol() == 0 && crossLocation.empty())
				//{
	
				//}
				else
				{
					next = last;
					move();
					last = getLocation().getAdjacentLocation(getDirection());
				}
				stepCount++;
			}
		}
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
		if (gr.isValid(leftLoc) && gr.get(leftLoc) == null)
			valid.add(leftLoc);
		if (gr.isValid(rightLoc) && gr.get(rightLoc) == null)
			valid.add(rightLoc);
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
		next = loc.getAdjacentLocation(getDirection());
		if (!grid.isValid(next))
		{
			return false;
		}
		Actor neighbor = grid.get(next);
		return neighbor == null;
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