package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.model.Contact;

import java.util.List;

public interface ContactService {

    public void createContact(ContactRequest request);
    public void removeContact(Long id);
    public List<ContactResponse> getContactByName(String name);
    public List<ContactResponse> getByName(String name);
    public List<ContactResponse> getAllContact();
	public void updateContact(ContactRequest contact, Long id);

}
