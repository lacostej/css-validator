//
// $Id: CssBackgroundPositionMob.java,v 1.4 2010-01-05 13:49:40 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssBackgroundConstants;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'background-position'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [&lt;percentage&gt; | &lt;length&gt;]{1,2} | [top | center
 *   | bottom] || [left | center | right]<BR>
 *   <EM>Initial:</EM> 0% 0%<BR>
 *   <EM>Applies to:</EM> block-level and replaced elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to the size of the element itself<BR>
 *   <P> If a background image has been specified, the value of
 *   'background-position' specifies its initial position.
 *   <P> With a value pair of '0% 0%', the upper left corner of the image is
 *   placed in the upper left corner of the box that surrounds the content of
 *   the element (i.e., not the box that surrounds the padding, border or
 *   margin). A value pair of '100% 100%' places the lower right corner of the
 *   image in the lower right corner of the element. With a value pair of '14%
 *   84%', the point 14% across and 84% down the image is to be placed at the
 *   point 14% across and 84% down the element.
 *   <P> With a value pair of '2cm 2cm', the upper left corner of the image is
 *   placed 2cm to the right and 2cm below the upper left corner of the element.
 *   <P> If only one percentage or length value is given, it sets the horizontal
 *   position only, the vertical position will be 50%. If two values are given,
 *   the horizontal position comes first. Combinations of length and percentage
 *   values are allowed, e.g. '50% 2cm'. Negative positions are allowed.
 *   <P> One can also use keyword values to indicate the position of the
 *   background image. Keywords cannot be combined with percentage values, or
 *   length values.  The possible combinations of keywords and their
 *   interpretations are as follows:

 *   <UL>
 *     <LI>
 *       'top left' and 'left top' both mean the same as '0% 0%'.
 *     <LI>
 *       'top', 'top center' and 'center top' mean the same as '50% 0%'.
 *     <LI>
 *       'right top' and 'top right' mean the same as '100% 0%'.
 *     <LI>
 *       'left', 'left center' and 'center left' mean the same as '0% 50%'.
 *     <LI>
 *       'center' and 'center center' mean the same as '50% 50%'.
 *     <LI>
 *       'right', 'right center' and 'center right' mean the same as '100% 50%'.
 *     <LI>
 *       'bottom left' and 'left bottom' mean the same as '0% 100%'.
 *     <LI>
 *       'bottom', 'bottom center' and 'center bottom' mean the same as '50% 100%'.
 *     <LI>
 *       'bottom right' and 'right bottom' mean the same as '100% 100%'.
 *   </UL>
 *   <P>
 *   examples:
 *   <PRE>
 *   BODY { background: url(banner.jpeg) right top }    / * 100%   0% * /
 *   BODY { background: url(banner.jpeg) top center }   / *  50%   0% * /
 *   BODY { background: url(banner.jpeg) center }       / *  50%  50% * /
 *   BODY { background: url(banner.jpeg) bottom }       / *  50% 100% * /
 *  </PRE>
 *   <P>
 *   If the background image is fixed with regard to the canvas (see the
 *   'background-attachment' property above), the image is placed relative to
 *   the canvas instead of the element. E.g.:
 *   <PRE>
 *   BODY {
 *     background-image: url(logo.png);
 *     background-attachment: fixed;
 *     background-position: 100% 100%;
 *   }
 *  </PRE>
 *   <P>
 *   In the example above, the image is placed in the lower right corner of the
 *   canvas.
 * @version $Revision: 1.4 $
 * @see org.w3c.css.properties.css.CssBackgroundAttachment
 */
