<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="投标详细信息id" prop="detailId">
        <el-input
          v-model="queryParams.detailId"
          placeholder="请输入投标详细信息id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目当前状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择项目当前状态" clearable>
          <el-option
            v-for="dict in project_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="投标人id" prop="bidderId">
        <el-input
          v-model="queryParams.bidderId"
          placeholder="请输入投标人id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="施工队ID" prop="teamId">
        <el-input
          v-model="queryParams.teamId"
          placeholder="请输入施工队ID"
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
      <el-form-item label="当前阶段描述" prop="currentStage">
        <el-input
          v-model="queryParams.currentStage"
          placeholder="请输入当前阶段描述"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目开始日期" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择项目开始日期">
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
          v-hasPermi="['project:ProProjectProgress:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:ProProjectProgress:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:ProProjectProgress:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['project:ProProjectProgress:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ProProjectProgressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="进程记录id" align="center" prop="progressId" />
      <el-table-column label="投标详细信息id" align="center" prop="detailId" width="120"  />
      <el-table-column label="项目当前状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="project_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="投标人id" align="center" prop="bidderId" />
      <el-table-column label="施工队ID" align="center" prop="teamId" />
      <el-table-column label="负责人员ID" align="center" prop="managerId" />
      <el-table-column label="当前阶段描述" align="center" prop="currentStage" />
      <el-table-column label="项目开始日期" align="center" prop="startDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目结束日期" align="center" prop="endDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180" >
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['project:ProProjectProgress:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['project:ProProjectProgress:remove']">删除</el-button>
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

    <!-- 添加或修改项目进程对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="ProProjectProgressRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="投标详细信息id" prop="detailId">
          <el-input v-model="form.detailId" placeholder="请输入投标详细信息id" />
        </el-form-item>
        <el-form-item label="项目当前状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择项目当前状态">
            <el-option
              v-for="dict in project_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="投标人id" prop="bidderId">
          <el-input v-model="form.bidderId" placeholder="请输入投标人id" />
        </el-form-item>
        <el-form-item label="施工队ID" prop="teamId">
          <el-input v-model="form.teamId" placeholder="请输入施工队ID" />
        </el-form-item>
        <el-form-item label="负责人员ID" prop="managerId">
          <el-input v-model="form.managerId" placeholder="请输入负责人员ID" />
        </el-form-item>
        <el-form-item label="当前阶段描述" prop="currentStage">
          <el-input v-model="form.currentStage" placeholder="请输入当前阶段描述" />
        </el-form-item>
        <el-form-item label="项目开始日期" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择项目开始日期">
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

<script setup name="ProProjectProgress">
import { listProProjectProgress, getProProjectProgress, delProProjectProgress, addProProjectProgress, updateProProjectProgress } from "@/api/project/ProProjectProgress";

const { proxy } = getCurrentInstance();
const { project_status } = proxy.useDict('project_status');

const ProProjectProgressList = ref([]);
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
    detailId: null,
    status: null,
    bidderId: null,
    teamId: null,
    managerId: null,
    currentStage: null,
    startDate: null,
    endDate: null
  },
  rules: {
    detailId: [
      { required: true, message: "投标详细信息id不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "项目当前状态不能为空", trigger: "change" }
    ],
    bidderId: [
      { required: true, message: "投标人id不能为空", trigger: "blur" }
    ],
    teamId: [
      { required: true, message: "施工队ID不能为空", trigger: "blur" }
    ],
    managerId: [
      { required: true, message: "负责人员ID不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询项目进程列表 */
function getList() {
  loading.value = true;
  listProProjectProgress(queryParams.value).then(response => {
    ProProjectProgressList.value = response.rows;
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
    detailId: null,
    status: null,
    bidderId: null,
    teamId: null,
    managerId: null,
    currentStage: null,
    startDate: null,
    endDate: null
  };
  proxy.resetForm("ProProjectProgressRef");
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
  title.value = "添加项目进程";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _progressId = row.progressId || ids.value
  getProProjectProgress(_progressId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改项目进程";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ProProjectProgressRef"].validate(valid => {
    if (valid) {
      if (form.value.progressId != null) {
        updateProProjectProgress(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProProjectProgress(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除项目进程编号为"' + _progressIds + '"的数据项？').then(function() {
    return delProProjectProgress(_progressIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('project/ProProjectProgress/export', {
    ...queryParams.value
  }, `ProProjectProgress_${new Date().getTime()}.xlsx`)
}

getList();
</script>
