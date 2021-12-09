<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    {{dataForm}}===
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
    <el-form-item label="名称" prop="specName">
      <el-input v-model="dataForm.spec.specName" placeholder="名称"></el-input>
    </el-form-item>
      <el-button type="primary" plain size="small" @click="dataForm.options.push({})">新增规格选项</el-button>
      <el-table
        :data="dataForm.options"
        border
        style="width: 100%;">
        <el-table-column
          prop="id"
          header-align="center"
          align="center"
          label="规格选项">
          <template slot-scope="scope">
            <el-input v-model="scope.row.optionName"></el-input>
          </template>
        </el-table-column>
        <el-table-column
          prop="specName"
          header-align="center"
          align="center"
          label="排序">
          <template slot-scope="scope">
            <el-input v-model="scope.row.orders"></el-input>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          header-align="center"
          align="center"
          width="150"
          label="操作">
          <template slot-scope="scope">
            <el-button type="danger" size="small" @click="dataForm.options.splice(scope.index,1)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
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
        dataForm: {       //    列表信息对象
          spec: {           //规格对象
            specName: '',
          },
          options:[],       //代表规格选项
        },
        dataRule: {
          spec:{
            specName: [
              { required: true, message: '名称不能为空', trigger: 'blur' }
            ]
          }
        }
      }
    },
    methods: {
      init (id=0) {
        this.dataForm.id = id
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/manager/specification/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.spec = data.specification.spec
                this.dataForm.options = data.specification.options
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
          let data = this.$http.adornData({
            'id': this.dataForm.id || undefined,
            'spec': this.dataForm.spec,
            'options':this.dataForm.options
          })
        console.log("data:",data);
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/manager/specification/${!this.dataForm.id ? 'save' : 'update'}`),
              method: `${this.dataForm.id ? 'put' : 'post'}`,
              data
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
