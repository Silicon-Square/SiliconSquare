package it.siliconsquare.common;

import it.siliconsquare.model.component.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlVisualizer {

    /**
     * Adds a space between Strings, instead of a dash. <br>
     * Example1: "a-b" -> "a b" <br>
     * Example2: "a-b-c" -> "a b c"
     *
     * @param phrase A String including '-'
     * @return A String including ' ' instead of the '-'
     */
    public static String fromSlug(String phrase) {
        return phrase.replace('-', ' ');
    }

    /**
     * Adds a space between Strings, instead of a dash. <br>
     * Example1: "a-b" -> "a b" <br>
     * Example2: "a-b-c" -> "a b c"
     *
     * @param phrase     A String including '-'
     * @param capitalize true if the first letters of the String should be
     *                   capitalized
     * @return A String including ' ' instead of the '-'
     */
    public static String fromSlug(String phrase, boolean capitalize) {
        if (capitalize)
            return capitalizeAllWords(fromSlug(phrase));
        return fromSlug(phrase);

    }

    /**
     * @param phrase The String to be formatted as a string capitalized
     * @return
     */
    public static String capitalizeAllWords(String phrase) {
        return Arrays.stream(phrase.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
                .collect(Collectors.joining(" "));

    }

    /**
     * Adds a dash between Strings, instead of a space. <br>
     * Example1: "a b" -> "a-b" <br>
     * Example2: "a b c" -> "a-b-c"
     *
     * @param phrase A String including ' '
     * @return A String including '-' instead of strings separated by void space.
     */
    public static String toSlug(String phrase) {
        return phrase.replace(' ', '-');
    }

    /**
     * @param attributes
     * @param bold       true if the The Attribute Name should be bold
     * @return
     */
    public static String toTableHTML(List<Attribute> attributes, boolean bold) {

        StringBuilder sb = new StringBuilder();
        String width = " width=\"300\"";
        int count = 0;
        for (Attribute attribute : attributes) {
            if (attribute.getValue() == null || attribute.getValue().isEmpty() || attribute.getValue().equals("null"))
                continue;
            if (count >= 1)
                width = "";

            String row = "<tr><td" + width + ">" + attribute.getName(true) + "</td><td>"
                    + attribute.getValue().replace("\\n", "<br>") + "</td></tr>";
            sb.append(row);
            count++;

        }
        return sb.toString();
    }

    /**
     * @param string The String to be formatted as a string capitalized and
     *               replacing underscores and spaces with dashes.
     * @return The formatted String.
     */
    public static String toEnum(String string) {
        string = string.replace('-', '_').replace(' ', '_');
        return string.toUpperCase();
    }

    public static boolean containsKeywords(List<Attribute> list, String stringToSearch, String keyword,
            String separator, boolean exactlyMatch) {

        if (list == null || list.isEmpty())
            return false;

        if (stringToSearch == null || stringToSearch.isEmpty())
            return false;

        if (keyword == null || keyword.isEmpty())
            return false;

        if (separator == null || separator.isEmpty())
            separator = "";

        Attribute attributeToConfront = null;
        for (Attribute a : list) {
            if (a.getName() == null || a.getName().isEmpty())
                continue;
            if (a.getName().equalsIgnoreCase(stringToSearch)) {
                attributeToConfront = a;
                break;
            }
        }
        if (attributeToConfront == null)
            return false;

        if (attributeToConfront.getValue() == null || attributeToConfront.getValue().isEmpty())
            return false;

        // transform list to lowercase if !exactlyMatch where the value != null
        if (!exactlyMatch) {
            for (Attribute attr : list) {
                if (attr.getName() != null)
                    attr.setName(attr.getName().toLowerCase());
                if (attr.getValue() != null)
                    attr.setValue(attr.getValue().toLowerCase());
            }
            keyword = keyword.toLowerCase();
            stringToSearch = stringToSearch.toLowerCase();
        }

        String[] values = new String[] { attributeToConfront.getValue() };
        if (attributeToConfront.getValue().contains(separator))
            values = attributeToConfront.getValue().split(separator);

        if (!keyword.contains(separator)) {
            for (String value : values) {
                if (exactlyMatch) {
                    if (value.equalsIgnoreCase(keyword))
                        return true;
                } else if (value.contains(keyword))
                    return true;
            }
        } else {
            String[] keywordsArray = keyword.split("\\" + separator);
            for (String k : keywordsArray) {
                for (String value : values) {
                    if (value == null || value.isEmpty() || k.isEmpty())
                        continue;
                    if (exactlyMatch) {
                        if (value.equalsIgnoreCase(k))
                            return true;
                    } else if (value.contains(k))
                        return true;
                }
            }

        }
        return false;
    }

    /**
     * @param pos The position of the attribute in the list (e.g. "200mm x 140mm" ->
     *            with pos=0 will be 200, with pos=1 will be 140)
     * @return the digits found in a String.
     */
    public static int getDigitsFound(String string, int pos) {
        String[] digitsString = string.split("\\D+");
        List<Integer> digits = new ArrayList<Integer>();
        for (int i = 0; i < digitsString.length; i++)
            if (!digitsString[i].isEmpty())
                digits.add(Integer.parseInt(digitsString[i]));
        int number = -1;
        if (digits.size() > 0 && pos <= digits.size())
            number = digits.get(pos);
        return number;
    }

    /**
     * @return the value of the attribute with the given name from a list of
     *         attributes.
     * @see {@link Attribute}
     */
    public static String getValue(List<Attribute> attributes, String attributeName) {
        Attribute a = null;
        for (Attribute attribute : attributes)
            if (attribute.getName() != null && attribute.getName().equals(attributeName)) {
                a = attribute;
                break;
            }
        if (a == null || a.getValue() == null)
            return null;
        return a.getValue();
    }
}
