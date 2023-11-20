package org.cmms.modules.yxgl.service;

import org.cmms.modules.yxgl.entity.Khhffjxx;
import org.cmms.modules.yxgl.entity.Khhf;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 客户回访
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
public interface IKhhfService extends IService<Khhf> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(Khhf khhf,List<Khhffjxx> khhffjxxList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(Khhf khhf,List<Khhffjxx> khhffjxxList);

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 * 根据证件号码获取回访记录
	 * @param zjhm
	 * @return
	 */
	public List<Khhf> selectByZjhm(String zjhm);

}
