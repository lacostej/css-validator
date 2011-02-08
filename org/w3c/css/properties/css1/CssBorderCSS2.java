//
// $Id: CssBorderCSS2.java,v 1.5 2010-01-05 13:49:40 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision: 1.5 $
 */
public class CssBorderCSS2 extends CssProperty {

    CssBorderTopCSS2 top;
    CssBorderRightCSS2 right;
    CssBorderBottomCSS2 bottom;
    CssBorderLeftCSS2 left;

    /**
     * Create a new CssBorderFaceCSS2
     */
    public CssBorderCSS2() {
	top = new CssBorderTopCSS2();
	right = new CssBorderRightCSS2();
	bottom = new CssBorderBottomCSS2();
	left = new CssBorderLeftCSS2();
    }

    /**
     * Create a new CssBorderFaceCSS2
     *
     * @param value The value for this property
     * @exception InvalidParamException The value is incorrect
     */
    public CssBorderCSS2(ApplContext ac, CssExpression value,
	    boolean check) throws InvalidParamException {

	if(check && value.getCount() > 3) {
	     throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = value.getValue();

	setByUser();

	top = new CssBorderTopCSS2(ac, value);

	if (val == value.getValue()) {
	    throw new InvalidParamException("value",
					    value.getValue(),
					    getPropertyName(), ac);
	}
	right = new CssBorderRightCSS2();
	bottom = new CssBorderBottomCSS2();
	left = new CssBorderLeftCSS2();

	CssBorderTopWidthCSS2 w = top.width;
	CssBorderTopStyleCSS2 s = top.style;
	CssBorderTopColorCSS2 c = top.color;

	if(w != null) {
	    right.width  =
		new CssBorderRightWidthCSS2((CssBorderFaceWidthCSS2) w.get());
	    left.width =
		new CssBorderLeftWidthCSS2((CssBorderFaceWidthCSS2) w.get());
	    bottom.width =
		new CssBorderBottomWidthCSS2((CssBorderFaceWidthCSS2) w.get());
	}
	if(s != null) {
	    right.style =
		new CssBorderRightStyleCSS2((CssBorderFaceStyleCSS2) s.get());
	    left.style =
		new CssBorderLeftStyleCSS2((CssBorderFaceStyleCSS2) s.get());
	    bottom.style =
		new CssBorderBottomStyleCSS2((CssBorderFaceStyleCSS2) s.get());
	}
	if(c != null) {
	    right.color =
		new CssBorderRightColorCSS2((CssBorderFaceColorCSS2) c.get());
	    left.color =
		new CssBorderLeftColorCSS2((CssBorderFaceColorCSS2) c.get());
	    bottom.color =
		new CssBorderBottomColorCSS2((CssBorderFaceColorCSS2) c.get());
	}
    }

    public CssBorderCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return top.get();
    }

    /**
     * Returns the top property
     */
    public CssBorderTopCSS2 getTop() {
	return top;
    }

    /**
     * Returns the right property
     */
    public CssBorderRightCSS2 getRight() {
	return right;
    }

    /**
     * Returns the bottom property
     */
    public CssBorderBottomCSS2 getBottom() {
	return bottom;
    }

    /**
     * Returns the left property
     */
    public CssBorderLeftCSS2 getLeft() {
	return left;
    }

    /**
     * @param bottom The bottom to set.
     */
    public void setBottom(CssBorderBottomCSS2 bottom) {
        this.bottom = bottom;
    }

    /**
     * @param left The left to set.
     */
    public void setLeft(CssBorderLeftCSS2 left) {
        this.left = left;
    }

    /**
     * @param right The right to set.
     */
    public void setRight(CssBorderRightCSS2 right) {
        this.right = right;
    }

    /**
     * @param top The top to set.
     */
    public void setTop(CssBorderTopCSS2 top) {
        this.top = top;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if(top != null) {
	    return top.toString();
	}
	return "";
    }

