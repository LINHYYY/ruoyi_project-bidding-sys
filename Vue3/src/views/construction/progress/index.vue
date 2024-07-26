<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="进度ID" prop="progressId">
        <el-input
          v-model="queryParams.progressId"
          placeholder="请输入进度ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目ID" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="施工日期" prop="constructionDate">
        <el-date-picker clearable
          v-model="queryParams.constructionDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择施工日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="uploadTime">
        <el-date-picker clearable
          v-model="queryParams.uploadTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择创建时间">
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
          v-hasPermi="['construction:progress:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['construction:progress:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['construction:progress:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['construction:progress:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="progressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="60" align="center" />
      <el-table-column label="序号" type="index" width="60" align="center" prop="progressId" />
      <el-table-column label="项目ID" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" width="150"/>
      <el-table-column label="施工日期" align="center" prop="constructionDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.constructionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="每日施工计划" align="center" prop="dailyContent" show-overflow-tooltip />
      <el-table-column label="施工问题描述" align="center" prop="progressDescription" show-overflow-tooltip />
      <el-table-column label="施工进度描述" align="center" prop="constructionDescription" show-overflow-tooltip/>
      <el-table-column label="更新时间" align="center" prop="uploadTime" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['construction:progress:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['construction:progress:remove']">删除</el-button>
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

    <!-- 添加或修改施工队日志对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="progressRef" :model="form" :rules="rules" label-width="200px">
        <el-form-item label="项目ID" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="施工日期" prop="constructionDate">
          <el-date-picker clearable
            v-model="form.constructionDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择预计施工日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="每日施工计划" prop="dailyContent">
          <el-input v-model="form.dailyContent" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="施工问题描述" prop="progressDescription">
          <el-input v-model="form.progressDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="施工进度描述" prop="constructionDescription">
          <el-input v-model="form.constructionDescription" type="textarea" placeholder="请输入内容" />
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

<script setup name="Progress">
import { listProgress, getProgress, delProgress, addProgress, updateProgress } from "@/api/construction/progress";

const { proxy } = getCurrentInstance();

const progressList = ref([]);
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
    progressId: null,
    projectId: null,
    constructionDate: null,
    dailyContent: null,
    progressDescription: null,
    constructionDescription: null,
    uploadTime: null,
    createTime: null
  },
  rules: {
    projectId: [
      { required: true, message: "项目ID不能为空", trigger: "blur" }
    ],
    constructionDate: [
      { required: true, message: "预计施工日期不能为空", trigger: "blur" }
    ],
    dailyContent: [
      { required: true, message: "每日施工计划不能为空", trigger: "blur" }
    ],
    progressDescription: [
      { required: true, message: "施工问题描述不能为空", trigger: "blur" }
    ],
    constructionDescription: [
      { required: true, message: "施工进度描述不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询施工队日志列表 */
function getList() {
  loading.value = true;
  listProgress(queryParams.value).then(response => {
    progressList.value = response.rows;
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
    progressId: null,
    projectId: null,
    constructionDate: null,
    dailyContent: null,
    progressDescription: null,
    constructionDescription: null,
    uploadTime: null,
    createTime: null
  };
  proxy.resetForm("progressRef");
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
  ids.value = selection.map(item => item.progressId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加施工队日志";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _progressId = row.progressId || ids.value
  getProgress(_progressId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改施工队日志";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["progressRef"].validate(valid => {
    if (valid) {
      if (form.value.progressId != null) {
        updateProgress(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProgress(form.value).then(response => {
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
  const _progressIds = row.progressId || ids.value;
  proxy.$modal.confirm('是否确认删除施工队日志编号为"' + _progressIds + '"的数据项？').then(function() {
    return delProgress(_progressIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('construction/progress/export', {
    ...queryParams.value
  }, `progress_${new Date().getTime()}.xlsx`)
}

getList();
</script>
