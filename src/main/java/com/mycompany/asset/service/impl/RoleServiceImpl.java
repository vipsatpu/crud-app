package com.mycompany.asset.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.asset.dao.RoleDao;
import com.mycompany.asset.model.Role;
import com.mycompany.asset.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role findById(int id) {
		logger.info("RoleServiceImpl.findById :-" + id);
		return roleDao.findById(id);
	}

	@Override
	public Role findByName(String name) {
		logger.info("RoleServiceImpl.findByName :-" + name);
		return roleDao.findByName(name);
	}

	@Override
	public Role save(Role role) {
		logger.info("RoleServiceImpl.save :-" + role.getRoleName());
		return roleDao.save(role);
	}

	@Override
	public void deleteById(int id) {
		logger.info("RoleServiceImpl.deleteById :-" + id);
		roleDao.deleteById(id);
	}

	@Override
	public List<Role> findAllRoles() {
		logger.info("RoleServiceImpl.findAllRoles :-");
		return roleDao.findAllRoles();
	}

}
