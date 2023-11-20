package org.cmms.modules.khgl.grkhgl.service.impl;

import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.dklldj.lldjgl.glzhgl.mapper.CbsInvmBaseMapper;
import org.cmms.modules.khgl.grkhgl.entity.*;
import org.cmms.modules.khgl.grkhgl.mapper.*;
import org.cmms.modules.khgl.grkhgl.service.IVKhglGrkhglService;
import org.cmms.modules.khgl.grkhgl.service.IZcsxNhcjxxService;
import org.cmms.modules.khgl.grkhgl.service.IvKhglKrkhglService;
import org.cmms.modules.khgl.nh.entity.CamsZcsxNhcjxx;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.mapper.CamsZcsxNhcjxxMapper;
import org.cmms.modules.khgl.nh.mapper.FjglMapper;
import org.cmms.modules.system.service.impl.SysDictServiceImpl;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.misc.BASE64Decoder;
import org.apache.commons.codec.binary.Base64;

/**
 * @Description: 个人客户
 * @Author: jeecg-boot
 * @Date:   2020-07-03
 * @Version: V1.0
 */
@Slf4j
@Service
public class vKhglKrkhglServiceImpl extends ServiceImpl<vKhglKrkhglMapper, vKhglKrkhgl> implements IvKhglKrkhglService {
    @Autowired
    CamsZcsxGrpjsxxxMapper camsZcsxGrpjsxxxMapper;
    @Autowired
    KhhmcxxMapper khhmcxxMapper;
    @Autowired
    ZcsxNhcjxxMapper zcsxNhcjxxMapper;
    @Autowired
    SysDictServiceImpl sysDictService;
    @Autowired
    FjglMapper fjglMapper;
    @Autowired
    CbsInvmBaseMapper cbsInvmBaseMapper;
    @Autowired
    CamsZcsxGrpjsxxxBcMapper camsZcsxGrpjsxxxBcMapper;
    @Autowired
    private ITjfxZhbyService tjfxZhbyService;
/*    @Autowired
    private vKhglKrkhglMapper  vKhglGrkhglMapper;
    @Autowired
    private IvKhglKrkhglService ivKhglKrkhglService;
    @Autowired
    private IVKhglGrkhglService iVKhglGrkhglService;*/

    @Value(value = "${common.path.upload}")
    private String uploadpath;
    private final String PATTERN = "^[0-9]*$";

