// $Id: CssColorCSS21.java,v 1.7 2010-01-05 19:49:51 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import static org.w3c.css.values.CssColorConstants.SYSTEMCOLORS;
import static org.w3c.css.values.CssColorConstants.COLORNAME_CSS21;

/**
 * CssColorCSS21<br />
 * Created: Aug 30, 2005 1:53:59 PM<br />
 */
public class CssColorCSS21 extends CssColorCSS2 {

    private static int[] tableColorHash;
    private static int[] tableSystemColorHash;

    static {
        // We create the two table containing hashvalues of each color
        tableColorHash = new int[COLORNAME_CSS21.length];
        for (int i = 0; i < COLORNAME_CSS21.length; i++) {
            tableColorHash[i] = COLORNAME_CSS21[i].toLowerCase().hashCode();
        }

        tableSystemColorHash = new int[SYSTEMCOLORS.length];
        for (int i = 0; i < tableSystemColorHash.length; i++) {
            tableSystemColorHash[i] = SYSTEMCOLORS[i].toLowerCase().hashCode();
        }
    }

    public CssColorCSS21(ApplContext ac, String s)
            throws InvalidParamException {
        setIdentColor(s, ac);
    }

    /**
     * Parse an ident color.
     */
    private void setIdentColor(String s, ApplContext ac)
            throws InvalidParamException {
        String lower_s = s.toLowerCase();
        int hash = lower_s.hashCode();

        int indexOfColor = searchColor(hash, tableColorHash);

        if (indexOfColor != -1) {
            computeIdentColor(definedColors, COLORNAME_CSS21[indexOfColor]);
        } else {
            // the color has not been found, search it the system colors
            indexOfColor = searchColor(hash, tableSystemColorHash);
            if (indexOfColor != -1) {
                computeIdentColor(definedColors, SYSTEMCOLORS[indexOfColor]);
            } else {
                // the color does not exist in this profile, this is an error
                throw new InvalidParamException("value", s, "color", ac);
            }
        }
    }

    private int searchColor(int colorHash, int[] tableColorHash) {
        for (int i = 0; i < tableColorHash.length; i++) {
            if (tableColorHash[i] == colorHash) {
                return i;
            }
        }
        return -1;
    }

}
