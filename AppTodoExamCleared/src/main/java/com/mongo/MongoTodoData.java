package com.mongo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public class MongoTodoData {

	@Id
	private String id;
	
    @NotBlank
    private String title;		//Todoリストのタイトル
    
    private List<MongoTodoListData> InsideTodo = new ArrayList<MongoTodoListData>(); //リストの中身 Todo本文など

	public MongoTodoData() {
	}

	public List<MongoTodoListData> getInsideTodo() {
		return InsideTodo;
	}
	
	public void setInsideTodo(MongoTodoListData todo) {
		//repo.save(todo);
		this.InsideTodo.add(todo);
	}
    
	public MongoTodoData(String title) {
		this.title = title;
	}
	

    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
    	this.title = title; 
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String Id){
    	this.id = Id; 
    }

}
