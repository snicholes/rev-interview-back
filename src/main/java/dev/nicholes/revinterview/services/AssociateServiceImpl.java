package dev.nicholes.revinterview.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.nicholes.revinterview.data.QuestionRepository;
import dev.nicholes.revinterview.data.UserRepository;
import dev.nicholes.revinterview.models.Question;
import dev.nicholes.revinterview.models.User;

@Service
public class AssociateServiceImpl implements AssociateService {
	private QuestionRepository questionRepo;
	private UserRepository userRepo;
	
	@Autowired
	public AssociateServiceImpl(QuestionRepository questionRepo, UserRepository userRepo) {
		this.questionRepo=questionRepo;
		this.userRepo=userRepo;
	}

	@Override
	public Set<Question> getAllQuestions() {
		Set<Question> questions = new HashSet<>();
		questions.addAll(questionRepo.findAll());
		return questions;
	}

	@Override
	public Set<Question> getQuestionsByTopic(String topic) {
		return questionRepo.findByTopicIgnoreCase(topic);
	}

	@Override
	@Transactional
	public User markQuestion(User user, Question question) {
		if (user!=null && question!=null) {
			if (user.getSavedQuestions().contains(question)) {
				user.getSavedQuestions().remove(question);
			} else {
				user.getSavedQuestions().add(question);
			}
			user = userRepo.save(user);
			return user;
		}
		return null;
	}

	@Override
	public User getUserById(int id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) return user.get();
		return null;
	}

	@Override
	public Question getQuestionById(int id) {
		Optional<Question> question = questionRepo.findById(id);
		if (question.isPresent()) return question.get();
		return null;
	}

	@Override
	public Set<User> getAllUsers() {
		Set<User> users = new HashSet<>();
		users.addAll(userRepo.findAll());
		return users;
	}

}
