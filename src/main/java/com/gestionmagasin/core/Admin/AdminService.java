package com.gestionmagasin.core.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AdminService implements AdminServiceImplementation{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public List<Admin> getAll() {
		return adminRepository.findAll();
	}

	@Override
	public Optional<Admin> getById(Long id) {
		return adminRepository.findById(id);
	}
	

	@Override
	public List<Admin> getByNom(String nom) {
		return adminRepository.findByNom(nom);
	}

	@Override
	public List<Admin> getByPrenom(String prenom) {
		return adminRepository.findByPrenom(prenom);
	}

	@Override
	public List<Admin> getByEmail(String email) {
		return null;
	}

	@Override
	public List<Admin> getByTelephone(String telephone) {
		return adminRepository.findByTelephone(telephone);
	}

	@Override
	public List<Admin> getByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

	@Override
	public List<Admin> getByPassword(String password) {
		return adminRepository.findByPassword(password);
	}


	@Override
	public Admin create(String username, String password, String nom, String prenom, String email, String telephone) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setNom(nom);
        admin.setPrenom(prenom);
        admin.setEmail(email);
        admin.setTelephone(telephone);
        return adminRepository.save(admin);
	}

	@Override
	public Admin update(Long id, String username, String password, String nom, String prenom, String email, String telephone) {
		  Optional<Admin> adminOptional = adminRepository.findById(id);
	        if (adminOptional.isPresent()) {
	            Admin admin = adminOptional.get();

	            if (username != null) {
	                admin.setUsername(username);
	            }

	            if (password != null) {
	                admin.setPassword(password);
	            }
	            
	            if (nom != null) {
	                admin.setNom(nom);
	            }
	            
	            if (prenom != null) {
	                admin.setPrenom(prenom);
	            }
	            
	            if (email != null) {
	                admin.setEmail(email);
	            }
	            
	            if (telephone != null) {
	                admin.setTelephone(telephone);
	            }
	         

	            return adminRepository.save(admin);
	        }
	        return null;
	}

	@Override
	public Admin delete(Long id) {
		 Optional<Admin> adminOptional = adminRepository.findById(id);
	        if (adminOptional.isPresent()) {
	            Admin admin = adminOptional.get();
	            adminRepository.deleteById(id);
	            return admin;
	        }
		return null;
	}

}
