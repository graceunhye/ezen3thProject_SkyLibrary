function userModify() {
	
	$("#userName").html("<input type='text' name='userName' value='"+$("#userName").text()+"'>");
	var userNumStr;
    var array = ($("#userNum").text().trim()).split("-");
    userNumStr = "<input type='text' name='userNumSplit1' value='"+array[0]+"' maxlength='3' size='3'>-";
    userNumStr += "<input type='text' name='userNumSplit2' value='"+array[1]+"' maxlength='4' size='3'>-";
    userNumStr += "<input type='text' name='userNumSplit3' value='"+array[2]+"' maxlength='4' size='3'>";
	$("#userNum").html(userNumStr);
	
	
	var userPostNumStr;
	userPostNumStr = "<input type='text' name='userPostNum' size='7' value='"+$("#userPostNum").text()+"'>&nbsp";
	userPostNumStr += "<input type='button' value='우편번호 찾기' onclick='PostOpen()'><br>";
	userPostNumStr += "<input type='text' name='userAddr' size='50' value='"+$("#userAddr").text()+"'><br>";
	userPostNumStr += "<input type='text' name='userAddrDetail' size='50' value='"+$("#userAddrDetail").text()+"'>";
	$("#userAddressInfo").html(userPostNumStr);
	

	var userEmailStr;
	var array3 = ($("#userEmail").text().trim()).split("@");
	userEmailStr = "<input type='text' name='userEmailID' value='"+array3[0]+"'> @";
	userEmailStr += "<input type='text' name='userEmailDomain' value='"+array3[1]+"'>";
	$("#userEmail").html(userEmailStr);
	
	
	var buttonStr = "<input type='button' value='저장하기' id='button' onclick='userInfoSaveFn()'>";
	$("#buttonArea").html(buttonStr);
	
}


function PostOpen(){
	new daum.Postcode({
	    oncomplete: function(data) {
	        //data는 사용자가 선택한 주소 정보를 담고 있는 객체
	    	$("input[name='userPostNum']").val(data.zonecode);
	    	$("input[name='userAddr']").val(data.address);
	    }
	}).open();
	
}

function userInfoSaveFn(){
	var userID			= $("#userID").text();
	var userName        = $("input[name='userName']").val();
	var userEmailID     = $("input[name='userEmailID']").val();
	var userEmailDomain = $("input[name='userEmailDomain']").val();
	var userNumSplit1   = $("input[name='userNumSplit1']").val();
	var userNumSplit2   = $("input[name='userNumSplit2']").val();
	var userNumSplit3   = $("input[name='userNumSplit3']").val();
	var userAddr        = $("input[name='userAddr']").val();
	var userAddrDetail  = $("input[name='userAddrDetail']").val();
	var userPostNum     = $("input[name='userPostNum']").val();
	
	
	if(userName == ""){
		alert("이름을 기입해주세요.");
		("input[name='userName']").focus();
		
	}else if(userEmailID == "" || userEmailDomain == ""){
		
		alert("이메일을 기입해주세요.");
		$("input[name='userEmailID']").focus();
		
	}else if(userNumSplit2 == "" || userNumSplit3 == "" || userNumSplit1 == ""){
	 	
		alert("번호를 입력해주세요.");
		$("input[name='userNumSplit1']").focus();
		
	}else{

			$.ajax({
				url: "/myPage/ajax/userInfoModifyOk",
				type: "POST",
				data: {
					userID: userID,
					userName: userName,
					userAddr: userAddr,
					userPostNum: userPostNum,
					userAddrDetail: userAddrDetail,
					userEmailID: userEmailID,
					userEmailDomain: userEmailDomain,
					userNumSplit1: userNumSplit1,
					userNumSplit2: userNumSplit2,
					userNumSplit3: userNumSplit3
				},error: function(){
					alert("submit function error");
				},success: function(data){

					if(data == 1){
						alert("변경되었습니다.")
						location.href="/myPage/userInfo";
					}else {
						alert("오류가 발생했습니다.");
					}
				}
				
			})
	}

}
