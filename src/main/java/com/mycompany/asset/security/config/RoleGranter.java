package com.mycompany.asset.security.config;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.stereotype.Service;

import com.mycompany.asset.model.Role;
import com.mycompany.asset.model.StaffMember;
import com.mycompany.asset.service.StaffMemberService;


@Service("roleGranter")
public class RoleGranter  implements AuthorityGranter {

	@Autowired
	StaffMemberService memberService;
	
	@Override
	public Set<String> grant(Principal principal) {
		Set<String> roles = new HashSet();
		StaffMember memberRoles = memberService.findByUserId(principal.getName());
		System.out.println(" User Roles :"+memberRoles.getStaffRoles());
		for(Role role : memberRoles.getStaffRoles()) {			
			System.out.println(" Role :"+role.getRoleName());
			Set<String> groupSet =  Collections.singleton("ROLE_"+role.getRoleName());
			roles.addAll(groupSet);
		}
		return null;
	}

}
