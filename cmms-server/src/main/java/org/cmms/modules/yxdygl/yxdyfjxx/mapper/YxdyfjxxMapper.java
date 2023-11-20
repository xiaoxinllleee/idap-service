package org.cmms.modules.yxdygl.yxdyfjxx.mapper;

import java.util.List;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 营销单元附件信息
 * @Author: jeecg-boot
 * @Date:   2020-07-28
 * @Version: V1.0
 */
public interface YxdyfjxxMapper extends BaseMapper<Yxdyfjxx> {

	public boolean deleteByMainId(String mainId);
    
	public List<Yxdyfjxx> selectByMainId(String mainId);
}
