<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="spu ID" prop="goodsId">
      <el-input v-model="dataForm.goodsId" placeholder="spu ID"></el-input>
    </el-form-item>
    <el-form-item label="sku ID" prop="itemId">
      <el-input v-model="dataForm.itemId" placeholder="sku ID"></el-input>
    </el-form-item>
    <el-form-item label="标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="标题"></el-input>
    </el-form-item>
    <el-form-item label="商品图片" prop="smallPic">
      <el-input v-model="dataForm.smallPic" placeholder="商品图片"></el-input>
    </el-form-item>
    <el-form-item label="原价格" prop="price">
      <el-input v-model="dataForm.price" placeholder="原价格"></el-input>
    </el-form-item>
    <el-form-item label="秒杀价格" prop="costPrice">
      <el-input v-model="dataForm.costPrice" placeholder="秒杀价格"></el-input>
    </el-form-item>
    <el-form-item label="商家ID" prop="sellerId">
      <el-input v-model="dataForm.sellerId" placeholder="商家ID"></el-input>
    </el-form-item>
    <el-form-item label="添加日期" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="添加日期"></el-input>
    </el-form-item>
    <el-form-item label="审核日期" prop="checkTime">
      <el-input v-model="dataForm.checkTime" placeholder="审核日期"></el-input>
    </el-form-item>
    <el-form-item label="审核状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="审核状态"></el-input>
    </el-form-item>
    <el-form-item label="开始时间" prop="startTime">
      <el-input v-model="dataForm.startTime" placeholder="开始时间"></el-input>
    </el-form-item>
    <el-form-item label="结束时间" prop="endTime">
      <el-input v-model="dataForm.endTime" placeholder="结束时间"></el-input>
    </el-form-item>
    <el-form-item label="秒杀商品数" prop="num">
      <el-input v-model="dataForm.num" placeholder="秒杀商品数"></el-input>
    </el-form-item>
    <el-form-item label="剩余库存数" prop="stockCount">
      <el-input v-model="dataForm.stockCount" placeholder="剩余库存数"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="introduction">
      <el-input v-model="dataForm.introduction" placeholder="描述"></el-input>
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
          goodsId: '',
          itemId: '',
          title: '',
          smallPic: '',
          price: '',
          costPrice: '',
          sellerId: '',
          createTime: '',
          checkTime: '',
          status: '',
          startTime: '',
          endTime: '',
          num: '',
          stockCount: '',
          introduction: ''
        },
        dataRule: {
          goodsId: [
            { required: true, message: 'spu ID不能为空', trigger: 'blur' }
          ],
          itemId: [
            { required: true, message: 'sku ID不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '标题不能为空', trigger: 'blur' }
          ],
          smallPic: [
            { required: true, message: '商品图片不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '原价格不能为空', trigger: 'blur' }
          ],
          costPrice: [
            { required: true, message: '秒杀价格不能为空', trigger: 'blur' }
          ],
          sellerId: [
            { required: true, message: '商家ID不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '添加日期不能为空', trigger: 'blur' }
          ],
          checkTime: [
            { required: true, message: '审核日期不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '审核状态不能为空', trigger: 'blur' }
          ],
          startTime: [
            { required: true, message: '开始时间不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '结束时间不能为空', trigger: 'blur' }
          ],
          num: [
            { required: true, message: '秒杀商品数不能为空', trigger: 'blur' }
          ],
          stockCount: [
            { required: true, message: '剩余库存数不能为空', trigger: 'blur' }
          ],
          introduction: [
            { required: true, message: '描述不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/seckillgoods/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.goodsId = data.seckillGoods.goodsId
                this.dataForm.itemId = data.seckillGoods.itemId
                this.dataForm.title = data.seckillGoods.title
                this.dataForm.smallPic = data.seckillGoods.smallPic
                this.dataForm.price = data.seckillGoods.price
                this.dataForm.costPrice = data.seckillGoods.costPrice
                this.dataForm.sellerId = data.seckillGoods.sellerId
                this.dataForm.createTime = data.seckillGoods.createTime
                this.dataForm.checkTime = data.seckillGoods.checkTime
                this.dataForm.status = data.seckillGoods.status
                this.dataForm.startTime = data.seckillGoods.startTime
                this.dataForm.endTime = data.seckillGoods.endTime
                this.dataForm.num = data.seckillGoods.num
                this.dataForm.stockCount = data.seckillGoods.stockCount
                this.dataForm.introduction = data.seckillGoods.introduction
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
              url: this.$http.adornUrl(`/manager/seckillgoods/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'goodsId': this.dataForm.goodsId,
                'itemId': this.dataForm.itemId,
                'title': this.dataForm.title,
                'smallPic': this.dataForm.smallPic,
                'price': this.dataForm.price,
                'costPrice': this.dataForm.costPrice,
                'sellerId': this.dataForm.sellerId,
                'createTime': this.dataForm.createTime,
                'checkTime': this.dataForm.checkTime,
                'status': this.dataForm.status,
                'startTime': this.dataForm.startTime,
                'endTime': this.dataForm.endTime,
                'num': this.dataForm.num,
                'stockCount': this.dataForm.stockCount,
                'introduction': this.dataForm.introduction
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
