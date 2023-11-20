package org.cmms.modules.khgl.qtzrr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.qtzrr.entity.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 其他自然人花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface IKhglQtzrrhmcxxService extends IService<KhglQtzrrhmcxx> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(KhglQtzrrhmcxx khglKhhmcxx, List<CamsZcsxQtzrrcjxx> camsZcsxNhcjxxList, List<Qtzrrfcxx> nhfcxxList, List<Ywhywwlxx> ywhywwlxxList, List<QtzrrPjsxxx> nhPjsxxxList, List<Fjgl> fjglList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(KhglQtzrrhmcxx khglKhhmcxx, List<CamsZcsxQtzrrcjxx> camsZcsxNhcjxxList, List<Qtzrrfcxx> nhfcxxList, List<Ywhywwlxx> ywhywwlxxList, List<QtzrrPjsxxx> nhPjsxxxList, List<Fjgl> fjglList) ;
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
