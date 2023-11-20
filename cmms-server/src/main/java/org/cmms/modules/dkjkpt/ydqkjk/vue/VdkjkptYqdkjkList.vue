<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="tjyf">
              <a-input placeholder="请输入tjyf" v-model="queryParam.tjyf"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="jgdm">
              <a-input placeholder="请输入jgdm" v-model="queryParam.jgdm"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="khmc">
              <a-input placeholder="请输入khmc" v-model="queryParam.khmc"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="zjhm">
              <a-input placeholder="请输入zjhm" v-model="queryParam.zjhm"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="dkzh">
              <a-input placeholder="请输入dkzh" v-model="queryParam.dkzh"></a-input>
            </a-form-item>
          </a-col>
          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('逾期贷款监控')">导出</a-button>
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
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
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
    <vdkjkptYqdkjk-modal ref="modalForm" @ok="modalFormOk"></vdkjkptYqdkjk-modal>
  </a-card>
</template>

<script>
  import VdkjkptYqdkjkModal from './modules/VdkjkptYqdkjkModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "VdkjkptYqdkjkList",
    mixins:[JeecgListMixin],
    components: {
      VdkjkptYqdkjkModal
    },
    data () {
      return {
        description: '逾期贷款监控管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: 'tjyf',
            align:"center",
            dataIndex: 'tjyf'
           },
		   {
            title: 'jgdm',
            align:"center",
            dataIndex: 'jgdm'
           },
		   {
            title: 'khmc',
            align:"center",
            dataIndex: 'khmc'
           },
		   {
            title: 'zjhm',
            align:"center",
            dataIndex: 'zjhm'
           },
		   {
            title: 'dkzh',
            align:"center",
            dataIndex: 'dkzh'
           },
		   {
            title: 'bmkkh',
            align:"center",
            dataIndex: 'bmkkh'
           },
		   {
            title: 'dkje',
            align:"center",
            dataIndex: 'dkje'
           },
		   {
            title: 'dkye',
            align:"center",
            dataIndex: 'dkye'
           },
		   {
            title: 'jkrq',
            align:"center",
            dataIndex: 'jkrq'
           },
		   {
            title: 'dqrq',
            align:"center",
            dataIndex: 'dqrq'
           },
		   {
            title: 'qxr',
            align:"center",
            dataIndex: 'qxr'
           },
		   {
            title: 'jxr',
            align:"center",
            dataIndex: 'jxr'
           },
		   {
            title: 'syts',
            align:"center",
            dataIndex: 'syts'
           },
		   {
            title: 'khlx',
            align:"center",
            dataIndex: 'khlx'
           },
		   {
            title: 'dz',
            align:"center",
            dataIndex: 'dz'
           },
		   {
            title: 'dhhm',
            align:"center",
            dataIndex: 'dhhm'
           },
		   {
            title: 'xddkpz',
            align:"center",
            dataIndex: 'xddkpz'
           },
		   {
            title: 'khjlyggh',
            align:"center",
            dataIndex: 'khjlyggh'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/dkjkpt.ydqkjk/vdkjkptYqdkjk/list",
          delete: "/dkjkpt.ydqkjk/vdkjkptYqdkjk/delete",
          deleteBatch: "/dkjkpt.ydqkjk/vdkjkptYqdkjk/deleteBatch",
          exportXlsUrl: "dkjkpt.ydqkjk/vdkjkptYqdkjk/exportXls",
          importExcelUrl: "dkjkpt.ydqkjk/vdkjkptYqdkjk/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>