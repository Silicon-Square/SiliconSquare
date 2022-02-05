package it.siliconsquare.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.descriptor.web.ResourceBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.siliconsquare.common.redirect.ResourcePath;

@Controller
public class RobotsController {

    @RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
    @ResponseBody
    public String getRobots(HttpServletRequest request) {
        URL url;
        StringBuilder sb = new StringBuilder();
        try {
            url = new URL(ResourcePath.ROOT.value + "/robots.txt");
            Scanner s = new Scanner(url.openStream());
            while (s.hasNext())
                sb.append(s.next() + "\n");
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}