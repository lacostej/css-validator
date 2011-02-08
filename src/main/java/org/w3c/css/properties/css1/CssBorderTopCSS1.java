//
// $Id: CssBorderTopCSS1.java,v 1.4 2010-01-05 13:49:41 ylafon Exp $
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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'border-top'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;border-top-width&gt; || &lt;border-style&gt; ||
 *   &lt;color&gt;<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This is a shorthand property for setting the width, style and color of an
 *   element's top border.
 *   <PRE>
 *   H1 { border-bottom: thick solid red }
 * </PRE>
 *   <P>
 *   The above rule will set the width, style and color of the border below the
 *   H1 element. Omitted values will be set to their initial values:
 *   <PRE>
 *   H1 { border-bottom: thick solid }
 * </PRE>
 *   <P>
 *   Since the color value is omitted in the example above, the border color will
 *   be the same as the 'color' value of the element itself.
 *   <P>
 *   Note that while the 'border-style' property accepts up to four values, this
 *   property only accepts one style value.
 *
 * @version $Revision: 1.4 $
 */
public class CssBorderTopCSS1 extends CssProperty implements CssOperator {

    CssBorderTopWidthCSS1 width;
    CssBorderTopStyleCSS1 style;
    CssBorderTopColorCSS1 color;

    /**
     * Create a new CssBorderFaceCSS1
     */
    public CssBorderTopCSS1() {
    }

    /**
     * Create a new CssBorderFaceCSS1
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBorderTopCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = null;
	char op = SPACE;
	boolean find = true;

	if(check && expression.getCount() > 3) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();

	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();
	    if (val == null)
		break;

	    if (op != SPACE)
		throw new InvalidParamException("operator",
						((new Character(op)).toString()),
						ac);
	    if (width == null) {
		try {
		    width = new CssBorderTopWidthCSS1(ac, expression);
		    find = true;
		} catch(InvalidParamException e){
		    //nothing to do, style will test this value
		}
	    }
	    if (!find && style == null) {
		try {
		    style = new CssBorderTopStyleCSS1(ac, expression);
		    find = true;
		} catch(InvalidParamException e){
		    //nothing to do, color will test this value
		}
	    }
	    if (!find && color == null) {
		// throws an exception if the value is not valid
		color = new CssBorderTopColorCSS1(ac, expression);
		find = true;
	    }
	}

	//if some values have not been set, we set them with their default ones
	/*
	if (width == null) {
	    width = new CssBorderTopWidthCSS1();
	}
	if (style == null) {
	    style = new CssBorderTopStyleCSS1();
	}
	if (color == null) {
	    color = new CssBorderTopColorCSS1();
	}
	*/
    }

    public CssBorderTopCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return width;
    }

    /**
     * Returns the color property
     */
    public CssValue getColor() {
	if (color != null) {
	    return color.getColor();
	} else {
	    return null;
	}
    }

    /**
     * Returns the width property
     */
    public CssValue getWidth() {
	if (width != null) {
	    return width.getValue();
	} else {
	    return null;
	}
    }

    /**
     * Returns the style property
     */
    public String getStyle() {
	if (style != null) {
	    return style.getStyle();
	} else {
	    return null;
	}
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	String ret = "";
	if(width != null) {
	    ret += width;
	}
	if(style != null) {
	    if(!ret.equals("")) {
		ret += " ";
	    }
	    ret += style;
	}
	if(color != null) {
	    if(!ret.equals("")) {
		ret += " ";
	    }
	    ret += color;
	}
	return ret;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-top";
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	if(width != null) {
	    width.important = true;
	}
	if(style != null) {
	    style.important = true;
	}
	if(color != null) {
	    color.important = true;
	}
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((width == null || width.important) &&
		(style == null || style.important) &&
		(color == null || color.important));
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
	if ((width != null && style != null &&
	     color != null) &&
	    (getImportant() ||
	     (!width.important &&
	      !style.important &&
	      !color.important))) {
	    printer.print(this);
	} else {
	    if (width != null)
		width.print(printer);
	    if (style != null)
		style.print(printer);
	    if (color != null)
		color.print(printer);
	}

    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if(width != null) {
	    width.addToStyle(ac, style);
	}
	if(this.style != null) {
	    this.style.addToStyle(ac, style);
	}
	if(color != null) {
	    color.addToStyle(ac, style);
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
	    return ((Css1Style) style).getBorderTopCSS1();
	} else {
	    return ((Css1Style) style).cssBorderCSS1.getTop();
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
	if(width != null) {
	    width.setInfo(line, source);
	}
	if(style != null) {
	    style.setInfo(line, source);
	}
	if(color != null) {
	    color.setInfo(line, source);
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
	if (width != null) {
	    width.setSelectors(selector);
	}
	if (style != null) {
	    style.setSelectors(selector);
	}
	if (color != null) {
	    color.setSelectors(selector);
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (property instanceof CssBorderTopCSS1) {
	    CssBorderTopCSS1 top = (CssBorderTopCSS1) property;
	    return (width != null && width.equals(top.width) &&
		    style != null && style.equals(top.style) &&
		    color != null && color.equals(top.color));
	} else {
	    return false;
	}
    }

    void check() {
	if ((style != null)
	    && (style.face.value == 0)) {
	    if (width != null) {
		width.face.value = new CssLength();
	    }
	}
    }
}
