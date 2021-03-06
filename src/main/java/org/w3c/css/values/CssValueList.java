// $Id: CssValueList.java,v 1.1 2010-01-05 13:50:01 ylafon Exp $
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2010.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.util.ArrayList;

/**
 * A space separated value list.
 *
 */
public class CssValueList extends CssValue {

    public static final int type = CssTypes.CSS_VALUE_LIST;

    public ArrayList<CssValue> value;

    public final int getType() {
        return type;
    }

    /**
     * Create a new CssValueList
     */
    public CssValueList() {
        value = new ArrayList<CssValue>();
    }

    /**
     * Create a new CssValueList
     *
     * @param val the <EM>ArrayList</EM> of CssValue
     */
    public CssValueList(ArrayList<CssValue> val) {
        value = val;
    }

    /**
     * Set the value of this string.
     *
     * @param s the ArrayList of CSS values
     * @param ac  For errors and warnings reports.
     * @throws org.w3c.css.util.InvalidParamException
     *          The unit is incorrect
     */
    public void set(String s, ApplContext ac)
            throws InvalidParamException {
        throw new InvalidParamException("invalid-class", s, ac);
    }

    public void add(CssValue val) {
        value.add(val);
    }

    /**
     * Returns the value
     */
    public Object get() {
        return value;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CssValue aCssValue: value) {
            sb.append(aCssValue.toString()).append(" ");
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    /**
     * Get the hash code of the internal string.
     */
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        return (value instanceof CssValueList &&
                this.value.equals(((CssValueList) value).value));
    }

}