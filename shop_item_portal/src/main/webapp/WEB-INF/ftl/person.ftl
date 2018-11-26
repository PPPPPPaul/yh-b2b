<html>
	<table>
		<tr>
			<td>编号${n!"我是null"}</td>
			<td>姓名</td>
			<td>手机号</td>
			<td>住址</td>
		</tr>
		<#list person as p>
			<#if p_index%2==0>
				<tr bgcolor="pink">
				<#else>
				<tr bgcolor="blue">
			</#if>
					<td>${p.id!}</td>
					<td>${p.name!}</td>
					<td>${p.phone!}</td>
					<td>${p.address!}</td>
				</tr>
		</#list>
	</table>
</html>