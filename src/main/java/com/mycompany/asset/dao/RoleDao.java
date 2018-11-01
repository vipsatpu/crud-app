package com.mycompany.asset.dao;

import java.util.List;

import com.mycompany.asset.model.Role;

public interface RoleDao {
	
	Role findById(int id);
	
	Role findByName(String name);
	
	Role save(Role role);
	
	void deleteById(int id);
	
	List<Role> findAllRoles();
}
