package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    public Task createTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Le titre de la tache est obligatoire");
        }
        return repository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updated) {
        return repository.findById(id).map(existing -> {
            if (updated.getTitle() != null && !updated.getTitle().isBlank()) {
                existing.setTitle(updated.getTitle());
            }
            existing.setDescription(updated.getDescription());
            existing.setCompleted(updated.isCompleted());
            return repository.save(existing);
        });
    }

    public boolean deleteTask(Long id) {
        return repository.deleteById(id);
    }
}
