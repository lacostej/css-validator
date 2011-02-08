//
// $Id: CssTextUlStyle.java,v 1.3 2010-01-05 13:49:55 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
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
 *  <P>
 *  <EM>Value:</EM>  none | solid | double | dotted | thick | dashed | dot-dash | dot-dot-dash | wave | single-accounting | double-accounting | inherit<BR>
 *  <EM>Initial:</EM>solid<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property specifies the underline style to use when 'text-decoration'
 *  is set to 'underline'.
 */

public class CssTextUlStyle extends CssProperty {

    CssValue ulstyle;

    static CssIdent solid = new CssIdent("solid");

    private static String[] values = {
	"none", "solid", "double", "dotted", "thick", "dashed", "dot-dash",
	"dot-dot-dash", "wave", "inherit"
    };

    /**
     * Create a new CssTextUlStyle
     */
    public CssTextUlStyle() {
	ulstyle = solid;
    }

    /**
     * Create a new CssTextUlStyle
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextUlStyle(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (;i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		ulstyle = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}

    }

    public CssTextUlStyle(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextUlStyle != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextUlStyle = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextUlStyle();
	}
	else {
	    return ((Css3Style) style).cssTextUlStyle;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextUlStyle &&
		ulstyle.equals(((CssTextUlStyle) property).ulstyle));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-underline-style";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return ulstyle;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return ulstyle.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return ulstyle.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return ulstyle == solid;
    }

}
