<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="秒杀商品ID" prop="seckillId">
      <el-input v-model="dataForm.seckillId" placeholder="秒杀商品ID"></el-input>
    </el-form-item>
    <el-form-item label="支付金额" prop="money">
      <el-input v-model="dataForm.money" placeholder="支付金额"></el-input>
    </el-form-item>
    <el-form-item label="用户" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户"></el-input>
    </el-form-item>
    <el-form-item label="商家" prop="sellerId">
      <el-input v-model="dataForm.sellerId" placeholder="商家"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="支付时间" prop="payTime">
      <el-input v-model="dataForm.payTime" placeholder="支付时间"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="收货人地址" prop="receiverAddress">
      <el-input v-model="dataForm.receiverAddress" placeholder="收货人地址"></el-input>
    </el-form-item>
    <el-form-item label="收货人电话" prop="receiverMobile">
      <el-input v-model="dataForm.receiverMobile" placeholder="收货人电话"></el-input>
    </el-form-item>
    <el-form-item label="收货人" prop="receiver">
      <el-input v-model="dataForm.receiver" placeholder="收货人"></el-input>
    </el-form-item>
    <el-form-item label="交易流水" prop="transactionId">
      <el-input v-model="dataForm.transactionId" placeholder="交易流水"></el-input>
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
          id: 0,
          seckillId: '',
          money: '',
          userId: '',
          sellerId: '',
          createTime: '',
          payTime: '',
          status: '',
          receiverAddress: '',
          receiverMobile: '',
          receiver: '',
          transactionId: ''
        },
        dataRule: {
          seckillId: [
            { required: true, message: '秒杀商品ID不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '支付金额不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: '用户不能为空', trigger: 'blur' }
          ],
          sellerId: [
            { required: true, message: '商家不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          payTime: [
            { required: true, message: '支付时间不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          receiverAddress: [
            { required: true, message: '收货人地址不能为空', trigger: 'blur' }
          ],
          receiverMobile: [
            { required: true, message: '收货人电话不能为空', trigger: 'blur' }
          ],
          receiver: [
            { required: true, message: '收货人不能为空', trigger: 'blur' }
          ],
          transactionId: [
            { required: true, message: '交易流水不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/manager/seckillorder/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.seckillId = data.seckillOrder.seckillId
                this.dataForm.money = data.seckillOrder.money
                this.dataForm.userId = data.seckillOrder.userId
                this.dataForm.sellerId = data.seckillOrder.sellerId
                this.dataForm.createTime = data.seckillOrder.createTime
                this.dataForm.payTime = data.seckillOrder.payTime
                this.dataForm.status = data.seckillOrder.status
                this.dataForm.receiverAddress = data.seckillOrder.receiverAddress
                this.dataForm.receiverMobile = data.seckillOrder.receiverMobile
                this.dataForm.receiver = data.seckillOrder.receiver
                this.dataForm.transactionId = data.seckillOrder.transactionId
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
              url: this.$http.adornUrl(`/manager/seckillorder/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'seckillId': this.dataForm.seckillId,
                'money': this.dataForm.money,
                'userId': this.dataForm.userId,
                'sellerId': this.dataForm.sellerId,
                'createTime': this.dataForm.createTime,
                'payTime': this.dataForm.payTime,
                'status': this.dataForm.status,
                'receiverAddress': this.dataForm.receiverAddress,
                'receiverMobile': this.dataForm.receiverMobile,
                'receiver': this.dataForm.receiver,
                'transactionId': this.dataForm.transactionId
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
