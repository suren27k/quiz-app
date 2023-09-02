package com.suren.quiz.service;

import com.suren.quiz.dao.QuestionDao;
import com.suren.quiz.dao.QuizDao;
import com.suren.quiz.entity.Question;
import com.suren.quiz.entity.QuestionWrapper;
import com.suren.quiz.entity.Quiz;
import com.suren.quiz.entity.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionDao questionDao;
	public ResponseEntity<Quiz> createQuiz(String category, Integer noOfQuestions, String title)
	{
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, noOfQuestions);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);

		quiz = quizDao.save(quiz);

		return new ResponseEntity<>(quiz, HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id)
	{
		Optional<Quiz> quiz = quizDao.findById(id);

		List<Question> questionsFromDB = quiz.orElse(new Quiz()).getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();

		for(Question question : questionsFromDB)
		{
			QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestion(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
			questionsForUser.add(questionWrapper);
		}

		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> evaluateResult(Integer id, List<QuizResponse> quizResponses)
	{
		Optional<Quiz> quiz = quizDao.findById(id);

		List<Question> questionsFromDB = quiz.orElse(new Quiz()).getQuestions();

		Integer score = 0;

		for(int  i = 0; i<quizResponses.size();i++)
		{
			if(questionsFromDB.get(i).getCorrectAnswer().equals(quizResponses.get(i).getAnswer()))
			{
				score++;
			}
		}

		return new ResponseEntity<>(score, HttpStatus.OK);
	}
}
