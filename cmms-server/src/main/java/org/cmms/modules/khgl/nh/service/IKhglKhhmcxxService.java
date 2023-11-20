package org.cmms.modules.khgl.nh.service;

import org.cmms.modules.khgl.nh.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface IKhglKhhmcxxService extends IService<KhglKhhmcxx> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(KhglKhhmcxx khglKhhmcxx, List<CamsZcsxNhcjxx> camsZcsxNhcjxxList, List<Nhfcxx> nhfcxxList, List<Ywhywwlxx> ywhywwlxxList, List<NhPjsxxx> nhPjsxxxList, List<Fjgl> fjglList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(KhglKhhmcxx khglKhhmcxx, List<CamsZcsxNhcjxx> camsZcsxNhcjxxList,List<Nhfcxx> nhfcxxList, List<Ywhywwlxx> ywhywwlxxList, List<NhPjsxxx> nhPjsxxxList, List<Fjgl> fjglList) ;
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
