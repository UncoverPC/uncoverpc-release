package com.uncoverpc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.uncoverpc.db.QuizService;
import com.uncoverpc.model.quiz.Question;
import com.uncoverpc.model.quiz.Quiz;

@Controller
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	
	@GetMapping("/productquiz/{quizName}")
	public ModelAndView getQuiz(@PathVariable(value = "quizName") String quizName) {
		ModelAndView model = new ModelAndView("quiz_template.html");
		Quiz quiz = quizService.findByQuizTitle(quizName);
		model.addObject("questions", quiz.getQuestions());

		model.addObject("quizTitle", quiz.getTitle());
		return model;
	}
	
	@GetMapping("/create_quiz1")
	public ModelAndView testQuiz() {
		ModelAndView model = new ModelAndView("create_quiz1.html");
		return model;
	}
	
	@PostMapping("/api/quiz/create/")
	public ModelAndView addQuiz (HttpServletRequest request, Quiz json) {
		System.out.println("Trying to add quiz");
		try {
//			//JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
//			
//			JsonArray jsonQuestions = obj.getAsJsonArray("question_bank");
//			ArrayList<Question> questions = parseJsonArray(jsonQuestions);
//			String title = obj.get("quizTitle").toString();
			//Quiz newQuiz = new Quiz(questions, title, quizService);
			//quizService.save(newQuiz);
			
			ModelAndView view = new ModelAndView("quizCreateSuccess.html");
			return view;
		}
		catch(Exception e) {
			ModelAndView view = new ModelAndView("");
			return view;
		}
	}
	
	public ArrayList<Question> parseJsonArray(JsonArray json) {
	ArrayList<Question> list = new ArrayList<Question>();
		for(int i=0; i< json.size(); i++) {
			JsonObject tempObj = json.get(i).getAsJsonObject();
			boolean scalable = tempObj.get("scalable").getAsBoolean();
			String question = tempObj.get("question").toString();
			JsonArray jsonAns = tempObj.get("answers").getAsJsonArray();
			ArrayList<String> answers = new ArrayList<String>();
			
			for(int j=0; j<jsonAns.size(); j++) {
				answers.add(jsonAns.get(j).toString());
			}
			Question tempQuestion = new Question(question, scalable, answers);
			list.add(tempQuestion);
		}
		return list;
	}
}
