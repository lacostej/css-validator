//
// $Id: MediaMinDeviceAspectRatio.java,v 1.4 2010-01-05 13:49:57 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision: 1.4 $
 */
public class MediaMinDeviceAspectRatio extends CssProperty implements CssOperator {

    String value = "";

    /**
     * Create a new MediaMinDeviceAspectRatio
     */
    public MediaMinDeviceAspectRatio() {
		//empty
    }

    /**
     * Create a new MediaMinDeviceAspectRatio.
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public MediaMinDeviceAspectRatio(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 2) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = null;

	if (expression != null) {
	    val = expression.getValue();
	}

	setByUser();
	char op = SPACE;

	if (val != null) {
	    if (val instanceof CssNumber) {
		if (((CssNumber) val).isInteger()) {
		    value = val.toString();
		} else {
		    throw new InvalidParamException("value", expression.getValue(),
			    getPropertyName(), ac);
		}
	    } else {
		throw new InvalidParamException("value", expression.getValue(),
			getPropertyName(), ac);
	    }

	    op = expression.getOperator();
	    expression.next();
	    val = expression.getValue();

	    if (op == SLASH && val != null) {

		if (val instanceof CssNumber) {
		    if (((CssNumber) val).isInteger()) {
			value += "/" + val.toString();
		    } else {
			throw new InvalidParamException("value", expression.getValue(),
				getPropertyName(), ac);
		    }
		} else {
		    throw new InvalidParamException("value", expression.getValue(),
			    getPropertyName(), ac);
		}


	    } else {
		throw new InvalidParamException("value", expression.getValue(),
			getPropertyName(), ac);
	    }

	    expression.next();

	}
    }

    public MediaMinDeviceAspectRatio(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property.
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property.
     */
    public String getPropertyName() {
	return "min-device-aspect-ratio";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
		return false;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		if (value.equals("")) {
			return null;
		} else {
			return value;
		}
    }


    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css3Style style0 = (Css3Style) style;
	if (style0.mediaMinDeviceAspectRatio != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.mediaMinDeviceAspectRatio = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMediaMinDeviceAspectRatio();
	} else {
	    return ((Css3Style) style).mediaMinDeviceAspectRatio;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof MediaMinDeviceAspectRatio && value.equals(((MediaMinDeviceAspectRatio) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
