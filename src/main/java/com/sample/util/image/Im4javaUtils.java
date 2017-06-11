package com.sample.util.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.im4java.core.InfoException;
import org.im4java.process.ProcessStarter;

/**
 * 通过 im4java 调用 ImageMagick/GraphicsMagick 处理图片的工具类。 <br>
 * <br>
 * 
 * 可选的重心方位：NorthWest, North, NorthEast, West, Center, East, SouthWest, South, SouthEast <br>
 * <br>
 * 
 * 依赖： <br>
 * 1、im4java-1.4.0.jar <br>
 * 2、ImageMagick/GraphicsMagick 应用程序 <br>
 * <br>
 * 
 * 注： Windows 系统下需要设置 ImageMagick/GraphicsMagick 应用程序路径，可通过以下两种方式设置： <br>
 * 1、在 classes 根目录下寻找 im4java.properties 配置文件（默认） <br>
 * 2、在 web.xml 配置 path 路径。 <br>
 * <br>
 * 
 * Servlet 配置参数 <br>
 * path : ImageMagick/GraphicsMagick 应用程序路径 <br>
 * useGM : 是否使用 GraphicsMagick 处理图片（true/false）
 *
 */

public class Im4javaUtils extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String PATH_PARAM = "path";
	private static final String USEGM_PARAM = "useGM";
	private static final String DEFAULT_CONFIG_FILE = "/im4java.properties";

	static {

		Properties properties = getProperties();

		if (properties != null) {
			String useGM = properties.getProperty(USEGM_PARAM);
			if ("true".equalsIgnoreCase(useGM)) {
				System.setProperty("im4java.useGM", "true");
			}

			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				String path = properties.getProperty(PATH_PARAM);
				if (path != null) {
					ProcessStarter.setGlobalSearchPath(path);
				}
			}
		}
	}

	/**
	 * 通过 Servlet 读取配置参数并设置 im4java.useGM 系统属性和 path 路径。
	 * 
	 */
	public void init() {

		String useGM = getInitParameter(USEGM_PARAM);
		if ("true".equalsIgnoreCase(useGM)) {
			System.setProperty("im4java.useGM", "true");
		}

		String path = getInitParameter(PATH_PARAM);
		if (path != null && System.getProperty("os.name").toLowerCase().contains("win")) {
			ProcessStarter.setGlobalSearchPath(path);
		}
	}

	/**
	 * 读取默认配置文件参数。
	 * 
	 * @return
	 */
	private static Properties getProperties() {

		Properties properties = null;
		InputStream inputStream = Object.class.getResourceAsStream(DEFAULT_CONFIG_FILE);

		if (inputStream != null) {
			properties = new Properties();
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				// logging...
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// logging...
					e.printStackTrace();
				}
			}
		}

		return properties;
	}

	/**
	 * 缩放图片。 <br>
	 * <br>
	 * 
	 * 注：有些 gif 动画也可能转换过程中会出错，也有可能效果不佳。
	 * 
	 * @param resizeWidth
	 *            缩放宽度
	 * @param resizeHeight
	 *            缩放高度
	 * @param quality
	 *            图片质量
	 * @param sourcePath
	 *            源图片路径
	 * @param destPath
	 *            目标图片路径
	 * @return
	 */
	public static boolean resize(Integer resizeWidth, Integer resizeHeight, Double quality, String sourcePath, String destPath) {

		return resizeExtent(resizeWidth, resizeHeight, null, null, null, null, quality, sourcePath, destPath);
	}

	/**
	 * 缩放图片并限定图片范围。
	 * 
	 * @param resizeWidth
	 *            缩放宽度
	 * @param resizeHeight
	 *            缩放高度
	 * @param extentWidth
	 *            范围宽度
	 * @param extentHeight
	 *            范围高度
	 * @param background
	 *            填充区域背景色
	 * @param gravity
	 *            重心方位
	 * @param quality
	 *            图片质量
	 * @param sourcePath
	 *            源图片路径
	 * @param destPath
	 *            目标图片路径
	 * @return
	 */
	public static boolean resizeExtent(Integer resizeWidth, Integer resizeHeight, Integer extentWidth, Integer extentHeight, String background, String gravity, Double quality, String sourcePath, String destPath) {

		try {

			IMOperation op = new IMOperation();

			op.addImage(sourcePath);

			if (resizeWidth != null || resizeHeight != null) {
				op.resize(resizeWidth, resizeHeight); // 任何一个为 null， 则按不为 null 的一边为基准取等比例
			}

			if (background != null) {
				op.background(background); // 填充背景色，默认白色。
			}

			if (gravity != null) {
				op.gravity(gravity); // 重心方位，默认 NorthWest
			}

			if (extentWidth != null || extentHeight != null) {
				op.extent(extentWidth, extentHeight); // 范围，任何一个为 null， 则按不为 null 的一边为基准取等比例
			}

			if (quality != null) {
				op.quality(quality); // 图片输出质量，默认 75.0
			}

			op.p_profile("*"); // 删除图片 Exif 信息

			if (destPath == null) {
				op.addImage(sourcePath);
			} else {
				op.addImage(destPath);
			}

			ConvertCmd convert = new ConvertCmd();

			convert.run(op);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 限定图片范围。
	 * 
	 * @param extentWidth
	 *            范围宽度
	 * @param extentHeight
	 *            范围高度
	 * @param background
	 *            填充区域背景色
	 * @param gravity
	 *            重心方位
	 * @param quality
	 *            图片质量
	 * @param sourcePath
	 *            源图片路径
	 * @param destPath
	 *            目标图片路径
	 * @return
	 */
	public static boolean extent(Integer extentWidth, Integer extentHeight, String background, String gravity, Double quality, String sourcePath, String destPath) {

		return resizeExtent(null, null, extentWidth, extentHeight, background, gravity, quality, sourcePath, destPath);
	}

	/**
	 * 旋转图片。
	 * 
	 * @param rotate
	 *            旋转角度（负数为逆时针）
	 * @param quality
	 *            图片质量
	 * @param sourcePath
	 *            源图片路径
	 * @param destPath
	 *            目标图片路径
	 * @return
	 */
	public static boolean rotate(Double rotate, Double quality, String sourcePath, String destPath) {

		try {

			IMOperation op = new IMOperation();

			op.addImage(sourcePath);

			op.rotate(rotate);

			if (quality != null) {
				op.quality(quality);
			}

			op.p_profile("*");

			if (destPath == null) {
				op.addImage(sourcePath);
			} else {
				op.addImage(destPath);
			}

			ConvertCmd convert = new ConvertCmd();

			convert.run(op);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 裁剪图片。 <br>
	 * <br>
	 * 
	 * 注：裁剪起点坐标必须在图片长宽的范围内，否则会抛出异常的同时并生成一张原图一样的图片。
	 * 
	 * @param width
	 *            裁剪区域宽度
	 * @param height
	 *            裁剪区域高度
	 * @param x
	 *            裁剪起点横坐标
	 * @param y
	 *            裁剪起点纵坐标
	 * @param quality
	 *            图片质量
	 * @param sourcePath
	 *            源图片路径
	 * @param destPath
	 *            目标图片路径
	 * @return
	 */
	public static boolean crop(Integer width, Integer height, int x, int y, Double quality, String sourcePath, String destPath) {

		try {

			IMOperation op = new IMOperation();

			op.addImage(sourcePath);

			op.crop(width, height, x, y);

			if (quality != null) {
				op.quality(quality);
			}

			op.p_profile("*");

			if (destPath == null) {
				op.addImage(sourcePath);
			} else {
				op.addImage(destPath);
			}

			ConvertCmd convert = new ConvertCmd();

			convert.run(op);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 文字水印（仅限英文，中文会乱码，如需中文则可使用 BufferedImage 生成含中文文本的图片对象，然后再通过使用图片水印实现）。
	 * 
	 * @param font
	 *            字体
	 * @param pointsize
	 *            字体大小
	 * @param fill
	 *            字体颜色
	 * @param gravity
	 *            重心方位
	 * @param x
	 *            横向偏移量
	 * @param y
	 *            纵向偏移量
	 * @param text
	 *            水印文本
	 * @param quality
	 *            图片质量
	 * @param sourcePath
	 *            源图片路径
	 * @param destPath
	 *            目标图片路径
	 * @return
	 */
	public static boolean textWatermark(String font, Integer pointsize, String fill, String gravity, int x, int y, String text, Double quality, String sourcePath, String destPath) {

		try {

			IMOperation op = new IMOperation();

			op.addImage(sourcePath);

			op.font(font); // 字体，必须

			if (pointsize != null) {
				op.pointsize(pointsize); // 字体大小，默认 12
			}

			if (fill != null) {
				op.fill(fill); // 字体颜色，默认黑色
			}

			if (gravity != null) {
				op.gravity(gravity); // 重心方位，默认 NorthWest
			}

			op.draw("text " + x + "," + y + " '" + text + "'");

			if (quality != null) {
				op.quality(quality);
			}

			op.p_profile("*");

			if (destPath == null) {
				op.addImage(sourcePath);
			} else {
				op.addImage(destPath);
			}

			ConvertCmd convert = new ConvertCmd();

			convert.run(op);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 图片水印（可以对 BufferedImage 进行处理）。
	 * 
	 * @param watermarkObject
	 *            水印图片对象，可以是图片路径、或者 BufferedImage
	 * @param x
	 *            横坐标偏移量
	 * @param y
	 *            纵坐标偏移量
	 * @param dissolve
	 *            透明度
	 * @param gravity
	 *            重心方位
	 * @param quality
	 *            图片质量
	 * @param sourcePath
	 *            源图片路径
	 * @param destPath
	 *            目标图片路径
	 * @return
	 */
	public static boolean imageWatermark(Object watermarkObject, Integer x, Integer y, Integer dissolve, String gravity, Double quality, String sourcePath, String destPath) {

		try {

			IMOperation op = new IMOperation();

			op.addImage();

			op.addImage();

			if (x != null && y != null) {
				op.geometry(null, null, x, y); // （水印图片的）缩放宽度、缩放高度、横坐标偏移量、纵坐标偏移量
			}

			if (dissolve != null) {
				op.dissolve(dissolve); // （水印图片的）透明度，默认不透明
			}

			if (gravity != null) {
				op.gravity(gravity); // （水印图片的）重心方位，默认 NorthWest
			}

			if (quality != null) {
				op.quality(quality);
			}

			op.p_profile("*");

			op.addImage();

			if (destPath == null) {
				destPath = sourcePath;
			}

			CompositeCmd composite = new CompositeCmd();

			composite.run(op, watermarkObject, sourcePath, destPath);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 生成文本 BufferedImage （解决含有中文文本的水印问题）。
	 * 
	 * @param width
	 *            图片宽度，若为 null，则按文本所占像素宽度
	 * @param height
	 *            图片高度，若为 null，则按文本字体像素高度
	 * @param backgroundColor
	 *            背景色
	 * @param backgroundAlpha
	 *            背景色透明度
	 * @param font
	 *            字体
	 * @param fontColor
	 *            字体颜色
	 * @param fontAlpha
	 *            字体颜色透明度
	 * @param antialiasing
	 *            字体是否抗锯齿
	 * @param text
	 *            文本
	 * @param x
	 *            文本呈现横坐标，若为 null，则按 0
	 * @param y
	 *            文本呈现纵坐标，若为 null，则按则按文本字体像素高度的5/6
	 * @return
	 */
	public static BufferedImage getTextBufferedImage(Integer width, Integer height, String backgroundColor, Float backgroundAlpha, Font font, String fontColor, Float fontAlpha, boolean antialiasing, String text, Integer x, Integer y) {

		if (width == null) {
			width = getTextPixelWidth(font.getSize(), text);
		}

		if (height == null) {
			height = font.getSize();
		}

		BufferedImage bufferedImage = new BufferedImage(width, height, Transparency.TRANSLUCENT); // 透明的 BufferedImage

		Graphics2D graphics2d = bufferedImage.createGraphics();

		if (antialiasing) {
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 字体消除锯齿
		}

		if (backgroundColor != null) {

			if (backgroundAlpha == null) {
				backgroundAlpha = 1.0f;
			}
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, backgroundAlpha)); // 透明度
			graphics2d.setColor(getColor(backgroundColor));
			graphics2d.fillRect(0, 0, width, height);
		}

		if (text != null) {

			if (fontAlpha == null) {
				fontAlpha = 1.0f;
			}
			graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fontAlpha)); // 透明度
			if (fontColor == null) {
				fontColor = "#000000";
			}
			graphics2d.setColor(getColor(fontColor));
			graphics2d.setFont(font);

			if (x == null) {
				x = 0;
			}
			if (y == null) {
				y = font.getSize() * 5 / 6; // 因为实际输出纵坐标会有所偏差，此处按小字体大概计算出差不多的高度
			}
			graphics2d.drawString(text, x, y);

		}

		graphics2d.dispose();

		return bufferedImage;
	}

	/**
	 * 计算文本所占的像素长度（按 gbk 编码所占字节数区分宽度占比）。
	 * 
	 * @param fontSize
	 * @param text
	 * @return
	 */
	public static int getTextPixelWidth(int fontSize, String text) {

		int length = 0;

		try {
			length = text.getBytes("gbk").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		length = fontSize * length / 2;
		return length;
	}

	/**
	 * 将十六进制颜色值转换为 Color 对象。
	 * 
	 * @param str
	 * @return
	 */
	public static Color getColor(String str) {

		if (str.startsWith("#")) {
			str = str.substring(1);
		}
		int i = Integer.parseInt(str, 16);

		return new Color(i);
	}

	/**
	 * 获取图片信息（完整信息）。
	 * 
	 * @param imagePath
	 * @return
	 */
	public static Info getInfo(String imagePath) {

		return getInfo(imagePath, false);
	}

	/**
	 * 获取图片信息（true：基本信息/false：完整信息）。
	 * 
	 * @param imagePath
	 * @param basic
	 * @return
	 */
	public static Info getInfo(String imagePath, boolean basic) {

		try {
			return new Info(imagePath, basic);
		} catch (InfoException e) {
			e.printStackTrace();
			return null;
		}
	}

	// /**
	// * 测试获取图片信息。
	// *
	// * @param imagePath
	// * @throws IOException
	// * @throws InterruptedException
	// * @throws IM4JavaException
	// */
	// public static void testImageInfo(String imagePath) throws IOException, InterruptedException, IM4JavaException {
	//
	// IMOperation op = new IMOperation();
	// // op.format("%w"); // 设置获取宽度参数
	// // op.format("%h"); // 设置获取高度参数
	// op.format("width:%w,height:%h,path:%d%f,size:%b%[EXIF:DateTimeOriginal]");
	// op.addImage(imagePath);
	//
	// IdentifyCmd identifyCmd = new IdentifyCmd(true); // GraphicsMagick
	// ArrayListOutputConsumer output = new ArrayListOutputConsumer();
	// identifyCmd.setOutputConsumer(output);
	// identifyCmd.run(op);
	// ArrayList<String> cmdOutput = output.getOutput();
	//
	// System.out.println(cmdOutput);
	//
	// // 参数列表：
	// // %b file size
	// // %c comment
	// // %d directory
	// // %e filename extension
	// // %f filename
	// // %g page dimensions and offsets
	// // %h height
	// // %i input filename
	// // %k number of unique colors
	// // %l label
	// // %m magick
	// // %n number of scenes
	// // %o output filename
	// // %p page number
	// // %q image bit depth
	// // %r image type description
	// // %s scene number
	// // %t top of filename
	// // %u unique temporary filename
	// // %w width
	// // %x horizontal resolution
	// // %y vertical resolution
	// // %A transparency supported
	// // %C compression type
	// // %D GIF disposal method
	// // %G Original width and height
	// // %H page height
	// // %M original filename specification
	// // %O page offset (x,y)
	// // %P page dimensions (width,height)
	// // %Q compression quality
	// // %T time delay (in centi-seconds)
	// // %U resolution units
	// // %W page width
	// // %X page horizontal offset (x)
	// // %Y page vertical offset (y)
	// // %@ trim bounding box
	// // %# signature
	// // \n newline
	// // \r carriage return
	// // %% %
	//
	// }

	// public static void main(String[] args) throws Exception {
	//
	// // boolean b1 = resize(100, null, 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // System.out.println(b1);
	//
	// // boolean b2 = resizeExtent(300, null, 300, 300, "#ff0000", "Center", 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // System.out.println(b2);
	//
	// // boolean b2 = extent(300, null, "#ff0000", null, 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // System.out.println(b2);
	//
	// // boolean b3 = rotate(180.0, 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // System.out.println(b3);
	//
	// // boolean b4 = crop(60, 60, -10, -10, 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // boolean b4 = crop(60, 60, 0, 0, 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // System.out.println(b4);
	//
	// // boolean b5 = textWatermark("Verdana", 50, null, "west", 100, 100, "I love you", 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // System.out.println(b5);
	//
	// // boolean b6 = imageWatermark("d:/b.jpg", 25, 10, 50, "SouthEast", 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // System.out.println(b6);
	//
	// // String text = "你好吗，abcdAEDF@#@#伟大";
	// // Font font = new Font("宋体", Font.PLAIN, 14);
	// // int fontWidth = getTextPixelWidth(font.getSize(), text);
	// // int bufferWidth = 1200;
	// // int bufferHeight = 25;
	// // int x = 1200-fontWidth-5;
	// // int y = 17;
	// // BufferedImage bufferedImage = getTextBufferedImage(bufferWidth, bufferHeight, "#000000", 0.6f, font, "ffffff", 1.0f, false, text, x, y);
	// // boolean b6 = imageWatermark(bufferedImage, 0, 10, null, "SouthEast", 90.0, "d:/a.jpg", "d:/a0.jpg");
	// // System.out.println(b6);
	//
	// // Info info = new Info("d:/b.jpg"); // 完整信息
	// // Info info = new Info("d:/b.jpg", true); // 基本信息
	//
	// // Info info = getInfo("d:/b.jpg", true);
	// // Enumeration<String> enumeration = info.getPropertyNames();
	// // while (enumeration.hasMoreElements()) {
	// // String key = enumeration.nextElement();
	// // System.out.println(key + " = " + info.getProperty(key));
	// // }
	//
	// }

}