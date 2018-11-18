package com.java.smart.services.channel;

import com.java.smart.enums.ResultCode;
import com.java.smart.exception.SmartException;
import com.java.smart.mybatis.mapper.ChannelMapper;
import com.java.smart.mybatis.model.Channel;
import com.java.smart.pojo.ChannelReq;
import com.java.smart.pojo.ChannelRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChannelService {

	@Resource
	ChannelMapper channelMapper;

	private static Logger logger = LoggerFactory.getLogger(ChannelService.class);

	public Map<String,Object> getChannelList(Map<String, String> conditions) throws SmartException {

		Map<String,Object> map = new HashMap<String,Object>();
		List<Channel>  channelList = new ArrayList<Channel>();
		List<ChannelRes> channels = new ArrayList<ChannelRes>();

		int limit = 10;
		int offset = 0;
		if (conditions.containsKey("limit")) {
			limit = Integer.parseInt(conditions.get("limit"));
		}// 每页条数
		if (conditions.containsKey("offset")) {
			offset = Integer.parseInt(conditions.get("offset")) / limit;
		}// 页码
		conditions.remove("limit");
		conditions.remove("offset");
		conditions.put("rowstart", String.valueOf(offset * limit + 1));
		conditions.put("rowend", String.valueOf((offset + 1) * limit));

		Integer count = channelMapper.getCountByConditions(conditions);
		logger.debug("根据条件查询到的条数为--" + count);

		if(StringUtils.isEmpty(count) || count == 0){
			throw new SmartException(ResultCode.NotFoundMEssage.getCode(),ResultCode.NotFoundMEssage.getMsg());
		}
		map.put("total",count);

		channelList = channelMapper.getChannelByConditions(conditions);

		channels = convert(channelList);

		map.put("channels",channels);

		return map;
	}


	/**
	 * 根据渠道记录的主键删除渠道记录
	 * @param pk1
	 * @throws SmartException
	 */
	public void deleteChannel(String pk1) throws SmartException{
		 int c = channelMapper.deleteChannel(pk1);

		 logger.debug("删除的条数为：" + c);
		 if(c <= 0){
		 	throw new SmartException(ResultCode.DeleteFailed.getCode(),ResultCode.DeleteFailed.getMsg());
		 }
	}


	/**
	 * 根据渠道记录的主键查询渠道记录
	 * @param pk1
	 * @return
	 * @throws SmartException
	 */
	public ChannelRes getChannelByPK1(String pk1) throws SmartException{

		Channel channel = channelMapper.getChannelByPK1(pk1);

		if(null == channel){
			throw new SmartException(ResultCode.NotFoundMEssage.getCode(),ResultCode.NotFoundMEssage.getMsg());
		}

		ChannelRes channelRes = new ChannelRes();
		channelRes.setPk1(channel.getPk1());
		channelRes.setChannelId(channel.getChannelId()==null ? "":channel.getChannelId());
		channelRes.setChannelName(channel.getChannelName()==null ? "":channel.getChannelName());
		channelRes.setChannelEname(channel.getChannelEname()==null ? "":channel.getChannelEname());
		channelRes.setProtocol(channel.getProtocol()==null ? "":channel.getProtocol());
		channelRes.setMessageType(channel.getMessageType()==null ? "":channel.getMessageType());
		channelRes.setReqUnicode(channel.getReqUnicode()==null ? "UTF-8":channel.getReqUnicode());
		channelRes.setResUnicode(channel.getResUnicode()==null ? "UTF-8":channel.getResUnicode());
		channelRes.setContactName(channel.getContactName()==null ? "":channel.getContactName());
		channelRes.setContactPhone(channel.getContactPhone()==null ? "":channel.getContactPhone());
		channelRes.setRemark(channel.getRemark()==null ? "":channel.getRemark());
		channelRes.setPort(channel.getPort()==null ? "":channel.getPort());
		channelRes.setLength(channel.getLength());

		return channelRes;
	}

	/**
	 * 插入一条新的渠道信息
	 * @param req
	 */
	public void insertChannel(ChannelReq req) throws SmartException{

		if(StringUtils.isEmpty(req.getChannelId())){
			throw new SmartException(ResultCode.UpdateFailed.getCode(),ResultCode.UpdateFailed.getMsg());
		}


		//将请求字段映射到插入数据的字段中
		Channel channel = new Channel();
		channel.setPk1(req.getPk1());
		channel.setChannelId(req.getChannelId());
		channel.setChannelName(req.getChannelName());
		channel.setChannelEname(req.getChannelEname().toUpperCase());
		channel.setContactName(req.getContactName());
		channel.setContactPhone(req.getContactPhone());
		channel.setMessageType(req.getMessageType().toUpperCase());
		channel.setPort(req.getPort());
		channel.setProtocol(req.getProtocol().toUpperCase());
		channel.setRemark(req.getRemark());
		channel.setReqUnicode(req.getReqUnicode().toUpperCase());
		channel.setResUnicode(req.getResUnicode().toUpperCase());
		channel.setLength(req.getLength());

		//判断是否存在同一个渠道 或同一个端口的渠道信息
		int countChannel = channelMapper.veriftChannel(channel);
		if(countChannel > 0){
			throw new SmartException(ResultCode.InsertFailedWithRepeat.getCode(),ResultCode.InsertFailedWithRepeat.getMsg());
		}

		int count;
		try{
			count = channelMapper.addChannel(channel);
		}catch (Exception e){
			logger.debug("数据库操作异常："+e.getMessage());
			throw new SmartException(ResultCode.SqlException.getCode(),ResultCode.SqlException.getMsg());
		}

		if(count <= 0){
			throw new SmartException(ResultCode.UpdateFailed.getCode(),ResultCode.UpdateFailed.getMsg());
		}




	}

	/**
	 * 根据渠道PK 修改一条数据
	 * @param req
	 */
	public void updateChannel(ChannelReq req) throws SmartException{

		//将请求字段映射到插入数据的字段中
		Channel channel = new Channel();
		channel.setPk1(req.getPk1());
		channel.setChannelId(null);
		channel.setChannelName(req.getChannelName());
		channel.setChannelEname(req.getChannelEname().toUpperCase());
		channel.setContactName(req.getContactName());
		channel.setContactPhone(req.getContactPhone());
		channel.setMessageType(req.getMessageType());
		channel.setPort(req.getPort());
		channel.setProtocol(req.getProtocol().toUpperCase());
		channel.setRemark(req.getRemark());
		channel.setReqUnicode(req.getReqUnicode().toUpperCase());
		channel.setResUnicode(req.getResUnicode().toUpperCase());
		channel.setLength(req.getLength());

		int count;
		try{
			count = channelMapper.updateChannelInfo(channel);
		}catch (Exception e){
			logger.debug("数据库操作异常："+e.getMessage());
			throw new SmartException(ResultCode.SqlException.getCode(),ResultCode.SqlException.getMsg());
		}

		if(count <= 0){
			throw new SmartException(ResultCode.UpdateFailed.getCode(),ResultCode.UpdateFailed.getMsg());
		}

	}

	private List<ChannelRes> convert(List<Channel> channelList){

		if(null == channelList || channelList.size() == 0){
			return null;
		}

		List<ChannelRes> channels = new ArrayList<ChannelRes>();

		for(Channel channel: channelList){
			ChannelRes channelRes = new ChannelRes();
			channelRes.setPk1(channel.getPk1());
			channelRes.setChannelId(channel.getChannelId()==null ? "":channel.getChannelId());
			channelRes.setChannelName(channel.getChannelName()==null ? "":channel.getChannelName());
			channelRes.setChannelEname(channel.getChannelEname()==null ? "":channel.getChannelEname());
			channelRes.setProtocol(channel.getProtocol()==null ? "":channel.getProtocol());
			channelRes.setMessageType(channel.getMessageType()==null ? "":channel.getMessageType());
			channelRes.setReqUnicode(channel.getReqUnicode()==null ? "UTF-8":channel.getReqUnicode());
			channelRes.setResUnicode(channel.getResUnicode()==null ? "UTF-8":channel.getResUnicode());
			channelRes.setContactName(channel.getContactName()==null ? "":channel.getContactName());
			channelRes.setContactPhone(channel.getContactPhone()==null ? "":channel.getContactPhone());
			channelRes.setRemark(channel.getRemark()==null ? "":channel.getRemark());
			channelRes.setPort(channel.getPort()==null ? "":channel.getPort());
			channelRes.setLength(channel.getLength()==null ? "":channel.getLength());

			channels.add(channelRes);
		}
		return channels;
	}
}
