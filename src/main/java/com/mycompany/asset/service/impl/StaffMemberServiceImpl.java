package com.mycompany.asset.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.asset.dao.StaffMemberDao;
import com.mycompany.asset.model.StaffMember;
import com.mycompany.asset.service.StaffMemberService;

@Service
@Transactional
public class StaffMemberServiceImpl implements StaffMemberService {

	private static final Logger logger = LogManager.getLogger(StaffMemberServiceImpl.class);
	
	@Autowired
	private StaffMemberDao staffMemberDao;
	
	@Override
	public StaffMember findById(int id) {
		logger.info("StaffMemberServiceImpl.findById :-"+id);
		return staffMemberDao.findById(id);
	}

	@Override
	@Transactional
	public StaffMember findByUserId(String userId) {
		logger.info("StaffMemberServiceImpl.findByUserId :-"+userId);
		return staffMemberDao.findByUserId(userId);
	}

	@Override
	public StaffMember save(StaffMember staffMember) {
		logger.info("StaffMemberServiceImpl.save :-"+staffMember.getFirstName());
		return staffMemberDao.save(staffMember);
	}

	@Override
	public void deleteByUserId(String userId) {
		logger.info("StaffMemberServiceImpl.deleteByUserId :-"+userId);
		staffMemberDao.deleteByUserId(userId);
	}

	@Override
	@Transactional
	public List<StaffMember> findAllStaff() {
		logger.info("StaffMemberServiceImpl.findAllStaff :-");
		return staffMemberDao.findAllStaff();
	}

}
