package dev.nicholes.revinterview.controllers;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.nicholes.revinterview.models.Question;
import dev.nicholes.revinterview.models.User;
import dev.nicholes.revinterview.services.AdminService;
import dev.nicholes.revinterview.services.AssociateService;

@RestController
@RequestMapping(path="/questions")
@CrossOrigin(origins="http://localhost:4200")
public class QuestionsController {
	private String appUrl = "http://localhost:8080/questions";
	private AdminService adminServ;
	private AssociateService associateServ;
	
	@Autowired
	public QuestionsController(AdminService adminServ, AssociateService associateServ) {
		this.adminServ=adminServ;
		this.associateServ=associateServ;
	}
	
	// add
	@PostMapping
	public ResponseEntity<Question> addQuestion(@RequestBody Question newQuestion) {
		if (newQuestion!=null) {
			try {
				newQuestion = adminServ.addQuestion(newQuestion);
				URI newQLocation;
				newQLocation = new URI(appUrl + "/" + newQuestion.getId());
				return ResponseEntity.created(newQLocation).build();
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	// edit
	@PutMapping(path="/{qId}")
	public ResponseEntity<Question> editQuestion(@RequestBody Question editedQuestion,
			@PathVariable int qId) {
		if (editedQuestion==null)
			return ResponseEntity.badRequest().build();
		if (editedQuestion.getId() != qId)
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		
		editedQuestion = adminServ.editQuestion(editedQuestion);
		return ResponseEntity.ok(editedQuestion);
	}
	
	// get all
	@GetMapping
	public ResponseEntity<Set<Question>> getQuestions() {
		Set<Question> questions = associateServ.getAllQuestions();
		return ResponseEntity.ok(questions);
	}
	
	// get by topic
	@GetMapping(path="/{topic}")
	public ResponseEntity<Set<Question>> getQuestionsByTopic(@PathVariable String topic) {
		Set<Question> questions = associateServ.getQuestionsByTopic(topic);
		return ResponseEntity.ok(questions);
	}
	
	// mark
	@PutMapping(path="/{qId}/user/{userId}")
	public ResponseEntity<User> markQuestion(@PathVariable int qId,
			@PathVariable int userId) {
		User user = associateServ.getUserById(userId);
		if (user==null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		Question question = associateServ.getQuestionById(qId);
		if (question==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		user = associateServ.markQuestion(user, question);
		return ResponseEntity.ok(user);
	}
}
