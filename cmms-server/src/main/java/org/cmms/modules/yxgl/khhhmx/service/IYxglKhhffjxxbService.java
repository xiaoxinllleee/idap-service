package org.cmms.modules.yxgl.khhhmx.service;

import org.cmms.modules.yxgl.khhhmx.entity.YxglKhhffjxxb;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 客户回访附件信息
 * @Author: cmms
 * @Date:   2019-12-23
 * @Version: V1.0
 */
public interface IYxglKhhffjxxbService extends IService<YxglKhhffjxxb> {

	public List<YxglKhhffjxxb> selectByMainId(String mainId);
}
