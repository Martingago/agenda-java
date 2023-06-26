package cv.agenda.domain;

public class Email {
private String mail;

    public Email() {
    }

    public Email(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Email{" + "mail=" + mail + '}';
    }



}
