package io.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	List<Topic> topics = new ArrayList<>(Arrays.asList(
			new Topic("spring", "spring", "Spring Description"),
			new Topic("java", "java", "Java Description"),
			new Topic("Hibernate", "Hibernate", "Hibernate Description")
	));
	
	List<Topic> getAllTopics() {
		return topics;
	}
	
	public Topic getTopic(String id) {
		 return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}

	public void addTopic(Topic topic) {
		topics.add(topic);
	}

	public void updateTopic(String id, Topic topic) {
		for(int i=0;i<topics.size();i++)	{
			if(id.equals(topic.getId()))	{
				topics.set(i, topic);
				return;
			}
		}
	}

	public void deleteTopic(String id) {
		topics.removeIf(t -> t.getId().equals(id));
	}

}
