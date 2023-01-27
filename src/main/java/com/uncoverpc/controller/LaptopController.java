package com.uncoverpc.controller;

import java.util.List;
import java.util.Arrays;
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
		try {
			List<Laptop> list = laptopService.findbyLaptopUse(use);
			return laptopService.findbyLaptopUse(use);
		} catch(Exception e) {
			System.out.println(e);
		}
		return laptopService.findbyLaptopUse(use);
	}
	
	@PostMapping("/post/quizEnd")
	@ResponseBody
	public List<Laptop> getProducts(  @RequestBody QuizResponse QuizResponse,  HttpServletRequest request ) {
		
		HashMap <String, String> map = new HashMap<String, String>();
		for(int i=0; i<QuizResponse.getAnswers().size(); i++) {
			map.put(QuizResponse.getQuestions().get(i), QuizResponse.getAnswers().get(i));

		}
		List<Laptop> laptopsByUse = laptopService.findbyLaptopUse(map.get("Main use?"));
		int[] matchCounter = new int[laptopsByUse.size()];
		Arrays.fill(matchCounter, 0);
		
		if(laptopsByUse.size() <= 3) {
			return laptopsByUse;
		}
		
		
		for(int j=0; j<QuizResponse.getQuestions().size(); j++) {
			String key = QuizResponse.getQuestions().get(j);
			for(int k=0; k<laptopsByUse.size(); k++) {
				if(laptopsByUse.get(k).getQuizResponses().get(key) == map.get(key)) {
					matchCounter[k]++;
				}
			}
		}
		
		int max = matchCounter[0];
		int second = matchCounter[1];
		int third = matchCounter[2];
	    int index = 0;
	    int index2 = 1;
	    int index3 =2;
		
		for (int i = 0; i < matchCounter.length; i++) {
	      if (max < matchCounter[i]) {
	        max = matchCounter[i];
	        index = i;
	      } else if (second < matchCounter[i]) {
	    	  second = matchCounter[i];
	    	  index2 =i;
	      }
	      else if (third < matchCounter[i]) {
	    	  third = matchCounter[i];
	    	  index3 =i;
	      }
	    }
		List<Laptop> recommendations = Arrays.asList(laptopsByUse.get(index), laptopsByUse.get(index2), laptopsByUse.get(index3));
		return recommendations;
	}
}
