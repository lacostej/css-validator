//
// $Id: Css2Properties.java,v 1.3 2010-01-05 13:49:46 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.Utf8Properties;

import java.net.URL;

/**
 * @version $Revision: 1.3 $
 */
public class Css2Properties {
    public static Utf8Properties properties;

    public static String getString(CssProperty property, String prop) {
        return properties.getProperty(property.getPropertyName() + "." + prop);
    }

    public static boolean getInheritance(CssProperty property) {
        return getString(property, "inherited").equals("true");
    }

    static {
        properties = new Utf8Properties();
        try {
            URL url;
            url = Css2Properties.class.getResource("CSS2Default.properties");
            properties.load(url.openStream());
        } catch (Exception e) {
            System.err.println("org.w3c.css.properties2.Css2Properties: " +
                    "couldn't load properties ");
            System.err.println("  " + e.toString());
        }
    }
}
