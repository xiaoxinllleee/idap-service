<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="支行名称">
              <a-input placeholder="请输入支行名称" v-model="queryParam.zhmc"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="客户经理">
              <a-input placeholder="请输入客户经理" v-model="queryParam.khmc"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('计划下发')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i>
        <span>已选择</span>
        <a style="font-weight: 600">
          {{ selectedRowKeys.length }}
        </a>
        <span>项</span>
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <gzap_jhxf_real-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import Gzap_jhxf_realModal from './modules/Gzap_jhxf_realModal'

  export default {
    name: "Gzap_jhxf_realList",
    mixins: [JeecgListMixin],
    components: {
      Gzap_jhxf_realModal
    },
    data () {
      return {
        description: '计划下发管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender:function (t, r, index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '支行名称',
            align:"center",
            dataIndex: 'zhmc'
          },
          {
            title: '客户经理',
            align:"center",
            dataIndex: 'khmc'
          },
          {
            title: '工作类型',
            align:"center",
            dataIndex: 'gzlx'
          },
          {
            title: '计划开始日期',
            align:"center",
            dataIndex: 'jhkssj'
          },
          {
            title: '计划完成日期',
            align:"center",
            dataIndex: 'jhwcsj'
          },
          {
            title: '实际完成日期',
            align:"center",
            dataIndex: 'sjwcrq'
          },
          {
            title: '概述',
            align:"center",
            dataIndex: 'gs'
          },
          {
            title: '内容',
            align:"center",
            dataIndex: 'nr'
          },
          {
            title: '状态',
            align:"center",
            dataIndex: 'zt'
          },
          {
            title: '新增人',
            align:"center",
            dataIndex: 'xzr'
          },
          {
            title: '新增时间',
            align:"center",
            dataIndex: 'xzsj'
          },
          {
            title: '备注',
            align:"center",
            dataIndex: 'bz'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        // 请求参数
    	url: {
              list: "/Gzap_jhxf/gzap_jhxf_real/list",
              delete: "/Gzap_jhxf/gzap_jhxf_real/delete",
              deleteBatch: "/Gzap_jhxf/gzap_jhxf_real/deleteBatch",
              exportXlsUrl: "Gzap_jhxf/gzap_jhxf_real/exportXls",
              importExcelUrl: "Gzap_jhxf/gzap_jhxf_real/importExcel",
           },
        }
      },
      computed: {
        importExcelUrl: function(){
          return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
        }
      },


    methods: {

      initDictConfig() {
      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>