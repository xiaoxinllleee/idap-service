package org.cmms.modules.gzap.jhxf.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.gzap.jhxf.entity.jhxf_khjl;

/**
 * @Description: 计划下发_客户经理
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
public interface jhxf_khjlMapper extends BaseMapper<jhxf_khjl> {

	public boolean deleteByMainId(String mainId);

	public List<jhxf_khjl> selectByMainId(String mainId);
}
