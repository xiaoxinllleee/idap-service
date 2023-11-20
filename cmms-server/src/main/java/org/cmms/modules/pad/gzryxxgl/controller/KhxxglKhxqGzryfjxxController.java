package org.cmms.modules.pad.gzryxxgl.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.gzryxxgl.entity.GzryfjxxPo;
import org.cmms.modules.pad.gzryxxgl.entity.KhxxglKhxqGzry;
import org.cmms.modules.pad.gzryxxgl.entity.KhxxglKhxqGzryfjxx;
import org.cmms.modules.pad.gzryxxgl.service.IKhxxglKhxqGzryService;
import org.cmms.modules.pad.gzryxxgl.service.IKhxxglKhxqGzryfjxxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 公职人员附件信息
 * @Author: jeecg-boot
 * @Date: 2022-08-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "公职人员附件信息")
@RestController
@RequestMapping("/gzryxxgl/khxxglKhxqGzryfjxx")
public class KhxxglKhxqGzryfjxxController extends JeecgController<KhxxglKhxqGzryfjxx, IKhxxglKhxqGzryfjxxService> {
    @Autowired
    private IKhxxglKhxqGzryfjxxService khxxglKhxqGzryfjxxService;
    @Autowired
    private IKhxxglKhxqGzryService khxxglKhxqGzryService;

    /**
     * 分页列表查询
     *
     * @param khxxglKhxqGzryfjxx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "公职人员附件信息-分页列表查询")
    @ApiOperation(value = "公职人员附件信息-分页列表查询", notes = "公职人员附件信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(KhxxglKhxqGzryfjxx khxxglKhxqGzryfjxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<KhxxglKhxqGzryfjxx> queryWrapper = QueryGenerator.initQueryWrapper(khxxglKhxqGzryfjxx, req.getParameterMap());
        Page<KhxxglKhxqGzryfjxx> page = new Page<KhxxglKhxqGzryfjxx>(pageNo, pageSize);
        IPage<KhxxglKhxqGzryfjxx> pageList = khxxglKhxqGzryfjxxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 根据证件号码查询附件信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryGzryFjxx", method = RequestMethod.GET)
    public Result<?> queryGzryFjxx(@RequestParam(name = "id") String id) {
        try {
            QueryWrapper<KhxxglKhxqGzry> fjxxQueryWrapper = new QueryWrapper<>();
            fjxxQueryWrapper.eq("id", id);
            KhxxglKhxqGzry khxxglKhxqGzry = khxxglKhxqGzryService.getOne(fjxxQueryWrapper,false);

            QueryWrapper<KhxxglKhxqGzryfjxx> fjxxQueryWrapper1 = new QueryWrapper<>();
            fjxxQueryWrapper1.eq("zjhm", khxxglKhxqGzry.getZjhm());
            List<KhxxglKhxqGzryfjxx> list = khxxglKhxqGzryfjxxService.list(fjxxQueryWrapper1);
            if (list != null && list.size() > 0) {
                for (KhxxglKhxqGzryfjxx khxxglKhxqGzryfjxx : list) {
                    khxxglKhxqGzryfjxx.setZllj("");
                }
                return Result.ok(list);
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("查询成功");
    }

    /**
     * 根据证件号码和资料类型查询附件信息
     *
     * @param id
     * @param zllx
     * @return
     */
    @RequestMapping(value = "/queryByZjhmAndZllx", method = RequestMethod.GET)
    public Result<?> queryByZjhmAndZllx(@RequestParam(name = "id") String id,
                                        @RequestParam(name = "zllx") String zllx) {
        try {
            QueryWrapper<KhxxglKhxqGzry> fjxxQueryWrapper = new QueryWrapper<>();
            fjxxQueryWrapper.eq("id", id);
            KhxxglKhxqGzry khxxglKhxqGzry = khxxglKhxqGzryService.getOne(fjxxQueryWrapper,false);

            QueryWrapper<KhxxglKhxqGzryfjxx> fjxxQueryWrapper1 = new QueryWrapper<>();
            fjxxQueryWrapper.eq("zjhm", khxxglKhxqGzry.getZjhm());
            fjxxQueryWrapper.eq("zllx", zllx);
            List<KhxxglKhxqGzryfjxx> list = khxxglKhxqGzryfjxxService.list(fjxxQueryWrapper1);
            if (list != null && list.size() > 0) {
                list.get(0).setZllj("");
                return Result.ok(list.get(0));
            }
        } catch (Exception e) {
            log.error("查询公职人员附件信息失败", e);
            return Result.error("查询公职人员附件信息失败");
        }
        return Result.ok("查询成功");
    }

