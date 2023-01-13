package com.uncoverpc.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncoverpc.product.Laptop;
import com.uncoverpc.db.LaptopService;
import com.uncoverpc.model.quiz.QuizResponse;



@Controller
public class LaptopController {
	@Autowired
	private LaptopService laptopService;
	
	@GetMapping("/api/get/findbyLaptopUse")
	@ResponseBody
	public List<Laptop> findByUse(HttpServletRequest request, @RequestParam(name="use") String use) {
		return laptopService.findbyLaptopUse(use);
	}
	
	@PostMapping("/post/quizEnd")
	@ResponseBody
	public List<Laptop> getProducts(  @RequestBody QuizResponse QuizResponse,  HttpServletRequest request ) {
		
		
		System.out.println("Getting Products");

		System.out.println(QuizResponse.toString());
		HashMap <String, String> map = new HashMap<String, String>();
		for(int i=0; i<QuizResponse.getAnswers().size(); i++) {
			map.put(QuizResponse.getQuestions().get(i), QuizResponse.getAnswers().get(i));

		}
		System.out.println(map.get("use"));

		List<Laptop> laptopsByUse = laptopService.findbyLaptopUse(map.get("use"));
		
		System.out.println(laptopsByUse.get(0).toString());
		
		
		List<Laptop> tempLaptops1 = laptopsByUse;
		System.out.println(tempLaptops1.size());
		List<Laptop> tempLaptops2 = new ArrayList<Laptop>();
		
		for(int j=0; j<QuizResponse.getQuestions().size(); j++) {
			String key = QuizResponse.getQuestions().get(j);
			for(int k=0; k<tempLaptops1.size(); k++) {
				if(tempLaptops1.get(k).getQuizResponses().getMap().get(key) == map.get(key)) {

					tempLaptops2.add(tempLaptops1.get(k));
				}
			}
			if(tempLaptops2.isEmpty()) {
				continue;
			} else {
				tempLaptops1 = tempLaptops2;
				tempLaptops2.clear();
			}
		}
		List<Laptop>recommendations = tempLaptops2;
		if(tempLaptops2.isEmpty()) {
			recommendations = tempLaptops1;
		}
		return recommendations;
	}
}
