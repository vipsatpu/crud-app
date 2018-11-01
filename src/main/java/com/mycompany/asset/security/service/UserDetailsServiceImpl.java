package com.mycompany.asset.security.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.asset.dao.StaffMemberDao;
import com.mycompany.asset.model.Role;
import com.mycompany.asset.model.StaffMember;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private StaffMemberDao staffMemberDao;	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("UserDetailsServiceImpl.loadUserByUsername --"+userName);
		StaffMember sMember = staffMemberDao.findByUserId(userName);
		if (sMember == null) {
            System.out.println("Staff Member not found! " + userName);
            throw new UsernameNotFoundException("Staff member " + userName + " was not found in the database");
        }		
		List<GrantedAuthority> authorities = getGrantedAuthorities(sMember);
		return new org.springframework.security.core.userdetails.User(sMember.getAppUserId(), sMember.getAppPassword(), 
				 true, true, true, true, authorities);
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(StaffMember user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role memberRole : user.getStaffRoles()){
			logger.info("Staff Member Roles : {}", memberRole);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+memberRole.getRoleName()));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}
