# Information
Since I understand nothing about the subject of the exercise I have to implement,
I'll change the subject. 


## The subject

### Auction

Allowing user to declare bids such that :

    -  winner is the one with the highest bid.
    -  winning price is the highest bid from a no-winner.
    
###### So finally, the winner will pay the auction at the winning price
    
    
## Implementation
### How To ... run

To run this app please :

    sbt run

thus, the server will be available to **localhost:8080**

### How to ... use


This is a example : 


```bash
echo "add bids..."
curl -H "Content-Type: application/json" -X POST -d '{"name":"xyz", "numbers" : [1, 2] }' http://localhost:8080/addBids
curl -H "Content-Type: application/json" -X POST -d '{"name":"abc", "numbers" : [3, 4, 5] }' http://localhost:8080/addBids
curl -H "Content-Type: application/json" -X POST -d '{"name":"yycc", "numbers" : [1, 2] }' http://localhost:8080/addBids
curl -H "Content-Type: application/json" -X POST -d '{"name":"efg", "numbers" : [8, 9] }' http://localhost:8080/addBids
curl -H "Content-Type: application/json" -X POST -d '{"name":"yycc", "numbers" : [7, 10] }' http://localhost:8080/addBids

echo "launch lottery"
curl -H "Content-Type: application/json" -X GET  http://localhost:8080/lottery?reservedPrice=8
```
will give you :

```json
{"name" : "yycc","amount" : 9}
```

#### Enjoy



### Details

Such Scala is a better language for our implementation, we use AKKA framework :

* Exposing API.
* Multi threading with the actor model.

I think building an app with akka allow you to use deep functionality of scala.


