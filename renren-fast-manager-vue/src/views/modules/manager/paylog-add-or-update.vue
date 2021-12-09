<template>
  <el-dialog
    :title="!dataForm.outTradeNo ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="创建日期" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建日期"></el-input>
    </el-form-item>
    <el-form-item label="支付完成时间" prop="payTime">
      <el-input v-model="dataForm.payTime" placeholder="支付完成时间"></el-input>
    </el-form-item>
    <el-form-item label="支付金额（分）" prop="totalFee">
      <el-input v-model="dataForm.totalFee" placeholder="支付金额（分）"></el-input>
    </el-form-item>
    <el-form-item label="用户ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户ID"></el-input>
    </el-form-item>
    <el-form-item label="交易号码" prop="transactionId">
      <el-input v-model="dataForm.transactionId" placeholder="交易号码"></el-input>
    </el-form-item>
    <el-form-item label="交易状态" prop="tradeState">
      <el-input v-model="dataForm.tradeState" placeholder="交易状态"></el-input>
    </el-form-item>
    <el-form-item label="订单编号列表" prop="orderList">
      <el-input v-model="dataForm.orderList" placeholder="订单编号列表"></el-input>
    </el-form-item>
    <el-form-item label="支付类型" prop="payType">
      <el-input v-model="dataForm.payType" placeholder="支付类型"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          outTradeNo: 0,
          createTime: '',
          payTime: '',
          totalFee: '',
          userId: '',
          transactionId: '',
          tradeState: '',
          orderList: '',
          payType: ''
        },
        dataRule: {
          createTime: [
            { required: true, message: '创建日期不能为空', trigger: 'blur' }
          ],
          payTime: [
            { required: true, message: '支付完成时间不能为空', trigger: 'blur' }
          ],
          totalFee: [
            { required: true, message: '支付金额（分）不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: '用户ID不能为空', trigger: 'blur' }
          ],
          transactionId: [
            { required: true, message: '交易号码不能为空', trigger: 'blur' }
          ],
          tradeState: [
            { required: true, message: '交易状态不能为空', trigger: 'blur' }
          ],
          orderList: [
            { required: true, message: '订单编号列表不能为空', trigger: 'blur' }
          ],
          payType: [
            { required: true, message: '支付类型不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.outTradeNo = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.outTradeNo) {
            this.$http({
              url: this.$http.adornUrl(`/manager/paylog/info/${this.dataForm.outTradeNo}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.createTime = data.payLog.createTime
                this.dataForm.payTime = data.payLog.payTime
                this.dataForm.totalFee = data.payLog.totalFee
                this.dataForm.userId = data.payLog.userId
                this.dataForm.transactionId = data.payLog.transactionId
                this.dataForm.tradeState = data.payLog.tradeState
                this.dataForm.orderList = data.payLog.orderList
                this.dataForm.payType = data.payLog.payType
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/manager/paylog/${!this.dataForm.outTradeNo ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'outTradeNo': this.dataForm.outTradeNo || undefined,
                'createTime': this.dataForm.createTime,
                'payTime': this.dataForm.payTime,
                'totalFee': this.dataForm.totalFee,
                'userId': this.dataForm.userId,
                'transactionId': this.dataForm.transactionId,
                'tradeState': this.dataForm.tradeState,
                'orderList': this.dataForm.orderList,
                'payType': this.dataForm.payType
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
