
# Wrap
A redis like database, written in java




## Usage

Wrap currently only supports `string` and `hashmap` types
```
127.0.0.1:6379> set author Ido
OK 
127.0.0.1:6379> get author
Ido

127.0.0.1:6379> hset user:1 username Dips
OK
127.0.0.1:6379> hget user:1 username
Dips
127.0.0.1:6379> hgetall user:1
1) username
2) Dips
                                                                                                                                                                                                                                                               
```


## Roadmap

- Sorted Lists

- ~~Hashmaps~~


