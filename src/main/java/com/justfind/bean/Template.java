package com.justfind.bean;

import java.util.List;

/**
 * 微信推送消息模版
 *
 */

public class Template {
	// 消息接收方
	private String touser;
	// 模板id
	private String templateId;
	// 模板消息详情链接
	private String url;
	// 参数列表
	private List<TemplateParam> templateParamList;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toJSON() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append(String.format("\"touser\":\"%s\"", this.touser)).append(",");
		buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
		if (this.url != null) {
			buffer.append(String.format("\"url\":\"%s\"", this.url)).append(",");
		}
		buffer.append("\"data\":{");
		TemplateParam param = null;
		for (int i = 0; i < this.templateParamList.size(); i++) {
			param = templateParamList.get(i);
			// 判断是否追加逗号
			if (i < this.templateParamList.size() - 1) {

				buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(),
						param.getValue(), param.getColor()));
			} else {
				buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(),
						param.getValue(), param.getColor()));
			}

		}
		buffer.append("}");
		buffer.append("}");
		return buffer.toString();
	}

	public List<TemplateParam> getTemplateParamList() {
		return templateParamList;
	}

	public void setTemplateParamList(List<TemplateParam> templateParamList) {
		this.templateParamList = templateParamList;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
}
