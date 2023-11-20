package org.cmms.modules.gzap.jhxf_real.mapper;

import java.util.List;
import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_khjl_real;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface Gzap_jhxf_khjl_realMapper extends BaseMapper<Gzap_jhxf_khjl_real> {

	public boolean deleteByMainId(String mainId);

	public List<Gzap_jhxf_khjl_real> selectByMainId(String mainId);
}
