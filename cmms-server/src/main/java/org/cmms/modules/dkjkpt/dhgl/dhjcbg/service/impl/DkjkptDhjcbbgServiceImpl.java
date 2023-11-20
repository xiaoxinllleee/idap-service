package org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.DkjkptDhjcbgfjxx;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VDkjkptDhjcbbg;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.mapper.DkjkptDhjcbgfjxxMapper;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.mapper.DkjkptDhjcbbgMapper;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.IdkjkptDhjcbbgService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@Service
public class DkjkptDhjcbbgServiceImpl extends ServiceImpl<DkjkptDhjcbbgMapper, VDkjkptDhjcbbg> implements IdkjkptDhjcbbgService {

	@Autowired
	private DkjkptDhjcbbgMapper dkjkptDhjcbbgMapper;
	@Autowired
	private DkjkptDhjcbgfjxxMapper dhjcbgfjxxMapper;

	@Value(value = "${common.path.upload}")
	private String uploadpath;

	@Override
	@Transactional
	public void saveMain(VDkjkptDhjcbbg v_dkjkptDhjcbbg, List<DkjkptDhjcbgfjxx> dkjkpt_dhjcbgfjxxList) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		for(DkjkptDhjcbgfjxx entity:dkjkpt_dhjcbgfjxxList) {
			//外键设置
			if(entity.getFjsjjd().equals("5")||entity.getFjsjjd().equals("6")){
				entity.setFjlx("2");
			}else if(entity.getFjsjjd().equals("1")||entity.getFjsjjd().equals("2")||entity.getFjsjjd().equals("3")||entity.getFjsjjd().equals("4")){
				entity.setFjlx("3");

			}else if((entity.getFjsjjd().equals("7"))){
				entity.setFjlx("1");
			}else{
				entity.setFjlx("4");
			}

			entity.setZjhm(v_dkjkptDhjcbbg.getZjhm());
			entity.setLrr(loginUser.getRealname());
			entity.setLrbz(1);
			entity.setLrsj(new Timestamp(System.currentTimeMillis()));
			if(!entity.getFwlj().equals("")&&entity.getFwlj()!=null){
				String fwlj = entity.getFwlj();
				String fjlj = uploadpath + File.separator + fwlj;
				File file = new File(fjlj);
				entity.setWjlj(fjlj);
				entity.setWjdx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
				dhjcbgfjxxMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(VDkjkptDhjcbbg v_dkjkptDhjcbbg, List<DkjkptDhjcbgfjxx> dkjkpt_dhjcbgfjxxList) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		//1.先删除子表数据
		dhjcbgfjxxMapper.deleteByMainId(v_dkjkptDhjcbbg.getZjhm());
		//2.子表数据重新插入
		for(DkjkptDhjcbgfjxx entity:dkjkpt_dhjcbgfjxxList) {
			//外键设置
			if(entity.getFjsjjd().equals("5")||entity.getFjsjjd().equals("6")){
				entity.setFjlx("2");
			}else if(entity.getFjsjjd().equals("1")||entity.getFjsjjd().equals("2")||entity.getFjsjjd().equals("3")||entity.getFjsjjd().equals("4")){
				entity.setFjlx("3");

			}else if((entity.getFjsjjd().equals("7"))){
				entity.setFjlx("1");
			}else{
				entity.setFjlx("4");
			}
			entity.setZjhm(v_dkjkptDhjcbbg.getZjhm());
			entity.setLrr(loginUser.getRealname());
			entity.setLrbz(1);
			entity.setLrsj(new Timestamp(System.currentTimeMillis()));
			entity.setZjhm(v_dkjkptDhjcbbg.getZjhm());
			if(!entity.getFwlj().equals("")&&entity.getFwlj()!=null){
				String fwlj = entity.getFwlj();
				String fjlj = uploadpath + File.separator + fwlj;
				File file = new File(fjlj);
				entity.setWjlj(fjlj);
				entity.setWjdx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
				dhjcbgfjxxMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		dhjcbgfjxxMapper.deleteByMainId(id);
		dkjkptDhjcbbgMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			dhjcbgfjxxMapper.deleteByMainId(id.toString());
			dkjkptDhjcbbgMapper.deleteById(id);
		}
	}

	@Override
	public List<VDkjkptDhjcbbg> selectByMainId(String mainId) {
		return dkjkptDhjcbbgMapper.selectByMainId(mainId);
	}
}
