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
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['construction:photos:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['construction:photos:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['construction:photos:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['construction:photos:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="photosList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="photoId" />
      <el-table-column label="项目ID" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName"/>
      <el-table-column label="施工队ID" align="center" prop="teamId" />
      <el-table-column label="施工队名称" align="center" prop="teamName" width="100"/>
      <el-table-column label="施工队负责人" width="110" property="teamLeaderName"></el-table-column>
      <el-table-column label="负责人联系方式" width="120" property="teamContactNumber"></el-table-column>
      <el-table-column label="上传图片用户ID" width="130" align="center" prop="userId" />
      <el-table-column label="用户名称" property="userName"></el-table-column>
      <el-table-column label="图片存储地址" align="center" prop="photoUrl" width="110">
        <template #default="scope">
          <image-preview :src="scope.row.photoUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['construction:photos:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['construction:photos:remove']">删除</el-button>
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

    <!-- 添加或修改施工现场对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="photosRef" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="项目ID" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="施工队ID" prop="teamId">
          <el-input v-model="form.teamId" placeholder="请输入施工队ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="图片存储地址" prop="photoUrl">
          <image-upload v-model="form.photoUrl"/>
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

<script setup name="Photos">
import { listPhotos, getPhotos, delPhotos, addPhotos, updatePhotos } from "@/api/construction/photos";

const { proxy } = getCurrentInstance();

const photosList = ref([]);
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
    userId: null,
    uploadTime: null
  },
  rules: {
    projectId: [
      { required: true, message: "项目ID不能为空", trigger: "blur" }
    ],
    teamId: [
      { required: true, message: "施工队ID不能为空", trigger: "blur" }
    ],
    userId: [
      { required: true, message: "用户ID不能为空", trigger: "blur" }
    ],
    photoUrl: [
      { required: true, message: "图片存储地址不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询施工现场列表 */
function getList() {
  loading.value = true;
  listPhotos(queryParams.value).then(response => {
    photosList.value = response.rows;
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
    photoId: null,
    projectId: null,
    teamId: null,
    userId: null,
    photoUrl: null,
    uploadTime: null
  };
  proxy.resetForm("photosRef");
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
  ids.value = selection.map(item => item.photoId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加施工现场";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _photoId = row.photoId || ids.value
  getPhotos(_photoId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改施工现场";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["photosRef"].validate(valid => {
    if (valid) {
      if (form.value.photoId != null) {
        updatePhotos(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPhotos(form.value).then(response => {
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
  const _photoIds = row.photoId || ids.value;
  proxy.$modal.confirm('是否确认删除施工现场编号为"' + _photoIds + '"的数据项？').then(function() {
    return delPhotos(_photoIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('construction/photos/export', {
    ...queryParams.value
  }, `photos_${new Date().getTime()}.xlsx`)
}

getList();
</script>
