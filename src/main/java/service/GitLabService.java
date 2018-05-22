package service;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The network operations surrounding GitLab.
 */

public interface GitLabService {
    /**
     * Creates a new GitLab issue with the specified elements.
     * @param projectId The identifier of the project you wish to add the issue under.
     * @param title The title of the issue
     * @param description The description of the issue
     * @param labels Any labels (comma-separated) that are to be applied to the issue.
     * @return a {@link Call} object representing the request.
     */

    @POST("projects/{projectId}/issues")
    Call<String> createNewGitLabIssue(@Path("projectId") String projectId,
                                            @Query("title") String title,
                                            @Query("description") String description,
                                            @Query("labels") String labels);
}
