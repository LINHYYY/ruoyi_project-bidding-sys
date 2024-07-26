<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="125px">
      <el-form-item label="成本填报" prop="costReporting">
        <el-input
          v-model="queryParams.costReporting"
          placeholder="请输入成本填报"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分成金额" prop="profitEstimate">
        <el-input
          v-model="queryParams.profitEstimate"
          placeholder="请输入分成金额"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分成比例" prop="bonusRatio">
        <el-input
          v-model="queryParams.bonusRatio"
          placeholder="请输入分成比例"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投标人id" prop="bidderId">
        <el-input
          v-model="queryParams.bidderId"
          placeholder="请输入投标人id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话号码" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入电话号码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为敏感资料" prop="sensitiveFlag">
        <el-select v-model="queryParams.sensitiveFlag" placeholder="请选择是否为敏感资料" clearable>
          <el-option
            v-for="dict in sensitive_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="预计项目启动日期" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择预计项目启动日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预计项目结束日期" prop="endDate">
        <el-date-picker clearable
          v-model="queryParams.endDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择预计项目结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核时间" prop="reviewTime">
        <el-date-picker clearable
          v-model="queryParams.reviewTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择审核时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="投标审核状态" prop="bidReviewStatus">
        <el-select v-model="queryParams.bidReviewStatus" placeholder="请选择投标审核状态" clearable>
          <el-option
            v-for="dict in bid_review_status"
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
          v-hasPermi="['project:bid_review_details:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:bid_review_details:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:bid_review_details:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['project:bid_review_details:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bid_review_detailsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="投标详细信息表id" align="center" prop="detailId" width="130" />
      <el-table-column label="成本填报" align="center" prop="costReporting" width="130"/>
      <el-table-column label="分成金额" align="center" prop="profitEstimate" width="130" />
      <el-table-column label="分成比例" align="center" prop="bonusRatio" />
      <el-table-column label="投标人id" align="center" prop="bidderId" />
      <el-table-column label="电话号码" align="center" prop="phoneNumber" width="130" />
      <el-table-column label="投标文件" align="center" prop="bidDocument" show-overflow-tooltip/>
      <el-table-column label="是否为敏感资料" align="center" prop="sensitiveFlag">
        <template #default="scope">
          <dict-tag :options="sensitive_flag" :value="scope.row.sensitiveFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="预计项目启动日期" align="center" prop="startDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预计项目结束日期" align="center" prop="endDate" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核时间" align="center" prop="reviewTime" width="120">
        <template #default="scope">
          <span>{{ parseTime(scope.row.reviewTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投标审核状态" align="center" prop="bidReviewStatus">
        <template #default="scope">
          <dict-tag :options="bid_review_status" :value="scope.row.bidReviewStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="130">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['project:bid_review_details:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['project:bid_review_details:remove']">删除</el-button>
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

    <!-- 添加或修改投标审核详细信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="bid_review_detailsRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item v-show="flag" label="项目编号" prop="id">
          <el-input v-model="form.id" placeholder="请输入项目编号" />
        </el-form-item>
        <el-form-item v-show="flag" label="成本填报" prop="costReporting">
          <el-input v-model="form.costReporting" placeholder="请输入成本填报" />
        </el-form-item>
        <el-form-item v-show="flag" label="分成金额" prop="profitEstimate">
          <el-input v-model="form.profitEstimate" placeholder="请输入分成金额" />
        </el-form-item>
        <el-form-item v-show="flag" label="分成比例" prop="bonusRatio">
          <el-input v-model="form.bonusRatio" placeholder="请输入分成比例" />
        </el-form-item>
        <el-form-item label="投标人id" prop="bidderId">
          <el-input v-model="form.bidderId" placeholder="请输入投标人id" />
        </el-form-item>
        <el-form-item label="电话号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入电话号码" />
        </el-form-item>
        <el-form-item label="投标文件" prop="bidDocument">
          <file-upload v-model="form.bidDocument"/>
        </el-form-item>
        <el-form-item label="是否为敏感资料" prop="sensitiveFlag">
          <el-select v-model="form.sensitiveFlag" placeholder="请选择是否为敏感资料">
            <el-option
              v-for="dict in sensitive_flag"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-show="flag" label="预计项目启动日期" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择预计项目启动日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-show="flag" label="预计项目结束日期" prop="endDate">
          <el-date-picker clearable
            v-model="form.endDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择预计项目结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-show="flag" label="审核时间" prop="reviewTime">
          <el-date-picker clearable
            v-model="form.reviewTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择审核时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-show="flag" label="投标审核状态" prop="bidReviewStatus">
          <el-select v-model="form.bidReviewStatus" placeholder="请选择投标审核状态">
            <el-option
              v-for="dict in bid_review_status"
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

<script setup name="Bid_review_details">
import { listBid_review_details, getBid_review_details, delBid_review_details, addBid_review_details, updateBid_review_details } from "@/api/project/bid_review_details";

const { proxy } = getCurrentInstance();
const { sensitive_flag, bid_review_status } = proxy.useDict('sensitive_flag', 'bid_review_status');

const bid_review_detailsList = ref([]);
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
    costReporting: null,
    profitEstimate: null,
    bonusRatio: null,
    bidderId: null,
    phoneNumber: null,
    bidDocument: null,
    sensitiveFlag: null,
    startDate: null,
    endDate: null,
    reviewTime: null,
    bidReviewStatus: null
  },
  rules: {
    bidderId: [
      { required: true, message: "投标人id不能为空", trigger: "blur" }
    ],
    phoneNumber: [
      { required: true, message: "电话号码不能为空", trigger: "blur" }
    ],
    bidDocument: [
      { required: true, message: "投标文件不能为空", trigger: "blur" }
    ],
    sensitiveFlag: [
      { required: true, message: "是否为敏感资料不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询投标审核详细信息列表 */
function getList() {
  loading.value = true;
  listBid_review_details(queryParams.value).then(response => {
    bid_review_detailsList.value = response.rows;
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
    detailId: null,
    id: null,
    costReporting: null,
    profitEstimate: null,
    bonusRatio: null,
    bidderId: null,
    phoneNumber: null,
    bidDocument: null,
    sensitiveFlag: null,
    startDate: null,
    endDate: null,
    reviewTime: null,
    bidReviewStatus: null
  };
  proxy.resetForm("bid_review_detailsRef");
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
  ids.value = selection.map(item => item.detailId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加投标审核详细信息";
  flag.value = false;
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _detailId = row.detailId || ids.value
  getBid_review_details(_detailId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改投标审核详细信息";
      flag.value = true;  
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bid_review_detailsRef"].validate(valid => {
    if (valid) {
      if (form.value.detailId != null) {
        updateBid_review_details(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addBid_review_details(form.value).then(response => {
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
  const _detailIds = row.detailId || ids.value;
  proxy.$modal.confirm('是否确认删除投标审核详细信息编号为"' + _detailIds + '"的数据项？').then(function() {
    return delBid_review_details(_detailIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('project/bid_review_details/export', {
    ...queryParams.value
  }, `bid_review_details_${new Date().getTime()}.xlsx`)
}

getList();
</script>
