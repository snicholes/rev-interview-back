package dev.nicholes.revinterview.services;

import java.util.Set;

import dev.nicholes.revinterview.models.Question;
import dev.nicholes.revinterview.models.User;

public interface AssociateService {
	// get all questions
	public Set<Question> getAllQuestions();
	
	// get questions by topic
	public Set<Question> getQuestionsByTopic(String topic);
	
	// mark question
	public User markQuestion(User user, Question question);
	
	public User getUserById(int id);
	public Question getQuestionById(int id);
	public Set<User> getAllUsers();
}
