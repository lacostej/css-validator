/*
 * (c) COPYRIGHT 1995-1999 MIT, INRIA and Keio University. All Rights reserved.
 * W3C Intellectual Property Notice and Legal Disclaimers:
 *  http://www.w3.org/Consortium/Legal/
 *
 * HTTPURL.java
 * $Id: HTTPURL.java,v 1.22 2009-02-15 18:23:48 ylafon Exp $
 */
package org.w3c.css.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import java.util.zip.GZIPInputStream;

import org.w3c.www.mime.MimeType;
import org.w3c.www.mime.MimeTypeFormatException;

import org.apache.velocity.io.UnicodeInputStream;

/**
 * @version $Revision: 1.22 $
 * @author  Philippe Le Hegaret
 */
public class HTTPURL {

    /**
     * Don't create this class
     */
    private HTTPURL() {
    }


    public static String getHTTPStatusCode(int status) {
	switch (status) {
	case 100:
	    return "Continue";
	case 101:
	    return "Switching Protocols";
	case 200:
	    return "OK";
	case 201:
	    return "Created";
	case 202:
	    return "Accepted";
	case 203:
	    return "Non-Authoritative Information";
	case 204:
	    return "No Content";
	case 205:
	    return "Reset Content";
	case 206:
	    return "Partial Content";
	case 300:
	    return "Multiple Choices";
	case 301:
	    return "Moved Permanently";
	case 302:
	    return "Found";
	case 303:
	    return "See Other";
	case 304:
	    return "Not Modified";
	case 305:
	    return "Use Proxy";
	case 306:
	    return "(Unused)";
	case 307:
	    return "Temporary Redirect";
	case 400:
	    return "Bad Request";
	case 401:
	    return "Unauthorized";
	case 402:
	    return "Payment Required";
	case 403:
	    return "Forbidden";
	case 404:
	    return "Not Found";
	case 405:
	    return "Method Not Allowed";
	case 406:
	    return "Not Acceptable";
	case 407:
	    return "Proxy Authentication Required";
	case 408:
	    return "Request Timeout";
	case 409:
	    return "Conflict";
	case 410:
	    return "Gone";
	case 411:
	    return "Length Required";
	case 412:
	    return "Precondition Failed";
	case 413:
	    return "Request Entity Too Large";
	case 414:
	    return "Request-URI Too Long";
	case 415:
	    return "Unsupported Media Type";
	case 416:
	    return "Requested Range Not Satisfiable";
	case 417:
	    return "Expectation Failed";
	case 500:
	    return "Internal Server Error";
	case 501:
	    return "Not Implemented";
	case 502:
	    return "Bad Gateway";
	case 503:
	    return "Service Unavailable";
	case 504:
	    return "Gateway Timeout";
	case 505:
	    return "HTTP Version Not Supported";
	default:
	    return Integer.toString(status, 10);
	}
    }

    public static URL getURL(String url) throws IOException {
	// url = URLEncoder.encode(url);
	try {
	    return new URL(url);
	} catch (MalformedURLException e) {
	    //if (!url.startsWith("http:")) { // ook!? dkfj://wwww.3.org -> http://dkfj://www.w3.org
	    if(url.indexOf("://") == -1) { // the protocol is missing
	    	return new URL("http://" + url);
	    } else {
		throw (IOException) e.fillInStackTrace();
	    }
	}
    }

    public static URL getURL(URL base, String url)
	throws MalformedURLException
    {
	//	url = URLEncoder.encode(url);

	return new URL(base, url);
    }

    private static URLConnection getConnection(URL url, int count)
	throws IOException
    {
	return getConnection(url, count, null);
    }

