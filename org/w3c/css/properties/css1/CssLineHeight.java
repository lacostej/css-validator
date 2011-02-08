//
// $Id: CssLineHeight.java,v 1.5 2010-01-05 13:49:43 ylafon Exp $
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
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'line-height'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> normal | &lt;number&gt; | &lt;length&gt; |
 *   &lt;percentage&gt;<BR>
 *   <EM>Initial:</EM> normal<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> relative to the font size of the element itself<BR>
 *   <P>
 *   The property sets the distance between two adjacent lines' baselines.
 *   <P> When a numerical value is specified, the line height is given by the
 *   font size of the current element multiplied with the numerical value. This
 *   differs from a percentage value in the way it inherits: when a numerical
 *   value is specified, child elements will inherit the factor itself, not the
 *   resultant value (as is the case with <A
 *   HREF="#percentage-units">percentage</A> and other units).
 *   <P>
 *   Negative values are not allowed.
 *   <P>
 *   The three rules in the example below have the same resultant line height:
 *   <PRE>
 *   DIV { line-height: 1.2; font-size: 10pt }     /* number * /
 *   DIV { line-height: 1.2em; font-size: 10pt }   /* length * /
 *   DIV { line-height: 120%; font-size: 10pt }    /* percentage * /
 * </PRE>
 *
 * @version $Revision: 1.5 $
 */
public class CssLineHeight extends CssProperty {


    private CssValue value;
    private static CssIdent normal = new CssIdent("normal");
    private static CssIdent number = new CssIdent("number");
    private static CssIdent none = new CssIdent("none");
    private static CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssLineHeight
     */
    public CssLineHeight() {
	value = normal;
    }

    /**
     * Creates a new CssLineHeight
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssLineHeight(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	setByUser();

	if (val instanceof CssNumber || val instanceof CssLength ||
	        val instanceof CssPercentage) {
	    float v = ((Number) val.get()).floatValue();
	    if (v >= 0) {
		value = val;
		expression.next();
		return;
	    } else {
		throw new InvalidParamException("negative-value",
						Float.toString(v), ac);
	    }
	} else if (inherit.equals(val)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (normal.equals(val)) {
	    value = normal;
	    expression.next();
	    return;
	} else if (number.equals(val)) {
		value = number;
		expression.next();
		return;
	} else if (none.equals(val)) {
		value = none;
		expression.next();
		return;
	} else if (initial.equals(val)) {
		value = initial;
		expression.next();
		return;
	}

	throw new InvalidParamException("value", expression.getValue(),
					getPropertyName(), ac);
    }

    public CssLineHeight(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	if (value == null)
	    return normal;
	return value;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "line-height";
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
	CssFont cssFont = ((Css1Style) style).cssFont;
	if (cssFont.lineHeight != null)
	    style.addRedefinitionWarning(ac, this);
	cssFont.lineHeight = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getLineHeight();
	} else {
	    return ((Css1Style) style).cssFont.lineHeight;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLineHeight &&
		((CssLineHeight) property).value == value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == normal;
    }

}
