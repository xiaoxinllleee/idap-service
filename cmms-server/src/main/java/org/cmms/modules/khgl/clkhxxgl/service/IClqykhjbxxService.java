package org.cmms.modules.khgl.clkhxxgl.service;

import org.cmms.modules.khgl.clkhxxgl.entity.Clkhzlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhywwlxx;
import org.cmms.modules.khgl.clkhxxgl.entity.Clqykhjbxx;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 存量企业客户基本信息
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface IClqykhjbxxService extends IService<Clqykhjbxx> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(Clqykhjbxx clqykhjbxx, List<Clkhzlxx> clqykhzlxxList, Clkhywwlxx clqykhywwlxx) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(Clqykhjbxx clqykhjbxx, List<Clkhzlxx> clkhzlxxList, Clkhywwlxx clqykhywwlxx);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
