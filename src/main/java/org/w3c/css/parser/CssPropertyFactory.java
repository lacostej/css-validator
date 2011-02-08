//
// $Id: CssPropertyFactory.java,v 1.25 2011-01-12 15:01:56 tgambet Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.parser;

import org.w3c.css.properties.PropertiesLoader;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.WarningParamException;
import org.w3c.css.util.Utf8Properties;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @version $Revision: 1.25 $
 * @author Philippe Le Hegaret
 */
public class CssPropertyFactory implements Cloneable {

    // all recognized properties are here.
    private Utf8Properties properties;
	
    //all used profiles are here (in the priority order)
    private static String[] SORTEDPROFILES = PropertiesLoader.getProfiles();

    // private Utf8Properties allprops;

    // does not seem to be used
    // private String usermedium;

    public CssPropertyFactory getClone() {
	try {
	    return (CssPropertyFactory) clone();
	} catch (CloneNotSupportedException ex) {
	    ex.printStackTrace();
	    return null;
	}
    }

    /**
     * Create a new CssPropertyFactory
     */
    /*
     * public CssPropertyFactory(URL url, URL allprop_url) { properties = new
     * Utf8Properties(); InputStream f = null; try { f = url.openStream();
     * properties.load(f); } catch (IOException e) { e.printStackTrace(); }
     * finally { try { if (f != null) f.close(); } catch (Exception e) {
     * e.printStackTrace(); } // ignore }
     *  // list of all properties allprops = new Utf8Properties(); InputStream
     * f_all = null; try { f_all = allprop_url.openStream();
     * allprops.load(f_all); } catch (IOException e) { e.printStackTrace(); }
     * finally { try { if (f_all != null) f_all.close(); } catch (Exception e) {
     * e.printStackTrace(); } // ignore } }
     */

    public CssPropertyFactory(String profile) {
	properties = PropertiesLoader.getProfile(profile);
	// It's not good to have null properties :-/
	if (properties == null) {
	    throw new NullPointerException();
	}
    }

    public String getProperty(String name) {
	return properties.getProperty(name);
    }

    private Vector<String> getVector(String media) {
	Vector<String> list = new Vector<String>(4);
	String medium = new String();
	StringTokenizer tok = new StringTokenizer(media, ",");

	while (tok.hasMoreTokens()) {
	    medium = tok.nextToken();
	    medium = medium.trim();
	    list.addElement(medium);
	}

	return list;
    }

    // public void setUserMedium(String usermedium) {
    // this.usermedium = usermedium;
    // }

    // bug: FIXME
    // @media screen and (min-width: 400px) and (max-width: 700px), print {
    // a {
    // border: 0;
    // }
    // }
    public synchronized CssProperty createMediaFeature(ApplContext ac, AtRule atRule, String property,
						       CssExpression expression) throws Exception {
	// String result = "ok";
	String media = atRule.toString();
	int pos = -1;
	int pos2 = media.toUpperCase().indexOf("AND");

	if (pos2 == -1) {
	    pos2 = media.length();
	}

	if (media.toUpperCase().indexOf("NOT") != -1) {
	    pos = media.toUpperCase().indexOf("NOT");
	    media = media.substring(pos + 4, pos2);
	} else if (media.toUpperCase().indexOf("ONLY") != -1) {
	    pos = media.toUpperCase().indexOf("ONLY");
	    media = media.substring(pos + 4, pos2);
	} else {
	    pos = media.indexOf(" ");
	    media = media.substring(pos + 1, pos2);
	}

	media = media.trim();

	String classname = properties.getProperty("mediafeature" + "." + property);

	if (classname == null) {
	    if (atRule instanceof AtRuleMedia && (!media.equals("all"))) {
		// I don't know this property
		throw new InvalidParamException("noexistence-media", property, media, ac);
		// ac.getFrame().addWarning("noexistence-media", property);
		// classname = allprops.getProperty(property);
	    } else {
		// I don't know this property
		throw new InvalidParamException("noexistence", property, media, ac);
	    }
	}

	try {
	    // create an instance of your property class
	    Class expressionclass = CssExpression.class;
	    if (expression != null) {
		expressionclass = expression.getClass();
	    }
	    // Maybe it will be necessary to add the check parameter as for
	    // create property, so... FIXME
	    Class[] parametersType = { ac.getClass(), expressionclass };
	    Constructor constructor = Class.forName(classname).getConstructor(parametersType);
	    Object[] parameters = { ac, expression };
	    // invoke the constructor
	    return (CssProperty) constructor.newInstance(parameters);
	} catch (InvocationTargetException e) {
	    // catch InvalidParamException
	    InvocationTargetException iv = e;
	    Exception ex = (Exception) iv.getTargetException();
	    throw ex;
	}

    }

