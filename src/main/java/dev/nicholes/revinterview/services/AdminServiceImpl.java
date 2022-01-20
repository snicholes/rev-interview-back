package dev.nicholes.revinterview.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.nicholes.revinterview.data.QuestionRepository;
import dev.nicholes.revinterview.models.Question;

@Service
public class AdminServiceImpl implements AdminService {
	private QuestionRepository questionRepo;
	
	@Autowired
	public AdminServiceImpl(QuestionRepository questionRepo) {
		this.questionRepo = questionRepo;
	}

	@Override
	public Question addQuestion(Question newQuestion) {
		newQuestion = questionRepo.save(newQuestion);
		return newQuestion;
	}

	@Override
	public Question editQuestion(Question editedQuestion) {
		editedQuestion = questionRepo.save(editedQuestion);
		return editedQuestion;
	}

}
