package org.cmms.modules.ywgl.cdkfx.wdcdktj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.wdcdktj.entity.ErpYljcWdcdktj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 网点存贷款统计
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
public interface ErpYljcWdcdktjMapper extends BaseMapper<ErpYljcWdcdktj> {
    void pWdcdktj(@Param("jgdm")String jgdm,@Param("tjyf")String tjyf,@Param("username")String username);

    /**
     * 从`sys_bas_cfg`获取业务科目号
     * @param cfgcode 业务参数代码
     * @return
     */
    String querySubjectNo(@Param("cfgcode") String cfgcode);
}
