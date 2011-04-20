package org.prefs;

public class ConflictingIdentifierException extends Exception {

	private static final long serialVersionUID = -6756684810786923572L;

	public ConflictingIdentifierException() {
		this(null);
	}
	
	public ConflictingIdentifierException(String message) {
		super(message);
	}
}
