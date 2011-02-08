//
// $Id: CssColorCSS1.java,v 1.6 2010-01-05 13:49:42 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'color'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;color&gt;<BR>
 *   <EM>Initial:</EM> UA specific<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property describes the text color of an element (often referred to as
 *   the <EM>foreground</EM> color). There are different ways to specify red:
 *   <PRE>
 *   EM { color: red }              /* natural language * /
 *   EM { color: rgb(255,0,0) }     /* RGB range 0-255   * /
 * </PRE>
 * @version $Revision: 1.6 $
 */
public class CssColorCSS1 extends CssProperty {

    CssValue color;

    /**
     * Create a new CssColor
     */
    public CssColorCSS1() {
    }

    /**
     * Set the value of the property
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssColorCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	switch (val.getType()) {
	case CssTypes.CSS_COLOR:
	    color = val;
	    break;
	case CssTypes.CSS_IDENT:
	    color = new org.w3c.css.values.CssColorCSS1(ac, (String) val.get());
	    break;
	default:
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
	expression.next();
    }

    public CssColorCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }

    /**
     * Returns the color
     */
    public org.w3c.css.values.CssColor getColor() {
	return (org.w3c.css.values.CssColor) color;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return color.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if(color != null)
	    return color.toString();
	return "";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssColorCSS1 != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.cssColorCSS1 = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getColorCSS1();
	} else {
	    return ((Css1Style) style).cssColorCSS1;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColorCSS1 &&
		color.equals(((CssColorCSS1) property).color));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "color";
    }

}
