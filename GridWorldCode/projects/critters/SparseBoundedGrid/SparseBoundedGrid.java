import java.util.LinkedList;
import java.util.*;
import info.gridworld.actor.*;
import info.gridworld.grid.*;
/*
 *本类用链表数组来表示grid上的格子
 *
 *
 * */
public  class SparseBoundedGrid<E> extends AbstractGrid<E>
{
	//链表数组模拟grid
	private LinkedList<OccupantInCol>[] occupantArray;
	//grid的列数
	private int cols;
	//grid的行数
	private int rows;
	public SparseBoundedGrid(int rows, int cols)
	{
		if (rows <= 0)
		{
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0)
		{
			throw new IllegalArgumentException("cols <= 0");
		}
		//初始化
		this.rows = rows;
		this.cols = cols;
		occupantArray = new LinkedList[rows];
		for (int i = 0; i < rows; i++)
		{
			occupantArray[i] = new LinkedList<OccupantInCol>();
		}
	}
	//返回行数rows
	public int getNumRows()
	{
		return rows;
	}
	//返回列数cols
	public int getNumCols()
	{
		return cols;
	}
	//判断一个位置是否有效；当其在grid内部时有效，否则无效
	public boolean isValid(Location loc)
	{
		int row = loc.getRow();
		int col = loc.getCol();
		return (row >= 0 && col >= 0 && row < rows && col < cols);
	}
	//将一个新元素放入指定位置，并返回该位置上的旧元素
	public E put(Location loc, E obj)
	{
		if (!isValid(loc))
		{
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		}
		if (null == obj)
		{
			throw new NullPointerException("obj == null");
		}
	
		int row = loc.getRow();
		int col = loc.getCol();
		//如果该行没有元素，则将新元素直接插入，并返回空
		if (occupantArray[row].size() == 0)
		{
			occupantArray[row].add(new OccupantInCol(obj, col));
			return null;
		}
		OccupantInCol old = null;
		//查找新元素的位置和旧元素
		for (int i = 0; i < occupantArray[row].size(); i++)
		{
			if (occupantArray[row].get(i).getCol() == col)
			{
				old = occupantArray[row].get(i);
				occupantArray[row].get(i).setOccupant(obj);
				break;
			}
		}
		if (null == old)
		{
			occupantArray[row].add(new OccupantInCol(obj, col));
			return null;
		}
		else
		{
			return (E)old.getOccupant();
		}

	}
	//将指定位置上的元素删除，并将其返回
	public E remove(Location loc)
	{
		if (!isValid(loc))
		{
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		}
		int row = loc.getRow();
		int col = loc.getCol();

		OccupantInCol tmp = null;

		//查找指定位置上的元素
		for (int i = 0; i < occupantArray[row].size(); i++)
		{
			tmp = occupantArray[row].get(i);
			if (tmp.getCol() == col)
			{
				occupantArray[row].remove(i);
				return (E)tmp.getOccupant();
			}
		}
		return null;
	}
	//将指定位置上的元素返回；若该位置上没有元素，则返回空
	public E get(Location loc)
	{
		if (!isValid(loc))
		{
			throw new IllegalArgumentException("Location " + loc + " is valid");
		}
		int row = loc.getRow();
		int col = loc.getCol();
		//查找该位置上的元素
		for (int i = 0; i < occupantArray[row].size(); i++)
		{
			OccupantInCol tmp = occupantArray[row].get(i);
			if (tmp.getCol() == col)
			{
				return (E)tmp.getOccupant();
			}
		}
		return null;
	}
	//将grid上所有被占据的位置返回
	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Location loc = new Location(r,c);
				//判断该位置是否被占据
				if (get(loc) != null)
				{
					locs.add(loc);
				}
			}
		}
		return locs;
	}

}



class OccupantInCol
{
	//the instance
	private Object occupant;
	//the index
	private int col;
	public OccupantInCol(Object occupant, int c)
	{
		this.occupant = occupant;
		this.col = c;
	}
	public OccupantInCol()
	{
		occupant = null;
		col = 0;
	}
	//get the instance
	public Object getOccupant()
	{
		return occupant;
	}
	//set the insance 
	public void setOccupant(Object occ)
	{
		occupant = occ;
	}
	//get the index
	public int getCol()
	{
		return col;
	}
	public void setCol(int col)
	{
		this.col = col;
	}
}
