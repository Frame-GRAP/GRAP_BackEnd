# GRAP 서비스 BackEnd Repository
- [GRAP_FrontEnd](https://github.com/Frame-GRAP/GRAP_FrontEnd)  
- [GRAP_Admin](https://github.com/Frame-GRAP/GRAP_Admin)  
- [GRAP_RecommendationSystem](https://github.com/Frame-GRAP/GRAP_RecommendationSystem)
----------------------------------------------------------------  
## 목차
[1. 개요](#1-개요)  
[2. 시스템 개요](#2-시스템-개요)  
[3. 데이터베이스](#3-데이터베이스)  
[4. 외부 API 목록](#4-외부-api-목록)  
[5. 소개 영상](#5-소개-영상)


## 1. 개요
> 최근 게임 산업의 전례없는 흥행으로 인해 게임을 즐기는 인구의 비율이 나날이 늘어가고 있다. 이에 따라 새로운 게임에 대한 수요 또한 증가하고 있으나, 현재 새로운 게임을 소개 및 추천하는 전문 플랫폼이 없고, 대체 수단인 스팀 등의 온라인 게임 다운로드 플랫폼에서는 단순한 이미지, 짧은 영상으로 게임을 소개하고 있어 그 기능을 제대로 수행하지 못한다. 이에 따라 기존의 문제점을 적극적으로 해결할 방안으로 넷플릭스의 맞춤 추천에 효율적인 UI/UX, 실제 게임 플레이 영상, 머신러닝을 이용한 개인별 맞춤화 게임 추천 서비스를 융합하여 유익한 정보와 더 나은 추천을 제공하는 플랫폼인 ‘GRAP’을 기획하게 되었다.  


## 2. 시스템 개요
<img src="https://user-images.githubusercontent.com/67397679/120896409-57f69200-c65c-11eb-8c16-47b9767ba089.png" width="500">

AWS EC2, S3, RDS 및 Spring을 사용하여 백엔드를, React를 사용하여 프론트엔드를 개발하고, Travis CI, CodeDeploy를 활용하여 DevOps를 구축한다. 또한 Jsoup, Selenium으로 영상 크롤링을 구현한다. 추천 알고리즘으로는 hybrid filtering을 사용하여 content-based filtering 기법으로 초기의 cold-start 문제를 해결하고, 이 후 collaborative filtering 기법으로 보다 향상된 개인별 맞춤화 추천 서비스를 구현한다. 이 때 Flask를 이용하여 학습 서버를 운용하고, 정기적으로 Spring API 서버로부터 S3를 통해 데이터를 받아 학습하여 추천 결과를 갱신한다.

## 3. 데이터베이스

<img src="https://user-images.githubusercontent.com/67397679/120896645-6f824a80-c65d-11eb-9c33-6a1826dad003.png" width="200">

이름(테이블 명)| 설명| 
---- | ---- |
Category|카테고리 명칭|
Category Tab|카테고리 탭|
Coupon|쿠폰 명칭|
Custom Tab|카테고리 이외의 탭(ex. 최근 인기 게임 등)|
Favor|유저가 찜한 게임|
Video|게임별 영상|
Game|게임|
Game and Category|카테고리별 게임 매핑|
Game and Custom Tab|커스텀 탭별 게임 매핑|
Membership|가입 가능한 멤버십 종류|
Payment|결제 내역|
Related Game|게임별 연관도|
Report|신고|
Review|평점 및 댓글|
Review Value|각 리뷰에 대한 좋아요 및 싫어요|
Starter|신규 유저의 선호도 조사를 위한 게임들|
User|사용자 정보|
User and Coupon|유저가 소유하고 있는 쿠폰 매핑|
User and Category Preference|유저의 카테고리 선호도 매핑|
User and Game Preference|유저의 게임 선호도 매핑|
User Recommend Game|유저에게 추천되는 게임 매핑|



## 4. 외부 API 목록
* [I'm port](https://www.iamport.kr/)
* [Steam Works](https://partner.steamgames.com/doc/api?l=koreana)
* [Kakao Developers](https://developers.kakao.com/)
* [Naver Developers](https://developers.naver.com/main/)
* [Google Cloud Platform](https://console.cloud.google.com/apis/)

## 5. 소개 영상
https://softcon.ajou.ac.kr/works/works.asp?uid=425
