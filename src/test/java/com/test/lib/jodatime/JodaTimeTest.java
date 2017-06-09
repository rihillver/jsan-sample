package com.test.lib.jodatime;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import junit.framework.TestCase;

public class JodaTimeTest extends TestCase {

	public void testFoo() {

		DateTime dt = new DateTime(2012, 12, 15, 18, 23, 55);
		System.out.println(dt);

		// 通过jdk时间对象构造
		Date date = new Date();
		DateTime dateTime = new DateTime(date);

		Calendar calendar = Calendar.getInstance();
		dateTime = new DateTime(calendar);

		// Joda-time 各种操作.....
		dateTime = dateTime.plusDays(1) // 增加天
				.plusYears(1)// 增加年
				.plusMonths(1)// 增加月
				.plusWeeks(1)// 增加星期
				.minusMillis(1)// 减分钟
				.minusHours(1)// 减小时
				.minusSeconds(1);// 减秒数

		// 计算完转换成jdk 对象
		Date date2 = dateTime.toDate();
		Calendar calendar2 = dateTime.toCalendar(Locale.CHINA);

		System.out.println(date2);
		System.out.println(calendar2);

	}

	public void testBar() {

		String str = "1996-01-01T00:00:00+08:00";

		DateTime dateTime = DateTime.parse(str); // 默认ISO8601日期时间格式

		System.out.println(dateTime);
		System.out.println(dateTime.toDate());

	}

}
