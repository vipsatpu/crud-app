package com.mycompany.asset.rest.controller;

import java.text.MessageFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.asset.model.StaffMember;
import com.mycompany.asset.service.StaffMemberService;
import com.mycompany.asset.utils.Messages;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	StaffMemberService staffMemberService;

	/**
	 * Retrieves all Users
	 * 
	 * @return
	 */
	@RequestMapping(value = "/secure/list/", method = RequestMethod.GET)
	public ResponseEntity<List<StaffMember>> getAllCustomersInfo() {
		System.out.println(" API.Called ");
		String logMessage = MessageFormat.format(Messages.getMessage("API.called"), "CustomerController",
				"getAllCustomersInfo");
		logger.info(logMessage);
		List<StaffMember> staffMemberList = staffMemberService.findAllStaff();		
		if (staffMemberList.isEmpty()) {

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StaffMember>>(staffMemberList, HttpStatus.OK);
	}
}
