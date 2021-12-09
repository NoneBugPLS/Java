<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="父类目ID=0时，代表的是一级的类目" prop="parentId">
      <el-input v-model="dataForm.parentId" placeholder="父类目ID=0时，代表的是一级的类目"></el-input>
    </el-form-item>
    <el-form-item label="类目名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="类目名称"></el-input>
    </el-form-item>
    <el-form-item label="类型id" prop="typeId">
      <el-input v-model="dataForm.typeId" placeholder="类型id"></el-input>
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
          parentId: '',
          name: '',
          typeId: ''
        },
        dataRule: {
          parentId: [
            { required: true, message: '父类目ID=0时，代表的是一级的类目不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '类目名称不能为空', trigger: 'blur' }
          ],
          typeId: [
            { required: true, message: '类型id不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/itemcat/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.parentId = data.itemCat.parentId
                this.dataForm.name = data.itemCat.name
                this.dataForm.typeId = data.itemCat.typeId
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
              url: this.$http.adornUrl(`/manager/itemcat/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'parentId': this.dataForm.parentId,
                'name': this.dataForm.name,
                'typeId': this.dataForm.typeId
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
