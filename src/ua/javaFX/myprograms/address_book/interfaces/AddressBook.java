package ua.javaFX.myprograms.address_book.interfaces;

import ua.javaFX.myprograms.address_book.objects.Person;

/**
 * Created by Lenovo on 27.01.2016.
 */
public interface AddressBook {

    void addPerson(Person person);

    void editPerson(Person person);

    void deletePerson(Person person);
}
