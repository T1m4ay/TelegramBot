package com.example.TelegramBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Task {
    @JsonProperty("id")
    private String id;
    @JsonProperty("assigner_id")
    private String assignerId;
    @JsonProperty("assignee_id")
    private String assigneeId;
    @JsonProperty("project_id")
    private String projectId;
    @JsonProperty("section_id")
    private String sectionId;
    @JsonProperty("parent_id")
    private String parentId;
    @JsonProperty("order")
    private int order;
    @JsonProperty("content")
    private String content;
    @JsonProperty("description")
    private String description;
    @JsonProperty("is_completed")
    private boolean isCompleted;
    @JsonProperty("labels")
    private String[] labels;
    @JsonProperty("priority")
    private int priority;
    @JsonProperty("comment_count")
    private int commentCount;
    @JsonProperty("creator_id")
    private String creatorId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("due")
    private Due due;
    @JsonProperty("url")
    private String url;
    @JsonProperty("duration")
    private String duration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Due getDue() {
        return due;
    }

    public void setDue(Due due) {
        this.due = due;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static class Due {
        @JsonProperty("date")
        private String date;
        @JsonProperty("string")
        private String string;
        @JsonProperty("lang")
        private String lang;
        @JsonProperty("is_recurring")
        private boolean isRecurring;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public boolean isRecurring() {
            return isRecurring;
        }

        public void setRecurring(boolean recurring) {
            isRecurring = recurring;
        }
    }

    // Дополнительные методы, конструкторы и т. д.
}
