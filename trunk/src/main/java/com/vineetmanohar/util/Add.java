package com.vineetmanohar.util;

import java.util.Map;

public class Add extends ELMethod {
	private static final long serialVersionUID = 1L;

	public Add() {
		super(2);
	}

	public Object result(Object... args) {
		double total = 0;
		for (Object arg : args) {
			Number number = (Number) arg;
			total += number.doubleValue();
		}

		return total;
	}

	public static void main(String[] args) {
		Add add = new Add();

		Object res1 = add.get(1);
		Map map1 = (Map) res1;
		Object res2 = map1.get(2);
		
		System.out.print(res2);
	}
}