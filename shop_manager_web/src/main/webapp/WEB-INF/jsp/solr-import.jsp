<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function importItems(){
		$.post("/solr/item/import", function(data){
			if(data.status == 200){
				$.messager.alert('导入成功!');
			}
		});
	}
</script>
	<div>
		<a class="easyui-linkbutton" href="javaScript:void(0)" onclick="importItems();">一键导入索引库</a>
	</div>
