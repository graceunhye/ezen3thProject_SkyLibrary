# :books: SkyLibrary : 무인 도서 관리 시스템


|메인언어|사용툴|DB|그 밖에|
|:--:|:--:|:--:|:--:|
|java|Eclipse, Spring Framework|MySQL|Ajax, JSTL, JSP ...|


### :smile: 들어가기 전에...
 > 위 프로젝트는 1개월간은 MVC1모델로 구현했었습니다. 그리고 이후 1개월간은 MVC1을 분해해 MVC2로 만드는 작업을 했습니다. 위 과정을 통해 데이터가 어떻게 나뉘어지는지 자세히 알 수 있었습니다.

### :star2: 프로젝트 목표
>사서 없이 사람들이 책을 대출하고 반납할 수 있는 도서대여관리 시스템 구현

 그 밖에 시스템 구현을 통해 JSP, API, JAVA, Spring Framework에 대한 이해를 높이고자 하였습니다. 프로젝트 내에서 관리자 페이지(희망도서관리, 회원관리, 질의응답관리), 회원 페이지(도서통합검색, 희망도서신청 및 희망도서신청내역, 회원가입, 메인화면, 페이징 등)을 구현하였습니다.


관리자 페이지는 비동기식 조회 시스템입니다. 
화면 내 이동이 최소한이어야한다고 생각했습니다. 때문에 테이블을 고정시키고 스크롤을 만들어 스크롤로 간단하게 모든 정보를 볼 수 있도록 하였습니다.

---

## 희망도서관리
AJAX에서 URI를 조회해 Controller에 데이터를 전달하고 Service - DAO - MyBatis - MySQL 로부터 데이터를 받아 웹에 출력했습니다. 3-4가지의 검색옵션이 있으며, Mapper에서 IF문을 통해 검색 옵션이 들어왔을 때, 특정 정보가 들어왔을 때 등을 구분해 사용자가 원하는 정보가 나오도록 하였습니다. 그 밖에 조회된 정보마다 체크박스가 있어 많은 정보를 한번에 처리하기 편리하도록 하였습니다.
희망도서는 신청중/처리중/취소됨/소장중으로 나뉘었으며, 조회 정보를 체크해 처리구분을 저장하면 비동기식으로 곧바로 스크롤 테이블과 상세보기 모두 변경사항이 반영됩니다.

---
## 질의응답관리
질의 응답 구분은 답변대기/답변완료로 이루어져있으며, 검색기능은 없습니다.
질문응답관리는 질문 조회기능이 있습니다. 질문날짜/답변구분을 파라미터로 받아 조회합니다. 해당 페이지는 체크박스가 없으며 질문조회결과 1행을 클릭하면 각 행에 아이디를 매개변수로 받는 함수에 의해 상세보기가 하단에 나타납니다. 상세보기란에는 질문상세정보와 답변이 등록되지 않은 글은 글쓰기 기능이, 이미 답변이 달린 글은 수정 기능이 생깁니다. 수정/등록이 실행되면 데이터가 자동으로 다시 조회되고 조회 목록에 답변일이 채워지고 상세보기에도 답변완료 처리가 됩니다.

---

## 회원관리
회원관리는 관리자 페이지입니다. 관리자는 위 페이지에서 회원을 구분해 저장할 수 있습니다. 회원은 일반회원(default), 탈퇴회원, 강제탈퇴 회원으로 나뉩니다.
회원을 강제탈퇴 시키기 위해 회원을 외래키로 사용하는 테이블들 내 데이터를 지워야했습니다. 또한 답변테이블은 질문테이블을 외래키로 받기 때문에 가장 먼저 지워야했습니다. 모두 지우고 UPDATE문을 이용해 회원 구분을 탈퇴회원으로 변경했습니다. (다시 가입하는 것을 방지하기 위해서.)

회원 탈퇴 외에도 관리자는 회원이름/아이디/가입날짜 로 검색이 가능합니다. 검색 결과는 테이블 내에 AJAX에 의해 출력됩니다. 출력 된 결과 중 대출중(권), 연체여부는 대여테이블과 조인한 결과로 얻었습니다. 그 밖에도 회원정보 1행을 누를 시 하단에 상세정보가 나오는데, 서브쿼리를 사용해 상세한 도서대여정보를 가져올 수 있었습니다.

```sql
<select id="userSearchOk" resultType="java.util.Map" parameterType="searchVO">
	SELECT  userID,
			userPW,
			userName,
			userNum,
			userPostNum, 
			userAddr,
			userAddrDetail,
			userEmail,
			userType,
			DATE_FORMAT(userJoinDate, '%Y-%m-%d') as userJoinDate,
			(select count(bookISBN) from rent where userID=user.userID) as rentCount,
			<![CDATA[(select count(rentEndDate) from rent where userID=user.userID and rentEndDate < curdate()) as lateRentCount]]>
	FROM user
	WHERE 1=1
	<if test="selectType != null and selectType !='' and selectType != 3">
	AND userType=#{selectType}
	</if>
	<if test="startDate != null and endDate != null 
	and startDate != '' and endDate !=''">
	AND userJoinDate BETWEEN #{startDate} AND #{endDate}
	</if>
	<if test="searchText != null and searchText != ''">
		<if test="searchType == 'userID' ">
		AND userID LIKE CONCAT('%',#{searchText},'%')
		</if>
		<if test="searchType == 'userName' ">
		AND userName LIKE CONCAT('%',#{searchText},'%')
		</if>
	</if>
	ORDER BY userName ASC
</select>
```
위 프로젝트를 진행하다 원하는 DATA 포맷으로 출력하기 위하여 MySQL에서 제공하는 **DATAFORMAT()** 함수를 이용하여 **yyyy-mm-dd**포맷으로 데이터를 SELECT 하여 출력하였습니다.



