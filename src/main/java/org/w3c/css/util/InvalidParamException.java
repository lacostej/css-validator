//
// $Id: InvalidParamException.java,v 1.11 2010-01-08 21:38:01 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;


import org.w3c.css.parser.analyzer.ParseException;

/**
 * @version $Revision: 1.11 $
 */
public class InvalidParamException extends ParseException {

    /**
     * Create a new InvalidParamException.
     */
    public InvalidParamException() {
        super();
    }

    /**
     * Create a new InvalidParamException with an error message.
     *
     * @param message the error message
     */
    public InvalidParamException(String message, ApplContext ac) {
        super(ac.getMsg().getErrorString((message != null) ? message : ""));
    }

    /**
     * Create a new InvalidParamException with an error message class.
     *
     * @param error   the error message class.
     * @param message a message to add
     */
    public InvalidParamException(String error, Object message, ApplContext ac) {
        super(processError(error, (message != null) ? message : null, ac));
    }

    /**
     * Create a new InvalidParamException.
     *
     * @param error    the error message class
     * @param message1 the first message to add
     * @param message1 the second message to add
     */
    public InvalidParamException(String error, Object message1,
                                 Object message2, ApplContext ac) {
        super(processError(error,
                (message1 != null) ? message1.toString() : null,
                (message2 != null) ? message2.toString() : null,
                ac));
    }

    private static String processError(String error, Object args, ApplContext ac) {
        if (args instanceof String[]) {
            String[] s_args = (String[]) args;
            StringBuilder sb = new StringBuilder();
            String str = null;

            if (error != null) {
                str = ac.getMsg().getErrorString(error);
            }
            if (str == null) {
                return "can't find the error message for " + error;
            } else {
                // replace all parameters
                String[] msg_parts = str.split("%s");
                int j = 0;
                sb.append(msg_parts[0]);
                for (int i = 1; i < msg_parts.length; i++) {
                    if (j < s_args.length) {
                        sb.append(s_args[j++]);
                    }
                    sb.append(msg_parts[i]);
                }
                return sb.toString();
            }
        } else {
            return processError(error, args.toString(), "", ac);
        }
    }


    private static String processError(String error, String arg1,
                                       String arg2, ApplContext ac) {
        String str = null;

        if (error != null) {
            str = ac.getMsg().getErrorString(error);
        }
        if (str == null) {
            return "can't find the error message for " + error;
        } else {
            // replace all parameters
            int i;
            while ((i = str.indexOf("%s")) >= 0) {
                StringBuilder sb = new StringBuilder(str.substring(0, i));
                sb.append(arg1).append(str.substring(i + 2));
                str = sb.toString();
                arg1 = arg2;
            }
            return str;
        }
    }

} // InvalidParamException
