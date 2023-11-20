package org.cmms.modules.khgl.clkhxxgl.service;

import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjtcy;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjbxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 存量个人客户基本信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface IClgrkhjbxxService extends IService<Clgrkhjbxx> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(Clgrkhjbxx clgrkhjbxx, List<Clgrkhjtcy> clgrkhjtcyList, List<Clkhzlxx> clgrkhzlxxList, Clkhywwlxx clgrkhywwlxx) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(Clgrkhjbxx clgrkhjbxx, List<Clgrkhjtcy> clgrkhjtcyList, List<Clkhzlxx> clgrkhzlxxList, Clkhywwlxx clgrkhywwlxx);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	int syncYesClgrxx();

}
