package org.cmms.modules.gzap.gzrz.service;

import org.cmms.modules.gzap.gzrz.entity.GzapRzglKhgh;
import org.cmms.modules.gzap.gzrz.entity.GzapRzglJrjhzj;
import org.cmms.modules.gzap.gzrz.entity.GzapRzgl;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 工作日志
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
public interface IGzapRzglService extends IService<GzapRzgl> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(GzapRzgl gzapRzgl, List<GzapRzglKhgh> gzapRzglKhghList, List<GzapRzglJrjhzj> gzapRzglJrjhzjList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(GzapRzgl gzapRzgl, List<GzapRzglKhgh> gzapRzglKhghList, List<GzapRzglJrjhzj> gzapRzglJrjhzjList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
