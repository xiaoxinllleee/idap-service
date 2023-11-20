package org.cmms.modules.xddagl.dkdagl.dhjczllr.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.entity.Dhjcbgfjxx;

import java.util.List;

/**
 * @Description: 附件信息
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
@DS("eweb")
public interface IDhjcbgfjxxService extends IService<Dhjcbgfjxx> {

	public List<Dhjcbgfjxx> selectByMainId(String mainId);
}
