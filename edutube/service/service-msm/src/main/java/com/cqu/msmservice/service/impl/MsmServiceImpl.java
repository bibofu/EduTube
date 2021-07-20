package com.cqu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cqu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author fubibo
 * @create 2021-07-14 下午5:18
 */

@Service
public class MsmServiceImpl implements MsmService {


    @Override
    public boolean send(Map<String, Object> param, String phone) {
       return true;
        /**
         * to be finished
         * 实现阿里云发送短信到收集
         */
    }
}
