package com.cqu.msmservice.service;

import java.util.Map;

/**
 * @author fubibo
 * @create 2021-07-14 下午5:18
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
