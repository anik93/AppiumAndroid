package com.selenium.exceptions;

public class SortNotFoundException extends Exception {

	private static final long serialVersionUID = -3422030315741193360L;

	private String name;

	public SortNotFoundException(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
