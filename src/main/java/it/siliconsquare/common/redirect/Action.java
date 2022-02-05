package it.siliconsquare.common.redirect;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public enum Action {
    LOGOUT("/logout"),
    ADMIN_PANEL("/admin_panel.permission"),
    SEND_NEW_PASSWORD("/send-new-password"),
    UPDATE_USER("/update"),
    DELETE_USERS("/delete"),

    BAN_USERS("/ban"),
    EDIT_USER("/edit"),
    EDIT_ROLE("/edit-role"),
    EDIT_COMPONENT("component/edit/"),
    DELETE_COMPONENT("/component/delete/"),
    ALL_COMPONENT_CATEGORY("/component/allComponentCategories"),
    ALL_USERS("/allUsers"),
    SBAN_USERS("/sbanuser"),
    ALL_STATES("/allStates.asp"),
    ADD_COMPONENT("/component/add/"),

    GET_ISSUES("/allIssues"),
    CONTACT_US("/contact"),

    PUBLISH_POST("/publishPost"),
    SUGGEST_COMPONENT("/suggest/Component");

    public String value;

    //function that returns the list of all actions values
    public static List<String> getAllActions() {
        return Arrays.asList(values()).stream().map(Action::getValue).collect(java.util.stream.Collectors.toList());
    }

    private Action(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
