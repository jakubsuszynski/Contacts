package jsuszynski.contacts.domain;

import java.time.LocalDate;

public class ContactBuilder {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String category;
    private String subcategory;
    private String telephone;
    private LocalDate dob;

    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ContactBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ContactBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public ContactBuilder setSubcategory(String subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public ContactBuilder setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public ContactBuilder setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public Contact createContact() {
        return new Contact(name, surname, email, password, category, subcategory, telephone, dob);
    }
}