---
## 로그인/회원가입/회원정보수정
회원가입/로그인은 사서/사용자로 나뉩니다. A링크를 함수로 연결해 AJAX로 HTML을 다르게 출렸했습니다. 아이디 패스워드를 입력 후 로그인 버튼 클릭 시 Controller로 URI경로/POST 로 이동해 Service 메서드/Repository 메서드를 타고 namespace경로인 mapper(root-context에서 지정된)의 경로를 찾아가고 mybatis-config.xml을 참고해 파라미터를 전달합니다. SQL문을 실행 후 출력된 결과를 return 받아 IF문으로 존재하는 아이디와 패스워드가 일치하는지 확인해 최종결과를 return합니다.

회원가입은 '아이디 중복체크'를 사용합니다. 아이디 중복체크 시 위와 동일한 경로를 거쳐 SQL WHERE를 사용해 존재 여부를 확인합니다. 또한 Controller에서 Patten 확인을 하여 적절한 아이디인지 검사합니다. 존재여부, 패턴 확인을 거치고 해당 결과를 String으로 전달해 AJAX에서 String 결과값에 따라 다른 alert을 띄우도록 하였습니다. 그 밖에도 DaumAPI를 사용해 우편번호, 주소를 받았습니다.

필수 항목을 기입하지 않았을 때 JS에서 검열을 합니다. 작성 요청 alert을 띄우고 채워야하는 input 박스에 포커스 합니다. 마지막으로 회원가입 최종 버튼을 누를 시 아이디 중복체크 여부/ 그 밖에 기입되었는지 등을 확인합니다. 아이디 중복체크는 광역변수에 불리언 타입의 변수를 선언하고 아이디 체크가 완료되는 순간 true로 저장합니다. 때문에 마지막에 모든 것을 체크 할 때 광역변수를 확인하고 submit 전에 다시 false 선언을 해줘 되돌립니다.

---
## 각종 검색창/페이징 구현
1차 프로젝트에선 검색+페이징이 아닌 검색 기능만 있었습니다. 2차 프로젝트로 오며 도서를 검색 시 목록, 페이징이 되며 키워드
/select option 값이 남도록 해야했습니다. 그러기 위해선 키워드, OPTION값, 현재페이지, 전체게시물 수, 한 페이지에 보여줄 게시물 수 가 필요했습니다. 먼저 SQL문에 파라미터로 보낼 객체를 만들었습니다. Paging 객체를 만들어 페이지와 관련된 변수들을 생성하고 생성자에 계산이 필요한 메서드들을 넣어 객체가 변수값을 받으면 자동으로 계산이 필요한 변수들을 저장하도록 했습니다. 모든 조건이 만족되고 SQL문에 파라미터로 페이징 객체를 넘겨 IF문을 통해 원하는 SQL문 결과를 얻을 수 있었습니다.

페이징된 JSP파일 내에는 페이지 번호를 클릭시 값을 전달할 수 있도록 GET방식으로 파라미터를 전달했습니다. 그 밖에도 ">"와 같은 부등호를 누를 시 nowPage=${nowPage + 1}로 다음 페이지로 바로 넘어갈 수 있도록 하였습니다. 이땐 종종 페이지 정보는 보내고 검색키워드을 보내지 않아 페이지를 넘길 때 검색결과가 초기화 되곤 했었습니다. 곧바로 오류를 발견하고 수정했던 기억이 납니다.

---

## 느낀점
전반적으로 최대한 도움을 받지 않고 수업과 개인공부로 모두 채우려 했습니다. 다양한 기능을 넣는 것보다 안정적으로 웹을 구현하는 게 더 중요하다고 생각했습니다. 프로젝트가 끝나가는 지금 좋은 결정이라 생각합니다. 새로운 정보를 침착하게 받아들이고 자신의 것으로 곱씹어 소화할 수 있는 능력이 생겼습니다.

새롭게 시도한 Spring Framework는 초반에 데이터의 흐름을 이해하려 노력했습니다. 단순히 controller, service..등이 필요하다고 암기하는 것이 아니라 각각의 경로가 왜 필요하고 그 경로에서 어떤 데이터/신호를 받는지 이해하려 했습니다. 후반부엔 Spring에 제법 익숙해졌습니다. 앞으로도 조금 느릴지라도 끊임없이 제것으로 만들고자 노력할 것 입니다. 코드 한 줄이라도 생각하며 쓰는 개발자가 되고싶습니다.