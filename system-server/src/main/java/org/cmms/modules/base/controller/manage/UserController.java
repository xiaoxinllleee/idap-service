package org.cmms.modules.base.controller.manage;

import org.cmms.common.annotation.SystemLog;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.LogType;
import org.cmms.common.exception.XbootException;
import org.cmms.common.utils.*;
import org.cmms.common.vo.PageVo;
import org.cmms.common.vo.Result;
import org.cmms.common.vo.SearchVo;
import org.cmms.modules.base.entity.User;
import org.cmms.modules.base.service.*;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Exrickx
 */
@Slf4j
@RestController
@Api(description = "用户接口")
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;



    @PersistenceContext
    private EntityManager entityManager;


    @RequestMapping(value = "/searchByName/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "通过用户名搜索用户")
    public Result<List<User>> searchByName(@PathVariable String username) throws UnsupportedEncodingException {

        List<User> list = userService.findByUsernameLikeAndStatus(URLDecoder.decode(username, "utf-8"), CommonConstant.STATUS_NORMAL);
        entityManager.clear();
        list.forEach(u -> {
            u.setPassword(null);
        });
        return new ResultUtil<List<User>>().setData(list);
    }

//    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
//    @ApiOperation(value = "获取全部用户数据")
//    public Result<List<User>> getByCondition(){
//
//        List<User> list = userService.getAll();
//        for(User u: list){
//            // 清除持久上下文环境 避免后面语句导致持久化
//            entityManager.clear();
//            u.setPassword(null);
//        }
//        return new ResultUtil<List<User>>().setData(list);
//    }


}
