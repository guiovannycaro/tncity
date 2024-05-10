<%@page import="com.captcha.botdetect.web.CaptchaUrls"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.captcha.botdetect.HelpLinkMode"%>
<%@page import="com.captcha.botdetect.SoundRegenerationMode"%>
<%@page import="com.captcha.botdetect.SoundFormat"%>
<%@page import="com.captcha.botdetect.SoundStyle"%>
<%@page import="com.captcha.botdetect.ImageFormat"%>
<%@page import="com.captcha.botdetect.ImageSize"%>
<%@page import="com.captcha.botdetect.ImageStyle"%>
<%@page import="com.captcha.botdetect.CodeStyle"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BotDetect Java CAPTCHA Options: Form Object Settings Code Example</title>
    <link type="text/css" rel="stylesheet" href="botdetectcaptcha?get=layout-stylesheet" />
    <link type="text/css" rel="stylesheet" href="stylesheet.css" />
</head>
<body>
    <form method="post" action="" class="column" id="form1">
        <h1>BotDetect Java CAPTCHA Options: <br /> Form Object Settings Code Example</h1>

        <fieldset>
            <legend>Java CAPTCHA validation</legend>
            <label for="captchaCode1">Retype the characters from the picture:</label>

            <% 
                // Adding BotDetect Captcha to the page
                Captcha captcha1 = Captcha.load(request, "captcha1");
                captcha1.setUserInputID("captchaCode1");
                
                captcha1.setCodeLength(6);
                captcha1.setCodeStyle(CodeStyle.NUMERIC);
                captcha1.setCodeTimeout(300); // 5 minutes
                captcha1.setDisallowedCodeSubstringsCsv("1,2,3,4,5,00,777,9999");

                captcha1.setImageStyle(ImageStyle.SUN_AND_WARM_AIR);
                captcha1.setImageSize(new ImageSize(250, 60));
                captcha1.setImageFormat(ImageFormat.PNG);

                captcha1.setSoundEnabled(true);
                captcha1.setSoundStyle(SoundStyle.SYNTH);
                captcha1.setSoundFormat(SoundFormat.WAV_PCM_8BIT_8KHZ_MONO);
                captcha1.setSoundRegenerationMode(SoundRegenerationMode.LIMITED);
                captcha1.setSoundStartDelay(100); // 0.1 seconds

                captcha1.setLocale("es-MX");
                captcha1.setImageTooltip("Custom Mexican Spanish Captcha image tooltip");
                captcha1.setSoundTooltip("Custom Mexican Spanish Captcha sound icon tooltip");
                captcha1.setReloadTooltip("Custom Mexican Spanish Captcha reload icon tooltip");
                captcha1.setHelpLinkUrl("custom-mexican-spanish-captcha-help-page.html");
                captcha1.setHelpLinkText("Custom Mexican Spanish Captcha help link text");

                captcha1.setReloadEnabled(true);
                captcha1.setUseSmallIcons(false);
                captcha1.setUseHorizontalIcons(false);
                captcha1.setSoundIconUrl("");
                captcha1.setReloadIconUrl("");
                captcha1.setIconsDivWidth(27);
                captcha1.setHelpLinkEnabled(true);
                captcha1.setHelpLinkMode(HelpLinkMode.TEXT);
                captcha1.setTabIndex(-1);
                captcha1.setAdditionalCssClasses("class1 class2 class3");
                captcha1.setAdditionalInlineCss("border: 4px solid #fff; background-color:#f8f8f8;");

                captcha1.setAddCssInclude(false);
                captcha1.setAddScriptInclude(true);
                captcha1.setAutoUppercaseInput(true);
                captcha1.setAutoFocusInput(true);
                captcha1.setAutoClearInput(true);
                captcha1.setAutoReloadExpiredCaptchas(true);
                captcha1.setAutoReloadTimeout(7200); // 2 hours
                captcha1.setRemoteScriptEnabled(true);
                
                String captchaHtml1 = captcha1.getHtml();
                out.write(captchaHtml1);
            %>

            <div class="validationDiv">
                <input name="captchaCode1" type="text" id="captchaCode1" />
                
                <% 
                    // when the form is submitted
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        // validate the Captcha to check we're not dealing with a bot
                        boolean isHuman1 = captcha1.validate(request.getParameter("captchaCode1"));
                        if (!isHuman1) {
                            // Captcha validation failed, show error message
                            out.print("<span class=\"incorrect\">Incorrect code</span>");
                        } else {
                            // Captcha validation passed, perform protected action
                            out.print("<span class=\"correct\">Correct code</span>");
                        }
                    }
                %>
            </div>
        </fieldset>
            
        <fieldset>
            <legend>Java CAPTCHA validation</legend>
            <label for="captchaCode2">Retype the characters from the picture:</label>

            <% 
                // Adding BotDetect Captcha to the page
                Captcha captcha2 = Captcha.load(request, "captcha2");
                captcha2.setUserInputID("captchaCode2");

                captcha2.setCodeLength(3);
                captcha2.setCodeStyle(CodeStyle.ALPHA);
                captcha2.setCodeTimeout(900); // 5 minutes
                captcha2.setDisallowedCodeSubstringsCsv("AAA,BBB,CCC");
                
                List<ImageStyle> imageStyles = new ArrayList<ImageStyle>();
                imageStyles.add(ImageStyle.BLACK_OVERLAP);
                imageStyles.add(ImageStyle.GRAFFITI);
                imageStyles.add(ImageStyle.OVERLAP);
                
                captcha2.setImageStyle(imageStyles);
                captcha2.setImageSize(new ImageSize(120, 35));
                captcha2.setImageFormat(ImageFormat.PNG);
                
                captcha2.setCustomDarkColor(java.awt.Color.decode("#006400"));
                captcha2.setCustomLightColor(java.awt.Color.decode("#eeeeff"));
  
                captcha2.setSoundStyle(SoundStyle.DISPATCH);
                captcha2.setSoundFormat(SoundFormat.WAV_PCM_8BIT_8KHZ_MONO);
                captcha2.setSoundRegenerationMode(SoundRegenerationMode.NONE);

                captcha2.setLocale("fr-CA");
                captcha2.setImageTooltip("Custom Canadian French Captcha image tooltip");
                captcha2.setSoundTooltip("Custom Canadian French Captcha sound icon tooltip");
                captcha2.setReloadTooltip("Custom Canadian French Captcha reload icon tooltip");
                captcha2.setHelpLinkUrl("custom-canadian-french-captcha-help-page.html");
                captcha2.setHelpLinkText("Custom Canadian French Captcha help link text");

                captcha2.setReloadEnabled(true);
                captcha2.setUseSmallIcons(true);
                captcha2.setUseHorizontalIcons(true);
                captcha2.setSoundIconUrl("");
                captcha2.setReloadIconUrl("");
                captcha2.setIconsDivWidth(50);
                captcha2.setHelpLinkEnabled(true);
                captcha2.setHelpLinkMode(HelpLinkMode.IMAGE);
                captcha2.setTabIndex(15);
                captcha2.setAdditionalCssClasses("");
                captcha2.setAdditionalInlineCss("");
                
                captcha2.setAddCssInclude(false);
                captcha2.setAddScriptInclude(true);
                captcha2.setAutoUppercaseInput(false);
                captcha2.setAutoFocusInput(false);
                captcha2.setAutoClearInput(false);
                captcha2.setAutoReloadExpiredCaptchas(true);
                captcha2.setAutoReloadTimeout(3600); // 1 hours
                captcha2.setSoundStartDelay(1000); // 1 second
                captcha2.setRemoteScriptEnabled(false);
                
                String captchaHtml2 = captcha2.getHtml();
                out.write(captchaHtml2);
            %>

            <div class="validationDiv">
                <input name="captchaCode2" type="text" id="captchaCode2" />
                
                <% 
                    // when the form is submitted
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        // validate the Captcha to check we're not dealing with a bot
                        boolean isHuman2 = captcha2.validate(request.getParameter("captchaCode2"));
                        if (!isHuman2) {
                            // Captcha validation failed, show error message
                            out.print("<span class=\"incorrect\">Incorrect code</span>");
                        } else {
                            // Captcha validation passed, perform protected action
                            out.print("<span class=\"correct\">Correct code</span>");
                        }
                    }
                %>
            </div>
        </fieldset>
        
        <input type="submit" name="submitButton" id="submitButton" value="Submit Form" />
    </form>
            
    <div class="column">
        <div class="column">
            <div class="note">
                <h3>CAPTCHA Code Example Description</h3>
                <p>This BotDetect Captcha Java code example shows how to configure Captcha challenges by setting <code>Captcha</code> object properties in Java form source.</p>
                <p>Multiple Java forms within the same Java website can be protected by BotDetect Captcha challenges: e.g. you could add Captcha controls in both your Contact form and Registration form source.</p>
                <p>To function properly, separate Captcha challenges placed on each form should have different names (<code>CaptchaId</code> values sent to the <code>Captcha</code> object constructor, <code>captcha1</code> and <code>captcha2</code> in this example), and can use completely different Captcha settings.</p>
                <p>Even multiple Captcha instances placed on the same form won't interfere with each other's validation and functionality. And if a user opens the same page in multiple browser tabs, each tab will independently validate the shown Captcha code.</p>
                <p>Shared Captcha settings should always be placed in the <code>WEB-INF/web.xml</code> file, and only diverging settings set through Captcha object instance properties in form code, to avoid code duplication.</p> 
                <p>Settings that affect only Captcha container markup generation take effect immediately (changing <code>captcha.getHtml()</code> output), but settings that affect Captcha challenge (image or sound) generation in separate Http requests need to be saved in Java Session state when set through <code>Captcha</code> object instance properties in form source, consuming server resources and reverting to defaults when the Java Session expires.</p>
            </div>
        </div>

        <div class="column">
            <% if (Captcha.isFree()) { %>
                <div class="note warning">
                    <h3>Free Version Limitations</h3>
                    <ul>
                        <li>The free version of BotDetect only includes a limited subset of the available CAPTCHA image styles and CAPTCHA sound styles.</li>
                        <li>The free version of BotDetect includes a randomized <code>BotDetect™</code> trademark in the background of 50% of all Captcha images generated.</li>
                        <li>It also has limited sound functionality, replacing the CAPTCHA sound with "SOUND DEMO" for randomly selected 50% of all CAPTCHA codes.</li>
                        <li>Lastly, the bottom 10 px of the CAPTCHA image are reserved for a link to the BotDetect website.</li>
                    </ul>
                    <p>These limitations are removed if you <a rel="nofollow" href="http://captcha.com/shop.html?utm_source=installation&amp;utm_medium=java&amp;utm_campaign=4.0.Beta3.7" title="BotDetect CAPTCHA online store, pricing information, payment options, licensing &amp; upgrading">upgrade</a> your BotDetect license.</p>
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
