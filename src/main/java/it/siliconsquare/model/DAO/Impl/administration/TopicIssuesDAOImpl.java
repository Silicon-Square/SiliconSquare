package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.TopicIssuesDAO;
import it.siliconsquare.model.administration.TopicIssues;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TopicIssuesDAOImpl implements TopicIssuesDAO {
    @Autowired
    private List<TopicIssues> topicIssuesList;
    private String sql = "SELECT * FROM topic_issues ";

    public TopicIssuesDAOImpl() {
        topicIssuesList = getAllTopicIssuesP();
    }

    @Override
    public List<TopicIssues> getAllTopicIssues() {
        return topicIssuesList;
    }

    private List<TopicIssues> getAllTopicIssuesP() {
        return Q2ObjList.fromSelect(TopicIssues.class, sql);
    }

    @Override
    public TopicIssues getTopicIssuesById(int id) {
        return Q2Obj.fromSelect(TopicIssues.class, sql + " WHERE id_topic = ?", id);
    }


}
