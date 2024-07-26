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
      <el-form-item label="文件类型" prop="fileType">
        <el-select v-model="queryParams.fileType" placeholder="请选择文件类型" clearable>
          <el-option
            v-for="dict in file_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="上传时间" prop="uploadTime">
        <el-date-picker clearable
          v-model="queryParams.uploadTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择上传时间">
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
          v-hasPermi="['construction:accept:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['construction:accept:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['construction:accept:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['construction:accept:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="acceptList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="fileId" />
      <el-table-column label="项目ID" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="施工队ID" align="center" prop="teamId" />
      <el-table-column label="施工队名称" align="center" prop="teamName" />
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="文件类型" align="center" prop="fileType">
        <template #default="scope">
          <dict-tag :options="file_type" :value="scope.row.fileType"/>
        </template>
      </el-table-column>
      <el-table-column label="文件存储路径" align="center" prop="fileUrl" show-overflow-tooltip/>
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['construction:accept:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['construction:accept:remove']">删除</el-button>
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

    <!-- 添加或修改施工验收表管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="acceptRef" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="项目ID" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="施工队ID" prop="teamId">
          <el-input v-model="form.teamId" placeholder="请输入施工队ID" />
        </el-form-item>
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="文件类型" prop="fileType">
          <el-select v-model="form.fileType" placeholder="请选择文件类型">
            <el-option
              v-for="dict in file_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文件存储路径" prop="fileUrl">
          <file-upload v-model="form.fileUrl"/>
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

<script setup name="Accept">
import { listAccept, getAccept, delAccept, addAccept, updateAccept } from "@/api/construction/accept";

const { proxy } = getCurrentInstance();
const { file_type } = proxy.useDict('file_type');

const acceptList = ref([]);
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
    projectId: null,
    teamId: null,
    fileName: null,
    fileType: null,
    uploadTime: null
  },
  rules: {
    projectId: [
      { required: true, message: "项目ID不能为空", trigger: "blur" }
    ],
    teamId: [
      { required: true, message: "施工队ID不能为空", trigger: "blur" }
    ],
    fileName: [
      { required: true, message: "文件名称不能为空", trigger: "blur" }
    ],
    fileType: [
      { required: true, message: "文件类型不能为空", trigger: "change" }
    ],
    fileUrl: [
      { required: true, message: "文件存储路径不能为空", trigger: "blur" }
    ],
  }
});




const { queryParams, form, rules } = toRefs(data);

/** 查询施工验收表管理列表 */
function getList() {
  loading.value = true;
  listAccept(queryParams.value).then(response => {
    acceptList.value = response.rows;
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
    fileId: null,
    projectId: null,
    teamId: null,
    fileName: null,
    fileType: null,
    fileUrl: null,
    uploadTime: null
  };
  proxy.resetForm("acceptRef");
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
  ids.value = selection.map(item => item.fileId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加施工验收表管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _fileId = row.fileId || ids.value
  getAccept(_fileId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改施工验收表管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["acceptRef"].validate(valid => {
    if (valid) {
      if (form.value.fileId != null) {
        updateAccept(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addAccept(form.value).then(response => {
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
  const _fileIds = row.fileId || ids.value;
  proxy.$modal.confirm('是否确认删除施工验收表管理编号为"' + _fileIds + '"的数据项？').then(function() {
    return delAccept(_fileIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('construction/accept/export', {
    ...queryParams.value
  }, `accept_${new Date().getTime()}.xlsx`)
}

getList();
</script>
