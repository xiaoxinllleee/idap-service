package org.cmms.modules.pad.gzryxxgl.controller;

import java.util.*;

import cn.hutool.core.codec.Base64;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;

import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.pad.gzryxxgl.entity.CamsZcsxGzryfcxx;
import org.cmms.modules.pad.gzryxxgl.entity.CamsZcsxGzrypjsxxx;
import org.cmms.modules.pad.gzryxxgl.entity.Gzryhfxxb;
import org.cmms.modules.pad.gzryxxgl.entity.KhxxglKhxqGzry;
import org.cmms.modules.pad.gzryxxgl.service.*;

import org.cmms.modules.pad.nhxxgl.entity.*;
import org.cmms.modules.pad.nhxxgl.service.*;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 公职人员信息管理
 * @Author: jeecg-boot
 * @Date: 2022-08-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "公职人员信息管理")
@RestController
@RequestMapping("/gzryxxgl/khxxglKhxqGzry")
public class KhxxglKhxqGzryController extends JeecgController<KhxxglKhxqGzry, IKhxxglKhxqGzryService> {
    @Autowired
    private IKhxxglKhxqGzryService khxxglKhxqGzryService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private IYwhywwlxxService ywhywwlxxService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private ICamsZcsxNhfcxxPadService camsZcsxNhfcxxPad;
    @Autowired
    private ICamsZcsxNhpjsxxxPadService iCamsZcsxNhpjsxxxPadService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IvKhglNhhzxxglService vKhglNhhzxxgl12;
    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;
    @Autowired
    private IYxglKhhfxxbService yxglKhhfxxbService;
    @Autowired
    private IGzryhfxxbService gzryhfxxbService;
    @Autowired
    private ICamsZcsxGzryfcxxService camsZcsxGzryfcxxService;
    @Autowired
    private ICamsZcsxGzrypjsxxxService camsZcsxGzrypjsxxxService;

    /**
     * 分页列表查询
     *
     * @param khxxglKhxqGzry
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "公职人员信息管理-分页列表查询")
    @ApiOperation(value = "公职人员信息管理-分页列表查询", notes = "公职人员信息管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(KhxxglKhxqGzry khxxglKhxqGzry,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<KhxxglKhxqGzry> queryWrapper = QueryGenerator.initQueryWrapper(khxxglKhxqGzry, req.getParameterMap());
        Page<KhxxglKhxqGzry> page = new Page<KhxxglKhxqGzry>(pageNo, pageSize);
        IPage<KhxxglKhxqGzry> pageList = khxxglKhxqGzryService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param khxxglKhxqGzry
     * @return
     */
    @AutoLog(value = "公职人员信息管理-添加")
    @ApiOperation(value = "公职人员信息管理-添加", notes = "公职人员信息管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhxxglKhxqGzry khxxglKhxqGzry) {
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Result<KhxxglKhxqGzry> result = new Result<KhxxglKhxqGzry>();
        try {
            //对证件号码进行解密
            khxxglKhxqGzry.setZjhm(Base64.decodeStr(khxxglKhxqGzry.getZjhm()));
            //判断证件号码是否存在
            QueryWrapper<KhxxglKhxqGzry> khxqGzryQueryWrapper = new QueryWrapper<>();
            khxqGzryQueryWrapper.eq("zjhm", khxxglKhxqGzry.getZjhm());
            List<KhxxglKhxqGzry> list = khxxglKhxqGzryService.list(khxqGzryQueryWrapper);
            if (list != null && list.size() > 0) {
                return Result.error("证件号码已存在公职人员信息！");
            }
            khxxglKhxqGzry.setSfcj("2");
            khxxglKhxqGzry.setSfyxzf("2");
            khxxglKhxqGzry.setLrr(user.getUsername());
            khxxglKhxqGzry.setLrsj(new Date());
            khxxglKhxqGzry.setLrbz(0);
            khxxglKhxqGzry.setSfzf("2");
            khxxglKhxqGzryService.save(khxxglKhxqGzry);

            //进行回访记录
            saveYxglGzryhfxxb(khxxglKhxqGzry);
            //提取是否有效走访
            khxxglKhxqGzryService.init(khxxglKhxqGzry.getId(),user.getWorkNo(),user.getUsername());

            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("系统错误，添加失败！");
        }
        return result;
    }

