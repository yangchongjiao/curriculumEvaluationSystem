package com.curriculumEvaluationSystem.exception;


public class CustomerException extends RuntimeException {

	private static final long serialVersionUID = 4198083849617940127L;

	private int errorCode;
	
	private String errorMsg;
	
	public CustomerException(int errorCode, String errorMsg ){
		this.errorCode = errorCode;
		
		this.errorMsg = errorMsg;
	}
	public CustomerException(Throwable cause,int errorCode) {
        super(errorCode + "", cause);
        this.errorCode = errorCode;
    }

    public CustomerException(Throwable cause, String errMessage) {
        super(errMessage, cause);
        this.errorCode = 10000;
    }
    
	public CustomerException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}
	
	public CustomerException(Throwable cause) {
		super(cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
