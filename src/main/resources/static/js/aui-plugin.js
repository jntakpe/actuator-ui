$.validator.setDefaults({
    errorClass: "invalid",
    validClass: "valid",
    errorElement: "em",
    highlight: function (element, errorClass, validClass) {
        if (element.type === "radio") {
            this.findByName(element.name).addClass(errorClass).removeClass(validClass).parent().addClass('state-error').removeClass('state-success');
        } else {
            $(element).addClass(errorClass).removeClass(validClass).parent().addClass('state-error').removeClass('state-success');
        }
    },
    unhighlight: function (element, errorClass, validClass) {
        if (element.type === "radio") {
            this.findByName(element.name).removeClass(errorClass).addClass(validClass).parent().addClass('state-success').removeClass('state-error');
        } else {
            $(element).removeClass(errorClass).addClass(validClass).parent().addClass('state-success').removeClass('state-error');
        }
    }
});