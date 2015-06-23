
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageRunner {
	public static void main(String[] args) throws IOException
	{
		//图片路径
		String filePath = "/home/hadoop/bmptest/";
		MyImage myImage = new MyImage();
		MyImageProcessor processor = new MyImageProcessor();
		//读取图片
		BufferedImage image = (BufferedImage) myImage.myRead(filePath + "1.bmp");
		//红化处理
		Image red = processor.showChanelR(image);
		//绿化处理
		Image green = processor.showChanelG(image);
		//蓝化处理
		Image blue = processor.showChanelB(image);
		//灰度处理
		Image gray = processor.showGray(image);

		
		//将图片写入指定位置
		myImage.myWrite(red, filePath + "red");
		myImage.myWrite(green, filePath + "green");
		myImage.myWrite(blue, filePath + "blue");
		myImage.myWrite(gray, filePath + "gray");
		
	}
}
