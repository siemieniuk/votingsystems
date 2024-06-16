# voting-system
A voting system created using Spring and React


How to Start?

1. `docker compose up -d`
2. `docker exec -it mongo mongosh -u root -p example`
3. `use voting`
4. `db.createUser({user: "root", pwd: "example", roles: ["dbOwner"]})`