// $Id: PseudoFactory.java,v 1.7 2009-02-12 21:26:35 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import org.w3c.css.selectors.pseudofunctions.PseudoFunctionContains;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionLang;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNot;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNthChild;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNthLastChild;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNthOfType;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNthLastOfType;

/**
 * PseudoFactory<br />
 * Created: Sep 2, 2005 2:41:09 PM<br />
 */
public class PseudoFactory {

    private static final String[] PSEUDOCLASS_CONSTANTSCSS3 =
    { "link", "visited", "active", "focus", "target",
	"hover", "first-child", "enabled", "disabled",
	"checked", "indeterminate", "root", "last-child",
	"first-of-type", "last-of-type", "only-of-type",
	"only-child", "empty", "valid", "invalid", "required",
	"optional", "read-only", "read-write",
	"default", "in-range", "out-of-range"
    };

    private static final String[] PSEUDOCLASS_CONSTANTSCSS2 =
    {
	"link", "visited", "active", "target", "focus",
	"hover", "first-child"
    };

    private static final String[] PSEUDOCLASS_CONSTANTSTV =
    {
	"link", "visited", "active", "focus", "first-child"
    };

    private static final String[] PSEUDOCLASS_CONSTANTSCSS1 =
    {
	"link", "visited", "active", "target"
    };

    private static final String[] PSEUDOCLASS_CONSTANTS_MOBILE =
    {
	"link", "visited", "active", "focus"
    };

    private static final String[] PSEUDOELEMENT_CONSTANTSCSS3 =
    {
	"first-line", "first-letter", "before", "after",
	"selection", "marker", "value", "choices", "repeat-item",
	"repeat-index"
    };

    private static final String[] PSEUDOELEMENT_CONSTANTSCSS2 =
    {
	"first-line", "first-letter", "before", "after"
    };

    private static final String[] PSEUDOELEMENT_CONSTANTSCSS1 =
    {
	"first-line", "first-letter"
    };

    private static final String[] PSEUDOFUNCTION_CONSTANTSCSS3 =
    {
	"nth-child", "nth-last-child", "nth-of-type", "nth-last-of-type",
	"lang", "contains", "not"
    };

    private static final String[] PSEUDOFUNCTION_CONSTANTSCSS2 =
    {
	"lang"
    };

    /**
     * Returns the possible pseudo-classes for a profile
     * @param profile the profile to get associated pseudo-classes
     * @return the possible pseudo-classes for the profile
     */
    public static String[] getPseudoClass(String profile) {
	if(profile == null || profile.equals("css2")|| profile.equals("css21")) {
	    return PSEUDOCLASS_CONSTANTSCSS2;
	}
	if(profile.equals("css1")) {
	    return PSEUDOCLASS_CONSTANTSCSS1;
	}
	if(profile.equals("css3")) {
	    return PSEUDOCLASS_CONSTANTSCSS3;
	}
	if(profile.equals("tv")) {
	    return PSEUDOCLASS_CONSTANTSTV;
	}
	if(profile.equals("mobile")) {
	    return PSEUDOCLASS_CONSTANTS_MOBILE;
	}
	return null;
    }

    /**
     * Returns the possible pseudo-elements for a profile
     * @param profile the profile to get associated pseudo-elements
     * @return the possible pseudo-elements for the profile
     */
    public static String[] getPseudoElement(String profile) {
	if(profile == null || profile.equals("css2") || profile.equals("css21")
	    || profile.equals("tv")) {
	    return PSEUDOELEMENT_CONSTANTSCSS2;
	}
	if(profile.equals("css1")) {
	    return PSEUDOELEMENT_CONSTANTSCSS1;
	}
	if(profile.equals("css3")) {
	    return PSEUDOELEMENT_CONSTANTSCSS3;
	}
	return null;
    }

    /**
     * Returns the possible pseudo-functions for a profile
     * @param profile the profile to get associated pseudo-functions
     * @return the possible pseudo-functions for the profile
     */
    public static String[] getPseudoFunction(String profile) {
	if(profile == null || profile.equals("css2") || profile.equals("css21") ||
		profile.equals("mobile") || profile.equals("tv")) {
	    return PSEUDOFUNCTION_CONSTANTSCSS2;
	}
	if(profile.equals("css3")) {
	    return PSEUDOFUNCTION_CONSTANTSCSS3;
	}
	return null;
    }

    /**
     * Returns the possible pseudo-elements written as pseudo-classes
     * for a specific profile
     * @param profile the profile to get associated exceptions to the rule
     * @return the possible pseudo-elements/classes for the profile
     */  
    public static String[] getPseudoElementExceptions(String profile) {
	if(profile == null || profile.equals("css2") ||
	   profile.equals("css21") || profile.equals("css3") ||
	   profile.equals("mobile") || profile.equals("tv")) {
	    return PSEUDOELEMENT_CONSTANTSCSS2;
	}
	return null;
    }

    /**
     * Returns a PseudoFunctionSelector based on the name of the 
     * selector
     * @param name, the name of the pseudofun selector
     * @param value, its value
     * @throws InvalidParamException
     */
    public static PseudoFunctionSelector newPseudoFunction(String name, 
					       String value, ApplContext ac) 
	throws InvalidParamException
    {
	if (name == null) {
	    throw new InvalidParamException("pseudo", 
					    "null pseudofunction", ac);
	}
	if (name.equals("lang")) {
	    return new PseudoFunctionLang(name, value);
	}
	if (name.equals("not")) {
	    return new PseudoFunctionNot(name, value);
	}
	if (name.equals("contains")) {
	    return new PseudoFunctionContains(name, value);
	}
	if (name.equals("nth-child")) {
	    return new PseudoFunctionNthChild(name, value);
	}
	if (name.equals("nth-last-child")) {
	    return new PseudoFunctionNthLastChild(name, value);
	}
	if (name.equals("nth-of-type")) {
	    return new PseudoFunctionNthOfType(name, value);
	}
	if (name.equals("nth-last-of-type")) {
	    return new PseudoFunctionNthLastOfType(name, value);
	}
	throw new InvalidParamException("pseudo", 
					":"+name, ac);
    }
}
