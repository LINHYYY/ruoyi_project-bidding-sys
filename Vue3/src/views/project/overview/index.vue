<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="审核ID" prop="approvalId">
        <el-input
          v-model="queryParams.approvalId"
          placeholder="请输入审核ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人员ID" prop="managerId">
        <el-input
          v-model="queryParams.managerId"
          placeholder="请输入负责人员ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目预算" prop="budgetTotal">
        <el-input
          v-model="queryParams.budgetTotal"
          placeholder="请输入项目预算"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目启动日期" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择项目启动日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目结束日期" prop="endDate">
        <el-date-picker clearable
          v-model="queryParams.endDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择项目结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['project:overview:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:overview:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:overview:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['project:overview:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="overviewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目编号" align="center" prop="id" />
      <el-table-column label="审核ID" align="center" prop="approvalId" />
      <el-table-column label="项目名称" align="center" prop="projectName" width="180" />
      <el-table-column label="负责人员ID" align="center" prop="managerId" width="90"/>
      <el-table-column label="项目预算" align="center" prop="budgetTotal" />
      <el-table-column label="项目启动日期" align="center" prop="startDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目结束日期" align="center" prop="endDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目图片" align="center" prop="imageUrl" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.imageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="项目简述" align="center" prop="briefDesc" show-overflow-tooltip/>
      <el-table-column label="上传附件文件" align="center" prop="attachmentPath" show-overflow-tooltip/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['project:overview:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['project:overview:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改项目基础信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="overviewRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="审核项目ID" prop="approvalId">
          <el-input v-model="form.approvalId" placeholder="请输入审核ID" />
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="负责人员ID" prop="managerId">
          <el-input v-model="form.managerId" placeholder="请输入负责人员ID" />
        </el-form-item>
        <el-form-item label="项目预算" prop="budgetTotal">
          <el-input v-model="form.budgetTotal" placeholder="请输入项目预算" />
        </el-form-item>
        <el-form-item label="项目启动日期" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择项目启动日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目结束日期" prop="endDate">
          <el-date-picker clearable
            v-model="form.endDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择项目结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目图片" prop="imageUrl">
          <image-upload v-model="form.imageUrl"/>
        </el-form-item>
        <el-form-item label="项目简述" prop="briefDesc">
          <el-input v-model="form.briefDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="上传附件文件" prop="attachmentPath">
          <file-upload v-model="form.attachmentPath"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Overview">
import { listOverview, getOverview, delOverview, addOverview, updateOverview } from "@/api/project/overview";

const { proxy } = getCurrentInstance();

const overviewList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    approvalId: null,
    projectName: null,
    managerId: null,
    budgetTotal: null,
    startDate: null,
    endDate: null,
    imageUrl: null,
    briefDesc: null,
    attachmentPath: null
  },
  rules: {
    approvalId: [
      { required: true, message: "审核ID不能为空", trigger: "blur" }
    ],
    projectName: [
      { required: true, message: "项目名称不能为空", trigger: "blur" }
    ],
    managerId: [
      { required: true, message: "负责人员ID不能为空", trigger: "blur" }
    ],
    budgetTotal: [
      { required: true, message: "项目预算不能为空", trigger: "blur" }
    ],
    startDate: [
      { required: true, message: "项目启动日期不能为空", trigger: "blur" }
    ],
    endDate: [
      { required: true, message: "项目结束日期不能为空", trigger: "blur" }
    ],
    briefDesc: [
      { required: true, message: "项目简述不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询项目基础信息列表 */
function getList() {
  loading.value = true;
  listOverview(queryParams.value).then(response => {
    overviewList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    approvalId: null,
    projectName: null,
    managerId: null,
    budgetTotal: null,
    startDate: null,
    endDate: null,
    imageUrl: null,
    briefDesc: null,
    attachmentPath: null
  };
  proxy.resetForm("overviewRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加项目基础信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getOverview(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改项目基础信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["overviewRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateOverview(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addOverview(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除项目基础信息编号为"' + _ids + '"的数据项？').then(function() {
    return delOverview(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('project/overview/export', {
    ...queryParams.value
  }, `overview_${new Date().getTime()}.xlsx`)
}

getList();
</script>
