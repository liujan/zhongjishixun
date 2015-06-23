import java.awt.ToolKit;
import java.awt.*;
import java.io.*;
import awt.image.*;
import javax.imageio.ImageIO;

class MyImageIo implements IImageIo
{
	public Image myRead(String filePath)
	{
		Image image = null;
		File file = new File(filePath);
		try
		{
			FileInputStream in = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(in);
			byte[] buff = new byte[1000000];
			dis.read(buff,0,1000000);
			int width = (((int)buff[21] & 0xff) << 24)
						| (((int)buff[20] & 0xff) << 16)
						| (((int)buff[19] & 0xff) << 8)
						| ((int)buff[18] & 0xff);
			int height = (((int)buff[25] & 0xff) << 24)
						| (((int)buff[24] & 0xff) << 16)
						| (((int)buff[23] & 0xff) << 8)
						| ((int)buff[22] & 0xff);
			image = Toolkit.getDefaultToolkit().createImage(width, height, buff, 0, width);
			return image;
		}
		catch(Exception e)
		{
		}
	}

	public Image myWrite(Image image, String filePath)
	{
		try
		{
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = bufferimage.getGraphics();
			g.drawImage(image,0,0,null);
			ImageIO.write(bufferimage,"bmp",new File(filePath));
			return image;
		}
	
		catch(Exception e)
		{
		
		}
	}
	
}
