package be.sentas.inidial.device;

import be.sentas.inidial.model.Contact;
import be.sentas.inidial.model.Phone;
import be.sentas.inidial.model.PhoneType;

import java.util.ArrayList;
import java.util.List;

public class DesktopNativeService implements NativeService {
    @Override
    public void callNumber(String number) {
        System.out.println("Call " + number);
    }

    @Override
    public void sendTextMessage(String number) {
        System.out.println("Send text message to " + number);
    }

    @Override
    public List<Contact> getContacts() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Contact> contacts = new ArrayList<>();
        Contact yannick = new Contact("Yannick", "Van Godtsenhoven");
        yannick.setId("1");
        yannick.getNumbers().add(new Phone("12345", PhoneType.HOME));
        yannick.getNumbers().add(new Phone("12345", PhoneType.MOBILE));
        contacts.add(yannick);
        Contact liesbeth = new Contact("Liesbeth", "Toorman");
        liesbeth.setId("2");
        liesbeth.getNumbers().add(new Phone("12345", PhoneType.HOME));
        liesbeth.getNumbers().add(new Phone("12345", PhoneType.MOBILE));
        liesbeth.getNumbers().add(new Phone("12345", PhoneType.OTHER));
        contacts.add(liesbeth);
        Contact pieter = new Contact("Pieter", "Jagers");
        pieter.setId("3");
        pieter.getNumbers().add(new Phone("12345", PhoneType.OTHER));
        pieter.getNumbers().add(new Phone("12345", PhoneType.MOBILE));
        contacts.add(pieter);
        Contact herman = new Contact("Herman", "Toorman");
        herman.setId("4");
        herman.getNumbers().add(new Phone("12345", PhoneType.HOME));
        contacts.add(herman);
        return contacts;
    }

    @Override
    public byte[] getContactPicture(String contactId) {
        return null;
    }
}
