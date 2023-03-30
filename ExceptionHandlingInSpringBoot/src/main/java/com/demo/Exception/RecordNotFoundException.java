package com.demo.Exception;

public class RecordNotFoundException extends RuntimeException {

	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String mssg) {
		super(mssg);
	}

}
