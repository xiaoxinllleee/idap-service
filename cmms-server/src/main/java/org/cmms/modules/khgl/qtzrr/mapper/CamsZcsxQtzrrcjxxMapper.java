package org.cmms.modules.khgl.qtzrr.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.qtzrr.entity.CamsZcsxQtzrrcjxx;
import org.springframework.stereotype.Component;

/**
 * @Description: 其他自然人采集信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Component
public interface CamsZcsxQtzrrcjxxMapper extends BaseMapper<CamsZcsxQtzrrcjxx> {

	public boolean deleteByMainId(String mainId);
    
	public List<CamsZcsxQtzrrcjxx> selectByMainId(String mainId);
}
