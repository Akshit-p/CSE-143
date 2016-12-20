function GradeItAdditional() {}

GradeItAdditional.incompleteFormSubmit = function ( formId ) {
    $("<input>")
        .attr("type", "hidden")
        .attr("name", "incomplete")
        .attr("value", 'true')
        .appendTo(formId);
    $( formId ).find( "input.submit-button" ).attr("name", "fake-submit");
    $( formId ).submit();
};

GradeItAdditional.attachFlashCloseButtons = function() {
    Array.forEach(
        DOM.elementsByClass("div", "grade-it-alert", document),
        function(closeBox) {
            var closeLink = DOM.descendent("a", "close", closeBox);
            Events.addLinkListener(closeLink, function( event ) {
                var alertBox = event.target.parentNode;
                DOM.toggleDisplay(alertBox);
            });
        });
};

if (typeof(Events) === 'function') {
    Events.addOnLoad(function() {
        GradeItAdditional.attachFlashCloseButtons();
    });
}


