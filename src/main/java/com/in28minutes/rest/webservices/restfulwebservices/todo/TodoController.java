package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
	
	private TodoService todos;
	private TodoJpaRepository todos_jpa;
	
	public TodoController(TodoService todos,TodoJpaRepository todos_jpa) {
		super();
		this.todos = todos;
		this.todos_jpa = todos_jpa;
	}

	@GetMapping("users/{username}/todos")
	public List<Todo> getAllUserTodos(@PathVariable String username) {
		// List<Todo> todos = this.todos.findByUsername(username);
		List<Todo> todos = this.todos_jpa.findByUsername(username);
		return todos;
	}
	
	@GetMapping("users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username,@PathVariable Integer id) {
		// Todo response = todos.findById(id);
		Todo response = todos_jpa.findById(id).get();
		return response;
	}
	
	@DeleteMapping("users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable Integer id) {
		todos_jpa.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("users/{username}/todos/{id}")
	public void updateTodo(@PathVariable String username,@PathVariable Integer id, @RequestBody Todo todo) {
		todos_jpa.save(todo);
	}
	
	@PostMapping("users/{username}/todos")
	public Todo updateTodo(@PathVariable String username, @RequestBody Todo todo) {
		todo.setId(null);
		Todo createdTodo = todos_jpa.save(todo);

		return createdTodo;
	}
	
}
