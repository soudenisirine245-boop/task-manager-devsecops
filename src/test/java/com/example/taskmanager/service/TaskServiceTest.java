package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService(new TaskRepository());
    }

    @Test
    void createAndListTask() {
        Task created = service.createTask(new Task(null, "Configurer Jenkins", "Pipeline CI/CD", false));
        assertNotNull(created.getId());
        assertEquals("Configurer Jenkins", created.getTitle());
        assertEquals(1, service.getAllTasks().size());
    }

    @Test
    void rejectBlankTitle() {
        Task invalid = new Task(null, "   ", "Description", false);
        assertThrows(IllegalArgumentException.class, () -> service.createTask(invalid));
    }

    @Test
    void updateAndDeleteTask() {
        Task created = service.createTask(new Task(null, "Scanner Docker", "Trivy", false));
        Task updated = service.updateTask(created.getId(), new Task(null, "Scanner Docker", "Trivy OK", true)).orElseThrow();
        assertTrue(updated.isCompleted());
        assertTrue(service.deleteTask(created.getId()));
        assertTrue(service.getTaskById(created.getId()).isEmpty());
    }
}
