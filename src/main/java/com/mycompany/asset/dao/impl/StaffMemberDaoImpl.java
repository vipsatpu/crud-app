package com.mycompany.asset.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.asset.dao.StaffMemberDao;
import com.mycompany.asset.model.StaffMember;

@Repository("staffMemberDao")
@Transactional
public class StaffMemberDaoImpl extends AbstractDao<Integer, StaffMember> implements StaffMemberDao {

	private static final Logger logger = LogManager.getLogger(StaffMemberDaoImpl.class);

	@Override
	public StaffMember findById(int id) {
		logger.info("StaffDaoImpl.findById");

		StaffMember staffMember = getByKey(id);
		if (staffMember != null) {
			Hibernate.initialize(staffMember.getStaffRoles());
		}
		return staffMember;
	}

	@Override
	@Transactional
	public StaffMember findByUserId(String userId) {
		logger.info("StaffDaoImpl.findByUserId");
		//Criteria crit = createEntityCriteria();
		
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<StaffMember> q = cb.createQuery(StaffMember.class);
		Root<StaffMember> pClass = q.from(StaffMember.class);
		ParameterExpression<String> p = cb.parameter(String.class);
		q.select(pClass).where(cb.equal(pClass.get("appUserId"), userId));
		
		List<StaffMember> staffs = getSession().createQuery(q).getResultList();
		
		StaffMember staffMember = null;
		if(staffs != null && staffs.size() > 0) {
			staffMember =staffs.get(0);
		}	
		return staffMember;
	}

	@Override
	public StaffMember save(StaffMember sMember) {
		logger.info("StaffDaoImpl.save");
		return saveStaff(sMember);
	}

	public StaffMember saveStaff(StaffMember sMember) {
		logger.info("StaffDaoImpl.saveStaff");
		getSession().save(sMember);
		return sMember;
	}

	@Override
	public void deleteByUserId(String userId) {
		logger.info("StaffDaoImpl.deleteByUserId");
	}

	@Override
	@Transactional
	public List<StaffMember> findAllStaff() {
		logger.info("StaffDaoImpl.findAllStaff");

		CriteriaQuery<StaffMember> criteria = getSession().getCriteriaBuilder().createQuery(StaffMember.class);
		Root<StaffMember> pClass = criteria.from(StaffMember.class);
		criteria.select(pClass);
		List<StaffMember> staffs = getSession().createQuery(criteria).getResultList();
		return staffs;
	}
}
