package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents one issue within a Jira instance.
 */

public class JiraIssue {

    /**
     * The title of the Jira Issue.
     */

    private String title;

    /**
     * The description of the Jira issue.
     */

    private String description;

    /**
     * The amount of story points assigned to the Jira issue.
     */

    private Double storyPoints;

    /**
     * The labels that have been assigned to the Jira issue.
     */

    private List<String> labels;

    /**
     * The assignee that has been assigned to the Jira issue.
     */

    private String assignee;

    /**
     * The issue type. Can be one of the following: Story, Bug, or Task.
     */

    private String type;

    /**
     * The priority of the story. Can be either: Low, Medium, High, Highest, or Critical.
     */

    private String priority;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Double storyPoints) {
        this.storyPoints = storyPoints;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void addLabel(String label) {
        if (this.labels == null) {
            this.labels = new ArrayList<String>();
        }
        this.labels.add(label);
    }
}
