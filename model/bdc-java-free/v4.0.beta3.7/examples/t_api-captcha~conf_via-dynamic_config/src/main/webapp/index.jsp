<%@page import="com.captcha.botdetect.CodeStyle"%>
<%@page import="com.captcha.botdetect.examples.dynamic_captcha.Counter"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BotDetect Java CAPTCHA Options: Request Dynamic Settings Code Example</title>
    <link type="text/css" rel="stylesheet" href="stylesheet.css" />
</head>
<body>
    <form method="post" action="" class="column" id="form1">
        <h1>BotDetect Java CAPTCHA Options: <br /> Request Dynamic Settings Code Example</h1>

        <fieldset>
            <legend>Java CAPTCHA validation</legend>
            <label for="captchaCode">Retype the characters from the picture:</label>
            <%
                // Adding BotDetect Captcha to the page
                Captcha captcha = Captcha.load(request, "dynamicCaptcha");
                captcha.setUserInputID("captchaCode");
                String captchaHtml = captcha.getHtml();
                out.write(captchaHtml);
            %>

            <div class="validationDiv">
                <input name="captchaCode" type="text" id="captchaCode" />
                <input type="submit" name="SubmitButton" id="SubmitButton" value="Submit Form" />
                
                <%
                    Counter counter = new Counter(request.getSession());
                    
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        // validate the Captcha to check we're not dealing with a bot
                        boolean isHuman = captcha.validate(request.getParameter("captchaCode"));
                        if (!isHuman) {
                            // Captcha validation failed, show error message
                            out.print("<span class=\"incorrect\">Incorrect code</span>");
                            counter.incrementFailedValidationsCount();
                        } else {
                            // Captcha validation passed, perform protected action
                            out.print("<span class=\"correct\">Correct code</span>");
                            counter.resetFailedValidationsCount();
                        }
                    }
                %>
            </div>
        </fieldset>

        <div id="output">
            <% 
                Integer count = counter.getFailedValidationsCount();
            %>

            <p>Failed Captcha validations: <%= count %></p>
            <%
                if (count < 3) {
                    out.print("<p>Dynamic Captcha difficulty: Easy</p>");
                } else if (count < 10) {
                    out.print("<p>Dynamic Captcha difficulty: Moderate</p>");
                } else {
                    out.print("<p>Dynamic Captcha difficulty: Hard</p>");
                }
            %>
        </div>
    </form>

    <div class="column">
        <div class="column">
            <div class="note">
                <h3>CAPTCHA Code Example Description</h3>
                <p>This BotDetect Captcha Java code example shows how to dynamically adjust Captcha configuration, potentially on each Http request made by the client.</p>
                <p>Any Java code setting Captcha properties in the <code>CaptchaFilter.java</code> file will be executed not only for each protected form GET or POST request (like Captcha configuration code placed in form source would be), but also for each GET request loading a Captcha image or sound, or making an Ajax Captcha validation call.</p>
                <p>If configured values are dynamic (e.g. <code>CaptchaRandomization</code> helper or other function calls in <code>CaptchaFilter.java</code> code), they will be re-calculated for each Captcha challenge generated. For example, Captcha <code>ImageStyle</code> randomized in <code>CaptchaFilter.java</code> code will change on each Captcha reload button click.</p>
                <p>This means your code can reliably keep track of visitor interaction with the Captcha challenge and dynamically adjust its settings. Also, while <code>CaptchaFilter.java</code> settings apply to all Captcha instances by default, you can also selectively apply them based on CaptchaId.</p>
                <p>To show an example of the possible dynamic Captcha configuration adjustments, this code example increases the difficulty of the Captcha test if the visitor associated with the current Java Session fails a certain number of Captcha validation attempts, and also sets the Captcha locale to Chinese for requests from a certain IP range.</p>
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
