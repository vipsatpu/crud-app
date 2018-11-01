package com.mycompany.asset.service;

import java.util.List;

import com.mycompany.asset.model.StaffMember;

public interface StaffMemberService {

	StaffMember findById(int id);

	StaffMember findByUserId(String userId);

	StaffMember save(StaffMember staff);

	void deleteByUserId(String userId);

	List<StaffMember> findAllStaff();
}
