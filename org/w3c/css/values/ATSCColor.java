//
// $Id: ATSCColor.java,v 1.9 2010-01-05 13:50:00 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import java.util.HashMap;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 *   <H3>
 *     &nbsp;&nbsp; Color units
 *   </H3>
 *   <P>
 *   A color is a either a keyword or a numerical RGB specification.
 *   <P>
 *   The suggested list of keyword color names is: aqua, black, blue, fuchsia,
 *   gray, green, lime, maroon, navy, olive, purple, red, silver, teal, white,
 *   and yellow. These 16 colors are taken from the Windows VGA palette, and their
 *   RGB values are not defined in this specification.
 *   <PRE>
 *   BODY {color: black; background: white }
 *   H1 { color: maroon }
 *   H2 { color: olive }
 * </PRE>
 *   <P>
 *   The RGB color model is being used in numerical color specifications. These
 *   examples all specify the same color:
 *   <PRE>
 *   EM { color: #f00 }              / * #rgb * /
 *   EM { color: #ff0000 }           / * #rrggbb * /
 *   EM { color: rgb(255,0,0) }      / * integer range 0 - 255 * /
 *   EM { color: rgb(100%, 0%, 0%) } / * float range 0.0% - 100.0% * /
 * </PRE>
 *   <P>
 *   The format of an RGB value in hexadecimal notation is a '#' immediately followed
 *   by either three or six hexadecimal characters. The three-digit RGB notation
 *   (#rgb) is converted into six-digit form (#rrggbb) by replicating digits,
 *   not by adding zeros. For example, #fb0 expands to #ffbb00. This makes sure
 *   that white (#ffffff) can be specified with the short notation (#fff) and
 *   removes any dependencies on the color depth of the display.
 *   <P>
 *   The format of an RGB value in the functional notation is 'rgb(' followed
 *   by a comma-separated list of three numerical values (either three integer
 *   values in the range of 0-255, or three percentage values in the range of
 *   0.0% to 100.0%) followed by ')'. Whitespace characters are allowed around
 *   the numerical values.
 *   <P>
 *   Values outside the numerical ranges should be clipped. The three rules below
 *   are therefore equivalent:
 *   <PRE>
 *   EM { color: rgb(255,0,0) }       / * integer range 0 - 255 * /
 *   EM { color: rgb(300,0,0) }       / * clipped to 255 * /
 *   EM { color: rgb(110%, 0%, 0%) }  / * clipped to 100% * /
 * </PRE>
 *   <P>
 *   RGB colors are specified in the sRGB color space <A HREF="#ref9">[9]</A>.
 *   UAs may vary in the fidelity with which they represent these colors, but
 *   use of sRGB provides an unambiguous and objectively measurable definition
 *   of what the color should be, which can be related to international standards
 *   <A HREF="#ref10">[10]</A>.
 *   <P>
 *   UAs may limit their efforts in displaying colors to performing a gamma-correction
 *   on them. sRGB specifies a display gamma of 2.2 under specified viewing
 *   conditions. UAs adjust the colors given in CSS such that, in combination
 *   with an output device's "natural" display gamma, an effective display gamma
 *   of 2.2 is produced. <A HREF="#appendix-d">Appendix D</A> gives further details
 *   of this. Note that only colors specified in CSS are affected; e.g., images
 *   are expected to carry their own color information.
 *
 *
 *   <H2>
 *     <A NAME="appendix-d">Appendix D: Gamma correction</A>
 *   </H2>
 *   <P>
 *   (This appendix is informative, not normative)
 *   <P>
 *   See the <A href="http://www.w3.org/pub/WWW/TR/PNG-GammaAppendix">Gamma
 *   Tutorial</A> in the PNG specification <A href="#ref12">[12]</A> if you aren't
 *   already familiar with gamma issues.
 *   <P>
 *   In the computation, UAs displaying on a CRT may assume an ideal CRT and ignore
 *   any effects on apparent gamma caused by dithering. That means the minimal
 *   handling they need to do on current platforms is:
 *   <DL>
 *     <DT>
 *       PC using MS-Windows
 *     <DD>
 *       none
 *     <DT>
 *       Unix using X11
 *     <DD>
 *       none
 *     <DT>
 *       Mac using QuickDraw
 *     <DD>
 *       apply gamma 1.39 <A HREF="#ref13">[13]</A> (ColorSync-savvy applications
 *       may simply pass the sRGB ICC profile <A HREF="#ref14">[14]</A> to ColorSync
 *       to perform correct color correction)
 *     <DT>
 *       SGI using X
 *     <DD>
 *       apply the gamma value from <TT>/etc/config/system.glGammaVal</TT> (the default
 *       value being 1.70; applications running on Irix 6.2 or above may simply pass
 *       the sRGB ICC profile to the color management system)
 *     <DT>
 *       NeXT using NeXTStep
 *     <DD>
 *       apply gamma 2.22
 *   </DL>
 *   <P>
 *   "Applying gamma" means that each of the three R, G and B must be converted
 *   to R'=R<SUP>gamma</SUP>, G'=G<SUP>gamma</SUP>, G'=B<SUP>gamma</SUP>, before
 *   handing to the OS.
 *   <P>
 *   This may rapidly be done by building a 256-element lookup table once per
 *   browser invocation thus:
 *   <PRE>
 *   for i := 0 to 255 do
 *     raw := i / 255;
 *     corr := pow (raw, gamma);
 *     table[i] := trunc (0.5 + corr * 255.0)
 *   end
 * </PRE>
 *   <P>
 *   which then avoids any need to do transcendental math per color attribute,
 *   far less per pixel.

 * See also
 * <P>
 * <A NAME="ref9">[9]</A> M Anderson, R Motta, S Chandrasekar, M Stokes:
 * "<A HREF="http://www.hpl.hp.com/personal/Michael_Stokes/srgb.htm">Proposal
 * for a Standard Color Space for the Internet - sRGB</A>"
 * (http://www.hpl.hp.com/personal/Michael_Stokes/srgb.htm)
 *   <P>
 *  <A NAME="ref10">[10]</A> CIE Publication 15.2-1986,
 *  "<A HREF="http://www.hike.te.chiba-u.ac.jp/ikeda/CIE/publ/abst/15-2-86.html">Colorimetry,
 *  Second Edition</A>", ISBN 3-900-734-00-3
 *  (http://www.hike.te.chiba-u.ac.jp/ikeda/CIE/publ/abst/15-2-86.html)
 * <P>
 * <A NAME="ref12">[12]</A>
 * "<A href="http://www.w3.org/pub/WWW/TR/REC-png-multi.html">PNG (Portable
 * Network Graphics) Specification, Version 1.0 specification</A>"
 * (http://www.w3.org/pub/WWW/TR/REC-png-multi.html)
 * <P>
 * <A NAME="ref13">[13]</A> Charles A. Poynton:
 * "<A HREF="ftp://ftp.inforamp.net/pub/users/poynton/doc/Mac/Mac_gamma.pdf">Gamma
 * correction on the Macintosh Platform</A>"
 * (ftp://ftp.inforamp.net/pub/users/poynton/doc/Mac/Mac_gamma.pdf)
 * <P>
 * <A NAME="ref14">[14]</A> International Color Consortium:
 * "<A HREF="ftp://sgigate.sgi.com/pub/icc/ICC32.pdf">ICC Profile Format
 *  Specification, version 3.2</A>", 1995 (ftp://sgigate.sgi.com/pub/icc/ICC32.pdf)
 *
 * @version $Revision: 1.9 $
 */
public class ATSCColor extends CssValue
        implements CssColorConstants, CssOperator {

    Object color;
    RGBATSC rgb;
    static HashMap<String,Object> definedColorsATSC;
    static CssIdent inherit = new CssIdent("inherit");

    /**
     * Create a new CssColor.
     */
    public ATSCColor() {
	color = inherit;
    }

    /**
     * Create a new ATSCColor with a color name.
     *
     * @param s The name color.
     * @exception InvalidParamException the color is incorrect
     */
    public ATSCColor(ApplContext ac, String s) throws InvalidParamException {
	//	setIdentColor(s.toLowerCase(), ac);
	setIdentColor(s, ac);
    }

    /**
     * Set the value from a defined color RBG.
     *
     * @param s the string representation of the color.
     * @exception InvalidParamException the color is incorrect.
     */
    public void set(String s, ApplContext ac)  throws InvalidParamException {
	if (s.startsWith("#")) {
	    setShortRGBColor(s.toLowerCase(), ac);
	} else {
	    setIdentColor(s, ac);
	}
    }

    /**
     * Return the internal value.
     */
    public Object get() {
	if (color != null) {
	    if (color == inherit) {
		return null;
	    } else {
		return color;
	    }
	} else {
	    return rgb.r;
	}
    }

    /**
     * Returns <code>true</code> if the internal value is the default value
     * (e.g. 'inherited').
     */
    public boolean isDefault() {
	return color == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (color != null) {
	    if (color == inherit) {
		return inherit.toString();
	    } else {
		return color.toString();
	    }
	} else {
	    return rgb.toString();
	}
    }

    /**
     * Parse a RGB color.
     * format rgb(<num>%?, <num>%?, <num>%?)
     */
    public void setRGBColor(CssExpression exp, ApplContext ac)
  	    throws InvalidParamException {
	CssValue val = exp.getValue();
	char op = exp.getOperator();
	color = null;
	rgb = new RGBATSC();

	if (val == null || op != COMMA) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (val instanceof CssNumber) {
	    rgb.r = ((Float) val.get());//.intValue();
	} else if (val instanceof CssPercentage) {
	    rgb.r = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("rgb", val, ac);
	}

	exp.next();
	val = exp.getValue();
	op = exp.getOperator();

	if (val == null || op != COMMA) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (val instanceof CssNumber) {
	    rgb.g = ((Float) val.get());//.intValue();
	} else if (val instanceof CssPercentage) {
	    rgb.g = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("rgb", val, ac);
	}

	exp.next();
	val = exp.getValue();
	op = exp.getOperator();

	if (val == null) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (val instanceof CssNumber) {
	    rgb.b = ((Float) val.get());//.intValue();
	} else if (val instanceof CssPercentage) {
	    rgb.b = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("rgb", val, ac);
	}

	exp.next();
	if (exp.getValue() != null) {
	    throw new InvalidParamException("rgb", exp.getValue(), ac);
	}
    }

    public void setATSCrgba(CssExpression exp, ApplContext ac)
	throws InvalidParamException{

	CssValue val = exp.getValue();
	char op = exp.getOperator();
	color = null;
	rgb = new RGBATSC();

	if (val == null || op != COMMA) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (val instanceof CssNumber) {
	    rgb.r = ((Float) val.get());//.intValue();
	} else if (val instanceof CssPercentage) {
	    rgb.r = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("ATSCrgb", val, ac);
	}

	exp.next();
	val = exp.getValue();
	op = exp.getOperator();

	if (val == null || op != COMMA) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (val instanceof CssNumber) {
	    rgb.g = ((Float) val.get());//.intValue();
	} else if (val instanceof CssPercentage) {
	    rgb.g = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("ATSCrgb", val, ac);
	}

	exp.next();
	val = exp.getValue();
	op = exp.getOperator();

	if (val == null) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (val instanceof CssNumber) {
	    rgb.b = ((Float) val.get());//.intValue();
	} else if (val instanceof CssPercentage) {
	    rgb.b = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("ATSCrgb", val, ac);
	}

	exp.next();
	val = exp.getValue();
	op = exp.getOperator();

	if (val == null) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (val instanceof CssNumber) {
	    rgb.o = ((Float) val.get());//.intValue();
	} else if (val instanceof CssPercentage) {
	    rgb.o = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("ATSCrgb", val, ac);
	}

	exp.next();
	if (exp.getValue() != null) {
	    throw new InvalidParamException("ATSCrgb", exp.getValue(), ac);
	}

    }

    /**
     * Parse a RGB color.
     * format #(3-6)<hexnum>
     */
    private void setShortRGBColor(String s, ApplContext ac)
	    throws InvalidParamException {
	int r;
	int g;
	int b;

	rgb = new RGBATSC();
	color = null;
	s = s.substring(1);

	if (s.length() != 3 && s.length() != 6) {
	    throw new InvalidParamException("rgb", s, ac);
	}
	if (s.length() == 3) {
	    String sh = s.substring(0,1);
	    r = Integer.parseInt(sh+sh, 16);
	    sh = s.substring(1,2);
	    g = Integer.parseInt(sh+sh, 16);
	    sh = s.substring(2,3);
	    b = Integer.parseInt(sh+sh, 16);
	} else {
	    r = Integer.parseInt(s.substring(0,2), 16);
	    g = Integer.parseInt(s.substring(2,4), 16);
	    b = Integer.parseInt(s.substring(4,6), 16);
	}
	rgb.r = new Integer(r);
	rgb.g = new Integer(g);
	rgb.b = new Integer(b);
	rgb.output = "#" + s;
    }

    /**
     * Parse an ident color.
     */
    private void setIdentColor(String s, ApplContext ac)
	    throws InvalidParamException {
	String lower_s = s.toLowerCase();
	Object obj = definedColorsATSC.get(lower_s);
	if (obj != null) {
	    if (obj instanceof RGBATSC) {
		color = lower_s;
		rgb = (RGBATSC) obj;
	    } else if (obj instanceof String) {
		color = (String) obj;
// 2007-05 - this warning on color string capitalization is plain silly, 
// commenting it out-- ot@w3.org
//		if (!obj.equals(s)) {
//		    ac.getFrame().addWarning("color.mixed-capitalization",
//					     s);
//		}
	    }
	    return;
	}

	throw new InvalidParamException("value", s, "color", ac);
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object cssColor) {
	return ((cssColor instanceof ATSCColor) &&
		((color != null && color.equals(((ATSCColor) cssColor).color))
		 || ((color == null)
		     && (rgb != null)
		     && (((ATSCColor) cssColor).rgb != null)
		     && (rgb.r.equals(((ATSCColor) cssColor).rgb.r)
			 && rgb.g.equals(((ATSCColor) cssColor).rgb.g)
			 && rgb.b.equals(((ATSCColor) cssColor).rgb.b)))));
    }

    /**
     * Gets the red component of this color.
     */
    public Object getRed() {
	return rgb.r;
    }

    /**
     * Gets the green component of this color.
     */
    public Object getGreen() {
	return rgb.g;
    }

    /**
     * Gets the blue component of this color.
     */
    public Object getBlue() {
	return rgb.b;
    }

    static {
	definedColorsATSC = new HashMap<String,Object>();
	definedColorsATSC.put("black",
			  new RGBATSC(new Integer(0),
				      new Integer(0),
				      new Integer(0),
				      new Integer(255)));
	definedColorsATSC.put("silver",
			  new RGBATSC(new Integer(192),
				      new Integer(192),
				      new Integer(192),
				      new Integer(255)));
	definedColorsATSC.put("gray",
			  new RGBATSC(new Integer(128),
				      new Integer(128),
				      new Integer(128),
				      new Integer(255)));
	definedColorsATSC.put("white",
			  new RGBATSC(new Integer(255),
				      new Integer(255),
				      new Integer(255),
				      new Integer(255)));
	definedColorsATSC.put("maroon",
			  new RGBATSC(new Integer(128),
				      new Integer(0),
				      new Integer(0),
				      new Integer(255)));
	definedColorsATSC.put("red",
			  new RGBATSC(new Integer(255),
				      new Integer(0),
				      new Integer(0),
				      new Integer(255)));
	definedColorsATSC.put("purple",
			  new RGBATSC(new Integer(128),
				      new Integer(0),
				      new Integer(128),
				      new Integer(255)));
	definedColorsATSC.put("fuchsia",
			  new RGBATSC(new Integer(255),
				      new Integer(0),
				      new Integer(255),
				      new Integer(255)));
	definedColorsATSC.put("green",
			  new RGBATSC(new Integer(0),
				      new Integer(128),
				      new Integer(0),
				      new Integer(255)));
	definedColorsATSC.put("lime",
			  new RGBATSC(new Integer(0),
				      new Integer(255),
				      new Integer(0),
				      new Integer(255)));
	definedColorsATSC.put("olive",
			  new RGBATSC(new Integer(128),
				      new Integer(128),
				      new Integer(0),
				      new Integer(255)));
	definedColorsATSC.put("yellow",
			  new RGBATSC(new Integer(255),
				      new Integer(255),
				      new Integer(0),
				      new Integer(255)));
	definedColorsATSC.put("navy",
			  new RGBATSC(new Integer(0),
				      new Integer(0),
				      new Integer(128),
				      new Integer(255)));
	definedColorsATSC.put("blue",
			  new RGBATSC(new Integer(0),
				      new Integer(0),
				      new Integer(255),
				      new Integer(255)));
	definedColorsATSC.put("teal",
			  new RGBATSC(new Integer(0),
				      new Integer(128),
				      new Integer(128),
				      new Integer(255)));
	definedColorsATSC.put("aqua",
			  new RGBATSC(new Integer(0),
				      new Integer(255),
				      new Integer(255),
				      new Integer(255)));
        definedColorsATSC.put("activeborder", "ActiveBorder");
        definedColorsATSC.put("activecaption", "ActiveCaption");
        definedColorsATSC.put("appworkspace", "AppWorkspace");
        definedColorsATSC.put("background", "Background");
        definedColorsATSC.put("buttonface", "ButtonFace");
        definedColorsATSC.put("buttonhighlight", "ButtonHighlight");
        definedColorsATSC.put("buttonshadow", "ButtonShadow");
        definedColorsATSC.put("buttontext", "ButtonText");
        definedColorsATSC.put("captiontext", "CaptionText");
        definedColorsATSC.put("graytext", "GrayText");
        definedColorsATSC.put("highlight", "Highlight");
        definedColorsATSC.put("highlighttext", "HighlightText");
        definedColorsATSC.put("inactiveborder", "InactiveBorder");
        definedColorsATSC.put("inactivecaption", "InactiveCaption");
        definedColorsATSC.put("inactivecaptiontext", "InactiveCaptionText");
        definedColorsATSC.put("infobackground", "InfoBackground");
        definedColorsATSC.put("infotext", "InfoText");
        definedColorsATSC.put("menu", "Menu");
        definedColorsATSC.put("menutext", "MenuText");
        definedColorsATSC.put("scrollbar", "Scrollbar");
        definedColorsATSC.put("threeddarkshadow", "ThreeDDarkShadow");
        definedColorsATSC.put("threedface", "ThreeDFace");
        definedColorsATSC.put("threedhighlight", "ThreeDHighlight");
        definedColorsATSC.put("threedlightshadow", "ThreeDLightShadow");
        definedColorsATSC.put("threedshadow", "ThreeDShadow");
        definedColorsATSC.put("window", "Window");
        definedColorsATSC.put("windowframe", "WindowFrame");
        definedColorsATSC.put("windowtext", "WindowText");
    }

}

class RGBATSC {

    String output = null;
    Object r;
    Object g;
    Object b;
    Object o;

    /**
     * Create a new RGBATSC
     */
    public RGBATSC() {
    }

    /**
     * Create a new RGBATSC with default values
     */
    public RGBATSC(Object r, Object g, Object b, Object o) {
	this.r = r;
	this.g = g;
	this.b = b;
	this.o = o;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (output == null) {
	    return "rgb("+r+","+g+","+b+","+o+")";
	} else {
	    return output;
	}
    }
}

