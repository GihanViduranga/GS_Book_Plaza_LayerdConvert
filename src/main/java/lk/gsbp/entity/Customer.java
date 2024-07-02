package lk.gsbp.entity;

import lombok.*;

public class Customer {
    private String CustomerId;
    private String Name;
    private String Address;
    private String Contact;
    private String Email;

    public Customer() {
    }

    public Customer(String customerId, String name, String address, String contact, String email) {
        CustomerId = customerId;
        Name = name;
        Address = address;
        Contact = contact;
        Email = email;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerId='" + CustomerId + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