    public boolean equals(CssProperty property) {
	return false; // FIXME
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border";
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	if(top != null) {
	    top.setImportant();
	}
	if(right != null) {
	    right.setImportant();
	}
	if(left != null) {
	    left.setImportant();
	}
	if(bottom != null) {
	    bottom.setImportant();
	}
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() { // FIXME
	return (top.getImportant() &&
		right.getImportant() &&
		left.getImportant() &&
		bottom.getImportant());
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
	int printMacro = 0;

	if ((top.width != null && bottom.width != null &&
	     right.width != null && left.width != null) &&
	    ((top.width.important && bottom.width.important &&
	      right.width.important && left.width.important) ||
	     (!top.width.important && !bottom.width.important &&
	      !right.width.important && !left.width.important))) {
	    CssBorderWidthCSS2 width = new CssBorderWidthCSS2(top.width, bottom.width,
						      right.width, left.width);
	    if (top.important) {
		width.setImportant();
	    }
	    printMacro = 1;
	    width.print(printer);
	}
	if ((top.style != null && bottom.style != null &&
	     right.style != null && left.style != null) &&
	    ((top.style.important && bottom.style.important &&
	      right.style.important && left.style.important) ||
	     (!top.style.important && !bottom.style.important &&
	      !right.style.important && !left.style.important))) {
	    CssBorderStyleCSS2 style = new CssBorderStyleCSS2(top.style, bottom.style,
						      right.style, left.style);
	    if (top.important) {
		style.setImportant();
	    }
	    printMacro |= 2;
	    style.print(printer);
	}
	if ((top.color != null && bottom.color != null &&
	     right.color != null && left.color != null) &&
	    ((top.color.important && bottom.color.important &&
	      right.color.important && left.color.important) ||
	     (!top.color.important && !bottom.color.important &&
	      !right.color.important && !left.color.important))) {
	    CssBorderColorCSS2 color = new CssBorderColorCSS2(top.color, bottom.color,
						      right.color, left.color);
	    if (top.important) {
		color.setImportant();
	    }
	    printMacro |= 4;
	    color.print(printer);
	}

	if (printMacro == 0) {
	    top.print(printer);
	    right.print(printer);
	    bottom.print(printer);
	    left.print(printer);
	} else {
	    if ((printMacro & 1) == 0) {
		if (top.width != null) top.width.print(printer);
		if (right.width != null) right.width.print(printer);
		if (bottom.width != null) bottom.width.print(printer);
		if (left.width != null) left.width.print(printer);
	    }
	    if ((printMacro & 2) == 0) {
		if (top.style != null) top.style.print(printer);
		if (right.style != null) right.style.print(printer);
		if (bottom.style != null) bottom.style.print(printer);
		if (left.style != null) left.style.print(printer);
	    }
	    if ((printMacro & 4) == 0) {
		if (top.color != null) top.color.print(printer);
		if (right.color != null) right.color.print(printer);
		if (bottom.color != null) bottom.color.print(printer);
		if (left.color != null) left.color.print(printer);
	    }
	}

    }

    /**
     * Set the context.
     * Overrides this method for a macro
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     * @see org.w3c.css.css.StyleSheetParser#handleRule
     */
    public void setSelectors(CssSelectors selector) {
	super.setSelectors(selector);
	if (top != null) {
	    top.setSelectors(selector);
	}
	if (right != null) {
	    right.setSelectors(selector);
	}
	if (bottom != null) {
	    bottom.setSelectors(selector);
	}
	if (left != null) {
	    left.setSelectors(selector);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if(top != null) {
	    top.addToStyle(ac, style);
	}
	if(right != null) {
	    right.addToStyle(ac, style);
	}
	if(left != null) {
	    left.addToStyle(ac, style);
	}
	if(bottom != null) {
	    bottom.addToStyle(ac, style);
	}
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderCSS2();
	} else {
	    return ((Css1Style) style).cssBorderCSS2;
	}
    }

    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line The line number where this property is defined
     * @param source The source file where this property is defined
     */
    public void setInfo(int line, String source) {
	super.setInfo(line, source);
	if(top != null) {
	    top.setInfo(line, source);
	}
	if(right != null) {
	    right.setInfo(line, source);
	}
	if(left != null) {
	    left.setInfo(line, source);
	}
	if(bottom != null) {
	    bottom.setInfo(line, source);
	}
    }

    void check() {
	if(top != null) {
	    top.check();
	}
	if(bottom != null) {
	    bottom.check();
	}
	if(right != null) {
	    right.check();
	}
	if(left != null) {
	    left.check();
	}
    }
}
