//
// $Id: DocumentParser.java,v 1.5 2010-06-18 16:18:10 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.Util;
import org.w3c.www.mime.MimeType;
import org.w3c.www.mime.MimeTypeFormatException;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @version $Revision: 1.5 $
 */
public final class DocumentParser {

    public static MimeType wap;

    static {
        try {
        wap = new MimeType("application/vnd.wap.xhtml+xml");
        } catch (MimeTypeFormatException mex) {
            wap = null;
        }
    }

    private StyleSheet style;
    private URL htmlURL;
    private Exception exception;
    private ApplContext ac;

    /**
     * Create a new DocumentParser
     * 
     * @exception Exception
     *                An error
     */
    public DocumentParser(ApplContext ac, String urlString) throws Exception {
	this.htmlURL = HTTPURL.getURL(urlString);
	this.ac = ac;
	urlString = htmlURL.toString();
	String urlLower = urlString.toLowerCase();
	String media = ac.getMedium();
	String urlProtocol = htmlURL.getProtocol();

	if (!"http".equals(urlProtocol) && !"https".equals(urlProtocol)) {
	    if (urlLower.endsWith(".css")) {
		StyleSheetParser parser = new StyleSheetParser();
		parser.parseURL(ac, htmlURL, null, null, media, StyleSheetOrigin.AUTHOR);
		style = parser.getStyleSheet();
	    } else if (urlLower.endsWith(".html") || urlLower.endsWith(".shtml") || urlLower.endsWith("/")) {
		TagSoupStyleSheetHandler handler = new TagSoupStyleSheetHandler(htmlURL, ac);
		handler.parse(htmlURL);
		style = handler.getStyleSheet();
		if (style != null) {
		    style.setType("text/html");
		}
	    } else if (urlLower.endsWith(".xhtml") || urlLower.endsWith(".xml")) {
		// Seems like we need to use tagsout in this case as well
		XMLStyleSheetHandler handler = new XMLStyleSheetHandler(htmlURL, ac);
		handler.parse(htmlURL);
		style = handler.getStyleSheet();
		if (style != null) {
		    style.setType("text/xml");
		}
	    } else {
		throw new Exception("Unknown file");
	    }
	} else {
	    URLConnection connection = null;

	    if ("https".equals(urlProtocol)) {
		    // Step 1: trust manager
		    // Create a trust manager that does not validate certificate chains
		    TrustManager[] trustAllCerts = new TrustManager[] {
			    new X509TrustManager() {
				    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					    return null;
				    }
				    public void checkClientTrusted(
					    java.security.cert.X509Certificate[] certs, String authType) {
				    }
				    public void checkServerTrusted(
						    java.security.cert.X509Certificate[] certs, String authType) {
					    }
			    }
		    };

		    // Install the all-trusting trust manager
		    try {
			    SSLContext sc = SSLContext.getInstance("SSL");
			    sc.init(null, trustAllCerts, new java.security.SecureRandom());
			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		    } catch (Exception e) {
		    }

		    // Step 2: hostname verifier
		    HostnameVerifier hv = new HostnameVerifier() {
			    public boolean verify(String urlHostName, SSLSession session) {
				    return true;
			    }
		    };
		    HttpsURLConnection.setDefaultHostnameVerifier(hv);
	    }

	    try {
		boolean isXML = false;
		String cType;

		// @@ hum, maybe? (plh, yes probably :-) )
		String credential = ac.getCredential();

		connection = HTTPURL.getConnection(htmlURL, ac);
		htmlURL = connection.getURL();

		String httpCL = connection.getHeaderField("Content-Location");
		if (httpCL != null) {
		    htmlURL = HTTPURL.getURL(htmlURL, httpCL);
		}

		cType = connection.getContentType();
		if (cType == null) {
		    cType = "unknown/unknown";
		}
		MimeType contentType = null;
		try {
		    contentType = new MimeType(cType);
		} catch (MimeTypeFormatException ex) {
		}

		if (Util.onDebug) {
		    System.err.println("[DEBUG] content type is [" + contentType + ']');
		}

		if (contentType.match(MimeType.TEXT_HTML) == MimeType.MATCH_SPECIFIC_SUBTYPE) {
		    TagSoupStyleSheetHandler handler;
		    handler = new TagSoupStyleSheetHandler(htmlURL, ac);
		    handler.parse(urlString, connection);
		    style = handler.getStyleSheet();

		    if (style != null) {
			style.setType("text/html");
		    }
		} else if (contentType.match(MimeType.TEXT_CSS) == MimeType.MATCH_SPECIFIC_SUBTYPE) {
		    StyleSheetParser parser = new StyleSheetParser();
		    parser.parseURL(ac, htmlURL, null, null, media, StyleSheetOrigin.AUTHOR);
		    style = parser.getStyleSheet();
		} else if ((contentType.match(MimeType.TEXT_XML) == MimeType.MATCH_SPECIFIC_SUBTYPE)
			   || (contentType.match(MimeType.APPLICATION_XHTML_XML) == MimeType.MATCH_SPECIFIC_SUBTYPE)
                || (contentType.match(wap) == MimeType.MATCH_SPECIFIC_SUBTYPE)) {
		    // TagSoup ?
		    XMLStyleSheetHandler handler = new XMLStyleSheetHandler(htmlURL, ac);
		    handler.parse(urlString, connection);
		    style = handler.getStyleSheet();

		    if (style != null) {
			style.setType("text/xml");
		    }
		} else {
		    throw new IOException("Unknown mime type : " + contentType);
		}
	    } finally {
		try {
		    connection.getInputStream().close();
		} catch (Exception e) {
		}
	    }
	}
    }

    /**
     * Returns the recognized style sheet.
     * 
     * @return A style sheet.
     */
    public StyleSheet getStyleSheet() {
	return style;
    }

} // HTMLStyleSheetParser
