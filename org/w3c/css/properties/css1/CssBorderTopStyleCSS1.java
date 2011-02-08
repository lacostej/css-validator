//
// $Id: CssBorderTopStyleCSS1.java,v 1.4 2010-01-05 13:49:42 ylafon Exp $
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
public class CssBorderTopStyleCSS1 extends CssProperty {

    CssBorderFaceStyleCSS1 face;

    /**
     * Create a new CssBorderTopStyleCSS1
     */
    public CssBorderTopStyleCSS1() {
	face = new CssBorderFaceStyleCSS1();
    }

    /**
     * Create a new CssBorderTopStyleCSS1 with an another CssBorderFaceStyleCSS1
     *
     * @param another The another side.
     */
    public CssBorderTopStyleCSS1(CssBorderFaceStyleCSS1 another) {
	setByUser();

	face = another;
    }

    /**
     * Create a new CssBorderTopStyleCSS1
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopStyleCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	face = new CssBorderFaceStyleCSS1(ac, expression);
    }

    public CssBorderTopStyleCSS1(ApplContext ac, CssExpression expression)
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
	return "border-top-style";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderTopCSS1 top = ((Css1Style) style).cssBorderCSS1.getTop();
	if (top.getStyle() != null)
	    style.addRedefinitionWarning(ac, this);
	top.style = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderTopStyleCSS1();
	} else {
	    return ((Css1Style) style).cssBorderCSS1.getTop().style;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopStyleCSS1 &&
		face.equals(((CssBorderTopStyleCSS1) property).face));
    }

}
