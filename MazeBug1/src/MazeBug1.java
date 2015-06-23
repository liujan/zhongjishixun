

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug1 extends Bug {
	//标记将要走到的位置
	private Location next;
	//标记上一个位置
	private Location last;
	//标记是否到达终点了
	private boolean isEnd = false;
	//记录分叉路位置和转弯位置
	private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	//计算所走的步数
	private Integer stepCount = 0;
	//当到达终点时，标记是否已经显示相关信息
	private boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug1() {
		setColor(Color.ORANGE);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		//判断是否到达终点
		if (isEnd) {
		//to show step count when reach the goal		
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			//前面，左边和右边三个方向中还有空位置可以走
			//将当前方向标志为last，在移动到下一方向next		
			last = getLocation();
			//移动到新位置next
			move();
			//increase step count when move 
		}
		else
		{			
			//如果前方，左边和右边都可以空位可走了，就要往回走
			if (!crossLocation.isEmpty())
			{
				//如果此时还有交叉点或转弯点，就要判断是否到达该位置，以便转弯
				//取出最近走过的一个交叉或转弯位置
				ArrayList<Location> turnPoint = crossLocation.peek();
				Location turnLast = turnPoint.get(0);
				Location turnLoc = turnPoint.get(1);
				//如果走到了该交叉或转弯点，则转弯
				if (getLocation().getRow() == turnLoc.getRow() && getLocation().getCol() == turnLoc.getCol())
				{
					//转弯
					next = turnLast;
					//移除该点
					crossLocation.pop();
					move();
					last = getLocation().getAdjacentLocation(getDirection());
				}
				else
				{
					//如果没有到达交叉或转弯点，则继续沿直线往回走
					next = last;
					if (getGrid().isValid(next) && !(getGrid().get(next) instanceof Rock))
					{
						move();
					}
					last = getLocation().getAdjacentLocation(getDirection());
				}				
				//stepCount++;
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
	
	//在前方，左边和右边中的空位置中随机选出一个，作为下个位置的值
	public Location selectMoveLocation(ArrayList<Location> locs)
	{
		int n = locs.size();
		int r = (int)(Math.random() * n);
		Location l = locs.get(r);
		Location currentLoc = getLocation();
		
		//如果当前位置为转弯点或分叉点，则需将该点，前一位置和下一位置加入到crossLocation中
		if (!crossLocation.isEmpty())
		{
			Location p = crossLocation.peek().get(1);
			if ((p.getRow() != currentLoc.getRow() || p.getCol() != currentLoc.getCol())
					&& (locs.size() > 1 || (locs.size() == 1 && (currentLoc.getDirectionToward(l)- getDirection()) != 0)))
			{
				ArrayList<Location> turnPoint = new ArrayList<Location>();
				turnPoint.add(last);
				turnPoint.add(getLocation());
				turnPoint.add(l);
				crossLocation.push(turnPoint);;
			}
		}
		else
		{
			if ((locs.size() > 1) || (locs.size() == 1 && (currentLoc.getDirectionToward(l)- getDirection()) != 0))
			{
				ArrayList<Location> turnPoint = new ArrayList<Location>();
				turnPoint.add(last);
				turnPoint.add(getLocation());
				turnPoint.add(l);
				crossLocation.push(turnPoint);
			}
		}
		
		return l;	
	}
	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	//返回前方，左边和右边位置中空的位置
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
		{
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		//左边位置
		Location leftLoc = loc.getAdjacentLocation((getDirection() + 270) % 360);
		//右边位置
		Location rightLoc = loc.getAdjacentLocation((getDirection() + 90) % 360);
		//前方位置
		Location aheadLoc = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(leftLoc) && gr.get(leftLoc) == null)
		{
			valid.add(leftLoc);
		}
		if (gr.isValid(rightLoc) && gr.get(rightLoc) == null)
		{
			valid.add(rightLoc);
		}
		if (gr.isValid(aheadLoc) && gr.get(aheadLoc) == null)
		{
			valid.add(aheadLoc);
		}
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
		{
			return false;
		}
		Location loc = getLocation();		
		Location aheadLoc = loc.getAdjacentLocation(getDirection());			
		Location leftLoc = loc.getAdjacentLocation((getDirection() + 270) % 360);
		Location rightLoc = loc.getAdjacentLocation((getDirection() + 90) % 360);
		Location behindLoc = loc.getAdjacentLocation((getDirection() + 180) % 360);
		//判断终点是否在四周
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
		else if (grid.isValid(behindLoc) && grid.get(behindLoc) instanceof Rock && grid.get(behindLoc).getColor().getRGB() == Color.RED.getRGB())
		{
			isEnd = true;
		}
		//得到可移动的位置数组
		ArrayList<Location> locs = getValid(getLocation());
		if (!locs.isEmpty())
		{
			//得到将要移动到的位置
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
		{
			return;
		}
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			//步数加1
			stepCount ++;
			moveTo(next);
		} else
		{
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}