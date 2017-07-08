package com.sample.www.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsan.mvc.MappingInfo;
import com.jsan.mvc.MvcConfig;
import com.jsan.mvc.View;
import com.jsan.mvc.resolve.AbstractResolver;
import com.jsan.mvc.resolve.annotation.RegisterName;

@RegisterName(GifResolver.GIF)
public class GifResolver extends AbstractResolver {

	public static final String GIF = "gif";

	@Override
	public void execute(View view, MvcConfig mvcConfig, MappingInfo mappingInfo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setContentType(response, view.getContentType(), "image/gif");

		writeBytes(response, view.getValue());
	}

}
