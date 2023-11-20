package org.cmms.modules.gzap.gzrz.service;

import org.cmms.modules.gzap.gzrz.entity.GzapRzglJrjhzj;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 今天计划总结
 * @Author: cmms
 * @Date:   2019-09-08
 * @Version: V1.0
 */
public interface IGzapRzglJrjhzjService extends IService<GzapRzglJrjhzj> {

	public List<GzapRzglJrjhzj> selectByMainId(String mainId);
}
