package dev.nicholes.revinterview.services;

import dev.nicholes.revinterview.models.Question;

public interface AdminService {
	// add new question
	public Question addQuestion(Question newQuestion);
	
	// edit question
	public Question editQuestion(Question editedQuestion);
}
