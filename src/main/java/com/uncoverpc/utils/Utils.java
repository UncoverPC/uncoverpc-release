package com.uncoverpc.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class Utils {
	
	
	@PostMapping("/search")
	public Map<String, Object> processSearch (@RequestBody String search) {
		search = "Laptop, Large Screen, Black";
		List<String> list = Arrays.asList(search.split(","));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Product Type", list.get(0));
		List properties = list.subList(1, list.size());
		map.put("Properties", properties);
//		map.forEach((key, value) -> System.out.println(key + ":" + value));
		return map;
	}
}