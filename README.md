# tensquare

十次方项目源码   要的自取

base: 9002   Label   基本

recruit 9003   招聘


qa  9004  问答

article 9005 文章

gathering 9006 活动（Redis/Cache）

spit  9007  吐槽（MongoDB）

search:  9008 全文搜索elasticsearch

jpa的条件查询真麻烦

推荐大家使用mybatis-plus

docker 搭建环境(我用的是阿里云服务器，所以不会出现关闭的情况，如果要设置开机自启的请百度)

redis: docker run -di --name=tensquare_redis -p 6379:6379 redis

mysql: docker run -di --name=tensquare_mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 centos/mysql-57-centos7