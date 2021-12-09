<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('manager:goods:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('manager:goods:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
      <el-button type="primary" icon="el-icon-share" plain @click="updateStatus('1')">审核通过</el-button>
      <el-button type="primary" icon="el-icon-edit" plain  @click="updateStatus('2')">驳回</el-button>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="商品ID">
      </el-table-column>
      <el-table-column
        prop="goodsName"
        header-align="center"
        align="center"
        label="商品名称">
      </el-table-column>
      <el-table-column
        prop="price"
        header-align="center"
        align="center"
        label="商品价格">
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="一级分类">
        <template slot-scope="scope">
          {{itemCatList[scope.row.category1Id]}}
        </template>
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="二级分类">
        <template slot-scope="scope">
          {{itemCatList[scope.row.category2Id]}}
        </template>
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="三级分类">
        <template slot-scope="scope">
          {{itemCatList[scope.row.category3Id]}}
        </template>
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="状态">
        <template slot-scope="scope">
          {{status[scope.row.auditStatus]}}
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './goods-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        status:["未审核","己审核","审核未通过","己关闭"],
        itemCatList:[],           //代表所有的商品分类
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
      this.findItemList()
    },
    methods: {
      //查询商品分类
      findItemList() {
        this.$http({
          url: this.$http.adornUrl('/manager/itemcat/findItemCats'),
          method: 'get',
        }).then(({data}) => {
          if (data.code == 0) {
            for (let i = 0; i < data.itemCatList.length; i++) {
              let itemCat = data.itemCatList[i];
              //以商品id为key，以商品名称为值放到一个新的关联数组
              this.itemCatList[itemCat.id] = itemCat.name;
            }
          }
        })
      },
      // 获取数据列表
      getDataList() {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/manager/goods/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle(val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle(val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle(val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle(id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle(id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/manager/goods/delete'),
            method: 'delete',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },
      //审核
      updateStatus(status){    //status：审核状态的值
        //得到要审核的商品id数组
        let ids = this.dataListSelections.map(item=>{
          return item.id
          console.log(status)
        })
       //2. 向后台发请求
        this.$http({
          url: this.$http.adornUrl(`/manager/goods/updateStatus/${status}/${ids}`),
          method: 'post',
        }).then(({data}) => {
          if (data.code ==0){
            this.getDataList();
          }
        })
      },
    }
  }
</script>
