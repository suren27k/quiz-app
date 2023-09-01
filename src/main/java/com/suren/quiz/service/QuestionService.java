package com.suren.quiz.service;

import com.suren.quiz.dao.QuestionDao;
import com.suren.quiz.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService
{
	@Autowired
	QuestionDao questionDao;
	public List<Question> getAllQuestions()
	{
		return questionDao.findAll();
	}

	public List<Question> getQuestionsByCategory(String category)
	{
		return questionDao.findByCategory(category);
	}

	public Question addNewQuestion(Question question)
	{
		return questionDao.save(question);
	}
}
