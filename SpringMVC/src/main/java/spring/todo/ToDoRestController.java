package spring.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoRestController {

	@Autowired
	ToDoService service;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<ToDo> listAllTodos() {
        List<ToDo> todos = service.retrieveToDos("siri");
        return todos;
    }
	
	@RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
    public ToDo retrieveToDo(@PathVariable int id) {
        ToDo toDo = service.retrieveTodo(id);
        return toDo;
    }
}
