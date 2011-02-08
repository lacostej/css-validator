//
// $Id: CssOverflowX.java,v 1.3 2010-01-05 13:49:54 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYLeft 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyLeft statement at
// http://www.w3.org/Consortium/Legal/copyLeft-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

public class CssOverflowX extends CssProperty {

    CssValue overflowX;

    static CssIdent auto = new CssIdent("auto");
    static CssIdent visible = new CssIdent("visible");
    static CssIdent hidden = new CssIdent("hidden");
    static CssIdent scroll = new CssIdent("scroll");
    static CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssOverflowX
     */
    public CssOverflowX() {
	// nothing to do
    }

    /**
     * Create a new CssOverflowX
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssOverflowX(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    overflowX = val;
	    expression.next();
	} else if (val.equals(visible)) {
	    overflowX = val;
	    expression.next();
	} else if (val.equals(hidden)) {
	    overflowX = val;
	    expression.next();
	} else if (val.equals(scroll)) {
	    overflowX = val;
	    expression.next();
	} else if (val.equals(auto)) {
	    overflowX = val;
	    expression.next();
	} else if (val.equals(initial)) {
	    overflowX = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}
    }

    public CssOverflowX(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssOverflowX != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssOverflowX = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getOverflowX();
	}
	else {
	    return ((Css3Style) style).cssOverflowX;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssOverflowX &&
		overflowX.equals(((CssOverflowX) property).overflowX));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "overflow-x";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return overflowX;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return overflowX.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return overflowX.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {

	return overflowX == visible;
    }

}
