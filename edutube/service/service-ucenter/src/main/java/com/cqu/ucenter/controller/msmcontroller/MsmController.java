package com.cqu.ucenter.controller.msmcontroller;

import com.cqu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author fubibo
 * @create 2021-07-21 下午2:06
 */

@Api(description = "根据手机号发送验证码")
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信的方法
    @ApiOperation(value = "发送短信")
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
//        code = RandomUtil.getFourBitRandom();
        //写死验证码1234
        code="1234";
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);

//        boolean isSend = msmService.send(param,phone);
        boolean isSend=true;
        if(isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("失败");
        }
    }


}
