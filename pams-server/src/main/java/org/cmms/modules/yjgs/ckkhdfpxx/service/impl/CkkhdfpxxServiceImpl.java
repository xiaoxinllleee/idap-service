package org.cmms.modules.yjgs.ckkhdfpxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.modules.yjgs.ckkhdfpxx.entity.Ckkhdfpxx;
import org.cmms.modules.yjgs.ckkhdfpxx.mapper.CkkhdfpxxMapper;
import org.cmms.modules.yjgs.ckkhdfpxx.service.ICkkhdfpxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: 待认领存款客户信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
@Service
public class CkkhdfpxxServiceImpl extends ServiceImpl<CkkhdfpxxMapper, Ckkhdfpxx> implements ICkkhdfpxxService {
    @Autowired
    private CkkhdfpxxMapper ckkhdfpxxMapper;

    @Override
    public void init(){
        ckkhdfpxxMapper.init();
    }

    @Override
    public List<Ckkhdfpxx> getListByIds(List<String> ids) {
        return ckkhdfpxxMapper.getListByIds(ids);
    }

    @Override
    public void deleteSptgsj(String tableId) {
        ckkhdfpxxMapper.deleteSptgsj(tableId);
    }
    @Override
    public void savaCkkhghxx(String id){
        //DS 没起效果
//        QueryWrapper<YjgsCkkhspxx> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",id);
//        List<YjgsCkkhspxx> yjgsCkkhspxxList = yjgsCkkhspxxService.list(queryWrapper);
//        for (YjgsCkkhspxx yjgsCkkhspxx : yjgsCkkhspxxList) {
//            //保存管户信息 DS 没起效果
//            YjgsCkkhghlsb yjgsCkkhghlsb = new YjgsCkkhghlsb();
//            yjgsCkkhghlsb.setJgdm(yjgsCkkhspxx.getJgdm());
//            yjgsCkkhghlsb.setYggh(yjgsCkkhspxx.getYggh());
//            yjgsCkkhghlsb.setKhbh(yjgsCkkhspxx.getKhbh());
//            yjgsCkkhghlsb.setYjbl(BigDecimal.valueOf(100));
//            yjgsCkkhghlsb.setKsrq(new Date());
//            yjgsCkkhghlsb.setSjly("2");
//            yjgsCkkhghlsb.setLrbz("1");
//            yjgsCkkhghlsbMapper.insert(yjgsCkkhghlsb);
//
//            //删除待分配的数据  DS 没起效果
//            UpdateWrapper<YjgsCkkhdfpxxb> updateWrapper = new UpdateWrapper<>();
//            updateWrapper.eq("khbh",yjgsCkkhspxx.getKhbh());
//            yjgsCkkhdfpxxbMapper.delete(updateWrapper);
//
//        }
    }
}
