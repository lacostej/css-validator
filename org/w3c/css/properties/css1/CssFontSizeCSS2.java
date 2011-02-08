//
// $Id: CssFontSizeCSS2.java,v 1.4 2010-01-05 13:49:43 ylafon Exp $
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
 *     &nbsp;&nbsp; 'font-size'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;absolute-size&gt; | &lt;relative-size&gt; |
 *   &lt;length&gt; | &lt;percentage&gt;<BR>
 *   <EM>Initial:</EM> medium<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> relative to parent element's font size<BR>
 *   <DL>
 *     <DT>
 *       <B>&lt;absolute-size&gt;</B>
 *     <DD> An &lt;absolute-size&gt; keyword is an index to a table of font
 *     sizes computed and kept by the UA. Possible values are: [ xx-small |
 *     x-small | small | medium | large | x-large | xx-large ]. On a computer
 *     screen a scaling factor of 1.5 is suggested between adjacent indexes; if
 *     the 'medium' font is 10pt, the 'large' font could be 15pt. Different
 *     media may need different scaling factors. Also, the UA should take the
 *     quality and availability of fonts into account when computing the
 *     table. The table may be different from one font family to another.
 *     <DT>
 *       <B>&lt;relative-size&gt;</B>
 *     <DD> A &lt;relative-size&gt; keyword is interpreted relative to the table
 *     of font sizes and the font size of the parent element. Possible values
 *     are: [ larger | smaller ]. For example, if the parent element has a font
 *     size of 'medium', a value of 'larger' will make the font size of the
 *     current element be 'large'.  If the parent element's size is not close to
 *     a table entry, the UA is free to interpolate between table entries or
 *     round off to the closest one. The UA may have to extrapolate table values
 *     if the numerical value goes beyond the keywords.
 *   </DL>
 *   <P> Length and percentage values should not take the font size table into
 *   account when calculating the font size of the element.
 *   <P> Negative values are not allowed.
 *   <P> On all other properties, 'em' and 'ex' length values refer to the font
 *   size of the current element. On the 'font-size' property, these length
 *   units refer to the font size of the parent element.
 *   <P>
 *   Note that an application may reinterpret an explicit size, depending on the
 *   context. E.g., inside a VR scene a font may get a different size because
 *   of perspective distortion.
 *   <P>
 *   Examples:
 *   <PRE>
 *   P { font-size: 12pt; }
 *   BLOCKQUOTE { font-size: larger }
 *   EM { font-size: 150% }
 *   EM { font-size: 1.5em }
 *   </PRE>
 *   <P>
 *   If the suggested scaling factor of 1.5 is used, the last three declarations
 *   are identical.
 * @version $Revision: 1.4 $
 */
public class CssFontSizeCSS2 extends CssProperty implements CssFontConstantCSS2 {

    int value;
    CssValue cssValue;

    /**
     * Create a new FontSizeCSS2
     */
    public CssFontSizeCSS2() {
	value = 3; // default value is medium
    }

    /**
     * Creates a new CssFontSizeCSS2
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */
    public CssFontSizeCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	setByUser();
	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i=0; i<hash_values.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    cssValue = null;
		    expression.next();
		    return;
		}
	    }
	} else if (val instanceof CssPercentage) {
	    float num = ((Float) val.get()).floatValue();
	    if (num < 0) {
		throw new InvalidParamException("negative-value",
						val.toString(), ac);
	    }
	    this.cssValue = val;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    CssValue length = ((CssNumber) val).getLength();
	    if (length != null) {
		cssValue = length;
		expression.next();
		return;
	    }
	} else if (val instanceof CssLength) {
	    float f = ((Float) val.get()).floatValue();
	    if (f >= 0) {
		cssValue = val;
		expression.next();
		return;
	    } else {
		throw new InvalidParamException("negative-value",
						val.toString(), ac);
	    }
	}

	throw new InvalidParamException("value",
					val, getPropertyName(), ac);
    }

    public CssFontSizeCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the current value
     */
    public Object get() {
	if (cssValue != null)
	    return cssValue;
	else
	    return FONTSIZE[value];
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == FONTSIZE.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (cssValue != null)
	    return cssValue.toString();
	else
	    return FONTSIZE[value];
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font-size";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssFontCSS2 cssFont = ((Css1Style) style).cssFontCSS2;
	if (cssFont.fontSize != null)
	    style.addRedefinitionWarning(ac, this);
	cssFont.fontSize = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFontSizeCSS2();
	} else {
	    return ((Css1Style) style).cssFontCSS2.fontSize;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (cssValue == null)
	    return (property instanceof CssFontSizeCSS2 &&
		    ((CssFontSizeCSS2) property).value == value);
	else
	    return (property instanceof CssFontSizeCSS2 &&
		    ((CssFontSizeCSS2) property).cssValue.equals(cssValue));
    }

    private static int[] hash_values;

    static {
	hash_values = new int[FONTSIZE.length];
	for (int i=0; i<FONTSIZE.length; i++)
	    hash_values[i] = FONTSIZE[i].hashCode();
    }
}
