package com.suren.quiz.controller;

import com.suren.quiz.entity.Question;
import com.suren.quiz.entity.QuestionWrapper;
import com.suren.quiz.entity.Quiz;
import com.suren.quiz.entity.QuizResponse;
import com.suren.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController
{
	@Autowired
	QuizService quizService;

	@PostMapping("create")
	public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam Integer noOfQuestions, @RequestParam String title)
	{
		return quizService.createQuiz(category, noOfQuestions, title);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
	{
		return quizService.getQuizQuestions(id);
	}

	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> quizResponses)
	{
		return quizService.evaluateResult(id, quizResponses);
	}
}
