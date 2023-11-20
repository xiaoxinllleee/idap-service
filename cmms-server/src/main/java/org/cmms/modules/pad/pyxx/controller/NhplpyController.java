package org.cmms.modules.pad.pyxx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.Base64Util;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhxxglService;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khxxgl.clkhxx.entity.Clkhgl;
import org.cmms.modules.khxxgl.clkhxx.service.IClkhglService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;
import org.cmms.modules.pad.pyxx.entity.NhplpyWzxx;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
import org.cmms.modules.pad.pyxx.service.INhplpyService;
import org.cmms.modules.pad.pyxx.service.INhplpyWzxxService;
import org.cmms.modules.pad.pyxx.service.IPyfjxxService;
import org.cmms.modules.pad.pyxx.vo.PlpyFjxx;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 农户批量评议
 * @Author: jeecg-boot
 * @Date:   2022-03-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农户批量评议")
@RestController
@RequestMapping("/pad/pyxx/nhplpy")
public class NhplpyController extends JeecgController<Nhplpy, INhplpyService> {
    @Autowired
    private INhplpyService nhplpyService;
    @Autowired
    private IPyfjxxService pyfjxxService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IClkhglService clkhglService;
    @Autowired
    private INhbkbpyService nhbkbpyService;
    @Autowired
    private INhplpyWzxxService nhplpyWzxxService;
    /**
     * 分页列表查询
     *
     * @param nhplpy
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "农户批量评议-分页列表查询")
    @ApiOperation(value="农户批量评议-分页列表查询", notes="农户批量评议-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Nhplpy nhplpy,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Nhplpy> queryWrapper = QueryGenerator.initQueryWrapper(nhplpy, req.getParameterMap());
        Page<Nhplpy> page = new Page<Nhplpy>(pageNo, pageSize);
        IPage<Nhplpy> pageList = nhplpyService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "农户批量评议-添加")
    @ApiOperation(value="农户批量评议-添加", notes="农户批量评议-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JSONObject jsonObject) {
        String pyyid = jsonObject.getString("pyyid");
        String pyyKhid = jsonObject.getString("pyyKhid");
        Nhplpy nhplpy = JSONObject.toJavaObject(jsonObject, Nhplpy.class);
        if(StringUtils.isNotEmpty(pyyid)) {
            //如果id不为空，使用ID查询，因为证件号码做了脱敏
            Nhplpy nhplpyInfo = nhplpyService.getById(pyyid);
            nhplpy.setPyyzjhm(nhplpyInfo.getPyyzjhm());
        } else if(StringUtils.isNotEmpty(pyyKhid)) {
            Nhxq nhxq = nhxqService.getById(pyyKhid);
            nhplpy.setPyyzjhm(nhxq.getZjhm());
        }

        //查询是否当前评议轮数已经存在，防止两人同时评议时造成的重复数据
        QueryWrapper<Nhplpy> nhplpyQueryWrapper = new QueryWrapper<>();
        nhplpyQueryWrapper.eq("pywg", nhplpy.getPywg());
        nhplpyQueryWrapper.eq("pyls", nhplpy.getPyls());
        List<Nhplpy> nhplpyList = nhplpyService.list(nhplpyQueryWrapper);
        if (!nhplpyList.isEmpty()) {
            if (nhplpy.getPyls().equals("-1"))
                return Result.ok();
            return Result.error("评议网格已经存在当前评议轮数的评议信息，请退出页面重新进入");
        }


        if (getRedisQydm().equals(QydmEnums.TIANYI.getQydmCode())){
            String fwlj = "/sign/nhplpy/";
            if (!FileUtil.isDirectory(uploadpath+fwlj)){
                FileUtil.mkdir(uploadpath+fwlj);
            }
            String qz = "data:image/png;base64,";
            if (org.apache.commons.lang3.StringUtils.isNotBlank(nhplpy.getPyygdzqm()) && nhplpy.getPyygdzqm().startsWith(qz)){
                String fileName = IdUtil.fastSimpleUUID() + ".png";
                String wjlj = uploadpath+fwlj+fileName;
                Base64Util.toImage(nhplpy.getPyygdzqm(), wjlj);
                nhplpy.setPyygdzqm(fwlj+fileName);
            }

            if (org.apache.commons.lang3.StringUtils.isNotBlank(nhplpy.getPfygdzqm()) && nhplpy.getPfygdzqm().startsWith(qz)){
                String fileName = IdUtil.fastSimpleUUID() + ".png";
                String wjlj = uploadpath+fwlj+fileName;
                Base64Util.toImage(nhplpy.getPfygdzqm(), wjlj);
                nhplpy.setPfygdzqm(fwlj+fileName);
            }
        }
        nhplpyService.save(nhplpy);

        //保存位置信息
        String wzxx = jsonObject.getString("wzxx");
        if (StringUtils.isNotEmpty(wzxx)) {
            NhplpyWzxx nhplpyWzxx = new NhplpyWzxx();
            nhplpyWzxx.setNhplpyId(nhplpy.getId());
            nhplpyWzxx.setPyrq(DateUtil.beginOfDay(new Date()));
            nhplpyWzxx.setWzxx(wzxx);
            nhplpyWzxxService.save(nhplpyWzxx);
        }
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "农户批量评议-编辑")
    @ApiOperation(value="农户批量评议-编辑", notes="农户批量评议-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JSONObject jsonObject) {
        Nhplpy nhplpy = JSONObject.toJavaObject(jsonObject, Nhplpy.class);
        //需要处理base64
        if (getRedisQydm().equals(QydmEnums.TIANYI.getQydmCode())){
            String fwlj = "/sign/nhplpy/";
            if (!FileUtil.isDirectory(uploadpath+fwlj)){
                FileUtil.mkdir(uploadpath+fwlj);
            }
            String qz = "data:image/png;base64,";
            if (org.apache.commons.lang3.StringUtils.isNotBlank(nhplpy.getPyygdzqm())){
                if (nhplpy.getPyygdzqm().startsWith(qz)){
                    String fileName = IdUtil.fastSimpleUUID() + ".png";
                    String wjlj = uploadpath+fwlj+fileName;
                    Base64Util.toImage(nhplpy.getPyygdzqm(), wjlj);
                    nhplpy.setPyygdzqm(fwlj+fileName);
                }else {
                    nhplpy.setPyygdzqm(null);
                }

            }

            if (org.apache.commons.lang3.StringUtils.isNotBlank(nhplpy.getPfygdzqm())){
                if (nhplpy.getPfygdzqm().startsWith(qz)){
                    String fileName = IdUtil.fastSimpleUUID() + ".png";
                    String wjlj = uploadpath+fwlj+fileName;
                    Base64Util.toImage(nhplpy.getPfygdzqm(), wjlj);
                    nhplpy.setPfygdzqm(fwlj+fileName);
                }else {
                    nhplpy.setPfygdzqm(null);
                }

            }
            nhplpyService.updateById(nhplpy);
//			if (StringUtils.isNotBlank(nhplpy.getPywg())){
//				 String pywg = nhplpy.getPywg();
//				 if (pywg.contains(",")){
//					 String[] split = pywg.split(",");
//					 for (int i = 0; i < split.length; i++) {
//						 //先删除旧的
//						 LambdaQueryWrapper<Nhplpy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//						 lambdaQueryWrapper.eq(Nhplpy::getPywg,split[i]);
//						 lambdaQueryWrapper.eq(Nhplpy::getPyls,nhplpy.getPyls());
//						 service.remove(lambdaQueryWrapper);
//						 nhplpy.setPywg(split[i]);
//						 nhplpy.setId(IdUtil.fastSimpleUUID());
//						 service.save(nhplpy);
//					 }
//
//				 }else {
//					 nhplpyService.updateById(nhplpy);
//				 }
//			}
        }else {
            nhplpyService.updateById(nhplpy);
        }
        //保存位置信息
        String wzxx = jsonObject.getString("wzxx");
        if (StringUtils.isNotEmpty(wzxx)) {
            //查询当天是否有位置信息
            QueryWrapper<NhplpyWzxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("nhplpy_id", nhplpy.getId());
            queryWrapper.eq("pyrq", DateUtil.beginOfDay(new Date()));
            List<NhplpyWzxx> wzxxList = nhplpyWzxxService.list(queryWrapper);
            if (wzxxList.isEmpty()) {
                NhplpyWzxx nhplpyWzxx = new NhplpyWzxx();
                nhplpyWzxx.setNhplpyId(nhplpy.getId());
                nhplpyWzxx.setPyrq(DateUtil.beginOfDay(new Date()));
                nhplpyWzxx.setWzxx(wzxx);
                nhplpyWzxxService.save(nhplpyWzxx);
            } else {
                UpdateWrapper<NhplpyWzxx> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", wzxxList.get(0).getId());
                NhplpyWzxx update = new NhplpyWzxx();
                update.setWzxx(wzxx);
                nhplpyWzxxService.update(update, updateWrapper);
            }
        }
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户批量评议-通过id删除")
    @ApiOperation(value="农户批量评议-通过id删除", notes="农户批量评议-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        nhplpyService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "农户批量评议-批量删除")
    @ApiOperation(value="农户批量评议-批量删除", notes="农户批量评议-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.nhplpyService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户批量评议-通过id查询")
    @ApiOperation(value="农户批量评议-通过id查询", notes="农户批量评议-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        Nhplpy nhplpy = nhplpyService.getById(id);
        return Result.ok(nhplpy);
    }

    /**
     * 通过pywg查询
     *
     * @param pywg
     * @return
     */
    @AutoLog(value = "农户批量评议-通过pywg查询")
    @ApiOperation(value="农户批量评议-通过pywg查询", notes="农户批量评议-通过pywg查询")
    @GetMapping(value = "/queryByPywg")
    public Result<?> queryByPywg(@RequestParam(name="pywg",required=true) String pywg) {
        QueryWrapper<Nhplpy> nhplpyQueryWrapper = new QueryWrapper<>();
        nhplpyQueryWrapper.eq("pywg", pywg);
//		 nhplpyQueryWrapper.orderByDesc("pyls");
        nhplpyQueryWrapper.last(" order by pyls * 1 desc");
        List<Nhplpy> nhplpyList = nhplpyService.list(nhplpyQueryWrapper);
        return Result.ok(nhplpyList);
    }

