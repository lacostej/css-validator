//
// $Id: CssFontWeight.java,v 1.4 2010-01-05 13:49:43 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'font-weight'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> normal | bold | bolder | lighter | 100 | 200 | 300 | 400
 *   | 500 | 600 | 700 | 800 | 900<BR>
 *   <EM>Initial:</EM> normal<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> The 'font-weight' property selects the weight of the font. The values
 *   '100' to '900' form an ordered sequence, where each number indicates a
 *   weight that is at least as dark as its predecessor. The keyword 'normal' is
 *   synonymous with '400', and 'bold' is synonymous with '700'. Keywords other
 *   than 'normal' and 'bold' have been shown to be often confused with font
 *   names and a numerical scale was therefore chosen for the 9-value list.
 *   <PRE>
 *   P { font-weight: normal }    400
 *   H1 { font-weight: 700 }      bold
 * </PRE>
 *   <P>
 *   The 'bolder' and 'lighter' values select font weights that are relative to
 *   the weight inherited from the parent:
 *   <PRE>
 *   STRONG { font-weight: bolder }
 * </PRE>
 *   <P>
 *   Child elements inherit the resultant weight, not the keyword value.
 *   <P> Fonts (the font data) typically have one or more properties whose
 *   values are names that are descriptive of the "weight" of a font. There is
 *   no accepted, universal meaning to these weight names. Their primary role is
 *   to distinguish faces of differing darkness within a single font
 *   family. Usage across font families is quite variant; for example a font
 *   that you might think of as being bold might be described as being
 *   <EM>Regular, Roman, Book, Medium, Semi-</EM> or <EM>DemiBold, Bold,</EM> or
 *   <EM>Black,</EM> depending on how black the "normal" face of the font is
 *   within the design. Because there is no standard usage of names, the weight
 *   property values in CSS1 are given on a numerical scale in which the value
 *   '400' (or 'normal') corresponds to the "normal" text face for that
 *   family. The weight name associated with that face will typically be
 *   <EM>Book, Regular, Roman, Normal</EM> or sometimes <EM>Medium</EM>.
 *   <P>
 *   The association of other weights within a family to the numerical weight
 *   values is intended only to preserve the ordering of darkness within that
 *   family. However, the following heuristics tell how the assignment is done
 *   in typical cases:
 *   <UL>
 *     <LI> If the font family already uses a numerical scale with nine values
 *     (like e.g. <EM>OpenType</EM> does), the font weights should be mapped
 *     directly.
 *     <LI> If there is both a face labeled <EM>Medium</EM> and one labeled
 *     <EM>Book, Regular, Roman</EM> or <EM>Normal,</EM> then the
 *     <EM>Medium</EM> is normally assigned to the '500'.
 *     <LI> The font labeled "Bold" will often correspond to the weight value
 *     '700'.
 *     <LI> If there are fewer then 9 weights in the family, the default
 *     algorithm for filling the "holes" is as follows. If '500' is unassigned,
 *     it will be assigned the same font as '400'. If any of the values '600',
 *     '700', '800' or '900' remains unassigned, they are assigned to the same
 *     face as the next darker assigned keyword, if any, or the next lighter one
 *     otherwise. If any of '300', '200' or '100' remains unassigned, it is
 *     assigned to the next lighter assigned keyword, if any, or the next darker
 *     otherwise.
 *   </UL>
 *   <P> The following two examples illustrate the process. Assume four weights
 *   in the "Example1" family, from lightest to darkest: <EM>Regular, Medium,
 *   Bold, Heavy.</EM> And assume six weights in the "Example2" family:
 *   <EM>Book, Medium, Bold, Heavy, Black, ExtraBlack.</EM> Note how in the
 *   second example it has been decided <EM>not</EM> to assign "Example2
 *   ExtraBlack" to anything.
 *   <PRE>
 *     Available faces       |  Assignments  |  Filling the holes
 *     ----------------------+---------------+-------------------
 *     "Example1 Regular"    |  400          |  100, 200, 300
 *     "Example1 Medium"     |  500          |
 *     "Example1 Bold"       |  700          |  600
 *     "Example1 Heavy"      |  800          |  900
 * </PRE>
 *   <PRE>
 *     Available faces       |  Assignments  |  Filling the holes
 *     ----------------------+---------------+-------------------
 *     "Example2 Book"       |  400          |  100, 200, 300
 *     "Example2 Medium"     |  500          |
 *     "Example2 Bold"       |  700          |  600
 *     "Example2 Heavy"      |  800          |
 *     "Example2 Black"      |  900          |
 *     "Example2 ExtraBlack" |  (none)       |
 * </PRE>
 *   <P> Since the intent of the relative keywords 'bolder' and 'lighter' is to
 *   darken or lighten the face <EM>within the family</EM> and because a family
 *   may not have faces aligned with all the symbolic weight values, the
 *   matching of 'bolder' is to the next darker face available on the client
 *   within the family and the matching of 'lighter' is to the next lighter face
 *   within the family.  To be precise, the meaning of the relative keywords
 *   'bolder' and 'lighter' is as follows:
 *   <UL>
 *     <LI> 'bolder' selects the next weight that is assigned to a font that is
 *     darker than the inherited one. If there is no such weight, it simply
 *     results in the next darker numerical value (and the font remains
 *     unchanged), unless the inherited value was '900' in which case the
 *     resulting weight is also '900'.
 *     <LI> 'lighter' is similar, but works in the opposite direction: it
 *     selects the next lighter keyword with a different font from the inherited
 *     one, unless there is no such font, in which case it selects the next
 *     lighter numerical value (and keeps the font unchanged).
 *   </UL>
 *   <P> There is no guarantee that there will be a darker face for each of the
 *   'font-weight' values; for example, some fonts may have only a normal and a
 *   bold face, others may have eight different face weights. There is no
 *   guarantee on how a UA will map font faces within a family to weight
 *   values. The only guarantee is that a face of a given value will be no less
 *   dark than the faces of lighter values.
 *
 * @version $Revision: 1.4 $
 */
