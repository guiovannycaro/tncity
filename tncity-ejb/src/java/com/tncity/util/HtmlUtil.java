/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author inmoticamaster
 */
public class HtmlUtil {

    private static byte[] compress(String text) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            OutputStream out = new DeflaterOutputStream(baos);
            out.write(text.getBytes("ISO-8859-1"));
            out.close();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        return baos.toByteArray();
    }

    private static String decompress(byte[] bytes) {
        InputStream in = new InflaterInputStream(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            return new String(baos.toByteArray(), "ISO-8859-1");
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public static String compressHtmlBase64(String html) {
        HtmlCompressor compressor = new HtmlCompressor();
        compressor.setCompressCss(true);
        compressor.setCompressJavaScript(true);

        String htmlC = compressor.compress(html);
        byte[] c = compress(htmlC);
        return Base64.encode(c);
    }

    public static String decompressHtmlBase64(String base64) {
        try {
            byte[] data = new Base64().decode(base64);
            return decompress(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;

    }

    public static String removeSpecialTags(String html) {
        String res = removeTags(html, "script");
        res = removeTags(res, "SCRIPT");
        res = removeTags(res, "link");
        res = removeTags(res, "LINK");
        res = removeTags(res, "STYLE");
        res = removeTags(res, "style");
        return res;
    }

    public static String removeTags(String message, String tag) {
        String scriptRegex = "<(/)?[ ]*" + tag + "[^>]*>";
        Pattern pattern2 = Pattern.compile(scriptRegex);

        if (message != null) {
            Matcher matcher2 = pattern2.matcher(message);
            StringBuffer str = new StringBuffer(message.length());
            while (matcher2.find()) {
                matcher2.appendReplacement(str, Matcher.quoteReplacement(" "));
            }
            matcher2.appendTail(str);
            message = str.toString();
        }
        return message;
    }

    public static List<String> listImgSrc(String html) {
        List<String> lst = new ArrayList<>();
        final String regex = "(?<=<img src=\")[^\"]*";
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(html);
        while (m.find()) {
            lst.add(m.group());
        }
        return lst;
    }

    public static void main(String[] args) {
        String html = "< SCRIPT >ok</script>";
        System.out.println(removeSpecialTags(html));

    }

}
