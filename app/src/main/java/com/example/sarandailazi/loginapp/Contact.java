package com.example.sarandailazi.loginapp;

/**
 * Created by Saranda Ilazi on 13/09/2017.
 */
public class Contact {

    String name, surname, email, uname, pass, birthday, address, gender;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getSurname(){
        return surname;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setUname(String uname){
        this.uname = uname;
    }
    public String getUname(){
        return uname;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public String getPass(){
        return pass;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String validate() {
        // Check for empty
        if(isEmpty(this.getName()))
            return "Name is empty";
        else if(isEmpty(this.getSurname()))
            return "Surname is empty";
        else if(isEmpty(this.getUname()))
            return "Username is empty";
        else if(isEmpty(this.getEmail()))
            return "Email is empty";
        else if(isEmpty(this.getPass()))
            return "Password is empty";
        else if(isEmpty(this.getBirthday()))
            return "Birthday is empty";
        else if(isEmpty(this.getAddress()))
            return "Address is empty";
        else if(isEmpty(this.getGender()))
            return "Gender is empty";

        return "";
    }

    private boolean isEmpty(String text) {
        if(text == null || text.equals(""))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "\n\nContact\n" +
                "Name=" + name + '\n' +
                "Surname=" + surname + '\n' +
                "Email=" + email + '\n' +
                "Username=" + uname + '\n' +
                "Birthday=" + birthday + '\n' +
                "Address=" + address + '\n' +
                "Gender=" + gender + '\n';
    }
}
