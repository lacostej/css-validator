//
// $Id: CssBorderRightColorCSS2.java,v 1.5 2010-01-05 13:49:41 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision: 1.5 $
 */
public class CssBorderRightColorCSS2 extends CssProperty {

    CssBorderFaceColorCSS2 face;

    /**
     * Create a new CssBorderRightColorCSS2
     */
    public CssBorderRightColorCSS2() {
	face = new CssBorderFaceColorCSS2();
    }

    /**
     * Create a new CssBorderRightColorCSS2 with an another CssBorderFaceColorCSS2
     *
     * @param another The another side.
     */
    public CssBorderRightColorCSS2(CssBorderFaceColorCSS2 another) {

	setByUser();

	face = another;
    }

    /**
     * Create a new CssBorderRightColorCSS2
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderRightColorCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	face = new CssBorderFaceColorCSS2(ac, expression);
    }

    public CssBorderRightColorCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }

    /**
     * @return Returns the face.
     */
    public CssBorderFaceColorCSS2 getFace() {
        return face;
    }

    /**
     * @param face The face to set.
     */
    public void setFace(CssBorderFaceColorCSS2 face) {
        this.face = face;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }

    /**
     * Returns the color of this property
     */
    public CssValue getColor() {
	if(face != null) {
	    return face.getColor();
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
	return "border-right-color";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderRightCSS2 right = ((Css1Style) style).cssBorderCSS2.right;
	if (right.color != null)
	    style.addRedefinitionWarning(ac, this);
	right.color = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderRightColorCSS2();
	} else {
	    return ((Css1Style) style).cssBorderCSS2.getRight().color;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderRightColorCSS2 &&
		face.equals(((CssBorderRightColorCSS2) property).face));
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     */
    public void print(CssPrinterStyle printer) {
	if (!face.isDefault())
	    printer.print(this);
    }
}
