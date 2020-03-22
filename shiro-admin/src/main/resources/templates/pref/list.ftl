<#include "/layout/header.ftl"/>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="/">首页</a></li>
            <li class="active">用户偏好</li>
        </ol>
        <div class="x_panel">
            <div class="x_content">
                <div class="<#--table-responsive-->">
                    <div class="btn-group hidden-xs" id="toolbar">
                        <@shiro.hasPermission name="user:add">
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="user:batchDelete">
                            <button id="btn_delete_ids" type="button" class="btn btn-default" title="删除选中">
                                </i> <strong>用户偏好分析</strong>
                            </button>
                        </@shiro.hasPermission>
                    </div>
                    <table id="tablelist">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/layout/footer.ftl"/>


<script>
    /**
     * 操作按钮
     * @param code
     * @param row
     * @param index
     * @returns {string}
     */
    function operateFormatter(code, row, index) {
        var currentUserId = '${user.id}';
        var trUserId = row.id;
        var operateBtn = [
            '<@shiro.hasPermission name="user:edit"><a class="btn btn-xs btn-primary btn-update" data-id="' + trUserId + '"><i class="fa fa-edit"></i>编辑预案</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="user:delete"><a class="btn btn-xs btn-danger btn-remove" data-id="' + trUserId + '"><i class="fa fa-trash-o"></i>删除远</a></@shiro.hasPermission>',
            '<@shiro.hasPermission name="user:allotRole"><a class="btn btn-xs btn-info btn-allot" data-id="' + trUserId + '"><i class="fa fa-circle-thin"></i>分配安全事件类型</a></@shiro.hasPermission>',
        ];
        return operateBtn.join('');
    }
    function operateFormatter1(code, row, index) {
            var currentUserId = '${user.id}';
            var trUserId = row.id;
            var operateBtn = [
                '<@shiro.hasPermission name="user:allotRole"><a class="btn btn-xs btn-info btn-allot" data-id="' + trUserId + '">手机族</a></@shiro.hasPermission>',
            ];
            return operateBtn.join('');
        }
    function operateFormatter2(code, row, index) {
                var currentUserId = '${user.id}';
                var trUserId = row.id;
                var operateBtn = [
                    '<@shiro.hasPermission name="user:allotRole"><a class="btn btn-xs btn-info btn-allot" data-id="' + trUserId + '">爱喝水</a></@shiro.hasPermission>',
                ];
                return operateBtn.join('');
            }

    $(function () {
        var options = {
            url: "/pref/list",
            getInfoUrl: "/user/get/{id}",
            updateUrl: "/user/edit",
            removeUrl: "/user/remove",
            createUrl: "/user/add",
            saveRolesUrl: "/user/saveUserRoles",
            columns: [
                {
                    checkbox: true
                }, {
                    field: 'id',
                    title: '用户id',
                    editable: false,
                }, {
                    field: 'car',
                    title: '车牌号',
                    editable: true
                }, {
                    field: 'tab1',
                    title: '标签1',
                    editable: true
                },
                {
                    field: 'tab2',
                    title: '标签2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                    editable: true
                },

            ],
            modalName: "偏好"
        };
        //1.初始化Table
        $.tableUtil.init(options);
        //2.初始化Button的点击事件
        $.buttonUtil.init(options);

        /* 分配预案角色 */
        $('#tablelist').on('click', '.btn-allot', function () {
            console.log("分配权限");
            var $this = $(this);
            var userId = $this.attr("data-id");
            $.ajax({
                async: false,
                type: "POST",
                data: {uid: userId},
                url: '/roles/rolesWithSelected',
                dataType: 'json',
                success: function (json) {
                    var data = json.data;
                    console.log(data);
                    var setting = {
                        check: {
                            enable: true,
                            chkboxType: {"Y": "ps", "N": "ps"},
                            chkStyle: "radio"
                        },
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback: {
                            onCheck: function (event, treeId, treeNode) {
                                console.log(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
                                var treeObj = $.fn.zTree.getZTreeObj(treeId);
                                var nodes = treeObj.getCheckedNodes(true);
                                var ids = new Array();
                                for (var i = 0; i < nodes.length; i++) {
                                    //获取选中节点的值
                                    ids.push(nodes[i].id);
                                }
                                console.log(ids);
                                console.log(userId);
                                $.post(options.saveRolesUrl, {"userId": userId, "roleIds": ids.join(",")}, function (obj) {
                                }, 'json');
                            }
                        }
                    };
                    var tree = $.fn.zTree.init($("#treeDemo"), setting, data);
                    tree.expandAll(true);//全部展开

                    $('#selectRole').modal('show');
                }
            });
        });
    });
</script>