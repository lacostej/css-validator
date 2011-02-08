// $Id: CssRuleList.java,v 1.19 2009-02-25 20:44:49 ylafon Exp $
// Author: Sijtsche de Jong
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.util.Vector;

import org.w3c.css.parser.AtRule;
import org.w3c.css.util.Messages;

public class CssRuleList {

    AtRule atRule;
    String atRuleString;
    Vector rulelist;
    public String pseudopage;
    String indent;

    public CssRuleList() {
	atRule = null;
	atRuleString = new String();
	rulelist = new Vector();
	indent = new String();
    }

    public void addStyleRule(CssStyleRule stylerule) {
	rulelist.addElement(stylerule);
    }

    public Vector getStyleRules() {
	return rulelist;
    }

    public void addAtRule(AtRule atRule) {
	this.atRule = atRule;
	atRuleString = atRule.toString();
    }

    public String getAtRule() {
	return atRuleString;
    }
    
    public String getAtRuleEscaped() {
	return Messages.escapeString(atRuleString);
    }
	
    public boolean isEmpty() {
	return rulelist.isEmpty();
    }

    public String toString() {
	StringBuilder ret = new StringBuilder();
	if (null != atRule && atRule.isEmpty()) {
	    if (!atRuleString.equals("")) {
		ret.append(atRuleString);
		ret.append("\n\n");
	    }
	} else {
	    if (!atRuleString.equals("")) {
		ret.append(atRuleString);
		ret.append(" {\n\n");
	    }
	    for (int i = 0; i < rulelist.size(); i++) {
		ret.append((CssStyleRule) rulelist.elementAt(i));
	    }

	    if (!atRuleString.equals("")) {
		ret.append("}\n");
	    }
	}
	return ret.toString();
    }

}
