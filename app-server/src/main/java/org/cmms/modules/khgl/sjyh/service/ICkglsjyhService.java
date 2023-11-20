package org.cmms.modules.khgl.sjyh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.sjyh.entity.Ckglsjyh;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.sjyh.entity.KhzlbVo;

import java.util.List;

/**
 * @Description: 客户管理_手机银行
 * @Author: jeecg-boot
 * @Date:   2022-03-16
 * @Version: V1.0
 */
public interface ICkglsjyhService extends IService<Ckglsjyh> {
    List<KhzlbVo> getList(int start, int end, int jx, int px, String ssmc);
    IPage<KhzlbVo> getPageList(Page page, int jx, int px, String ssmc);

}
