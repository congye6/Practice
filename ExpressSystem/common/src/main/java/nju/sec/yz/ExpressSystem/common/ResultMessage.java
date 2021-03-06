package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 返回信息
 * @author 周聪
 */
public class ResultMessage implements Serializable{

	private Result result;
	//备注
	private String message;
	
	public ResultMessage(Result result){
		this.result=result;
		if(result==Result.SUCCESS){
			message="success";
		}
		else message="failure";
	}
	
	public ResultMessage(Result result,String message){
		this.result=result;
		this.message=message;
	}

	
	public Result getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}
	
	public void setResult(Result result) {
		this.result = result;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
