package org.cmms.modules.gzap.rwxf.service;

import org.cmms.modules.gzap.rwxf.entity.GzapRwxf_Rwgl;
import org.cmms.modules.gzap.rwxf.entity.GzapRwxfJxz;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 工作日志
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
public interface IGzapRwxfJxzService extends IService<GzapRwxfJxz> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(GzapRwxfJxz gzapRwxfJxz, List<GzapRwxf_Rwgl> gzapRwxf_RwglList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(GzapRwxfJxz gzapRwxfJxz, List<GzapRwxf_Rwgl> gzapRwxf_RwglList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
