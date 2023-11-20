package org.cmms.modules.khgl.nh.mapper;

import java.util.List;
import org.cmms.modules.khgl.nh.entity.CamsZcsxNhcjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 农户采集信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Component
public interface CamsZcsxNhcjxxMapper extends BaseMapper<CamsZcsxNhcjxx> {

	public boolean deleteByMainId(String mainId);
    
	public List<CamsZcsxNhcjxx> selectByMainId(String mainId);
}
