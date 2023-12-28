
# Wrap
A redis like database, written in java




## Usage

Wrap currently only supports `string, integer and hashmap` types
```
127.0.0.1:6379> set author Ido
OK 
127.0.0.1:6379> get author
Ido

127.0.0.1:6379> hset user:1 username Dips
OK
127.0.0.1:6379> hget user:1 username
Dips
127.0.0.1:6379> hset user:1 age 17
OK
127.0.0.1:6379> hget user:1 age
(Integer) 17

127.0.0.1:6379> hgetall user:1
1) username
2) Dips
3) age
4) 17
                                                                                                                                                                                                                                                               
```


## Roadmap

- Sorted Lists

- ~~Hashmaps~~


