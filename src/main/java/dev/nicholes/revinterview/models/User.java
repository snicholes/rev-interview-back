package dev.nicholes.revinterview.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	@ManyToMany
	@JoinTable(name="saved_question",
		joinColumns=@JoinColumn(name="person_id"),
		inverseJoinColumns=@JoinColumn(name="question_id"))
	private List<Question> savedQuestions;
	
	public User() { super(); }
	
	public User(String firstName, String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
		this.savedQuestions=new LinkedList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Question> getSavedQuestions() {
		return savedQuestions;
	}

	public void setSavedQuestions(List<Question> savedQuestions) {
		this.savedQuestions = savedQuestions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, savedQuestions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName)
				&& Objects.equals(savedQuestions, other.savedQuestions);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", savedQuestions="
				+ savedQuestions + "]";
	}
}
