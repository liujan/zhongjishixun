import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import imagereader.*;

//该类对图片进行读写，并去除图片中的冗余信息
class MyImage implements IImageIO
{
	
	//读取指定位置上的图片，然后将其返回
	public Image myRead(String filePath)
	{
		Image image = null;
		File file = new File(filePath);
		try
		{
			FileInputStream in = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(in);
			byte[] buff = new byte[54];
			//读取图片基本数据
			dis.read(buff,0,54);
			//保存图片宽度
			int width = (((int)buff[21] & 0xff) << 24)
						| (((int)buff[20] & 0xff) << 16)
						| (((int)buff[19] & 0xff) << 8)
						| ((int)buff[18] & 0xff);
			//高度
			int height = (((int)buff[25] & 0xff) << 24)
						| (((int)buff[24] & 0xff) << 16)
						| (((int)buff[23] & 0xff) << 8)
						| ((int)buff[22] & 0xff);
			//图片大小（字节为单位）
			int bmpSize = (((int)buff[37] & 0xff) << 24)
						| (((int)buff[36] & 0xff) << 16)
						| (((int)buff[35] & 0xff) << 8)
						| ((int)buff[34] & 0xff);
			
			//每行后面补0的个数
			int numOfReBit = (bmpSize / height - width * 3) % 4;
			//读取图片原始数据
			byte[] data = new byte[bmpSize];
			dis.read(data, 0, bmpSize);
			int index = 0;
			//保存取出无用信息后的图片数据
			int[] newData = new int[width * height];
			//将图片中的无用信息去掉，并保存在int类型的数组中
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
				//越过补的0
				index += numOfReBit;
			}
			
			//生成新的图片
			image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height
	,				newData, 0, width));
			in.close();
			
			BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = buffImg.getGraphics();
			g.drawImage(image,0,0,null);
			return buffImg;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	//将指定图片写入到指定位置
	public Image myWrite(Image image, String filePath) 
	{
		try
		{
			File file = new File(filePath + ".bmp");		
			ImageIO.write((RenderedImage) image, "bmp", file);
			
			return image;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}