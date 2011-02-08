//
// $Id: AtRuleFontFace.java,v 1.4 2007-11-26 05:07:17 ot Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * AtRuleFontFace.java
 * $Id: AtRuleFontFace.java,v 1.4 2007-11-26 05:07:17 ot Exp $
 */
package org.w3c.css.parser;

/**
 * This class manages all media defines by CSS2
 *
 * @version $Revision: 1.4 $
 * @author  Philippe Le Hegaret
 */
public class AtRuleFontFace extends AtRule {
    static int internal = 0;
    int hash;

    /**
     * Create a new AtRuleFontFace
     */
    public AtRuleFontFace() {
       hash = ++internal;
    }


    /**
     * Returns the at rule keyword
     */
    public String keyword() {
	return "font-face";
    }

    /**
     * The second must be exactly the same of this one
     */
    public boolean canApply(AtRule atRule) {
	return (atRule instanceof AtRuleFontFace);
    }

    /**
     * Return true if other is an instance of AtRUleFontFace
     */
    public boolean equals(Object other) {
	return (other instanceof AtRuleFontFace);
    }

    /**
     * The second must only match this one
     */
    public boolean canMatched(AtRule atRule) {
	return (atRule instanceof AtRuleFontFace);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return "@" + keyword();
    }

    public int hashCode() {
	return hash;
    }

}
