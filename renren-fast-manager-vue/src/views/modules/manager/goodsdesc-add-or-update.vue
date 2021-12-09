<template>
  <el-dialog
    :title="!dataForm.goodsId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="描述" prop="introduction">
      <el-input v-model="dataForm.introduction" placeholder="描述"></el-input>
    </el-form-item>
    <el-form-item label="规格结果集，所有规格，包含isSelected" prop="specificationItems">
      <el-input v-model="dataForm.specificationItems" placeholder="规格结果集，所有规格，包含isSelected"></el-input>
    </el-form-item>
    <el-form-item label="自定义属性（参数结果）" prop="customAttributeItems">
      <el-input v-model="dataForm.customAttributeItems" placeholder="自定义属性（参数结果）"></el-input>
    </el-form-item>
    <el-form-item label="" prop="itemImages">
      <el-input v-model="dataForm.itemImages" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="包装列表" prop="packageList">
      <el-input v-model="dataForm.packageList" placeholder="包装列表"></el-input>
    </el-form-item>
    <el-form-item label="售后服务" prop="saleService">
      <el-input v-model="dataForm.saleService" placeholder="售后服务"></el-input>
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
          goodsId: 0,
          introduction: '',
          specificationItems: '',
          customAttributeItems: '',
          itemImages: '',
          packageList: '',
          saleService: ''
        },
        dataRule: {
          introduction: [
            { required: true, message: '描述不能为空', trigger: 'blur' }
          ],
          specificationItems: [
            { required: true, message: '规格结果集，所有规格，包含isSelected不能为空', trigger: 'blur' }
          ],
          customAttributeItems: [
            { required: true, message: '自定义属性（参数结果）不能为空', trigger: 'blur' }
          ],
          itemImages: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          packageList: [
            { required: true, message: '包装列表不能为空', trigger: 'blur' }
          ],
          saleService: [
            { required: true, message: '售后服务不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.goodsId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.goodsId) {
            this.$http({
              url: this.$http.adornUrl(`/manager/goodsdesc/info/${this.dataForm.goodsId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.introduction = data.goodsDesc.introduction
                this.dataForm.specificationItems = data.goodsDesc.specificationItems
                this.dataForm.customAttributeItems = data.goodsDesc.customAttributeItems
                this.dataForm.itemImages = data.goodsDesc.itemImages
                this.dataForm.packageList = data.goodsDesc.packageList
                this.dataForm.saleService = data.goodsDesc.saleService
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
              url: this.$http.adornUrl(`/manager/goodsdesc/${!this.dataForm.goodsId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'goodsId': this.dataForm.goodsId || undefined,
                'introduction': this.dataForm.introduction,
                'specificationItems': this.dataForm.specificationItems,
                'customAttributeItems': this.dataForm.customAttributeItems,
                'itemImages': this.dataForm.itemImages,
                'packageList': this.dataForm.packageList,
                'saleService': this.dataForm.saleService
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
