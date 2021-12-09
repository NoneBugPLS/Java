<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商品标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="商品标题"></el-input>
    </el-form-item>
    <el-form-item label="商品卖点" prop="sellPoint">
      <el-input v-model="dataForm.sellPoint" placeholder="商品卖点"></el-input>
    </el-form-item>
    <el-form-item label="商品价格，单位为：元" prop="price">
      <el-input v-model="dataForm.price" placeholder="商品价格，单位为：元"></el-input>
    </el-form-item>
    <el-form-item label="" prop="stockCount">
      <el-input v-model="dataForm.stockCount" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="库存数量" prop="num">
      <el-input v-model="dataForm.num" placeholder="库存数量"></el-input>
    </el-form-item>
    <el-form-item label="商品条形码" prop="barcode">
      <el-input v-model="dataForm.barcode" placeholder="商品条形码"></el-input>
    </el-form-item>
    <el-form-item label="商品图片" prop="image">
      <el-input v-model="dataForm.image" placeholder="商品图片"></el-input>
    </el-form-item>
    <el-form-item label="所属类目，叶子类目" prop="categoryid">
      <el-input v-model="dataForm.categoryid" placeholder="所属类目，叶子类目"></el-input>
    </el-form-item>
    <el-form-item label="商品状态，1-正常，2-下架，3-删除" prop="status">
      <el-input v-model="dataForm.status" placeholder="商品状态，1-正常，2-下架，3-删除"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    <el-form-item label="" prop="itemSn">
      <el-input v-model="dataForm.itemSn" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="costPirce">
      <el-input v-model="dataForm.costPirce" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="marketPrice">
      <el-input v-model="dataForm.marketPrice" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="isDefault">
      <el-input v-model="dataForm.isDefault" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="goodsId">
      <el-input v-model="dataForm.goodsId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="sellerId">
      <el-input v-model="dataForm.sellerId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="cartThumbnail">
      <el-input v-model="dataForm.cartThumbnail" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="category">
      <el-input v-model="dataForm.category" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="brand">
      <el-input v-model="dataForm.brand" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="spec">
      <el-input v-model="dataForm.spec" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="seller">
      <el-input v-model="dataForm.seller" placeholder=""></el-input>
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
          title: '',
          sellPoint: '',
          price: '',
          stockCount: '',
          num: '',
          barcode: '',
          image: '',
          categoryid: '',
          status: '',
          createTime: '',
          updateTime: '',
          itemSn: '',
          costPirce: '',
          marketPrice: '',
          isDefault: '',
          goodsId: '',
          sellerId: '',
          cartThumbnail: '',
          category: '',
          brand: '',
          spec: '',
          seller: ''
        },
        dataRule: {
          title: [
            { required: true, message: '商品标题不能为空', trigger: 'blur' }
          ],
          sellPoint: [
            { required: true, message: '商品卖点不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '商品价格，单位为：元不能为空', trigger: 'blur' }
          ],
          stockCount: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          num: [
            { required: true, message: '库存数量不能为空', trigger: 'blur' }
          ],
          barcode: [
            { required: true, message: '商品条形码不能为空', trigger: 'blur' }
          ],
          image: [
            { required: true, message: '商品图片不能为空', trigger: 'blur' }
          ],
          categoryid: [
            { required: true, message: '所属类目，叶子类目不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '商品状态，1-正常，2-下架，3-删除不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ],
          itemSn: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          costPirce: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          marketPrice: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          isDefault: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          goodsId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          sellerId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          cartThumbnail: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          category: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          brand: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          spec: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          seller: [
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
              url: this.$http.adornUrl(`/manager/item/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.title = data.item.title
                this.dataForm.sellPoint = data.item.sellPoint
                this.dataForm.price = data.item.price
                this.dataForm.stockCount = data.item.stockCount
                this.dataForm.num = data.item.num
                this.dataForm.barcode = data.item.barcode
                this.dataForm.image = data.item.image
                this.dataForm.categoryid = data.item.categoryid
                this.dataForm.status = data.item.status
                this.dataForm.createTime = data.item.createTime
                this.dataForm.updateTime = data.item.updateTime
                this.dataForm.itemSn = data.item.itemSn
                this.dataForm.costPirce = data.item.costPirce
                this.dataForm.marketPrice = data.item.marketPrice
                this.dataForm.isDefault = data.item.isDefault
                this.dataForm.goodsId = data.item.goodsId
                this.dataForm.sellerId = data.item.sellerId
                this.dataForm.cartThumbnail = data.item.cartThumbnail
                this.dataForm.category = data.item.category
                this.dataForm.brand = data.item.brand
                this.dataForm.spec = data.item.spec
                this.dataForm.seller = data.item.seller
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
              url: this.$http.adornUrl(`/manager/item/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'title': this.dataForm.title,
                'sellPoint': this.dataForm.sellPoint,
                'price': this.dataForm.price,
                'stockCount': this.dataForm.stockCount,
                'num': this.dataForm.num,
                'barcode': this.dataForm.barcode,
                'image': this.dataForm.image,
                'categoryid': this.dataForm.categoryid,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime,
                'itemSn': this.dataForm.itemSn,
                'costPirce': this.dataForm.costPirce,
                'marketPrice': this.dataForm.marketPrice,
                'isDefault': this.dataForm.isDefault,
                'goodsId': this.dataForm.goodsId,
                'sellerId': this.dataForm.sellerId,
                'cartThumbnail': this.dataForm.cartThumbnail,
                'category': this.dataForm.category,
                'brand': this.dataForm.brand,
                'spec': this.dataForm.spec,
                'seller': this.dataForm.seller
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
