package ua.javaFX.myprograms.address_book.interfaces.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.javaFX.myprograms.address_book.interfaces.AddressBook;
import ua.javaFX.myprograms.address_book.objects.Person;

/**
 * Created by Lenovo on 01.02.2016.
 */
public class CollectionAddressBook implements AddressBook {
    private ObservableList<Person> persons = FXCollections.observableArrayList();

    @Override
    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public void editPerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {
        persons.remove(person);
    }

    public ObservableList<Person> getPersons() {
        return persons;
    }

    public void fillTestData(){
        persons.add(new Person("Chernomor", "111111111"));
        persons.add(new Person("Ruslan", "222222222"));
        persons.add(new Person("Ludmila", "333333333"));
        persons.add(new Person("Kot", "444444444"));
        persons.add(new Person("Koschey", "555555555"));
    }

    public void printAddressBook(){
        int n = 1;
        for (Person p : persons){
            System.out.println(n++ + ")" + " " + p);
        }
    }
}
