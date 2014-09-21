$(function () {

    $.fn.extend({
        /**
         * Validateur jQuery initialisé avec le placement correct des messages d'erreurs
         * @param settings paramètres du validateur
         */
        auiValidate: function (settings) {
            settings.errorPlacement = function (error, element) {
                error.insertAfter(element.parent());
            };
            this.validate(settings);
        }
    });
});
