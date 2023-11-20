package org.cmms.modules.yxgl.service;

import org.cmms.modules.yxgl.entity.Khhffjxx;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 客户回访附件信息
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
public interface IKhhffjxxService extends IService<Khhffjxx> {

	public List<Khhffjxx> selectByMainId(String mainId);
}
