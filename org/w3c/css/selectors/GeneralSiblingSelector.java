// $Id: GeneralSiblingSelector.java,v 1.1 2009-02-12 10:32:52 ylafon Exp $
// (c) COPYRIGHT MIT, ERCIM and Keio, 2009.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

/**
 * GeneralSibling<br />
 */
public class GeneralSiblingSelector implements Selector {

    /**
     * @see Selector#toString()
     */
    public String toString() {
	return " ~ ";
    }

    /**
     * @see Selector#getName()
     */
    public String getName() {
	return "~";
    }

    /**
     * @see Selector#canApply(Selector)
     */
    public boolean canApply(Selector other) {
	return false;
    }

}
