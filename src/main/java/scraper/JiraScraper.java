package scraper;

import converter.CsvRecordConverter;
import model.JiraIssue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * A tool used to convert the Jira exported issues in CSV (comma-separated values) format
 * into {@link JiraIssue} objects.
 */

public class JiraScraper {
    private Properties properties;
    private CsvRecordConverter converter;

    /**
     * Instantiates a new instance of JiraScraper.
     * @param properties The {@link Properties} that will be used to determine which columns
     *                   are associated with which values.
     * @param converter The {@link CsvRecordConverter} that will be used to convert each CSV record
     *                  into a {@link JiraIssue} model object.
     */

    public JiraScraper(Properties properties, CsvRecordConverter converter) {
        this.properties = properties;
        this.converter = converter;
    }

    /**
     * Scrapes all the CSV records within the file specified via {@link Properties}
     * and returns a List of {@link JiraIssue}.
     * @return the list of scraped issues.
     */

    public List<JiraIssue> scrapeJiraIssues() {
        List<JiraIssue> issues = new ArrayList<JiraIssue>();
        try {
            File file = new File("src/main/resources/".concat(properties.getProperty("filename")));
            Scanner input = new Scanner(file);
            input.nextLine(); //Skip Header Line
            Integer lineNumber = 2;
            while (input.hasNext()) {
                try {
                    JiraIssue issue = converter.convertRecordToJiraIssue(input.nextLine());
                    issues.add(issue);
                } catch (Exception e) {
                    System.err.println("There was something wrong with line ".concat(lineNumber.toString()).concat("!"));
                    e.printStackTrace();
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return issues;
    }
}
