//
// $Id: CssTop.java,v 1.3 2010-01-05 13:49:45 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 */
public class CssTop extends CssBoxOffsetFace {

  /**
   * Create a new CssTop
   */
  public CssTop() {
    super();
  }

  /**
   * Create a new CssTop
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssTop(ApplContext ac, CssExpression expression,boolean check)
  	throws InvalidParamException {
    super(ac, expression, check);
  }

  public CssTop(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
  }

  /**
   * Returns the name of this property
   */
  public String getPropertyName() {
    return "top";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css1Style style0 = (Css1Style) style;
    if (style0.cssTop != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssTop = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getTop();
    } else {
      return ((Css1Style) style).getTop();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */
  public boolean equals(CssProperty property) {
    return (property instanceof CssTop &&
	    value.equals(((CssTop) property).value));
  }

}
