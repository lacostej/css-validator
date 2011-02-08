// $Id: PseudoClassSelector.java,v 1.3 2006-04-19 11:28:06 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

/**
 * PseudoClass<br />
 * Created: Sep 1, 2005 3:58:43 PM<br />
 */
public class PseudoClassSelector implements Selector {
    
    String name;

    /**
     * Creates a new pseudo-class given its name
     * @param name the name of this pseudo-class
     */
    public PseudoClassSelector(String name) {
	this.name = name;
    }

    /**
     * @see Selector#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this pseudo-class
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see Selector#toString()
     */
    public String toString() {
	return ":" + name;
    }

    /**
     * @see Selector#canApply(Selector)
     */
    public boolean canApply(Selector other) {
	return false;
    }

}
