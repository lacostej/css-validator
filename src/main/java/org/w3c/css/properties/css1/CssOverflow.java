//
// $Id: CssOverflow.java,v 1.3 2010-01-05 13:49:44 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 */
public class CssOverflow extends CssProperty {

    CssValue value;

    private static CssIdent visible = new CssIdent("visible");
    private static CssIdent hidden = new CssIdent("hidden");
    private static CssIdent scroll = new CssIdent("scroll");
    private static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssOverflow
     */
    public CssOverflow() {
	value = visible;
    }

    /**
     * Create a new CssOverflow
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssOverflow(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	setByUser();
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val.equals(visible)) {
	    value = visible;
	    expression.next();
	} else if (val.equals(hidden)) {
	    value = hidden;
	    expression.next();
	} else if (val.equals(visible)) {
	    value = visible;
	    expression.next();
	} else if (val.equals(scroll)) {
	    value = scroll;
	    expression.next();
	} else if (val.equals(auto)) {
	    value = auto;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssOverflow(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "overflow";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssOverflow != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssOverflow = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getOverflow();
	} else {
	    return ((Css1Style) style).cssOverflow;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssOverflow &&
		value.equals(((CssOverflow) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == visible;
    }

}
