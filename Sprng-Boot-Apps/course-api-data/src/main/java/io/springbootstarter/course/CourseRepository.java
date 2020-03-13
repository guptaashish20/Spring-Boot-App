package io.springbootstarter.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.springbootstarter.topic.Topic;

public interface CourseRepository extends CrudRepository<Course, String> {
	List<Course> findByTopicId(String topicId);
}
