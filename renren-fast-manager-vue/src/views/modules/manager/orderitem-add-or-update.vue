<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商品id" prop="itemId">
      <el-input v-model="dataForm.itemId" placeholder="商品id"></el-input>
    </el-form-item>
    <el-form-item label="SPU_ID" prop="goodsId">
      <el-input v-model="dataForm.goodsId" placeholder="SPU_ID"></el-input>
    </el-form-item>
    <el-form-item label="订单id" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="订单id"></el-input>
    </el-form-item>
    <el-form-item label="商品标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="商品标题"></el-input>
    </el-form-item>
    <el-form-item label="商品单价" prop="price">
      <el-input v-model="dataForm.price" placeholder="商品单价"></el-input>
    </el-form-item>
    <el-form-item label="商品购买数量" prop="num">
      <el-input v-model="dataForm.num" placeholder="商品购买数量"></el-input>
    </el-form-item>
    <el-form-item label="商品总金额" prop="totalFee">
      <el-input v-model="dataForm.totalFee" placeholder="商品总金额"></el-input>
    </el-form-item>
    <el-form-item label="商品图片地址" prop="picPath">
      <el-input v-model="dataForm.picPath" placeholder="商品图片地址"></el-input>
    </el-form-item>
    <el-form-item label="" prop="sellerId">
      <el-input v-model="dataForm.sellerId" placeholder=""></el-input>
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
          itemId: '',
          goodsId: '',
          orderId: '',
          title: '',
          price: '',
          num: '',
          totalFee: '',
          picPath: '',
          sellerId: ''
        },
        dataRule: {
          itemId: [
            { required: true, message: '商品id不能为空', trigger: 'blur' }
          ],
          goodsId: [
            { required: true, message: 'SPU_ID不能为空', trigger: 'blur' }
          ],
          orderId: [
            { required: true, message: '订单id不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '商品标题不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '商品单价不能为空', trigger: 'blur' }
          ],
          num: [
            { required: true, message: '商品购买数量不能为空', trigger: 'blur' }
          ],
          totalFee: [
            { required: true, message: '商品总金额不能为空', trigger: 'blur' }
          ],
          picPath: [
            { required: true, message: '商品图片地址不能为空', trigger: 'blur' }
          ],
          sellerId: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/orderitem/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.itemId = data.orderItem.itemId
                this.dataForm.goodsId = data.orderItem.goodsId
                this.dataForm.orderId = data.orderItem.orderId
                this.dataForm.title = data.orderItem.title
                this.dataForm.price = data.orderItem.price
                this.dataForm.num = data.orderItem.num
                this.dataForm.totalFee = data.orderItem.totalFee
                this.dataForm.picPath = data.orderItem.picPath
                this.dataForm.sellerId = data.orderItem.sellerId
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
              url: this.$http.adornUrl(`/manager/orderitem/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'itemId': this.dataForm.itemId,
                'goodsId': this.dataForm.goodsId,
                'orderId': this.dataForm.orderId,
                'title': this.dataForm.title,
                'price': this.dataForm.price,
                'num': this.dataForm.num,
                'totalFee': this.dataForm.totalFee,
                'picPath': this.dataForm.picPath,
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
