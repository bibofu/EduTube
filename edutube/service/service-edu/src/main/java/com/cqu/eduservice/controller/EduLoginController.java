package com.cqu.eduservice.controller;

import com.cqu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author fubibo
 * @create 2021-07-06 上午10:24
 */


@Api(description = "管理员登录")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @ApiOperation(value = "登录返回token")
    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");
    }

    @ApiOperation(value = "返回用户信息")
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://z3.ax1x.com/2021/07/20/WYqCX4.png");
    }
}
