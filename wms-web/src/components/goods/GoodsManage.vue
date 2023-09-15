<script>
import current from "element-ui/packages/table/src/store/current";
import SelectUser from "@/components/user/SelectUser.vue";
export default {
    name:"GoodsManage",
    components: {
        SelectUser
    },
    methods:{
        doSelectUser(val){
            console.log(val)
            this.tempUser=val
        },
        confirmUser(){
            this.form1.username=this.tempUser.name
            this.form1.userId=this.tempUser.id
            this.innerVisible=false
        },
        selectCurrentChange(val) {
            this.currentRow = val;
        },
        formatStorage(row){
            let temp=this.storageData.find(item=>{
                return item.id == row.storage;
            })
            return temp && temp.name
        },
        formatGoodsType(row){
            let temp=this.goodsTypeData.find(item=>{
                return item.id == row.goodsType;
            })
            return temp && temp.name
        },
        loadPost(){
            this.$axios.post(this.$httpUrl+'/goods/listPage',{
                pageSize:this.pageSize,
                pageNum:this.pageNum,
                param:{
                    name:this.name,
                    goodsType:this.goodsType+'',
                    storage:this.storage+''
                }
            }).then(res=>res.data).then(res=>{
                if(res.code==200){
                    this.tableData=res.data
                    this.total=res.total
                }else {
                    alert('获取数据失败')
                }
            })
        },
        loadStorage(){
            this.$axios.get(this.$httpUrl+'/storage/list').then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==200){
                    this.storageData=res.data
                }else {
                    alert('获取数据失败')
                }
            })
        },
        loadGoodsType(){
            this.$axios.get(this.$httpUrl+'/goodstype/list').then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==200){
                    this.goodsTypeData=res.data
                }else {
                    alert('获取数据失败')
                }
            })
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageSize=val;
            this.pageNum=1;
            this.loadPost();
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.pageNum=val;
            this.loadPost()
        },
        resetParam(){
            this.name='';
            this.storage='';
            this.goodsType='';
            this.loadPost();
        },
        mod(row){//参数是拿到的一整行的数据
            this.centerDialogVisible=true;
            this.$nextTick(()=>{//异步处理
                //赋值到表单,form是表单内容
                this.form.id=row.id;
                this.form.name=row.name;
                this.form.storage=row.storage;
                this.form.goodsType=row.goodsType;
                this.form.count=row.count;
                this.form.remark=row.remark;
            })
        },
        del(id){
            this.$axios.get(this.$httpUrl+'/goods/del?id='+id).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==200){
                    this.$message({
                        message: '操作成功~',
                        type: 'success'
                    });
                    this.loadPost()
                }else {
                    this.$message({
                        message: '操作失败！',
                        type: 'error'
                    });
                }
            })
        },
        add(){
            this.centerDialogVisible=true;
            this.$nextTick(()=>{//异步处理
                this.resetForm();
                //this.form.id = ''
            })
        },
        inGoods(){
            if(!this.currentRow.id){
                alert('请选择记录！');
                return;
            }
            this.inDialogVisible=true;
            this.$nextTick(()=>{//异步处理
                this.resetInForm();
            })

            this.form1.goodsname=this.currentRow.name;
            this.form1.goods=this.currentRow.id;
            this.form1.adminId=this.user.id;
            this.form1.action=1
        },
        outGoods(){
            if(!this.currentRow.id){
                alert('请选择记录！');
                return;
            }
            this.inDialogVisible=true;
            this.$nextTick(()=>{//异步处理
                this.resetInForm();
            })

            this.form1.goodsname=this.currentRow.name;
            this.form1.goods=this.currentRow.id;
            this.form1.adminId=this.user.id;
            this.form1.action=2
        },
        save(){
            this.$refs.form.validate((valid) => {
                if (valid) {
                    if(this.form.id){//表示修改
                        this.doMod();
                    }else {
                        this.doSave();
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm() {
            this.$refs.form.resetFields();//表单内容回到前一个状态
        },
        resetInForm() {
            this.$refs.form1.resetFields();//表单内容回到前一个状态
        },
        doSave(){
            this.$axios.post(this.$httpUrl+'/goods/save',this.form).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==200){
                    this.$message({
                        message: '操作成功~',
                        type: 'success'
                    });
                    this.centerDialogVisible=false
                    this.loadPost()
                }else {
                    this.$message({
                        message: '操作失败！',
                        type: 'error'
                    });
                }
            })
        },
        doInGoods(){
            console.log(this.form1)
            this.$axios.post(this.$httpUrl+'/record/save',this.form1).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==200){
                    this.$message({
                        message: '操作成功~',
                        type: 'success'
                    });
                    this.inDialogVisible=false
                    this.loadPost()
                }else {
                    this.$message({
                        message: '操作失败！',
                        type: 'error'
                    });
                }
            })
        },
        doMod(){
            this.$axios.post(this.$httpUrl+'/goods/update',this.form).then(res=>res.data).then(res=>{
                console.log(res)
                if(res.code==200){
                    this.$message({
                        message: '操作成功~',
                        type: 'success'
                    });
                    this.centerDialogVisible=false
                    this.loadPost()
                }else {
                    this.$message({
                        message: '操作失败！',
                        type: 'error'
                    });
                }
            })
        },
        selectUser(){
            this.innerVisible=true;
        },
    },
    beforeMount() {
        this.loadGoodsType();
        this.loadStorage();
        this.loadPost();
    },
    data() {

        let checkCount=(rule,value,callback)=>{
            if(value>9999){
                callback(new error('数量输入过大'));
            }else {
                callback();
            }
        };
        return {
            user:JSON.parse(sessionStorage.getItem('CurUser')),
            goodsTypeData:[],
            storageData:[],
            tableData: [],
            pageSize:10,
            pageNum:1,
            total:0,
            name:'',
            storage: '',
            goodsType: '',
            centerDialogVisible:false,
            inDialogVisible:false,
            innerVisible:false,
            action:1,
            user_id: '',
            currentRow:{},
            form:{
                id:'',
                name:'',
                storage:'',
                goodsType:'',
                count:'',
                remark:''
            },
            form1:{
                goods:'',
                goodsname:'',
                count:'',
                userId:'',
                username:'',
                adminId:'',
                remark: ''
            },
            rules: {//验证规则
                name: [
                    {required: true, message: '请输入物品名', trigger: 'blur'}
                ],
                goodsType:[
                    {required: true, message: '请选择物品类别', trigger: 'blur'}
                ],
                storage:[
                    {required: true, message: '请选择仓库', trigger: 'blur'}
                ],
                count:[
                    {required:true,message:'请输入数量',trigger:'blur'},
                    {pattern:/^([1-9][0-9]*){1,4}$/,message:'数量必须为正整数字',trigger:'blur'},
                    {validator:checkCount,trigger: 'blur'}
                ]
            },
            rules1:{
                user_id:[
                    {required: true, message: '请选择申请人', trigger: 'blur'}
                ],
                count:[
                    {required:true,message:'请输入数量',trigger:'blur'},
                    // {pattern:/^([1-9][0-9]*){1,4}$/,message:'库存不足',trigger:'blur'},
                    // {validator:checkCount,trigger: 'blur'}
                ]
            },
            tempUser:{}
        }
    }
}
</script>

