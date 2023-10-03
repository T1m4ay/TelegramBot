package com.example.TelegramBot.service;

import com.example.TelegramBot.DTO.TaskDTO;
import com.example.TelegramBot.model.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TodoistService {

    private final String todoistApiUrl = "https://api.todoist.com/rest/v2";

    @Value("${todoist.api.token}")
    private String todoistApiToken;

    private final RestTemplate restTemplate;

    public TodoistService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> createTask(TaskDTO taskDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(todoistApiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"content\": \"" + taskDTO.getTaskName() + "\", \"project_id\": " + "2319449197" + ", \"description\": \"" + taskDTO.getDescriptionText() + "\", \"priority\": " + taskDTO.getPriority() + ", \"section_id\": " + taskDTO.getSectionId() + "}";

        System.out.println(requestBody);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(todoistApiUrl + "/tasks", HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> createTimeForTask(String dateName, String taskName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(todoistApiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = getAllTasks();
        List<Task> tasks = null;
        String taskId = null;

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                tasks = objectMapper.readValue(responseBody, new TypeReference<List<Task>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        for(Task task : tasks){
            if(task.getContent().equals(taskName)){
                taskId = task.getId();
            }
        }

        String requestBody = "{\"due_string\": \"" + dateName + "\"}";

        System.out.println(requestBody + " " + taskId);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(todoistApiUrl + "/tasks/" + taskId, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> getAllTasks() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(todoistApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(todoistApiUrl + "/tasks", HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<String> getAllSections() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(todoistApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(todoistApiUrl + "/sections", HttpMethod.GET, entity, String.class);
    }

}
