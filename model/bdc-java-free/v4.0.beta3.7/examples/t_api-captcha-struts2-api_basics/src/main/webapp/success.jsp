<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BotDetect Java CAPTCHA Validation: Struts 2 Basic CAPTCHA Code Example</title>
        <link rel="stylesheet" href="stylesheet.css" type="text/css" />
    </head>
    <body>
        <div class="column">
            <h1>BotDetect Java CAPTCHA Validation: <br /> Struts 2 Basic CAPTCHA Code Example</h1>
            <p class="message">Captcha validation passed!</p>
            
            <p class="navigation"><a href="index.jsp">Back</a></p>
        </div>
        
        <div class="column">
            <div class="column">
                <div class="note">
                    <h3>CAPTCHA Code Example Description</h3>
                    <p>This example project shows the most basic source code required to protect a Struts page with BotDetect CAPTCHA and validate the user input.</p>
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
