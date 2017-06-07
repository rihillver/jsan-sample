package com.jsan.util.local;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 要改进的：新版的 pinyin 支持同音字。。。。。
 * 
 * 中文拼音工具类（支持繁体字）。<br>
 * <br>
 * 
 * 1、生成全拼。 <br>
 * 2、生成拼音首字母。<br>
 * 
 * 注：非中文则原样输出（包括空格）。
 *
 */

public class PinyinUtils {

	/**
	 * 返回中文拼音（小写）。
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinyin(String str) {

		String result = null;

		if (str != null) {

			HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
			defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 设置输出为小写字母
			defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 去掉声调

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < str.length(); i++) {

				String[] pinyinArray = null;
				try {
					pinyinArray = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i), defaultFormat);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
				if (pinyinArray == null) {
					sb.append(str.charAt(i));
				} else {
					sb.append(pinyinArray[0]);
				}
			}
			result = sb.toString();
		}

		return result;
	}

	/**
	 * 返回中文拼音（小写），如果为 null 则返回 "" 。
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinyins(String str) {

		return str == null ? "" : getPinyin(str);
	}

	/**
	 * 返回中文拼音首字母（小写）。
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinyinInitial(String str) {

		String result = null;

		if (str != null) {

			HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
			defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 设置输出为小写字母

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < str.length(); i++) {

				String[] pinyinArray = null;
				try {
					pinyinArray = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i), defaultFormat);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
				if (pinyinArray == null) {
					sb.append(str.charAt(i));
				} else {
					sb.append(pinyinArray[0].charAt(0));
				}
			}
			result = sb.toString();
		}

		return result;
	}

	/**
	 * 返回中文拼音首字母（小写），如果为 null 则返回 "" 。
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinyinInitials(String str) {

		return str == null ? "" : getPinyinInitial(str);
	}

	// public static void main(String[] args) {
	//
	// long l = System.currentTimeMillis();
	//
	// String str = "a 弼马温，繁體字又該是如何電腦，哈哈 I Love YOu！ （~！@！@#￥#￥……￥%……）";
	// String py = PinyinUtil.getPinyin(str);
	// System.out.println(py);
	//
	// for (int i = 0; i < 100; i++) {
	// System.out.println(PinyinUtil.getPinyinInitials(RandomUtil.getChinese(10)));
	// }
	//
	// System.out.println(System.currentTimeMillis() - l);
	//
	// }

}