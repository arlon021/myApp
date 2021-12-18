package br.com.contact.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.model.Contact;
import br.com.contact.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public void createContact(ContactRequest request) {
        this.contactRepository.save(new Contact().convertToEntity(request));
    }

    @Override
    public void removeContact(Long id) {
        this.contactRepository.deleteById(id);
    }

    @Override
    public List<ContactResponse> getContactByName(String name) {
    	
    	List<ContactResponse> contatcs2 = this.contactRepository.findAll()
    			.stream().map(contact -> contact.convertToContactResponse(contact))
    			.filter(contact -> contact.getName().equals(name)).collect(Collectors.toList());
    	
//        List<Contact> contatcs = this.contactRepository.findAll();
//        List<ContactResponse> contatcsList = new ArrayList<>();
//        for(Contact c : contatcs) {
//        	if(c.getName().equals(name)) {
//        		contatcsList.add(new Contact().convertToContactResponse(c));
//        	}
//        }
        //Contact c = contatcs.stream().filter(contact -> contact.getName().equals(name)).findAny().get();
        return contatcs2;
    }

    @Override
    public List<ContactResponse> getByName(String name) {
        return this.contactRepository.findByName(name)
        		.stream().map(contact -> contact.convertToContactResponse(contact))
        		.collect(Collectors.toList());
    }

    @Override
    public List<ContactResponse> getAllContact() {

        return this.contactRepository.findAll()
                .stream().map(contact -> contact.convertToContactResponse(contact))
                .collect(Collectors.toList());
    }
    
    @Override
    public void updateContact(ContactRequest contact, Long id) {
    	
    	Contact con = contactRepository.findById(id).get();
    	BeanUtils.copyProperties(contact, con, "id");
    	contactRepository.save(con);	
    }

}
