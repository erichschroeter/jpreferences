package org.jpreferences.storage;

/**
 * Should be thrown when an identifier conflicts with another in a storage object.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:05 PM
 */
public class ConflictingIdentifierException extends Exception {



	public void finalize() throws Throwable {
		super.finalize();
	}

	public ConflictingIdentifierException(){

	}

	/**
	 * 
	 * @param message
	 */
	public ConflictingIdentifierException(String message){

	}

}