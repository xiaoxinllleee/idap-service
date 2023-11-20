package org.cmms.modules.jtxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.base.entity.Dict;
import org.cmms.modules.jtxx.entity.Jtxx;
import org.cmms.modules.jtxx.entity.JtxxList;
import org.cmms.modules.jtxx.mapper.JtxxMapper;
import org.cmms.modules.jtxx.service.IJtxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @Description: 家庭信息
 * @Author: jeecg-boot
 * @Date: 2020-10-16
 * @Version: V1.0
 */
@Service
public class JtxxServiceImpl extends ServiceImpl<JtxxMapper, Jtxx> implements IJtxxService {

    @Autowired
    private JtxxMapper jtxxMapper;

    @Override
    public List<Jtxx> queryJtxx(String hhbm, String id) {
        return jtxxMapper.queryJtxx(hhbm, id);
    }

    @Override
    public Integer insertJtxxList(List<Jtxx> jtxxList, String hhbm) {

        for (Jtxx jtxx : jtxxList) {
            jtxx.setHhbm(hhbm);
            jtxx.setHyzt(jtxx.getHyzt());
            jtxx.setZt(jtxx.getZt());
            jtxx.setSex(jtxx.getSex());
            jtxx.setName(jtxx.getName());
            jtxx.setZjhm(jtxx.getZjhm());
            jtxx.setYhzgx(jtxx.getYhzgx());
            jtxx.setLxfs(jtxx.getLxfs());
            jtxxMapper.insert(jtxx);
        }
        return 1;
    }

    @Override
    public Dict queryDict(String dictCode) {
        return jtxxMapper.queryDict(dictCode);
    }

    @Override
    public Integer deleteJtcyxx(String hhbm, String id) {
        return jtxxMapper.deleteJtcyxx(hhbm, id);
    }

    @Override
    public Integer updateJtxx(JtxxList jtxxList) {
        //修改基本信息
        jtxxMapper.updateById(jtxxList.getJtxx());
        //修改家庭成员户号编码为空
        if (jtxxList.getIds() != null) {
            for (String id : jtxxList.getIds()) {
                jtxxMapper.updateJtcyxx(id);
            }
        }
        //循环家庭成员列表
        for (Jtxx jtxx : jtxxList.getJtxxList()) {
            Jtxx j = jtxxMapper.selectByZjhm(jtxx.getZjhm());
            if (j == null) {
                jtxx.setHhbm(jtxxList.getJtxx().getHhbm());
                jtxx.setHyzt(jtxx.getHyzt());
                jtxx.setZt(jtxx.getZt());
                jtxx.setSex(jtxx.getSex());
                jtxx.setName(jtxx.getName());
                jtxx.setZjhm(jtxx.getZjhm());
                jtxx.setYhzgx(jtxx.getYhzgx());
                jtxx.setLxfs(jtxx.getLxfs());
                jtxxMapper.insert(jtxx);
            } else {
                QueryWrapper<Jtxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm", jtxx.getZjhm());
                jtxxMapper.update(jtxx, queryWrapper);
            }
        }
        return 1;

//        List<Jtxx> list = jtxxMapper.selectByHhbm(jtxxList.getJtxx().getHhbm(), jtxxList.getJtxx().getId());
//
//        System.out.println("list---" + list);
//        System.out.println("jtxxList---" + jtxxList.getJtxxList());
//        List<Jtxx> aAndB = new ArrayList<>();
//        Jtxx jtcyxx = null;
//        for (Jtxx bb : list) {//A,B
//            for (Jtxx aa : jtxxList.getJtxxList()) {//A
//                if (bb.equals(aa)) {
//                    jtcyxx = aa;
//                }
//            }
//            System.out.println(jtcyxx+"--------jtcyxx");
//            if (!jtcyxx.equals(bb)) {
//                aAndB.add(bb);
//            }
//        }
//        System.out.println("aAndB---" + aAndB);
        //修改被删除的家庭成员信息设置户号编码为空
//        for (Jtxx jtxx : aAndB) {
//            System.out.println(jtxx.getId()+"------------------Id");
//            jtxxMapper.updateJtcyxx(jtxx.getId());
//        }
//

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateHhbm(String id, String sign) {
        if (sign.equals("0")) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return jtxxMapper.updateJtcyxx(id);


    }

    @Override
    public Jtxx selectByZjhm(String zjhm) {
        return jtxxMapper.selectByZjhm(zjhm);
    }

    @Override
    public Jtxx getName(String zjhm) {
        return jtxxMapper.getName(zjhm);
    }

    @Override
    public Integer deleteByzjhm(String zjhm) {
        return jtxxMapper.deleteByzjhm(zjhm);
    }

    @Override
    @Transactional
    public Integer deleteById(String id) {
        return jtxxMapper.myDeleteById(id);
    }
}
