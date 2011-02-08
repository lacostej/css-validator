//
// $Id: CssMarginSide.java,v 1.4 2010-01-05 13:49:44 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

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
 * @version $Revision: 1.4 $
 */
public abstract class CssMarginSide extends CssProperty {

    CssValue value;

    /**
     * Create a new CssMarginSide
     */
    public CssMarginSide() {
	value = new CssLength();
    }

    /**
     * Create a new CssMarginSide with an another CssMarginSide
     *
     * @param another The another side.
     */
    public CssMarginSide(CssMarginSide another) {
	value = another.value;
    }

    /**
     * Create a new CssMarginSide
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssMarginSide(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    value = val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	    expression.next();
	} else if (val.equals(auto)) {
	    value = auto;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }

    public CssMarginSide(ApplContext ac, CssExpression expression)
    	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the internal CssValue value.
     */
    public CssValue getValue() {
	return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Compares two sides for equality.
     *
     * @param side The other side.
     */
    public boolean equals(CssMarginSide side) {
	return value.equals(side.value);
    }

    /**
     * Is this property contains a default value.
     */
    public boolean isDefault() {
	if (value != auto)
	    return ((Float) value.get()).floatValue() == 0;
	else
	    return false;
    }

    private static CssIdent auto = new CssIdent("auto");
}
