<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="支行名称">
              <a-input placeholder="请输入支行名称" v-decorator="['zhmc', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户经理">
              <a-input placeholder="请输入客户经理" v-decorator="['khmc', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="工作类型">
              <a-input placeholder="请输入工作类型" v-decorator="['gzlx', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="计划开始日期">
              <a-date-picker
                placeholder="请输入计划开始日期"
                style="width:100%"
                v-decorator="[ 'jhkssj', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="计划完成日期">
              <a-date-picker
                placeholder="请输入计划完成日期"
                style="width:100%"
                v-decorator="[ 'jhwcsj', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="实际完成日期">
              <a-date-picker
                placeholder="请输入实际完成日期"
                style="width:100%"
                v-decorator="[ 'sjwcrq', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="概述">
              <a-input placeholder="请输入概述" v-decorator="['gs', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="内容">
              <a-input placeholder="请输入内容" v-decorator="['nr', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="状态">
              <a-input placeholder="请输入状态" v-decorator="['zt', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="新增人">
              <a-input placeholder="请输入新增人" v-decorator="['xzr', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="新增时间">
              <a-date-picker
                placeholder="请输入新增时间"
                style="width:100%"
                v-decorator="[ 'xzsj', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="备注">
              <a-input placeholder="请输入备注" v-decorator="['bz', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="客户明细" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="gzap_jhxf_khjl_realTable.loading"
            :columns="gzap_jhxf_khjl_realTable.columns"
            :dataSource="gzap_jhxf_khjl_realTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'

  export default {
    name: 'Gzap_jhxf_realModal',
    mixins: [JEditableTableMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['gzap_jhxf_khjl_real', ],
        activeKey: 'gzap_jhxf_khjl_real',
        // 客户明细
        gzap_jhxf_khjl_realTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '工作内容',
              key: 'gznr',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '实际完成情况',
              key: 'sjwcqk',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '客户经理',
              key: 'khjl',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/Gzap_jhxf/gzap_jhxf_real/add",
          edit: "/Gzap_jhxf/gzap_jhxf_real/edit",
          gzap_jhxf_khjl_real: {
            list: '/Gzap_jhxf/gzap_jhxf_real/queryGzap_jhxf_khjl_realByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'zhmc', 'khmc', 'gzlx', 'jhkssj', 'jhwcsj', 'sjwcrq', 'gs', 'nr', 'zt', 'xzr', 'xzsj', 'bz', ))
          // 时间格式化
          this.form.setFieldsValue({ jhkssj: this.model.jhkssj ? moment(this.model.jhkssj) : null })
          this.form.setFieldsValue({ jhwcsj: this.model.jhwcsj ? moment(this.model.jhwcsj) : null })
          this.form.setFieldsValue({ sjwcrq: this.model.sjwcrq ? moment(this.model.sjwcrq) : null })
          this.form.setFieldsValue({ xzsj: this.model.xzsj ? moment(this.model.xzsj) : null })
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.gzap_jhxf_khjl_real.list, params, this.gzap_jhxf_khjl_realTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        main.jhkssj = main.jhkssj ? main.jhkssj.format() : null;
        main.jhwcsj = main.jhwcsj ? main.jhwcsj.format() : null;
        main.sjwcrq = main.sjwcrq ? main.sjwcrq.format() : null;
        main.xzsj = main.xzsj ? main.xzsj.format() : null;
        return {
          ...main, // 展开
          gzap_jhxf_khjl_realList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>