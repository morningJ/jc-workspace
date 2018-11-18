$table = $("#channelRecords");
$(function () {
    // 1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    // 2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#channelRecords').bootstrapTable({
            url: '/channel/list',   //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            //contentType: "application/x-www-form-urlencoded",//post请求的话就加上这个句话
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
           // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "pk1",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是 否显示详细视图
            detailView: false,                   //是否显示父子表

            showExport: true,  //是否显示导出按钮
            exportTypes: ['excel'],  //导出文件类型
            exportDataType: "all",              //basic', 'all', 'selected'.
            exportOptions: {
                fileName: '渠道信息表',  //文件名称设置
                tableName: '渠道信息表'
            },
            responseHandler:function(res){
                return {
                    rows : res.channels,
                    total : res.total
                }
            },
            columns: [
//            	{
//                checkbox: false
//            	},
                {
                    field: 'channelId',
                    title: '渠道编号'
                }, {
                    field: 'channelName',
                    title: '渠道名称'
                }, {
                    field: 'channelEname',
                    title: '渠道英文简称'
                }, {
                    field: 'protocol',
                    title: '通讯协议'
                }, {
                    field: 'length',
                    title: '报文头长度'
                }, {
                    field: 'messageType',
                    title: '报文格式'
                }, {
                    field: 'port',
                    title: '请求端口'
                },{
                    field: 'reqUnicode',
                    title: '请求字符集'
                },  {
                    field: 'resUnicode',
                    title: '返回字符集'
                }, {
                    field: 'contactName',
                    title: '联系人姓名'
                }, {
                    field: 'contactPhone',
                    title: '联系人电话'
                }
                , {
                    field: 'remark',
                    title: '备注'
                }
                ,{
                    field:"operate",
                    title:"操作",
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }]
        });
    };
    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            channelId: $("#channelId").val(),
            channelName: $("#channelName").val(),
            channelEname: $("#channelEname").val(),
            protocol: $("#protocol").val(),
            messageType: $("#messageType").val(),
            port: $("#port").val(),
        };
        return temp;
    };
    return oTableInit;
};

var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};
    oInit.Init = function () {
        $("#channelSelect").click(function () {
            $("#channelRecords").bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: 10});
        });
    };
    return oInit;
};

function operateFormatter(value, row, index) {
    return [
        '<a href="/channel/from?pk1='+row.pk1+'" >',
        '<i class="glyphicon glyphicon-edit"></i>修改',
        '</a>  ',
        '<a class="remove" href="javascript:void(0);">',
        '<i class="glyphicon glyphicon-remove"></i>删除',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click .remove': function (e, value, row, index) {
        operaModel.delRow(row.pk1, '/channel/delete', 'pk1');
    }
};

