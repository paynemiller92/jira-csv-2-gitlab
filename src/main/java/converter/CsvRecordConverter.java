package converter;

import model.JiraIssue;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The tool that converts CSV (Comma Separated Value) records into {@link JiraIssue}
 * model objects.
 */

public class CsvRecordConverter {

    private Integer titlePosition;
    private Integer descriptionPosition;
    private Integer typePosition;
    private Integer priorityPosition;
    private List<Integer> labelPositions;
    private Integer pointsPosition;

    /**
     * Instantiates a new instance of CsvRecordConverter, using the positions found
     * within the config.properties {@link Properties} file.
     * @param properties the properties file housing the position information.
     */

    public CsvRecordConverter(Properties properties) {
        this.titlePosition = Integer.valueOf(properties.getProperty("titlePosition"));
        this.descriptionPosition = Integer.valueOf(properties.getProperty("descriptionPosition"));
        this.typePosition = Integer.valueOf(properties.getProperty("typePosition"));
        this.priorityPosition = Integer.valueOf(properties.getProperty("priorityPosition"));
        this.labelPositions = retrievePositionsForProperty(properties, "labelPositions");
        this.pointsPosition = Integer.valueOf(properties.getProperty("pointsPosition"));
    }

    /**
     * Converts the singular CSV (Comma Seperated Value) record into a {@link JiraIssue} model object.
     * @param csvRecord The singular CSV record to be converted.
     * @return the converted {@link JiraIssue} object.
     */

    public JiraIssue convertRecordToJiraIssue(String csvRecord) {
        String[] elements = csvRecord.split(",");
        JiraIssue issue = new JiraIssue();
        issue.setTitle(elements[titlePosition]);
        issue.setDescription(elements[descriptionPosition]);
        issue.setType(elements[typePosition]);
        issue.setPriority(elements[priorityPosition]);
        issue.setStoryPoints(Double.valueOf(elements[pointsPosition]));
        for (Integer position : labelPositions) {
            String label = elements[position];
            if (!label.isEmpty()) {
                issue.addLabel(label);
            }
        }
        return issue;
    }

    private List<Integer> retrievePositionsForProperty(Properties properties, String propertyName) {
        String commaSeperatedList = properties.getProperty(propertyName);
        String[] elements = commaSeperatedList.split(",");
        List<Integer> positions = new ArrayList<Integer>();

        for (String element : elements) {
            positions.add(Integer.valueOf(element));
        }

        return positions;
    }
}
