$( document ).ready(function() {

    $("#delete-btn").click(function () {
        if(!confirm("Are you sure you want to delete the customer?"))
            return false;
    });
});