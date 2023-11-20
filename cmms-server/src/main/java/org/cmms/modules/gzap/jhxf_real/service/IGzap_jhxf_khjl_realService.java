package org.cmms.modules.gzap.jhxf_real.service;

import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_khjl_real;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 客户明细
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface IGzap_jhxf_khjl_realService extends IService<Gzap_jhxf_khjl_real> {

	public List<Gzap_jhxf_khjl_real> selectByMainId(String mainId);
}
