package com.java.smart.mybatis.mapper;

import com.java.smart.mybatis.model.Channel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChannelMapper {

    /**
     * 拉取全部渠道
     * @return
     */
    public List<Channel> getChannelList();

    /**
     * 根据条件查询渠道信息
     * @return
     */
    public List<Channel> getChannelByConditions(Map<String,String> conditions);

    /**
     * 根据条件查询渠道信息条数
     * @return
     */
    public Integer getCountByConditions(Map<String,String> conditions);

    /**
     * 根据主键查询单笔渠道信息
     * @return
     */
    public Channel getChannelByPK1(String pk1);

    /**
     * 修改渠道信息
     * @param channel
     */
    public int updateChannelInfo(Channel channel);

    /**
     * 删除渠道信息
     * @param pk1
     */
    public int deleteChannel(String pk1);

    /**
     * 新增渠道
     * @param channel
     */
    public int addChannel(Channel channel);


    /**
     * 校验渠道信息是否存在
     * @param channel
     * @return
     */
    public int veriftChannel(Channel channel);
}
