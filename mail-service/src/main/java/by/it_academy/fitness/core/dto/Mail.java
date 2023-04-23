package by.it_academy.fitness.core.dto;

public class Mail {
    private String mailTo;
    private String message;
    private String subject;

    public Mail(String mailTo, String message, String subject) {
        this.mailTo = mailTo;
        this.message = message;
        this.subject = subject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
