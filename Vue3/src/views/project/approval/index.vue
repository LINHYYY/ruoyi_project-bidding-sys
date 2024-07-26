<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="120px">
      <el-form-item label="审核人ID" prop="approverId">
        <el-input
          v-model="queryParams.approverId"
          placeholder="请输入审核人ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交人ID" prop="submitterId">
        <el-input
          v-model="queryParams.submitterId"
          placeholder="请输入提交人ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交审核的日期" prop="submitDate" >
        <el-date-picker clearable
          v-model="queryParams.submitDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择提交审核的日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核完成的日期" prop="approveDate">
        <el-date-picker clearable
          v-model="queryParams.approveDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择审核完成的日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核状态" prop="approvalStatus">
        <el-select v-model="queryParams.approvalStatus" placeholder="请选择审核状态" clearable>
          <el-option
            v-for="dict in approval_status"
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
          v-hasPermi="['project:approval:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:approval:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:approval:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['project:approval:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="approvalList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="审核项目ID"  width="100" align="center" prop="approvalId" />
      <el-table-column label="审核人ID" align="center" prop="approverId" />
      <el-table-column label="提交人ID" align="center" prop="submitterId" />
      <el-table-column label="项目图片" align="center" prop="imageUrl" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.imageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="上传附件文件" align="center" prop="attachmentPath" show-overflow-tooltip/>
      <el-table-column label="提交审核的日期" align="center" prop="submitDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.submitDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核完成的日期" align="center" prop="approveDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.approveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="approvalStatus">
        <template #default="scope">
          <dict-tag :options="approval_status" :value="scope.row.approvalStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['project:approval:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['project:approval:remove']">删除</el-button>
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

    <!-- 添加或修改项目审核表对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="approvalRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item v-show="flag" label="审核人ID" prop="approverId">
          <el-input v-model="form.approverId" placeholder="请输入审核人ID" />
        </el-form-item>
        <el-form-item label="提交人ID" prop="submitterId">
          <el-input v-model="form.submitterId" placeholder="请输入提交人ID" />
        </el-form-item>
        <el-form-item label="项目图片" prop="imageUrl">
          <image-upload v-model="form.imageUrl"/>
        </el-form-item>
        <el-form-item label="上传附件文件" prop="attachmentPath">
          <file-upload v-model="form.attachmentPath"/>
        </el-form-item>
        <el-form-item label="提交审核的日期" prop="submitDate">
          <el-date-picker clearable
            v-model="form.submitDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择提交审核的日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-show="flag" label="审核完成的日期" prop="approveDate">
          <el-date-picker clearable
            v-model="form.approveDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择审核完成的日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-show="flag" label="审核状态" prop="approvalStatus">
          <el-select v-model="form.approvalStatus" placeholder="请选择审核状态">
            <el-option
              v-for="dict in approval_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
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

<script setup name="Approval">
import { listApproval, getApproval, delApproval, addApproval, updateApproval } from "@/api/project/approval";

const { proxy } = getCurrentInstance();
const { approval_status } = proxy.useDict('approval_status');

const approvalList = ref([]);
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
    approverId: null,
    submitterId: null,
    imageUrl: null,
    attachmentPath: null,
    submitDate: null,
    approveDate: null,
    approvalStatus: null
  },
  rules: {
    submitterId: [
      { required: true, message: "提交人ID不能为空", trigger: "blur" }
    ],
    submitDate: [
      { required: true, message: "提交审核的日期不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询项目审核表列表 */
function getList() {
  loading.value = true;
  listApproval(queryParams.value).then(response => {
    approvalList.value = response.rows;
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
    approvalId: null,
    approverId: null,
    submitterId: null,
    imageUrl: null,
    attachmentPath: null,
    submitDate: null,
    approveDate: null,
    approvalStatus: null
  };
  proxy.resetForm("approvalRef");
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
  ids.value = selection.map(item => item.approvalId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加项目审核表";
  flag.value = false;
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _approvalId = row.approvalId || ids.value
  getApproval(_approvalId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改项目审核表";
    flag.value = true;
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["approvalRef"].validate(valid => {
    if (valid) {
      if (form.value.approvalId != null) {
        updateApproval(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addApproval(form.value).then(response => {
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
  const _approvalIds = row.approvalId || ids.value;
  proxy.$modal.confirm('是否确认删除项目审核表编号为"' + _approvalIds + '"的数据项？').then(function() {
    return delApproval(_approvalIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('project/approval/export', {
    ...queryParams.value
  }, `approval_${new Date().getTime()}.xlsx`)
}

getList();
</script>
