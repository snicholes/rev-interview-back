package dev.nicholes.revinterview.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.nicholes.revinterview.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	public Set<Question> findByTopicIgnoreCase(String topic);
}
