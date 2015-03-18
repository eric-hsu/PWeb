package com.jinxin.common.web;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @className:BaseContronller.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-23 下午2:04:46
 */

public abstract class BaseContronller {
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
}
