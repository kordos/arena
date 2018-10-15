
mysql na vagrancie:

docker run -d -p 3306:3306 --env="MYSQL_ROOT_PASSWORD=root" --name=test-mysql mysql

user: root
pass: root