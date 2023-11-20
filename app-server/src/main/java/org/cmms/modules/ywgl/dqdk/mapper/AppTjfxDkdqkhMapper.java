package org.cmms.modules.ywgl.dqdk.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.dqdk.entity.AppDqdkVO;
import org.cmms.modules.ywgl.dqdk.entity.AppTjfxDkdqkh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
public interface AppTjfxDkdqkhMapper extends BaseMapper<AppTjfxDkdqkh> {

    IPage<AppDqdkVO> getListByPage(Page page,@Param("dqrq") String dqrq
            ,@Param("dkye") String dkye,@Param("ye") String ye
            ,@Param("khlx") String khlx,@Param("yggh")String yggh);
}
