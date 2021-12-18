package br.com.contact.controller;


import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.service.ContactService;
import br.com.contact.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> get(){
        return this.contactService.getAllContact();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody ContactRequest request){
        this.contactService.createContact(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteContact(@RequestParam Long id) {
        this.contactService.removeContact(id);
    }

    @GetMapping("/find-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> getContactByName(@PathVariable String name) {
       return this.contactService.getContactByName(name);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void putContact(@RequestBody ContactRequest contact, @PathVariable Long id) {
    	this.contactService.updateContact(contact, id);
    }

    
    
    
    
    
    
    
    
    
    
}
