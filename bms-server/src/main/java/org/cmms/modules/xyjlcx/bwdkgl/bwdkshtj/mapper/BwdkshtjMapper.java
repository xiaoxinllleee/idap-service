package org.cmms.modules.xyjlcx.bwdkgl.bwdkshtj.mapper;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshtj.entity.Bwdkshtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 表外贷款收回统计
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
public interface BwdkshtjMapper extends BaseMapper<Bwdkshtj> {
    void pBwdkshtj(@Param("tjrqq")String tjrqq,@Param("tjrqz")String tjrqz,@Param("username")String username,@Param("rzwd")String rzwd);
}
