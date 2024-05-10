<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BotDetect Java CAPTCHA Validation: JSP jQuery Validation CAPTCHA Code Example</title>
    <link rel="stylesheet" href="stylesheet.css" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
    <form method="post" action="jqueryValidationAction" class="column" id="form1">
        <h1>BotDetect Java CAPTCHA Validation:<br> JSP jQuery Validation CAPTCHA Code Example</h1>
        <fieldset>
            <legend>CAPTCHA included in JSP form validation</legend>
            <div class="input">
                <label for="name">Name:</label>
                <input type="text" name="name" class="textbox" value="${param.name}" />
                <span class="incorrect">${messages.nameIncorrect}</span>
            </div>

            <div class="input">
                <label for="email">Email:</label>
                <input type="text" name="email" class="textbox" value="${param.email}" />
                <span class="incorrect">${messages.emailIncorrect}</span>
            </div>

            <div class="input">
                <label for="message">Short message:</label>
                <textarea class="inputbox" name="message" rows="5" cols="40">${param.message}</textarea>
                <span class="incorrect">${messages.messageIncorrect}</span>
            </div>

            <%
                if (request.getSession().getAttribute("isCaptchaSolved") == null) {
            %>
                    <label for="captchaCode" class="prompt">Retype the characters from the picture:</label>

                    <!-- Adding BotDetect Captcha to the page -->
                    <botDetect:captcha id="jQueryValidatedCaptcha" 
                                userInputID="captchaCode"
                                codeLength="3"
                                imageWidth="150"
                                imageStyle="GRAFFITI2" />

                    <div class="validationDiv">
                        <input id="captchaCode" type="text" name="captchaCode" />
                        <span class="incorrect">${messages.captchaIncorrect}</span>
                    </div>
            <%
                }
            %>

            <input type="submit" name="submitButton" id="submitButton" value="Submit" />
            <span class="correct">${messages.captchaCorrect}</span>
        </fieldset>
        
        <script type="text/javascript" src="js/validation-rules.js"></script>
        
    </form>
            
    <div class="column">
        <div class="column">
            <div class="note">
                <h3>CAPTCHA Code Example Description</h3>
                <p>This BotDetect Captcha Java code example shows how to integrate BotDetect Java CAPTCHA validation with <a target="_blank" href="http://jqueryvalidation.org/validate">jQuery Validation</a> client-side form validation.</p>
                <p>It uses the Captcha Form Example as a starting point, and adds client-side jQuery Validation rules for all form fields.</p>
                <p>Client-side validation is not secure by itself (it can be bypassed trivially), so the example also shows how the protected form action must always be secured by server-side CAPTCHA validation first, and use client-side validation only to improve the user experience.</p>
            </div>
        </div>
    
        <div class="column">
            <% if (Captcha.isFree()) { %>
                <div class="note warning">
                    <h3>Free Version Limitations</h3>
                    <ul>
                        <li>The free version of BotDetect only includes a limited subset of the available CAPTCHA image styles and CAPTCHA sound styles.</li>
                        <li>The free version of BotDetect includes a randomized <code>BotDetect&#8482;</code> trademark in the background of 50% of all Captcha images generated.</li>
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
