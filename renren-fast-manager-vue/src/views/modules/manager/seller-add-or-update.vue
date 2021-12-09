<template>
  <el-dialog
    :title="!dataForm.sellerId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="公司名" prop="name">
      <el-input v-model="dataForm.name" placeholder="公司名"></el-input>
    </el-form-item>
    <el-form-item label="店铺名称" prop="nickName">
      <el-input v-model="dataForm.nickName" placeholder="店铺名称"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="EMAIL" prop="email">
      <el-input v-model="dataForm.email" placeholder="EMAIL"></el-input>
    </el-form-item>
    <el-form-item label="公司手机" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="公司手机"></el-input>
    </el-form-item>
    <el-form-item label="公司电话" prop="telephone">
      <el-input v-model="dataForm.telephone" placeholder="公司电话"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="详细地址" prop="addressDetail">
      <el-input v-model="dataForm.addressDetail" placeholder="详细地址"></el-input>
    </el-form-item>
    <el-form-item label="联系人姓名" prop="linkmanName">
      <el-input v-model="dataForm.linkmanName" placeholder="联系人姓名"></el-input>
    </el-form-item>
    <el-form-item label="联系人QQ" prop="linkmanQq">
      <el-input v-model="dataForm.linkmanQq" placeholder="联系人QQ"></el-input>
    </el-form-item>
    <el-form-item label="联系人电话" prop="linkmanMobile">
      <el-input v-model="dataForm.linkmanMobile" placeholder="联系人电话"></el-input>
    </el-form-item>
    <el-form-item label="联系人EMAIL" prop="linkmanEmail">
      <el-input v-model="dataForm.linkmanEmail" placeholder="联系人EMAIL"></el-input>
    </el-form-item>
    <el-form-item label="营业执照号" prop="licenseNumber">
      <el-input v-model="dataForm.licenseNumber" placeholder="营业执照号"></el-input>
    </el-form-item>
    <el-form-item label="税务登记证号" prop="taxNumber">
      <el-input v-model="dataForm.taxNumber" placeholder="税务登记证号"></el-input>
    </el-form-item>
    <el-form-item label="组织机构代码" prop="orgNumber">
      <el-input v-model="dataForm.orgNumber" placeholder="组织机构代码"></el-input>
    </el-form-item>
    <el-form-item label="公司地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="公司地址"></el-input>
    </el-form-item>
    <el-form-item label="公司LOGO图" prop="logoPic">
      <el-input v-model="dataForm.logoPic" placeholder="公司LOGO图"></el-input>
    </el-form-item>
    <el-form-item label="简介" prop="brief">
      <el-input v-model="dataForm.brief" placeholder="简介"></el-input>
    </el-form-item>
    <el-form-item label="创建日期" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建日期"></el-input>
    </el-form-item>
    <el-form-item label="法定代表人" prop="legalPerson">
      <el-input v-model="dataForm.legalPerson" placeholder="法定代表人"></el-input>
    </el-form-item>
    <el-form-item label="法定代表人身份证" prop="legalPersonCardId">
      <el-input v-model="dataForm.legalPersonCardId" placeholder="法定代表人身份证"></el-input>
    </el-form-item>
    <el-form-item label="开户行账号名称" prop="bankUser">
      <el-input v-model="dataForm.bankUser" placeholder="开户行账号名称"></el-input>
    </el-form-item>
    <el-form-item label="开户行" prop="bankName">
      <el-input v-model="dataForm.bankName" placeholder="开户行"></el-input>
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
          sellerId: 0,
          name: '',
          nickName: '',
          password: '',
          email: '',
          mobile: '',
          telephone: '',
          status: '',
          addressDetail: '',
          linkmanName: '',
          linkmanQq: '',
          linkmanMobile: '',
          linkmanEmail: '',
          licenseNumber: '',
          taxNumber: '',
          orgNumber: '',
          address: '',
          logoPic: '',
          brief: '',
          createTime: '',
          legalPerson: '',
          legalPersonCardId: '',
          bankUser: '',
          bankName: ''
        },
        dataRule: {
          name: [
            { required: true, message: '公司名不能为空', trigger: 'blur' }
          ],
          nickName: [
            { required: true, message: '店铺名称不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: 'EMAIL不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '公司手机不能为空', trigger: 'blur' }
          ],
          telephone: [
            { required: true, message: '公司电话不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          addressDetail: [
            { required: true, message: '详细地址不能为空', trigger: 'blur' }
          ],
          linkmanName: [
            { required: true, message: '联系人姓名不能为空', trigger: 'blur' }
          ],
          linkmanQq: [
            { required: true, message: '联系人QQ不能为空', trigger: 'blur' }
          ],
          linkmanMobile: [
            { required: true, message: '联系人电话不能为空', trigger: 'blur' }
          ],
          linkmanEmail: [
            { required: true, message: '联系人EMAIL不能为空', trigger: 'blur' }
          ],
          licenseNumber: [
            { required: true, message: '营业执照号不能为空', trigger: 'blur' }
          ],
          taxNumber: [
            { required: true, message: '税务登记证号不能为空', trigger: 'blur' }
          ],
          orgNumber: [
            { required: true, message: '组织机构代码不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '公司地址不能为空', trigger: 'blur' }
          ],
          logoPic: [
            { required: true, message: '公司LOGO图不能为空', trigger: 'blur' }
          ],
          brief: [
            { required: true, message: '简介不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建日期不能为空', trigger: 'blur' }
          ],
          legalPerson: [
            { required: true, message: '法定代表人不能为空', trigger: 'blur' }
          ],
          legalPersonCardId: [
            { required: true, message: '法定代表人身份证不能为空', trigger: 'blur' }
          ],
          bankUser: [
            { required: true, message: '开户行账号名称不能为空', trigger: 'blur' }
          ],
          bankName: [
            { required: true, message: '开户行不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.sellerId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.sellerId) {
            this.$http({
              url: this.$http.adornUrl(`/manager/seller/info/${this.dataForm.sellerId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.seller.name
                this.dataForm.nickName = data.seller.nickName
                this.dataForm.password = data.seller.password
                this.dataForm.email = data.seller.email
                this.dataForm.mobile = data.seller.mobile
                this.dataForm.telephone = data.seller.telephone
                this.dataForm.status = data.seller.status
                this.dataForm.addressDetail = data.seller.addressDetail
                this.dataForm.linkmanName = data.seller.linkmanName
                this.dataForm.linkmanQq = data.seller.linkmanQq
                this.dataForm.linkmanMobile = data.seller.linkmanMobile
                this.dataForm.linkmanEmail = data.seller.linkmanEmail
                this.dataForm.licenseNumber = data.seller.licenseNumber
                this.dataForm.taxNumber = data.seller.taxNumber
                this.dataForm.orgNumber = data.seller.orgNumber
                this.dataForm.address = data.seller.address
                this.dataForm.logoPic = data.seller.logoPic
                this.dataForm.brief = data.seller.brief
                this.dataForm.createTime = data.seller.createTime
                this.dataForm.legalPerson = data.seller.legalPerson
                this.dataForm.legalPersonCardId = data.seller.legalPersonCardId
                this.dataForm.bankUser = data.seller.bankUser
                this.dataForm.bankName = data.seller.bankName
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
              url: this.$http.adornUrl(`/manager/seller/${!this.dataForm.sellerId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'sellerId': this.dataForm.sellerId || undefined,
                'name': this.dataForm.name,
                'nickName': this.dataForm.nickName,
                'password': this.dataForm.password,
                'email': this.dataForm.email,
                'mobile': this.dataForm.mobile,
                'telephone': this.dataForm.telephone,
                'status': this.dataForm.status,
                'addressDetail': this.dataForm.addressDetail,
                'linkmanName': this.dataForm.linkmanName,
                'linkmanQq': this.dataForm.linkmanQq,
                'linkmanMobile': this.dataForm.linkmanMobile,
                'linkmanEmail': this.dataForm.linkmanEmail,
                'licenseNumber': this.dataForm.licenseNumber,
                'taxNumber': this.dataForm.taxNumber,
                'orgNumber': this.dataForm.orgNumber,
                'address': this.dataForm.address,
                'logoPic': this.dataForm.logoPic,
                'brief': this.dataForm.brief,
                'createTime': this.dataForm.createTime,
                'legalPerson': this.dataForm.legalPerson,
                'legalPersonCardId': this.dataForm.legalPersonCardId,
                'bankUser': this.dataForm.bankUser,
                'bankName': this.dataForm.bankName
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
