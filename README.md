# happy-pets-day
1. 메인페이지 url
   별다른 url 없이 http://localhost:10000/
   치면 이동되게 일단 설정

2.  펫 시터 컨트롤러 필요없는거 삭제 SitterController만 남기고
    GetMapping 설정 주소혹시 불편하면 작업하는 사람 편한대로 나중에 설정하면 됨

3. 이미지 관련파일들 모두 staitc폴더 안에으로 이동

4. 본인이 작업하는  모든 html파일에 헤더랑, 푸터css적용시키기
  ```html
  <link rel="stylesheet" th:href="@{/css/fragment/header.css}">
  <link rel="stylesheet" th:href="@{/css/fragment/footer.css}">
```
붙여넣기

프레그먼트 자체에서는 css적용 안됨

body 제일상단
```html
<header th:replace="fragment/header :: header"></header>
```
body 제일 하단
```html
<footer th:replace="fragment/footer :: footer"></footer>
<script th:src="@{/js/fragment/header.js}"></script>
```
붙여넣기