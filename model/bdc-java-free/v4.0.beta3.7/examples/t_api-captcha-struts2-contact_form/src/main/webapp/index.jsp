<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BotDetect Java CAPTCHA Validation: Struts 2 Form CAPTCHA Code Example</title>
        <link rel="stylesheet" href="stylesheet.css" type="text/css" />
    </head>
    <body>
        <s:form action="captchaAction" method="post"  theme="css_xhtml" class="column" id="form1">
            <h1>BotDetect Java CAPTCHA Validation: <br /> Struts 2 Form CAPTCHA Code Example</h1>
            <fieldset>
                <legend>CAPTCHA included in Struts form validation</legend>
                
                <s:textfield name ="user.name" label="Name" class="textbox"  />
                <s:textfield name ="user.email" label="Email" class="textbox" />
                <s:textarea name="user.message" label="Message" rows="5" cols="40" class="inputbox" />
                
                <label for="captchaCode">Retype the characters from the picture:</label>
            
                <!-- Adding BotDetect Captcha to the page -->    
                <botDetect:captcha id="formCaptcha" userInputID="captchaCode"/>
                
                <br/>
                <s:textfield name="captchaCode" id="captchaCode" class="textbox" />
                <s:submit name="validateCaptchaButton" label="Validate" id="validateCaptchaButton"/>
            </fieldset>
        </s:form>
        
        <div class="column">
            <div class="column">
                <div class="note">
                    <h3>CAPTCHA Code Example Description</h3>
                    <p>This code example shows how to add BotDetect CAPTCHA protection to a typical Struts form.</p>
                    <p>Captcha validation is integrated with other form fields validation, and only submissions that meet all validation criteria are accepted.</p> 
                    <p>If the Captcha is successfully solved but other field validation fails, the Captcha is hidden since the users have already proven they are human.</p>
                    <p>This kind of validation could be used on various types of public forms which accept messages, and are at risk of unwanted automated submissions.</p>
                    <p>For example, it could be used to ensure bots can't submit anything to a contact form, add guestbook entries, blog post comments or anonymous message board / forum replies.</p>
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
