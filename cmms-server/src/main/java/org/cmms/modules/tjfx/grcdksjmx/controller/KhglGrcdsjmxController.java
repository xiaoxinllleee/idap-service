package org.cmms.modules.tjfx.grcdksjmx.controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.SshUtil;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.tjfx.grcdksjmx.entity.KhglGrcdsjmx;
import org.cmms.modules.tjfx.grcdksjmx.service.IKhglGrcdsjmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.util.EtlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 个人存贷款数据明细
 * @Author: jeecg-boot
 * @Date: 2020-11-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "个人存贷款数据明细")
@RestController
@RequestMapping("/tjfx.grcdksjmx/khglGrcdsjmx")
public class KhglGrcdsjmxController extends JeecgController<KhglGrcdsjmx, IKhglGrcdsjmxService> {
    @Autowired
    private IKhglGrcdsjmxService khglGrcdsjmxService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private INhxqService nhxqService;

    /**
     * 分页列表查询
     *
     * @param khglGrcdsjmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "个人存贷款数据明细-分页列表查询")
    @ApiOperation(value = "个人存贷款数据明细-分页列表查询", notes = "个人存贷款数据明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(KhglGrcdsjmx khglGrcdsjmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<KhglGrcdsjmx> queryWrapper = QueryGenerator.initQueryWrapper(khglGrcdsjmx, req.getParameterMap());
        Page<KhglGrcdsjmx> page = new Page<KhglGrcdsjmx>(pageNo, pageSize);
        IPage<KhglGrcdsjmx> pageList = khglGrcdsjmxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param khglGrcdsjmx
     * @return
     */
    @AutoLog(value = "个人存贷款数据明细-添加")
    @ApiOperation(value = "个人存贷款数据明细-添加", notes = "个人存贷款数据明细-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhglGrcdsjmx khglGrcdsjmx) {
        khglGrcdsjmxService.save(khglGrcdsjmx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khglGrcdsjmx
     * @return
     */
    @AutoLog(value = "个人存贷款数据明细-编辑")
    @ApiOperation(value = "个人存贷款数据明细-编辑", notes = "个人存贷款数据明细-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhglGrcdsjmx khglGrcdsjmx) {
        khglGrcdsjmxService.updateById(khglGrcdsjmx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "个人存贷款数据明细-通过id删除")
    @ApiOperation(value = "个人存贷款数据明细-通过id删除", notes = "个人存贷款数据明细-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khglGrcdsjmxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "个人存贷款数据明细-批量删除")
    @ApiOperation(value = "个人存贷款数据明细-批量删除", notes = "个人存贷款数据明细-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khglGrcdsjmxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "个人存贷款数据明细-通过id查询")
    @ApiOperation(value = "个人存贷款数据明细-通过id查询", notes = "个人存贷款数据明细-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhglGrcdsjmx khglGrcdsjmx = khglGrcdsjmxService.getById(id);
        return Result.ok(khglGrcdsjmx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khglGrcdsjmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, KhglGrcdsjmx khglGrcdsjmx) {
        return super.exportXls(request, khglGrcdsjmx, KhglGrcdsjmx.class, "个人存贷款数据明细");
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
        return super.importExcel(request, response, KhglGrcdsjmx.class);
    }


    /**
     * 存贷款余额趋势图（一年内）
     *
     * @param zjhm
     * @return
     */
    @RequestMapping(value = "/getzhjynck", method = RequestMethod.GET)
    public Result<?> getzhjynck(@RequestParam(name = "zjhm", required = true) String zjhm) {
        JSONArray json = new JSONArray();
        JSONArray dkjson = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            List<Map> list = khglGrcdsjmxService.getgrjynck(zjhm);
            for (int i = 0; i < list.size(); i++) {
                Map map = list.get(i);

                //查询存款余额趋势图（一年内）
                JSONObject jo = new JSONObject();
                jo.put("type", map.get("TYPE"));
                jo.put("余额", map.get("存款余额"));
                jo.put("月日平", map.get("存款月日平"));
                jo.put("年日平", map.get("存款年日平"));
                json.add(jo);

                //查询贷款余额趋势图（一年内）
                JSONObject dkjo = new JSONObject();
                dkjo.put("type", map.get("TYPE"));
                dkjo.put("余额", map.get("贷款余额"));
                dkjo.put("月日平", map.get("贷款月日平"));
                dkjo.put("年日平", map.get("贷款年日平"));
                dkjson.add(dkjo);
            }
            jsonObject.put("ckyeList", json);
            jsonObject.put("dkyeList", dkjson);
            return Result.ok(jsonObject);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(e.toString());
        }
    }


    /**
     * 个人存贷款数据明细-提取
     *
     * @param tjyf
     * @return
     */
    @GetMapping(value = "/init")
    public Result<?> init(@RequestParam(name = "tjyf", required = true) String tjyf) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("tjyf", tjyf);
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                //同步oracle到impala
                EtlUtil.SHcallEtlRc(10, true, false,false,"tjfx_cssz", "idap");
                EtlUtil.SHcallEtlRc(10, true, false, false, "khxxgl_khxq_nh", "idap");
                QueryWrapper<KhglGrcdsjmx> queryWrapper=new QueryWrapper<>();
                queryWrapper.apply("tjyf =to_date('"+tjyf+"','YYYY-MM-dd')");
                khglGrcdsjmxService.remove(queryWrapper);
                //参数
                String csz_ckyrp = khglGrcdsjmxService.getCsz("CS0006");
                String csz_cknrp = khglGrcdsjmxService.getCsz("CS0007");
                String csz_dkyrp = khglGrcdsjmxService.getCsz("CS0008");
                String csz_dknrp = khglGrcdsjmxService.getCsz("CS0009");
                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_khywxxgrsjmx.py --fiscal_date " +
                        tjyf +
                        " --csz_ckyrp " +
                        csz_ckyrp +
                        " --csz_cknrp " +
                        csz_cknrp +
                        " --csz_dkyrp " +
                        csz_dkyrp +
                        " --csz_dknrp " +
                        csz_dknrp +
                        "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_KHYWXX_GRSJMX_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_KHYWXX_GRSJMX_IMPORT.sh");
            }else{
                khglGrcdsjmxService.init(param);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败！");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }
}