public class CssFontWeight extends CssProperty implements CssFontConstant {


    /**
     * an index in a array
     *
     * @see CssFontConstant#FONTWEIGHT
     */
    protected int value;

    // internal hack to compare strings
    private static int[] hash_values;

    /**
     * Create a new FontWeight with the default value.
     */
    public CssFontWeight() {
	// nothing to do
    }

    /**
     * Creates a new CssFontWeight with an expression.
     *
     * @param expr the expression
     * @exception InvalidParamException values are incorrect
     */
    public CssFontWeight(ApplContext ac, CssExpression expr, boolean check)
    	throws InvalidParamException {

	if(check && expr.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expr.getValue();

	setByUser();

	if (val instanceof CssIdent) {
	    int hash = expr.getValue().hashCode();

	    // try to find the hashCode in my internal hack array
	    for (int i=0; i<hash_values.length; i++)
		if (hash_values[i] == hash) {
		    this.value = i;
		    expr.next();
		    return;
		}
	} else if (val instanceof CssNumber) {
	    Object valf = val.get();
	    if(valf instanceof Integer) {
		int vali = ((Integer) valf).intValue();
		if(isCorrectWeight(vali)) { // verify the entire part number
		    this.value = vali;
		    expr.next();
		    return;
		}
	    }

	}

	throw new InvalidParamException("value", expr.getValue().toString(),
					getPropertyName(), ac);
    }

    public CssFontWeight(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the current value.
     */
    public Object get() {
	if (value >= 100)
	    return new Integer(value);
	else
	    return FONTWEIGHT[value];
    }


    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == FONTWEIGHT.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (value >= 100)
	    return Integer.toString(value);
	else
	    return FONTWEIGHT[value];
    }

    /**
     * Returns the name of this property.
     */
    public String getPropertyName() {
	return "font-weight";
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssFont cssFont = ((Css1Style) style).cssFont;
	if (cssFont.fontWeight != null)
	    style.addRedefinitionWarning(ac, this);
	cssFont.fontWeight = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFontWeight();
	} else {
	    return ((Css1Style) style).cssFont.fontWeight;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontWeight &&
		((CssFontWeight) property).value == value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == 0;
    }

    private boolean isCorrectWeight(int val) {
	val = val / 100;
	return val > 0 && val < 10;
    }

    static {
	hash_values = new int[FONTWEIGHT.length];
	for (int i=0; i<FONTWEIGHT.length; i++)
	    hash_values[i] = FONTWEIGHT[i].hashCode();
    }
}

