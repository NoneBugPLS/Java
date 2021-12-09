<template>
  <el-dialog
    :title="!dataForm.orderId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分" prop="payment">
      <el-input v-model="dataForm.payment" placeholder="实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分"></el-input>
    </el-form-item>
    <el-form-item label="支付类型，1、在线支付，2、货到付款" prop="paymentType">
      <el-input v-model="dataForm.paymentType" placeholder="支付类型，1、在线支付，2、货到付款"></el-input>
    </el-form-item>
    <el-form-item label="邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分" prop="postFee">
      <el-input v-model="dataForm.postFee" placeholder="邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分"></el-input>
    </el-form-item>
    <el-form-item label="状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价"></el-input>
    </el-form-item>
    <el-form-item label="订单创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="订单创建时间"></el-input>
    </el-form-item>
    <el-form-item label="订单更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="订单更新时间"></el-input>
    </el-form-item>
    <el-form-item label="付款时间" prop="paymentTime">
      <el-input v-model="dataForm.paymentTime" placeholder="付款时间"></el-input>
    </el-form-item>
    <el-form-item label="发货时间" prop="consignTime">
      <el-input v-model="dataForm.consignTime" placeholder="发货时间"></el-input>
    </el-form-item>
    <el-form-item label="交易完成时间" prop="endTime">
      <el-input v-model="dataForm.endTime" placeholder="交易完成时间"></el-input>
    </el-form-item>
    <el-form-item label="交易关闭时间" prop="closeTime">
      <el-input v-model="dataForm.closeTime" placeholder="交易关闭时间"></el-input>
    </el-form-item>
    <el-form-item label="物流名称" prop="shippingName">
      <el-input v-model="dataForm.shippingName" placeholder="物流名称"></el-input>
    </el-form-item>
    <el-form-item label="物流单号" prop="shippingCode">
      <el-input v-model="dataForm.shippingCode" placeholder="物流单号"></el-input>
    </el-form-item>
    <el-form-item label="用户id" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="买家留言" prop="buyerMessage">
      <el-input v-model="dataForm.buyerMessage" placeholder="买家留言"></el-input>
    </el-form-item>
    <el-form-item label="买家昵称" prop="buyerNick">
      <el-input v-model="dataForm.buyerNick" placeholder="买家昵称"></el-input>
    </el-form-item>
    <el-form-item label="买家是否已经评价" prop="buyerRate">
      <el-input v-model="dataForm.buyerRate" placeholder="买家是否已经评价"></el-input>
    </el-form-item>
    <el-form-item label="收货人地区名称(省，市，县)街道" prop="receiverAreaName">
      <el-input v-model="dataForm.receiverAreaName" placeholder="收货人地区名称(省，市，县)街道"></el-input>
    </el-form-item>
    <el-form-item label="收货人手机" prop="receiverMobile">
      <el-input v-model="dataForm.receiverMobile" placeholder="收货人手机"></el-input>
    </el-form-item>
    <el-form-item label="收货人邮编" prop="receiverZipCode">
      <el-input v-model="dataForm.receiverZipCode" placeholder="收货人邮编"></el-input>
    </el-form-item>
    <el-form-item label="收货人" prop="receiver">
      <el-input v-model="dataForm.receiver" placeholder="收货人"></el-input>
    </el-form-item>
    <el-form-item label="过期时间，定期清理" prop="expire">
      <el-input v-model="dataForm.expire" placeholder="过期时间，定期清理"></el-input>
    </el-form-item>
    <el-form-item label="发票类型(普通发票，电子发票，增值税发票)" prop="invoiceType">
      <el-input v-model="dataForm.invoiceType" placeholder="发票类型(普通发票，电子发票，增值税发票)"></el-input>
    </el-form-item>
    <el-form-item label="订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端" prop="sourceType">
      <el-input v-model="dataForm.sourceType" placeholder="订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端"></el-input>
    </el-form-item>
    <el-form-item label="商家ID" prop="sellerId">
      <el-input v-model="dataForm.sellerId" placeholder="商家ID"></el-input>
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
          orderId: 0,
          payment: '',
          paymentType: '',
          postFee: '',
          status: '',
          createTime: '',
          updateTime: '',
          paymentTime: '',
          consignTime: '',
          endTime: '',
          closeTime: '',
          shippingName: '',
          shippingCode: '',
          userId: '',
          buyerMessage: '',
          buyerNick: '',
          buyerRate: '',
          receiverAreaName: '',
          receiverMobile: '',
          receiverZipCode: '',
          receiver: '',
          expire: '',
          invoiceType: '',
          sourceType: '',
          sellerId: ''
        },
        dataRule: {
          payment: [
            { required: true, message: '实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分不能为空', trigger: 'blur' }
          ],
          paymentType: [
            { required: true, message: '支付类型，1、在线支付，2、货到付款不能为空', trigger: 'blur' }
          ],
          postFee: [
            { required: true, message: '邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '订单创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '订单更新时间不能为空', trigger: 'blur' }
          ],
          paymentTime: [
            { required: true, message: '付款时间不能为空', trigger: 'blur' }
          ],
          consignTime: [
            { required: true, message: '发货时间不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '交易完成时间不能为空', trigger: 'blur' }
          ],
          closeTime: [
            { required: true, message: '交易关闭时间不能为空', trigger: 'blur' }
          ],
          shippingName: [
            { required: true, message: '物流名称不能为空', trigger: 'blur' }
          ],
          shippingCode: [
            { required: true, message: '物流单号不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          buyerMessage: [
            { required: true, message: '买家留言不能为空', trigger: 'blur' }
          ],
          buyerNick: [
            { required: true, message: '买家昵称不能为空', trigger: 'blur' }
          ],
          buyerRate: [
            { required: true, message: '买家是否已经评价不能为空', trigger: 'blur' }
          ],
          receiverAreaName: [
            { required: true, message: '收货人地区名称(省，市，县)街道不能为空', trigger: 'blur' }
          ],
          receiverMobile: [
            { required: true, message: '收货人手机不能为空', trigger: 'blur' }
          ],
          receiverZipCode: [
            { required: true, message: '收货人邮编不能为空', trigger: 'blur' }
          ],
          receiver: [
            { required: true, message: '收货人不能为空', trigger: 'blur' }
          ],
          expire: [
            { required: true, message: '过期时间，定期清理不能为空', trigger: 'blur' }
          ],
          invoiceType: [
            { required: true, message: '发票类型(普通发票，电子发票，增值税发票)不能为空', trigger: 'blur' }
          ],
          sourceType: [
            { required: true, message: '订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端不能为空', trigger: 'blur' }
          ],
          sellerId: [
            { required: true, message: '商家ID不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.orderId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.orderId) {
            this.$http({
              url: this.$http.adornUrl(`/manager/order/info/${this.dataForm.orderId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.payment = data.order.payment
                this.dataForm.paymentType = data.order.paymentType
                this.dataForm.postFee = data.order.postFee
                this.dataForm.status = data.order.status
                this.dataForm.createTime = data.order.createTime
                this.dataForm.updateTime = data.order.updateTime
                this.dataForm.paymentTime = data.order.paymentTime
                this.dataForm.consignTime = data.order.consignTime
                this.dataForm.endTime = data.order.endTime
                this.dataForm.closeTime = data.order.closeTime
                this.dataForm.shippingName = data.order.shippingName
                this.dataForm.shippingCode = data.order.shippingCode
                this.dataForm.userId = data.order.userId
                this.dataForm.buyerMessage = data.order.buyerMessage
                this.dataForm.buyerNick = data.order.buyerNick
                this.dataForm.buyerRate = data.order.buyerRate
                this.dataForm.receiverAreaName = data.order.receiverAreaName
                this.dataForm.receiverMobile = data.order.receiverMobile
                this.dataForm.receiverZipCode = data.order.receiverZipCode
                this.dataForm.receiver = data.order.receiver
                this.dataForm.expire = data.order.expire
                this.dataForm.invoiceType = data.order.invoiceType
                this.dataForm.sourceType = data.order.sourceType
                this.dataForm.sellerId = data.order.sellerId
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
              url: this.$http.adornUrl(`/manager/order/${!this.dataForm.orderId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'orderId': this.dataForm.orderId || undefined,
                'payment': this.dataForm.payment,
                'paymentType': this.dataForm.paymentType,
                'postFee': this.dataForm.postFee,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'paymentTime': this.dataForm.paymentTime,
                'consignTime': this.dataForm.consignTime,
                'endTime': this.dataForm.endTime,
                'closeTime': this.dataForm.closeTime,
                'shippingName': this.dataForm.shippingName,
                'shippingCode': this.dataForm.shippingCode,
                'userId': this.dataForm.userId,
                'buyerMessage': this.dataForm.buyerMessage,
                'buyerNick': this.dataForm.buyerNick,
                'buyerRate': this.dataForm.buyerRate,
                'receiverAreaName': this.dataForm.receiverAreaName,
                'receiverMobile': this.dataForm.receiverMobile,
                'receiverZipCode': this.dataForm.receiverZipCode,
                'receiver': this.dataForm.receiver,
                'expire': this.dataForm.expire,
                'invoiceType': this.dataForm.invoiceType,
                'sourceType': this.dataForm.sourceType,
                'sellerId': this.dataForm.sellerId
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
