<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目ID" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目ID"
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
      <el-form-item label="项目开始日期" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择项目开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目负责人" prop="responsiblePerson">
        <el-input
          v-model="queryParams.responsiblePerson"
          placeholder="请输入项目负责人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in con_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['construction:association:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['construction:association:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['construction:association:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['construction:association:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="associationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" align="center" prop="relationId" width="80" />
      <el-table-column label="项目ID" align="center" prop="projectId"  />
      <el-table-column label="项目名称" align="center" prop="projectName" width="150"/>
      <el-table-column label="施工队ID" align="center" prop="teamId" />
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
      <el-table-column label="预计开始时间" align="center" prop="estimatedStartdate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.estimatedStartdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预计结束时间" align="center" prop="estimatedEnddate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.estimatedEnddate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目计划内容" align="center" prop="planContent" show-overflow-tooltip/>
      <el-table-column label="表创建时间" align="center" prop="createTime" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目负责人" align="center" prop="responsiblePerson" />
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="con_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['construction:association:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['construction:association:remove']">删除</el-button>
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
    <!-- 添加或修改施工项目状态对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="associationRef" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="项目ID" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="施工队ID" prop="teamId">
          <el-input v-model="form.teamId" placeholder="请输入施工队ID" />
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
        <el-form-item label="预计开始时间" prop="estimatedStartdate">
          <el-date-picker clearable
            v-model="form.estimatedStartdate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择预计开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计结束时间" prop="estimatedEnddate">
          <el-date-picker clearable
            v-model="form.estimatedEnddate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择预计结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目计划内容">
          <editor v-model="form.planContent" :min-height="192" />
        </el-form-item>
        <el-form-item label="项目负责人" prop="responsiblePerson">
          <el-input v-model="form.responsiblePerson" placeholder="请输入项目负责人" />
        </el-form-item>
        <el-form-item v-show="flag" label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in con_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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

<script setup name="Association">
import { listAssociation, getAssociation, delAssociation, addAssociation, updateAssociation } from "@/api/construction/association";

const { proxy } = getCurrentInstance();
const { con_status } = proxy.useDict('con_status');

const associationList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const flag = ref(true);
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    projectId: null,
    teamId: null,
    startDate: null,
    responsiblePerson: null,
    status: null,
  },
  rules: {
    projectId: [
      { required: true, message: "项目ID不能为空", trigger: "blur" }
    ],
    teamId: [
      { required: true, message: "施工队ID不能为空", trigger: "blur" }
    ],
    estimatedStartdate: [
      { required: true, message: "预计开始时间不能为空", trigger: "blur" }
    ],
    estimatedEnddate: [
      { required: true, message: "预计结束时间不能为空", trigger: "blur" }
    ],
    planContent: [
      { required: true, message: "项目计划内容不能为空", trigger: "blur" }
    ],
    responsiblePerson: [
      { required: true, message: "项目负责人不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询施工项目状态列表 */
function getList() {
  loading.value = true;
  listAssociation(queryParams.value).then(response => {
    associationList.value = response.rows;
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
    relationId: null,
    projectId: null,
    teamId: null,
    startDate: null,
    endDate: null,
    estimatedStartdate: null,
    estimatedEnddate: null,
    planContent: null,
    createTime: null,
    responsiblePerson: null,
    status: null,
    updateTime: null
  };
  proxy.resetForm("associationRef");
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
  ids.value = selection.map(item => item.relationId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加施工项目状态";
  flag.value = false;
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _relationId = row.relationId || ids.value
  getAssociation(_relationId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改施工项目状态";
      flag.value = true;
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["associationRef"].validate(valid => {
    if (valid) {
      if (form.value.relationId != null) {
        updateAssociation(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addAssociation(form.value).then(response => {
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
  const _relationIds = row.relationId || ids.value;
  proxy.$modal.confirm('是否确认删除施工项目状态编号为"' + _relationIds + '"的数据项？').then(function() {
    return delAssociation(_relationIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('construction/association/export', {
    ...queryParams.value
  }, `association_${new Date().getTime()}.xlsx`)
}

getList();
</script>
