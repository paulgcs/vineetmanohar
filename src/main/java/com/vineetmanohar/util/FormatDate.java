package com.vineetmanohar.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ${formatDate["MMM dd, yyyy HH:mm"][user.creationDate]}
 * 
 * @author Vineet Manohar
 * 
 */
public class FormatDate extends ELMethod {
	private static final long serialVersionUID = 1L;

	public FormatDate() {
		super(2);
	}

	public Object result(Object[] args) {
		String pattern = (String) args[0];
		Date date = (Date) args[1];

		return new SimpleDateFormat(pattern).format(date);
	}
}