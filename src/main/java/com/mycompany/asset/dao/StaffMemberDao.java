package com.mycompany.asset.dao;

import java.util.List;

import com.mycompany.asset.model.StaffMember;

public interface StaffMemberDao{

	StaffMember findById(int id);

	StaffMember findByUserId(String userId);

	StaffMember save(StaffMember staff);

	void deleteByUserId(String userId);

	List<StaffMember> findAllStaff();

}
