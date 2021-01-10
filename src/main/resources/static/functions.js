function sendEmail(){
    let recipients = $("#recipients").val();
    let subject = $("#subject").val();
    let body = $("#body").val();
    Email.send({
        SecureToken : "3934ab18-c5e6-4e72-8768-e3618903f99d",
        To : recipients,
        From : "lerchegaard@vingaard.dk",
        Subject : subject,
        Body : body
    }).then(
        message => alert("Mails afsendt med succes!")
    );
}
