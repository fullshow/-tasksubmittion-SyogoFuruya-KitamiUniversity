package com.mongo;

public class MongoTodoListData {
	
	// オプション
	private String memo; 			// Todo本文
	private String remark;			// Todoの備考欄		
	
	public MongoTodoListData() {
	}
	
	public MongoTodoListData(String memo , String remark) {
		super();
		this.memo = memo;
		this.remark = remark;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
