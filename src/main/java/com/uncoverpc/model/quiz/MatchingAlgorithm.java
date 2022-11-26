package com.uncoverpc.model.quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.uncoverpc.db.QuizProductService;
import com.uncoverpc.product.Product;

public class MatchingAlgorithm {

	@Autowired
	private QuizProductService quizProductService;
    
	public List<Product> matchProduct(Quiz quiz, List<QuizResponseAnswer> quizResponseAnswers){
		//Create a list of all question
		//Get list of questions
		//Find the question keyword
		//Find 
		List<Product> products = quizProductService.findAll();//TO DO, implement find by specific quiz
		
		//Iterate through ever quiz question
		for(int i = 0; i < quiz.getQuestions().size(); i++) {
		    //Get Users Answer
		    String userResponse = quizResponseAnswers.get(i).getAnswer();
		    for(int j = 0; j < products.size(); j++) {
		        //TO DO change to identifier instead of question
//		        Answer answer = (Answer) products.get(j).getAnswerBank().get(quiz.getQuestions().get(i).getQuestion());
//		        if()
		    }
		    
		}
		
		
		
		//= QuizProductService quiz.getTitle()//getting list of products
//		for(Question question: quiz.getQuestions()) {
//			for(Product product: products)
//		}
//		
//		for(quiz.getQuestions())
		//get quiz quesitons
		//iterate through quiz questions
		//every question has an answer
		//if answer matches one of the products features, then feature = 1, if not feature =0, if exceeds, features =2; if 1 or 2, score++ or score+=2;
		//take product with 3 highest score
		// if score is greater than like 5
		// ability to do different questions. different quesitons are processed differently.
		// question types
		// mutiple choice one answer
		// price range dragger
		// mutiple choice, mutiple answers
		// 
		return new ArrayList<Product>();
	}
	
	//TO DO add writing feature (you write your requirements, and algo will automatically return products)

}
