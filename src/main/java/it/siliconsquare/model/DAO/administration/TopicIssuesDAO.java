package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.TopicIssues;

import java.util.List;

public interface TopicIssuesDAO {
    public List<TopicIssues> getAllTopicIssues();

    public TopicIssues getTopicIssuesById(int id);
}
