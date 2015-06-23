
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import imagereader.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.awt.Color;

//该类可以对图片进行红化，绿化，蓝化和灰度处理
public class MyImageProcessor  implements IImageProcessor {

	//过滤掉红色和绿色通道，将图片保存为只有蓝色的image,并返回
	public Image showChanelB(Image arg0) {
		int[][] rgb = getRgb(arg0);
		int width = arg0.getWidth(null);
		int height = arg0.getHeight(null);
		
		BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferImage.getGraphics();
		g.drawImage(arg0,0,0,null);
		//将每个色素点上的红色和绿色去除
		for (int i = 0; i <  height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				//设置新的RGB
				Color color = new Color(0, 0, rgb[i][j] & 0x000000ff);
				bufferImage.setRGB(i, j, color.getRGB());
			}
		}
		return bufferImage;
	}

	//过滤掉蓝色和红色通道，将图片保存为只有绿色的image,并返回
	public Image showChanelG(Image arg0) {
		//绿色过滤器
		GreenFilter greenFilter = new GreenFilter();
		//过滤掉原图片的红色和蓝色，并生成新图片
		Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(arg0.getSource(), greenFilter));
		//宽度
		int width = image.getWidth(null);
		//高度
		int height = image.getHeight(null);
		//生成Bufferedimage,主要是为了后面返回
		BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		//将目标image写入到bufferimage
		g.drawImage(image,0,0,null);
		return buffImg;
	}

	//过滤掉蓝色和绿色通道，将图片保存为只有红色的image,并返回
	public Image showChanelR(Image arg0) {
		int[][] rgb = getRgb(arg0);
		int width = arg0.getWidth(null);
		int height = arg0.getHeight(null);
		
		BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferImage.getGraphics();
		g.drawImage(arg0,0,0,null);
		//除去蓝色和绿色
		for (int i = 0; i <  height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				//设置新的RGB,只剩下蓝色和绿色
				Color color = new Color(rgb[i][j] & 0x00ff0000 >> 16, 0, 0);
				bufferImage.setRGB(i, j, color.getRGB());
			}
		}
		return bufferImage;
	}

	@Override
	public Image showGray(Image arg0) {
		//灰色过滤器
		GrayFilter grayFilter = new GrayFilter();
		//将红绿蓝三种颜色揉合，生成灰色图片
		Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(arg0.getSource(), grayFilter));
		//宽度
		int width = image.getWidth(null);
		//高度
		int height = image.getHeight(null);
		//生成Bufferedimage,主要是为了后面返回
		BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		//将目标image写入到bufferimage
		g.drawImage(image,0,0,null);
		return buffImg;
	}
	//红色过滤器
	class RedFilter extends RGBImageFilter
	{
		public RedFilter()
		{
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int x, int y, int rgb) {
			//过滤掉蓝色和绿色
			return rgb & 0xffff0000;
		}
	}
	//绿色过滤器
	class GreenFilter extends RGBImageFilter
	{
		public GreenFilter()
		{
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int x, int y, int rgb) {
			//过滤掉红色和蓝色
			return rgb & 0xff00ff00;
		}
	}
	//蓝色过滤器
	class BlueFilter extends RGBImageFilter
	{
		public BlueFilter()
		{
			canFilterIndexColorModel = true;
		}
		public int filterRGB(int x, int y, int rgb) {
			//过滤掉红色和绿色
			return  rgb & 0xff0000ff;
		}
	}
	
	//灰色生成器
	class GrayFilter extends RGBImageFilter
	{
		public GrayFilter()
		{
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int x, int y, int rgb) {
			//I = 0.299 * R + 0.587 * G + 0.114 * B，其中R,G,B分别为红、绿、蓝通道的颜色值。
			//将红绿蓝三色通过不同权值揉合到一起
			int gray = (int)(((rgb & 0x00ff0000)>>16)*0.3 + ((rgb & 0x0000ff00)>>8)*0.59 + (rgb & 0x000000ff)*0.11);  
            return (rgb & 0xff000000)+(gray<<16)+(gray<<8)+gray;  
		}
		
	}
	//返回图片各个像素点的RGB
	public int[][] getRgb(Image image)
	{
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage bufferImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferImage.getGraphics();
		g.drawImage(image,0,0,null);

		int[][] rgb = new int[height][width];
		
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				rgb[i][j] = bufferImage.getRGB(i, j);
			}
		}		
		return rgb;
	}
}
