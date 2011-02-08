//
// $Id: AtRuleImport.java,v 1.1 2009-02-13 14:03:36 ylafon Exp $
//
// (c) COPYRIGHT MIT, Keio University and ERCIM, 2009.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * AtRuleMedia.java
 * $Id: AtRuleImport.java,v 1.1 2009-02-13 14:03:36 ylafon Exp $
 */
package org.w3c.css.parser;

import java.net.URL;

/**
 * This class manages all imports
 *
 * @version $Revision: 1.1 $
 * @author  Philippe Le Hegaret
 */
public class AtRuleImport extends AtRule {

    boolean     is_url   = false;
    String      linkname = null;
    AtRuleMedia media    = null;

    public String keyword() {
	return "import";
    }

    public boolean isEmpty() {
	return true;
    }

    /**
     * The second must be exactly the same of this one
     */
    public boolean canApply(AtRule atRule) {
	return false;
    }

    /**
     * The second must only match this one
     */
    public boolean canMatched(AtRule atRule) {
	return false;
    }


    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	StringBuilder ret  = new StringBuilder();

	ret.append('@');
	ret.append(keyword());
	ret.append(' ');
	if (is_url) {
	    ret.append("url(\'");
	    ret.append(linkname);
	    ret.append("\')");
	} else {
	    ret.append('\"');
	    ret.append(linkname);
	    ret.append('\"');
	} 
	if (media != null && !media.isEmpty()) {
	    ret.append(' ');
	    ret.append(media.getValueString());
	}
	ret.append(';');
	return ret.toString();
    }

    public AtRuleImport(String linkname, boolean is_url, AtRuleMedia media) {
	this.media    = media;
	this.linkname = linkname;
	this.is_url   = is_url; 
    }
}

