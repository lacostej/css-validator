//
// $Id: CssDisplayRole.java,v 1.3 2010-01-05 13:49:52 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> none | block | inline | list-item | marker | run-in | compact |
 *  table-row | table-cell | table-row-group | table-header-group | table-footer-group |
 *  table-column | table-column-group | table-caption | ruby-text | ruby-base |
 *  ruby-base-group | ruby-text-group | inherited <BR>
 *  <EM>Initial:</EM>inline<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssDisplayRole extends CssProperty {

    CssValue role;

    static CssIdent inline = new CssIdent("inline");

    private static String[] values = {
	"none", "block", "inline", "list-item", "run-in", "compact", "table-row",
	"table-cell", "table-row-group", "table-header-group", "table-footer-group",
	"table-column", "table-column-group", "table-caption", "ruby-text", "ruby-base",
	"ruby-base-group", "ruby-text-group", "initial", "inherit"
    };

    /**
     * Create a new CssDisplayRole
     */
    public CssDisplayRole() {
	role = inline;
    }

    /**
     * Create a new CssDisplayRole
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssDisplayRole(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		role = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssDisplayRole(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssDisplayRole != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssDisplayRole = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getDisplayRole();
	}
	else {
	    return ((Css3Style) style).cssDisplayRole;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssDisplayRole &&
		role.equals(((CssDisplayRole) property).role));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "display-role";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return role;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return role.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return role.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (role == inline);
    }

}
