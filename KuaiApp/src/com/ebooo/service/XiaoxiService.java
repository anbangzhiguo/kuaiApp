package com.ebooo.service;

import java.util.HashMap;

public class XiaoxiService extends BaseService{

	public XiaoxiService() {
		super();
	}
	public XiaoxiService(String name) {
		super(name);
	}

	@Override
	protected void executeCommand(String cmdId, HashMap params, long stamp) {
		params.put("jjjjjj", "kjkdfsjklcjdskl");
		returnMsgActivity(cmdId,params,stamp);
	}

}
