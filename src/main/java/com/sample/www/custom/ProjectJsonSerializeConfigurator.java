package com.sample.www.custom;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jsan.mvc.json.AbstractJsonSerializeConfigurator;

/**
 * 自定义全局 json 序列化配置器。
 *
 */

public class ProjectJsonSerializeConfigurator extends AbstractJsonSerializeConfigurator {

	{
		// 设置关闭循环引用检测的功能
		setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
		// 设置日期格式
		setDateFormat("yyyy-MM-dd HH:mm:ss");
	}
}
