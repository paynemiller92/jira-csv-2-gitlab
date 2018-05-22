package manager;

import model.GitLabIssue;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import service.GitLabService;

import java.util.List;
import java.util.Properties;

/**
 * The manager for all things {@link GitLabIssue} related.
 */

public class IssueManager {
    private GitLabService service;
    private Properties properties;

    /**
     * Instantiates a new instance of IssueManger using the configuration
     * found within the config.properties {@link Properties} file.
     * @param retrofit The {@link Retrofit} instance used to execute the creation request.
     * @param properties The {@link Properties} used to configure the creation request.
     */

    public IssueManager(Retrofit retrofit, Properties properties) {
        this.service = retrofit.create(GitLabService.class);
        this.properties = properties;
    }

    public void createGitLabIssues(List<GitLabIssue> issues) {
        for (GitLabIssue issue : issues) {
            service.createNewGitLabIssue(properties.getProperty("projectId"), issue.getTitle(), issue.getDescription(), commaSeparateLabels(issue))
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.code() > 199 && response.code() < 300) {
                                System.out.println("Issue created!");
                            } else {
                                if (response.body() != null) {
                                    System.err.println("Error: ".concat(response.body()));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable throwable) {

                        }
                    });
        }
    }

    private String commaSeparateLabels(GitLabIssue issue) {
        return StringUtils.join(issue.getLabels(), ",");
    }
}
