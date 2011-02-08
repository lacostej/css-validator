// $Id: PseudoFunctionNthChild.java,v 1.3 2009-02-12 21:26:36 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.pseudofunctions;

import org.w3c.css.selectors.PseudoFunctionSelector;

/**
 * PseudoFunctionNthChild<br />
 * Created: Sep 2, 2005 4:22:54 PM<br />
 */
public class PseudoFunctionNthChild extends PseudoFunctionSelector {

    public PseudoFunctionNthChild(String name, String value) {
	setName(name);
	setParam(value);
    }

}
