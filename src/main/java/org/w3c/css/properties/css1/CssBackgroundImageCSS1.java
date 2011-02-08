//
// $Id: CssBackgroundImageCSS1.java,v 1.5 2010-01-05 13:49:39 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssBackgroundImage;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * <H4>
 * &nbsp;&nbsp; 'background-image'
 * </H4>
 * <p/>
 * <EM>Value:</EM> &lt;url&gt; | none<BR>
 * <EM>Initial:</EM> none<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> N/A<BR>
 * <P> This property sets the background image of an element. When setting a
 * background image, one should also set a background color that will be used
 * when the image is unavailable. When the image is available, it is overlaid
 * on top of the background color.
 * <PRE>
 * BODY { background-image: url(marble.gif) }
 * P { background-image: none }
 * </PRE>
 *
 * @version $Revision: 1.5 $
 */
public class CssBackgroundImageCSS1 extends CssBackgroundImage {

    CssValue url = null;

    /**
     * Create a new CssBackgroundImageCSS1
     */
    public CssBackgroundImageCSS1() {
        url = none;
    }

    /**
     * Creates a new CssBackgroundImageCSS1
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundImageCSS1(ApplContext ac, CssExpression expression,
                                  boolean check) throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        setByUser();

        CssValue val = expression.getValue();

        switch (val.getType()) {
            case CssTypes.CSS_URL:
                url = val;
                break;
            case CssTypes.CSS_IDENT:
                if (none.equals(val)) {
                    url = none;
                    break;
                }
            default:
                throw new InvalidParamException("value", val,
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssBackgroundImageCSS1(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return url.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        CssBackgroundCSS1 cssBackground = ((Css1Style) style).cssBackgroundCSS1;
        if (cssBackground.image != null) {
            style.addRedefinitionWarning(ac, this);
        }
        cssBackground.image = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackgroundImageCSS1();
        } else {
            return ((Css1Style) style).cssBackgroundCSS1.image;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return ((url == null && property == null) ||
                (property instanceof CssBackgroundImageCSS1 &&
                        url != null &&
                        url.equals(((CssBackgroundImageCSS1) property).url)));
    }
}
