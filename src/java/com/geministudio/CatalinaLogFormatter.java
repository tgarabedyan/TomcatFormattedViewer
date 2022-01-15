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
public class CatalinaLogFormatter {
    public static String TableHeader = "<th width=\"120px\">Time</th><th width=\"100px\">Level</th><th>Executor</th><th width=\"75%\">Message</th>";
    
    public CatalinaLogFormatter() {
    }
    
    public String formatAsHtml(String line, String cssClass) {
        String[] parts = line.split(" ");
        //skip 1st part
        if (line.contains("WARNING"))
            cssClass = cssClass.concat(" warning");
        if (line.contains("SEVERE"))
            cssClass = cssClass.concat(" severe");
        String out = "";
        boolean addendumLine = false;
        if (parts.length>1 && parts[1].matches("(\\d\\d:\\d\\d:\\d\\d).*"))
             out = "<tr class=\"".concat(cssClass).concat("\"><td>").concat(parts[1]).concat("</td>");
        else { 
            out = "<tr class=\"".concat(cssClass).concat("\"><td colspan='3'>&nbsp;</td><td>").concat(line).concat("</td>");
            addendumLine = true;
        }
        if (parts.length>2 && !addendumLine)
            out = out.concat("<td>").concat(parts[2]).concat("</td>");      
        if (parts.length>3 && !addendumLine)
            out = out.concat("<td>").concat(parts[3]).concat("</td>");
        if (parts.length>4 && !addendumLine)
            out = out.concat("<td>").concat(line.substring(line.indexOf("]")+1)).concat("</td>");        
        out = out.concat("</tr>");
        return out;
    }
}
