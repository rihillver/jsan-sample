package com.sample.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 图形验证码工具类。<br>
 * <br>
 * 
 * 更专业的验证码可使用 patchca、kaptcha、JCaptcha、SkewPassImage 等。
 *
 */

public class Captcha {

	protected int width = 150;
	protected int height = 60;
	protected int length = 4;

	// protected String borderColor;
	protected String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	protected String backgroundColor1 = "#6EB4C8";
	protected String backgroundColor2 = "#126C9C";

	protected String[] fontColor = { "#EBEBEB" };
	protected String[] fontFamily = { "Times New Roman", "Arail", "Arail Black", "Courier New", "Comic sans MS" };

	protected Random random = new Random();

	/**
	 * 设置背景色（单色）。
	 * 
	 * @param backgroundColor
	 */
	public void setBackgroundColor(String backgroundColor) {

		this.backgroundColor1 = backgroundColor;
		this.backgroundColor2 = null;
	}

	/**
	 * 设置背景色（渐变）。
	 * 
	 * @param backgroundColor1
	 * @param backgroundColor2
	 */
	public void setBackgroundColor(String backgroundColor1, String backgroundColor2) {

		this.backgroundColor1 = backgroundColor1;
		this.backgroundColor2 = backgroundColor2;
	}

	/**
	 * 设置背景色 1 。
	 * 
	 * @param backgroundColor1
	 */
	public void setBackgroundColor1(String backgroundColor1) {

		this.backgroundColor1 = backgroundColor1;
	}

	/**
	 * 返回背景色 1 。
	 * 
	 * @return
	 */
	public String getBackgroundColor1() {

		return backgroundColor1;
	}

	/**
	 * 设置背景色 2 。
	 * 
	 * @param backgroundColor2
	 */
	public void setBackgroundColor2(String backgroundColor2) {

		this.backgroundColor2 = backgroundColor2;
	}

	/**
	 * 返回背景色 2 。
	 * 
	 * @return
	 */
	public String getBackgroundColor2() {

		return backgroundColor2;
	}

	/**
	 * 设置图片宽度。
	 * 
	 * @param width
	 */
	public void setWidth(int width) {

		this.width = width;
	}

	/**
	 * 返回图片宽度。
	 * 
	 * @return
	 */
	public int getWidth() {

		return width;
	}

	/**
	 * 设置图片高度。
	 * 
	 * @param height
	 */
	public void setHeight(int height) {

		this.height = height;
	}

	/**
	 * 返回图片高度。
	 * 
	 * @return
	 */
	public int getHeight() {

		return height;
	}

	/**
	 * 设置验证码长度。
	 * 
	 * @param length
	 */
	public void setLength(int length) {

		this.length = length;
	}

	/**
	 * 返回验证码长度。
	 * 
	 * @return
	 */
	public int getLength() {

		return length;
	}

	/**
	 * 设置待选字符串。
	 * 
	 * @param string
	 */
	public void setString(String string) {

		this.string = string;
	}

	/**
	 * 返回待选字符串。
	 * 
	 * @return
	 */
	public String getString() {

		return string;
	}

	/**
	 * 设置字体颜色。
	 * 
	 * @param fontColor
	 */
	public void setFontColor(String... fontColor) {

		this.fontColor = fontColor;
	}

	/**
	 * 返回字体颜色。
	 * 
	 * @return
	 */
	public String[] getFontColor() {

		return fontColor;
	}

	/**
	 * 设置字体。
	 * 
	 * @param fontFamily
	 */
	public void setFontFamily(String... fontFamily) {

		this.fontFamily = fontFamily;
	}

	/**
	 * 返回字体。
	 * 
	 * @return
	 */
	public String[] getFontFamily() {

		return fontFamily;
	}

	// /**
	// * 设置边框颜色。
	// *
	// * @param borderColor
	// */
	// public void setBorderColor(String borderColor) {
	//
	// this.borderColor = borderColor;
	// }

	// /**
	// * 返回边框颜色。
	// *
	// * @return
	// */
	// public String getBorderColor() {
	//
	// return borderColor;
	// }

	/**
	 * （随机）生成验证码图片，并返回验证码。
	 * 
	 * @param outputStream
	 * @return
	 */
	public String create(OutputStream outputStream) {

		String str = getRandomString();
		if (create(outputStream, str)) {
			return str;
		} else {
			return null;
		}
	}

	/**
	 * （指定）生成验证码图片，并返回验证码。
	 * 
	 * @param outputStream
	 * @param str
	 * @return
	 */
	public boolean create(OutputStream outputStream, String str) {

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2d = bufferedImage.createGraphics();

		// 单背景色或渐变背景色
		if (backgroundColor2 == null) {
			graphics2d.setColor(getColor(backgroundColor1));
		} else {
			GradientPaint gradientPaint = new GradientPaint(random.nextInt(width), random.nextInt(height), getColor(backgroundColor1), random.nextInt(width), random.nextInt(height), getColor(backgroundColor2));
			graphics2d.setPaint(gradientPaint);
		}

		// 图像优化除锯齿
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics2d.fillRect(0, 0, width, height);

		Color color = getColor(fontColor[random.nextInt(fontColor.length)]);
		graphics2d.setColor(color); // 颜色

		Font font = new Font(fontFamily[random.nextInt(fontFamily.length)], Font.PLAIN, width / (length - 1));
		graphics2d.setFont(font); // 字体

		for (int i = 0; i < length; i++) {

			double d1 = (random.nextInt(15) - 7) / 11.0;
			double d2 = (random.nextInt(3) - 0.9) / 11.0;

			int i1 = (int) (-d1 * 10 + (width / length / 5 + i * width / (length + 1)));
			int i2 = (int) (-d2 * 30 + (height * 3 / 4));

			graphics2d.shear(d1, d2);
			graphics2d.drawString(str.substring(i, i + 1), i1, i2);

			graphics2d.shear(-d1, -d2);
		}

		// if (borderColor != null) { // 设置边框
		// graphics2d.setColor(getColor(borderColor));
		// graphics2d.drawRect(0, 0, width - 1, height - 1);
		// }

		graphics2d.dispose();

		try {
			ImageIO.write(bufferedImage, "JPEG", outputStream);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 将十六进制颜色值转换为 Color 对象。
	 * 
	 * @param str
	 * @return
	 */
	protected Color getColor(String str) {

		if (str.startsWith("#")) {
			str = str.substring(1);
		}
		int i = Integer.parseInt(str, 16);

		return new Color(i);
	}

	/**
	 * 根据给定字符串返回随机验证码。
	 * 
	 * @return
	 */
	protected String getRandomString() {

		String str = "";
		int len = string.length();

		for (int i = 0, j = 0; i < length; i++) {
			j = random.nextInt(len);
			str += string.substring(j, j + 1);
		}

		return str;
	}

}
