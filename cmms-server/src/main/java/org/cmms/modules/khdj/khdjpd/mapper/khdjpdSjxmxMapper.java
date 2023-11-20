package org.cmms.modules.khdj.khdjpd.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khdj.khdjpd.entity.khdjpdSjxmx;

/**
 * @Description: 客户等级评定数据项明细
 * @Author: cmms
 * @Date:   2019-11-08
 * @Version: V1.0
 */
public interface khdjpdSjxmxMapper extends BaseMapper<khdjpdSjxmx> {
    
	public List<khdjpdSjxmx> viewDetail(@Param("pdzq") String pdzq,@Param("pdrq") Date pdrq,@Param("zjhm") String zjhm);
}
