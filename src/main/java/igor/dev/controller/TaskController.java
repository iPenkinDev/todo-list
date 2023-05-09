package igor.dev.controller;

import igor.dev.domain.Status;
import igor.dev.dto.TaskDto;
import igor.dev.service.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping()
    public String getAllTasks(Model model) {
        List<TaskDto> taskList = taskService.getAllTasks();
        model.addAttribute("tasks", taskList);
        return "tasks";
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable int id, Model model) {
        TaskDto taskDto = taskService.getTaskById(id);
        model.addAttribute("task", taskDto);
        return "task";
    }

    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new TaskDto());
        return "create-task";
    }

    @PostMapping("/new")
    public String createTask(@ModelAttribute("task") TaskDto taskDto) {
        taskService.createTask(taskDto);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String showEditTaskForm(@PathVariable int id, Model model) {
        TaskDto taskDto = taskService.getTaskById(id);
        model.addAttribute("task", taskDto);
        model.addAttribute("status", Status.values());
        return "edit-task";
    }

    @PostMapping("/edit")
    public String updateTask(@ModelAttribute TaskDto taskDto) {
        taskService.updateTask(taskDto);
        return "redirect:/tasks";
    }

    @DeleteMapping("/delete/{id}")
    public String removeTask(@PathVariable int id) {
        taskService.removeTask(id);
        return "redirect:/tasks";
    }
}