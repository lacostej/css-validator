//
// $Id: CssListStyleTypeCSS2.java,v 1.5 2010-01-05 13:49:44 ylafon Exp $
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
 *      &nbsp;&nbsp; 'list-style-type'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> disc | circle | square | decimal | lower-roman | upper-roman
 *   | lower-alpha | upper-alpha | none<BR>
 *   <EM>Initial:</EM> disc<BR>
 *   <EM>Applies to:</EM> elements with 'display' value 'list-item'<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> This property is used to determine the appearance of the list-item
 *   marker if 'list-style-image' is 'none' or if the image pointed to by the
 *   URL cannot be displayed.
 *   <PRE>
 *   OL { list-style-type: decimal }       /* 1 2 3 4 5 etc. * /
					    *   OL { list-style-type: lower-alpha }   /* a b c d e etc. * /
										       *   OL { list-style-type: lower-roman }   /* i ii iii iv v etc. * /
																  *   </PRE>
																  * @version $Revision: 1.5 $ */
public class CssListStyleTypeCSS2 extends CssProperty
    implements CssListStyleConstants {

    int value;

    private static int[] hash_values;

    /**
     * Create a new CssListStyleTypeCSS2
     */
    public CssListStyleTypeCSS2() {
	// nothing to do
    }

    /**
     * Create a new CssListStyleTypeCSS2
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssListStyleTypeCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	setByUser();

	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < LISTSTYLETYPECSS2.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}

	throw new InvalidParamException("value", val, getPropertyName(), ac);
    }

    public CssListStyleTypeCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * @return Returns the value.
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return LISTSTYLETYPECSS2[value];
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "list-style-type";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == (LISTSTYLETYPECSS2.length - 1);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return LISTSTYLETYPECSS2[value];
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssListStyleCSS2 cssListStyle = ((Css1Style) style).cssListStyleCSS2;
	if (cssListStyle.listStyleType != null)
	    style.addRedefinitionWarning(ac, this);
	cssListStyle.listStyleType = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getListStyleTypeCSS2();
	} else {
	    return ((Css1Style) style).cssListStyleCSS2.listStyleType;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssListStyleTypeCSS2 &&
		((CssListStyleTypeCSS2) property).value == value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == 0;
    }

    static {
	hash_values = new int[LISTSTYLETYPECSS2.length];
	for (int i = 0; i < LISTSTYLETYPECSS2.length; i++)
	    hash_values[i] = LISTSTYLETYPECSS2[i].hashCode();
    }
}
