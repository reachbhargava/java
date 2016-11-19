package spring.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToDoController {

	@Autowired
	ToDoService service;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String retrieveToDos(ModelMap model) {
		model.put("todos", service.retrieveToDos(getLoggedInUser()));
		return "list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String moveToAddToDo(ModelMap model) {
		// model.put("todo", new ToDo());
		model.addAttribute("todo", new ToDo());
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addToDo(ModelMap model, @ModelAttribute("todo") @Valid ToDo todo, BindingResult result) {
		
		throw new RuntimeException();
		
//		if (result.hasErrors()) {
//			return "todo";
//		}
//		service.addToDo(getLoggedInUser(), todo.getDescription(), new Date(), false);
//		return "redirect:list-todos";
	}

	private String getLoggedInUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
		return name;
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String goToUpdateToDo(ModelMap model, @RequestParam int id) {
		model.addAttribute("todo", service.retrieveTodo(id));
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model, @ModelAttribute("todo") @Valid ToDo todoFromScreen, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		ToDo toDo = service.retrieveTodo(todoFromScreen.getId());
		toDo.setDescription(todoFromScreen.getDescription());
		toDo.setFinishBy(todoFromScreen.getFinishBy());
		service.updateToDo(toDo);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteToDo(ModelMap model, @RequestParam int id) {
		service.deleteToDo(id);
		return "redirect:list-todos";
	}
}