public class CssBackgroundPositionMob extends CssProperty
        implements CssBackgroundConstants, CssOperator {

    CssValue horizontal;
    CssValue vertical;

    /**
     * Create a new CssBackgroundPositionMob
     */
    public CssBackgroundPositionMob() {
	horizontal = DefaultValue0;
	vertical = DefaultValue0;
    }

    /**
     * Creates a new CssBackgroundPositionMob
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBackgroundPositionMob(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 2) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	CssValue val = expression.getValue();
	char op  = expression.getOperator();
	int index;

	if (op != SPACE)
	    throw new  InvalidParamException("operator",
					     ((new Character(op)).toString()),
					     ac);

	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    horizontal = inherit;
	    vertical = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssIdent
	    && (index=IndexOfIdent((String) val.get())) != INVALID) {
	    CssValue next = expression.getNextValue();
	    if(next.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    expression.next();
	    if (next == null) {
		getPercentageFromIdent(index, INVALID);
	    } else if (next instanceof CssIdent) {
		int index2 = IndexOfIdent((String) next.get());
		if (index2 != INVALID) {
		    getPercentageFromIdent(index, index2);
		    expression.next();
		} else {
		    getPercentageFromIdent(index, INVALID);
		}
	    }
	} else if (val instanceof CssLength ||
		   val instanceof CssPercentage || val instanceof CssNumber) {
	    if (val instanceof CssNumber) {
		val = ((CssNumber) val).getLength();
	    }
	    horizontal = val;
	    expression.next();
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    if (val instanceof CssLength ||
		val instanceof CssPercentage || val instanceof CssNumber) {
		if (val instanceof CssNumber) {
		    val = ((CssNumber) val).getLength();
		}
		vertical = val;
		expression.next();
	    } else if(val != null) {
		throw new InvalidParamException("value", expression.getValue(),
			    getPropertyName(), ac);
	    }
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}


    }

    public CssBackgroundPositionMob(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return horizontal;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background-position";
    }

    /**
     * Returns the horizontal value of the position
     */
    public CssValue getHorizontalPosition() {
	return horizontal;
    }

    /**
     * Returns the vertical value of the position
     */
    public CssValue getVerticalPosition() {
	return vertical;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return horizontal == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (horizontal == inherit) {
	    return inherit.toString();
	} else {
	    String ret = "";
	    if (horizontal != null) {
		ret += horizontal;
	    }
	    if (vertical != null) {
		if (!ret.equals("")) {
		    ret += " ";
		}
		ret += vertical;
	    }
	    return ret;
	}
    }

    private void getPercentageFromIdent(int first, int second) {
	horizontal = DefaultValue50;
	vertical = DefaultValue50;
	if (first == POSITION_LEFT || second == POSITION_LEFT)
	    horizontal = DefaultValue0;
	if (first == POSITION_RIGHT || second == POSITION_RIGHT)
	    horizontal = DefaultValue100;
	if (first == POSITION_TOP || second == POSITION_TOP)
	    vertical = DefaultValue0;
	if (first == POSITION_BOTTOM || second == POSITION_BOTTOM)
	    vertical = DefaultValue100;
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBackgroundMob cssBackground = ((Css1Style) style).cssBackgroundMob;
	if (cssBackground.position != null)
	    style.addRedefinitionWarning(ac, this);
	cssBackground.position = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackgroundPositionMob();
	} else {
	    return ((Css1Style) style).cssBackgroundMob.position;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundPositionMob &&
		horizontal.equals(((CssBackgroundPositionMob) property).horizontal)
		&& vertical.equals(((CssBackgroundPositionMob) property).vertical));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return horizontal != null && vertical != null &&
	    horizontal.equals(DefaultValue0) && vertical.equals(DefaultValue0);
    }

    private int IndexOfIdent(String ident) throws InvalidParamException {
	int hash = ident.hashCode();
	for (int i = 0; i < POSITION.length; i++)
	    if (hash_values[i] == hash)
		return i;

	return -1;
    }

    private static int[] hash_values;

    private static int INVALID = -1;
    private static CssPercentage DefaultValue0 = new CssPercentage(0);
    private static CssPercentage DefaultValue50 = new CssPercentage(50);
    private static CssPercentage DefaultValue100 = new CssPercentage(100);

    static {
	hash_values = new int[POSITION.length];
	for (int i = 0; i < POSITION.length; i++)
	    hash_values[i] = POSITION[i].hashCode();
    }
}
