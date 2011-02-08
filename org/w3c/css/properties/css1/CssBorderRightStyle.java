//
// $Id: CssBorderRightStyle.java,v 1.4 2010-01-05 13:49:41 ylafon Exp $
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

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision: 1.4 $
 */
public class CssBorderRightStyle extends CssProperty {

    CssBorderFaceStyle face;

    /**
     * Create a new CssBorderRightStyle
     */
    public CssBorderRightStyle() {
	setByUser();

	face = new CssBorderFaceStyle();
    }

    /**
     * Create a new CssBorderRightStyle with an another CssBorderFaceStyle
     *
     * @param another The another side.
     */
    public CssBorderRightStyle(CssBorderFaceStyle another) {
	setByUser();

	face = another;
    }

    /**
     * Create a new CssBorderRightStyle.
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderRightStyle(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();

	face = new CssBorderFaceStyle(ac, expression);
    }

    public CssBorderRightStyle(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }

    /**
     * Returns the value
     */
    public String getStyle() {
	if(face != null) {
	    return face.getStyle();
	}
	return null;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if(face != null) {
	    return face.toString();
	}
	return "";
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-right-style";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderRight right = ((Css1Style) style).cssBorder.right;
	if (right.style != null)
	    style.addRedefinitionWarning(ac, this);
	right.style = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderRightStyle();
	} else {
	    return ((Css1Style) style).cssBorder.getRight().style;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderRightStyle &&
		face.equals(((CssBorderRightStyle) property).face));
    }

}
