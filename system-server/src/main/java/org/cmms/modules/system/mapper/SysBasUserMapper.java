package org.cmms.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.entity.SysBasUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 用户信息
 * @Author: jeecg-boot
 * @Date:   2020-07-22
 * @Version: V1.0
 */
public interface SysBasUserMapper extends BaseMapper<SysBasUser> {
    SysBasUser getByUserId(String userId);
    void updatePassword(@Param("userid") String userid,@Param("password") String password);
}
