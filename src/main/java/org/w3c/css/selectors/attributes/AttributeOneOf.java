// $Id: AttributeOneOf.java,v 1.5 2008-05-14 10:13:09 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.attributes;

import org.w3c.css.selectors.AttributeSelector;
import org.w3c.css.selectors.Selector;
import org.w3c.css.util.ApplContext;

/**
 * AttributeOneOf<br />
 * Created: Sep 1, 2005 4:30:13 PM<br />
 */
public class AttributeOneOf extends AttributeSelector {

    String   value;
    String[] values;

    public AttributeOneOf(String name, String value) {
	setName(name);
	this.value = value;
	this.values = null;
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
	values = null;
    }

    public boolean canApply(Selector other) {
	if (other instanceof AttributeAny) {
	    // [lang~=fr][lang]
	    // return [lang~=fr]
	    return true;
	} else if (other instanceof AttributeExact) {
	    String exact = ((AttributeExact) other).getValue();
	    // [lang~=fr][lang=fr]
	    if(value.equals(exact)) {
		// [lang~=fr][lang=fr]
		return true;
	    }
	    // [lang~=en][lang=fr]
	    return false;
	} else if (other instanceof AttributeOneOf) {
	    return true;
	} else if (other instanceof AttributeBegin) {
	    // [lang=~fr][lang|=fr]
	    return true;
	}
	return false;
    }

    public String toString() {
	return "[" + getName() + "~=\"" + value + "\"]";
    }

    private String[] computeValues() {
	values = value.split("\\s");
	return values;
    }

    public void applyAttribute(ApplContext ac, AttributeSelector attr) {
	String name = getName();
	int i;
	String val;
	boolean ok;

	if (name.equals(attr.getName())) {
	    if (values == null) {
		computeValues();
	    }
	    if (attr instanceof AttributeExact) {
		val = ((AttributeExact) attr).getValue();
		ok = false;
		for (i=0;!ok && i<values.length; i++) {
		    ok = val.equals(values[i]);
		}
		if (!ok) {
		    ac.getFrame().addWarning("incompatible", 
				  new String[] { toString(), attr.toString() });
		}
	    } else if (attr instanceof AttributeBegin) {
		val = ((AttributeBegin) attr).getValue();
		ok = false;
		String pval = val + '-';
		for (i=0;!ok && i<values.length; i++) {
		    ok = values[i].equals(val) || values[i].startsWith(pval);
		}
		if (!ok) {
		    ac.getFrame().addWarning("incompatible", 
				  new String[] { toString(), attr.toString() });
		}
	    } else if (attr instanceof AttributeStart) {
		val = ((AttributeStart) attr).getValue();
		ok = false;
		for (i=0;!ok && i<values.length; i++) {
		    ok = values[i].startsWith(val);
		}
		if (!ok) {
		    ac.getFrame().addWarning("incompatible", 
				  new String[] { toString(), attr.toString() });
		}
	    } else if (attr instanceof AttributeSubstr) {
		val = ((AttributeSubstr) attr).getValue();
		ok = false;
		for (i=0;!ok && i<values.length; i++) {
		    ok = (values[i].indexOf(val) >= 0);
		}
		if (!ok) {
		    ac.getFrame().addWarning("incompatible", 
				  new String[] { toString(), attr.toString() });
		}
	    } else if (attr instanceof AttributeSuffix) {
		val = ((AttributeSuffix) attr).getValue();
		ok = false;
		for (i=0;!ok && i<values.length; i++) {
		    ok = values[i].endsWith(val);
		}
		if (!ok) {
		    ac.getFrame().addWarning("incompatible", 
				  new String[] { toString(), attr.toString() });
		}
	    } else if (attr instanceof AttributeOneOf) {
		AttributeOneOf otherattr = (AttributeOneOf) attr;
		String[] othervalues = otherattr.values;
		int j;
		if (othervalues == null) {
		    othervalues = otherattr.computeValues();
		}
		ok = false;
		for (i=0;!ok && i<values.length; i++) {
		    for(j=0;!ok && j<othervalues.length; j++) {
			ok = values[i].equals(othervalues[j]);
		    }
		}
		if (!ok) {
		    ac.getFrame().addWarning("incompatible", 
				  new String[] { toString(), attr.toString() });
		}
	    }
	}
    }

}
