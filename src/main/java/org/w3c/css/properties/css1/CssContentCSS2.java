//
// $Id: CssContentCSS2.java,v 1.4 2010-01-05 13:49:42 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css1;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssString;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 */
public class CssContentCSS2 extends CssProperty {

    Vector values = new Vector();

    private static String CONTENT[] = {
	"open-quote", "close-quote", "no-open-quote", "no-close-quote" };

    private static int[] hash_values;


    /**
     * Create a new CssContent
     */
    public CssContentCSS2() {
    }

    /**
     * Create a new CssContent
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssContentCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();

	setByUser();
	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    values.addElement(inherit);
	    expression.next();
	    return;
	}

	addContent(ac, expression);

    }

    /**
     * @param ac
     * @param expression
     * @throws InvalidParamException
     */
    protected void addContent(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	CssValue val = expression.getValue();
	int counter = 0;
	char op = expression.getOperator();
	while ((op == CssOperator.SPACE)
	       && (counter < expression.getCount())) {
	    if (val instanceof CssIdent) {
		int hash = val.hashCode();
		int i = 0;
		for (;i < CONTENT.length; i++) {
		    if (hash_values[i] == hash) {
			break;
		    }
		}
		if (i == CONTENT.length) {
		    throw new InvalidParamException("value",
						    expression.getValue(),
						    getPropertyName(), ac);
		}
	    } else if (val instanceof CssString) {
		// nothing
	    } else if (val instanceof CssURL) {
		// nothing
	    } else if (val instanceof CssFunction) {
		CssFunction attr = (CssFunction) val;
		CssExpression params = attr.getParameters();
		CssValue v = params.getValue();
		if (attr.getName().equals("attr")) {
		    if ((params.getCount() != 1)
			|| !(v instanceof CssIdent)) {
			throw new InvalidParamException("attr",
							params.getValue(),
							getPropertyName(), ac);
		    }
		} else if (attr.getName().equals("counter")) {
		    if ((params.getCount() == 1)
			&& (v instanceof CssIdent)) {
			// nothing
		    } else if ((params.getCount() == 2)
			       && (v instanceof CssIdent)) {
			op = params.getOperator();
			params.next();
			v = params.getValue();
			if ((op != CssOperator.COMMA)
			    || !(v instanceof CssIdent)) {
			    throw new InvalidParamException("counter",
							    params.getValue(),
							    getPropertyName(), ac);
			}
		    } else {
			throw new InvalidParamException("counter",
							params.getValue(),
							getPropertyName(), ac);
		    }
		    params.starts();
		} else if (attr.getName().equals("counters")) {
		    if ((params.getCount() == 2)
			&& (v instanceof CssIdent)) {
			op = params.getOperator();
			params.next();
			v = params.getValue();
			if ((op != CssOperator.COMMA)
			    || !(v instanceof CssString)) {
			    throw new InvalidParamException("counters",
							    params.getValue(),
							    getPropertyName(), ac);
			}
		    } else if ((params.getCount() == 3)
			       && (v instanceof CssIdent)) {
			op = params.getOperator();
			params.next();
			v = params.getValue();
			if ((op != CssOperator.COMMA)
			    || !(v instanceof CssString)) {
			    throw new InvalidParamException("counters",
							    params.getValue(),
							    getPropertyName(), ac);
			}
			op = params.getOperator();
			params.next();
			v = params.getValue();
			if ((op != CssOperator.COMMA)
			    || !(v instanceof CssIdent)) {
			    throw new InvalidParamException("counters",
							    params.getValue(),
							    getPropertyName(), ac);
			}
		    } else {
			throw new InvalidParamException("counters",
							params.getValue(),
							getPropertyName(), ac);
		    }
		    params.starts();
		} else {
		    throw new InvalidParamException("value",
						    expression.getValue(),
						    getPropertyName(), ac);
		}
	    } else {
		throw new InvalidParamException("value",
						expression.getValue(),
						getPropertyName(), ac);
	    }
	    values.addElement(val);
	    expression.next();
	    counter++;
	    val = expression.getValue();
	    op = expression.getOperator();
	}
    }

    public CssContentCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * @return Returns the values.
     */
    public Vector getValues() {
        return values;
    }

    /**
     * @param values The values to set.
     */
    public void setValues(Vector values) {
        this.values = values;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return values;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "content";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return values.elementAt(0) == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	int i = 0;
	String ret = "";
	while (i < values.size()) {
	    ret += " " + values.elementAt(i);
	    i++;
	}
	return ret.substring(1);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssContentCSS2 != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.cssContentCSS2 = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getContent();
	} else {
	    return ((Css1Style) style).cssContent;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	// @@TODO
	return false;
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

    static {
	hash_values = new int[CONTENT.length];
	for (int i=0; i<CONTENT.length; i++)
	    hash_values[i] = CONTENT[i].hashCode();
    }
}
