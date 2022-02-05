package it.siliconsquare.model.component;

/**
 * Pair of Attribute Name and Value.
 * Both String
 */
public class Attribute {
    String name = "";
    String value = "";

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Attribute(String name, int value) {
        this.name = name;
        this.value = String.valueOf(value);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param bold - true if the value should be bold
     * @return
     */
    public String getName(boolean bold) {
        if (bold)
            return "<b>" + name + "</b>";

        return name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
