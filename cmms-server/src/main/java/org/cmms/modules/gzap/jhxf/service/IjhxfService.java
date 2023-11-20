package org.cmms.modules.gzap.jhxf.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.gzap.jhxf.entity.jhxf;
import org.cmms.modules.gzap.jhxf.entity.jhxf_khjl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 计划下发
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
public interface IjhxfService extends IService<jhxf> {

	/**
	 * 添加一对多
	 *
	 */
	public void saveMain(jhxf jhxf, List<jhxf_khjl> jhxf_khjlList) ;

	/**
	 * 修改一对多
	 *
	 */
	public void updateMain(jhxf jhxf, List<jhxf_khjl> jhxf_khjlList);

	/**
	 * 删除一对多
	 */
	public void delMain(String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

}
