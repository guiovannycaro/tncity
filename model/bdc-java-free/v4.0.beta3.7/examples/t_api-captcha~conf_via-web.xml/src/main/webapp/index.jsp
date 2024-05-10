<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page import="com.captcha.botdetect.web.CaptchaUrls"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BotDetect Java CAPTCHA Options: Application Config Settings Code Example</title>
    <link type="text/css" rel="stylesheet" href="stylesheet.css" />
    <script src="botdetectcaptcha?get=script-include"></script>
</head>
<body>
    <form method="post" action="" class="column" id="form1">

        <h1>BotDetect Java CAPTCHA Options: <br /> Application Config Settings Code Example</h1>

        <fieldset>
            <legend>Java CAPTCHA validation</legend>
            <label for="captchaCode">Retype the characters from the picture:</label>

            <% 
                // Adding BotDetect Captcha to the page
                Captcha captcha = Captcha.load(request, "appConfigCustomizedCaptcha");
                captcha.setUserInputID("captchaCode");
                String captchaHtml = captcha.getHtml();
                out.write(captchaHtml);
            %>

            <div class="validationDiv">
                <input name="captchaCode" type="text" id="captchaCode" />
                <input type="submit" name="ValidateCaptchaButton" id="validateCaptchaButton" value="Validate" />

                <% 
                    // when the form is submitted
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        // validate the Captcha to check we're not dealing with a bot
                        boolean isHuman = captcha.validate(request.getParameter("captchaCode"));
                        if (isHuman) {
                            // Captcha validation passed, perform protected action
                            out.print("<span class=\"correct\">Correct code</span>");
                        } else {
                            // Captcha validation failed, show error message
                            out.print("<span class=\"incorrect\">Incorrect code</span>");
                        }
                    }
                %>
            </div>
        </fieldset>
    </form>
	
    <div class="column">
        <div class="column">
            <div class="note">
                <h3>CAPTCHA Code Example Description</h3>
                <p>This BotDetect Captcha Java code example shows how to configure Captcha challenges by overriding Captcha library defaults in application configuration files.</p>
                <p>BotDetect allows user-defined customization of many Captcha options through <code>&lt;context-param&gt;</code> deployment descriptor in the <code>WEB-INF/web.xml</code> file.</p> 
                <p>Captcha settings from this configuration file will apply to all Captcha challenges shown on forms in the applications, and will act as defaults with which all Captcha objects will be created. This makes configuration file settings the simplest and most convenient way of Captcha customization for most use cases.</p>
                <p>The <code>WEB-INF/web.xml</code> file used in this code example contains detailed descriptions and explanations of the many customizable Captcha options exposed by the BotDetect Java Captcha configuration API.</p>
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
