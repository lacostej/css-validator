//
// $Id: CssColor.java,v 1.10 2010-01-05 19:49:50 ylafon Exp $
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
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'color'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;color&gt;<BR>
 *   <EM>Initial:</EM> UA specific<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property describes the text color of an element (often referred to as
 *   the <EM>foreground</EM> color). There are different ways to specify red:
 *   <PRE>
 *   EM { color: red }              /* natural language * /
 *   EM { color: rgb(255,0,0) }     /* RGB range 0-255   * /
 * </PRE>
 * @version $Revision: 1.10 $
 */
public class CssColor extends CssProperty implements CssOperator {

    CssValue color;
    org.w3c.css.values.CssColor tempcolor = new org.w3c.css.values.CssColor();
    String attrvalue = null;

    /**
     * Create a new CssColor
     */
    public CssColor() {
	color = inherit;
    }

    /**
     * Set the value of the property
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssColor(ApplContext ac, CssExpression expression, boolean check)
	throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	setByUser();

	switch(val.getType()) {
	case CssTypes.CSS_IDENT:
	    if (inherit.equals(val)) {
		color = inherit;
		break;
	    }
	    if ("css1".equals(ac.getCssVersion())) {
		color = new org.w3c.css.values.CssColorCSS1(ac,
							    (String) val.get());
	    } else if ("css2".equals(ac.getCssVersion())) {
		color = new org.w3c.css.values.CssColorCSS2(ac, 
							    (String) val.get());
	    } else if ("css3".equals(ac.getCssVersion())){
		color = new org.w3c.css.values.CssColor(ac, (String) val.get());
	    } else {
		color = new org.w3c.css.values.CssColorCSS2(ac, 
							    (String) val.get()); // SVG profiles
	    }
	    //	    color = new org.w3c.css.values.CssColor();
	    break;
	case CssTypes.CSS_COLOR:
	    color = val;
	    break;
	case CssTypes.CSS_FUNCTION:
	    CssFunction attr = (CssFunction) val;
	    CssExpression params = attr.getParameters();

	    if (attr.getName().equals("attr")) {
		CssValue v1 = params.getValue();
		params.next();
		CssValue v2 = params.getValue();
		if ((params.getCount() != 2)) {
		    throw new InvalidParamException("value",
						    params.getValue(),
						    getPropertyName(), ac);
		} else if (v1.getType() != CssTypes.CSS_IDENT) {
		    throw new InvalidParamException("value",
						    params.getValue(),
						    getPropertyName(), ac);

		} else if (!(v2.toString().equals("color"))) {
		    throw new InvalidParamException("value",
						    params.getValue(),
						    getPropertyName(), ac);
		} else {
		    attrvalue = "attr(" + v1 + ", " + v2 + ")";
		}
	    } else if (attr.getName().equals("rgba")) {
		tempcolor.setRGBAColor(params, ac);
		color = tempcolor;
	    } else if (attr.getName().equals("hsl")) {
		tempcolor.setHSLColor(params, ac);
		color = tempcolor;
	    } else if (attr.getName().equals("hsla")) {
		tempcolor.setHSLAColor(params, ac);
		color = tempcolor;
	    } else {
		throw new InvalidParamException("value",
						params.getValue(),
						getPropertyName(), ac);
	    }
	    break;
	default:
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
	expression.next();
    }

    public CssColor(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }

    /**
     * Returns the color
     */
    public org.w3c.css.values.CssColor getColor() {
	if (color.equals(inherit)) {
	    /*
	      System.err.println("[ERROR] org.w3c.css.properties.CssColor");
	      System.err.println("[ERROR] value is inherited");
	    */
	    return null;
	} else {
	    return (org.w3c.css.values.CssColor) color;
	}
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return color.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (attrvalue != null) {
	    return attrvalue;
	} else {
	    return color.toString();
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssColor != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.cssColor = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getColor();
	} else {
	    return ((Css1Style) style).cssColor;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColor &&
		color.equals(((CssColor) property).color));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "color";
    }

}
