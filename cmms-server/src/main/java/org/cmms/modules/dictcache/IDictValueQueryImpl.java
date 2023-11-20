package org.cmms.modules.dictcache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.constant.DictConstant;
import org.cmms.modules.dklldj.csszgl.xmgzsz.entity.Xmgzsz;
import org.cmms.modules.dklldj.csszgl.xmgzsz.mapper.XmgzszMapper;
import org.cmms.modules.system.entity.*;
import org.cmms.modules.system.mapper.HrBasOrganizationMapper;
import org.cmms.modules.system.mapper.SysDicMapper;
import org.cmms.modules.system.mapper.SysDictItemMapper;
import org.cmms.modules.system.mapper.SysUserMapper;
import org.cmms.modules.system.service.ISysDictItemService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.word.entity.PubDict;
import org.cmms.modules.word.mapper.PubDictMapper;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.mapper.EjyxdyglMapper;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.SjyxdyglMapper;
import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import org.cmms.modules.yxdygl.yjyxdygl.mapper.YjyxdyglMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IDictValueQueryImpl implements IDictValueQuery{
    HashMap<String, String> yjmap = null;
    HashMap<String, String> ejmap = null;
    HashMap<String, String> sjmap = null;
    HashMap<String, String> cszyMap = null;
    HashMap<String, String> pjMap = null;
    HashMap<String, String> hyzkMap = null;
    HashMap<String, String> yhzgxMap = null;
    HashMap<String, String> dkzlMap = null;
    HashMap<String, String> zcMap = null;
    HashMap<String, String> hrMap = null;
    HashMap<String, String> userMap = null;
    HashMap<String, String> userOrgCodeMap = null;
    @Autowired
    YjyxdyglMapper yjyxdyglMapper;
    @Autowired
    EjyxdyglMapper ejyxdyglMapper;
    @Autowired
    SjyxdyglMapper sjyxdyglMapper;
    @Autowired
    SysDictItemMapper sysDictItemMapper;
    @Autowired
    PubDictMapper pubDictMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysDicMapper sysDicMapper;
    @Autowired
    HrBasOrganizationMapper hrBasOrganizationMapper;

    @Override
    public void initMaps() {
        yjmap = null;
        ejmap = null;
        sjmap = null;



        cszyMap = null;
        pjMap = null;
        hyzkMap = null;
        yhzgxMap = null;
        dkzlMap = null;
        zcMap = null;
        userMap = null;
        userOrgCodeMap = null;
        hrMap = null;

    }

    @Override
    public String getCszyValue(String zy) {
        if (cszyMap == null) {
            List<SysDictItem> cszy = sysDictItemMapper.selectItemsByDictCode(DictConstant.PROFESSION_DICT);
            if (cszy != null && cszy.size() > 0) {
                cszyMap = new HashMap<>();
                for (int i = 0; i < cszy.size(); i++) {
                    cszyMap.put(cszy.get(i).getItemValue(), cszy.get(i).getItemText());
                }
            }
        }

        return StringUtils.isNoneBlank(cszyMap.get(zy))?cszyMap.get(zy):StringUtils.EMPTY;
    }

    @Override
    public String getHyzkValue(String hyzk) {
        if (hyzkMap == null) {
            List<SysDictItem> list = sysDictItemMapper.selectItemsByDictCode(DictConstant.MARRIAGE_DICT);
            if (list != null && list.size() > 0) {
                hyzkMap = new HashMap<>();
                for (int i = 0; i < list.size(); i++) {
                    hyzkMap.put(list.get(i).getItemValue(), list.get(i).getItemText());
                }
            }
        }
        return StringUtils.isNoneBlank(hyzkMap.get(hyzk))?hyzkMap.get(hyzk):StringUtils.EMPTY;
    }

    @Override
    public String getYearMonthValue(String zjhm) {
        return IdcardUtil.getYearByIdCard(zjhm)+DictConstant.YEAR_VAL+IdcardUtil.getMonthByIdCard(zjhm)+DictConstant.MOUTH_VAL;
    }

    @Override
    public String getYzhgxValue(String yhzgx) {
        if (yhzgxMap == null) {
            List<SysDictItem> list = sysDictItemMapper.selectItemsByDictCode(DictConstant.RELATION_DICT);
            if (list != null && list.size() > 0) {
                yhzgxMap = new HashMap<>();
                for (int i = 0; i < list.size(); i++) {
                    yhzgxMap.put(list.get(i).getItemValue(), list.get(i).getItemText());
                }
            }
        }
        return StringUtils.isNoneBlank(yhzgxMap.get(yhzgx))?yhzgxMap.get(yhzgx):StringUtils.EMPTY;
    }

    public void initDictMap(Map map, String dictCode){
        if (map == null){
            List<SysDictItem> dict = sysDictItemMapper.selectItemsByDictCode(dictCode);
            if (dict != null && dict.size() > 0) {
                map = new HashMap<>();
                for (int i = 0; i < dict.size(); i++) {
                    map.put(dict.get(i).getItemValue(), dict.get(i).getItemText());
                }
            }
        }
    }

    @Override
    public String getPddjValue(String yhzgx) {
        if (pjMap == null) {
            pjMap = new HashMap<>();
            pjMap.put("A", "特级");
            pjMap.put("B", "优秀");
            pjMap.put("C", "较好");
            pjMap.put("D", "一般");
            pjMap.put("E", "级外");
        }
        return StringUtils.isNoneBlank(pjMap.get(yhzgx))?pjMap.get(yhzgx):StringUtils.EMPTY;
    }

    @Override
    public String getyjValue(String sjyxdy) {
        if (yjmap == null) {
            List<Yjyxdygl> yjyxdygls = yjyxdyglMapper.selectList(null);
            if (yjyxdygls != null && yjyxdygls.size() > 0) {
                yjmap = new HashMap<>();
                for (int i = 0; i < yjyxdygls.size(); i++) {
                    yjmap.put(yjyxdygls.get(i).getDybh(), yjyxdygls.get(i).getDymc());
                }
            }
        }
        return StringUtils.isNoneBlank(yjmap.get(sjyxdy))?yjmap.get(sjyxdy):StringUtils.EMPTY;
    }

    @Override
    public String getejValue(String sjyxdy) {
        if (ejmap == null) {
            List<Ejyxdygl> ejyxdygls = ejyxdyglMapper.selectList(null);
            if (ejyxdygls != null && ejyxdygls.size() > 0) {
                ejmap = new HashMap<>();
                for (int i = 0; i < ejyxdygls.size(); i++) {
                    ejmap.put(ejyxdygls.get(i).getDybh(), ejyxdygls.get(i).getDymc());
                }
            }
        }
        return StringUtils.isNoneBlank(ejmap.get(sjyxdy))?ejmap.get(sjyxdy):StringUtils.EMPTY;
    }

    @Override
    public String getsjValue(String sjyxdy) {
        if (sjmap == null) {
            List<Sjyxdygl> sjyxdygls = sjyxdyglMapper.selectList(null);
            if (sjyxdygls != null && sjyxdygls.size() > 0) {
                sjmap = new HashMap<>();
                for (int i = 0; i < sjyxdygls.size(); i++) {
                    sjmap.put(sjyxdygls.get(i).getDybh(), sjyxdygls.get(i).getDymc());
                }
            }
        }
        return StringUtils.isNoneBlank(sjmap.get(sjyxdy))?sjmap.get(sjyxdy):StringUtils.EMPTY;
    }

    @Override
    public String getGrdkZllxValue(String sjyxdy) {
        if (dkzlMap == null) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("DICT_NM",DictConstant.ZLMC_GRXD);
            List<PubDict> list = pubDictMapper.selectList(queryWrapper);
            if (list != null && list.size() > 0) {
                dkzlMap = new HashMap<>();
                for (int i = 0; i < list.size(); i++) {
                    dkzlMap.put(list.get(i).getDictKey(), list.get(i).getDictValue());
                }
            }
        }
        return StringUtils.isNoneBlank(sjmap.get(sjyxdy))?sjmap.get(sjyxdy):StringUtils.EMPTY;
    }

    @Override
    public String getZcValue(String sjyxdy) {
        if (zcMap == null){
            zcMap = new HashMap<>();
            zcMap.put("00","无");
            zcMap.put("10","高级");
            zcMap.put("11","正高级职称");
            zcMap.put("12","副高级职称");
            zcMap.put("20","中级");
            zcMap.put("21","中级职称");
            zcMap.put("30","初级");
            zcMap.put("31","助理级职称");
            zcMap.put("32","技术员级职称");
            zcMap.put("99","未知");
        }
        return StringUtils.isNoneBlank(zcMap.get(sjyxdy))?zcMap.get(sjyxdy):StringUtils.EMPTY;
    }

    @Override
    public String getRoleValueByWorkNo(String workNO) {
        VsysUserRole byWorkNo = sysDictItemMapper.getByWorkNo(workNO);
        if (byWorkNo != null)
            return byWorkNo.getRoleCode();
        return null;
    }

    @Override
    public String getNameByUsername(String username) {
        if (userMap == null) {
            List<SysUser> sysUsers = sysUserMapper.selectList(null);
            if (sysUsers != null && sysUsers.size() > 0) {
                userMap = new HashMap<>();
                for (int i = 0; i < sysUsers.size(); i++) {
                    userMap.put(sysUsers.get(i).getUsername(), sysUsers.get(i).getRealname());
                }
            }
        }

        return StringUtils.isNoneBlank(userMap.get(username))?userMap.get(username):StringUtils.EMPTY;
    }

    @Override
    public String getSszhByUsername(String username) {
        if (userOrgCodeMap == null) {
            List<SysUser> sysUsers = sysUserMapper.selectList(null);
            if (sysUsers != null && sysUsers.size() > 0) {
                userOrgCodeMap = new HashMap<>();
                for (int i = 0; i < sysUsers.size(); i++) {
                    userOrgCodeMap.put(sysUsers.get(i).getUsername(), sysUsers.get(i).getOrgCode());
                }
            }
        }else {
            if (userOrgCodeMap.get(username) != null){
                return getNameBySszh(userOrgCodeMap.get(username));
            }else {
                return null;
            }
        }
        return null;
    }

    @Override
    public String getNameBySszh(String sszh) {
        if (hrMap == null) {
            List<HrBasOrganization> hrBasOrganizations = hrBasOrganizationMapper.selectList(null);
            if (hrBasOrganizations != null && hrBasOrganizations.size() > 0) {
                hrMap = new HashMap<>();
                for (int i = 0; i < hrBasOrganizations.size(); i++) {
                    hrMap.put(hrBasOrganizations.get(i).getZzbz(), hrBasOrganizations.get(i).getZzjc());
                }
            }
        }

        return StringUtils.isNoneBlank(hrMap.get(sszh))?hrMap.get(sszh):StringUtils.EMPTY;
    }

    Map<String, BigDecimal> zbgMap = null;
    @Autowired
    XmgzszMapper xmgzszMapper;
    @Override
    public BigDecimal getZbfzByZbgid(String zbgid) {
        if (zbgMap == null) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("qydm","020");
            List<Xmgzsz> list = xmgzszMapper.selectList(queryWrapper);
            if (list != null && list.size() > 0) {
                zbgMap = new HashMap<>();
                for (int i = 0; i < list.size(); i++) {
                    zbgMap.put(list.get(i).getZbgzid(), list.get(i).getZbgzfz());
                }
            }
        }
        return zbgMap.get(zbgid);
    }

    @Override
    @DS("eweb")
    public String getSeqRateZxlldjbDjidNextval(String seq) {
        if (seq == null){
            seq = "SEQ_PUBLIC_ID.nextval";
        }
        return sysDictItemMapper.getSeqRateZxlldjbDjidNextval(seq);
    }
    @Override
    @DS("idap")
    public String getSeqNextval(String seq) {
        if (seq == null){
            seq = "SEQ_PUBLIC_ID.nextval";
        }
        return sysDictItemMapper.getSeqRateZxlldjbDjidNextval(seq);
    }

    String dqdm = null;

    @Override
    public boolean isLy() {
        if (dqdm != null){
            return "020".equals(dqdm);
        }else {
            SysDic sysDic = sysDicMapper.queryByCode("101001");
            if (sysDic != null && sysDic.getValue() != null){
                dqdm = sysDic.getValue();
                return "020".equals(dqdm);
            }
        }
        return false;
    }

    @Override
    public String sjzzbz(String zzbz) {
        if (isLy()){
            HrBasOrganization hrBasOrganization = hrBasOrganizationMapper.queryZhbyZzbz(zzbz);
            if (hrBasOrganization != null && hrBasOrganization.getZzbz() != null)
                return hrBasOrganization.getZzbz();
        }
        return null;
    }
}
