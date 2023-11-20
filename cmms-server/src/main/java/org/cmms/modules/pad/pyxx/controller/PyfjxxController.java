package org.cmms.modules.pad.pyxx.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhxxgl.entity.CamsZcsxNhjbxxPad;
import org.cmms.modules.pad.nhxxgl.entity.FjxxRecive;
import org.cmms.modules.pad.nhxxgl.entity.KhglKhhmcxx;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.service.IKhglKhhmcxxPadService;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
import org.cmms.modules.pad.pyxx.entity.Pyfjxxfictitious;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import org.cmms.modules.pad.pyxx.service.INhplpyService;
import org.cmms.modules.pad.pyxx.service.IPyfjxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.pyxx.service.IPyyxxService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 评议附件信息
 * @Author: jeecg-boot
 * @Date: 2020-07-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "评议附件信息")
@RestController
@RequestMapping("/pad/pyxx/pyfjxx")
public class PyfjxxController extends JeecgController<Pyfjxx, IPyfjxxService> {
    @Autowired
    private IPyfjxxService pyfjxxService;

    @Autowired
    private INhbkbpyService iNhbkbpyService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;

    @Value(value = "${common.path.upload}")
    private String uploadpath;

    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private INhplpyService nhplpyService;
    @Autowired
    private IPyyxxService iPyyxxService;
    @Autowired
    IYxdyglMainService yxdyglMainService;

