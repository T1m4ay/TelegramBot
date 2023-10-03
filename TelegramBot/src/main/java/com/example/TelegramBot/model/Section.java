package com.example.TelegramBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Section {
    @JsonProperty("id")
    private String id;
    @JsonProperty("project_id")
    private String projectId;
    @JsonProperty("order")
    private int order;
    @JsonProperty("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
