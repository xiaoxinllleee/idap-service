package org.cmms.modules.gzap.jhxf_real.service;

import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_khjl_real;
import org.cmms.modules.gzap.jhxf_real.entity.Gzap_jhxf_real;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface IGzap_jhxf_realService extends IService<Gzap_jhxf_real> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(Gzap_jhxf_real gzap_jhxf_real, List<Gzap_jhxf_khjl_real> gzap_jhxf_khjl_realList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(Gzap_jhxf_real gzap_jhxf_real, List<Gzap_jhxf_khjl_real> gzap_jhxf_khjl_realList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
