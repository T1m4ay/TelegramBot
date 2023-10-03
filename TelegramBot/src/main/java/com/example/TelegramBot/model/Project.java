package com.example.TelegramBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("comment_count")
    private int commentCount;
    @JsonProperty("order")
    private int order;
    @JsonProperty("color")
    private String color;
    @JsonProperty("is_shared")
    private boolean isShared;;
    @JsonProperty("is_favorite")
    private boolean isFavorite;
    @JsonProperty("is_inbox_project")
    private boolean isInboxProject;
    @JsonProperty("is_team_inbox")
    private boolean isTeamInbox;
    @JsonProperty("view_style")
    private String viewStyle;
    @JsonProperty("url")
    private String url;
    @JsonProperty("parent_id")
    private String parentId;

    // Геттеры и сеттеры

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isInboxProject() {
        return isInboxProject;
    }

    public void setInboxProject(boolean inboxProject) {
        isInboxProject = inboxProject;
    }

    public boolean isTeamInbox() {
        return isTeamInbox;
    }

    public void setTeamInbox(boolean teamInbox) {
        isTeamInbox = teamInbox;
    }

    public String getViewStyle() {
        return viewStyle;
    }

    public void setViewStyle(String viewStyle) {
        this.viewStyle = viewStyle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
