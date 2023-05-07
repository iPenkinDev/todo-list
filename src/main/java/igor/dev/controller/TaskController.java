package igor.dev.controller;

import igor.dev.dto.TaskDto;
import igor.dev.service.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        List<TaskDto> taskList = taskService.getAllTasks();
        model.addAttribute("tasks", taskList);
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getTaskById(@PathVariable int id, Model model) {
        TaskDto taskDto = taskService.getTaskById(id);
        model.addAttribute("task", taskDto);
        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new TaskDto());
        return "create-task";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute("task") TaskDto taskDto) {
        taskService.createTask(taskDto);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String showEditTaskForm(@PathVariable int id, Model model) {
        TaskDto taskDto = taskService.getTaskById(id);
        model.addAttribute("task", taskDto);
        return "edit-task";
    }

    @PutMapping("/tasks/{id}")
    public String updateTask(@PathVariable int id, @ModelAttribute("task") TaskDto taskDto) {
        taskService.updateTask(taskDto, id);
        return "redirect:/tasks";
    }

    @DeleteMapping("/tasks/{id}")
    public String removeTask(@PathVariable int id) {
        taskService.removeTask(id);
        return "redirect:/tasks";
    }
}