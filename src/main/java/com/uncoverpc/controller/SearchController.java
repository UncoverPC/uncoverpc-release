package com.uncoverpc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uncoverpc.db.QuizService;
import com.uncoverpc.model.quiz.Quiz;

@Controller("api")
public class SearchController {

	@Autowired
	QuizService quizService;
	
	@PostMapping("/search")
	public List<Quiz> getQuiz(@RequestBody String quiz){
		//TO DO get matched quizzes
		return quizService.findAll();
	}
	@PostMapping("/searchProduct")
	public Map<String, Object> processSearch (@RequestBody String search) {
//		search = "Laptop, Large Screen, Black";
		List<String> list = Arrays.asList(search.split(","));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Product Type", list.get(0));
		List properties = list.subList(1, list.size());
		map.put("Properties", properties);
//		map.forEach((key, value) -> System.out.println(key + ":" + value));
		return map;
	}
	
}
