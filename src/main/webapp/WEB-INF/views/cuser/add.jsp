<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/head3.jsp"%>
<script type="text/javascript">
window.onload = function(){
		var $validateErrorContainer = $("#validateErrorContainer");
		var $validateErrorLabelContainer = $("#validateErrorContainer ul");
		var $validateForm = $("#validateForm");

		var $tab = $("#tab");

		// Tab效果
		$tab.tabs(".tabContent", {
			tabs : "input"
		});

		// 表单验证
		$validateForm.validate({
			wrapper : "li",
			errorClass : "validateError",
			ignoreTitle : true,
			errorPlacement : function(error, element) {
				var wrapHtm = '<label rel="tips"  style="color:red;">';
				wrapHtm += $(error).text();
				wrapHtm += '</label>'
				var $tips = $("label[rel='tips']", $(element).parent());
				if ($tips.size() > 0) {
					$tips.replaceWith($(wrapHtm));
				} else {
					$(wrapHtm).appendTo($(element).parent());
				}
			},

			success : function(label) {
			},
			rules : {
				"mobileNum" : {
					required : true,
					minlength : 11,
					number : true,
					maxlength : 11
				},
				"inviter" : {
					minlength : 11,
					number : true,
					maxlength : 11
				},
				"name" : {
					required : true,
					username : true,
					minlength : 2,
					maxlength : 20
				},
				"mileage" : {
					number : true
				},
				"password" : {
					required : true,
					minlength : 6,
					maxlength : 20
				},
				"rePassword" : {
					required : true,
					equalTo : "#password"
				}
			},

			messages : {
				"mobileNum" : {
					required : "请填写手机号",
					number : "手机号只允许数字",
					minlength : "手机号必须等于11",
					maxlength : "手机号必须等于11"
				},
				"inviter" : {
					number : "请填写邀请人手机号码",
					minlength : "手机号必须等于11",
					maxlength : "手机号必须等于11"
				},
				"name" : {
					required : "请填写昵称",
					username : "昵称只允许包含中文、英文、数字和下划线",
					minlength : "昵称长度必须大于等于2",
					maxlength : "昵称长度必须小于等于20"
				},
				"mileage" : {
					number : "只允许输入数字"
				},
				"password" : {
					required : "请填写密码",
					minlength : "密码必须大于等于6",
					maxlength : "密码必须小于等于20"
				},
				"rePassword" : {
					required : "请填写重复密码",
					equalTo : "两次密码输入不一致"
				}
			},
			submitHandler : function(form) {
				$(form).find(":submit").attr("disabled", true);
				form.submit();
			}
		});
		
		var input = document.getElementById("file_input");
        var result,div;
 
        if(typeof FileReader==='undefined'){
            result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
            input.setAttribute('disabled','disabled');
        }else{
            input.addEventListener('change',readFile,false);
        }　　　　
        function readFile(){
            for(var i=0;i<this.files.length;i++){
                if (!input['value'].match(/.jpeg|.jpg|.gif|.png|.bmp/i)){　
                    return alert("上传的图片格式不正确，请重新选择");
　　　　　　　　　	}
                var reader = new FileReader();
                
                reader.readAsDataURL(this.files[i]);
                reader.onload = function(e){
                    result = '<img width=100 src="'+this.result+'"> <input type="hidden" name="falseInput" value="'+this.result+'" />';
                    div = document.createElement('li');
                    div.innerHTML = result;
                    document.getElementById('PicUpload').appendChild(div);
    　　　　　　		}
            }
        }
    }
	
	 function photo(){
		document.getElementById("file_input").click();  
	 }
</script>
</head>
<body class="input">
	<div class="bar">
		添加客户(<span style="color: red;">*</span>为必填项)
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" name="member"
			action="${base}/admin/cuser/insert" method="post"
			enctype='multipart/form-data'>
			<input type="hidden" name="userType" value="0" />
			<div class="bar">
				<span style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;客户信息</span>
			</div>
			<table class="inputTable">
				<tr>
					<th>客户名称:</th>
					<td><input type="text" name="name" value="" class="formText" /><label
						class="requireField">*</label></td>
				</tr>
				<tr>
					<th>手机号码</th>
					<td><input type="text" name="mobileNum" value=""
						class="formText" /><label class="requireField">*</label></td>
				</tr>
				<tr>
					<th>密码:</th>
					<td><input id="password" type="password" name="password"
						value="" class="formText" /><label class="requireField">*</label></td>
				</tr>
				<tr>
					<th>重复密码:</th>
					<td><input type="password" name="rePassword" class="formText"
						value="" /><label class="requireField">*</label></td>
				</tr>
				<tr>
					<th>邀请人</th>
					<td><input type="text" name="inviter" value=""
						class="formText" /></td>
				</tr>
				<tr>
					<th>备注:</th>
					<td><textarea name="memo" class="formTextarea valid"></textarea></td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton"
					onclick="window.history.back(); return false;" value="返  回"
					hidefocus />
			</div>
		</form>
	</div>
</body>
</html>