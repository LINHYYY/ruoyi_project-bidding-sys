<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="设计队ID" prop="teamId">
        <el-input
          v-model="queryParams.teamId"
          placeholder="请输入设计队ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设计队名称" prop="teamName" width="200px">
        <el-input
          v-model="queryParams.teamName"
          placeholder="请输入设计队名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资质等级" prop="qualificationLevel">
        <el-input
          v-model="queryParams.qualificationLevel"
          placeholder="请输入资质等级"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="responsiblePerson">
        <el-input
          v-model="queryParams.responsiblePerson"
          placeholder="请输入负责人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="contactInfo">
        <el-input
          v-model="queryParams.contactInfo"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['design:teams:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['design:teams:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['design:teams:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['design:teams:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teamsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设计队ID" align="center" prop="teamId" />
      <el-table-column label="设计队名称" align="center" prop="teamName" />
      <el-table-column label="资质等级" align="center" prop="qualificationLevel" />
      <el-table-column label="负责人" align="center" prop="responsiblePerson" />
      <el-table-column label="联系方式" align="center" prop="contactInfo" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['design:teams:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['design:teams:remove']">删除</el-button>
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

    <!-- 添加或修改设计队列表对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="teamsRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="设计队名称" prop="teamName">
          <el-input v-model="form.teamName" placeholder="请输入施工队名称" />
        </el-form-item>
        <el-form-item label="资质等级" prop="qualificationLevel">
          <el-input v-model="form.qualificationLevel" placeholder="请输入资质等级" />
        </el-form-item>
        <el-form-item label="负责人" prop="responsiblePerson">
          <el-input v-model="form.responsiblePerson" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="联系方式" prop="contactInfo">
          <el-input v-model="form.contactInfo" placeholder="请输入联系方式" />
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

<script setup name="Teams">
import { listTeams, getTeams, delTeams, addTeams, updateTeams } from "@/api/design/teams";

const { proxy } = getCurrentInstance();

const teamsList = ref([]);
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
    teamId: null,
    teamName: null,
    qualificationLevel: null,
    responsiblePerson: null,
    contactInfo: null,
  },
  rules: {
    teamName: [
      { required: true, message: "设计队名称不能为空", trigger: "blur" }
    ],
    qualificationLevel: [
      { required: true, message: "资质等级不能为空", trigger: "blur" }
    ],
    responsiblePerson: [
      { required: true, message: "负责人不能为空", trigger: "blur" }
    ],
    contactInfo: [
      { required: true, message: "联系方式不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询设计队列表列表 */
function getList() {
  loading.value = true;
  listTeams(queryParams.value).then(response => {
    teamsList.value = response.rows;
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
    teamId: null,
    teamName: null,
    qualificationLevel: null,
    responsiblePerson: null,
    contactInfo: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("teamsRef");
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
  ids.value = selection.map(item => item.teamId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加设计队列表";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _teamId = row.teamId || ids.value
  getTeams(_teamId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改设计队列表";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["teamsRef"].validate(valid => {
    if (valid) {
      if (form.value.teamId != null) {
        updateTeams(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addTeams(form.value).then(response => {
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
  const _teamIds = row.teamId || ids.value;
  proxy.$modal.confirm('是否确认删除设计队列表编号为"' + _teamIds + '"的数据项？').then(function() {
    return delTeams(_teamIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('design/teams/export', {
    ...queryParams.value
  }, `teams_${new Date().getTime()}.xlsx`)
}

getList();
</script>