    /**
     * 新化:一个客户经理只能用一个评议员信息去评议一个村
     */
    @PostMapping("/getOneInfo")
    public Result<?> getOneInfo(@RequestBody JSONObject jsonObject) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String pyyid = jsonObject.getString("pyyid");

        QueryWrapper<Nhplpy> nhplpyQueryWrapper = new QueryWrapper<>();
        nhplpyQueryWrapper.eq("CREATE_BY", loginUser.getUsername()).eq("PYWG", jsonObject.getString("pywg"));
        List<Nhplpy> list = nhplpyService.list(nhplpyQueryWrapper);
        if (list != null && !list.isEmpty()) {
            List<Nhplpy> nhplpyList;
            if (StringUtils.isNotEmpty(pyyid)) {
                nhplpyList = list.stream().filter(item -> item.getPyyzjhm().equals(nhplpyService.getById(pyyid).getPyyzjhm())).collect(Collectors.toList());
            } else {
                nhplpyList = list.stream().filter(item -> item.getPyyzjhm().equals(jsonObject.getString("pyyzjhm"))).collect(Collectors.toList());
            }
            if (!nhplpyList.isEmpty()) {
                return Result.ok();
            } else {
                Date date = Collections.min(list.stream().map(Nhplpy::getCreateTime).collect(Collectors.toList()));
                List<Nhplpy> list2 = list.stream().filter(item -> item.getCreateTime().equals(date)).collect(Collectors.toList());
                return Result.ok(list2.get(0));
            }
        } else {
            return Result.ok();
        }
    }

    /**
     * 通过zjhm查询
     * 证件号经过BASE64加密
     *
     * @param pyyid
     * @param zjhmEncode
     * @return
     */
    @ApiOperation(value="农户批量评议-通过zjhm查询", notes="农户批量评议-通过zjhm查询")
    @GetMapping(value = "/queryByZjhm")
    public Result<?> queryByZjhm(@RequestParam(name="pyyid", required = false) String pyyid,
                                 @RequestParam(name="pyyKhid", required = false) String pyyKhid,
                                 @RequestParam(name="zjhm", required=false) String zjhmEncode,
                                 @RequestParam(name="pywg") String pywg) {
        if(StringUtils.isNotEmpty(pyyid)) {
            //如果id不为空，使用ID查询，因为证件号码做了脱敏
            Nhplpy nhplpy = nhplpyService.getById(pyyid);
            return Result.ok(nhplpy);
        } else if(StringUtils.isNotEmpty(pyyKhid)) {
            Nhxq nhxq = nhxqService.getById(pyyKhid);
            QueryWrapper<Nhplpy> nhplpyQueryWrapper = new QueryWrapper<>();
            nhplpyQueryWrapper.eq("pywg", pywg);
            nhplpyQueryWrapper.eq("pyyzjhm", nhxq.getZjhm());
            Nhplpy nhplpy = nhplpyService.getOne(nhplpyQueryWrapper);
            return Result.ok(nhplpy);
        }
        else {
            String zjhm = Base64.decodeStr(zjhmEncode);
            QueryWrapper<Nhplpy> nhplpyQueryWrapper = new QueryWrapper<>();
            nhplpyQueryWrapper.eq("pywg", pywg);
            nhplpyQueryWrapper.eq("pyyzjhm", zjhm);
            Nhplpy nhplpy = nhplpyService.getOne(nhplpyQueryWrapper);
            return Result.ok(nhplpy);
        }
    }

    @GetMapping(value = "/queryPlpyPyyxx")
    public Result<?> queryPlpyPyyxx(String pywg) {
        QueryWrapper<Nhplpy> pyxxQueryWrapper = new QueryWrapper<Nhplpy>();
        if(StringUtils.isNotEmpty(pywg)) {
            pyxxQueryWrapper.eq("pywg", pywg);
        }
        List<Nhplpy> list = nhplpyService.list(pyxxQueryWrapper);
        return Result.ok(list);
    }

    @RequestMapping(value = "/queryFjxx",method = RequestMethod.GET)
    public Result<?> queryPyFjxx(Pyfjxx jsonObject) {
        try {
            QueryWrapper<Nhplpy> pyxxQueryWrapper = new QueryWrapper<Nhplpy>();
            pyxxQueryWrapper.eq("id",jsonObject.getId());
            Nhplpy nhbkbpy = nhplpyService.getOne(pyxxQueryWrapper);
            QueryWrapper<Pyfjxx> fjxxQueryWrapper=new QueryWrapper<>();
            if (QydmEnums.TIANYI.getQydmCode().equals(getRedisQydm())) {
                fjxxQueryWrapper.eq("pylx", jsonObject.getPylx())
                        .eq("pyyzjhm", nhbkbpy.getPyyzjhm())
                        .eq("qydm", nhbkbpy.getPywg())
                        .isNull("zjhm")
                        .orderByAsc("CREATE_TIME");
            }else{
                fjxxQueryWrapper.eq("pylx",jsonObject.getPylx())
                        .eq("zllx",jsonObject.getZllx())
                        .eq("pyyzjhm",nhbkbpy.getPyyzjhm())
                        .isNull("zjhm")
                        .orderByAsc("CREATE_TIME");
            }
            List<Pyfjxx> list = pyfjxxService.list(fjxxQueryWrapper);
            if (list!=null && list.size()>0){
                return Result.ok(list);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询评议附件失败！");
            return  Result.error("查询评议附件失败！");
        }
        return Result.ok("查询成功");
    }

    @RequestMapping(value = "/saveFjxx",method = RequestMethod.POST)
    public Result<?> saveFjxx(@RequestBody PlpyFjxx plpyFjxx) {
        if (StringUtils.isNotEmpty(plpyFjxx.getPyyid())) {
            //ID不为空，则为选择已有的评议员信息
            //根据评议员ID查询评议员信息
            Nhplpy nhplpy = nhplpyService.getById(plpyFjxx.getPyyid());
            plpyFjxx.setPyyzjhm(nhplpy.getPyyzjhm());
        } else if(StringUtils.isNotEmpty(plpyFjxx.getPyyKhid())) {
            //评议员客户ID不为空，则为选择客户信息
            Nhxq nhxq = nhxqService.getById(plpyFjxx.getPyyKhid());
            plpyFjxx.setPyyzjhm(nhxq.getZjhm());
        } else {
            //手动输入 不作处理
        }

        if(plpyFjxx.getUploadFiles() != null && !plpyFjxx.getUploadFiles().isEmpty()) {
            //通过id查询授信对象证件号码
            String khid = plpyFjxx.getSxdxid();
            Nhxq khhmcxx = null;
            if (StringUtils.isNotEmpty(khid)) {
                QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", khid);
                khhmcxx = nhxqService.getOne(queryWrapper);
            }

            List<Pyfjxx> uploadFiles = new ArrayList<>();
            for (int i = 0; i < plpyFjxx.getUploadFiles().size(); i++) {
                Pyfjxx pyfjxx = plpyFjxx.getUploadFiles().get(i);
                if(StringUtils.isNotEmpty(pyfjxx.getId())) {
                    continue;
                }
                Pyfjxx fjgl = new Pyfjxx();
                fjgl.setPyyzjhm(plpyFjxx.getPyyzjhm());
                fjgl.setQydm(pyfjxx.getQydm());
                fjgl.setHhbm(pyfjxx.getHhbm());
                if (khhmcxx != null) {
                    fjgl.setZjhm(khhmcxx.getZjhm());
                }
                fjgl.setPylx(pyfjxx.getPylx());
                fjgl.setZllx(pyfjxx.getZllx());
                fjgl.setZldx(pyfjxx.getZldx());
                fjgl.setFwlj(pyfjxx.getFwlj());
                fjgl.setZlmc(pyfjxx.getZlmc());
                fjgl.setZllj(uploadpath+"/"+pyfjxx.getFwlj());
                uploadFiles.add(fjgl);
            }
            pyfjxxService.saveBatch(uploadFiles);
        }

        if(plpyFjxx.getDeleteFiles() != null && !plpyFjxx.getDeleteFiles().isEmpty()) {
            List<String> ids = plpyFjxx.getDeleteFiles().stream().filter(fjxx -> StringUtils.isNotEmpty(fjxx.getId())).map(Pyfjxx::getId).collect(Collectors.toList());
            if (ids != null && !ids.isEmpty()) {
                pyfjxxService.removeByIds(ids);
            }
        }
        return Result.ok("附件处理成功");
    }

    @RequestMapping(value = "/getSjhm",method = RequestMethod.GET)
    public Result<?> getSjhm(String khId) {
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",khId);
        Nhxq nhxq = nhxqService.getOne(queryWrapper,false);
        if (nhxq != null) {
            QueryWrapper<Clkhgl> clkhglQueryWrapper = new QueryWrapper<Clkhgl>();
            clkhglQueryWrapper.eq("zjhm", nhxq.getZjhm());
            List<Clkhgl> clkhglList = clkhglService.list(clkhglQueryWrapper);
            if (!clkhglList.isEmpty()) {
                jsonObject.put("sjhmXf", clkhglList.get(0).getLxfs());
            }
            jsonObject.put("sjhmImport", nhxq.getSjhmImport());
            jsonObject.put("sjhm", nhxq.getSjhm());
        } else {
            // 天易 工作平台/我的评议/授信对象:基本信息评议/评议详情 最新手机号码获取
            QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<>();
            nhbkbpyQueryWrapper.eq("id",khId);
            Nhbkbpy nhbkbpy = nhbkbpyService.getOne(nhbkbpyQueryWrapper,false);
            if (nhbkbpy != null) {
                queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm",nhbkbpy.getZjhm());
                nhxq = nhxqService.getOne(queryWrapper,false);
                if (nhxq != null) {
                    jsonObject.put("sjhm", nhxq.getSjhm());
                    jsonObject.put("sjhmImport", nhxq.getSjhmImport());
                    QueryWrapper<Clkhgl> clkhglQueryWrapper = new QueryWrapper<Clkhgl>();
                    clkhglQueryWrapper.eq("zjhm", nhbkbpy.getZjhm());
                    List<Clkhgl> clkhglList = clkhglService.list(clkhglQueryWrapper);
                    if (!clkhglList.isEmpty()) {
                        jsonObject.put("sjhmXf", clkhglList.get(0).getLxfs());
                    }
                }
            }
        }
        return Result.ok(jsonObject);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param nhplpy
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Nhplpy nhplpy) {
        return super.exportXls(request, nhplpy, Nhplpy.class, "农户批量评议");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Nhplpy.class);
    }

    @RequestMapping("/jspy")
    public Result<?> jspy(String pywg,Integer pyls,String pyyzjhm,String id, String pyyKhid){
        if (org.apache.commons.lang3.StringUtils.isBlank(id)){
            if (StringUtils.isNotEmpty(pyyKhid)) {
                Nhxq nhxq = nhxqService.getById(pyyKhid);
                pyyzjhm = nhxq.getZjhm();
            }
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("pyyzjhm", pyyzjhm);
            queryWrapper.eq("pyls", pyls);
            queryWrapper.eq("pywg", pywg);
            List<Nhplpy> list = service.list(queryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                Nhplpy nhplpy = list.get(0);
                id = nhplpy.getId();
            }

        }
        if (StringUtils.isNotEmpty(id)) {
            Nhplpy nhplpy = new Nhplpy();
            nhplpy.setId(id);
            nhplpy.setSfjspy("1");
            service.updateById(nhplpy);
        }
        return Result.ok();
    }

    @GetMapping("/queryWzxx")
    public Result<?> queryWzxx(String nhplpyId) {
        //查询有没有当天的位置信息
        QueryWrapper<NhplpyWzxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nhplpy_id", nhplpyId);
        queryWrapper.eq("pyrq", DateUtil.beginOfDay(new Date()));
        List<NhplpyWzxx> wzxxList = nhplpyWzxxService.list(queryWrapper);
        if (wzxxList.isEmpty()) {
            return Result.ok("未找到位置信息");
        } else {
            return Result.ok(wzxxList.get(0));
        }
    }
}
