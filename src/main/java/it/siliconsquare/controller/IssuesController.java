package it.siliconsquare.controller;

import com.google.gson.Gson;
import it.siliconsquare.model.administration.TopicIssues;
import it.siliconsquare.provider.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class IssuesController {

    @GetMapping("/allIssues")
    public String allIssues(HttpServletRequest req, HttpServletResponse resp) {
        List<TopicIssues> issues = Database.getInstance().getTopicIssuesDAO().getAllTopicIssues();

        String jsonIssues = new Gson().toJson(issues);

        return jsonIssues;
    }
}
