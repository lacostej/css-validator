// $Id: ErrorReport.java,v 1.1 2005-07-22 09:45:01 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.error;

import java.io.PrintWriter;

/**
 * ErrorReport<br />
 * Created: Jul 13, 2005 2:05:41 PM<br />
 */
public abstract class ErrorReport {
    abstract public void print(PrintWriter out);
}
