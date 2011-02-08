// InterruptedGetException.java
// $Id: InterruptedGetException.java,v 1.5 2007-11-26 05:07:17 ot Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 1999.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.util;

import java.io.IOException;

/**
 * Thrown when a HTTP Get is interrupted
 * @version $Revision: 1.5 $
 * @author  Benoit Mahe (bmahe@w3.org)
 */
public class InterruptedGetException extends IOException {

    public long bytesTransferred = 0;
    public long bytesExpected    = 0;

    public InterruptedGetException(String message) {
	super(message);
    }

}
