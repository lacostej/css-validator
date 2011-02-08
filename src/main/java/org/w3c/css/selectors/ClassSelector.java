// $Id: ClassSelector.java,v 1.2 2005-09-14 15:15:32 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

/**
 * ClassSelector<br />
 * Created: Sep 1, 2005 3:59:42 PM<br />
 */
public class ClassSelector implements Selector {

    String name;

    /**
     * Creates a new class selector given its name
     * @param name the name of this class selector
     */
    public ClassSelector(String name) {
	this.name = name;
    }

    /**
     * @see Selector#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this class selector
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see Selector#toString()
     */
    public String toString() {
	return "." + name;
    }

    /**
     * @see Selector#canApply(Selector)
     */
    public boolean canApply(Selector other) {
	return false;
    }

}
