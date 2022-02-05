package it.siliconsquare.controller;


import it.siliconsquare.connection.mail.EmailSender;
import it.siliconsquare.logger.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ContactController {

    @PostMapping("/contact")
    public void contact(HttpServletRequest req, HttpServletResponse resp) {

        String text = null;
        String receiver = req.getParameter("f-mail-target");
        String nome = req.getParameter("f-name");
        String mex = req.getParameter("f-text");
        String mail = req.getParameter("f-mail");
        String issue = req.getParameter("f-issue");

        if (receiver == null || nome == null || mex == null || mail == null || issue == null) {
            Logger.getInstance().captureMessage("error while receiving email fields");
            return;
        }

        text = "Ciao il mio nome è " + nome +
                 ",\n" + mex +
                 "\n\nPuoi contattarmi alla mail: " + mail;


        EmailSender.sendSimpleMessage(receiver, issue, text);
    }
}