    /**
     * 添加一对多
     *
     */
    @Override
    @Transactional
    public void saveMain(ZcsxNhcjxx zcsxNhcjxx, KrkhglReceive krkhglReceive, List<Khhmcxx>khhmcxxList, List<CamsZcsxGrpjsxxx>camsZcsxGrpjsxxxList){
        zcsxNhcjxx.setSjhm(krkhglReceive.getLxfs());
        zcsxNhcjxx.setKhlx1(krkhglReceive.getKhlx());
        zcsxNhcjxx.setCshygz(krkhglReceive.getCszy());
        zcsxNhcjxx.setLrsj(new Date());
        zcsxNhcjxxMapper.insert(zcsxNhcjxx);

        //保存花名册信息
        String zzbz = krkhglReceive.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzbz","ywjgdm",krkhglReceive.getSszh());
        Khhmcxx khhmcxx1 = new Khhmcxx();
        BeanUtils.copyProperties(krkhglReceive,khhmcxx1);
        khhmcxx1.setSszh(zzbz);
        khhmcxx1.setJgdm(krkhglReceive.getSszh());
        khhmcxx1.setDz(krkhglReceive.getZz());
        if (krkhglReceive.getHhbm()==null||krkhglReceive.getHhbm()==""){
            khhmcxx1.setHhbm(sysDictService.queryhhbm("SEQ_HHBM_LY.nextval"));
        }
        Khhmcxx khhmcxx2= khhmcxxMapper.selectByMainId(khhmcxx1.getZjhm());
        if(khhmcxx2==null){
            khhmcxxMapper.insert(khhmcxx1);
        }else{
            QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm",khhmcxx1.getZjhm());
            khhmcxxMapper.update(khhmcxx1,queryWrapper);
        }

        //插入家庭成员信息
        for (Khhmcxx khhmcxx : khhmcxxList) {
            khhmcxx.setHhbm(khhmcxx1.getHhbm());
            khhmcxx.setSsyxdy(krkhglReceive.getSsyxdy());
            khhmcxx.setJgdm(krkhglReceive.getSszh());
            khhmcxx.setSszh(zzbz);
            khhmcxx.setKhlx(krkhglReceive.getKhlx());
            Khhmcxx khhmcxx3= khhmcxxMapper.selectByMainId(khhmcxx.getZjhm());
            if(khhmcxx3==null){
                khhmcxxMapper.insert(khhmcxx);
            }else {
                QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm",khhmcxx.getZjhm());
                khhmcxxMapper.update(khhmcxx,queryWrapper);
            }
        }
        //新增评级授信信息
        for (CamsZcsxGrpjsxxx camsZcsxGrpjsxxx : camsZcsxGrpjsxxxList) {
            CamsZcsxGrpjsxxx grpjsxxx = camsZcsxGrpjsxxxMapper.selectByMainId(krkhglReceive.getHhbm());
            QueryWrapper<CamsZcsxGrpjsxxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("hhbm",camsZcsxGrpjsxxx.getHhbm());
            if(grpjsxxx!=null){
                if(!camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())&&krkhglReceive.getSfsxdx().equals("1")){
                    camsZcsxGrpjsxxx.setZjhm(krkhglReceive.getZjhm());
                    camsZcsxGrpjsxxx.setKhmc(krkhglReceive.getKhmc());
                }else if(!krkhglReceive.getSfsxdx().equals("1")&&camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())){
                    camsZcsxGrpjsxxx.setZjhm("");
                    camsZcsxGrpjsxxx.setKhmc("");
                }else{
                    camsZcsxGrpjsxxx.setZjhm(grpjsxxx.getZjhm());
                    camsZcsxGrpjsxxx.setKhmc(grpjsxxx.getKhmc());
                    camsZcsxGrpjsxxx.setQydm(grpjsxxx.getQydm());
                }
                camsZcsxGrpjsxxxMapper.update(camsZcsxGrpjsxxx, queryWrapper);
            }else{
                if(!krkhglReceive.getSfsxdx().equals("1")){
                    camsZcsxGrpjsxxx.setZjhm("");
                    camsZcsxGrpjsxxx.setKhmc("");
                }
                camsZcsxGrpjsxxx.setHhbm(khhmcxx1.getHhbm());
                camsZcsxGrpjsxxx.setQydm(krkhglReceive.getSsyxdy());
                camsZcsxGrpjsxxxMapper.insert(camsZcsxGrpjsxxx);
            }
        }
        //新增附件信息
        Fjgl fjgl = new Fjgl();
        //本人身份证附件
        JSONArray brsfzfjglList= krkhglReceive.getImgdate().getJSONArray("brsfzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"1");
        if(krkhglReceive.getImgdate().getJSONArray("brsfzfileList")!=null) {
            for (int i = 0; i < brsfzfjglList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (brsfzfjglList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = brsfzfjglList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + brsfzfjglList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("1");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(brsfzfjglList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                    System.out.println(brsfzfjglList.getJSONObject(i).getString("name"));
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(brsfzfjglList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + brsfzfjglList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("1");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(brsfzfjglList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }

        //配偶身份证附件
        JSONArray posfzfileList = krkhglReceive.getImgdate().getJSONArray("posfzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"2");
        if (krkhglReceive.getImgdate().getJSONArray("posfzfileList")!=null) {
            for (int i = 0; i < posfzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (posfzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = posfzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + posfzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("2");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(posfzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                    System.out.println(posfzfileList.getJSONObject(i).getString("name"));
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(posfzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + posfzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("2");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(posfzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }

        //结婚证附件
        JSONArray jhzfileList = krkhglReceive.getImgdate().getJSONArray("jhzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"3");
        if(krkhglReceive.getImgdate().getJSONArray("jhzfileList")!=null) {
            for (int i = 0; i < jhzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (jhzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = jhzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + jhzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("3");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(jhzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(jhzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + jhzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("3");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(jhzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //人像拍照附件
        JSONArray rxzpfileList = krkhglReceive.getImgdate().getJSONArray("rxzpfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"4");
        if(krkhglReceive.getImgdate().getJSONArray("rxzpfileList")!=null) {
            for (int i = 0; i < rxzpfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (rxzpfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =rxzpfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + rxzpfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("4");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(rxzpfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(rxzpfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + rxzpfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("4");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(rxzpfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //房屋拍照
        JSONArray fwpzfileList = krkhglReceive.getImgdate().getJSONArray("fwpzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"5");
        if(krkhglReceive.getImgdate().getJSONArray("fwpzfileList")!=null) {
            for (int i = 0; i < fwpzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (fwpzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =  fwpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + fwpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("5");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(fwpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(fwpzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + fwpzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("5");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(fwpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //权重拍照
        JSONArray qzpzfileList = krkhglReceive.getImgdate().getJSONArray("qzpzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"6");
        if(krkhglReceive.getImgdate().getJSONArray("qzpzfileList")!=null) {
            for (int i = 0; i < qzpzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (qzpzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = qzpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + qzpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("6");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qzpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(qzpzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + qzpzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("6");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qzpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //汽车照片
        JSONArray qczpfileList = krkhglReceive.getImgdate().getJSONArray("qczpfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"7");
        if(krkhglReceive.getImgdate().getJSONArray("qczpfileList")!=null) {
            for (int i = 0; i < qczpfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (qczpfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =  qczpfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + qczpfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("7");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qczpfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(qczpfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + qczpfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("7");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qczpfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //其他资产
        JSONArray qtzcfileList = krkhglReceive.getImgdate().getJSONArray("qtzcfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"8");
        if(krkhglReceive.getImgdate().getJSONArray("qtzcfileList")!=null) {
            for (int i = 0; i < qtzcfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (qtzcfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = qtzcfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + qtzcfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("8");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qtzcfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(qtzcfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + qtzcfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("8");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qtzcfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //经营场所、场地(果园、鱼塘等
        JSONArray jycsfileList = krkhglReceive.getImgdate().getJSONArray("jycsfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"9");
        if(krkhglReceive.getImgdate().getJSONArray("jycsfileList")!=null) {
            for (int i = 0; i < jycsfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (jycsfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =  jycsfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + jycsfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("9");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(jycsfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(jycsfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + jycsfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("9");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(jycsfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //采集者--采集者拍照
        JSONArray cjzpzfileList = krkhglReceive.getImgdate().getJSONArray("cjzpzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"10");
        if(krkhglReceive.getImgdate().getJSONArray("cjzpzfileList")!=null) {
            for (int i = 0; i < cjzpzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (cjzpzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = cjzpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + cjzpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("10");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(cjzpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(khhmcxx1.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(cjzpzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + cjzpzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("10");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(cjzpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
    }


    /**
     * 添加一对多
     *
     */
    @Override
    @Transactional
    public void saveMainPad(ZcsxNhcjxx zcsxNhcjxx, KrkhglReceive krkhglReceive, List<Khhmcxx>khhmcxxList, List<CamsZcsxGrpjsxxx>camsZcsxGrpjsxxxList){
        zcsxNhcjxx.setSjhm(krkhglReceive.getLxfs());
        zcsxNhcjxx.setKhlx1(krkhglReceive.getKhlx());

        zcsxNhcjxx.setCshygz(krkhglReceive.getCszy());
        zcsxNhcjxx.setLrsj(new Date());
        try {
            if(zcsxNhcjxx.getSign1()!=null&&zcsxNhcjxx.getSign1()!=""){
                zcsxNhcjxx.setSign1(saveImg(zcsxNhcjxx.getSign1()));
            }
            if(zcsxNhcjxx.getSign2()!=null&&zcsxNhcjxx.getSign2()!=""){
                zcsxNhcjxx.setSign2(saveImg(zcsxNhcjxx.getSign2()));
            }

            if(zcsxNhcjxx.getSign3()!=null&&zcsxNhcjxx.getSign3()!=""){
                zcsxNhcjxx.setSign3(saveImg(zcsxNhcjxx.getSign3()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String khjl = krkhglReceive.getSskhjl()== null ? loginUser.getWorkNo() : krkhglReceive.getSskhjl();
        zcsxNhcjxx.setSskhjl(khjl);
        zcsxNhcjxx.setLrr(khjl);
        zcsxNhcjxxMapper.insert(zcsxNhcjxx);
        //保存花名册信息
        String ywjgdm = krkhglReceive.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","ywjgdm","zzbz",krkhglReceive.getSszh());
        Khhmcxx khhmcxx1 = new Khhmcxx();
        BeanUtils.copyProperties(krkhglReceive,khhmcxx1);
        khhmcxx1.setSszh(krkhglReceive.getSszh());
        khhmcxx1.setJgdm(ywjgdm);
        khhmcxx1.setDz(krkhglReceive.getZz());
        khhmcxx1.setLrr(khjl);
        khhmcxx1.setLrsj(new Date());
        khhmcxx1.setXgsj(new Date());
        khhmcxx1.setXgr(khjl);
        if (krkhglReceive.getHhbm()==null||krkhglReceive.getHhbm()==""){
            khhmcxx1.setHhbm(sysDictService.queryhhbm("SEQ_HHBM_LY.nextval"));
        }
        Khhmcxx khhmcxx2= khhmcxxMapper.selectByMainId(khhmcxx1.getZjhm());
        if(khhmcxx2==null){
            khhmcxxMapper.insert(khhmcxx1);
        }else{
            QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm",khhmcxx1.getZjhm());
            khhmcxxMapper.update(khhmcxx1,queryWrapper);
        }

        //插入家庭成员信息
        for (Khhmcxx khhmcxx : khhmcxxList) {
            khhmcxx.setHhbm(khhmcxx1.getHhbm());
            khhmcxx.setSsyxdy(krkhglReceive.getSsyxdy());
            khhmcxx.setJgdm(ywjgdm);
            khhmcxx.setSszh(krkhglReceive.getSszh());
            khhmcxx.setKhlx(krkhglReceive.getKhlx());
            Khhmcxx khhmcxx3= khhmcxxMapper.selectByMainId(khhmcxx.getZjhm());
            if(khhmcxx3==null){
                khhmcxxMapper.insert(khhmcxx);
            }else {
                QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm",khhmcxx.getZjhm());
                khhmcxxMapper.update(khhmcxx,queryWrapper);
            }
        }
        //新增评级授信信息
        for (CamsZcsxGrpjsxxx camsZcsxGrpjsxxx : camsZcsxGrpjsxxxList) {
            CamsZcsxGrpjsxxx grpjsxxx = camsZcsxGrpjsxxxMapper.selectByMainId(krkhglReceive.getHhbm());
            QueryWrapper<CamsZcsxGrpjsxxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("hhbm",camsZcsxGrpjsxxx.getHhbm());
            if(grpjsxxx!=null){
                if(!camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())&&krkhglReceive.getSfsxdx().equals("1")){
                    camsZcsxGrpjsxxx.setZjhm(krkhglReceive.getZjhm());
                    camsZcsxGrpjsxxx.setKhmc(krkhglReceive.getKhmc());
                }else if(!krkhglReceive.getSfsxdx().equals("1")&&camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())){
                    camsZcsxGrpjsxxx.setZjhm("");
                    camsZcsxGrpjsxxx.setKhmc("");
                }else{
                    camsZcsxGrpjsxxx.setZjhm(grpjsxxx.getZjhm());
                    camsZcsxGrpjsxxx.setKhmc(grpjsxxx.getKhmc());
                    camsZcsxGrpjsxxx.setQydm(grpjsxxx.getQydm());
                }
                camsZcsxGrpjsxxxMapper.update(camsZcsxGrpjsxxx, queryWrapper);
            }else{
                if(!krkhglReceive.getSfsxdx().equals("1")){
                    camsZcsxGrpjsxxx.setZjhm("");
                    camsZcsxGrpjsxxx.setKhmc("");
                }
                camsZcsxGrpjsxxx.setHhbm(khhmcxx1.getHhbm());
                camsZcsxGrpjsxxx.setQydm(krkhglReceive.getSsyxdy());
                camsZcsxGrpjsxxxMapper.insert(camsZcsxGrpjsxxx);
            }
//            try {
//                ivKhglKrkhglService.prepare(zcsxNhcjxx.getZjhm(), khhmcxx1.getHhbm());
//                iVKhglGrkhglService.calculateModel(khhmcxx1.getHhbm(), zcsxNhcjxx.getZjhm());
//            }catch (Exception e){
//                log.error("模型计算失败！"+e.getMessage());
//            }
        }
    }

    /**
     * 修改一对多
     *
     */
    @Override
    @Transactional
    public void updateMain(ZcsxNhcjxx zcsxNhcjxx, KrkhglReceive krkhglReceive, List<Khhmcxx>khhmcxxList, List<CamsZcsxGrpjsxxx>camsZcsxGrpjsxxxList){
        zcsxNhcjxx.setSjhm(krkhglReceive.getLxfs());
        zcsxNhcjxx.setKhlx1(krkhglReceive.getKhlx());
        zcsxNhcjxx.setCshygz(krkhglReceive.getCszy());
        UpdateWrapper<ZcsxNhcjxx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("zjhm",zcsxNhcjxx.getZjhm());
        zcsxNhcjxxMapper.update(zcsxNhcjxx,updateWrapper);

        //保存花名册信息
        String zzbz = krkhglReceive.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzbz","ywjgdm",krkhglReceive.getSszh());
        Khhmcxx khhmcxx1 = new Khhmcxx();
        BeanUtils.copyProperties(krkhglReceive,khhmcxx1);
        khhmcxx1.setSszh(zzbz);
        khhmcxx1.setJgdm(krkhglReceive.getSszh());
        khhmcxx1.setDz(krkhglReceive.getZz());
        Khhmcxx khhmcxx2= khhmcxxMapper.selectByMainId(khhmcxx1.getZjhm());
        if(khhmcxx2==null){
            khhmcxxMapper.insert(khhmcxx1);
        }else{
            QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm",khhmcxx1.getZjhm());
            khhmcxxMapper.update(khhmcxx1,queryWrapper);
        }

        //插入家庭成员信息
        for (Khhmcxx khhmcxx : khhmcxxList) {
            khhmcxx.setSsyxdy(krkhglReceive.getSsyxdy());
            khhmcxx.setSszh(zzbz);
            khhmcxx.setJgdm(krkhglReceive.getSszh());
            khhmcxx.setHhbm(krkhglReceive.getHhbm());
            khhmcxx.setKhlx(krkhglReceive.getKhlx());
            Khhmcxx khhmcxx3= khhmcxxMapper.selectByMainId(khhmcxx.getZjhm());
            if(khhmcxx3==null){
                khhmcxxMapper.insert(khhmcxx);
            }else {
                QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm",khhmcxx.getZjhm());
                khhmcxxMapper.update(khhmcxx,queryWrapper);
            }
        }

        //修改评级授信信息
        for (CamsZcsxGrpjsxxx camsZcsxGrpjsxxx : camsZcsxGrpjsxxxList) {
            CamsZcsxGrpjsxxx grpjsxxx = camsZcsxGrpjsxxxMapper.selectByMainId(krkhglReceive.getHhbm());
            UpdateWrapper<CamsZcsxGrpjsxxx> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.eq("hhbm", camsZcsxGrpjsxxx.getHhbm());
            if(grpjsxxx!=null){
                if(!camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())&&krkhglReceive.getSfsxdx().equals("1")){
                    camsZcsxGrpjsxxx.setZjhm(krkhglReceive.getZjhm());
                    camsZcsxGrpjsxxx.setKhmc(krkhglReceive.getKhmc());
                    camsZcsxGrpjsxxx.setQydm(krkhglReceive.getSsyxdy());
                }else if(!krkhglReceive.getSfsxdx().equals("1")&&camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())){
                    camsZcsxGrpjsxxx.setZjhm("");
                    camsZcsxGrpjsxxx.setKhmc("");
                    camsZcsxGrpjsxxx.setQydm(krkhglReceive.getSsyxdy());
                }else{
                    camsZcsxGrpjsxxx.setZjhm(grpjsxxx.getZjhm());
                    camsZcsxGrpjsxxx.setKhmc(grpjsxxx.getKhmc());
                    camsZcsxGrpjsxxx.setQydm(grpjsxxx.getQydm());
                }
                camsZcsxGrpjsxxxMapper.update(camsZcsxGrpjsxxx, updateWrapper1);

                /*if (grpjsxxx.getZjhm()==null ||camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())) {
                    camsZcsxGrpjsxxx.setQydm(krkhglReceive.getSsyxdy());
                    UpdateWrapper<CamsZcsxGrpjsxxx> updateWrapper1 = new UpdateWrapper<>();
                    updateWrapper1.eq("hhbm", camsZcsxGrpjsxxx.getHhbm());
                    camsZcsxGrpjsxxxMapper.update(camsZcsxGrpjsxxx, updateWrapper1);
                }*/
            }else{
                camsZcsxGrpjsxxxMapper.insert(camsZcsxGrpjsxxx);
            }
        }

        //修改附件信息
        Fjgl fjgl = new Fjgl();
        //本人身份证附件
        JSONArray brsfzfjglList= krkhglReceive.getImgdate().getJSONArray("brsfzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"1");
        if(krkhglReceive.getImgdate().getJSONArray("brsfzfileList")!=null){
            for (int i = 0; i < brsfzfjglList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (brsfzfjglList.getJSONObject(i).getJSONObject("response")!=null){
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = brsfzfjglList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator+brsfzfjglList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("1");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(brsfzfjglList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                    System.out.println(brsfzfjglList.getJSONObject(i).getString("name"));
                }else{
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(brsfzfjglList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + brsfzfjglList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("1");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(brsfzfjglList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        };

        //配偶身份证附件
        JSONArray posfzfileList = krkhglReceive.getImgdate().getJSONArray("posfzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"2");
        if (krkhglReceive.getImgdate().getJSONArray("posfzfileList")!=null) {
            for (int i = 0; i < posfzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (posfzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = posfzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + posfzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("2");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(posfzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                    System.out.println(posfzfileList.getJSONObject(i).getString("name"));
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(posfzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + posfzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("2");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(posfzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //结婚证附件
        JSONArray jhzfileList = krkhglReceive.getImgdate().getJSONArray("jhzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"3");
        if(krkhglReceive.getImgdate().getJSONArray("jhzfileList")!=null) {
            for (int i = 0; i < jhzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (jhzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =jhzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + jhzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("3");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(jhzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(jhzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + jhzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("3");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(jhzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //人像拍照附件
        JSONArray rxzpfileList = krkhglReceive.getImgdate().getJSONArray("rxzpfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"4");
        if(krkhglReceive.getImgdate().getJSONArray("rxzpfileList")!=null) {
            for (int i = 0; i < rxzpfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (rxzpfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =rxzpfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + rxzpfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("4");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(rxzpfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(rxzpfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + rxzpfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("4");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(rxzpfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //房屋拍照
        JSONArray fwpzfileList = krkhglReceive.getImgdate().getJSONArray("fwpzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"5");
        if(krkhglReceive.getImgdate().getJSONArray("fwpzfileList")!=null) {
            for (int i = 0; i < fwpzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (fwpzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =  fwpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + fwpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("5");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(fwpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(fwpzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + fwpzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("5");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(fwpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //权重拍照
        JSONArray qzpzfileList = krkhglReceive.getImgdate().getJSONArray("qzpzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"6");
        if(krkhglReceive.getImgdate().getJSONArray("qzpzfileList")!=null) {
            for (int i = 0; i < qzpzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (qzpzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =  qzpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + qzpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("6");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qzpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(qzpzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + qzpzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("6");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qzpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //汽车照片
        JSONArray qczpfileList = krkhglReceive.getImgdate().getJSONArray("qczpfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"7");
        if(krkhglReceive.getImgdate().getJSONArray("qczpfileList")!=null) {
            for (int i = 0; i < qczpfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (qczpfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = qczpfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + qczpfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("7");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qczpfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(qczpfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + qczpfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("7");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qczpfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //其他资产
        JSONArray qtzcfileList = krkhglReceive.getImgdate().getJSONArray("qtzcfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"8");
        if(krkhglReceive.getImgdate().getJSONArray("qtzcfileList")!=null) {
            for (int i = 0; i < qtzcfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (qtzcfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj =  qtzcfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + qtzcfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("8");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qtzcfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(qtzcfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + qtzcfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("8");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(qtzcfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //经营场所、场地(果园、鱼塘等
        JSONArray jycsfileList = krkhglReceive.getImgdate().getJSONArray("jycsfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"9");
        if(krkhglReceive.getImgdate().getJSONArray("jycsfileList")!=null) {
            for (int i = 0; i < jycsfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (jycsfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = jycsfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + jycsfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("9");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(jycsfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(jycsfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + jycsfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("9");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(jycsfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }
        }
        //采集者--采集者拍照
        JSONArray cjzpzfileList = krkhglReceive.getImgdate().getJSONArray("cjzpzfileList");
        fjglMapper.deleteImg(krkhglReceive.getZjhm(),"10");
        if(krkhglReceive.getImgdate().getJSONArray("cjzpzfileList")!=null) {
            for (int i = 0; i < cjzpzfileList.size(); i++) {
                String id = UUIDGenerator.generate();
                if (cjzpzfileList.getJSONObject(i).getJSONObject("response") != null) {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    String fwlj = cjzpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setFwlj(fwlj);
                    String zjlj = uploadpath + File.separator + cjzpzfileList.getJSONObject(i).getJSONObject("response").getString("message");
                    fjgl.setZllj(zjlj);
                    fjgl.setZllx("10");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(cjzpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zjlj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                } else {
                    fjgl.setQydm(krkhglReceive.getSsyxdy());
                    fjgl.setHhbm(krkhglReceive.getHhbm());
                    fjgl.setZjhm(krkhglReceive.getZjhm());
                    fjgl.setFwlj(cjzpzfileList.getJSONObject(i).getString("url"));
                    String zllj = uploadpath + File.separator + cjzpzfileList.getJSONObject(i).getString("url");
                    fjgl.setZllj(zllj);
                    fjgl.setZllx("10");
                    fjgl.setZlbh(id);
                    fjgl.setZlmc(cjzpzfileList.getJSONObject(i).getString("name"));
                    File file = new File(zllj);
                    fjgl.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
                    LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    fjgl.setScr(loginUser.getUsername());
                    fjgl.setLrr(loginUser.getUsername());
                    fjgl.setScsj(DateUtils.getDate());
                    fjglMapper.insert(fjgl);
                }
            }

        }


    }


    /**
     * 修改一对多
     *
     */
    @Override
    @Transactional
    public void updateMainPad(ZcsxNhcjxx zcsxNhcjxx, KrkhglReceive grkhglReceive, List<Khhmcxx>khhmcxxList, List<CamsZcsxGrpjsxxx>camsZcsxGrpjsxxxList){

        String ywjgdm = grkhglReceive.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","ywjgdm","zzbz",grkhglReceive.getSszh());

        ZcsxNhcjxx xendCjxx = zcsxNhcjxxMapper.selectById(zcsxNhcjxx.getId());
        grkhglReceive.setZjhm(xendCjxx.getZjhm());
        zcsxNhcjxx.setZjhm(xendCjxx.getZjhm());

        zcsxNhcjxx.setSjhm(grkhglReceive.getLxfs());
        zcsxNhcjxx.setKhlx1(grkhglReceive.getKhlx());
        zcsxNhcjxx.setCshygz(grkhglReceive.getCszy());
        try {
            zcsxNhcjxx.setSign1(saveImg(zcsxNhcjxx.getSign1()));
            zcsxNhcjxx.setSign2(saveImg(zcsxNhcjxx.getSign2()));
            zcsxNhcjxx.setSign3(saveImg(zcsxNhcjxx.getSign3()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UpdateWrapper<ZcsxNhcjxx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("zjhm",zcsxNhcjxx.getZjhm());
        zcsxNhcjxxMapper.update(zcsxNhcjxx,updateWrapper);

        //保存花名册信息
        Khhmcxx khhmcxx1 = new Khhmcxx();
        BeanUtils.copyProperties(grkhglReceive,khhmcxx1);
        khhmcxx1.setSszh(grkhglReceive.getSszh());
        khhmcxx1.setJgdm(ywjgdm);
        khhmcxx1.setDz(grkhglReceive.getZz());
        khhmcxx1.setXgsj(new Date());
        Khhmcxx khhmcxx2= khhmcxxMapper.selectByMainId(khhmcxx1.getZjhm());
        if (khhmcxx2 == null) {
            khhmcxxMapper.insert(khhmcxx1);
        } else {
            QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm",khhmcxx1.getZjhm());
            khhmcxxMapper.update(khhmcxx1,queryWrapper);
        }

        //插入家庭成员信息
        for (Khhmcxx khhmcxx : khhmcxxList) {
            if (khhmcxx.getId() != null && khhmcxx.getId().length() > 0) {
                Khhmcxx jtcyxx = khhmcxxMapper.selectById(khhmcxx.getId());
                khhmcxx.setZjhm(jtcyxx.getZjhm());
                khhmcxx.setSsyxdy(grkhglReceive.getSsyxdy());
                khhmcxx.setSszh(grkhglReceive.getSszh());
                khhmcxx.setJgdm(ywjgdm);
                khhmcxx.setHhbm(grkhglReceive.getHhbm());
                khhmcxx.setKhlx(grkhglReceive.getKhlx());
                Khhmcxx khhmcxx3= khhmcxxMapper.selectByMainId(khhmcxx.getZjhm());
                if (khhmcxx3 == null) {
                    khhmcxxMapper.insert(khhmcxx);
                } else {
                    QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("zjhm",khhmcxx.getZjhm());
                    khhmcxxMapper.update(khhmcxx,queryWrapper);
                }
            } else {
                khhmcxx.setSsyxdy(grkhglReceive.getSsyxdy());
                khhmcxx.setSszh(grkhglReceive.getSszh());
                khhmcxx.setJgdm(ywjgdm);
                khhmcxx.setHhbm(grkhglReceive.getHhbm());
                khhmcxx.setKhlx(grkhglReceive.getKhlx());
                Khhmcxx khhmcxx3 = khhmcxxMapper.selectByMainId(khhmcxx.getZjhm());
                if (khhmcxx3 == null) {
                    khhmcxxMapper.insert(khhmcxx);
                } else {
                    QueryWrapper<Khhmcxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("zjhm",khhmcxx.getZjhm());
                    khhmcxxMapper.update(khhmcxx,queryWrapper);
                }
            }
        }

        //修改评级授信信息
        for (CamsZcsxGrpjsxxx camsZcsxGrpjsxxx : camsZcsxGrpjsxxxList) {
            CamsZcsxGrpjsxxx grpjsxxx = camsZcsxGrpjsxxxMapper.selectByMainId(grkhglReceive.getHhbm());
            UpdateWrapper<CamsZcsxGrpjsxxx> updateWrapper1 = new UpdateWrapper<>();
            updateWrapper1.eq("hhbm", camsZcsxGrpjsxxx.getHhbm());
            if (grpjsxxx != null) {
                if(!camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())&&grkhglReceive.getSfsxdx().equals("1")){
                    camsZcsxGrpjsxxx.setZjhm(grkhglReceive.getZjhm());
                    camsZcsxGrpjsxxx.setKhmc(grkhglReceive.getKhmc());
                    camsZcsxGrpjsxxx.setQydm(grkhglReceive.getSsyxdy());
                }else if(!grkhglReceive.getSfsxdx().equals("1")&&camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())){
                    camsZcsxGrpjsxxx.setZjhm("");
                    camsZcsxGrpjsxxx.setKhmc("");
                    camsZcsxGrpjsxxx.setQydm(grkhglReceive.getSsyxdy());
                }else{
                    camsZcsxGrpjsxxx.setZjhm(grpjsxxx.getZjhm());
                    camsZcsxGrpjsxxx.setKhmc(grpjsxxx.getKhmc());
                    camsZcsxGrpjsxxx.setQydm(grpjsxxx.getQydm());
                }
                camsZcsxGrpjsxxxMapper.update(camsZcsxGrpjsxxx, updateWrapper1);

//                if (grpjsxxx.getZjhm()==null ||camsZcsxGrpjsxxx.getZjhm().equals(grpjsxxx.getZjhm())) {
//                    camsZcsxGrpjsxxx.setQydm(grkhglReceive.getSsyxdy());
//                    UpdateWrapper<CamsZcsxGrpjsxxx> updateWrapper1 = new UpdateWrapper<>();
//                    updateWrapper1.eq("hhbm", camsZcsxGrpjsxxx.getHhbm());
//                    camsZcsxGrpjsxxxMapper.update(camsZcsxGrpjsxxx, updateWrapper1);
//                }
            }else{
                camsZcsxGrpjsxxxMapper.insert(camsZcsxGrpjsxxx);
            }

//            try {
//                ivKhglKrkhglService.prepare(zcsxNhcjxx.getZjhm(), khhmcxx1.getHhbm());
//                iVKhglGrkhglService.calculateModel(khhmcxx1.getHhbm(), zcsxNhcjxx.getZjhm());
//            }catch (Exception e){
//                log.error("模型计算失败！"+e.getMessage());
//            }
        }
    }


    @Override
    @Transactional
    public void delMain(String zjhm) {
/*
        khhmcxxMapper.deleteByMainId(zjhm);
        camsZcsxGrpjsxxxMapper.deleteByMainId(zjhm);
        zcsxNhcjxxMapper.deleteByMainId(zjhm);*/

    }

    @Override
    @Transactional
    public void delBatchMain(Collection<? extends Serializable> idList) {


    }

    @Override
    public int prepare(String zjhm, String hhbm) {
        CamsZcsxGrpjsxxxBc camsZcsxGrpjsxxxBc = new CamsZcsxGrpjsxxxBc();
        camsZcsxGrpjsxxxBc.setHhbm(hhbm);
        camsZcsxGrpjsxxxBc.setZjhm(zjhm);
        //获取定活期余额
        String dhqye = cbsInvmBaseMapper.dhqye(zjhm);
        if (StringUtils.isNotBlank(dhqye)) {
            camsZcsxGrpjsxxxBc.setDhqye(new BigDecimal(dhqye));
        } else {
            camsZcsxGrpjsxxxBc.setDhqye(new BigDecimal(0));
        }
        //存入交易额 存入交易次数
        camsZcsxGrpjsxxxBc.setCrjye(new BigDecimal(0));
        camsZcsxGrpjsxxxBc.setCrjycs(new BigDecimal(0));
        Map crxx = cbsInvmBaseMapper.crxx(zjhm);
        if (!crxx.isEmpty()) {
            if (crxx.get("count") != null)
                camsZcsxGrpjsxxxBc.setCrjycs(new BigDecimal(crxx.get("count").toString()));

            if (crxx.get("amount") != null)
                camsZcsxGrpjsxxxBc.setCrjye(new BigDecimal(crxx.get("amount").toString()));
        }
        //支出交易额 支出交易次数
        camsZcsxGrpjsxxxBc.setZcjye(new BigDecimal(0));
        camsZcsxGrpjsxxxBc.setZcjycs(new BigDecimal(0));
        Map zcxx = cbsInvmBaseMapper.zcxx(zjhm);
        if (!zcxx.isEmpty()) {
            if (crxx.get("count") != null)
                camsZcsxGrpjsxxxBc.setZcjycs(new BigDecimal(crxx.get("count").toString()));

            if (crxx.get("amount") != null)
                camsZcsxGrpjsxxxBc.setZcjye(new BigDecimal(crxx.get("amount").toString()));
        }
        //获取开户数量
        int ckzhs = cbsInvmBaseMapper.ckzhs(zjhm);
        camsZcsxGrpjsxxxBc.setCkzhs(ckzhs);

        //口袋零钱记录数 网银记录数 手机银行记录数 etc记录数 农信银记录数 水费代收
        camsZcsxGrpjsxxxBc.setKdlqjls(0);
        camsZcsxGrpjsxxxBc.setSjyhjls(0);
        camsZcsxGrpjsxxxBc.setEtcjls(0);
        camsZcsxGrpjsxxxBc.setNxyjls(0);
        camsZcsxGrpjsxxxBc.setWyjls(0);
        camsZcsxGrpjsxxxBc.setSfds(0);
        Map intermediaryBusiness = cbsInvmBaseMapper.getIntermediaryBusiness(zjhm);
        if (intermediaryBusiness != null) {
            String phoneBank = (String) intermediaryBusiness.get("phoneBank");
            String ebank = (String) intermediaryBusiness.get("ebank");
            String mobileBank = (String) intermediaryBusiness.get("mobileBank");
            String etc = (String) intermediaryBusiness.get("etc");
            String nxy = (String) intermediaryBusiness.get("nxy");
            String water = (String) intermediaryBusiness.get("water");
            if (StringUtils.isNotBlank(phoneBank)) {
                camsZcsxGrpjsxxxBc.setSjyhjls(Integer.valueOf(phoneBank));
            }
            if (StringUtils.isNotBlank(ebank)) {
                camsZcsxGrpjsxxxBc.setWyjls(Integer.valueOf(ebank));
            }
            if (StringUtils.isNotBlank(mobileBank)) {

            }
            if (StringUtils.isNotBlank(etc)) {
                camsZcsxGrpjsxxxBc.setEtcjls(Integer.valueOf(etc));
            }
            if (StringUtils.isNotBlank(nxy)) {
                camsZcsxGrpjsxxxBc.setNxyjls(Integer.valueOf(nxy));
            }
            if (StringUtils.isNotBlank(water)) {
                camsZcsxGrpjsxxxBc.setSfds(Integer.valueOf(water));
            }

            //表外贷款
            camsZcsxGrpjsxxxBc.setBwdk(new BigDecimal(0));
            String bwdk = cbsInvmBaseMapper.bwdk(zjhm);
            if (StringUtils.isNotBlank(bwdk))
                camsZcsxGrpjsxxxBc.setBwdk(new BigDecimal(bwdk));

            //todo 逾期次数
            camsZcsxGrpjsxxxBc.setYqcs(0);

            //授信额度
            camsZcsxGrpjsxxxBc.setSxed(new BigDecimal(0));
            String credit = cbsInvmBaseMapper.credit(zjhm);
            if (StringUtils.isNotBlank(credit))
                camsZcsxGrpjsxxxBc.setSxed(new BigDecimal(credit));

            //展期金额
            camsZcsxGrpjsxxxBc.setZqje(new BigDecimal(0));
            //是否信贷
            int sfxd = cbsInvmBaseMapper.sfxd(zjhm);
            camsZcsxGrpjsxxxBc.setSfds(sfxd);
            //开户时间
            //存款日平
            /*Map khsjAndCkrp = cbsInvmBaseMapper.khsjAndCkrp(zjhm);
            if (khsjAndCkrp != null) {
                if (khsjAndCkrp.get("dat") != null &&  khsjAndCkrp.get("dat").toString() != null) {
                    try {
                        camsZcsxGrpjsxxxBc.setKhsj(DateUtils.parseDate(khsjAndCkrp.get("dat").toString(), "yyyy-MM-dd"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if ( khsjAndCkrp.get("avg_day") != null && khsjAndCkrp.get("avg_day").toString() != null) {
                    camsZcsxGrpjsxxxBc.setCkrp(new BigDecimal(khsjAndCkrp.get("avg_day").toString()));
                }
            }*/

        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM",zjhm);
        queryWrapper.eq("HHBM",hhbm);
        camsZcsxGrpjsxxxBcMapper.delete(queryWrapper);
        return camsZcsxGrpjsxxxBcMapper.insert(camsZcsxGrpjsxxxBc);

    }

    public String saveImg(String baseImg) throws Exception   {
        //定义一个正则表达式的筛选规则，为了获取图片的类型
        String rgex = "data:image/(.*?);base64";
        String type = getSubUtilSimple(baseImg, rgex);
        //去除base64图片的前缀
        baseImg = baseImg.replaceFirst("data:(.+?);base64,", "");
        byte[] b;
        byte[] bs;
        OutputStream os = null;
        String fileName = "";
        String nowDate = "";
        // 格式化并获取当前日期（用来命名）
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        nowDate = format.format(new Date());
        //把图片转换成二进制
        b = Base64.decodeBase64(baseImg.replaceAll(" ", "+"));
        //生成路径
        String path = uploadpath  + File.separator + "imgSign" + File.separator + nowDate + File.separator;
        //随机生成图片的名字，同时根据类型结尾
        fileName = UUID.randomUUID().toString() + "." + type;
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        File imageFile = new File(path + "/" + fileName);
        BASE64Decoder d = new BASE64Decoder();
        // 保存
        try {
            bs = d.decodeBuffer(Base64.encodeBase64String(b));
            os = new FileOutputStream(imageFile);
            os.write(bs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.getLocalizedMessage();
                }
            }
        }

        return "imgSign" + File.separator + nowDate + File.separator + fileName;
    }


    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";


    }


}
