package com.captcha.botdetect.demos.features.bean;

import com.captcha.botdetect.CodeStyle;
import com.captcha.botdetect.ImageFormat;
import com.captcha.botdetect.ImageSize;
import com.captcha.botdetect.ImageStyle;
import com.captcha.botdetect.SoundFormat;
import com.captcha.botdetect.SoundStyle;
import com.captcha.botdetect.web.servlet.Captcha;
import java.awt.Color;
import java.io.Serializable;
import javax.servlet.ServletRequest;

public class FeaturesBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String locale;
    private int codeLength;
    private CodeStyle codeStyle;
    
    private ImageStyle imageStyle;
    private Color customLightColor;
    private Color customDarkColor;
    private ImageFormat imageFormat;
    private int imageWidth;
    private int imageHeight;

    private SoundStyle soundStyle;
    private SoundFormat soundFormat;

    public FeaturesBean() {
    }

    public Captcha getCaptcha(ServletRequest request, String captchaId) {
        Captcha captcha = Captcha.load(request, captchaId);
        captcha.setUserInputID("captchaCode");
     
        // applying captcha settings when form is submitted
        if (request.getParameter("ApplyCaptchaSettings") != null) {
            captcha.setLocale(locale);
            captcha.setCodeLength(codeLength);
            captcha.setCodeStyle(codeStyle);

            captcha.setImageStyle(imageStyle);
            captcha.setCustomLightColor(customLightColor);
            captcha.setCustomDarkColor(customDarkColor);
            captcha.setImageFormat(imageFormat);

            if ((imageWidth > 0) && (imageHeight > 0)) {
                captcha.setImageSize(new ImageSize(imageWidth, imageHeight));
            }

            captcha.setSoundStyle(soundStyle);
            captcha.setSoundFormat(soundFormat);
        }
        
        return captcha;
    }
    
    public String getLocale() {
        return locale;
    }
    
    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }
    
    public String getCodeStyle() {
        return codeStyle.name();
    }
    
    public void setCodeStyle(String codeStyle) {
        this.codeStyle = CodeStyle.parseString(codeStyle);
    }
    
    public String getImageFormat() {
        return imageFormat.name();
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = ImageFormat.parseString(imageFormat);
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }
    
    public String getImageStyle() {
        return imageStyle.name();
    }
    
    public void setImageStyle(String imageStyle) {
        if (imageStyle.equalsIgnoreCase("default")) {
            this.imageStyle = null;
        } else {
            this.imageStyle = ImageStyle.parseString(imageStyle);
        }
    }
    
    public void setCustomLightColor(String customLightColor) {
        if (customLightColor.equalsIgnoreCase("default")) {
            this.customLightColor = null;
        } else {
            try {
                this.customLightColor = (Color) Color.class.getField(customLightColor).get(null);
            } catch (Exception ex) {
            }
        }
    }
    
    public void setCustomDarkColor(String customDarkColor) {
        if (customDarkColor.equalsIgnoreCase("default")) {
             this.customDarkColor = null;
        } else {
            try {
                this.customDarkColor = (Color) Color.class.getField(customDarkColor).get(null);
            } catch (Exception ex) {
            }
        }
    }
    
    public String getSoundStyle() {
        return soundStyle.name();
    }

    public void setSoundStyle(String soundStyle) {
        if (soundStyle.equalsIgnoreCase("default")) {
            this.soundStyle = null;
        } else {
            this.soundStyle = SoundStyle.parseString(soundStyle);
        }
    }
    
    public String getSoundFormat() {
        return soundFormat.name();
    }

    public void setSoundFormat(String soundFormat) {
        this.soundFormat = SoundFormat.parseString(soundFormat);
    }
}
