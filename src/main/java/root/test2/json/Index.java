package root.test2.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.jsan.convert.JsonConvertService;
import com.jsan.convert.annotation.ConvertServiceRegister;
import com.jsan.mvc.View;
import com.jsan.mvc.annotation.Get;
import com.jsan.mvc.annotation.Post;
import com.jsan.mvc.annotation.Render;

public class Index {

	@Render
	public void list() {

	}

	@Get
	@Render
	public void one() {

	}

	/**
	 * json 字符串转数组、集合
	 * 
	 */
	@Post
	@Render(url = "result")
	@ConvertServiceRegister(JsonConvertService.class)
	public void one(View view, int id, String name, boolean sex, double[] json1, Object[] json2, HashSet<String> json3,
			List<ArrayList<BigDecimal>> json4, double[][] json5) {

		view.add("id", id);
		view.add("name", name);
		view.add("sex", sex);

		view.add("json1", Arrays.toString(json1));
		view.add("json2", Arrays.toString(json2));
		view.add("json3", json3);
		view.add("json4", json4);
		view.add("json5", Arrays.toString(json5));
		view.add("json5Array", json5);
	}

}
