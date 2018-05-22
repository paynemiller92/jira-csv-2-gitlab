import converter.CsvRecordConverter;
import manager.IssueManager;
import manager.PropertiesManager;
import manager.RetrofitManager;
import model.GitLabIssue;
import model.JiraIssue;
import retrofit2.Retrofit;
import scraper.JiraScraper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String... args) {
        loadProperties();
        Properties properties = PropertiesManager.getInstance().getProperties();

        CsvRecordConverter csvRecordConverter = new CsvRecordConverter(properties);
        JiraScraper scraper = new JiraScraper(properties, csvRecordConverter);

        List<JiraIssue> jiraIssues = scraper.scrapeJiraIssues();
        List<GitLabIssue> gitLabIssues = convertJiraIssuesIntoGitLabIssues(jiraIssues);

        Retrofit retrofit = RetrofitManager.getInstance(properties).getRetrofit();

        new IssueManager(retrofit, properties).createGitLabIssues(gitLabIssues);
    }

    private static void loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
            PropertiesManager.getInstance().setProperties(properties);
        } catch (FileNotFoundException e) {
            System.err.println("The file config.properties was not found! Please add this file to src/main/resources!");
        } catch (IOException e) {
            System.err.println("There was a problem loading the InputStream!");
            e.printStackTrace();
        }
    }

    private static List<GitLabIssue> convertJiraIssuesIntoGitLabIssues(List<JiraIssue> jiraIssues) {
        List<GitLabIssue> gitLabIssues = new ArrayList<GitLabIssue>();
        for (JiraIssue jiraIssue : jiraIssues) {
            GitLabIssue gitLabIssue = GitLabIssue.fromJiraIssue(jiraIssue);
            gitLabIssues.add(gitLabIssue);
        }
        return gitLabIssues;
    }
}
