// $Id: CssColumnFill.java,v 1.2 2010-01-05 19:49:50 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css3.Css3Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;

/**
 * http://www.w3.org/TR/2009/CR-css3-multicol-20091217/#filling-columns
 * <p/>
 * There are two strategies for filling columns: columns can either be
 * balanced, or not. If columns are balanced, UAs should minimize the variation
 * in column length. Otherwise, columns are filled sequentially and will
 * therefore end up having different lengths. In any case, the user agent
 * should try to honor the �widows� and �orphans� properties.
 * <p/>
 * Name: 	column-fill
 * Value: 	auto | balance
 * Initial: 	balance
 * Applies to: 	multi-column elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	see below
 * Computed value: 	as specified
 */

public class CssColumnFill extends CssProperty {

    private static final String propertyName = "column-fill";

    CssIdent value;

    static CssIdent balance;
    private static HashMap<String, CssIdent> allowed_values;

    static {
        balance = CssIdent.getIdent("balance");
        allowed_values = new HashMap<String, CssIdent>();
        allowed_values.put("balance", balance);
        allowed_values.put("auto", CssIdent.getIdent("auto"));
    }

    /**
     * Create a new CssColumnWidth
     */
    public CssColumnFill() {
        value = balance;
    }

    /**
     * Create a new CssColumnFill
     *
     * @param ac the context
     * @param expression The expression for this property
     * @param check if length check is needed
     * @throws InvalidParamException Incorrect value
     */
    public CssColumnFill(ApplContext ac, CssExpression expression,
                         boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        if (val.getType() != CssTypes.CSS_IDENT) {
            throw new InvalidParamException("value",
                    expression.getValue(),
                    getPropertyName(), ac);
        }
        // ident, so inherit, or allowed value
        if (inherit.equals(val)) {
            value = inherit;
        } else {
            val = allowed_values.get(val.toString());
            if (val == null) {
                throw new InvalidParamException("value",
                        expression.getValue(),
                        getPropertyName(), ac);
            }
            value = (CssIdent) val;
        }
        expression.next();
    }

    public CssColumnFill(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssColumnFill != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssColumnFill = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getColumnFill();
        } else {
            return ((Css3Style) style).cssColumnFill;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColumnFill &&
                value.equals(((CssColumnFill) property).value));
    }

    /**
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (inherit == value);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (balance == value);
    }

}
