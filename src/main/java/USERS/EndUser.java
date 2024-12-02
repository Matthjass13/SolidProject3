package USERS;

public class EndUser extends User {
    String rights;
    String mail;
    String number;
    String adress;

    public EndUser(String rights, String mail, String number, String adress) {
        this.rights = rights;
        this.mail = mail;
        this.number = number;
        this.adress = adress;
    }
    @Override
    public String getRights(){
        return rights;
    }
    @Override
    public String getName(){
        return "endUser";
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getAdress() {
        return adress;
    }
}
