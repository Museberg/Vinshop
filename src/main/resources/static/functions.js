function sendEmail(){
    let recipients = $("#recipients").val();
    let subject = $("#subject").val();
    let body = $("#body").val();
    Email.send({
        SecureToken : "c22b3ec7-4c66-49bd-9c8f-f27047792222",
        To : recipients,
        From : "lerchegaard@vingaard.dk",
        Subject : subject,
        Body : body
    }).then(
        message => alert("Emails er nu afsendt med succes!")
    );
}
