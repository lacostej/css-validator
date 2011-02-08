//
// $Id: CssVerticalAlignMob.java,v 1.4 2010-01-05 13:49:46 ylafon Exp $
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
 *     &nbsp;&nbsp; 'vertical-align'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> baseline | sub | super | top | text-top | middle | bottom
 *   | text-bottom | &lt;percentage&gt; <BR>
 *   <EM>Initial:</EM> baseline<BR>
 *   <EM>Applies to:</EM> inline elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to the 'line-height' of the element
 *   itself<BR>
 *   <P>
 *   The property affects the vertical positioning of the element. One set of
 *   keywords is relative to the parent element:
 *   <DL>
 *     <DT>
 *       'baseline'
 *     <DD>
 *       align the baseline of the element (or the bottom, if the element doesn't
 *       have a baseline) with the baseline of the parent
 *     <DT>
 *       'middle'
 *     <DD>
 *       align the vertical midpoint of the element (typically an image) with the
 *       baseline plus half the x-height of the parent
 *     <DT>
 *       'sub'
 *     <DD>
 *       subscript the element
 *     <DT>
 *       'super'
 *     <DD>
 *       superscript the element
 *     <DT>
 *       'text-top'
 *     <DD>
 *       align the top of the element with the top of the parent element's font
 *     <DT>
 *       'text-bottom'
 *     <DD>
 *       align the bottom of the element with the bottom of the parent element's font
 *   </DL>
 *   <P>
 *   Another set of properties are relative to the formatted line that the element
 *   is a part of:
 *   <DL>
 *     <DT>
 *       'top'
 *     <DD>
 *       align the top of the element with the tallest element on the line
 *     <DT>
 *       'bottom'
 *     <DD>
 *       align the bottom of the element with the lowest element on the line
 *   </DL>
 *   <P>
 *   Using the 'top' and 'bottom' alignment, unsolvable situations can occur where
 *   element dependencies form a loop.
 *   <P>
 *   Percentage values refer to the value of the 'line-height' property of the
 *   element itself. They raise the baseline of the element (or the bottom, if
 *   it has no baseline) the specified amount above the baseline of the parent.
 *   Negative values are possible. E.g., a value of '-100%' will lower the element
 *   so that the baseline of the element ends up where the baseline of the next
 *   line should have been. This allows precise control over the vertical position
 *   of elements (such as images that are used in place of letters) that don't
 *   have a baseline.
 *   <P>
 *   It is expected that a future version of CSS will allow &lt;length&amp;t;
 *   as a value on this property.
 *
 * @version $Revision: 1.4 $
 */
public class CssVerticalAlignMob extends CssProperty
    implements CssTextPropertiesConstants {

    Object value;

    private static int[] hash_values;

    /**
     * Create a new CssVerticalAlignMob
     */
    public CssVerticalAlignMob() {
	value = VERTICALALIGNMOB[0];
    }

    /**
     * Create a new CssVerticalAlign
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssVerticalAlignMob(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	int hash = val.hashCode();

	setByUser();

	if (val instanceof CssIdent) {
	    for (int i = 0; i < VERTICALALIGNMOB.length; i++)
		if (hash_values[i] == hash) {
		    value = VERTICALALIGNMOB[i];
		    expression.next();
		    return;
		}
	    throw new InvalidParamException("value",
					    val.toString(), getPropertyName(), ac);
	} else if (val instanceof CssPercentage) {
	    value = val;
	    expression.next();
	} else if (val instanceof CssLength) {
	    value = val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	    expression.next();
	} else {
	    throw new InvalidParamException("value",
					    val.toString(), getPropertyName(), ac);
	}
    }

    public CssVerticalAlignMob(ApplContext ac, CssExpression expression)
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
	return "vertical-align";
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
	if (style0.cssVerticalAlignMob != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssVerticalAlignMob = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getVerticalAlignMob();
	} else {
	    return ((Css1Style) style).cssVerticalAlignMob;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssVerticalAlignMob && value.equals(((CssVerticalAlignMob) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value.equals(VERTICALALIGNMOB[0]);
    }

    static {
	hash_values = new int[VERTICALALIGNMOB.length];
	for (int i=0; i<VERTICALALIGNMOB.length; i++)
	    hash_values[i] = VERTICALALIGNMOB[i].hashCode();
    }
}
