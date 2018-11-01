package com.mycompany.asset.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mycompany.asset.dao.RoleDao;
import com.mycompany.asset.model.Role;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {

	private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

	@Override
	public Role findById(int id) {
		logger.info("RoleDaoImpl.findById");
		return getByKey(id);
	}

	@Override
	public Role findByName(String name) {
		logger.info("RoleDaoImpl.findByName");
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("role_name", name));
		return  (Role) crit.uniqueResult();
	}

	@Override
	public Role save(Role role) {
		logger.info("RoleDaoImpl.save");
		getSession().save(role);
		return role;
	}

	@Override
	public void deleteById(int id) {
		logger.info("RoleDaoImpl.deleteById");
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("role_id", id));
		Role memberRole = (Role) crit.uniqueResult();
		delete(memberRole);
	}

	@Override
	public List<Role> findAllRoles() {
		logger.info("RoleDaoImpl.findAllRoles");
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("role_name"));
		return (List<Role>) crit.list();
	}

}