    /**
     * 分页列表查询
     *
     * @param pyfjxx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "评议附件信息-分页列表查询")
    @ApiOperation(value = "评议附件信息-分页列表查询", notes = "评议附件信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Pyfjxx pyfjxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Pyfjxx> queryWrapper = QueryGenerator.initQueryWrapper(pyfjxx, req.getParameterMap());
        Page<Pyfjxx> page = new Page<Pyfjxx>(pageNo, pageSize);
        IPage<Pyfjxx> pageList = pyfjxxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param pyfjxx
     * @return
     */
    @AutoLog(value = "评议附件信息-添加")
    @ApiOperation(value = "评议附件信息-添加", notes = "评议附件信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Pyfjxx pyfjxx) {
        pyfjxxService.save(pyfjxx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param pyfjxx
     * @return
     */
    @AutoLog(value = "评议附件信息-编辑")
    @ApiOperation(value = "评议附件信息-编辑", notes = "评议附件信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Pyfjxx pyfjxx) {
        pyfjxxService.updateById(pyfjxx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评议附件信息-通过id删除")
    @ApiOperation(value = "评议附件信息-通过id删除", notes = "评议附件信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        pyfjxxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "评议附件信息-批量删除")
    @ApiOperation(value = "评议附件信息-批量删除", notes = "评议附件信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.pyfjxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评议附件信息-通过id查询")
    @ApiOperation(value = "评议附件信息-通过id查询", notes = "评议附件信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Pyfjxx pyfjxx = pyfjxxService.getById(id);
        return Result.ok(pyfjxx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param pyfjxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Pyfjxx pyfjxx) {
        return super.exportXls(request, pyfjxx, Pyfjxx.class, "评议附件信息");
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
        return super.importExcel(request, response, Pyfjxx.class);
    }


    @GetMapping(value = "/DeleteImage")
    public Result<?> DeleteImage(@Param("zlbh") String zlbh) {
        try {
            pyfjxxService.remove(new UpdateWrapper<Pyfjxx>().eq("zlbh", zlbh));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除评议附件失败", e);
            return Result.error("删除评议附件失败！");
        }
        return Result.ok("删除成功");
    }

    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public Result<?> saveImage(Pyfjxxfictitious jsonObject) {
        try {
            if (StringUtils.isEmpty(jsonObject.getId()) && StringUtils.isEmpty(jsonObject.getPyid())) {
                if (!StringUtils.isEmpty(jsonObject.getPyyid())) {
                    //ID不为空，则为选择已有的评议员信息
                    //根据评议员ID查询评议员信息
                    QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("pyyid", jsonObject.getPyyid());
                    Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                    jsonObject.setPyyzjhm(pyyxx.getPyyzjhm());
                }
                //2020/12/14 周全
				 /*QueryWrapper<KhglNhhzxxgl> queryWrapper = new QueryWrapper<>();
				 queryWrapper.eq("id",jsonObject.getHzid());
				 KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(queryWrapper);*/

                QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", jsonObject.getZjhm());
                Nhxq khhmcxx = nhxqService.getOne(queryWrapper);

                Pyfjxx fjgl = new Pyfjxx();
                fjgl.setPyyzjhm(jsonObject.getPyyzjhm());
                fjgl.setQydm(jsonObject.getQydm());
                fjgl.setHhbm(jsonObject.getHhbm());
                fjgl.setZjhm(khhmcxx.getZjhm());
                //fjgl.setId(jsonObject.getId());
                fjgl.setPylx(jsonObject.getPylx());
                fjgl.setZllx(jsonObject.getZllx());
                fjgl.setZldx(jsonObject.getZldx());
                fjgl.setFwlj(jsonObject.getFwlj());
                fjgl.setZlmc(jsonObject.getZlmc());
                fjgl.setZllj(uploadpath + "/" + jsonObject.getFwlj());
                pyfjxxService.save(fjgl);
            } else {
                if (StringUtils.isEmpty(jsonObject.getId())) {
                    QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("id", jsonObject.getPyid());
                    Nhbkbpy nhbkbpy = iNhbkbpyService.getOne(queryWrapper);
                    Pyfjxx fjgl = new Pyfjxx();
                    fjgl.setPyyzjhm(nhbkbpy.getPyyzjhm());
                    fjgl.setQydm(jsonObject.getQydm());
                    fjgl.setHhbm(jsonObject.getHhbm());
                    fjgl.setZjhm(nhbkbpy.getZjhm());
                    //fjgl.setId(jsonObject.getId());
                    fjgl.setPylx(jsonObject.getPylx());
                    fjgl.setZllx(jsonObject.getZllx());
                    fjgl.setZldx(jsonObject.getZldx());
                    fjgl.setFwlj(jsonObject.getFwlj());
                    fjgl.setZlmc(jsonObject.getZlmc());
                    fjgl.setZllj(uploadpath + "/" + jsonObject.getFwlj());
                    pyfjxxService.save(fjgl);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传评议附件失败", e);
            return Result.error("上传评议附件失败！");
        }
        return Result.ok("添加成功");
    }


    //祁阳特殊处理
    @RequestMapping(value = "/saveQyImage", method = RequestMethod.POST)
    public Result<?> saveQyImage(Pyfjxxfictitious jsonObject) {
        try {
            if (StringUtils.isEmpty(jsonObject.getId()) && StringUtils.isEmpty(jsonObject.getPyid())) {
                if (!StringUtils.isEmpty(jsonObject.getPyyid())) {
                    //ID不为空，则为选择已有的评议员信息
                    //根据评议员ID查询评议员信息
                    QueryWrapper<Pyyxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("pyyid", jsonObject.getPyyid());
                    Pyyxx pyyxx = iPyyxxService.getOne(queryWrapper);
                    jsonObject.setPyyzjhm(pyyxx.getPyyzjhm());
                }
                //通过id查询授信对象证件号码
                QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", jsonObject.getZjhm());
                Nhxq khhmcxx = nhxqService.getOne(queryWrapper);

                Pyfjxx fjgl = new Pyfjxx();
                fjgl.setPyyzjhm(jsonObject.getPyyzjhm());
                fjgl.setQydm(jsonObject.getQydm());
                fjgl.setHhbm(jsonObject.getHhbm());
                fjgl.setZjhm(khhmcxx.getZjhm());
                //fjgl.setId(jsonObject.getId());
                fjgl.setPylx(jsonObject.getPylx());
                fjgl.setZllx(jsonObject.getZllx());
                fjgl.setZldx(jsonObject.getZldx());
                fjgl.setFwlj(jsonObject.getFwlj());
                fjgl.setZlmc(jsonObject.getZlmc());
                fjgl.setZllj(uploadpath + "/" + jsonObject.getFwlj());
                pyfjxxService.save(fjgl);
            } else {
                if (StringUtils.isEmpty(jsonObject.getId())) {
                    QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("id", jsonObject.getPyid());
                    Nhbkbpy nhbkbpy = iNhbkbpyService.getOne(queryWrapper);
                    Pyfjxx fjgl = new Pyfjxx();
                    fjgl.setPyyzjhm(nhbkbpy.getPyyzjhm());
                    fjgl.setQydm(jsonObject.getQydm());
                    fjgl.setHhbm(jsonObject.getHhbm());
                    fjgl.setZjhm(nhbkbpy.getZjhm());
                    //fjgl.setId(jsonObject.getId());
                    fjgl.setPylx(jsonObject.getPylx());
                    fjgl.setZllx(jsonObject.getZllx());
                    fjgl.setZldx(jsonObject.getZldx());
                    fjgl.setFwlj(jsonObject.getFwlj());
                    fjgl.setZlmc(jsonObject.getZlmc());
                    fjgl.setZllj(uploadpath + "/" + jsonObject.getFwlj());
                    pyfjxxService.save(fjgl);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }

    @RequestMapping(value = "/saveQyImage2", method = RequestMethod.POST)
    public Result<?> saveQyImage2(Pyfjxx jsonObject) {
        //zjhm通过sxdxid取 pyy信息通过pyyid去去
        String zjhm = null;
        String pyyzjhm = null;
        if (StringUtils.isNotBlank(jsonObject.getZjhm())) {
            Nhxq byId = nhxqService.getById(jsonObject.getZjhm());
            if (byId != null && StringUtils.isNotBlank(byId.getZjhm())) {
                zjhm = byId.getZjhm();
            }
        }
        if (StringUtils.isNotBlank(jsonObject.getPyyzjhm())) {
            if (IdcardUtil.isValidCard(jsonObject.getPyyzjhm())) {
                pyyzjhm = jsonObject.getPyyzjhm();
            } else {
                Nhplpy byId = nhplpyService.getById(jsonObject.getPyyzjhm());
                if (byId != null && StringUtils.isNotBlank(byId.getPyyzjhm())) {
                    pyyzjhm = byId.getPyyzjhm();
                }
            }
        }
        if (StringUtils.isNotBlank(jsonObject.getFwlj())) {
            LambdaQueryWrapper<Pyfjxx> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(Pyfjxx::getQydm, jsonObject.getQydm());
            queryWrapper.eq(Pyfjxx::getHhbm, jsonObject.getHhbm());
            queryWrapper.eq(Pyfjxx::getZjhm, jsonObject.getZjhm());
            queryWrapper.eq(Pyfjxx::getPyyzjhm, jsonObject.getPyyzjhm());
            queryWrapper.eq(Pyfjxx::getPylx, "5");
            service.remove(queryWrapper);

            String fwlj = jsonObject.getFwlj();
            String zllx = jsonObject.getZllx();
            String zlmc = jsonObject.getZlmc();
            String[] split = fwlj.split(",");
            String[] split1 = zllx.split(",");
            String[] split2 = zlmc.split(",");
            for (int i = 0; i < split.length; i++) {
                Pyfjxx pyfjxx = new Pyfjxx();
                pyfjxx.setHhbm(jsonObject.getHhbm());
                pyfjxx.setQydm(jsonObject.getQydm());
                pyfjxx.setZjhm(zjhm);
                pyfjxx.setPyyzjhm(pyyzjhm);
                pyfjxx.setPylx("5");
                pyfjxx.setZllx(split1[i]);
                pyfjxx.setZlmc(split2[i]);
                pyfjxx.setFwlj(split[i]);
                pyfjxx.setZllj(uploadpath + split[i]);
                service.save(pyfjxx);
            }
        }
        return Result.ok("添加成功");
    }

    @RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
    public Result<?> deleteImage(Pyfjxx jsonObject) {
        try {
            if (!StringUtils.isEmpty(jsonObject.getId())) {
                pyfjxxService.removeById(jsonObject.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除评议附件失败", e);
            return Result.error("删除评议附件失败！");
        }
        return Result.ok("删除成功");
    }


    @RequestMapping(value = "/queryPyFjxx", method = RequestMethod.GET)
    public Result<?> queryPyFjxx(Pyfjxx jsonObject) {
        try {
            QueryWrapper<Nhbkbpy> pyxxQueryWrapper = new QueryWrapper<Nhbkbpy>();
            pyxxQueryWrapper.eq("id", jsonObject.getId());
            Nhbkbpy nhbkbpy = iNhbkbpyService.getOne(pyxxQueryWrapper);
            QueryWrapper<Pyfjxx> fjxxQueryWrapper = new QueryWrapper<>();
            fjxxQueryWrapper.eq("hhbm", nhbkbpy.getHhbm())
                    .eq("zjhm", nhbkbpy.getZjhm())
                    .eq("pylx", jsonObject.getPylx())
                    .eq("zllx", jsonObject.getZllx())
                    .eq("pyyzjhm", nhbkbpy.getPyyzjhm())
                    .orderByAsc("CREATE_TIME");
            List<Pyfjxx> list = pyfjxxService.list(fjxxQueryWrapper);
            //天易查询批量评议评议员的附件
            if (QydmEnums.TIANYI.getQydmCode().equals(getRedisQydm())) {
                //查询村
                YxdyglMain yxdyglMain = yxdyglMainService.getById(nhbkbpy.getQydm());
                fjxxQueryWrapper = new QueryWrapper<>();
                if ("11".equals(jsonObject.getPylx())) {
                    fjxxQueryWrapper.eq("pylx", jsonObject.getPylx())
                            .eq("qydm", nhbkbpy.getQydm())
                            .orderByAsc("CREATE_TIME");
                }else if("12".equals(jsonObject.getPylx())){
                    fjxxQueryWrapper.in("pylx","12")
                            .eq("qydm", yxdyglMain.getParentId())
                            .orderByAsc("CREATE_TIME");
                    List<Pyfjxx> list1=pyfjxxService.list(fjxxQueryWrapper);
                    fjxxQueryWrapper = new QueryWrapper<>();
                    fjxxQueryWrapper.eq("pylx", "10")
                            .eq("pyyzjhm", nhbkbpy.getPyyzjhm())
                            .eq("qydm", yxdyglMain.getParentId())
                            .isNull("zjhm")
                            .orderByAsc("CREATE_TIME");
                    List<Pyfjxx> list2=pyfjxxService.list(fjxxQueryWrapper);
                    list.addAll(list1);
                    list.addAll(list2);
                    return Result.ok(list);
                }else {
                    fjxxQueryWrapper.eq("pylx", jsonObject.getPylx())
                            .eq("pyyzjhm", nhbkbpy.getPyyzjhm())
                            .eq("qydm", yxdyglMain.getParentId())
                            .isNull("zjhm")
                            .orderByAsc("CREATE_TIME");
                }
                List<Pyfjxx> list1 = pyfjxxService.list(fjxxQueryWrapper);
                list.addAll(list1);
                return Result.ok(list);
            }
            if (list != null && list.size() > 0) {
                return Result.ok(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询评议附件失败！");
            return Result.error("查询评议附件失败！");
        }
        return Result.ok("查询成功");
    }

}
