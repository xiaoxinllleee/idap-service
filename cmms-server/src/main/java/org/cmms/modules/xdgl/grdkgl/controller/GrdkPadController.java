package org.cmms.modules.xdgl.grdkgl.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.khgl.grkhgl.service.IKhhmcxxService;
import org.cmms.modules.khgl.khhmc.entity.Khhmc;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.service.*;
import org.cmms.modules.xdgl.grdkgl.service.impl.GrdkJtspServiceImpl;
import org.cmms.modules.xdgl.grdkgl.service.impl.GrxdzllbServiceImpl;
import org.cmms.modules.xdgl.grdkgl.vo.GrdkglPage;
import org.cmms.modules.xdgl.grdkgl.vo.GrxdInfoPage;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grkhpjsx;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrkhpjsxService;
import org.cmms.modules.yxdygl.sjyxdygl.entity.VSjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.service.IVSjyxdyglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
* @Description: 个人贷款
* @Author: jeecg-boot
* @Date:   2020-08-15
* @Version: V1.0
*/
@RestController
@RequestMapping("/xdgl/grdkgl/pad")
@Slf4j
public class GrdkPadController extends JeecgController<Grdkgl, IGrdkglService>  {
    @Autowired
    private IGrdkglService iGrdkglService;
    @Autowired
    private IJtcyxxService iJtcyxxService;
    @Autowired
    private IGlqyService iGlqyService;
    @Autowired
    private IFwxxService iFwxxService;
    @Autowired
    private ICfxxService iCfxxService;
    @Autowired
    private IClxxService iClxxService;
    @Autowired
    private IQtglzcService iQtgdzcService;
    @Autowired
    private IYhdkService iYhdkService;
    @Autowired
    private IBzdbService iBzdbService;
    @Autowired
    private IDydbService iDydbService;
    @Autowired
    private IZydbService iZydbService;
    @Autowired
    private IXydbService iXydbService;
    @Autowired
    private IGrdkcjxxService iGrdkcjxxService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private IYwhywwlxxService iYwhywwlxxService;
    @Autowired
    private IKhglKhhmcxxGrxdService iKhglKhhmcxxGrxdService;
    @Autowired
    private IYwhyewlxxJtsjhzService iYwhyewlxxJtsjhzService;
    @Autowired
    private IVSjyxdyglService iSjyxdyglService;
    @Autowired
    private IGrxdzllbService iGrxdzllbService;
    @Autowired
    private IGrkhpjsxService iGrkhpjsxService;
    @Autowired
    private IKhhmcxxService iKhhmcxxService;

    @Value(value = "${staticURL}")
    private String staticUrl;

    @Value(value = "${common.path.upload}")
    private String uploadpath;

