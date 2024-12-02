package USERS;

public class AdminUser extends User {
    String rights;
    String mail;
    String number;
    String adress;

    public AdminUser(String rights, String mail, String number, String adress) {
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
    public String getMail(){
        return mail;
    }
    @Override
    public String getAdress(){
        return adress;
    }
    @Override
    public String getNumber(){
        return number;
    }
}
