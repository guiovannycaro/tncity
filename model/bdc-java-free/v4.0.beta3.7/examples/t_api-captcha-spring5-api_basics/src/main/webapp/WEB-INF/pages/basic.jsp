<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BotDetect Java CAPTCHA Validation: Spring MVC Basic Code Example</title>
    <link rel="stylesheet" href="stylesheet.css" type="text/css" />
</head>
<body>
    <form:form action="validate" modelAttribute="basicExample" method="POST" cssClass="column" id="form1">
        <h1>BotDetect Java CAPTCHA Validation: <br /> Spring MVC Basic Code Example</h1>
        <fieldset>
            <legend>Java CAPTCHA validation</legend>
            <label for="captchaCode">Retype the characters from the picture:</label>

            <!-- Adding BotDetect Captcha to the page -->
            <botDetect:captcha id="basicExample" userInputID="captchaCode" />

            <div class="validationDiv">
                <input name="captchaCode" type="text" id="captchaCode" value="${basicExample.captchaCode}" />
                <input type="submit" name="validateCaptchaButton" value="Validate" id="validateCaptchaButton" />
                <span class="correct">${basicExample.captchaCorrect}</span>
                <span class="incorrect">${basicExample.captchaIncorrect}</span>
            </div>
        </fieldset>
    </form:form>
            
    <div class="column">
        <div class="column">
            <div class="note">
                <h3>CAPTCHA Code Example Description</h3>
                <p>This example project shows the most basic source code required to protect an Spring MVC page with BotDetect CAPTCHA and validate the user input.</p>
                <p>It can be used as a starting point when you are first learning how to use BotDetect.</p>
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
