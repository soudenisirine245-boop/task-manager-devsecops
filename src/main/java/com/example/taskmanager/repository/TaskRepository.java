package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private final Map<Long, Task> storage = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(counter.getAndIncrement());
        }
        storage.put(task.getId(), task);
        return task;
    }

    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}
