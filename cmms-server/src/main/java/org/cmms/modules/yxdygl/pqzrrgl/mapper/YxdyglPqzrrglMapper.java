package org.cmms.modules.yxdygl.pqzrrgl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxdygl.pqzrrgl.entity.YxdyglPqzrrgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 片区责任人管理
 * @Author: jeecg-boot
 * @Date:   2020-06-30
 * @Version: V1.0
 */
public interface YxdyglPqzrrglMapper extends BaseMapper<YxdyglPqzrrgl> {

    public YxdyglPqzrrgl queryqydm(Map<String,String>sql);

    public  int queryCountBykhjl(String khjl);
}
