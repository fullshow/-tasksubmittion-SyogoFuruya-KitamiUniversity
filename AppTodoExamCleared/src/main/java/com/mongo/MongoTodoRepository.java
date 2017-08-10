package com.mongo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTodoRepository extends MongoRepository<MongoTodoData, String> {

	public List<MongoTodoData> findByTitle(MongoTodoListData todo);

	public List<MongoTodoData> findBy(List<MongoTodoListData> todo);
	
}