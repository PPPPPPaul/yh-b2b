<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table cellpadding="5" style="margin-left: 30px" id="itemParamAddTable" class="itemParam">
	<tr>
		<td>商品类目:</td>
		<td><a href="javascript:void(0)" class="easyui-linkbutton selectItemCat" >选择类目</a> 
			<input type="hidden" name="cid" style="width: 280px;"></input>
		</td>
	</tr>
	<tr class="hide addGroupTr">
		<td>规格参数:</td>
		<td>
			<ul>
				<li><a href="javascript:void(0)" class="easyui-linkbutton addGroup">添加分组</a></li>
				<li class="param">
					<ul>
						<li>
							<input class="easyui-textbox" style="width: 150px;" name="group"/>&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton addParam"  title="添加参数" data-options="plain:true,iconCls:'icon-add'"></a>
						</li>
						<li>
							<span>|-------</span><input  style="width: 150px;" class="easyui-textbox" name="param"/>&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton delParam" title="删除" data-options="plain:true,iconCls:'icon-cancel'"></a>						
						</li>
					</ul>
				</li>
			</ul>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<a href="javascript:void(0)" class="easyui-linkbutton submit">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton close">关闭</a>
		</td>
	</tr>
</table>
<div  class="itemParamAddTemplate" style="display: none;">
	<li class="param">
		<ul>
			<li>
				<input class="easyui-textbox" style="width: 150px;" name="group"/>&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton addParam"  title="添加参数" data-options="plain:true,iconCls:'icon-add'"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton delParam"  title="删除参数" data-options="plain:true,iconCls:'icon-cancel'"></a>
			</li>
			<li>
				<span>|-------</span><input  style="width: 150px;" class="easyui-textbox" name="param"/>&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton delParam" title="删除" data-options="plain:true,iconCls:'icon-cancel'"></a>
			</li>
		</ul>
	</li>
</div>
<script type="text/javascript">
	$(function(){
		HG.initItemCat({
			fun:function(node){
				$(".addGroupTr").hide().find(".param").remove();
					
				  $.ajax({
					   type: "GET",
					   url: "/item/param/query/itemcatid/" + node.id,//
					   success: function(data){
						  // alert(data.data);
						   if(data.status ==200 && data.data!=null){
							  $.messager.alert("提示", "该类目已经添加，请选择其他类目。", undefined, function(){
								 $("#itemParamAddTable .selectItemCat").click();
							  });
							  return ;
						  }
						  $(".addGroupTr").show();
					   },
					   error: function(){
						   alert("error");
					   }
					});
				}
			});
		
		$(".addGroup").click(function(){
			  var temple = $(".itemParamAddTemplate li").eq(0).clone();
			  $(this).parent().parent().append(temple);
			  temple.find(".addParam").click(function(){
				  var li = $(".itemParamAddTemplate li").eq(2).clone();
				  li.find(".delParam").click(function(){
					  $(this).parent().remove();
				  });
				  li.appendTo($(this).parentsUntil("ul").parent());
			  });
			  temple.find(".delParam").click(function(){
				  $(this).parent().parent().parent().remove();
			  });
		 });
		
		$("#itemParamAddTable .close").click(function(){
			$(".panel-tool-close").click();
		});
		
		$("#itemParamAddTable .submit").click(function(){
			var params = [];
			var groups = $("#itemParamAddTable [name=group]");//添加参数的按钮
			groups.each(function(i,e){
				var p = $(e).parentsUntil("ul").parent().find("[name=param]");
				//规格参数项信息
				var _ps = [];
				p.each(function(_i,_e){
				
					var _val = $(_e).siblings("input").val();//用于筛选同辈元素的表达式
					if($.trim(_val).length>0){
						_ps.push(_val);						
					}
				});
				//取规格分组值
				var _val = $(e).siblings("input").val();
				if($.trim(_val).length>0 && _ps.length > 0){
					params.push({
						"group":_val,
						"params":_ps 
					});					
				}
			});
			//JSON.stringify(params)将params转换为json字符串。
			//$("#itemParamAddTable [name=cid]").val(76)
			var url = "/item/param/save/"+$("#itemParamAddTable [name=cid]").val();
			alert(JSON.stringify(params));
			$.post(
					url,
					{"paramData":JSON.stringify(params)},
					function(data){
						if(data.status==200){
							$.messager.alert('提示','新增商品规格成功!',undefined,function(){
								$(".panel-tool-close").click();
			   					$("#itemParamList").datagrid("reload");
			   				});
						}else{
							$.messager.alert('提示',data.msg,undefined,function(){
								
			   				});
						}
						
					});
		});
	});
</script>