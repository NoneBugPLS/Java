<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('manager:order:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('manager:order:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
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
        prop="orderId"
        header-align="center"
        align="center"
        label="订单id">
      </el-table-column>
      <el-table-column
        prop="payment"
        header-align="center"
        align="center"
        label="实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分">
      </el-table-column>
      <el-table-column
        prop="paymentType"
        header-align="center"
        align="center"
        label="支付类型，1、在线支付，2、货到付款">
      </el-table-column>
      <el-table-column
        prop="postFee"
        header-align="center"
        align="center"
        label="邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分">
      </el-table-column>
      <el-table-column
        prop="status"
        header-align="center"
        align="center"
        label="状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="订单创建时间">
      </el-table-column>
      <el-table-column
        prop="updateTime"
        header-align="center"
        align="center"
        label="订单更新时间">
      </el-table-column>
      <el-table-column
        prop="paymentTime"
        header-align="center"
        align="center"
        label="付款时间">
      </el-table-column>
      <el-table-column
        prop="consignTime"
        header-align="center"
        align="center"
        label="发货时间">
      </el-table-column>
      <el-table-column
        prop="endTime"
        header-align="center"
        align="center"
        label="交易完成时间">
      </el-table-column>
      <el-table-column
        prop="closeTime"
        header-align="center"
        align="center"
        label="交易关闭时间">
      </el-table-column>
      <el-table-column
        prop="shippingName"
        header-align="center"
        align="center"
        label="物流名称">
      </el-table-column>
      <el-table-column
        prop="shippingCode"
        header-align="center"
        align="center"
        label="物流单号">
      </el-table-column>
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        label="用户id">
      </el-table-column>
      <el-table-column
        prop="buyerMessage"
        header-align="center"
        align="center"
        label="买家留言">
      </el-table-column>
      <el-table-column
        prop="buyerNick"
        header-align="center"
        align="center"
        label="买家昵称">
      </el-table-column>
      <el-table-column
        prop="buyerRate"
        header-align="center"
        align="center"
        label="买家是否已经评价">
      </el-table-column>
      <el-table-column
        prop="receiverAreaName"
        header-align="center"
        align="center"
        label="收货人地区名称(省，市，县)街道">
      </el-table-column>
      <el-table-column
        prop="receiverMobile"
        header-align="center"
        align="center"
        label="收货人手机">
      </el-table-column>
      <el-table-column
        prop="receiverZipCode"
        header-align="center"
        align="center"
        label="收货人邮编">
      </el-table-column>
      <el-table-column
        prop="receiver"
        header-align="center"
        align="center"
        label="收货人">
      </el-table-column>
      <el-table-column
        prop="expire"
        header-align="center"
        align="center"
        label="过期时间，定期清理">
      </el-table-column>
      <el-table-column
        prop="invoiceType"
        header-align="center"
        align="center"
        label="发票类型(普通发票，电子发票，增值税发票)">
      </el-table-column>
      <el-table-column
        prop="sourceType"
        header-align="center"
        align="center"
        label="订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端">
      </el-table-column>
      <el-table-column
        prop="sellerId"
        header-align="center"
        align="center"
        label="商家ID">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.orderId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.orderId)">删除</el-button>
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
  import AddOrUpdate from './order-add-or-update'
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
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/manager/order/list'),
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
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.orderId
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/manager/order/delete'),
            method: 'post',
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
      }
    }
  }
</script>