<template>
    <div>
        <div style="margin-bottom: 5px">
            <el-input v-model="name" placeholder="请输入物品名" suffix-icon="el-icon-search" style="width: 200px"
                      @keyup.enter.native="loadPost"></el-input>
            <el-select v-model="storage" placeholder="请选择仓库" style="margin-left: 5px">
                <el-option
                    v-for="item in storageData"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                </el-option>
            </el-select>
            <el-select v-model="goodsType" placeholder="请选择物品类别" style="margin-left: 5px">
                <el-option
                    v-for="item in goodsTypeData"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                </el-option>
            </el-select>
            <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
            <el-button type="success" @click="resetParam">重置</el-button>
            <el-button type="primary" style="margin-left: 5px" @click="add" v-if="user.roleId!==2">新增</el-button>
            <el-button type="primary" @click="inGoods" v-if="user.roleId!==2">入库</el-button>
            <el-button type="primary" @click="outGoods" v-if="user.roleId!==2">出库</el-button>
        </div>
        <el-table :data="tableData"
                  :header-cell-style="{ background:'#f2f5fc', color: '#555555'}"
                  border
                  highlight-current-row
                  @current-change="selectCurrentChange"
        >
            <el-table-column prop="id" label="ID" width="60">
            </el-table-column>
            <el-table-column prop="name" label="物品名" width="180">
            </el-table-column>
            <el-table-column prop="storage" label="仓库" width="180" :formatter="formatStorage">
            </el-table-column>
            <el-table-column prop="goodsType" label="分类" width="180" :formatter="formatGoodsType">
            </el-table-column>
            <el-table-column prop="count" label="数量" width="180">
            </el-table-column>
            <el-table-column prop="remark" label="备注" width="">
            </el-table-column>
            <el-table-column prop="operate" label="操作" v-if="user.roleId!==2">
                <template slot-scope="scope">
                    <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
                    <el-popconfirm
                            title="确定删除这条数据吗？"
                            @confirm="del(scope.row.id)"
                            style="margin-left: 5px"
                    >
                        <el-button slot="reference" size="small" type="danger">删除</el-button>
                    </el-popconfirm>
                </template>>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5, 10, 20, 30]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
        <el-dialog
                title="新增"
                :visible.sync="centerDialogVisible"
                width="30%"
                center>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">

                <el-form-item label="物品名" prop="name">
                    <el-col :span="20">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="仓库" prop="storage">
                    <el-col :span="20">
                        <el-select v-model="form.storage" placeholder="请选择仓库" style="margin-left: 5px">
                            <el-option
                                v-for="item in storageData"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                            </el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
                <el-form-item label="分类" prop="goodsType">
                    <el-col :span="20">
                        <el-select v-model="form.goodsType" placeholder="请选择物品类别" style="margin-left: 5px">
                            <el-option
                                v-for="item in goodsTypeData"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                            </el-option>
                        </el-select>
                    </el-col>
                </el-form-item>
                <el-form-item label="数量" prop="count">
                    <el-col :span="20">
                        <el-input v-model="form.count"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-col :span="20">
                        <el-input type="textarea" v-model="form.remark"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
            <el-button type="primary" @click="inGoods">入 库</el-button>
            </span>
        </el-dialog>
        <el-dialog
            title="出入库"
            :visible.sync="inDialogVisible"
            width="30%"
            center>
            <el-dialog
                width="70%"
                title="用户选择"
                :visible.sync="innerVisible"
                append-to-body>
                <SelectUser @doSelectUser="doSelectUser"></SelectUser>
                <span slot="footer" class="dialog-footer">
                <el-button @click="innerVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirmUser">确 定</el-button>
                </span>
            </el-dialog>
            <el-form ref="form1" :model="form1" :rules="rules1" label-width="80px">

                <el-form-item label="物品名">
                    <el-col :span="20">
                        <el-input v-model="form1.goodsname" readonly></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="申请人" prop="user_id">
                    <el-col :span="20">
                        <el-input v-model="form1.username" readonly @click.native="selectUser"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="数量" prop="count">
                    <el-col :span="20">
                        <el-input v-model="form1.count"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-col :span="20">
                        <el-input type="textarea" v-model="form1.remark"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="inDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="doInGoods">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<style scoped>

</style>