    /**
     * 保存附件信息
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/saveGzryfjImage", method = RequestMethod.POST)
    public Result<?> saveGzryfjImage(@RequestBody List<GzryfjxxPo> jsonObject) {
        try {
            if (jsonObject != null && jsonObject.size() > 0) {
                String gzryId=jsonObject.get(0).getGzryId();
                QueryWrapper<KhxxglKhxqGzry> khxqGzryQueryWrapper = new QueryWrapper<>();
                khxqGzryQueryWrapper.eq("id", gzryId);
                KhxxglKhxqGzry khxxglKhxqGzry = khxxglKhxqGzryService.getOne(khxqGzryQueryWrapper,false);
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                for (int i = 0; i < jsonObject.size(); i++) {
                    if (StringUtils.isEmpty(jsonObject.get(i).getId())) {
                        KhxxglKhxqGzryfjxx fjgl = new KhxxglKhxqGzryfjxx();
                        fjgl.setId(UUID.randomUUID().toString().substring(0, 14));
                        fjgl.setGzryid(jsonObject.get(i).getGzryId());
                        fjgl.setZllx(jsonObject.get(i).getZllx());
                        fjgl.setZldx(jsonObject.get(i).getZldx());
                        fjgl.setFwlj(jsonObject.get(i).getFwlj());
                        fjgl.setZlmc(jsonObject.get(i).getZlmc());
                        fjgl.setZllj(uploadpath + "/" + jsonObject.get(i).getFwlj());
                        fjgl.setScsj(new Date());
                        fjgl.setScr(sysUser.getUsername());
                        fjgl.setLrsj(new Date());
                        fjgl.setLrr(sysUser.getUsername());
                        fjgl.setZjhm(khxxglKhxqGzry.getZjhm());
                        khxxglKhxqGzryfjxxService.save(fjgl);
                    }
                }
            }
        } catch (Exception e) {
            log.error("保存公职人员附件失败", e);
            return Result.error("保存公职人员附件失败");
        }
        return Result.ok("保存成功");
    }

    @RequestMapping(value = "/deleteGzryfjImage", method = RequestMethod.POST)
    public Result<?> deleteGzryfjImage(@RequestBody List<KhxxglKhxqGzryfjxx> jsonObject) {
        try {
            if (jsonObject != null && jsonObject.size() > 0) {
                for (int i = 0; i < jsonObject.size(); i++) {
                    if (!StringUtils.isEmpty(jsonObject.get(i).getId())) {
                        UpdateWrapper<KhxxglKhxqGzryfjxx> khglNhzllbPadUpdateWrapper = new UpdateWrapper<>();
                        khglNhzllbPadUpdateWrapper.eq("id", jsonObject.get(i).getId());
                        khxxglKhxqGzryfjxxService.remove(khglNhzllbPadUpdateWrapper);
                    }
                }
            }
        } catch (Exception e) {
            log.error("删除农户附件失败", e);
            return Result.error("删除农户附件失败");
        }
        return Result.ok("保存成功");
    }


    /**
     * 添加
     *
     * @param khxxglKhxqGzryfjxx
     * @return
     */
    @AutoLog(value = "公职人员附件信息-添加")
    @ApiOperation(value = "公职人员附件信息-添加", notes = "公职人员附件信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhxxglKhxqGzryfjxx khxxglKhxqGzryfjxx) {
        khxxglKhxqGzryfjxxService.save(khxxglKhxqGzryfjxx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khxxglKhxqGzryfjxx
     * @return
     */
    @AutoLog(value = "公职人员附件信息-编辑")
    @ApiOperation(value = "公职人员附件信息-编辑", notes = "公职人员附件信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhxxglKhxqGzryfjxx khxxglKhxqGzryfjxx) {
        khxxglKhxqGzryfjxxService.updateById(khxxglKhxqGzryfjxx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公职人员附件信息-通过id删除")
    @ApiOperation(value = "公职人员附件信息-通过id删除", notes = "公职人员附件信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khxxglKhxqGzryfjxxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "公职人员附件信息-批量删除")
    @ApiOperation(value = "公职人员附件信息-批量删除", notes = "公职人员附件信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khxxglKhxqGzryfjxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "公职人员附件信息-通过id查询")
    @ApiOperation(value = "公职人员附件信息-通过id查询", notes = "公职人员附件信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhxxglKhxqGzryfjxx khxxglKhxqGzryfjxx = khxxglKhxqGzryfjxxService.getById(id);
        return Result.ok(khxxglKhxqGzryfjxx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khxxglKhxqGzryfjxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, KhxxglKhxqGzryfjxx khxxglKhxqGzryfjxx) {
        return super.exportXls(request, khxxglKhxqGzryfjxx, KhxxglKhxqGzryfjxx.class, "公职人员附件信息");
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
        return super.importExcel(request, response, KhxxglKhxqGzryfjxx.class);
    }

}