    /**
     * 获取房产信息
     *
     * @param gzryid
     * @return
     */
    @GetMapping(value = "/queryFcxxByGzryid")
    public Result<?> queryFcxxByGzryid(@Param("gzryid") String gzryid) {
        try {
            if (!StringUtils.isEmpty(gzryid)) {
                List<CamsZcsxGzryfcxx> list = camsZcsxGzryfcxxService.getByGzryid(gzryid);
                if (list != null && list.size() > 0) {
                    return Result.ok(list);
                }
            } else {
                return Result.error("请求参数错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询房产信息错误！", e);
            return Result.error("查询房产信息错误！");
        }
        return Result.ok("没有房产数据");
    }

    /**
     * 获取评级授信信息
     *
     * @param gzryid
     * @return
     */
    @GetMapping(value = "/queryPjsxxxByGzryid")
    public Result<?> queryPjsxxxByGzryid(@Param("gzryid") String gzryid) {
        try {
            if (!StringUtils.isEmpty(gzryid)) {
                CamsZcsxGzrypjsxxx gzrypjsxxx = camsZcsxGzrypjsxxxService.getByGzryid(gzryid);
                if (gzrypjsxxx != null) {
                    JSONObject gzrypjsxxxJo = JSONObject.parseObject(JSONObject.toJSON(gzrypjsxxx).toString());
                    //获取资产信息
                    JSONArray gzryzcxxJa = new JSONArray();
                    //资产情况
                    JSONObject dcJo = new JSONObject();
                    dcJo.put("zclx", "地产");
                    dcJo.put("zcsl", gzrypjsxxx.getDcsl());
                    dcJo.put("zcjz", gzrypjsxxx.getDcjz());
                    dcJo.put("zcsm", gzrypjsxxx.getDcxqsm());
                    JSONObject jtgjJo = new JSONObject();
                    jtgjJo.put("zclx", "交通工具");
                    jtgjJo.put("zcsl", gzrypjsxxx.getJtgjsl());
                    jtgjJo.put("zcjz", gzrypjsxxx.getJtgjjz());
                    jtgjJo.put("zcsm", gzrypjsxxx.getJtgjxqsm());
                    JSONObject ckJo = new JSONObject();
                    ckJo.put("zclx", "存款");
                    ckJo.put("zcsl", gzrypjsxxx.getCksl());
                    ckJo.put("zcjz", gzrypjsxxx.getCkjz());
                    ckJo.put("zcsm", gzrypjsxxx.getCkxqsm());
                    JSONObject yjdzJo = new JSONObject();
                    yjdzJo.put("zclx", "有价单证");
                    yjdzJo.put("zcsl", gzrypjsxxx.getYjdzsl());
                    yjdzJo.put("zcjz", gzrypjsxxx.getYjdzjz());
                    yjdzJo.put("zcsm", gzrypjsxxx.getYjdzxqsm());
                    JSONObject gqJo = new JSONObject();
                    gqJo.put("zclx", "股权");
                    gqJo.put("zcsl", gzrypjsxxx.getGqsl());
                    gqJo.put("zcjz", gzrypjsxxx.getGqjz());
                    gqJo.put("zcsm", gzrypjsxxx.getGqxqsm());
                    JSONObject qtzcJo = new JSONObject();
                    qtzcJo.put("zclx", "其他资产");
                    qtzcJo.put("zcsl", gzrypjsxxx.getQtzcsl());
                    qtzcJo.put("zcjz", gzrypjsxxx.getQtzcjz());
                    qtzcJo.put("zcsm", gzrypjsxxx.getQtzcxqsm());
                    JSONObject nzsrJo = new JSONObject();
                    nzsrJo.put("zclx", "年总收入");
                    nzsrJo.put("zcsl", gzrypjsxxx.getNzsrsl());
                    nzsrJo.put("zcjz", gzrypjsxxx.getNzsrjz());
                    nzsrJo.put("zcsm", gzrypjsxxx.getNzsrxqsm());
                    gzryzcxxJa.add(dcJo);
                    gzryzcxxJa.add(jtgjJo);
                    gzryzcxxJa.add(ckJo);
                    gzryzcxxJa.add(yjdzJo);
                    gzryzcxxJa.add(gqJo);
                    gzryzcxxJa.add(qtzcJo);
                    gzryzcxxJa.add(nzsrJo);
                    //负债情况
                    JSONArray shfzxxJa = new JSONArray();
                    JSONObject bxtJo = new JSONObject();
                    bxtJo.put("jkfs", "本系统");
                    bxtJo.put("zqr", gzrypjsxxx.getBxtjkzqr());
                    bxtJo.put("jkje", gzrypjsxxx.getBxtjksl());
                    bxtJo.put("jksm", gzrypjsxxx.getBxtjkxqsm());
                    JSONObject thJo = new JSONObject();
                    thJo.put("jkfs", "他行");
                    thJo.put("zqr", gzrypjsxxx.getThjkzqr());
                    thJo.put("jkje", gzrypjsxxx.getThjksl());
                    thJo.put("jksm", gzrypjsxxx.getThjkxqsm());
                    JSONObject xykJo = new JSONObject();
                    xykJo.put("jkfs", "信用卡");
                    xykJo.put("zqr", gzrypjsxxx.getXykzqr());
                    xykJo.put("jkje", gzrypjsxxx.getXyksl());
                    xykJo.put("jksm", gzrypjsxxx.getXykxqsm());
                    JSONObject qtfzJo = new JSONObject();
                    qtfzJo.put("jkfs", "其他负债");
                    qtfzJo.put("zqr", gzrypjsxxx.getQtfzzqr());
                    qtfzJo.put("jkje", gzrypjsxxx.getQtfzsl());
                    qtfzJo.put("jksm", gzrypjsxxx.getQtfzxqsm());
                    JSONObject jtnkzJo = new JSONObject();
                    jtnkzJo.put("jkfs", "家庭年开支");
                    jtnkzJo.put("zqr", gzrypjsxxx.getJtnkzzqr());
                    jtnkzJo.put("jkje", gzrypjsxxx.getJtnkzsl());
                    jtnkzJo.put("jksm", gzrypjsxxx.getJtnkzxqsm());

                    shfzxxJa.add(bxtJo);
                    shfzxxJa.add(thJo);
                    shfzxxJa.add(xykJo);
                    shfzxxJa.add(qtfzJo);
                    shfzxxJa.add(jtnkzJo);

                    gzrypjsxxxJo.put("shzcxx", gzryzcxxJa);
                    gzrypjsxxxJo.put("shfzxx", shfzxxJa);
                    return Result.ok(gzrypjsxxxJo);
                }
            } else {
                return Result.error("请求参数错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询评级授信信息错误！", e);
            return Result.error("查询评级授信信息错误！");
        }
        return Result.ok("没有评级授信数据");
    }

    /**
     * 根据id获取证件号码
     * @param id
     * @return
     */
    private String getZjhm(String id){
        QueryWrapper<KhxxglKhxqGzry> khxqGzryQueryWrapper = new QueryWrapper<>();
        khxqGzryQueryWrapper.eq("id", id);
        return khxxglKhxqGzryService.getOne(khxqGzryQueryWrapper,false).getZjhm();
    }
    /**
     * 保存公职人员回访信息
     */
    protected void saveYxglGzryhfxxb(KhxxglKhxqGzry khxxglKhxqGzry) {
        Gzryhfxxb gzryhfxxb = new Gzryhfxxb();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(sysUser.getWorkNo());
        if (vhrbasstaffpost != null) {
            gzryhfxxb.setZzbz(vhrbasstaffpost.getZzbz());
            gzryhfxxb.setYggh(vhrbasstaffpost.getYggh());
            gzryhfxxb.setKhjlbh(vhrbasstaffpost.getKhjlbz());
        }
        gzryhfxxb.setId(UUID.randomUUID().toString().substring(0, 32));
        gzryhfxxb.setGzryid(khxxglKhxqGzry.getId());
        gzryhfxxb.setYxdy(khxxglKhxqGzry.getWgbh());
        gzryhfxxb.setKhmc(khxxglKhxqGzry.getKhmc());
        gzryhfxxb.setZjhm(khxxglKhxqGzry.getZjhm());
        gzryhfxxb.setHfrq(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd"), "yyyy-MM-dd"));
        gzryhfxxb.setSjly("2");
        gzryhfxxb.setLrr(sysUser.getUsername());
        gzryhfxxbService.save(gzryhfxxb);
    }

    /**
     * 添加房产信息
     * @param gzryfcxxList
     * @return
     */
    @Transactional
    @RequestMapping(value = "/AddFcxx", method = RequestMethod.POST)
    public Result<?> AddFcxx(@RequestBody List<CamsZcsxGzryfcxx> gzryfcxxList) {
        try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (gzryfcxxList != null && gzryfcxxList.size() > 0) {
                UpdateWrapper<CamsZcsxGzryfcxx> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("gzryid", gzryfcxxList.get(0).getGzryid());
                //先删除数据
                camsZcsxGzryfcxxService.remove(updateWrapper);
                for (int i = 0; i < gzryfcxxList.size(); i++) {
                    gzryfcxxList.get(i).setCreateTime(new Date());
                    gzryfcxxList.get(i).setCreateBy(sysUser.getUsername());
                    gzryfcxxList.get(i).setId(UUID.randomUUID().toString().substring(0, 32));
                }
                camsZcsxGzryfcxxService.saveBatch(gzryfcxxList);
            }
        } catch (Exception e) {
            log.error("编辑房产信息失败", e);
            return Result.error("系统错误，编辑房产信息失败！");
        }
        return Result.ok("添加成功");
    }

    /**
     * 添加评级授信信息
     * @param jsonObject
     * @return
     */
    @Transactional
    @RequestMapping(value = "/AddPjsxxx", method = RequestMethod.POST)
    public Result<?> AddPjsxxx(@RequestBody CamsZcsxGzrypjsxxx jsonObject) {
        try {
            QueryWrapper<CamsZcsxGzrypjsxxx> pjsxxx = new QueryWrapper<>();
            pjsxxx.eq("gzryid", jsonObject.getId());
            List<CamsZcsxGzrypjsxxx> list = camsZcsxGzrypjsxxxService.list(pjsxxx);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (list != null && list.size() > 0) {
                UpdateWrapper<CamsZcsxGzrypjsxxx> update = new UpdateWrapper<>();
                update.eq("gzryid", jsonObject.getId());
                jsonObject.setCreateBy(sysUser.getUsername());
                jsonObject.setUpdateBy(sysUser.getUsername());
                jsonObject.setUpdateTime(new Date());
                jsonObject.setGzryid(jsonObject.getId());
                camsZcsxGzrypjsxxxService.update(jsonObject, update);
            } else {
                Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(sysUser.getWorkNo());
                if (vhrbasstaffpost != null) {
                    jsonObject.setSxdxsszh(vhrbasstaffpost.getZzbz());
                }
                jsonObject.setCreateBy(sysUser.getUsername());
                camsZcsxGzrypjsxxxService.save(jsonObject);
            }
        } catch (Exception e) {
            log.error("编辑评级授信信息失败", e);
            return Result.error("系统错误，编辑评级授信信息失败！");
        }
        return Result.ok("添加成功");
    }

    @GetMapping(value = "/queryJtxxByZjhm")
    public Result<?> queryJtxxByZjhm(@Param("id") String id) {
        try {
            //根据身份证信息获取户号编码
            QueryWrapper<Nhxq> khhmcQueryWrapper1 = new QueryWrapper<>();
            khhmcQueryWrapper1.eq("zjhm", getZjhm(id));
            Nhxq nhxqxx = nhxqService.getOne(khhmcQueryWrapper1,false);

            if(nhxqxx!=null) {
                //根据户号查询
                QueryWrapper<Nhxq> khhmcQueryWrapper2 = new QueryWrapper<>();
                khhmcQueryWrapper2.eq("hhbm", nhxqxx.getHhbm());
                khhmcQueryWrapper2.orderByAsc("yhzgx");
                List<Nhxq> list = nhxqService.list(khhmcQueryWrapper2);
                List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
                for (int i = 0; i < list.size(); i++) {
                    NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
                    Nhxq nhxq = list.get(i);
                    BeanUtils.copyProperties(nhxq, nhJtcyxx1);
                    nhJtcyxx1.setLxfs(nhxq.getSjhm());
                    nhJtcyxx1.setDz(nhxq.getZz());
                    String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
                    nhJtcyxx1.setYhzgx(yhzgx);
                    String xb = nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
                    nhJtcyxx1.setXb(xb);
                    nhJtcyxx1.setSfdb(nhxq.getSfdbh());
                    nhJtcyxx.add(nhJtcyxx1);
                    List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
                    if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                        if (nhJtcyxx.size() > 0) {
                            ywhywwlxxes.get(0).setId(list.get(i).getId());
                            BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
                            nhJtcyxx.get(i).setBlye();
                        }
                    }
                    nhJtcyxx1.setHhbm(nhxq.getHhbm());
                }
                if (list != null && list.size() > 0) {
                    return Result.ok(nhJtcyxx);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    /**
     * 编辑
     *
     * @param khxxglKhxqGzry
     * @return
     */
    @AutoLog(value = "公职人员信息管理-编辑")
    @ApiOperation(value = "公职人员信息管理-编辑", notes = "公职人员信息管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhxxglKhxqGzry khxxglKhxqGzry) {
        if ("****".equals(khxxglKhxqGzry.getZjhm().substring(12,16))){
            QueryWrapper<KhxxglKhxqGzry> khxqGzryQueryWrapper = new QueryWrapper<>();
            khxqGzryQueryWrapper.eq("id", khxxglKhxqGzry.getId());
            khxxglKhxqGzry.setZjhm(khxxglKhxqGzryService.getOne(khxqGzryQueryWrapper,false).getZjhm());
        }
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        khxxglKhxqGzry.setSfcj("1");
        khxxglKhxqGzry.setCjr(user.getUsername());
        khxxglKhxqGzry.setXgr(user.getUsername());
        khxxglKhxqGzry.setXgsj(new Date());
        khxxglKhxqGzryService.updateById(khxxglKhxqGzry);

        //进行回访记录
        saveYxglGzryhfxxb(khxxglKhxqGzry);
        //提取是否有效走访
        khxxglKhxqGzryService.init(khxxglKhxqGzry.getId(),user.getWorkNo(),user.getUsername());

        return Result.ok("提交成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公职人员信息管理-通过id删除")
    @ApiOperation(value = "公职人员信息管理-通过id删除", notes = "公职人员信息管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khxxglKhxqGzryService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "公职人员信息管理-批量删除")
    @ApiOperation(value = "公职人员信息管理-批量删除", notes = "公职人员信息管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khxxglKhxqGzryService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公职人员信息管理-通过id查询")
    @ApiOperation(value = "公职人员信息管理-通过id查询", notes = "公职人员信息管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhxxglKhxqGzry khxxglKhxqGzry = khxxglKhxqGzryService.getById(id);
        return Result.ok(khxxglKhxqGzry);
    }
}
