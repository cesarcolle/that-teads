# Information
Since I understand nothing about the subject of the exercise I have to implement,
I'll change the subject. 


## New Subject

### The community lottery
The lottery gave N numbers.

There is a hidden solution S such that length(S) = N

Users can provide 1..N numbers to find the correct combination.
BUT it's possible to combine several numbers between participant.
Thus, we will have winners who they share the reward.

Example :
A => (1, 2, 3)
B => (1, 6, 8)
C => (1, 3, 7)
D => (5, 8)
and the given solution : Solution = (1, 2, 3, 5, 8)

winners => A, D


Of course this problem is no deterministic it could be reduces to the n-queens problem => NP problem.

### Details

Such Scala is a better language for our implementation, we use AKKA framework :

* Exposing API.
* Multi threading with the actor model.
