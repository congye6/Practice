package nju.sec.yz.ExpressSystem.exception;

public class InvalidPasswordException extends Exception{

	
	public InvalidPasswordException(){
		
	}
	
	public InvalidPasswordException(String msg){
		super(msg);
	}
}