   /**
    * 分页列表查询
    * @param grdkgl
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "xdgl/grdkgl/GrdkglList")
    public Result<?> queryPageList(Grdkgl grdkgl,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
        QueryWrapper<Grdkgl> queryWrapper = QueryGenerator.initQueryWrapper(grdkgl, req.getParameterMap());
        queryWrapper.orderByDesc("create_time");
        Page<Grdkgl> page = new Page<Grdkgl>(pageNo, pageSize);
        IPage<Grdkgl> pageList = iGrdkglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 新增时获取系统自动生成的户号编码
     * @return
     */
    @RequestMapping(value = "/getHhbm",method = RequestMethod.GET)
    public Result<?> getHhbm() {
        Result<JSONObject> result = new Result<JSONObject>();
        JSONObject jsonObject = new JSONObject();
        try {
            String hhbm = iSysDictService.queryhhbm("SEQ_HHBM_LY.nextval");
            jsonObject.put("hhbm",hhbm);
            result.setSuccess(true);
            return result.ok(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 校验填入的证件号码是否已采集
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "/queryInfoHasBeenCollectedByZjhm",method = RequestMethod.GET)
    public Result<?> queryInfoHasBeenCollectedByZjhm(@RequestParam(name = "zjhm",required = true) String zjhm) {
        Result<?> result = new Result<>();
        try {
            Grdkgl grdkgl = new Grdkgl();
            grdkgl.setZjhm(Base64.decodeStr(zjhm));
            Map<String,String[]> stringMap = new HashMap<>();
            QueryWrapper<Grdkgl> queryWrapper = QueryGenerator.initQueryWrapper(grdkgl, stringMap);
            Page<Grdkgl> page = new Page<Grdkgl>(1,10);
            IPage<Grdkgl> iPage = iGrdkglService.page(page, queryWrapper);
            if (iPage != null && iPage.getSize() > 0) {
                return Result.ok(iPage);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("查询失败!");
        }
        return result;
    }

    /**
     * 新增时，确认输入的证件号码是否已在小额农贷授信采集中录入
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "/QueryHasBeenCollectedByZjhmAtXend",method = RequestMethod.GET)
    public Result<?> QueryHasBeenCollectedByZjhmAtXend(@RequestParam(name = "zjhm",required = true) String zjhm) {
        Result<?> result = new Result<>();
        try {
            Grkhpjsx grkhPjsx = new Grkhpjsx();
            grkhPjsx.setZjhm(Base64.decodeStr(zjhm));
            Map<String,String[]> stringMap = new HashMap<>();
            QueryWrapper<Grkhpjsx> queryWrapper = QueryGenerator.initQueryWrapper(grkhPjsx, stringMap);
            Page<Grkhpjsx> page = new Page<Grkhpjsx>(1,10);
            IPage<Grkhpjsx> iPage = iGrkhpjsxService.page(page,queryWrapper);
            if (iPage != null && iPage.getSize() >0) {
                return Result.ok(iPage);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("查询失败!");
        }
        return result;
    }

    /**
     * 新增时输入的证件号码若已在小额农贷授信采集中录入，则从客户花名册中带回部分基础信息数据
     * @param zjhm
     * @return
     */
    @GetMapping(value = "/GetKhhmcPartialInfoByZjhm")
    public Result<?> GetKhhmcPartialInfoByZjhm(@RequestParam(name = "zjhm",required = true) String zjhm) {
        List<Khhmcxx> khhmcxxList = iKhhmcxxService.GetKhhmcPartialInfoByZjhm(Base64.decodeStr(zjhm));
        return Result.ok(khhmcxxList);
    }

    /**
     * 根据"所属营销单元编号"获取"所属支行"
     * @param ssyxdy
     * @return
     */
    @RequestMapping(value = "/GetSszhBySsyxdybh",method = RequestMethod.GET)
    public Result<?> GetSszhBySsyxdybh(@RequestParam(name = "ssyxdy",required = true) String ssyxdy) {
        Result<JSONObject> result = new Result<>();
        JSONObject jsonObject = new JSONObject();
        try {
            String sszh = iSjyxdyglService.querySszhBySjyxdybh(ssyxdy);
            jsonObject.put("sszh", sszh);
            result.setSuccess(true);
            return result.ok(jsonObject);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("村组信息有误,查询失败!");
        }
        return result;
    }

    /**
     * 移动端:Pad-个人贷款:贷前调查-新增保存
     * @param page
     * @return
     */
    @PostMapping(value = "/AddSaveByGrdkInfomation")
    public Result<?> AddSaveByGrdkInfomation(@RequestBody GrxdInfoPage page) {
        Grdkcjxx grdkcjxx = new Grdkcjxx();
        KhglKhhmcxxGrxd khhmcxxGrxd = new KhglKhhmcxxGrxd();
        BeanUtils.copyProperties(page, grdkcjxx);
        BeanUtils.copyProperties(page, khhmcxxGrxd);
        iGrdkglService.saveMainPad(
                grdkcjxx, khhmcxxGrxd,
                page.getJtcyxxList(), page.getGlqyList(),
                page.getFwxxList(), page.getCfxxList(), page.getClxxList(), page.getQtglzcList(), page.getYhdkList(),
                page.getBzdbList(), page.getDydbList(), page.getZydbList(), page.getXydbList());
        return Result.ok("保存成功");
    }

    /**
     * 移动端:Pad-个人贷款:贷前调查-编辑保存
     * @param page
     * @return
     */
    @PostMapping(value = "/EditSaveByGrdkInfomation")
    public Result<?> EditSaveByGrdkInfomation(@RequestBody GrxdInfoPage page) {
        Grdkcjxx grdkcjxx = new Grdkcjxx();
        KhglKhhmcxxGrxd khglKhhmcxxGrxd = new KhglKhhmcxxGrxd();
        BeanUtils.copyProperties(page, grdkcjxx);
        BeanUtils.copyProperties(page, khglKhhmcxxGrxd);
        iGrdkglService.updateMainPad(
                grdkcjxx, khglKhhmcxxGrxd,
                page.getJtcyxxList(), page.getGlqyList(),
                page.getFwxxList(), page.getCfxxList(), page.getClxxList(), page.getQtglzcList(), page.getYhdkList(),
                page.getBzdbList(), page.getDydbList(), page.getZydbList(), page.getXydbList());
        return Result.ok("保存成功");
    }

    /**
     * 根据"HHBM"提取"与我行业务往来信息"
     * @param hhbm
     * @return
     */
    @GetMapping(value = "/CountYwhywxxDataByHhbm")
    public Result<?> CountYwhywxxDataByHhbm(@RequestParam(name = "hhbm",required = true) String hhbm) {
        try {
            log.info("===CountYwhywxxDataByHhbm == {} ===",hhbm);
            iGrdkglService.CountYwhywxxDataByHhbm(hhbm);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.ok();
    }

    /**
     * 根据"HHBM"获取"与我行业务往来信息'家庭数据汇总'"
     * @param hhbm
     * @return
     */
    @GetMapping(value = "/GetYwhywwlxxJtsjhzByHhbm")
    public Result<?> GetYwhywwlxxJtsjhzByHhbm(@RequestParam(name="hhbm",required=true) String hhbm) {
        List<YwhyewlxxJtsjhz> ywhyewlxxJtsjhzList = iYwhyewlxxJtsjhzService.GetYwhywwlxxJtsjhzByHhbm(hhbm);
        return Result.ok(ywhyewlxxJtsjhzList);
    }

    /**
     * 根据"户号编码"&"ID"获取家庭成员信息
     * @param hhbm
     * @param id
     * @return
     */
    @GetMapping(value = "/queryFamilyListByHhbmAndId")
    public Result<?> queryFamilyListByHhbmAndId(@RequestParam(name="hhbm",required=true) String hhbm,
                                                @RequestParam(name="id",required=true) String id) {
        List<Jtcyxx> jtcyxxList = iJtcyxxService.queryFamilyListByHhbmAndId(hhbm,id);
        return Result.ok(jtcyxxList);
    }

    /**
     * 新增时，若该客户已在小额农贷授信中采集，则通过"HHBM"&"ID"获取"KHGL_KHHMCXX"家庭成员信息
     * @param hhbm
     * @param id
     * @return
     */
    @GetMapping(value = "/getJtcyxxByHmcHhbmAndId")
    public Result<JSONArray> getJtcyxxByHmcHhbmAndId(@RequestParam(name = "hhbm",required = true) String hhbm,
                                                     @RequestParam(name = "id",required = true) String id) {
        Result<JSONArray> result = new Result<>();
        List<Khhmcxx> khhmcxxList = iKhhmcxxService.getJtcyxxByHmcHhbmAndId(hhbm,id);
        JSONArray jsonArray = new JSONArray();
        for(Khhmcxx khhmcxx : khhmcxxList) {
            JSONObject object = new JSONObject();
            object.put("khmc", khhmcxx.getKhmc());
            object.put("zjhm", khhmcxx.getZjhm());
            object.put("yhzgx", khhmcxx.getYhzgx());
            object.put("cszy", khhmcxx.getCszy());
            object.put("sjhm", khhmcxx.getLxfs());
            jsonArray.add(object);
        }
        result.setResult(jsonArray);
        result.setCode(200);
        return result;
    }

    /**
     * 根据"ID"查询关联企业信息
     * @param id
     * @return
     */
    @GetMapping(value = "/queryCompanyListById")
    public Result<?> queryCompanyListById(@RequestParam(name="id",required=true) String id) {
        List<Glqy> glqyList = iGlqyService.queryCompanyListById(id);
        return Result.ok(glqyList);
    }

    /**
     * 根据"ID"获取固定资产信息:房屋信息
     * @param id
     * @return
     */
    @GetMapping(value = "/queryHouseInfoById")
    public Result<?> queryHouseInfoById(@RequestParam(name="id",required=true) String id) {
        List<Fwxx> fwxxList = iFwxxService.queryHouseInfoById(id);
        return Result.ok(fwxxList);
    }

    /**
     * 根据"ID"获取固定资产信息:厂房信息
     * @param id
     * @return
     */
    @GetMapping(value = "/queryFactoryInfoById")
    public Result<?> queryFactoryInfoById(@RequestParam(name="id",required=true) String id) {
        List<Cfxx> cfxxList = iCfxxService.queryFactoryInfoById(id);
        return Result.ok(cfxxList);
    }

    /**
     * 根据"ID"获取固定资产信息:车辆信息
     * @param id
     * @return
     */
    @GetMapping(value = "/queryVehicleInfoById")
    public Result<?> queryVehicleInfoById(@RequestParam(name="id",required=true) String id) {
        List<Clxx> clxxList = iClxxService.queryVehicleInfoById(id);
        return Result.ok(clxxList);
    }

    /**
     * 根据"ID"获取固定资产信息:其它固定资产
     * @param id
     * @return
     */
    @GetMapping(value = "/queryOtherFixedAssetsInfoById")
    public Result<?> queryOtherFixedAssetsInfoById(@RequestParam(name="id",required=true) String id) {
        List<Qtglzc> qtgdzcList = iQtgdzcService.queryOtherFixedAssetsInfoById(id);
        return Result.ok(qtgdzcList);
    }

    /**
     * 根据"ID"获取负债信息:银行贷款信息
     * @param id
     * @return
     */
    @GetMapping(value = "/queryBankLoadInfoById")
    public Result<?> queryBankLoadInfoById(@RequestParam(name="id",required=true) String id) {
        List<Yhdk> yhdkList = iYhdkService.queryBankLoadInfoById(id);
        return Result.ok(yhdkList);
    }

    /**
     * 根据"ID"获取担保方式:保证担保信息
     * @param id
     * @return
     */
    @GetMapping(value = "/queryGuaranteeInfoById")
    public Result<?> queryGuaranteeInfoById(@RequestParam(name = "id",required = true) String id) {
        List<Bzdb> bzdbList = iBzdbService.queryGuaranteeInfoById(id);
        return Result.ok(bzdbList);
    }

    /**
     * 根据"ID"获取担保方式:抵押担保信息
     * @param id
     * @return
     */
    @GetMapping(value = "/queryMortgageGuaranteeInfoById")
    public Result<?> queryMortgageGuaranteeInfoById(@RequestParam(name="id",required=true) String id) {
        List<Dydb> dydbList = iDydbService.queryMortgageGuaranteeInfoById(id);
        return Result.ok(dydbList);
    }

    /**
     * 根据"ID"获取担保方式:质押担保信息
     * @param id
     * @return
     */
    @GetMapping(value = "/queryPledgeGuaranteeInfoById")
    public Result<?> queryPledgeGuaranteeInfoById(@RequestParam(name="id",required=true) String id) {
        List<Zydb> zydbList = iZydbService.queryPledgeGuaranteeInfoById(id);
        return Result.ok(zydbList);
    }

    /**
     * 根据"ID"获取担保方式:信用担保
     * @param id
     * @return
     */
    @GetMapping(value = "/queryCreditGuaranteeInfoById")
    public Result<?> queryCreditGuaranteeInfoById(@RequestParam(name="id",required=true) String id) {
        List<Xydb> xydbList = iXydbService.queryCreditGuaranteeInfoById(id);
        return Result.ok(xydbList);
    }

    /**
     * 根据"ID"获取附件信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryFjxxDataById",method = RequestMethod.GET)
    public Result<?> queryFjxxDataById(@RequestParam(name = "id") String id) {
        try {
            QueryWrapper<Grdkgl> grdkglQueryWrapper = new QueryWrapper<>();
            grdkglQueryWrapper.eq("id", id);
            Grdkgl grdkgl = iGrdkglService.getOne(grdkglQueryWrapper);
            QueryWrapper<Grxdzllb> grxdzllbQueryWrapper = new QueryWrapper<>();
            grxdzllbQueryWrapper.eq("zjhm", grdkgl.getZjhm());
            List<Grxdzllb> grxdzllbList = iGrxdzllbService.list(grxdzllbQueryWrapper);
            if (grxdzllbList != null && grxdzllbList.size() > 0) {
                return Result.ok(grxdzllbList);
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("查询成功!");
    }

    /**
     * 保存附件信息
     * @param grxdzllbs
     * @return
     */
    @RequestMapping(value = "/SaveGrdkFjxx",method = RequestMethod.POST)
    public Result<?> SaveGrdkFjxx(@RequestBody JSONObject jsonObject) {
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String ZJHM = "";
        boolean flag = true;
        if (StringUtils.isEmpty(jsonObject.getString("cjxxId"))) {
            flag = false;
        } else {
            Grdkcjxx form = iGrdkcjxxService.getById(jsonObject.getString("cjxxId"));
            ZJHM = form.getZjhm();
        }
        List<Grxdzllb> grxdzllbs = jsonObject.getJSONArray("FjxxImageList").toJavaList(Grxdzllb.class);
        try {
            if (grxdzllbs != null && grxdzllbs.size() > 0) {
                for (int i = 0; i < grxdzllbs.size(); i++) {
                    if (StringUtils.isEmpty(grxdzllbs.get(i).getZlbh())) {
                        Grxdzllb grxdzllb = new Grxdzllb();
                        grxdzllb.setQydm(grxdzllbs.get(i).getQydm());
                        grxdzllb.setHhbm(grxdzllbs.get(i).getHhbm());
                        if (flag) {
                            grxdzllb.setZjhm(ZJHM);
                        } else {
                            grxdzllb.setZjhm(grxdzllbs.get(i).getZjhm());
                        }
                        grxdzllb.setZlbh(UUIDGenerator.generate());
                        grxdzllb.setZlmc(grxdzllbs.get(i).getZlmc());
                        grxdzllb.setZllx(grxdzllbs.get(i).getZllx());
                        grxdzllb.setZldx(grxdzllbs.get(i).getZldx());
                        grxdzllb.setZllj(uploadpath + "/" + grxdzllbs.get(i).getFwlj());
                        grxdzllb.setFwlj(grxdzllbs.get(i).getFwlj());
                        grxdzllb.setScr(user.getUsername());
                        grxdzllb.setScsj(new Date());
                        grxdzllb.setLrr(user.getUsername());
                        grxdzllb.setLrsj(new Date());
                        iGrxdzllbService.save(grxdzllb);
                    }
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("保存成功!");
    }

    /**
     * 删除附件信息
     * @param grxdzllbs
     * @return
     */
    @RequestMapping(value = "/DeleteGrdkFjxx",method = RequestMethod.POST)
    public Result<?> DeleteGrdkFjxx(@RequestBody List<Grxdzllb> grxdzllbs) {
        try {
            if (grxdzllbs!=null && grxdzllbs.size()>0) {
                for (int i = 0; i < grxdzllbs.size(); i++) {
                    if (!StringUtils.isEmpty(grxdzllbs.get(i).getZlbh())) {
                        UpdateWrapper<Grxdzllb> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.eq("zlbh", grxdzllbs.get(i).getZlbh());
                        iGrxdzllbService.remove(updateWrapper);
                    }
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("删除成功!");
    }

    /**
     * 根据"id"删除家庭成员信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteFamilyInfoById",method = RequestMethod.GET)
    public Result<?> deleteFamilyInfoById(@RequestParam(name = "id",required = true) String id) {
        try {
            QueryWrapper<Jtcyxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            Jtcyxx jtcyxx = iJtcyxxService.getOne(queryWrapper);
            if (jtcyxx != null) {
                // Method1：直接删除记录（暂时不可取）
                // iJtcyxxService.deleteFamilyInfoById(id);
                // Method2：清除户号编码不删除记录
                jtcyxx.setHhbm("");
                iJtcyxxService.updateById(jtcyxx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok("删除成功!");
    }

    /**
     * 根据"id"删除保证担保信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteBzdbxxById",method = RequestMethod.GET)
    public Result<?> deleteBzdbxxById(@RequestParam(name = "id",required = true) String id) {
        try {
            QueryWrapper<Bzdb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            Bzdb check = iBzdbService.getOne(queryWrapper);
            if (check != null) iBzdbService.removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok("删除成功!");
    }

}
