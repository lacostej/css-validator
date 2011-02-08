//
// $Id: ColorInterpolationFilters.java,v 1.4 2010-01-05 13:49:58 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> auto || sRGB || linearRGB || inherit<BR>
 *  <EM>Initial:</EM>sRGB<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class ColorInterpolationFilters extends CssProperty {

    CssValue interp;
    ApplContext ac;

    CssIdent auto = new CssIdent("auto");
    CssIdent sRGB = new CssIdent("sRGB");
    CssIdent linearRGB = new CssIdent("linearRGB");

    /**
     * Create a new InterpolationFilters
     */
    public ColorInterpolationFilters() {
	//nothing to do
    }

    /**
     * Create a new ColorInterpolationFilters
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ColorInterpolationFilters(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    interp = inherit;
	    expression.next();
	} else if (val.equals(sRGB)) {
	    interp = sRGB;
	    expression.next();
	} else if (val.equals(linearRGB)) {
	    interp = linearRGB;
	    expression.next();
	} else if (val.equals(auto)) {
	    interp = auto;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(),
		    getPropertyName(), ac);
	}
    }

    public ColorInterpolationFilters(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGBasicStyle) style).colorInterpolationFilters != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGBasicStyle) style).colorInterpolationFilters = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGBasicStyle) style).getColorInterpolationFilters();
	} else {
	    return ((SVGBasicStyle) style).colorInterpolationFilters;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ColorInterpolationFilters &&
		interp.equals( ((ColorInterpolationFilters) property).interp));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "color-interpolation-filters";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return interp;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return interp.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return interp.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (interp == linearRGB);
    }

}
