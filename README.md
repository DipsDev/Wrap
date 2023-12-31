
# Wrap
A redis like database, written in java




## Usage

Wrap currently only supports `string, integer, hashmap and sorted\unsorted lists` types

### Strings
Strings well, are strings.
```
127.0.0.1:6379> set author Ido
OK 
127.0.0.1:6379> get author
Ido
127.0.0.1:6379> strlen author
(Integer) 3
```

### HashMaps
Hashmaps are implemented using, well, hashmaps, that are known to have a constant time complexity.
```
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
4) (Integer) 17
```

### (Unsorted) Lists
(Unsorted) lists are implemented using doubly linked list, which reduces the time it takes to search in a list.
```
127.0.0.1:6379> lpush myunsortedlist "ido"
OK
127.0.0.1:6379> lpush myunsortedlist "github"
OK
127.0.0.1:6379> lrange myunsortedlist 0 2
1) github
2) ido
```

### (Sorted) Lists
Sorted lists are implemented using skip lists, which reduces significantly the time complexity.
```
127.0.0.1:6379> zadd mysorted 100 "my_great_string"
OK

more commands to be added
```


## Side Notes
Adding a command is a simple as it gets:

Create a class in the registers package, implement the `Command` interface,
to close off, make sure to annotate the class with `@RegisteredCommand`.

And that's it!

_Wrap uses the Reflection API to make it easier to add commands._


## Roadmap

- ~~Sorted Lists~~

- ~~Hashmaps~~


