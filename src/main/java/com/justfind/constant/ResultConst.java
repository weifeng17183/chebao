package com.justfind.constant;

import com.justfind.view.Message;

public class ResultConst {

	public final static int SUCCESS = 0;
	public final static int FAILED = -1;

	public static Message createMessage(String msg, int error, Object data) {

		Message message = new Message();
		message.setError(error);
		message.setMsg(msg);
		message.setData(data);
		return message;
	}

	public static Message success(Object data) {

		Message msg = new Message();
		msg.setError(SUCCESS);
		msg.setMsg("ok");
		msg.setData(data);

		return msg;
	}

	public static Message success(Object data, int total) {

		Message msg = new Message();
		msg.setError(SUCCESS);
		msg.setMsg("ok");
		msg.setData(data);
		return msg;
	}

	public static Message fail(Object data) {

		Message msg = new Message();
		msg.setError(FAILED);
		msg.setMsg("fail");
		msg.setData(data);
		return msg;
	}

}
