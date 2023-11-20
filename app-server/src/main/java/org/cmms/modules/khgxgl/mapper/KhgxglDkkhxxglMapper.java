package org.cmms.modules.khgxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgxgl.entity.KhgxglDkkhxxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款客户信息管理
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
public interface KhgxglDkkhxxglMapper extends BaseMapper<KhgxglDkkhxxgl> {

    String getAllCpxxByZjhm(String zjhm);
    String getAllCpxxByHth(String hth);
}
