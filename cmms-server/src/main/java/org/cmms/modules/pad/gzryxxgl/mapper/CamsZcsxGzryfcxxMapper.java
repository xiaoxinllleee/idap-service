package org.cmms.modules.pad.gzryxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.gzryxxgl.entity.CamsZcsxGzryfcxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 公职人员房产信息表
 * @Author: jeecg-boot
 * @Date:   2022-09-05
 * @Version: V1.0
 */
public interface CamsZcsxGzryfcxxMapper extends BaseMapper<CamsZcsxGzryfcxx> {

    public List<CamsZcsxGzryfcxx> getByGzryid(String gzryid);

}
