package com.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.ContactRepository;
import com.demo.entities.Contact;

@RestController
@CrossOrigin("*")
public class ContactRestService {

	@Autowired
	private ContactRepository contactRepository;
	
	/*
	 * get all contact from data base  
	 */
	@RequestMapping(value="/contacts",method=RequestMethod.GET)
	public List<Contact> getAll() {
		
		return contactRepository.findAll();
	}
	/*
	 * get one contact from data base the method has been changed from findOne to findById
	 */
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.GET)
	public Contact getOne(@PathVariable Long id) {
		
		return contactRepository.findById(id).get();
	}
	
	/*
	 * add contact using http method post the param send in the body of 
	 * the request 
	 */
	@RequestMapping(value="/contacts",method=RequestMethod.POST)
	public Contact save(@RequestBody Contact c) {
		System.out.println(c.getDateNaissance());
		return contactRepository.save(c);
	}
	
	/*
	 * remove contact from data base
	 */
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id) {
		contactRepository.deleteById(id);
		return true;
	}
	
	/*
	 * update contact from data base there is no method merge in JpaRepository!!
	 */
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.PUT)
	public Contact update(@PathVariable Long id,@RequestBody Contact c) {
		c.setId(id);
		return contactRepository.save(c);
	}
	
	/*
	 * search contact by  word 
	 */
	
	@RequestMapping(value="/ChercherContacts",method=RequestMethod.GET)
	public Page<Contact> chercher(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) {
		
		return contactRepository.chercher("%"+mc+"%", new PageRequest(page, size));
	}
	
}
