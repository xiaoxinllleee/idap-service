package org.cmms.modules.gzap.jhxf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.gzap.jhxf.entity.jhxf_khjl;

import java.util.List;

/**
 * @Description: 计划下发_客户经理
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
public interface Ijhxf_khjlService extends IService<jhxf_khjl> {

	public List<jhxf_khjl> selectByMainId(String mainId);
}
