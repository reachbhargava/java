package spring.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ToDoService {
	private static List<ToDo> todos = new ArrayList<ToDo>();
	private static int todoCount = 3;

	static {
		todos.add(new ToDo(1, "siri", "Learn Spring MVC", new Date(), false));
		todos.add(new ToDo(2, "siri", "Learn Struts", new Date(), false));
		todos.add(new ToDo(3, "siri", "Learn Hibernate", new Date(), false));
	}

	public List<ToDo> retrieveToDos(String user) {
		List<ToDo> filteredToDos = new ArrayList<ToDo>();
		for (ToDo todo : todos) {
			if (todo.getUser().equals(user))
				filteredToDos.add(todo);
		}
		return filteredToDos;
	}

	public void addToDo(String name, String desc, Date targetDate, boolean isDone) {
		todos.add(new ToDo(++todoCount, name, desc, targetDate, isDone));
	}

	public void deleteToDo(int id) {
		Iterator<ToDo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			ToDo todo = iterator.next();
			if (todo.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public ToDo retrieveTodo(int id) {
        for (ToDo todo : todos) {
            if (todo.getId() == id)
                return todo;
        }
        return null;
    }

    public void updateToDo(ToDo todo) {
        todos.remove(todo);
        todos.add(todo);
    }
}