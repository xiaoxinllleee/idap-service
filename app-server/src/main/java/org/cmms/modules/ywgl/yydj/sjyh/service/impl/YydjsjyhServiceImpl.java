package org.cmms.modules.ywgl.yydj.sjyh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.yydj.sjyh.entity.Yydjsjyh;
import org.cmms.modules.ywgl.yydj.sjyh.entity.YyjlVO;
import org.cmms.modules.ywgl.yydj.sjyh.mapper.YydjsjyhMapper;
import org.cmms.modules.ywgl.yydj.sjyh.service.IYydjsjyhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 手机银行
 * @Author: jeecg-boot
 * @Date:   2022-03-05
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class YydjsjyhServiceImpl extends ServiceImpl<YydjsjyhMapper, Yydjsjyh> implements IYydjsjyhService {
    @Override
    public List<YyjlVO> getList(Page page,  int sbzt, String username) {
        return baseMapper.getList(page, sbzt, username);
    }


   /* @Override
    public List<YyjlVO> getList(int start, int end, int sbzt) {
        return baseMapper.getList(start, end, sbzt);
    }*/
}
