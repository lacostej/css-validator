// $Id: CssColumnCount.java,v 1.1 2010-01-05 13:49:38 ylafon Exp $
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
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * http://www.w3.org/TR/2009/CR-css3-multicol-20091217/#cc
 * <p/>
 * Name:  	column-count
 * Value: 	&lt;integer&gt; | auto
 * Initial: 	auto
 * Applies to: 	non-replaced block-level elements (except table elements),
 * table cells, and inline-block elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	specified value
 * <p/>
 * This property describes the number of columns of a multicol element.
 */

public class CssColumnCount extends CssProperty {

    private static final String propertyName = "column-count";

    CssValue count;

    static CssIdent auto = CssIdent.getIdent("auto");

    /**
     * Create a new CssColumnCount
     */
    public CssColumnCount() {
        count = auto;
    }

    /**
     * Create a new CssColumnCount
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Incorrect value
     */
    public CssColumnCount(ApplContext ac, CssExpression expression,
                          boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();
        CssNumber num;

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                num = (CssNumber) val;
                if (!num.isInteger()) {
                    throw new InvalidParamException("integer",
                            expression.getValue(), getPropertyName(), ac);
                }
                if (num.getInt() <= 0) {
                    throw new InvalidParamException("strictly-positive",
                            expression.getValue(),
                            getPropertyName(), ac);
                }
                count = val;
                break;
            case CssTypes.CSS_IDENT:
                if (auto.equals(val)) {
                    count = auto;
                    break;
                }
                if (inherit.equals(val)) {
                    count = inherit;
                    break;
                }
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssColumnCount(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssColumnCount != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssColumnCount = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getColumnCount();
        } else {
            return ((Css3Style) style).cssColumnCount;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColumnCount &&
                count.equals(((CssColumnCount) property).count));
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
        return count;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (count == inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return count.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (count == auto);
    }
}
