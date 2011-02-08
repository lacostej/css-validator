//
// $Id: CssLineGridMode.java,v 1.3 2010-01-05 13:49:53 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *
 */

    public class CssLineGridMode extends CssProperty {

	CssValue linegridmode;

	private static CssIdent none = new CssIdent("none");
	private static CssIdent ideograph = new CssIdent("ideograph");
	private static CssIdent all = new CssIdent("all");

	/**
	 * Create a new CssLineGridMode
	 */
	public CssLineGridMode() {
	    linegridmode = none;
	}

	/**
	 * Create a new CssLineGridMode
	 *
	 *
	 */
	public CssLineGridMode(ApplContext ac, CssExpression expression,
		boolean check) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val.equals(none)) {
			linegridmode = none;
			expression.next();
	    }
	    else if (val.equals(ideograph)) {
			linegridmode = ideograph;
			expression.next();
	    }
	    else if (val.equals(all)) {
			linegridmode = all;
			expression.next();
	    }
	    else if (val.equals(inherit)) {
			linegridmode = inherit;
			expression.next();
	    }

	    else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}

	public CssLineGridMode(ApplContext ac, CssExpression expression)
		throws InvalidParamException {
	    this(ac, expression, false);
	}

	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssLineGridMode != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssLineGridMode = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getLineGridMode();
	    } else {
		return ((Css3Style) style).cssLineGridMode;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssLineGridMode &&
		    linegridmode.equals( ((CssLineGridMode) property).linegridmode));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "line-grid-mode";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return linegridmode;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return linegridmode.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return linegridmode.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return linegridmode == none;
	}

    }
