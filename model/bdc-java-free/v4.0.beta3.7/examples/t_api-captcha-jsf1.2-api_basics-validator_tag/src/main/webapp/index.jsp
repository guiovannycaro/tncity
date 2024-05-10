<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>BotDetect Java CAPTCHA Validation: JSF Validator CAPTCHA Code Example</title>
            <link rel="stylesheet" href="stylesheet.css" type="text/css"/>
        </head>
        <body>
            <h:form prependId="false" styleClass="column">
                <h1>BotDetect Java CAPTCHA Validation:<br/> JSF Validator CAPTCHA Code Example</h1>
                <fieldset>
                    <legend>Java CAPTCHA Validation</legend>
                    <h:outputLabel for="inputName" value="Name:"/>
                    <h:inputText id="inputName" validatorMessage="Missing name">
                        <f:validateLength minimum="2"/>
                    </h:inputText>
                    <h:message for="inputName" styleClass="incorrect"/>
                    
                    <h:outputLabel for="captchaCode" value="Retype the characters from the picture:"/>
                    <!-- Adding BotDetect Captcha to the page -->
                    <botDetect:jsfCaptcha id="exampleCaptcha"
                                          userInputID="captchaCode" />
                    
                    <div class="validationDiv">
                        <h:inputText id="captchaCode" value="#{validatorExample.captchaCode}"
                                     required="true" requiredMessage="Incorrect code"
                                     validatorMessage="Incorrect code">
                            <f:validator validatorId="exampleValidator"/>
                            <f:attribute name="captchaId" value="exampleCaptcha"/>
                        </h:inputText>
                        <h:message for="captchaCode" styleClass="incorrect"/>
                    </div>
                    
                    <h:commandButton  action="#{validatorExample.submit}" value="Submit"/>
                    
                    <h:outputText value="Validation passed." styleClass="correct" rendered="#{validatorExample.validated}"/>
                </fieldset>
            </h:form>    
                
            <div class="column">
                <div class="column">
                    <div class="note">
                        <h3>CAPTCHA Code Example Description</h3>
                        <p>This example project shows how to integrate BotDetect CAPTCHA validation with standard JSF page validation functionality and other validator controls.</p>
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
</f:view>
