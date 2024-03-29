package org.cmms.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.common.constant.CacheConstant;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.system.vo.DynamicDataSourceModel;
import org.cmms.common.util.dynamic.db.DataSourceCachePool;
import org.cmms.modules.system.entity.SysDict;
import org.cmms.modules.system.entity.SysDictItem;
import org.cmms.modules.system.mapper.SysDictItemMapper;
import org.cmms.modules.system.mapper.SysDictMapper;
import org.cmms.modules.system.model.TreeSelectModel;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @Author zhangweijian
 * @since 2018-12-28
 */
@Service
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {
	@Autowired
	private DynamicDataSourceProperties properties;

    @Autowired
    private SysDictMapper sysDictMapper;
    @Autowired
    private SysDictItemMapper sysDictItemMapper;
	@Value("${com.etl.sfdsjpt}")
	private String sfdsjpt;
	/**
	 * 通过查询指定code 获取字典
	 * @param code
	 * @return
	 */
	@Override
	@Cacheable(value = CacheConstant.SYS_DICT_CACHE,key = "#code")
	public List<DictModel> queryDictItemsByCode(String code) {
		log.info("无缓存dictCache的时候调用这里！");
		return sysDictMapper.queryDictItemsByCode(code);
	}

	@Override
	//@Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE)
	@DS("#ds")
	public List<DictModel> queryDictItemsByCodeAndDs(String code,String ds) {
		log.info("无缓存dictTableList的时候调用这里！");
		return sysDictMapper.queryDictItemsByCode(code);
	}

	/**
	 * 通过查询指定code 获取字典值text
	 * @param code
	 * @param key
	 * @return
	 */

	@Override
	@Cacheable(value = CacheConstant.SYS_DICT_CACHE,key = "#code+':'+#key")
	public String queryDictTextByKey(String code, String key) {
		log.info("无缓存dictText的时候调用这里！");
		return sysDictMapper.queryDictTextByKey(code, key);
	}

	@Override
	@Cacheable(value = CacheConstant.SYS_DICT_CACHE_VALUE,key = "#code+':'+#key")
	public String queryDictValueByKey(String code, String key) {
		return baseMapper.queryDictValueByKey(code, key);
	}


	/**
	 * 通过查询指定table的 text code 获取字典
	 * dictTableCache采用redis缓存有效期10分钟
	 * @param table
	 * @param text
	 * @param code
	 * @return
	 */
	@Override
	//@Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE)
	public List<DictModel> queryTableDictItemsByCode(String table, String text, String code) {
		log.info("无缓存dictTableList的时候调用这里！");
		return sysDictMapper.queryTableDictItemsByCode(table,text,code);
	}

	@Override
	//@Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE)
	@DS("#ds")
	public List<DictModel> queryTableDictItemsByCodeAndDs(String ds,String table, String text, String code) {
		log.info("无缓存dictTableList的时候调用这里！");


		return sysDictMapper.queryTableDictItemsByCode(table,text,code);
	}

	@Override
	public List<DictModel> queryTableDictItemsByCodeAndFilter(String table, String text, String code, String filterSql) {
		log.info("无缓存dictTableList的时候调用这里！");
		return sysDictMapper.queryTableDictItemsByCodeAndFilter(table,text,code,filterSql);
	}

	@Override
	@DS("#ds")
	public List<DictModel> queryTableDictItemsByCodeAndFilterAndDs(String ds,String table, String text, String code, String filterSql) {
		log.info("无缓存dictTableList的时候调用这里！");
		return sysDictMapper.queryTableDictItemsByCodeAndFilter(table,text,code,filterSql);
	}
	/**
	 * 通过查询指定table的 text code 获取字典值text
	 * dictTableCache采用redis缓存有效期10分钟
	 * @param table
	 * @param text
	 * @param code
	 * @param key
	 * @return
	 */
	@Override
	@Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE,key = "#table+':'+#text+':'+#code+':'+#key")
	public String queryTableDictTextByKey(String table,String text,String code, String key) {
		log.info("无缓存dictTable的时候调用这里！");
		List<String> textList = sysDictMapper.queryTableDictTextByKey(table,text,code,key);
		if (!textList.isEmpty()) {
			return textList.get(0);
		}
		return null;
	}

	@Override
	@Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE)
	@DS("#ds")
	public String queryTableDictTextByKeyAndDs(String table,String text,String code, String key ,String ds) {
		log.info("无缓存dictTable的时候调用这里！");
		log.info("queryTableDictTextByKeyAndDs[ds]======="+ds);
		final Map<String, DataSourceProperty> datasource = properties.getDatasource();
		DataSourceProperty dataSourceProperty = datasource.get(ds);
		if(dataSourceProperty.getDriverClassName().equals("org.apache.hive.jdbc.HiveDriver")){
			return sysDictMapper.queryTableDictTextByKeyByDsjpt(table,text,code,key);
		}else{
			//rownum=1查询有效率问题 改成list返回
			List<String> textList = sysDictMapper.queryTableDictTextByKey(table,text,code,key);
			if (!textList.isEmpty()) {
				return textList.get(0);
			}
			return null;
		}
	}
	/**
	 * 通过查询指定table的 text code 获取字典，包含text和value
	 * dictTableCache采用redis缓存有效期10分钟
	 * @param table
	 * @param text
	 * @param code
	 * @param keyArray
	 * @return
	 */
	@Override
	@Cacheable(value = CacheConstant.SYS_DICT_TABLE_CACHE)
	public List<String> queryTableDictByKeys(String table, String text, String code, String[] keyArray) {
		List<DictModel> dicts = sysDictMapper.queryTableDictByKeys(table, text, code, keyArray);
		List<String> texts = new ArrayList<>(dicts.size());
		// 查询出来的顺序可能是乱的，需要排个序
		for (String key : keyArray) {
			for (DictModel dict : dicts) {
				if (key.equals(dict.getValue())) {
					texts.add(dict.getText());
					break;
				}
			}
		}
		return texts;
	}

    /**
     * 根据字典类型id删除关联表中其对应的数据
     */
    @Override
    public boolean deleteByDictId(SysDict sysDict) {
        sysDict.setDelFlag(CommonConstant.DEL_FLAG_1);
        return  this.updateById(sysDict);
    }

    @Override
    @Transactional
    public void saveMain(SysDict sysDict, List<SysDictItem> sysDictItemList) {

        sysDictMapper.insert(sysDict);
        if (sysDictItemList != null) {
            for (SysDictItem entity : sysDictItemList) {
                entity.setDictId(sysDict.getId());
                sysDictItemMapper.insert(entity);
            }
        }
    }

	@Override
	public List<DictModel> queryAllDepartBackDictModel() {
		return baseMapper.queryAllDepartBackDictModel();
	}

	@Override
	public List<DictModel> queryAllUserBackDictModel() {
		return baseMapper.queryAllUserBackDictModel();
	}

	@Override
	public List<DictModel> queryTableDictItems(String table, String text, String code, String keyword) {
		return baseMapper.queryTableDictItems(table, text, code, "%"+keyword+"%");
	}

	@Override
	public List<TreeSelectModel> queryTreeList(Map<String, String> query, String table, String text, String code, String pidField, String pid, String hasChildField) {
		return baseMapper.queryTreeList(query,table, text, code, pidField, pid,hasChildField);
	}

	@Override
	public List<TreeSelectModel> loadOrganTreeData(Map<String, String> query, String table, String text, String code, String pidField, String pid, String hasChildField) {
		return baseMapper.loadOrganTreeData(query,table, text, code, pidField, pid,hasChildField);
	}

	@Override
	public void deleteOneDictPhysically(String id) {
		this.baseMapper.deleteOneById(id);
		this.sysDictItemMapper.delete(new LambdaQueryWrapper<SysDictItem>().eq(SysDictItem::getDictId,id));
	}

	@Override
	public void updateDictDelFlag(int delFlag, String id) {
		baseMapper.updateDictDelFlag(delFlag,id);
	}

	@Override
	public List<SysDict> queryDeleteList() {
		return baseMapper.queryDeleteList();
	}


	@Override
	public List<TreeSelectModel> queryZzjgTreeList(Map<String, String> query, String table, String text, String code, String pidField,String pid,String hasChildField, String getAllZzjg) {
		return baseMapper.queryZzjgTreeList(query, table, text, code, pidField, pid,hasChildField, getAllZzjg);
	}

	@Override
	public List<TreeSelectModel> queryTreeListToSszh(String table, String text, String code, String pidField,String pid,String hasChildField,String userid) {
		return baseMapper.queryTreeListToSszh(table, text, code, pidField, pid,hasChildField,userid);
	}

	@Override
	/**
	 * 获取户号编码
	 */
	public String queryhhbm(String text){
		return sysDictMapper.queryhhbm(text);
	}
	//@Cacheable(value = CacheConstant.SYS_DICT_CACHE,key = "#code")
	@Override
	public String descValueText(String code, String key) {
		return baseMapper.descValueText(code,key);
	}
}
