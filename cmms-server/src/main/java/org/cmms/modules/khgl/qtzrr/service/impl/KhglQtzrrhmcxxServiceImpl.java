package org.cmms.modules.khgl.qtzrr.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.mapper.YwhywwlxxMapper;
import org.cmms.modules.khgl.qtzrr.entity.*;
import org.cmms.modules.khgl.qtzrr.mapper.*;
import org.cmms.modules.khgl.qtzrr.service.IKhglQtzrrhmcxxService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.*;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Service
public class KhglQtzrrhmcxxServiceImpl extends ServiceImpl<KhglQtzrrhmcxxMapper, KhglQtzrrhmcxx> implements IKhglQtzrrhmcxxService {

	@Autowired
	private KhglQtzrrhmcxxMapper khglQtzrrhmcxxMapper;
	@Autowired
	private CamsZcsxQtzrrcjxxMapper camsZcsxQtzrrcjxxMapper;
	@Autowired
	private QtzrrfcxxMapper qtzrrfcxxMapper;
	@Autowired
	private YwhywwlxxMapper ywhywwlxxMapper;
	@Autowired
	private QtzrrPjsxxxMapper qtzrrPjsxxxMapper;
	@Autowired
	private QtzrrFjglMapper fjglMapper;
	@Autowired
	private ISysDictService iSysDictService;




	@Override
	@Transactional
	public void saveMain(KhglQtzrrhmcxx khglQtzrrhmcxx, List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList, List<Qtzrrfcxx> qtzrrfcxxList, List<Ywhywwlxx> ywhywwlxxList, List<QtzrrPjsxxx> qtzrrPjsxxxList, List<Fjgl> fjglList){
		String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");

		String id = UUIDGenerator.generate();
		khglQtzrrhmcxx.setId(id);
		khglQtzrrhmcxx.setKhlx("1");
		khglQtzrrhmcxxMapper.insert(khglQtzrrhmcxx);
		for(CamsZcsxQtzrrcjxx entity:camsZcsxQtzrrcjxxList) {
			//外键设置
			entity.setId(id);
			entity.setZjhm(khglQtzrrhmcxx.getZjhm());
			camsZcsxQtzrrcjxxMapper.insert(entity);
		}
		for (Ywhywwlxx ywhywwlxx : ywhywwlxxList) {
			ywhywwlxx.setHmcId(id);
			ywhywwlxx.setZjhm(khglQtzrrhmcxx.getZjhm());
			ywhywwlxxMapper.insert(ywhywwlxx);
		}
		for(Qtzrrfcxx entity: qtzrrfcxxList) {
			//外键设置
			entity.setZjhm(khglQtzrrhmcxx.getZjhm());
			entity.setQydm(qydm);
			qtzrrfcxxMapper.insert(entity);
		}
		for(QtzrrPjsxxx entity: qtzrrPjsxxxList) {
			//外键设置
			entity.setZjhm(khglQtzrrhmcxx.getZjhm());
			entity.setQydm(qydm);
			qtzrrPjsxxxMapper.insert(entity);
		}
		for(Fjgl entity:fjglList) {
			//外键设置
			entity.setZjhm(khglQtzrrhmcxx.getZjhm());
			entity.setQydm(qydm);
			fjglMapper.insert(entity);
		}
	}

	/**
	 * 修改一对多
	 *
	 */
	@Override
	@Transactional
	public void updateMain(KhglQtzrrhmcxx khglQtzrrhmcxx, List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList, List<Qtzrrfcxx> qtzrrfcxxList, List<Ywhywwlxx> ywhywwlxxList, List<QtzrrPjsxxx> qtzrrPjsxxxList, List<Fjgl> fjglList){
		UpdateWrapper<KhglQtzrrhmcxx> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("zjhm",khglQtzrrhmcxx.getZjhm());
		khglQtzrrhmcxxMapper.update(khglQtzrrhmcxx,updateWrapper);
		

		//2.修改子表数据
		for(CamsZcsxQtzrrcjxx entity:camsZcsxQtzrrcjxxList) {
			//外键设置
			/*CamsZcsxQtzrrcjxx  camsZcsxNhcjxx = new CamsZcsxQtzrrcjxx();
			BeanUtils.copyProperties(entity,camsZcsxNhcjxx);*/
			UpdateWrapper<CamsZcsxQtzrrcjxx> updateWrapper1 = new UpdateWrapper<>();
			updateWrapper1.eq("zjhm",khglQtzrrhmcxx.getZjhm());
			camsZcsxQtzrrcjxxMapper.update(entity,updateWrapper1);
		}
		for (Ywhywwlxx ywhywwlxx : ywhywwlxxList) {
			ywhywwlxx.setHmcId(khglQtzrrhmcxx.getId());
			UpdateWrapper<Ywhywwlxx> updateWrapper1 = new UpdateWrapper<>();
			updateWrapper1.eq("zjhm",khglQtzrrhmcxx.getZjhm()).eq("hmc_id",khglQtzrrhmcxx.getId());
			ywhywwlxxMapper.update(ywhywwlxx,updateWrapper1);

		}

		//1.先删除子表数据
		qtzrrfcxxMapper.deleteByMainId(khglQtzrrhmcxx.getZjhm());
		//2.子表数据重新插入
		for(Qtzrrfcxx entity:qtzrrfcxxList) {
			//外键设置
			entity.setZjhm(khglQtzrrhmcxx.getZjhm());
			qtzrrfcxxMapper.insert(entity);
		}


		for(QtzrrPjsxxx entity:qtzrrPjsxxxList) {
			//外键设置
			entity.setZjhm(khglQtzrrhmcxx.getZjhm());
			UpdateWrapper<QtzrrPjsxxx> updateWrapper1 = new UpdateWrapper<>();
			updateWrapper1.eq("zjhm",khglQtzrrhmcxx.getZjhm());
			qtzrrPjsxxxMapper.update(entity,updateWrapper1);
		}
		for(Fjgl entity:fjglList) {
			//外键设置
			entity.setZjhm(khglQtzrrhmcxx.getZjhm());
			fjglMapper.insert(entity);
		}

	}

	@Override
	@Transactional
	public void delMain(String zjhm) {
		khglQtzrrhmcxxMapper.deleteByMainId(zjhm);
		camsZcsxQtzrrcjxxMapper.deleteByMainId(zjhm);
		qtzrrfcxxMapper.deleteByMainId(zjhm);
		/*KhglQtzrrhmcxx khhmcxx = new KhglQtzrrhmcxx();
		khhmcxx.setZjhm(zjhm);
		Map<String,String[]> map = new HashMap<>();
		QueryWrapper<KhglQtzrrhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(khhmcxx,map);
		khglKhhmcxxMapper*/

		ywhywwlxxMapper.deleteByMainId(zjhm);
		qtzrrPjsxxxMapper.deleteByMainId(zjhm);
		fjglMapper.deleteByMainId(zjhm);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			camsZcsxQtzrrcjxxMapper.deleteByMainId(id.toString());
			qtzrrfcxxMapper.deleteByMainId(id.toString());
			ywhywwlxxMapper.deleteByMainId(id.toString());
			qtzrrPjsxxxMapper.deleteByMainId(id.toString());
			fjglMapper.deleteByMainId(id.toString());
		}
	}
	
}
