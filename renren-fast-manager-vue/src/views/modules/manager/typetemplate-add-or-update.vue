<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
    <el-form-item label="模板名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="模板名称"></el-input>
    </el-form-item>
    <el-form-item label="关联规格" prop="specIds">
      <el-input v-model="dataForm.specIds" placeholder="关联规格"></el-input>
    </el-form-item>
    <el-form-item label="关联品牌" prop="brandIds">
      <el-input v-model="dataForm.brandIds" placeholder="关联品牌"></el-input>
    </el-form-item>
    <el-form-item label="自定义属性" prop="customAttributeItems">
      <el-input v-model="dataForm.customAttributeItems" placeholder="自定义属性"></el-input>
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
          name: '',
          specIds: '',
          brandIds: '',
          customAttributeItems: ''
        },
        dataRule: {
          name: [
            { required: true, message: '模板名称不能为空', trigger: 'blur' }
          ],
          specIds: [
            { required: true, message: '关联规格不能为空', trigger: 'blur' }
          ],
          brandIds: [
            { required: true, message: '关联品牌不能为空', trigger: 'blur' }
          ],
          customAttributeItems: [
            { required: true, message: '自定义属性不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/typetemplate/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.typeTemplate.name
                this.dataForm.specIds = data.typeTemplate.specIds
                this.dataForm.brandIds = data.typeTemplate.brandIds
                this.dataForm.customAttributeItems = data.typeTemplate.customAttributeItems
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
              url: this.$http.adornUrl(`/manager/typetemplate/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'specIds': this.dataForm.specIds,
                'brandIds': this.dataForm.brandIds,
                'customAttributeItems': this.dataForm.customAttributeItems
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
