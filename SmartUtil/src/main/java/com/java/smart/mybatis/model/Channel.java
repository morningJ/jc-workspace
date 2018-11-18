package com.java.smart.mybatis.model;

import java.io.Serializable;

public class Channel implements Serializable {

	/**
	 * 主键
	 */
	private String pk1;

	/**
	 * 渠道编号
	 */
	private String channelId;

	/**
	 * 渠道名称
	 */
	private String channelName;

	/**
	 * 渠道英文简称
	 */
	private String channelEname;

	/**
	 * 通讯协议
	 */
	private String protocol;

	/***
	 * 报文格式
	 */
	private String messageType;

	/**
	 * 请求字符集
	 */
	private String reqUnicode;

	/**
	 * 返回字符集
	 */
	private String resUnicode;

	/**
	 * 请求端口
	 */
	private String port;

	/**
	 * 联系人姓名
	 */
	private String contactName;

	/**
	 * 联系人电话
	 */
	private String contactPhone;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * TCP报文时报文头长度
	 */
	private String length;

	public String getPk1() {
		return pk1;
	}

	public void setPk1(String pk1) {
		this.pk1 = pk1;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelEname() {
		return channelEname;
	}

	public void setChannelEname(String channelEname) {
		this.channelEname = channelEname;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getReqUnicode() {
		return reqUnicode;
	}

	public void setReqUnicode(String reqUnicode) {
		this.reqUnicode = reqUnicode;
	}

	public String getResUnicode() {
		return resUnicode;
	}

	public void setResUnicode(String resUnicode) {
		this.resUnicode = resUnicode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
}
