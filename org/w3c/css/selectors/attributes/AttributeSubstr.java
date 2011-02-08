// $Id: AttributeSubstr.java,v 1.3 2008-05-14 10:13:09 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.attributes;

import org.w3c.css.selectors.AttributeSelector;
import org.w3c.css.selectors.Selector;
import org.w3c.css.util.ApplContext;

/**
 * AttributeSubstr<br />
 * Created: Sep 1, 2005 4:35:13 PM<br />
 */
public class AttributeSubstr extends AttributeSelector {

private String value;

    public AttributeSubstr(String name, String value) {
	setName(name);
	this.value = value;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean canApply(Selector other) {
	return true;
    }

    public String toString() {
	return "[" + getName() + "*=\"" + value + "\"]";
    }

    public void applyAttribute(ApplContext ac, AttributeSelector attr) {
	String name = getName();
	if (name.equals(attr.getName())) {
	    // attribute exact knows how to match, delegate...
	    if (attr instanceof AttributeExact) {
		((AttributeExact) attr).applyAttribute(ac, this);
	    } else if (attr instanceof AttributeOneOf) {
		((AttributeOneOf) attr).applyAttribute(ac, this);
	    }
	}
    }

}
