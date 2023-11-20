package org.cmms.modules.dkjkpt.dhgl.dhjcbg.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.DkjkptDhjcbgfjxx;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VDkjkptDhjcbbg;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IdkjkptDhjcbbgService extends IService<VDkjkptDhjcbbg> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(VDkjkptDhjcbbg v_dkjkptDhjcbbg, List<DkjkptDhjcbgfjxx> dkjkpt_dhjcbgfjxxList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(VDkjkptDhjcbbg v_dkjkptDhjcbbg, List<DkjkptDhjcbgfjxx> dkjkpt_dhjcbgfjxxList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);


	public List<VDkjkptDhjcbbg> selectByMainId(String mainId);

}
