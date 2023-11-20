package org.cmms.modules.ywgl.yydj.sjyh.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.yydj.sjyh.entity.Yydjsjyh;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.yydj.sjyh.entity.YyjlVO;

import java.util.List;

/**
 * @Description: 手机银行
 * @Author: jeecg-boot
 * @Date:   2022-03-05
 * @Version: V1.0
 */
public interface IYydjsjyhService extends IService<Yydjsjyh> {

//    List<YyjlVO> getList(int start,  int end, int sbzt);
    List<YyjlVO> getList(Page page, int sbzt,String username);

}
