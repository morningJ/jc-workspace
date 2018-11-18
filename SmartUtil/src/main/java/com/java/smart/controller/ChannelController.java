package com.java.smart.controller;

import com.alibaba.fastjson.JSON;
import com.java.smart.annotation.Token;
import com.java.smart.enums.ResultCode;
import com.java.smart.exception.SmartException;
import com.java.smart.pojo.ChannelReq;
import com.java.smart.pojo.ChannelRes;
import com.java.smart.services.channel.ChannelService;
import com.java.smart.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/channel")
public class ChannelController {
	
	@Resource
	public ChannelService channelService;

	private static Logger logger = LoggerFactory.getLogger(ChannelController.class);

	/**
	 * 查询渠道list
	 * @param request
	 * @return
	 */
	@Token
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public String channelList(HttpServletRequest request){
		ModelMap model = new ModelMap();
		try{

			Map<String, String> conditions = new HashMap<String, String>();
			for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {//entrySet()方法将map集合中的映射关系取出
				if (!StringUtils.isEmpty(entry.getValue()[0])) {
					conditions.put(entry.getKey(), entry.getValue()[0]);
				}
			}

			model.putAll(channelService.getChannelList(conditions));

			model.put("code", ResultCode.susscess.getCode());
			model.put("msg",ResultCode.susscess.getMsg());

		}catch (SmartException e){
			model.put("code",e.getCode());
			model.put("msg",e.getMsg());
			model.put("total", 0);
			model.put("channels",new ArrayList<ChannelRes>());
		}

		return JSON.toJSONString(model);

	}

	/**
	 * 删除渠道信息
	 * @param ids
	 * @return
	 */
	@Token
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap deleteChannel(String ids){
		ModelMap model = new ModelMap();

		try{
			channelService.deleteChannel(ids);

			model.put("code",ResultCode.susscess.getCode());
			model.put("msg",ResultCode.susscess.getMsg());
		}catch (SmartException e){
			model.put("code",e.getCode());
			model.put("msg",e.getMsg());
		}

		return model;
	}

	/**
	 * 根据渠道主键查询渠道信息
	 * @param channel
	 * @param model
	 * @return
	 */
	@Token
    @RequestMapping(value = "/from", method = {RequestMethod.GET})
    public String add(ChannelReq channel, Model model) {

        ChannelRes channelRes = new ChannelRes();
	    try{
            if (StringUtils.isEmpty(channel.getPk1())) {
                throw new SmartException(ResultCode.SelectFailed.getCode(),ResultCode.SelectFailed.getMsg());
            }
            channelRes = channelService.getChannelByPK1(channel.getPk1());

            model.addAttribute("code",ResultCode.susscess.getCode());
            model.addAttribute("msg",ResultCode.susscess.getMsg());

        }catch (SmartException e){
            model.addAttribute("code",e.getCode());
            model.addAttribute("msg",e.getMsg());
        }

        model.addAttribute("channel", channelRes);

        return "channel/edit";
    }

	/**
	 * 添加或修改渠道信息
	 * @param req
	 * @param model
	 * @return
	 */
    @Token
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
    public ModelMap save(ChannelReq req){
		ModelMap model = new ModelMap();
		logger.debug(JSON.toJSONString(req));
		try {
			if(StringUtils.isEmpty(req.getPk1())){
				String pk1 = Constants.getUUID();
				req.setPk1(pk1);
				channelService.insertChannel(req);
			}else{
				channelService.updateChannel(req);
			}

			model.put("code",ResultCode.susscess.getCode());
			model.put("msg",ResultCode.susscess.getMsg());
			model.put("referer", "/channel");
		}catch (SmartException e){
			model.put("code",e.getCode());
			model.put("msg",e.getMsg());
		}

		return model;
	}

}
