<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="规格项名称" prop="optionName">
      <el-input v-model="dataForm.optionName" placeholder="规格项名称"></el-input>
    </el-form-item>
    <el-form-item label="规格ID" prop="specId">
      <el-input v-model="dataForm.specId" placeholder="规格ID"></el-input>
    </el-form-item>
    <el-form-item label="排序值" prop="orders">
      <el-input v-model="dataForm.orders" placeholder="排序值"></el-input>
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
          optionName: '',
          specId: '',
          orders: ''
        },
        dataRule: {
          optionName: [
            { required: true, message: '规格项名称不能为空', trigger: 'blur' }
          ],
          specId: [
            { required: true, message: '规格ID不能为空', trigger: 'blur' }
          ],
          orders: [
            { required: true, message: '排序值不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/specificationoption/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.optionName = data.specificationOption.optionName
                this.dataForm.specId = data.specificationOption.specId
                this.dataForm.orders = data.specificationOption.orders
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
              url: this.$http.adornUrl(`/manager/specificationoption/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'optionName': this.dataForm.optionName,
                'specId': this.dataForm.specId,
                'orders': this.dataForm.orders
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
