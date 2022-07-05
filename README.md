# KAIST_Project_1
카이스트 몰입캠프 공통과제 1
# KAIST_Project_1
카이스트 몰입캠프 공통과제 1
# 멋진사나이

> Week 1 3분반 김&박
> 
- 로그인을 통한 User별 전화번호부와 사진첩을 관리해주는 Android 기반 어플리케이션입니다.

# TAB 1 - 연락처!

![스크린샷 2022-07-05 오후 8 40 45](https://user-images.githubusercontent.com/65712771/177319509-0d02632a-aa33-4895-987f-8649daca7f10.png)

### Major Features


- 하단의 ADD 버튼을 통하여 연락처에 사람을 추가할 수 있습니다.
- 하단의 CLEAR ALL 버튼을 통하여 전체 삭제를 할 수 있습니다.
- 각각의 연락처 목록 옆의 CALL 버튼을 통하여  바로 통화를 할 수 있습니다.
- 각각의 연락처 목록을 클릭하면 상세 페이지로 이동할 수 있습니다.
<p align="center">
<img src = "https://user-images.githubusercontent.com/65712771/177320563-945be3c4-6bf8-477f-a33b-acea9db244b9.png" width="25%" height="25%">

### 기술 설명

- ListView를 사용하여 구현하였습니다.
- Fragment간의 이동을 통하여 버튼을 눌렀을때 화면 전환을 구현하였습니다.
- Intent를 통하여 핸드폰의 통화 앱으로 연결되도록 구현하였습니다.

# TAB 2 - 갤러리

![스크린샷 2022-07-05 오후 8 44 15](https://user-images.githubusercontent.com/65712771/177320107-2aa80d1b-5fae-4550-a17f-852506d638f5.png)

### Major Features

- ADD IMAGE 버튼을 통하여 핸드폰의 갤러리와 연동하여 사진을 받아올 수 있습니다.
- CLEAR ALL 버튼을 통하여 전체 삭제를 할 수 있습니다.
- 삭제하고 싶은 사진을 길게 누르면 사진을 삭제 할 수 있습니다.

### 기술 설명

- RecyclerView를 통하여 구현하였습니다.
- setOnLongClickListener을 통하여 길게 눌렀을때 사진이 remove되도록 구현하였습니다.
- Intent를 통하여 핸드폰의 갤러리로 이동하도록 구현하였습니다.

# TAB3 - 로그인

![스크린샷 2022-07-05 오후 8 46 00](https://user-images.githubusercontent.com/65712771/177320375-32efc2d2-6a9c-490c-ad9c-c27aa5a41974.png)

### Major Features

- Sign In 버튼을 통하여 회원가입을 할 수 있습니다.
- 회원가입한 User은 Log In 버튼을 통하여 로그인 할 수 있습니다.
- Save Info 버튼을 통하여 TAB 1과 TAB 2에서 수정한 사항을 저장 할 수 있습니다.

### 기술설명

- 파일 입출력과 json을 이용하여 User 목록을 만들었습니다.
