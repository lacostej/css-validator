//
// $Id: CssPaddingSideCSS3.java,v 1.4 2010-01-05 13:49:54 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

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
public abstract class CssPaddingSideCSS3 extends CssProperty {

    CssValue value;
    CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssPaddingSideCSS3.
     */
    public CssPaddingSideCSS3() {
	value = new CssLength();
    }

    /**
     * Create a new CssPaddingSideCSS3 with an another CssPaddingSideCSS3
     *
     * @param another An another side.
     */
    public CssPaddingSideCSS3(CssPaddingSideCSS3 another) {
	value = another.value;
    }

    /**
     * Create a new CssPaddingSideCSS3
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssPaddingSideCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val.equals(initial)) {
	    value = initial;
	    expression.next();
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    value = val;
	    float v = ((Float) val.get()).floatValue();
	    if (v < 0)
		throw new InvalidParamException("negative-value",
						Float.toString(v), ac);
	    expression.next();
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	    expression.next();
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }

    public CssPaddingSideCSS3(ApplContext ac, CssExpression expression)
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
     * Returns the internal value
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
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return ((Float) value.get()).floatValue() == 0;
    }
}
