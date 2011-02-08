//
// $Id: CssBorderTopWidthCSS1.java,v 1.4 2010-01-05 13:49:42 ylafon Exp $
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
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'border-top-width'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> thin | medium | thick | &lt;length&gt;<BR>
 *   <EM>Initial:</EM> 'medium'<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property sets the width of an element's top border. The width of the
 *   keyword values are UA dependent, but the following holds: 'thin' &lt;= 'medium'
 *   &lt;= 'thick'.
 *   <P>
 *   The keyword widths are constant throughout a document:
 *   <PRE>
 *   H1 { border: solid thick red }
 *   P  { border: solid thick blue }
 * </PRE>
 *   <P>
 *   In the example above, 'H1' and 'P' elements will have the same border width
 *   regardless of font size. To achieve relative widths, the 'em' unit can be
 *   used:
 *   <PRE>
 *   H1 { border: solid 0.5em }
 * </PRE>
 *   <P>
 *   Border widths cannot be negative.
 * @version $Revision: 1.4 $
 */
public class CssBorderTopWidthCSS1 extends CssProperty {

    CssBorderFaceWidthCSS1 face;

    /**
     * Create a new CssBorderTopWidth
     */
    public CssBorderTopWidthCSS1() {
	face = new CssBorderFaceWidthCSS1();
    }

    /**
     * Create a new CssBorderTopWidth with an another CssBorderFaceWidth
     *
     * @param another The another side.
     */
    public CssBorderTopWidthCSS1(CssBorderFaceWidthCSS1 another) {
	setByUser();

	face = another;
    }

    /**
     * Create a new CssBorderTopWidthCSS1
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopWidthCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	face = new CssBorderFaceWidthCSS1(ac, expression);
    }

    public CssBorderTopWidthCSS1(ApplContext ac, CssExpression expression)
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
     * Return the value of this property
     */
    public CssValue getValue() {
	if(face != null) {
	    return face.getValue();
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
	return "border-top-width";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderTopCSS1 top = ((Css1Style) style).cssBorderCSS1.top;

	if (top.width != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	top.width = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderTopWidthCSS1();
	} else {
	    return ((Css1Style) style).cssBorderCSS1.getTop().width;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopWidthCSS1 &&
		face.equals(((CssBorderTopWidthCSS1) property).face));
    }

}
