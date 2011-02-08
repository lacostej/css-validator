//
// $Id: CssBackgroundImageCSS2.java,v 1.1 2010-01-05 13:49:46 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css2;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssBackgroundImage;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
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
 * @version $Revision: 1.1 $
 */
public class CssBackgroundImageCSS2 extends CssBackgroundImage {

    public CssValue url = null;

    static public boolean checkMatchingIdent(CssIdent idval) {
        return none.equals(idval);
    }

    /**
     * Create a new CssBackgroundImageCSS2
     */
    public CssBackgroundImageCSS2() {
        url = none;
    }

    /**
     * Creates a new CssBackgroundImageCSS2
     *
     * @param ac  The context
     * @param expression The expression for this property
     * @param check if count check must be performed
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundImageCSS2(ApplContext ac, CssExpression expression,
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
                if (inherit.equals(val)) {
                    url = inherit;
                    break;
                }
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

    public CssBackgroundImageCSS2(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return url;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return (url == inherit);
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
        CssBackgroundCSS2 cssBackground = ((Css1Style) style).cssBackgroundCSS2;
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
            return ((Css1Style) style).getBackgroundImageCSS2();
        } else {
            return ((Css1Style) style).cssBackgroundCSS2.image;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return ((property == null && url == null)
                || (property instanceof CssBackgroundImageCSS2 &&
                url != null &&
                url.equals(((CssBackgroundImageCSS2) property).url)));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (url == none);
    }

}
