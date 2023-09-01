package com.suren.quiz.controller;

import com.suren.quiz.entity.Question;
import com.suren.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("question")
public class QuestionController
{
	@Autowired
	QuestionService questionService;
	@GetMapping("allQuestions")
	public List<Question> getAllQuestions()
	{
		return questionService.getAllQuestions();
	}

	@GetMapping("category/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category)
	{
		return questionService.getQuestionsByCategory(category);
	}

	@PostMapping("add")
	public Question addNewQuestion(@RequestBody Question question)
	{
		return questionService.addNewQuestion(question);
	}
}
