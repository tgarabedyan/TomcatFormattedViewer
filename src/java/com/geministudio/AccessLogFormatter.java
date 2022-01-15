/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geministudio;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tgarabedyan
 */
public class AccessLogFormatter {
    public static String TableHeader = "<th width=\"120px\">Source IP</th><th width=\"120px\">Time</th><th width=\"100px\">Method</th><th width=\"80%\">Context path</th><th>Status</th><th align=\"center\">ms</th>";
    
    public AccessLogFormatter() {
    }
    
    public String formatAsHtml(String line, String cssClass) {
        String[] parts = line.split(" ");
        String out = "<tr class=\"".concat(cssClass).concat("\"><td>").concat(parts[0]).concat("</td>");
        //if (parts.length>1)
        //    out = out.concat("<td>").concat(parts[1]).concat("</td>");
        //if (parts.length>2 && !parts[2].contains("["))
        //    out = out.concat("<td>").concat(parts[2]).concat("</td>");      
        if (parts.length>3 && parts[3].contains("["))
            //out = out.concat("<td>").concat(parts[3]).concat(parts[4]).concat("</td>");
            out = out.concat("<td>").concat(parts[3].substring(parts[3].indexOf(":")+1)).concat("</td>");
        if (parts.length>6) {
            String message = line.substring(line.indexOf("\""), line.lastIndexOf("\""));
            try {
                message = URLDecoder.decode(message, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(AccessLogFormatter.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                out = out.concat("<td>").concat(message.substring(1, message.indexOf(" "))).concat("</td>");
                out = out.concat("<td>").concat(message.substring(message.indexOf(" ")+1)).concat("</td>");
            } catch (IndexOutOfBoundsException ignore) {
                out=out.concat("<td>&nbsp;</td><td>").concat(message).concat("</td>");
            }
        }
        if (parts.length>8)
            out = out.concat("<td>").concat(parts[8]).concat("</td>");
        else
            out = out.concat("<td>&nbsp;</td>");
        if (parts.length>9)
            out = out.concat("<td align=\"right\">").concat(parts[9]).concat("</td>");
        else
            out = out.concat("<td>&nbsp;</td>");        
        out = out.concat("</tr>");
        return out;
    }
}
