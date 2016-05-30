# Echo Client 

This client needs to be run together with the [Echo Server](https://github.com/RabeaGleissner/java-echo-server).

## Start the server first

Clone [the server repo](https://github.com/RabeaGleissner/java-echo-server), navigate into the project folder and then type

`mvn package`

`java -cp target/server-1.0-SNAPSHOT.jar Main`

## Run the client

Once the server is running, you can start the client.

Clone this repo, navigate into the project folder and type:

`mvn package`

`java -cp target/echo-client-1.0-SNAPSHOT.jar Main`

Once the client is running, you can type anything into the console and it will be echoed back to you.

## Commands you can use

`#quit` -- disconnects the client

#### Not yet implemented

`#reverse` -- reverse mode - echoes back what you typed in reverse

`#normal` -- normal mode - echoes back what you typed
