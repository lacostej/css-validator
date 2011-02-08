//
// $Id: CssPaddingLeft.java,v 1.4 2010-01-05 13:49:45 ylafon Exp $
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

/**
 *   <H4>
 *     <A NAME="padding-left">5.5.9 &nbsp;&nbsp; 'padding-left'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt;<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the left padding of an element.
 *   <PRE>
 *   BLOCKQUOTE { padding-left: 20% }
 * </PRE>
 *   <P>
 *   Padding values cannot be negative.
 * @version $Revision: 1.4 $
 */
public class CssPaddingLeft extends CssPaddingSide {

  /**
   * Create a new CssPaddingLeft
   */
  public CssPaddingLeft() {
    super();
  }

  /**
   * Create a new CssPaddingLeft with an another CssPaddingSide
   * @param another The another side.
   */
  public CssPaddingLeft(CssPaddingSide another) {
    super(another);
  }

  /**
   * Create a new CssPaddingLeft
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssPaddingLeft(ApplContext ac, CssExpression expression, boolean check)
  	throws InvalidParamException {
    super(ac, expression, check);
  }

  public CssPaddingLeft(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
  }

  /**
   * Returns the name of this property
   */
  public String getPropertyName() {
    return "padding-left";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css1Style style0 = (Css1Style) style;
    if (style0.cssPadding.left != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssPadding.left = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getPaddingLeft();
    } else {
      return ((Css1Style) style).cssPadding.getLeft();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */
  public boolean equals(CssProperty property) {
    return (property instanceof CssPaddingLeft &&
	    value.equals(((CssPaddingLeft) property).value));
  }

}
