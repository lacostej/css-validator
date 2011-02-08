// $Id: CssBackgroundCSS21.java,v 1.6 2010-01-05 13:49:48 ylafon Exp $
// Author: Jean-Guilhem Rouel
// Revised by: Yves Lafon
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005-2008.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css2.CssBackgroundAttachmentCSS2;
import org.w3c.css.properties.css2.CssBackgroundCSS2;
import org.w3c.css.properties.css2.CssBackgroundImageCSS2;
import org.w3c.css.properties.css2.CssBackgroundRepeatCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssTypes;

/**
 * CssBackgroundCSS21<br />
 * Created: Aug 31, 2005 2:03:41 PM<br />
 */
public class CssBackgroundCSS21 extends CssBackgroundCSS2 {
    /**
     * Create a new CssBackgroundCSS2
     */
    public CssBackgroundCSS21() {
        super();
    }

    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @throws InvalidParamException The expression is incorrect
     */
    public CssBackgroundCSS21(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        CssValue val;
        char op = SPACE;
        boolean find = true;
        CssExpression background_position_expression = null;

        // too many values
        if (check && expression.getCount() > 6) {
            throw new InvalidParamException("unrecognize", ac);
        }

        setByUser();

        boolean manyValues = (expression.getCount() > 1);

        while (find) {
            val = expression.getValue();
            if (val == null) {
                break;
            }
            op = expression.getOperator();

            // if there are many values, we can't have inherit as one of them
            if (manyValues && val.equals(inherit)) {
                throw new InvalidParamException("unrecognize", null, null, ac);
            }

            switch (val.getType()) {
                case CssTypes.CSS_STRING:
                    if (check) {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                    find = false;
                    break;
                case CssTypes.CSS_URL:
                    if (getImage() == null) {
                        setImage(new CssBackgroundImageCSS2(ac, expression));
                        continue;
                    }
                    find = false;
                    break;
                case CssTypes.CSS_COLOR:
                    if (getColor2() == null) {
                        setColor(new CssBackgroundColorCSS21(ac, expression));
                        continue;
                    }
                    find = false;
                    break;
                case CssTypes.CSS_NUMBER:
                case CssTypes.CSS_PERCENTAGE:
                case CssTypes.CSS_LENGTH:
                    if (background_position_expression == null) {
                        background_position_expression = new CssExpression();
                    }
                    background_position_expression.addValue(val);
                    expression.next();
                    find = true;
                    break;
                case CssTypes.CSS_IDENT:
                    // the hard part, as ident can be from different subproperties
                    find = false;
                    CssIdent identval = (CssIdent) val;
                    // check background-image ident
                    if (CssBackgroundImageCSS2.checkMatchingIdent(identval)) {
                        if (getImage() == null) {
                            setImage(new CssBackgroundImageCSS2(ac, expression));
                            find = true;
                        }
                        break;
                    }
                    // check background-repeat ident
                    if (CssBackgroundRepeatCSS2.checkMatchingIdent(identval)) {
                        if (getRepeat() == null) {
                            setRepeat(new CssBackgroundRepeatCSS2(ac, expression));
                            find = true;
                        }
                        break;
                    }
                    // check background-attachment ident
                    if (CssBackgroundAttachmentCSS2.checkMatchingIdent(identval)) {
                        if (getAttachment() == null) {
                            setAttachment(new CssBackgroundAttachmentCSS2(ac,
                                    expression));
                            find = true;
                        }
                        break;
                    }
                    // check backgorund-position ident
                    if (CssBackgroundPositionCSS21.checkMatchingIdent(identval)) {
                        if (background_position_expression == null) {
                            background_position_expression = new CssExpression();
                        }
                        background_position_expression.addValue(val);
                        expression.next();
                        find = true;
                        break;
                    }

                    if (getColor2() == null) {
                        try {
                            setColor(new CssBackgroundColorCSS21(ac, expression));
                            find = true;
                            break;
                        } catch (InvalidParamException e) {
                            // nothing to do, image will test this value
                        }
                    }

                default:
                    if (check) {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                    find = false;
            }
            if (check && !find) {
                throw new InvalidParamException("unrecognize", ac);
            }
            if (op != SPACE) {
                throw new InvalidParamException("operator",
                        Character.toString(op),
                        ac);
            }
        }
        if (background_position_expression != null) {
            setPosition(new CssBackgroundPositionCSS21(ac,
                    background_position_expression,
                    check));
        }
    }

    public CssBackgroundCSS21(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}
