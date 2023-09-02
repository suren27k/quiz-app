package com.suren.quiz.service;

import com.suren.quiz.dao.QuestionDao;
import com.suren.quiz.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService
{
	@Autowired
	QuestionDao questionDao;
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
	{
		return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
	}

	public ResponseEntity<Question> addNewQuestion(Question question)
	{
		return new ResponseEntity<>(questionDao.save(question), HttpStatus.CREATED);
	}
}
