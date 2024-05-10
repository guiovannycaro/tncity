<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BotDetect Java CAPTCHA Validation: JSP Login Form CAPTCHA Code Example</title>
    <link rel="stylesheet" href="stylesheet.css" type="text/css" />
</head>
<body>
    <form method="post" action="loginFormAction" class="column" id="form1">
        <h1>BotDetect Java CAPTCHA Validation:<br> JSP Login Form CAPTCHA Code Example</h1>
        <fieldset>
            <legend>CAPTCHA included in JSP form validation</legend>
            
            <div class="input">
                <label for="username">Username:</label>
                <input type="text" name="username" id="username" class="textbox" value="${param.username}" />
            </div>

            <div class="input">
                <label for="Password">Password:</label>
                <% if (request.getAttribute("isLoggedIn") == null) { %>
                        <input type="password" name="password" id="password" class="textbox" />
                <% } else { %>
                        <input type="text" name="password" id="password" class="textbox" value="${param.password}" />
                <% } %>
            </div>
            
            <label class="incorrect">${messages.loginError}</label>

            <%
                if ((request.getSession().getAttribute("isCaptchaSolved") == null) 
                        && (request.getAttribute("isLoggedIn") == null)) {
            %>
                    <label for="captchaCode" class="prompt">Retype the characters from the picture:</label>

                    <!-- Adding BotDetect Captcha to the page -->
                    <botDetect:captcha id="loginCaptcha" 
                                userInputID="captchaCode"
                                codeLength="4-6"
                                imageWidth="200"
                                codeStyle="ALPHA" />

                    <div class="validationDiv">
                        <input id="captchaCode" type="text" name="captchaCode" />
                        <span class="incorrect">${messages.captchaIncorrect}</span>
                    </div>
            <%
                }
            %>
            
            <% if (request.getAttribute("isLoggedIn") == null) { %>
                    <input type="submit" name="submitButton" id="submitButton" value="Login" />
            <% } else { %>
                    <a href="index.jsp">Back to login page.</a>
            <% } %>
        </fieldset>
    </form>
            
    <div class="column">
        <div class="column">
            <div class="note">
                <h3>CAPTCHA Code Example Description</h3>
                <p>This BotDetect Captcha Java code example shows how to add BotDetect CAPTCHA validation to simple JSP login forms.</p>
                <p>To prevent bots from trying to guess the login info by brute force submission of a large number of common values, the visitor first has to prove they are human (by solving the CAPTCHA), and only then is their username and password submission checked against the authentication data store.</p>
                <p>Also, if they enter an invalid username + password combination three times, they have to solve the CAPTCHA again. This prevents attempts in which the attacker would first solve the CAPTCHA themselves, and then let a bot brute-force the authentication info.</p>
                <p>To keep the example code simple, the example doesn't access a data store to authenticate the user, but <strong>accepts all logins with usernames and passwords at least 5 characters long as valid</strong>.</p>
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
