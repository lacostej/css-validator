// $Id: CssBackgroundSize.java,v 1.4 2010-01-22 10:51:17 ylafon Exp $
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2010.
// Please first read the full copyright statement in file COPYRIGHT.html

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
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;
import java.util.HashMap;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-size
 * <p/>
 * <p/>
 * Name: 	background-size
 * Value: 	&lt;bg-size&gt; [ , &lt;bg-size&gt; ]*
 * Initial: 	auto
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	see text
 * Media: 	visual
 * Computed value: 	for &lt;length&gt; the absolute value, otherwise as
 * specified
 * <p/>
 * Specifies the size of the background images. Where
 * <p/>
 * &lt;bg-size&gt; = [ &lt;length&gt; | &lt;percentage&gt; | auto ]{1,2} |
 * cover | contain
 */
public class CssBackgroundSize extends CssProperty {

    private static final String propertyName = "background-size";

    private static CssIdent auto;
    private static HashMap<String, CssIdent> allowed_values;

    static {
        auto = CssIdent.getIdent("auto");
        allowed_values = new HashMap<String, CssIdent>();
        allowed_values.put("auto", auto);
        allowed_values.put("cover", CssIdent.getIdent("cover"));
        allowed_values.put("contain", CssIdent.getIdent("contain"));
    }

    public static boolean isMatchingIdent(CssIdent ident) {
        return allowed_values.containsKey(ident.toString());
    }

    Object value;

    /**
     * Create a new CssBackgroundSize
     */
    public CssBackgroundSize() {
        value = auto;
    }

    /**
     * Create a new CssBackgroundSize
     *
     * @param ac         The context
     * @param expression The expression for this property
     * @param check      if arguments count must be checked.
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundSize(ApplContext ac, CssExpression expression,
                             boolean check) throws InvalidParamException {
        ArrayList<CssValue> values = new ArrayList<CssValue>();
        char op;
        CssValue val;
        CssValueList vl = null;
        boolean is_complete = true;

        setByUser();

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();
            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                    val = ((CssNumber) val).getLength();
                case CssTypes.CSS_LENGTH:
                case CssTypes.CSS_PERCENTAGE:
                    // per spec only non-negative values are allowed
                    float f = ((Float) val.get()).floatValue();
                    if (f < 0) {
                        throw new InvalidParamException("negative-value",
                                val.toString(), ac);
                    }
                    if (is_complete) {
                        vl = new CssValueList();
                        vl.add(val);
                    } else {
                        vl.add(val);
                        values.add(vl);
                    }
                    is_complete = !is_complete;
                    break;
                case CssTypes.CSS_IDENT:
                    if (inherit.equals(val)) {
                        // if we got inherit after other values, fail
                        // if we got more than one value... fail
                        if ((values.size() > 0) || (expression.getCount() > 1)) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        values.add(inherit);
                        break;
                    } else if (auto.equals(val)) {
                        if (is_complete) {
                            vl = new CssValueList();
                            vl.add(auto);
                        } else {
                            vl.add(auto);
                            values.add(vl);
                        }
                        is_complete = !is_complete;
                        break;
                    } else {
                        CssValue v = allowed_values.get(val.toString());
                        // if ok, and if we are not in a middle of a compound
                        // value...
                        if (v != null && is_complete) {
                            values.add(v);
                            break;
                        }
                    }
                default:
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);

            }
            expression.next();
            if (!expression.end()) {
                // incomplete value followed by a comma... it's complete!
                if (!is_complete && (op == COMMA)) {
                    values.add(vl);
                    is_complete = true;
                }
                // complete values are separated by a comma, otherwise space
                if ((is_complete && (op != COMMA)) ||
                        (!is_complete && (op != SPACE))) {
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
            }
        }
        // if we reach the end in a value that can come in pair
        if (!is_complete) {
            values.add(vl);
        }
        if (values.size() == 1) {
            value = values.get(0);
        } else {
            value = values;
        }
    }


    public CssBackgroundSize(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssBackgroundSize != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssBackgroundSize = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getCssBackgroundSize();
        } else {
            return ((Css3Style) style).cssBackgroundSize;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundSize &&
                value.equals(((CssBackgroundSize) property).value));
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

    public void set(Object val) {
        value = val;
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
        if (value instanceof ArrayList) {
            ArrayList values = (ArrayList) value;
            StringBuilder sb = new StringBuilder();
            for (Object aValue : values) {
                sb.append(aValue.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (auto == value);
    }

}
