//
// $Id: CssFloat.java,v 1.4 2010-01-05 13:49:43 ylafon Exp $
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
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'float'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> left | right | none<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>

 *   <P> With the value 'none', the element will be displayed where it appears
 *   in the text. With a value of 'left' ('right') the element will be moved to
 *   the left ('right') and the text will wrap on the right (left) side of the
 *   element.  With a value of 'left' or 'right', the element is treated as
 *   block-level (i.e. the 'display' property is ignored).
 *
 *   <H3>
 *      &nbsp;&nbsp; Floating elements
 *   </H3>

 *   <P> Using the <A HREF="#float">'float'</A> property, an element can be
 *   declared to be outside the normal flow of elements and is then formatted as
 *   a block-level element. For example, by setting the 'float' property of an
 *   image to 'left', the image is moved to the left until the margin, padding
 *   or border of another block-level element is reached. The normal flow will
 *   wrap around on the right side. The margins, borders and padding of the
 *   element itself will be honored, and the margins never collapse with the
 *   margins of adjacent elements.

 *   <P> A floating element is positioned subject to the following constraints:

 *   <OL>

 *     <LI> The left outer edge of a left-floating element may not be to the
 *     left of the left inner edge of its parent element. Analogously for right
 *     floating elements.

 *     <LI> The left outer edge of a left floating element must be to the right
 *     of the right outer edge of every earlier (in the HTML source)
 *     left-floating element or the top of the former must be lower than the
 *     bottom of the latter. Analogously for right floating elements.

 *     <LI> The right outer edge of a left-floating element may not be to the
 *     right of the left outer edge of any right-floating element that is to the
 *     right of it. Analogously for right-floating elements.

 *     <LI> A floating element's top may not be higher than the inner top of its
 *     parent.

 *     <LI> A floating element's top may not be higher than the top of any
 *     earlier floating or block-level element.

 *     <LI> A floating element's top may not be higher than the top of any
 *     <EM>line-box</EM> (see section 4.4) with content that precedes the
 *     floating element in the HTML source.

 *     <LI> A floating element must be placed as high as possible.

 *     <LI> A left-floating element must be put as far to the left as possible,
 *     a right-floating element as far to the right as possible. A higher
 *     position is preferred over one that is further to the left/right.

 *   </OL>
 *   <PRE>
 *   &lt;STYLE TYPE="text/css"&gt;
 *     IMG { float: left }
 *     BODY, P, IMG { margin: 2em }
 *   &lt;/STYLE&gt;

 *   &lt;BODY&gt;
 *     &lt;P&gt;
 *       &lt;IMG SRC=img.gif&gt;
 *       Some sample text that has no other...
 *   &lt;/BODY&gt;
 * </PRE>
 *   <P>
 *   The above example could be formatted as:
 *   <PRE>
 *    ________________________________________
 *   |
 *   |          max(BODY margin, P margin)
 *   |          ______________________________
 *   |    |    |             Some sample text
 *   | B  | P  | IMG margins that has no other
 *   | O  |    |    _____    purpose than to
 *   | D  | m  |   |     |   show how floating
 *   | Y  | a  |   | IMG |   elements are moved
 *   |    | r  |   |     |   to the side of the
 *   | m  | g  |   |_____|   parent element
 *   | a  | i  |             while honoring
 *   | r  | n  |             margins, borders
 *   | g  |    |             and padding. Note
 *   | i  |    |how adjacent vertical margins
 *   | n  |    |are collapsed between non-
 *   |    |    |floating block-level elements.
 * </PRE>
 *   <P> Note that the margin of the 'P' elements enclose the floating 'IMG'
 *   element.
 *   <P> There are two situations when floating elements can overlap with the
 *   margin, border and padding areas of other elements:
 *   <UL>
 *     <LI> when the floating element has a negative margin: negative margins on
 *     floating elements are honored as on other block-level elements.
 *     <LI> when the floating element is wider or higher than the element it is
 *     inside
 *   </UL>
 *
 * @version $Revision: 1.4 $
 */
public class CssFloat extends CssProperty {

    int value;

    private static String[] FLOAT = { "none", "left", "right", "inherit" };
    private static int[] hash_values;

    /**
     * Create a new CssFloat
     */
    public CssFloat() {
	// nothing to do
    }

    /**
     * Create a new CssFloat
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssFloat(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	setByUser();
	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < FLOAT.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}
	throw new InvalidParamException("value", expression.getValue(),
					getPropertyName(), ac);
    }

    public CssFloat(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return FLOAT[value];
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "float";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == FLOAT.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return FLOAT[value];
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssFloat != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssFloat = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFloat();
	} else {
	    return ((Css1Style) style).cssFloat;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFloat &&
		value == ((CssFloat) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == 0;
    }

    static {
	hash_values = new int[FLOAT.length];
	for (int i = 0; i < FLOAT.length; i++)
	    hash_values[i] = FLOAT[i].hashCode();
    }
}
