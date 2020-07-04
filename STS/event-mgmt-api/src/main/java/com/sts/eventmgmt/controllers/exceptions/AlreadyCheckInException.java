package com.sts.eventmgmt.controllers.exceptions;

public class AlreadyCheckInException extends RuntimeException {
	
	public void notifyAlreadyCheckedIn() {
		
		System.out.println("already checked in!");
	}

}
