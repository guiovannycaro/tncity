/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 *
 * @author root
 */
public class VelocityUtil {

    public static String render(String contentTemplate, HashMap map) {
        /**
         * Prepare context data
         */
        VelocityContext context = new VelocityContext();

        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String k = it.next().toString();
            context.put(k, map.get(k));
        }

        StringWriter swOut = new StringWriter();

        /**
         * Merge data and template
         */
        Velocity.evaluate(context, swOut, "log-tag-name", contentTemplate);
        return swOut.toString();
    }

    public static String render(File fileVSL, HashMap map) {
        String contentTemplate = new Archivo(fileVSL).getContent();
        return VelocityUtil.render(contentTemplate, map);

    }

    public String firstUpper(String val) {
        return val.trim().substring(0, 1).toUpperCase() + val.trim().substring(1, val.trim().length()).trim();
    }

    public String firstLower(String val) {
        return val.trim().substring(0, 1).toLowerCase() + val.trim().substring(1, val.trim().length()).trim();
    }

    public static void main(String[] args) {
        String code = "#set ($name =\"Edwar\")"
                + "    <h1>Hello $name !  - $param - ${param2.toUpperCase()}</h1>";

        HashMap map = new HashMap();

        map.put("param", "Val. Parametro");
        map.put("param2", "abc");

        String codGen = VelocityUtil.render(code, map);
        System.out.println(">>GENERA:");
        System.out.println(codGen);

    }
}
