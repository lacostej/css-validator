//
// $Id: CssWordSpacing.java,v 1.4 2010-01-05 13:49:46 ylafon Exp $
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
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'word-spacing'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> normal | &lt;length&gt; <BR>
 *   <EM>Initial:</EM> normal<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> The length unit indicates an addition to the default space between
 *   words.  Values can be negative, but there may be implementation-specific
 *   limits.  The UA is free to select the exact spacing algorithm. The word
 *   spacing may also be influenced by justification (which is a value of the
 *   'align' property).
 *   <PRE>
 *   H1 { word-spacing: 0.4em }
 * </PRE>
 *   <P>
 *   Here, the word-spacing between each word in 'H1' elements would be increased
 *   by '1em'.
 *
 * @version $Revision: 1.4 $
 */
public class CssWordSpacing extends CssProperty {

    private CssValue length;
    private static CssIdent normal = new CssIdent("normal");

    /**
     * Create a new CssWordSpacing.
     */
    public CssWordSpacing() {
	length = normal;
    }

    /**
     * Create a new CssWordSpacing with an expression
     *
     * @param expression The expression
     * @exception InvalidParamException The expression is incorrect
     */
    public CssWordSpacing(ApplContext ac, CssExpression expression,
	boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	setByUser();

	if (val instanceof CssLength) {
	    length = (CssLength) val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    length = ((CssNumber) val).getLength();
	    expression.next();
	} else if (val.equals(inherit)) {
	    length = inherit;
	    expression.next();
	} else if (val.equals(normal)) {
	    length = normal;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssWordSpacing(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return length;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "word-spacing";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return length == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return length.toString();
    }

    /**
     * Adds this property to a style.
     *
     * @param style The style.
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssWordSpacing != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssWordSpacing = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getWordSpacing();
	} else {
	    return ((Css1Style) style).cssWordSpacing;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssWordSpacing &&
		length.equals(((CssWordSpacing) property).length));
    }
}
