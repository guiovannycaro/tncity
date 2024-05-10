function handleSignupRequest(xhr, status, args) {
    if (args.validationFailed) {
        // primefaces UI validation failed
        return;
    }

    // we should reload captcha image after server-side validation is completed
    // in order to update new captcha code for current instance id
    var captcha = window["jsfPrimefacesSignupFormCaptcha"];
    captcha.ReloadImage();
}

function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed) {
        // primefaces UI validation failed
        return;
    }

    // we should reload captcha image after server-side validation is completed
    // in order to update new captcha code for current instance id
    var captcha = window["jsfPrimefacesLoginFormCaptcha"];
    captcha.ReloadImage();
}