<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码，加密存储" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码，加密存储"></el-input>
    </el-form-item>
    <el-form-item label="注册手机号" prop="phone">
      <el-input v-model="dataForm.phone" placeholder="注册手机号"></el-input>
    </el-form-item>
    <el-form-item label="注册邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="注册邮箱"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="created">
      <el-input v-model="dataForm.created" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="" prop="updated">
      <el-input v-model="dataForm.updated" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat" prop="sourceType">
      <el-input v-model="dataForm.sourceType" placeholder="会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="nickName">
      <el-input v-model="dataForm.nickName" placeholder="昵称"></el-input>
    </el-form-item>
    <el-form-item label="真实姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="真实姓名"></el-input>
    </el-form-item>
    <el-form-item label="使用状态（Y正常 N非正常）" prop="status">
      <el-input v-model="dataForm.status" placeholder="使用状态（Y正常 N非正常）"></el-input>
    </el-form-item>
    <el-form-item label="头像地址" prop="headPic">
      <el-input v-model="dataForm.headPic" placeholder="头像地址"></el-input>
    </el-form-item>
    <el-form-item label="QQ号码" prop="qq">
      <el-input v-model="dataForm.qq" placeholder="QQ号码"></el-input>
    </el-form-item>
    <el-form-item label="账户余额" prop="accountBalance">
      <el-input v-model="dataForm.accountBalance" placeholder="账户余额"></el-input>
    </el-form-item>
    <el-form-item label="手机是否验证 （0否  1是）" prop="isMobileCheck">
      <el-input v-model="dataForm.isMobileCheck" placeholder="手机是否验证 （0否  1是）"></el-input>
    </el-form-item>
    <el-form-item label="邮箱是否检测（0否  1是）" prop="isEmailCheck">
      <el-input v-model="dataForm.isEmailCheck" placeholder="邮箱是否检测（0否  1是）"></el-input>
    </el-form-item>
    <el-form-item label="性别，1男，2女" prop="sex">
      <el-input v-model="dataForm.sex" placeholder="性别，1男，2女"></el-input>
    </el-form-item>
    <el-form-item label="会员等级" prop="userLevel">
      <el-input v-model="dataForm.userLevel" placeholder="会员等级"></el-input>
    </el-form-item>
    <el-form-item label="积分" prop="points">
      <el-input v-model="dataForm.points" placeholder="积分"></el-input>
    </el-form-item>
    <el-form-item label="经验值" prop="experienceValue">
      <el-input v-model="dataForm.experienceValue" placeholder="经验值"></el-input>
    </el-form-item>
    <el-form-item label="生日" prop="birthday">
      <el-input v-model="dataForm.birthday" placeholder="生日"></el-input>
    </el-form-item>
    <el-form-item label="最后登录时间" prop="lastLoginTime">
      <el-input v-model="dataForm.lastLoginTime" placeholder="最后登录时间"></el-input>
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
          username: '',
          password: '',
          phone: '',
          email: '',
          created: '',
          updated: '',
          sourceType: '',
          nickName: '',
          name: '',
          status: '',
          headPic: '',
          qq: '',
          accountBalance: '',
          isMobileCheck: '',
          isEmailCheck: '',
          sex: '',
          userLevel: '',
          points: '',
          experienceValue: '',
          birthday: '',
          lastLoginTime: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码，加密存储不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '注册手机号不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '注册邮箱不能为空', trigger: 'blur' }
          ],
          created: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          updated: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          sourceType: [
            { required: true, message: '会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat不能为空', trigger: 'blur' }
          ],
          nickName: [
            { required: true, message: '昵称不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '真实姓名不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '使用状态（Y正常 N非正常）不能为空', trigger: 'blur' }
          ],
          headPic: [
            { required: true, message: '头像地址不能为空', trigger: 'blur' }
          ],
          qq: [
            { required: true, message: 'QQ号码不能为空', trigger: 'blur' }
          ],
          accountBalance: [
            { required: true, message: '账户余额不能为空', trigger: 'blur' }
          ],
          isMobileCheck: [
            { required: true, message: '手机是否验证 （0否  1是）不能为空', trigger: 'blur' }
          ],
          isEmailCheck: [
            { required: true, message: '邮箱是否检测（0否  1是）不能为空', trigger: 'blur' }
          ],
          sex: [
            { required: true, message: '性别，1男，2女不能为空', trigger: 'blur' }
          ],
          userLevel: [
            { required: true, message: '会员等级不能为空', trigger: 'blur' }
          ],
          points: [
            { required: true, message: '积分不能为空', trigger: 'blur' }
          ],
          experienceValue: [
            { required: true, message: '经验值不能为空', trigger: 'blur' }
          ],
          birthday: [
            { required: true, message: '生日不能为空', trigger: 'blur' }
          ],
          lastLoginTime: [
            { required: true, message: '最后登录时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.user.username
                this.dataForm.password = data.user.password
                this.dataForm.phone = data.user.phone
                this.dataForm.email = data.user.email
                this.dataForm.created = data.user.created
                this.dataForm.updated = data.user.updated
                this.dataForm.sourceType = data.user.sourceType
                this.dataForm.nickName = data.user.nickName
                this.dataForm.name = data.user.name
                this.dataForm.status = data.user.status
                this.dataForm.headPic = data.user.headPic
                this.dataForm.qq = data.user.qq
                this.dataForm.accountBalance = data.user.accountBalance
                this.dataForm.isMobileCheck = data.user.isMobileCheck
                this.dataForm.isEmailCheck = data.user.isEmailCheck
                this.dataForm.sex = data.user.sex
                this.dataForm.userLevel = data.user.userLevel
                this.dataForm.points = data.user.points
                this.dataForm.experienceValue = data.user.experienceValue
                this.dataForm.birthday = data.user.birthday
                this.dataForm.lastLoginTime = data.user.lastLoginTime
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
              url: this.$http.adornUrl(`/manager/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'phone': this.dataForm.phone,
                'email': this.dataForm.email,
                'created': this.dataForm.created,
                'updated': this.dataForm.updated,
                'sourceType': this.dataForm.sourceType,
                'nickName': this.dataForm.nickName,
                'name': this.dataForm.name,
                'status': this.dataForm.status,
                'headPic': this.dataForm.headPic,
                'qq': this.dataForm.qq,
                'accountBalance': this.dataForm.accountBalance,
                'isMobileCheck': this.dataForm.isMobileCheck,
                'isEmailCheck': this.dataForm.isEmailCheck,
                'sex': this.dataForm.sex,
                'userLevel': this.dataForm.userLevel,
                'points': this.dataForm.points,
                'experienceValue': this.dataForm.experienceValue,
                'birthday': this.dataForm.birthday,
                'lastLoginTime': this.dataForm.lastLoginTime
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
