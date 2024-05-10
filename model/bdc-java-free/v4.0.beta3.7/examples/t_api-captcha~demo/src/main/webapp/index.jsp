<%@page import="com.captcha.botdetect.SoundFormat"%>
<%@page import="com.captcha.botdetect.SoundStyle"%>
<%@page import="com.captcha.botdetect.ImageFormat"%>
<%@page import="java.lang.reflect.Modifier"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.awt.Color"%>
<%@page import="java.util.List"%>
<%@page import="com.captcha.botdetect.ImageStyle"%>
<%@page import="com.captcha.botdetect.CodeStyle"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BotDetect Java CAPTCHA Features Demo</title>
    <link rel="stylesheet" href="stylesheet.css" type="text/css"/>
</head>
<body>
    <form action="featuresAction" method="post" class="column" id="form1">
        <h1>BotDetect Java CAPTCHA Features Demo</h1>

        <fieldset>
            <legend>Java CAPTCHA validation</legend>
            <label for="captchaCode">Retype the characters from the picture:</label>
            
            <jsp:useBean id="featuresBean" class="com.captcha.botdetect.demos.features.bean.FeaturesBean" scope="request" />
            <jsp:setProperty name="featuresBean" property="*"/>
            
            <% 
                Captcha captcha = featuresBean.getCaptcha(request, "exampleCaptcha");
                String captchaHtml = captcha.getHtml();
                out.write(captchaHtml);
            %>
             
            <div class="validationDiv">
                <input name="captchaCode" type="text" id="captchaCode" />
                <input type="submit" name="validateCaptchaButton" value="Validate" id="ValidateCaptchaButton" />&nbsp;
                <span class="correct">${messages.captchaCorrect}</span>
                <span class="incorrect">${messages.captchaIncorrect}</span>
            </div>
        </fieldset>

        <fieldset id="codeProperties">
            <legend>CAPTCHA Code Properties</legend>
            <table cellpadding="5" cellspacing="0" summary="CAPTCHA Properties layout table">
                <tr>
                    <td class="left">
                        <label for="locale"><span>Locale:</span></label>
                    </td>
                    <td>
                        <select name="locale" id="locale">
                            <option <%= captcha.getLocale().equalsIgnoreCase("en-Latn-US") ? "selected" : "" %> value="en-US">en-US</option>
                            <option <%= captcha.getLocale().equalsIgnoreCase("en-Latn-CA") ? "selected" : "" %> value="en-CA">en-CA</option>
                            <option <%= captcha.getLocale().equalsIgnoreCase("fr-Latn-CA") ? "selected" : "" %> value="fr-CA">fr-CA</option>
                            <option <%= captcha.getLocale().equalsIgnoreCase("es-Latn-MX") ? "selected" : "" %> value="es-MX">es-MX</option>
                        </select>
                    </td>
                    <td></td>
                </tr>
                
                <tr>
                    <td class="left">
                        <label for="codeLength"><span>Code Length:</span></label>
                    </td>
                    <td>
                        <select name="codeLength" id="codeLength">
                            <option value="0" >[Default (4-6)]</option>
                            <% 
                                String codeLength = request.getParameter("codeLength");
                                for (int i = 1; i <= 15; i++) {
                            %>
                                    <option value="<%= i %>" <%= ((codeLength != null) && (codeLength.equalsIgnoreCase(Integer.toString(i)))) ? "selected" : "" %>><%= i %></option>
                            <%  } %>
                        </select>
                    </td>
                    <td></td>
                </tr>
                
                <tr>
                    <td class="left">
                        <label for="codeStyle"><span>Code Style:</span></label>
                    </td>
                    <td>
                        <select name="codeStyle" id="codeStyle">
                            <% 
                                String codeStyle = request.getParameter("codeStyle");
                                for (CodeStyle style : CodeStyle.values()) { %>
                                    <option value="<%= style.name() %>" <%= ((codeStyle != null) && (codeStyle.equalsIgnoreCase(style.name()))) ? "selected" : "" %>><%= style.name() %></option>
                            <%  } %>
                        </select>
                    </td>
                    <td></td>
                </tr>
            </table>
        </fieldset>
        
        <fieldset id="imageProperties">
            <legend>CAPTCHA Image Properties</legend>
            <table cellpadding="5" cellspacing="0" summary="CAPTCHA Properties layout table">
                <% if (!Captcha.isFree()) { %>
                <tr>
                    <td class="left">
                        <label for="imageStyle"><span>Image Algos</span></label>
                    </td>
                    <td>
                        <select name="imageStyle" id="imageStyle">
                            <option value="default">[Default]</option>
                            <% 
                                String imageStyle = request.getParameter("imageStyle");
                                for (ImageStyle style : ImageStyle.values()) { %>
                                    <option value="<%= style.name() %>" 
                                        <%= ((imageStyle != null) && (imageStyle.equalsIgnoreCase(style.name()))) ? "selected" : "" %> 
                                        <%= ImageStyle.getLicenceRestrictedStyles().contains(style) ? "disabled" : "" %> 
                                    ><%= style.name() %></option>
                            <%  } %>
                        </select>
                    </td>
                    <td></td>
                </tr>
                <% } %>
                
                <tr>
                    <td class="left">
                        <label for="customLightColor"><span>Custom Light Color:</span></label>
                    </td>
                    <td>
                        <select name="customLightColor" id="customLightColor">
                            <option value="default">[Default]</option>
                            <% 
                                String customLightColor = request.getParameter("customLightColor");
 
                                Field[] colorFields = Color.class.getDeclaredFields();
                                Field cf;
                                for (int i = 1; i < colorFields.length; i = i + 2) {
                                    cf = colorFields[i];
                                    if (Modifier.isStatic(cf.getModifiers()) && (cf.getType().getName().equalsIgnoreCase("java.awt.Color"))) { %>
                                        <option value="<%= cf.getName() %>" 
                                            <%= ((customLightColor != null) && (customLightColor.equalsIgnoreCase(cf.getName()))) ? "selected" : "" %> 
                                        ><%= cf.getName() %></option>
                            <%      }
                                } %>
                        </select>
                    </td>
                    <td></td>
                </tr>
                
                <tr>
                    <td class="left">
                        <label for="customDarkColor"><span>Custom Dark Color:</span></label>
                    </td>
                    <td>
                        <select name="customDarkColor" id="customDarkColor">
                            <option value="default">[Default]</option>
                            <% 
                                String customDarkColor = request.getParameter("customDarkColor");
 
                                for (int i = 1; i < colorFields.length; i = i + 2) {
                                    cf = colorFields[i];
                                    if (Modifier.isStatic(cf.getModifiers()) && (cf.getType().getName().equalsIgnoreCase("java.awt.Color"))) { %>
                                        <option value="<%= cf.getName() %>" 
                                            <%= ((customDarkColor != null) && (customDarkColor.equalsIgnoreCase(cf.getName()))) ? "selected" : "" %> 
                                        ><%= cf.getName() %></option>
                            <%      }
                                } %>
                        </select>
                    </td>
                    <td></td>
                </tr>
              
                <tr>
                    <td class="left">
                        <label for="imageFormat"><span>Image Format:</span></label>
                    </td>
                    <td>
                        <select name="imageFormat" id="imageFormat">
                            <% 
                                String imageFormat = request.getParameter("imageFormat");
                                for (ImageFormat format : ImageFormat.values()) { %>
                                    <option value="<%= format.name() %>" <%= ((imageFormat != null) && (imageFormat.equalsIgnoreCase(format.name()))) ? "selected" : "" %>><%= format.name() %></option>
                            <%  } %>
                        </select>
                    </td>
                    <td></td>
                </tr>
                
                <tr>
                    <td class="left">
                        <label for="imageWidth">Image Width:</label>
                    </td>
                    <td>
                        <input name="imageWidth" id="imageWidth" type="text" class="textboxSmall" value="${param.imageWidth == null ? 250 : param.imageWidth}" />&nbsp;px
                    </td>
                    <td></td>
                </tr>
                
                <tr>
                    <td class="left">
                        <label for="imageHeight">Image Height:</label>
                    </td>
                    <td>
                        <input name="imageHeight" id="imageHeight" type="text" class="textboxSmall" value="${param.imageHeight == null ? 50 : param.imageHeight}" />&nbsp;px
                    </td>
                    <td></td>
                </tr>
            </table>
        </fieldset>
        
        <fieldset id="audioProperties">
            <legend>CAPTCHA Audio Properties</legend>
            <table cellpadding="5" cellspacing="0" summary="CAPTCHA Properties layout table">
                <% if (!Captcha.isFree()) { %>
                <tr>
                    <td class="left">
                        <label for="soundStyle"><span>Sound Style:</span></label>
                    </td>
                    <td>
                        <select name="soundStyle" id="soundStyle">
                            <option value="default">[Default]</option>
                            <% 
                                String soundStyle = request.getParameter("soundStyle");
                                for (SoundStyle style : SoundStyle.values()) { %>
                                    <option value="<%= style.name() %>" 
                                        <%= ((soundStyle != null) && (soundStyle.equalsIgnoreCase(style.name()))) ? "selected" : "" %> 
                                        <%= SoundStyle.getLicenceRestrictedStyles().contains(style) ? "disabled" : "" %> 
                                    ><%= style.name() %></option>
                            <%  } %>
                        </select>
                    </td>
                    <td></td>
                </tr>
                <% } %>
                
                <tr>
                    <td class="left">
                        <label for="soundFormat"><span>Sound Format:</span></label>
                    </td>
                    <td>
                        <select name="soundFormat" id="soundFormat">
                            <% 
                                String soundFormat = request.getParameter("soundFormat");
                                for (SoundFormat format : SoundFormat.values()) { 
                                    if (!format.equals(SoundFormat.UNKNOWN)) { %>
                                        <option value="<%= format.name() %>" <%= ((soundFormat != null) && (soundFormat.equalsIgnoreCase(format.name()))) ? "selected" : "" %>><%= format.name() %></option>
                            <%      }
                                } %>
                        </select>
                    </td>
                    <td></td>
                </tr>
            </table>
        </fieldset>

        <div class="submit">
            <input type="submit" name="ApplyCaptchaSettings" value="Apply" id="ApplyButton" />
        </div>
    </form>

  <div class="column">
        <div class="column">
            <div class="note">
                <h3>Description</h3>
                <p>This demo allows you to easily experiment with various BotDetect parameters and their combinations, so you can see how powerful and customizable BotDetect Captcha is.</p>
                <p>Please note that values in brackets (such as <code>[Default]</code> and <code>[Random]</code>) are not actual parameter values you can use directly in your code.</p>
            </div>
            <div class="note" id="localization">
                <h3>Localization</h3>
                <p>BotDetect installations include pronunciation sound packages (required for localized Captcha sounds) only for North-American locales by deafult. The <code>Locale</code> drop-down list values relect this fact.</p>
                <p>You can specify any other locale string for the <code>BDC_locale</code> parameter value in <code>web.xml</code> file (e.g. <code>"en-GB"</code>, <code>"ru"</code>, <code>"zh-Hans"</code>). However, not all character sets might be supported yet, and you will have to download the pronunciation sound package separately from our site when it's available.</p>
                <p>You can always see the full list of locales for which we support both Captcha images and Captcha sounds &ndash; and download the required pronunciation sound packages &ndash; at the <a rel="nofollow" href="http://captcha.com/captcha-localizations.html?utm_source=installation&amp;utm_medium=java&amp;utm_campaign=4.0.Beta3.7" title="BotDetect 4 CAPTCHA Localizations">BotDetect 4 CAPTCHA localizations</a> page.</p>
            </div>
        </div>
    
        <div class="column">
            <% if (Captcha.isFree()) { %>
            <div class="note warning">
                <h3>Free Version Limitations</h3>
                <ul>
                    <li>The free version of BotDetect includes a randomized <code>BotDetect™</code> trademark in the background of 50% of all Captcha images generated.</li>
                    <li>It also has limited sound functionality, replacing the CAPTCHA sound with "SOUND DEMO" for randomly selected 50% of all CAPTCHA codes.</li>
                    <li>Lastly, the bottom 10 px of the CAPTCHA image are reserved for a link to the BotDetect website.</li>
                </ul>
                <p>These limitations are removed if you <a rel="nofollow" href="http://captcha.com/shop.html?utm_source=installation&amp;utm_medium=php&amp;utm_campaign=4.0.Beta3.7" title="BotDetect CAPTCHA online store, pricing information, payment options, licensing &amp; upgrading">upgrade</a> your BotDetect license.</p>
            </div>
            <% } %>
            
            <div class="note warning">
                <h3>Beta Release Warning</h3>
                <p>BotDetect Java Captcha Library Beta is a work in progress, and we need you to guide our efforts towards a polished product. Please <a href="http://captcha.com/contact.html?topic=java">let us know</a> if you encounter any bugs, implementation issues, or a usage scenario you would like to discuss.</p>
            </div>
        </div>
    </div>
  
    <div id="systeminfo">
        <p><%= Captcha.getLibInfo() %></p>
    </div>
</body>
</html>
