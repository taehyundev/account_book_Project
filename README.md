# 가계부
Java로 만듬

# mysql 구성
table userlist 로 만듬

```mysql
create table userlist(
    idx int not null auto_increment,
    id varchar(250) not null,
    pwd varchar(250) not null,
    primary key(idx);
)
```

# 추가사항
csv 파일 다른 사용자로 로그인했을때 수동으로 파일 저장하는 기능 추가