package br.com.contact.model;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;


    public Contact convertToEntity(ContactRequest request) {

        return Contact.builder().name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    public ContactResponse convertToContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.email)
                .phone(contact.getPhone())
                .build();
    }


}
