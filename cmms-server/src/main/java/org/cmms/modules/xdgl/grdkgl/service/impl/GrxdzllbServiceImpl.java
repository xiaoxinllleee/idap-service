package org.cmms.modules.xdgl.grdkgl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.xdgl.grdkgl.entity.Grxdzllb;
import org.cmms.modules.xdgl.grdkgl.entity.Jtcyxx;
import org.cmms.modules.xdgl.grdkgl.mapper.GrxdzllbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IGrxdzllbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.File;
import java.math.BigDecimal;

/**
 * @Description: 个人信贷资料列表
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Service
public class GrxdzllbServiceImpl extends ServiceImpl<GrxdzllbMapper, Grxdzllb> implements IGrxdzllbService {
    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Autowired
    private GrxdzllbMapper grxdzllbMapper;

    public void savefjxxMain(JSONObject imgdate,String sjmc,Jtcyxx hmcxx,String zllx) {
        grxdzllbMapper.deleteimg(hmcxx.getZjhm(),zllx);
        Grxdzllb grxdzllb = new Grxdzllb();
        JSONArray jsonArray = imgdate.getJSONObject("imgdate").getJSONArray(sjmc);
        if (imgdate.getJSONObject("imgdate").getJSONArray(sjmc) != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                String id = UUIDGenerator.generate();
                if (jsonArray.getJSONObject(i).getJSONObject("response") != null) {
                    grxdzllb.setQydm(hmcxx.getSsyxdy());
                    grxdzllb.setHhbm(hmcxx.getHhbm());
                    grxdzllb.setZjhm(hmcxx.getZjhm());
                    grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
                    String fwlj = jsonArray.getJSONObject(i).getJSONObject("response").getString("message");
                    grxdzllb.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + jsonArray.getJSONObject(i).getJSONObject("response").getString("message");
                    grxdzllb.setZllj(zjlj);
                    grxdzllb.setZllx(zllx);
                    grxdzllb.setZlbh(id);
                    grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    grxdzllb.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    grxdzllb.setScr(loginUser.getUsername());
                    grxdzllb.setLrr(loginUser.getUsername());
                    grxdzllb.setScsj(DateUtils.getDate());
                    grxdzllbMapper.insert(grxdzllb);
                } else {
                    grxdzllb.setQydm(hmcxx.getSsyxdy());
                    grxdzllb.setHhbm(hmcxx.getHhbm());
                    grxdzllb.setZjhm(hmcxx.getZjhm());
                    grxdzllb.setFwlj(jsonArray.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + jsonArray.getJSONObject(i).getString("url");
                    grxdzllb.setZllj(zllj);
                    grxdzllb.setZllx(zllx);
                    grxdzllb.setZlbh(id);
                    grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    grxdzllb.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    grxdzllb.setScr(loginUser.getUsername());
                    grxdzllb.setLrr(loginUser.getUsername());
                    grxdzllb.setScsj(DateUtils.getDate());
                    grxdzllbMapper.insert(grxdzllb);
                }
            }
        }
    }

    public void updatefjxxMain(JSONObject imgdate,String sjmc,Jtcyxx hmcxx,String zllx) {
        grxdzllbMapper.deleteimg(hmcxx.getZjhm(),zllx);
        Grxdzllb grxdzllb = new Grxdzllb();
        JSONArray jsonArray = imgdate.getJSONObject("imgdate").getJSONArray(sjmc);
        if (imgdate.getJSONObject("imgdate").getJSONArray(sjmc) != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                String id = UUIDGenerator.generate();
                if (jsonArray.getJSONObject(i).getJSONObject("response") != null) {
                    grxdzllb.setQydm(hmcxx.getSsyxdy());
                    grxdzllb.setHhbm(hmcxx.getHhbm());
                    grxdzllb.setZjhm(hmcxx.getZjhm());
                    grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
                    String fwlj = jsonArray.getJSONObject(i).getJSONObject("response").getString("message");
                    grxdzllb.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + jsonArray.getJSONObject(i).getJSONObject("response").getString("message");
                    grxdzllb.setZllj(zjlj);
                    grxdzllb.setZllx(zllx);
                    grxdzllb.setZlbh(id);
                    grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    grxdzllb.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    grxdzllb.setScr(loginUser.getUsername());
                    grxdzllb.setLrr(loginUser.getUsername());
                    grxdzllb.setScsj(DateUtils.getDate());
                    grxdzllbMapper.insert(grxdzllb);
                } else {
                    grxdzllb.setQydm(hmcxx.getSsyxdy());
                    grxdzllb.setHhbm(hmcxx.getHhbm());
                    grxdzllb.setZjhm(hmcxx.getZjhm());
                    grxdzllb.setFwlj(jsonArray.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + jsonArray.getJSONObject(i).getString("url");
                    grxdzllb.setZllj(zllj);
                    grxdzllb.setZllx(zllx);
                    grxdzllb.setZlbh(id);
                    grxdzllb.setZlmc(jsonArray.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    grxdzllb.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    grxdzllb.setScr(loginUser.getUsername());
                    grxdzllb.setLrr(loginUser.getUsername());
                    grxdzllb.setScsj(DateUtils.getDate());
                    grxdzllbMapper.insert(grxdzllb);
                }
            }
        }
    }

}
