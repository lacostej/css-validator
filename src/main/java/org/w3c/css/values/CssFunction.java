//
// $Id: CssFunction.java,v 1.8 2010-01-06 09:27:44 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;

/**
 * A CSS float number.
 *
 * @version $Revision: 1.8 $
 */
public class CssFunction extends CssValue {

    public static final int type = CssTypes.CSS_FUNCTION;

    public final int getType() {
        return type;
    }

    String name;
    CssExpression parameters;

    /**
     * Set the value of this function
     *
     * @param s  the string representation of the frequency.
     * @param ac For errors and warnings reports.
     */
    public void set(String s, ApplContext ac) {
        // @@TODO
    }

    public void set(String name, CssExpression parameters) {
        this.name = name;
        this.parameters = parameters;

    }

    /**
     * Returns the value
     */
    public Object get() {
        // @@TODO
        return null;
    }

    /**
     * Returns the name of the function
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the parameters expression
     */
    public CssExpression getParameters() {
        return parameters;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append('(').append(parameters).append(')');
        return sb.toString();
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        // @@FIXME
        return (value instanceof CssFunction &&
                this.name.equals(((CssFunction) value).name));
    }
}
