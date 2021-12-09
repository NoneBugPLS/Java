<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="商品基本信息">
        <el-form ref="goods" :model="goodsForm" label-width="120px">
          {{goodsForm}}====
          <el-form-item label="商品分类">
            <!--1. 查询一级分类-->
            <el-select v-model="goodsForm.goods.category1Id" placeholder="请选择一级分类">
              <el-option :label="cate.name" :value="cate.id" v-for="cate in categories1" :key="cate.id"></el-option>
            </el-select>
            <!--1. 查询二级分类-->
            <el-select v-model="goodsForm.goods.category2Id" placeholder="请选择二级分类">
              <el-option :label="cate.name" :value="cate.id" v-for="cate in categories2" :key="cate.id"></el-option>
            </el-select>
            <!--1. 查询三级分类-->
            <el-select v-model="goodsForm.goods.category3Id" placeholder="请选择二级分类">
              <el-option :label="cate.name" :value="cate.id" v-for="cate in categories3" :key="cate.id"></el-option>
            </el-select>
            模板ID：{{goodsForm.goods.typeTemplateId}}
          </el-form-item>
          <el-form-item label="商品名称">
            <el-input v-model="goodsForm.goods.goodsName"></el-input>
          </el-form-item>
          <el-form-item label="品牌">
            <el-select v-model="goodsForm.goods.brandId" placeholder="请选择品牌">
              <el-option :label="brand.text" :value="brand.id" v-for="brand in brandList" :key="brand.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="副标题">
            <el-input v-model="goodsForm.goods.caption"></el-input>
          </el-form-item>
          <el-form-item label="价格:">
            <el-input v-model="goodsForm.goods.price"></el-input>
          </el-form-item>
          <el-form-item label="商品介绍:">
            <el-input type="textarea" v-model="goodsForm.goodsDesc.introduction"></el-input>
          </el-form-item>
          <el-form-item label="包装列表:">
            <el-input type="textarea" v-model="goodsForm.goodsDesc.packageList"></el-input>
          </el-form-item>
          <el-form-item label="售后服务:">
            <el-input type="textarea" v-model="goodsForm.goodsDesc.saleService"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="商品图片">
        <el-button type="success" plain size="small" @click="dialogFormVisible=true, imageEntity={}, fileList=[]" >新建</el-button>
        <el-table
          :data="goodsForm.goodsDesc.itemImages"
          border
          style="width: 100%;">
          <el-table-column
            prop="color"
            header-align="center"
            align="center"
            label="颜色">
          </el-table-column>
          <el-table-column
            header-align="center"
            align="center"
            label="图片">
            <template slot-scope="scope">
              <img :src="scope.row.url">
            </template>
          </el-table-column>

          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="150"
            label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="goodsForm.goodsDesc.itemImages.splice(scope.row.index,1)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>


      <el-tab-pane label="拓展属性">
        {{goodsForm.goodsDesc.customAttributeItems}}=====
        <el-table
          :data="goodsForm.goodsDesc.customAttributeItems"
          style="width: 100%;">
          <el-table-column
            prop="text"
            header-align="center"
            align="center"
          width="150px">
          </el-table-column>
          <el-table-column
            header-align="center"
            align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.value">
              </el-input>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>


      <el-tab-pane label="规格">
        {{optionList}}-----
        <el-table
          :data="specifications"
          style="width: 100%;">
          <el-table-column
            width="150px"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              <span>{{scope.row.text}}</span>
            </template>
          </el-table-column>
          <el-table-column
            header-align="center"
            align="left">
            <template slot-scope="scope">
              <el-checkbox-group size="small"  v-model="optionList[scope.$index]" @change="getSpecItem">
                <el-checkbox :label="option.optionName" border v-for="option in scope.row.options" :key="option.id"></el-checkbox>
              </el-checkbox-group>
            </template>
          </el-table-column>

        </el-table>
        <el-table
          :data="goodsForm.items"
          style="width: 100%;">
          <el-table-column width="200px" align="center"
                           :key="key" :label="spec.attributeName"
                           v-for="(spec,key) in goodsForm.goodsDesc.specificationItems">
            <template slot-scope="scope">
              {{scope.row.spec[spec.attributeName]}}
            </template>
          </el-table-column>
          <!--2.2 处理价格-->
          <el-table-column  label="价格"  align="center" >
            <template slot-scope="scope">
              <el-input v-model="scope.row.price"/>
            </template>
          </el-table-column>
          <!--2.3 处理数量-->
          <el-table-column  label="库存"  align="center" >
            <template slot-scope="scope">
              <el-input v-model="scope.row.num"/>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <div style="margin-top: 10px;">
        <el-button type="primary" @click="save">保存</el-button>
        <el-button>返回列表</el-button>
      </div>
    </el-tabs>
    <el-dialog title="添加图片" :visible.sync="dialogFormVisible">
      <el-form :model="imageEntity">
        <el-form-item label="颜色" label-width="100px">
          <el-input v-model="imageEntity.color" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图片" label-width="100px">
          <!--文件上传组件-->
          <!--:http-request="uploadFile" 此代码代表自定义文件上传-->
          <el-upload
            action=""
            class="upload-demo"
            :file-list="fileList"
            :http-request="uploadFile"
            list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveImage">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import specification from "../../../../../renren-fast-manager-vue/src/views/modules/manager/specification";

  export default {
    data() {
      return {
        goodsForm: {     //这个goodsform是一个传到后端信息的组合对象  它里面包含的三个 goods goodsDesc  items 分别对应你在后端写入的 goods类中的三个对象
          goods: {typeTemplateId:'', brandId:''},             //spu
          goodsDesc: {itemImages:[], customAttributeItems:[],specificationItems:[]},         //spu 商品描述  {[商品图片]， [商品拓展属性]}
          items: [],             //sku 商品列表
        },
        categories1:[],           //一级分类
        categories2:[],           //二级分类
        categories3:[],           //三级分类
        brandList:[],       //代表某个模板下的品牌列表
        dialogFormVisible:false,    //代表弹窗
        imageEntity:{},           //代表上传图片的对象
        fileList:[],              //代表上传后的文件列表
        specifications:[],         //代表规格以及规格选项
        optionList:[],                  //代表是否选择
      }

    },
    created() {
      //1,查询一级分类
      this.findCategories1();
      //2. 通过goods页面传过来的id 进行查询
      this.findById();
    },
    watch:{       //用于监控某个变量值的变化
      categories1newId(itemCatId, oldV){      //(itemCatId代表当前选择的一级分类ID
        //console.log("newV:", itemCatId, "oldV:",oldV);
        //现在就可以根据一级分类的ID向后台发出查询一级分类对应的二级分类列表
        this.$http({
          url: this.$http.adornUrl(`/shop/itemcat/findByParentId/${itemCatId}`),
          method: 'get',
        }).then(({data}) => {
          if (data.code ==0){
            this.categories2 = data.list;
          }
        })
      },
      categories2newId(itemCatId, oldV){      //(itemCatId代表当前选择的二级分类ID
        //console.log("newV:", itemCatId, "oldV:",oldV);
        //现在就可以根据二级分类的ID向后台发出查询二级分类对应的三级分类列表
        this.$http({
          url: this.$http.adornUrl(`/shop/itemcat/findByParentId/${itemCatId}`),
          method: 'get',
        }).then(({data}) => {
          if (data.code ==0){
            this.categories3 = data.list;
          }
        })
      },
      categories3newId(itemCatId, oldV){      //(itemCatId代表当前选择的三级分类ID
        console.log("newV:", itemCatId, "oldV:",oldV);
        //现在就可以根据三级分类的ID向后台发出查询三级分类对应的模板id 变化type_Id
        this.$http({
          url: this.$http.adornUrl(`/shop/itemcat/info/${itemCatId}`),
          method: 'get',
        }).then(({data}) => {
          if (data.code ==0){
            this.goodsForm.goods.typeTemplateId = data.itemCat.typeId;
          }
        })
      },
      typetemplatenewId(typeId, oldV){      //typeId代表模板id
        //0.1 得到修改页面传入的参数id
        let id = this.$route.query.id;
        // //4. 当从商品展示页面点击修改跳转到当前页面，要记得把传过来的ID 在这里声明下

        //1. 模板ID变化就到后台查询这个模板对应的品牌列表    brandsIds
        //2. 模板ID变化后查询出模板对象中对应的拓展属性，    custom_attribute_items
        //3. 模板ID变化后查询出模板对应的规格对象      spec_ids
        this.$http({
          url: this.$http.adornUrl(`/shop/typetemplate/info/${typeId}`),
          method: 'get',
        }).then(({data}) => {
          if (data.code ==0){
            //1). 找到brandIds  品牌列表
            this.brandList = JSON.parse(data.typeTemplate.brandIds);
            //2). 找到拓展属性  custom_attribute_items
            //  if (!id){         //如果是添加商品操作，才执行下面的语句
            //    this.goodsForm.goodsDesc.customAttributeItems = JSON.parse(data.typeTemplate.customAttributeItems);
            //  }
            this.goodsForm.goodsDesc.customAttributeItems = JSON.parse(data.typeTemplate.customAttributeItems);
            //3). 找到规格对象  这个得从后面拿数据
            this.$http({
              url: this.$http.adornUrl(`/shop/specification/findByTypeId/${typeId}`),
              method: 'get',
            }).then(({data}) => {
              this.specifications = data.specifications;
              //console.log("data",this.specifications)
              if(!this.$route.query.id) {    //此处一定要添加这个条件，否则，此值就会在显示修改页面时，将得到的optionList的值覆盖掉
                this.optionList = [];
                data.specifications.forEach(sp => {
                  this.optionList.push([]);
                })
              }
            })
          }
        })
      }

    },

    computed:{    //自动计算属性值得变化
      categories1newId(){       //1. 计算一级分类  ID 是否变化
        return this.goodsForm.goods.category1Id;
      },
      categories2newId(){       //2. 计算二级分类  ID 是否变化
        return this.goodsForm.goods.category2Id;
      },
      categories3newId(){       //3. 计算三级分类  ID 是否变化
        return this.goodsForm.goods.category3Id;
      },
      typetemplatenewId(){      //4. 计算模板ID是否变化
        return this.goodsForm.goods.typeTemplateId;
      }
    },
    methods: {
      //5. 通过id 显示修改信息
      findById(){
        //5.1 拿到修改页面传来的参数
        let id = this.$route.query.id;
        //5.2 通过id查询商品
        this.$http({
          url: this.$http.adornUrl(`/shop/goods/findById/${id}`),
          method: 'get',
        }).then(({data}) => {
          if (data.code ==0){
            this.goodsForm = data.goodsForm;
            this.goodsForm.goods.brandId = parseInt(data.goodsForm.goods.brandId);
            //5.3 将后端查询到的字符串转换为json对象
            this.goodsForm.goodsDesc.specificationItems = JSON.parse(this.goodsForm.goodsDesc.specificationItems)
            this.goodsForm.goodsDesc.itemImages = JSON.parse(this.goodsForm.goodsDesc.itemImages)
            this.goodsForm.goodsDesc.customAttributeItems = JSON.parse(this.goodsForm.goodsDesc.customAttributeItems)
            this.goodsForm.items.forEach(item=>{
              item.spec = JSON.parse(item.spec);
            })
            //5.4 让规格选项自动勾选
            this.optionList = [];
            this.goodsForm.goodsDesc.specificationItems.forEach(spec=>{
              this.optionList.push(spec.attributeValue);
            })
            //console.log("optionList:",this.optionList);
          }


        })
      },
      //1,查询一级分类
      findCategories1(){
        this.$http({
          url: this.$http.adornUrl(`/shop/itemcat/findAll`),
          method: 'get',
        }).then(({data}) => {
          this.categories1 = data.categories1;
            })

      },
      // 保存商品
      save(){
        //1. 把前端的JSON对象转换成字符串，因为后端对象都是字符串类型的数据
        this.goodsForm.goodsDesc.specificationItems = JSON.stringify(this.goodsForm.goodsDesc.specificationItems);
        this.goodsForm.goodsDesc.itemImages = JSON.stringify(this.goodsForm.goodsDesc.itemImages );
        this.goodsForm.goodsDesc.customAttributeItems = JSON.stringify(this.goodsForm.goodsDesc.customAttributeItems);
        this.goodsForm.items.forEach(item=>{
          item.spec = JSON.stringify(item.spec);
        })
        //2. 向后台发送请求
        this.$http({
          url: this.$http.adornUrl(`/shop/goods/${this.$route.query.id ? 'update':'save'} `),
          method: `${this.$route.query.id ? 'put' : 'post'}`,
          data:this.goodsForm
        }).then(({data}) => {
          if (data.code ==0){
            this.goodsForm={
              goods:{typeTemplateId:''},
              goodsDesc:{itemImages:[],customAttributeItems:[],specificationItems:[]},
              items:[],
            }
            this.fileList = []
            this.optionList=[];           //选择的规格选项列表
            this.specifications=[]
            this.imageEntity = {}
          }
        })
      },

      //2. 文件上传
      uploadFile(val){
        //1. 构造上传的表单对象
        let data = new FormData();
        //2. 添加要上传的文件数据
        data.append("file",val.file);    //上传的文件对象
        //3. 清空上传的列表
        this.fileList = [];
        //4. 开始上传
        this.$http({
          url: this.$http.adornUrl('/shop/upload'),
          method: 'post',
          data:data,
          headers:{"Context-Type":"multipart/form-data"}
        }).then(({data}) => {
          if(data.code == 0){
            //如果上传成功，就向文件列表添加文件
            this.fileList.push({name:val.raw,url:data.url});
            this.imageEntity.url = data.url;
          }
        })
      },
      //3. 保存图片
      saveImage(){
        this.goodsForm.goodsDesc.itemImages.push(this.imageEntity);
        this.dialogFormVisible=false
      },
      //4. 点击规格选项时的事件处理
      getSpecItem(){
        console.log("optionList", this.optionList)    //这个optionList 是我们选中规格的值
        //0. 每次要设置为默认值，不然就会重复
        this.goodsForm.goodsDesc.specificationItems=[];
        //1. 遍历optionList, 在其中遍历this.specifications（就是我们自己从后台拿到的规格信息），再为this.goodsForm.goodsDesc.specificationItems赋值
        for (let i = 0; i < this.optionList.length; i++) {
          //2. 得到对应的规格的名称
          let specName =this.specifications[i].text;
          //3. 为this.goodsForm.goodsDesc.specificationItems赋值
          this.goodsForm.goodsDesc.specificationItems.push({"attributeName":specName,"attributeValue":this.optionList[i]})
        }
        //4. 【方法一】进行兼容性处理，如果attributeValue这个数组没有值，就将这个对象从this.goodsForm.goodsDesc.specificationItems数组中删除
        // for (let i = 0; i < this.optionList.length; i++) {
        //   let  len = this.goodsForm.goodsDesc.specificationItems[i].attributeValue.length;
        //   if(len == 0){
        //     this.goodsForm.goodsDesc.specificationItems.splice(i,1);
        //     break;
        //   }
        // }
        //4. 解决信息为空的数组显示问题 【方法二】采用过滤器处理，只显示attributeValue.length > 0
        this.goodsForm.goodsDesc.specificationItems = this.goodsForm.goodsDesc.specificationItems.filter(f=>f.attributeValue.length > 0);

        //5. 给GoodsForm.items(sku商品列表  就是 tb_item) 赋值
        this.createItems();
      },
      createItems(){
        //1. 定义sku商品列表的初始值
        this.goodsForm.items = [{spec:{},price:0,num:9999,status:'0',isDefault:'0'}];
        //2. 将用户点击的规格及规格选项的值附给item
        let items = this.goodsForm.goodsDesc.specificationItems;
        //3. 遍历 items ，把用户勾选的规格以及选项赋值给sku商品
        items.forEach(item=>{
          this.goodsForm.items = this.createColumn(this.goodsForm.items,item.attributeName, item.attributeValue)
        })
      },
      createColumn(items,specName,specValue){
        //1. 定义存放返回结果的新数组
        let skuList = [];
        //2. 遍历items数组
        items.forEach(item=>{
          specValue.forEach(val=>{
            //2.1 根据原来的数据进行深克隆
            let row = JSON.parse(JSON.stringify(item));
            //2.2 为新行的spec赋值
            row.spec[specName]=val
            //2.3 添加这个新行到返回的新数组中
            skuList.push(row);
          })
        })
        //3. 返回skuList
        return skuList;
      }
    }
  }
</script>
