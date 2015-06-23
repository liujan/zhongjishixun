import java.util.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class UnboundedGrid2<E> extends AbstractGrid<E>
{
	private int cols;
	private int rows;
	private Object[][] occupantArray;
	public UnboundedGrid2()
	{
		cols = 16;
		rows = 16;
		occupantArray = new Object[rows][cols];
	}

	public int getNumRows()
	{
		return -1;
	}
	public int getNumCols()
	{
		return -1;
	}
	public boolean isValid(Location loc)
	{
		return loc.getRow() >= 0 && loc.getCol() >= 0;
	}
	public E get(Location loc)
	{
		if (null == loc)
		{
			throw new NullPointerException("loc == null");
		}
		return (E)occupantArray[loc.getRow()][loc.getCol()];
	}

	public E put(Location loc, E obj)
	{
		if (null == loc)
		{
			throw new NullPointerException("loc == null");
		}
		if (null == obj)
		{
			throw new NullPointerException("obj == null");
		}
		if (isValid(loc))
		{
			int row = loc.getRow();
			int col = loc.getCol();
			if (row >= (rows-1) || col >= (cols-1))
			{
				expandArray();
			}
			Object oldObj = occupantArray[row][col];
			occupantArray[row][col] = obj;
			return (E)oldObj;
		}
		return null;
	}
	
	public void expandArray()
	{
		int newRows = rows * 2;
		int newCols = cols * 2;
		Object[][] tmp = new Object[newRows][newCols];
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				tmp[i][j] = occupantArray[i][j];
			}
		}
		occupantArray = new Object[newRows][newCols];
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				occupantArray[i][j] = tmp[i][j];
			}
		}
		rows = newRows;
		cols = newCols;
	}

	public E remove(Location loc)
	{
		if (!isValid(loc))
		{
			return null;
		}
		if (loc.getRow() >= rows || loc.getCol() >= cols)
		{
			return null;
		}
		Object obj = occupantArray[loc.getRow()][loc.getCol()];
		occupantArray[loc.getRow()][loc.getCol()] = null;
		return (E)obj;
	}

	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				Location loc = new Location(i, j);
				if (get(loc) != null)
				{
					locs.add(loc);
				}
			}
		}
		return locs;
	}
}
