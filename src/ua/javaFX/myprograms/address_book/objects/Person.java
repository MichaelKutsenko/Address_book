package ua.javaFX.myprograms.address_book.objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Lenovo on 27.01.2016.
 */
public class Person {

    private SimpleStringProperty fio = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public Person(){

    }

    public Person(String fio, String phone) {
        this.fio.set(fio);
        this.phone.set(phone);
    }

    public String getFio() {
        return fio.get();
    }

    public SimpleStringProperty fioProperty(){
        return fio;
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty(){
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return "fio = " + fio  + ", phone = " + phone;
    }
}
