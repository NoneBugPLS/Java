<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商家ID" prop="sellerId">
      <el-input v-model="dataForm.sellerId" placeholder="商家ID"></el-input>
    </el-form-item>
    <el-form-item label="SPU名" prop="goodsName">
      <el-input v-model="dataForm.goodsName" placeholder="SPU名"></el-input>
    </el-form-item>
    <el-form-item label="默认SKU" prop="defaultItemId">
      <el-input v-model="dataForm.defaultItemId" placeholder="默认SKU"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="auditStatus">
      <el-input v-model="dataForm.auditStatus" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="是否上架" prop="isMarketable">
      <el-input v-model="dataForm.isMarketable" placeholder="是否上架"></el-input>
    </el-form-item>
    <el-form-item label="品牌" prop="brandId">
      <el-input v-model="dataForm.brandId" placeholder="品牌"></el-input>
    </el-form-item>
    <el-form-item label="副标题" prop="caption">
      <el-input v-model="dataForm.caption" placeholder="副标题"></el-input>
    </el-form-item>
    <el-form-item label="一级类目" prop="category1Id">
      <el-input v-model="dataForm.category1Id" placeholder="一级类目"></el-input>
    </el-form-item>
    <el-form-item label="二级类目" prop="category2Id">
      <el-input v-model="dataForm.category2Id" placeholder="二级类目"></el-input>
    </el-form-item>
    <el-form-item label="三级类目" prop="category3Id">
      <el-input v-model="dataForm.category3Id" placeholder="三级类目"></el-input>
    </el-form-item>
    <el-form-item label="小图" prop="smallPic">
      <el-input v-model="dataForm.smallPic" placeholder="小图"></el-input>
    </el-form-item>
    <el-form-item label="商城价" prop="price">
      <el-input v-model="dataForm.price" placeholder="商城价"></el-input>
    </el-form-item>
    <el-form-item label="分类模板ID" prop="typeTemplateId">
      <el-input v-model="dataForm.typeTemplateId" placeholder="分类模板ID"></el-input>
    </el-form-item>
    <el-form-item label="是否启用规格" prop="isEnableSpec">
      <el-input v-model="dataForm.isEnableSpec" placeholder="是否启用规格"></el-input>
    </el-form-item>
    <el-form-item label="是否删除" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="是否删除"></el-input>
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
          sellerId: '',
          goodsName: '',
          defaultItemId: '',
          auditStatus: '',
          isMarketable: '',
          brandId: '',
          caption: '',
          category1Id: '',
          category2Id: '',
          category3Id: '',
          smallPic: '',
          price: '',
          typeTemplateId: '',
          isEnableSpec: '',
          isDelete: ''
        },
        dataRule: {
          sellerId: [
            { required: true, message: '商家ID不能为空', trigger: 'blur' }
          ],
          goodsName: [
            { required: true, message: 'SPU名不能为空', trigger: 'blur' }
          ],
          defaultItemId: [
            { required: true, message: '默认SKU不能为空', trigger: 'blur' }
          ],
          auditStatus: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          isMarketable: [
            { required: true, message: '是否上架不能为空', trigger: 'blur' }
          ],
          brandId: [
            { required: true, message: '品牌不能为空', trigger: 'blur' }
          ],
          caption: [
            { required: true, message: '副标题不能为空', trigger: 'blur' }
          ],
          category1Id: [
            { required: true, message: '一级类目不能为空', trigger: 'blur' }
          ],
          category2Id: [
            { required: true, message: '二级类目不能为空', trigger: 'blur' }
          ],
          category3Id: [
            { required: true, message: '三级类目不能为空', trigger: 'blur' }
          ],
          smallPic: [
            { required: true, message: '小图不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '商城价不能为空', trigger: 'blur' }
          ],
          typeTemplateId: [
            { required: true, message: '分类模板ID不能为空', trigger: 'blur' }
          ],
          isEnableSpec: [
            { required: true, message: '是否启用规格不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '是否删除不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/goods/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sellerId = data.goods.sellerId
                this.dataForm.goodsName = data.goods.goodsName
                this.dataForm.defaultItemId = data.goods.defaultItemId
                this.dataForm.auditStatus = data.goods.auditStatus
                this.dataForm.isMarketable = data.goods.isMarketable
                this.dataForm.brandId = data.goods.brandId
                this.dataForm.caption = data.goods.caption
                this.dataForm.category1Id = data.goods.category1Id
                this.dataForm.category2Id = data.goods.category2Id
                this.dataForm.category3Id = data.goods.category3Id
                this.dataForm.smallPic = data.goods.smallPic
                this.dataForm.price = data.goods.price
                this.dataForm.typeTemplateId = data.goods.typeTemplateId
                this.dataForm.isEnableSpec = data.goods.isEnableSpec
                this.dataForm.isDelete = data.goods.isDelete
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
              url: this.$http.adornUrl(`/manager/goods/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'sellerId': this.dataForm.sellerId,
                'goodsName': this.dataForm.goodsName,
                'defaultItemId': this.dataForm.defaultItemId,
                'auditStatus': this.dataForm.auditStatus,
                'isMarketable': this.dataForm.isMarketable,
                'brandId': this.dataForm.brandId,
                'caption': this.dataForm.caption,
                'category1Id': this.dataForm.category1Id,
                'category2Id': this.dataForm.category2Id,
                'category3Id': this.dataForm.category3Id,
                'smallPic': this.dataForm.smallPic,
                'price': this.dataForm.price,
                'typeTemplateId': this.dataForm.typeTemplateId,
                'isEnableSpec': this.dataForm.isEnableSpec,
                'isDelete': this.dataForm.isDelete
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
