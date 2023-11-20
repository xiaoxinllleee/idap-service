package org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.Cldkhtsjgl;

/**
 * @Description: 存量贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-06
 * @Version: V1.0
 */
public interface CldkhtsjglMapper extends BaseMapper<Cldkhtsjgl> {
    public void pCldkhtsjgl();
}
