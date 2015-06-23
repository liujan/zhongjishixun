import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class ImageTest {
	private String filePath = "/home/hadoop/bmptest/";
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRed() {	
		//检测红色是否相同
		//我处理后的图片
		int[] myData = readImage(filePath + "red.bmp");
		//TA给出的答案图片
		int[] originalData = readImage(filePath + "goal/1_red_goal.bmp");
		
		boolean result = Compare(myData, originalData);
		assertEquals(true, result);
	}
	@Test
	public void testGreen() {
		//检测绿色是否相同
		//我处理的图片
		int[] myData = readImage(filePath + "green.bmp");
		//TA给出的答案图片
		int[] originalData = readImage(filePath + "goal/1_green_goal.bmp");
		
		boolean result = Compare(myData, originalData);
		assertEquals(true, result);
	}
	@Test
	public void testBlue() {
		//检测蓝色是否相同
		//我处理的图片
		int[] myData = readImage(filePath + "blue.bmp");
		//TA给出的答案图片
		int[] originalData = readImage(filePath + "goal/1_blue_goal.bmp");
		
		boolean result = Compare(myData, originalData);
		assertEquals(true, result);
	}
	@Test
	public void testGray() {
		//检测灰度是否相同
		//我处理的图片
		int[] myData = readImage(filePath + "gray.bmp");
		//TA给出的答案图片
		int[] originalData = readImage(filePath + "goal/1_gray_goal.bmp");
		
		boolean result = Compare(myData, originalData);
		assertEquals(true,result);
	}
	@Test
	public void testRedWidth() throws Exception
	{
		File myFile = new File(filePath + "red.bmp");
		File goalFile = new File(filePath + "goal/1_red_goal.bmp");
		BufferedImage myImage = ImageIO.read(myFile);
		BufferedImage goalImage = ImageIO.read(goalFile);
		
		assertEquals(myImage.getWidth(), goalImage.getWidth());
	}
	@Test
	public void testRedHeight() throws Exception
	{
		File myFile = new File(filePath + "red.bmp");
		File goalFile = new File(filePath + "goal/1_red_goal.bmp");
		BufferedImage myImage = ImageIO.read(myFile);
		BufferedImage goalImage = ImageIO.read(goalFile);
		
		assertEquals(myImage.getHeight(), goalImage.getHeight());
	}
	@Test
	public void testGreenWidth() throws Exception
	{
		File myFile = new File(filePath + "green.bmp");
		File goalFile = new File(filePath + "goal/1_green_goal.bmp");
		BufferedImage myImage = ImageIO.read(myFile);
		BufferedImage goalImage = ImageIO.read(goalFile);
		
		assertEquals(myImage.getWidth(), goalImage.getWidth());
	}
	@Test
	public void testGreenHeight() throws Exception
	{
		File myFile = new File(filePath + "green.bmp");
		File goalFile = new File(filePath + "goal/1_green_goal.bmp");
		BufferedImage myImage = ImageIO.read(myFile);
		BufferedImage goalImage = ImageIO.read(goalFile);
		
		assertEquals(myImage.getHeight(), goalImage.getHeight());
	}
	@Test
	public void testBlueWidth() throws Exception
	{
		File myFile = new File(filePath + "blue.bmp");
		File goalFile = new File(filePath + "goal/1_blue_goal.bmp");
		BufferedImage myImage = ImageIO.read(myFile);
		BufferedImage goalImage = ImageIO.read(goalFile);
		
		assertEquals(myImage.getWidth(), goalImage.getWidth());
	}
	@Test
	public void testBlueHeight() throws Exception
	{
		File myFile = new File(filePath + "blue.bmp");
		File goalFile = new File(filePath + "goal/1_blue_goal.bmp");
		BufferedImage myImage = ImageIO.read(myFile);
		BufferedImage goalImage = ImageIO.read(goalFile);
		
		assertEquals(myImage.getHeight(), goalImage.getHeight());
	}
	@Test
	public void testGrayWidth() throws Exception
	{
		File myFile = new File(filePath + "gray.bmp");
		File goalFile = new File(filePath + "goal/1_gray_goal.bmp");
		BufferedImage myImage = ImageIO.read(myFile);
		BufferedImage goalImage = ImageIO.read(goalFile);
		
		assertEquals(myImage.getWidth(), goalImage.getWidth());
	}
	@Test
	public void testGrayHeight() throws Exception
	{
		File myFile = new File(filePath + "gray.bmp");
		File goalFile = new File(filePath + "goal/1_gray_goal.bmp");
		BufferedImage myImage = ImageIO.read(myFile);
		BufferedImage goalImage = ImageIO.read(goalFile);
		
		assertEquals(myImage.getHeight(), goalImage.getHeight());
	}
	//读取图片，并将其数据放到int数组中
	private int[] readImage(String filePath)
	{
		File file = new File(filePath);
		try
		{
			FileInputStream in = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(in);
			//保存图片宽度
			int width = 400;
			int height = 400;
			int bmpSize = width * height * 3;
			//读取图片原始数据
			byte[] data = new byte[bmpSize];
			dis.read(data, 0, bmpSize);
			
			int index = 0;
			int[] newData = new int[width * height];
			//将byte数组转化成int数组
			for (int i = 0; i < height; i++)
			{
				for (int j = 0; j < width; j++)
				{
					newData[(height-i-1) * width + j] = 
							((255 & 0xff) << 24)
							| (((int)data[index + 2] & 0xff) << 16)
							| (((int)data[index + 1] & 0xff) << 8)
							| (((int)data[index] & 0xff));
					index += 3;
				}
			}
			dis.close();
			return newData;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	private boolean Compare(int[] my, int []original)
	{
		int length = my.length;
		int length1 = original.length;
		if (length != length1)
		{
			return false;
		}
		for (int i = 0; i < length; i++)
		{
			if (my[i] != original[i])
			{
				return false;
			}
		}
		return true;
	}
}
