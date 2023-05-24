package com.furkanylmz.myFirstApp.controllet.helloController;

import com.furkanylmz.myFirstApp.repository.TodoRepository;
import com.furkanylmz.myFirstApp.model.entıty.Todo;
import com.in28minutes.myFirstApp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes(names = "name")
public class TodoControllerJPA {

    private TodoService todoService;

    private TodoRepository repository;

    public TodoControllerJPA(TodoService todoService, TodoRepository repository) {
        super();
        this.repository = repository;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        String username = getLoggedInUsername(model);
        List<Todo> todos = repository.findByUsername(username);
        model.addAttribute("todos", todos);
        return "Todos";
    }



    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        String username = getLoggedInUsername(model);
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1),false);
        model.put("todo", todo);
        return "addtodo";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo (ModelMap model, @Valid Todo todo, BindingResult result){
        if (result.hasErrors()){
            return "addtodo";
        }
        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        repository.save(todo);
        //todoService.addTodo(username,todo.getDescription(), todo.getTargetDate(),false);
        return "redirect:list-todos";

    }
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        //Delete todo

        repository.deleteById(id);
        return "redirect:list-todos";

    }
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model){
        Todo todo = repository.findById(id).get();
        model.addAttribute("todo",todo);
        return "addtodo";

    }
    @RequestMapping(value="update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()){
            return "addtodo";
        }
        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        repository.save(todo);
        return "redirect:list-todos";
    }
    private String getLoggedInUsername(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
