<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BotDetect Java CAPTCHA Validation: Spring Security CAPTCHA Code Example</title>
     <style type="text/css">
        body {
            background-color: #EEEEFF;
            font-family: Verdana;
            font-size: 14px;
            margin: 0;
            padding: 0 0 20px 10px;
          }

          fieldset {
            padding: 3px 10px 10px 10px;
            margin: 11px;
            width: 410px;
            display: block;
          }

          legend {
            padding: 2px 7px;
            margin: 0;
            color: #999999;
            font-size: 13px;
          }

          label {
            display: block;
            padding: 0;
            margin: 0 0 3px 0;
          }

          div.input {
            margin: 7px 0;
          }

          input.textbox {
            width: 223px;
                  margin-top: 3px;
          }

          input:not([type='submit']) {
              width: 272px;
          }

          .validationDiv {
            padding: 0px 0px 5px 0px;
            margin: 8px 0px 0px 0px;
          }

          .correct {
            color: Green;
            font-size: 0.8em;
            font-weight: bold;
          }

          .incorrect {
            color: Red;
            font-size: 0.8em;
            font-weight: bold;
          }

          .column {
            display: inline-block;
            vertical-align: top;
          }

          .note {
            margin: 10px 10px 20px 10px;
            width: 432px;
          }

          .note p {
            font-size: 12px;
          }

          .note ul {
            margin: 5px 0 5px 0; 
            padding-left: 25px; 
            list-style-type: disc;
          }

          .note li {
            font-size: 12px;
            margin: 0 0 5px 0;
          }

          h1 {
            padding: 20px 0 0 10px; 
            margin: 0;
            font-size: 17px; 
                  font-family: Arial;
            width: 440px;
          }

          h2 {
            padding: 15px 0 0 0; 
            margin: 0 0 0 10px;
            font-size: 15px; 
          }

          h3 {
            margin: 0;
            padding: 15px 0 0 0;
            font-size: 12px;
          }

          .navigation {
            margin: 20px 5px 5px 0;
          }

          .warning {
            color: Red;
          }

          #systeminfo {
            margin: 25px 20px 20px 10px;
            padding: 0 10px;
            border: 1px solid #ddd;
            clear: both;
            font-size: 11px;
            color: #999;
            background-color: #f0f0f0;
          }

          #systeminfo  p {
            margin: 10px 0;
            padding: 0;
          }

          .error {
            padding: 15px;
            margin-bottom: 15px;
            margin-top: 15px;
            border: 1px solid transparent;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 15px;
            margin-top: 15px;
            border: 1px solid transparent;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }
        
        .navigation {
            margin: 5px 5px 5px 12px;
        }

        .message {
            padding: 10px;
            margin: 10px;
            border: 1px solid #ccc;
            width: 410px;
        }
    </style>
</head>
<body>
    <div class="column">
        <h1>BotDetect Java CAPTCHA Validation:<br/> Spring Security CAPTCHA Code Example</h1>
        <h2>View Messages</h2>
        
        <div class="message">
            <p>Here is the protected page use Login form filter, this mean you login success with user and validate captcha.</p>
            <p>This example project shows how to add BotDetect CAPTCHA validation to basic Login form filter in JavaServer Pages Web Applications.</p>
        </div>

        <div class="navigation">
            <a href="javascript:formSubmit()"> Logout</a>
            <c:url value="/logout" var="logoutUrl" />
            <form action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </div>
    </div>  
    
    <div class="column">
        <div class="column">
            <div class="note">
                <h3>CAPTCHA Code Example Description</h3>
                <p>This example shows how to use BotDetect CAPTCHA validation and spring security together to make your web be better protected. It's based on spring security form log in and log out functions.</p>
                <p>In the log in form, the user have to enter required information and the captcha code to ensure that this is human. The code will be validated after the form is submitted.</p>
                <p>To keep the example code simple, the example doesn't access a data store to authenticate the user, but <strong>login with "captcha" as username and password</strong>.</p>
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
    
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>
</body>
</html>