    public synchronized CssProperty createProperty(ApplContext ac, AtRule atRule, String property,
						   CssExpression expression) throws Exception {
	String classname = null;
	String media = atRule.toString();
	int pos = -1;
	String upperMedia = media.toUpperCase();
	int pos2 = upperMedia.indexOf("AND ");

	if (pos2 == -1) {
	    pos2 = media.length();
	}

	if ((pos = upperMedia.indexOf("NOT")) != -1) {
	    media = media.substring(pos + 4, pos2);
	} else if ((pos = upperMedia.indexOf("ONLY")) != -1) {
	    media = media.substring(pos + 4, pos2);
	} else {
	    pos = media.indexOf(' ');
	    media = media.substring(pos + 1, pos2);
	}

	media = media.trim();

	classname = setClassName(atRule, media, ac, property);
		
	// the property does not exist in this profile
	// this is an error... or a warning if it exists in another profile
	if (classname == null) {
            if (ac.getTreatVendorExtensionsAsWarnings() &&
                isVendorExtension(property)) {
                throw new WarningParamException("vendor-extension", property);
            }
	    ArrayList<String> pfsOk = new ArrayList<String>();
			
	    for (int i=0; i<SORTEDPROFILES.length; ++i) {
		String p = String.valueOf(SORTEDPROFILES[i]);
		if (!p.equals(ac.getCssVersion()) && PropertiesLoader.getProfile(p).containsKey(property)) {
		    pfsOk.add(p);
		}
	    }
			
	    if (pfsOk.size() > 0) {
		/*
		// This should be uncommented when no-profile in enabled
		if (ac.getProfile().equals("none")) {
		// the last one should be the best one to use
		String	pf = (String) pfsOk.get(pfsOk.size()-1),
		old_pf = ac.getCssVersion();
		ac.setCssVersion(pf);
		ac.getFrame().addWarning("noexistence", new String[] { property, ac.getMsg().getString(old_pf), pfsOk.toString() });
		classname = setClassName(atRule, media, ac, property);
		ac.setCssVersion(old_pf);
		}
		else
		*/
		throw new InvalidParamException("noexistence", new String[] { property, ac.getMsg().getString(ac.getCssVersion()), pfsOk.toString() }, ac);
	    } else {
		throw new InvalidParamException("noexistence-at-all", property, ac);
	    }
	}

	CssIdent initial = new CssIdent("initial");

	try {
	    if (expression.getValue().equals(initial) && ac.getCssVersion().equals("css3")) {
		// create an instance of your property class
		Class[] parametersType = {};
		Constructor constructor = Class.forName(classname).getConstructor(parametersType);
		Object[] parameters = {};
		// invoke the constructor
		return (CssProperty) constructor.newInstance(parameters);
	    } else {
		// create an instance of your property class
		Class[] parametersType = { ac.getClass(), expression.getClass(), boolean.class };
		Constructor constructor = Class.forName(classname).getConstructor(parametersType);
		Object[] parameters = { ac, expression, Boolean.TRUE};
		// invoke the constructor
		return (CssProperty) constructor.newInstance(parameters);
			
	    }
	} catch (InvocationTargetException e) {
	    // catch InvalidParamException
	    InvocationTargetException iv = e;
	    Exception ex = (Exception) iv.getTargetException();
	    throw ex;
	}
    }

    private String setClassName(AtRule atRule, String media, ApplContext ac, String property) {
	String className;
	Vector<String> list = new Vector<String>(getVector(media));
	if (atRule instanceof AtRuleMedia) {
	    className = PropertiesLoader.getProfile(ac.getCssVersion()).getProperty(property);
	    // a list of media has been specified
	    if (className != null && !media.equals("all")) {
		String propMedia = PropertiesLoader.mediaProperties.getProperty(property);
		for (int i = 0; i < list.size(); i++) {
		    String medium = list.elementAt(i);
		    if (propMedia.indexOf(medium.toLowerCase()) == -1 && !propMedia.equals("all")) {
			ac.getFrame().addWarning("noexistence-media", new String[] { property, medium + " (" + propMedia + ")" });
		    }
		}
	    }
	} else {
	    className = PropertiesLoader.getProfile(ac.getCssVersion()).getProperty("@" + atRule.keyword() + "." + property);
	}
	return className;
    }

    private boolean isVendorExtension(String property) {
      return property.length() > 0 &&
          (property.charAt(0) == '-' || property.charAt(0) == '_');
    }
}
