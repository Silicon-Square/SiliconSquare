package it.siliconsquare.model.component;

import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import java.util.List;

public abstract class Component {

    public abstract List<Attribute> getAllSpecifications();

    /**
     * @return a list of {@link Attribute} which has name and value
     *         Name has the database column name, value has the value of the
     *         attribute <br>
     *         <br>
     *         (e.g. "memory_type" and "DDR3" are the name and value of the
     *         attribute)
     * @see Attribute
     */
    public abstract List<Attribute> getAllCompatibilityParameters();

    public abstract String getTitle();

    public abstract String getAmazonLink();

    public abstract String getImageLink();

    public abstract String getCategory();

    public abstract State getState();

    public abstract int getId();

    public abstract void setId(int id);

    public abstract void setState(State state);

    public abstract String isCompatible(ComponentCategory category, List<Attribute> allParameters);

    public abstract String toString();

    /**
     * @return the object name used for the database table name
     *         e.g. If it is "configuration_memory" it returns "memory"
     */
    public abstract String getObjectTableName();

    /**
     * Checks if the component can be added multiple times to a single configuration
     * 
     * @return
     */
    public boolean canBeAddedMultipleTimes() {
        return true;
    };

    public String getAmazonASIN() {
        if (this.getAmazonLink() == null)
            return "";
        String ASIN = this.getAmazonLink().substring(this.getAmazonLink().lastIndexOf("/") + 1);
        return ASIN;
    }

}