    private static URLConnection getConnection(URL url, int count,
					       ApplContext ac)
	throws IOException
    {
	if (count > 5) {
	    throw new ProtocolException("Server redirected too many "+
					"times (5)");
	}

	if (Util.servlet) {
	    String protocol = url.getProtocol();
	if (! (
		("https".equalsIgnoreCase(protocol)) || ("http".equalsIgnoreCase(protocol))
	   )  ) {
 		System.err.println( "[WARNING] : someone is trying to get the file: "
 				    + url );
 		throw new FileNotFoundException("import " + url +
 						": Operation not permitted");
 	    }


	}

	URLConnection urlC = url.openConnection();

	if (Util.onDebug) {
	    System.err.println( "Accessing " + url);
	    if (ac.getCredential() != null) {
		System.err.println( "with [" + ac.getCredential() + ']');
	    }
	}
	// avoid all kind of caches
	urlC.setRequestProperty("Pragma", "no-cache");
	urlC.setRequestProperty("Cache-Control", "no-cache, no-store");
	// for the fun
	urlC.setRequestProperty("User-Agent",
				"Jigsaw/2.2.5 W3C_CSS_Validator_JFouffa/2.0");
	// relay authorization information
	if (ac.getCredential() != null) {
	    urlC.setRequestProperty("Authorization",ac.getCredential());
	}
	// relay languages
	if (ac.getLang() != null) {
	    if (ac.getLang().indexOf('*') == -1) {
		urlC.setRequestProperty("Accept-Language", ac.getLang() + ",*");
	    } else {
		urlC.setRequestProperty("Accept-Language",ac.getLang());
	    }
	}
	// should I put an Accept header?
	urlC.setRequestProperty("Accept",
				"text/css,text/html,text/xml,"
				+"application/xhtml+xml,application/xml,"
				+"image/svg+xml,*/*;q=0.1");

	urlC.connect();

	if (urlC instanceof HttpURLConnection) {
	    HttpURLConnection httpURL = (HttpURLConnection) urlC;
	    int status;
	    try {
		status = httpURL.getResponseCode();
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
		throw new FileNotFoundException(url + ": " +
						getHTTPStatusCode(404));
	    }

	    switch (status) {
	    case HttpURLConnection.HTTP_OK:
		// nothing to do
		break;
	    case HttpURLConnection.HTTP_MOVED_PERM:
	    case HttpURLConnection.HTTP_MOVED_TEMP:
		try {
		    URL u = getURL(httpURL.getHeaderField("Location"));
		    return getConnection(u, count+1, ac);
		} finally {
		    httpURL.disconnect();
		}
	    case HttpURLConnection.HTTP_UNAUTHORIZED:
		String realm = httpURL.getHeaderField("WWW-Authenticate");
		httpURL.disconnect();
		if (realm != null) {
		    throw new ProtocolException (realm);
		}
	    default:
		try {
		    if (httpURL.getResponseMessage() != null) {
			throw new FileNotFoundException(url + ": " +
						 httpURL.getResponseMessage());
		    } else {
			throw new FileNotFoundException(url + ": " +
						   getHTTPStatusCode(status));
		    }
		} finally {
		    httpURL.disconnect();
		}
	    }
	}
	return urlC;
    }

    public static URLConnection getConnection(URL url)
	throws IOException
    {
	return getConnection(url, 0);
    }

    public static URLConnection getConnection(URL url, ApplContext ac)
	throws IOException
    {
	return getConnection(url, 0, ac);
    }

    /* more madness */
    public static InputStream getInputStream(ApplContext ac, URLConnection uco) 
	throws IOException 
    {
	InputStream orig_stream = uco.getInputStream();
	String charset;
	String encoding;
	if (orig_stream == null) {
	    return orig_stream; // let it fail elsewhere
	}
	encoding = uco.getContentEncoding();
	// not set -> return
	if (encoding != null) {
	    if (encoding.equalsIgnoreCase("gzip")) {
		orig_stream = new GZIPInputStream(orig_stream);
	    }
	}
	charset = getCharacterEncoding(ac, uco);
	if ((charset == null) || (charset.regionMatches(true, 0, "utf", 0, 3))) {
	    UnicodeInputStream is = new UnicodeInputStream(orig_stream);
	    charset =  is.getEncodingFromStream();
	    if (charset != null) {
		ac.setCharsetForURL(uco.getURL(), charset);
	    }
	    return is;
	}
	return orig_stream;
    }

    public static String getCharacterEncoding(ApplContext ac, 
					      URLConnection uco) 
    {
	String charset = ac.getCharsetForURL(uco.getURL());
	if (charset != null) {
	    return charset;
	}
	String mtypestr = uco.getContentType();
	if (mtypestr == null) {
	    return mtypestr;
	}
	MimeType mt;
	try { 
	    mt = new MimeType(mtypestr);
	} catch (MimeTypeFormatException mex) {
	    return null;
	}
        charset =  mt.getParameterValue("charset");
	if (charset != null) {
	    ac.setCharsetForURL(uco.getURL(), charset);
	}
	return charset;
    }
    /**
     *
     */
    public static void main(String[] args)
	throws Exception
    {
        int c;
	InputStream in = HTTPURL.getConnection(
	                                     getURL(args[0])).getInputStream();

	while ((c = in.read()) != -1) {
	    System.err.print((char) c);
	}
	System.exit(0);
    }
}
