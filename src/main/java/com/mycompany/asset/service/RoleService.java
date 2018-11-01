package com.mycompany.asset.service;

import java.util.List;

import com.mycompany.asset.model.Role;

public interface RoleService {

	Role findById(int id);

	Role findByName(String name);

	Role save(Role role);

	void deleteById(int id);

	List<Role> findAllRoles();

}
