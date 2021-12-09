<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="商家ID" prop="sellerId">
      <el-input v-model="dataForm.sellerId" placeholder="商家ID"></el-input>
    </el-form-item>
    <el-form-item label="是否默认   （‘Y’是   'N'否）" prop="isDefault">
      <el-input v-model="dataForm.isDefault" placeholder="是否默认   （‘Y’是   'N'否）"></el-input>
    </el-form-item>
    <el-form-item label="模版名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="模版名称"></el-input>
    </el-form-item>
    <el-form-item label="发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）" prop="sendTimeType">
      <el-input v-model="dataForm.sendTimeType" placeholder="发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）"></el-input>
    </el-form-item>
    <el-form-item label="统一价格" prop="price">
      <el-input v-model="dataForm.price" placeholder="统一价格"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
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
          isDefault: '',
          name: '',
          sendTimeType: '',
          price: '',
          createTime: ''
        },
        dataRule: {
          sellerId: [
            { required: true, message: '商家ID不能为空', trigger: 'blur' }
          ],
          isDefault: [
            { required: true, message: "是否默认   （‘Y’是   'N'否）不能为空", trigger: 'blur' }
          ],
          name: [
            { required: true, message: '模版名称不能为空', trigger: 'blur' }
          ],
          sendTimeType: [
            { required: true, message: '发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '统一价格不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/freighttemplate/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sellerId = data.freightTemplate.sellerId
                this.dataForm.isDefault = data.freightTemplate.isDefault
                this.dataForm.name = data.freightTemplate.name
                this.dataForm.sendTimeType = data.freightTemplate.sendTimeType
                this.dataForm.price = data.freightTemplate.price
                this.dataForm.createTime = data.freightTemplate.createTime
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
              url: this.$http.adornUrl(`/manager/freighttemplate/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'sellerId': this.dataForm.sellerId,
                'isDefault': this.dataForm.isDefault,
                'name': this.dataForm.name,
                'sendTimeType': this.dataForm.sendTimeType,
                'price': this.dataForm.price,
                'createTime': this.dataForm.createTime
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
