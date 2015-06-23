import java.util.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;
/*
 *这个类用数组了表示无边界的grid，但每个位置上的row和col都
 *必须为非负
 *
 * */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
	//储存列数
	private int cols;
	//储存行数
	private int rows;
	//用数组表示grid
	private Object[][] occupantArray;
	public UnboundedGrid2()
	{
		//初始化为一个16 × 16 的方格
		cols = 16;
		rows = 16;
		occupantArray = new Object[rows][cols];
	}
	//返回行数
	public int getNumRows()
	{
		return -1;
	}
	//返回列数
	public int getNumCols()
	{
		return -1;
	}
	//判断某个位置是否有效
	public boolean isValid(Location loc)
	{
		return loc.getRow() >= 0 && loc.getCol() >= 0;
	}
	//返回loc位置上的元素
	public E get(Location loc)
	{
		if (null == loc)
		{
			throw new NullPointerException("loc == null");
		}
		if ((loc.getRow() < 0 || loc.getRow() >= rows) || (loc.getCol() < 0 || loc.getCol() >= cols))
		{
			return null;
		}
		return (E)occupantArray[loc.getRow()][loc.getCol()];
	}
	//将新元素插入到指定位置，并返回该位置上的旧元素
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
			while(true)
			{
				if (row >= (rows-1) || col >= (cols-1))
				{
				//如果指定位置在当前数组范围之外，则将数组的列数和行数都扩展为两倍
					expandArray();
				}
				else
				{
					break;
				}
			}
			Object oldObj = occupantArray[row][col];
			//插入新元素
			occupantArray[row][col] = obj;
			return (E)oldObj;
		}
		return null;
	}
	//将数组occupantArray的行数和列数都扩展为原来的两倍
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
	//移除指定位置上的元素，并将其返回
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
	//返回grid上所有被占据的格子的位置
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
