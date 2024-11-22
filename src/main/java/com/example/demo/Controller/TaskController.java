package com.example.demo.Controller;

import com.example.demo.Model.Task;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/tasks/form")
    public String showTaskForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        Task task = id != null ? taskRepository.findById(id).orElse(new Task()) : new Task();
        model.addAttribute("task", task);
        model.addAttribute("categories", categoryRepository.findAll());
        return "task_form";
    }

    @PostMapping("/tasks/save")
    public String saveTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/task_list")
    public String getTaskList(Model model) {
        return "task_list";
    }
}
