package org.cmms.modules.khxxgl.khflgl.qyxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsJbxxQyzllb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.shxxgl.entity.CamsJbxxShzllb;

/**
 * @Description: 企业附件资料列表
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
public interface CamsJbxxQyzllbMapper extends BaseMapper<CamsJbxxQyzllb> {

    public List<CamsJbxxQyzllb> getByQyid(String qyid);
}
