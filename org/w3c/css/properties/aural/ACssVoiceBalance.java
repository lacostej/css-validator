//
// $Id: ACssVoiceBalance.java,v 1.4 2010-01-05 13:49:36 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;


/**
 *
 * @version $Revision: 1.4 $
 */
public class ACssVoiceBalance extends ACssProperty {

    CssValue value;

    private static int[] hash_values;

    private static String[] VOLUME = { "left", "center", "right", "inherit" };

    private static CssIdent defaultValue = new CssIdent(VOLUME[3]);

    /**
     * Create a new ACssVoiceBalance
     */
    public ACssVoiceBalance() {
	value = defaultValue;
    }

    /**
     * Creates a new ACssVoiceBalance
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ACssVoiceBalance(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this();

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	int index;

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val instanceof CssIdent) {
	    value = checkIdent(ac, (CssIdent) val);
	} else if (val instanceof CssNumber) {
	    CssNumber num = (CssNumber) val;
	    int i = (int) num.getValue();
	    if (i < 0) {
		value = new CssNumber(ac, 0);
	    } else if (i > 100) {
		value = new CssNumber(ac, 100);
	    } else {
		value = val;
	    }
	    expression.next();
	} else {
	    throw new InvalidParamException("value",
					    expression.getValue().toString(),
					    getPropertyName(), ac);
	}

	expression.next();
    }

    public ACssVoiceBalance(ApplContext ac, CssExpression expression)
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
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
    }

    /**
     * Returns some usable value of this property...
     *
     * @deprecated
     */
    public float getValue() { // vm
	return ((CssNumber) value).getValue();
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "voice-balance";
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssVoiceBalance != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssVoiceBalance = this;
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ACssVoiceBalance &&
		value.equals(((ACssVoiceBalance) property).value));
    }

    private CssIdent checkIdent(ApplContext ac, CssIdent ident)
	throws InvalidParamException {

	int hash = ident.hashCode();
	for (int i = 0; i < VOLUME.length; i++) {
	    if (hash_values[i] == hash) {
		return ident;
	    }
	}

	throw new InvalidParamException("value", ident.toString(),
					getPropertyName(), ac);
    }

    /** @deprecated */
    private CssPercentage ValueOfIdent(ApplContext ac, CssIdent ident)
	throws InvalidParamException {

	int hash = ident.hashCode();
	for (int i = 0; i < VOLUME.length; i++) {
	    if (hash_values[i] == hash) {
		return new CssPercentage(ACssProperties.getValue(this, VOLUME[i]));
	    }
	}

	throw new InvalidParamException("value", ident.toString(),
					getPropertyName(), ac);
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getVoiceBalance();
	} else {
	    return ((ACssStyle) style).acssVoiceBalance;
	}
    }

    static {
	hash_values = new int[VOLUME.length];
	for (int i = 0; i < VOLUME.length; i++)
	    hash_values[i] = VOLUME[i].hashCode();
    }
}

