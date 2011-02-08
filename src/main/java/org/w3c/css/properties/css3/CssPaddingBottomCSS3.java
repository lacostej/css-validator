//
// $Id: CssPaddingBottomCSS3.java,v 1.4 2010-01-05 13:49:54 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'padding-bottom'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt;<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the bottom padding of an element.
 *   <PRE>
 *   BLOCKQUOTE { padding-bottom: 2em }
 * </PRE>
 *   <P>
 *   Padding values cannot be negative.
 * @version $Revision: 1.4 $
 */
public class CssPaddingBottomCSS3 extends CssPaddingSideCSS3 {

    /**
     * Create a new CssPaddingBottomCSS3
     */
    public CssPaddingBottomCSS3() {
	super();
    }

    /**
     * Create a new CssPaddingBottomCSS3 with an another CssPaddingSideCSS3
     *
     * @param another The another side.
     */
    public CssPaddingBottomCSS3(CssPaddingSideCSS3 another) {
	super(another);
    }

    /**
     * Create a new CssPaddingBottomCSS3
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssPaddingBottomCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	super(ac, expression, check);
    }

    public CssPaddingBottomCSS3(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "padding-bottom";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssPaddingBottomCSS3 != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssPaddingBottomCSS3 = this;

    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getPaddingBottomCSS3();
	} else {
	    return ((Css3Style) style).cssPaddingCSS3.getBottom();
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssPaddingBottomCSS3 &&
		value.equals(((CssPaddingBottomCSS3) property).value));
    }

}
