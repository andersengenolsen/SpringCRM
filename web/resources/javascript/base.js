$( document ).ready(function() {

    $("#delete-customer").click(function () {
        if(!confirm("Are you sure you want to delete the customer?"))
            return false;
    });
});