package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents one issue with a GitLab instance. Contains several fields for title,
 * description, assignee, labels, and weight.
 */

public class GitLabIssue {

    /**
     * The title of the GitLab issue.
     */

    private String title;

    /**
     * The description of the GitLab issue.
     */

    private String description;

    /**
     * The assignee that the GitLab issue is assigned to.
     */

    private String assignee;

    /**
     * The labels that will be applied to the GitLab issue.
     */

    private List<String> labels;

    /**
     * The weight of the GitLab issue.
     */

    private Double weight;

    /**
     * Creates a new instance of GitLabIssue from an instance of {@link JiraIssue}.
     * @param jiraIssue The Jira issue to convert into a GitLab issue.
     * @return the new instance of GitLabIssue, populated with the fields from JiraIssue.
     */

    public static GitLabIssue fromJiraIssue(JiraIssue jiraIssue) {
        GitLabIssue issue = new GitLabIssue();
        issue.title = jiraIssue.getTitle();
        issue.description = jiraIssue.getDescription().concat(jiraIssue.getStoryPoints() + " points");
        issue.labels = jiraIssue.getLabels();
        issue.addLabel(issue.tranformType(jiraIssue.getType()));
        issue.addLabel(jiraIssue.getPriority());
        issue.weight =  jiraIssue.getStoryPoints();
        return issue;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignee() {
        return assignee;
    }

    public List<String> getLabels() {
        return labels;
    }

    public Double getWeight() {
        return weight;
    }

    private String tranformType(String jiraIssueType) {
        if (jiraIssueType.equals("Task")) {
            return "Technical Task";
        } else if (jiraIssueType.equals("Bug")) {
            return "Defect";
        } else {
            return jiraIssueType;
        }
    }

    private void addLabel(String label) {
        if (this.labels == null) {
            this.labels = new ArrayList<String>();
        }
        this.labels.add(label);
    }
}
