//0.0.4版本起 返回结果将有两部分组成:
/*
{
	index:[],	//旧版本的下标数组形式
	value:[]	//菜单中的valve,结构和下标结果数组一样,只是把下标替换成了value而已
}
*/
// 以下演示数据中,我故意把value设置成跟name一样,只是为了方便演示,使示例更加易懂,实际使用时候value应该是一个标识,给后台识别所用的.
// 数据较长，请仔细查看。
export default [{
    "name": '小区',
    "type": 'hierarchy',
    "submenu": [
		{
			"name": "不限",
			"value": ""
        },
	]
},
    {
        "name": '方式',
        "type": 'hierarchy',
        "submenu": [
            {
                "name": "不限",
                "value": ""
            },
            {
                "name": "整租",
                "value": "0"
            },
            {
                "name": "合租",
                "value": "1"
            },
        ]
    },
    {
        "name": '租金',
        "type": 'hierarchy',
        "submenu": [
            {
                "name": "不限",
                "value": ""
            },
            {
                "name": "<1000元",
                "value": "1000"
            },
            {
                "name": "1000-1500元",
                "value": "1500"
            },
            {
                "name": "1500-2000元",
                "value": "2000"
            },
            {
                "name": "2000-3000元",
                "value": "3000"
            },
            {
                "name": "3000-4500元",
                "value": "4500"
            },
            {
                "name": "<6000元",
                "value": "6000"
            }
        ]
    },
    {
        "name": '筛选',
        "type": 'radio',
        "submenu": [{
				"name": "居室",
				"submenu": [{
					"name": "一室",
					"value": "一室"
				},
				{
					"name": "二室",
					"value": "二室"
				},
				{
					"name": "三室",
					"value": "三室"
				},
				{
					"name": "四室",
					"value": "四室"
				}
            ]
        },
            {
                "name": "装修",
                "submenu": [
					{
                    "name": "简装",
                    "value": "简装",
					},
                    {
                        "name": "中装",
                        "value": "中装",
                    },
                    {
                        "name": "精装修",
                        "value": "精装修"
                    },
                    {
                        "name": "豪装",
                        "value": "豪装"
                    }
                ]
            },
            {
                "name": "房屋亮点",
                "submenu": [
					{
						"name": "独卫",
						"value": "独卫"
					},
					{
						"name": "独立阳台",
						"value": "独立阳台"
					},
					{
						"name": "精装修",
						"value": "精装修"
					},
					{
						"name": "智能锁",
						"value": "智能锁"
					},
					{
						"name": "可短租",
						"value": "可短租"
					},
					{
						"name": "首次出租",
						"value": "首次出租"
					},
					{
						"name": "免物业费",
						"value": "免物业费"
					},
					{
						"name": "民用水电",
						"value": "民用水电"
					},
                ]
            }
        ]
    }
]

