package dev.nicholes.revinterview.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String questionText;
	private String topic;
	private Integer answerId;
	
	public Question() { super(); }
	
	public Question(String questionText, String topic) {
		setQuestionText(questionText);
		setTopic(topic);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answerId, id, questionText, topic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(answerId, other.answerId) && id == other.id
				&& Objects.equals(questionText, other.questionText) && Objects.equals(topic, other.topic);
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + ", topic=" + topic + ", answerId=" + answerId
				+ "]";
	}
}
