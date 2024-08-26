[ API 명세서 ]

| 기능 | Method | URL | Request | Response |
| --- | --- | --- | --- | --- |
| 유저 저장 | POST | /api/members | 요청 body | 상태코드,상태boolean값,메세지 |
| 유저 단건 조회 | GET | /api/members/{id} | 요청 path | 단건 유저 |
| 유저 다건 조회 | GET | /api/members | 요청 | 다건 유저 |
| 유저 수정 | PUT | /api/members/{id} | 요청 path, 요청 body | 상태코드,상태boolean값,메세지 |
| 유저 삭제 | DELETE | /api/members/{id} | 요청 path | 상태코드,상태boolean값,메세지 |
| 일정 저장  | POST | /api/calendars | 요청 body | 상태코드,상태boolean값,메세지 |
| 일정 단건 조회 | GET | /api/calendars/{id} | 요청 path | 단건 일정 |
| 일정 수정 | PUT | /api/calendars/{id} | 요청 path, 요청 body | 상태코드,상태boolean값,메세지 |
| 일정 삭제 | DELETE | /api/calendars/{id} | 요청 path | 상태코드,상태boolean값,메세지 |
| 댓글 저장 | POST | /api/calendars | 요청 body | 상태코드,상태boolean값,메세지 |
| 댓글 단건 조회 | GET | /api/calendars/{id} | 요청 path | 단건 댓글 |
| 댓글 다건 조회 | GET | /api/calendars | 요청 | 다건 댓글 |
| 댓글 수정 | PUT | /api/calendars/{id} | 요청 path, 요청 body | 상태코드,상태boolean값,메세지 |
| N페이지 조회 | GET | /api/calendars/{page}/{size} | 요청 path | 일정제목, 일정내용, 댓글개수, 일정작성일, 일정수정일, 일정작성유저명 |

[ ERD ]

![img.png](img.png)