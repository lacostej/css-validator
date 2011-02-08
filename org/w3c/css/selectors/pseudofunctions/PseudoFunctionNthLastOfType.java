// $Id: PseudoFunctionNthLastOfType.java,v 1.3 2009-02-12 21:26:36 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.pseudofunctions;

import org.w3c.css.selectors.PseudoFunctionSelector;

/**
 * PseudoFunctionNthLastOfType<br />
 * Created: Sep 2, 2005 4:24:17 PM<br />
 */
public class PseudoFunctionNthLastOfType extends PseudoFunctionSelector {

    public PseudoFunctionNthLastOfType(String name, String value) {
	setName(name);
	setParam(value);
    }

}
