package org.cmms.modules.yxgl.khhhmx.service;

import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhffjxxb;
import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhfmxb;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 客户回访明细
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
public interface IYxglKhhfmxbService extends IService<YxglKhhfmxb> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(YxglKhhfmxb yxglKhhfmxb, List<YxglKhhffjxxb> yxglKhhffjxxbList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(YxglKhhfmxb yxglKhhfmxb, List<YxglKhhffjxxb> yxglKhhffjxxbList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
