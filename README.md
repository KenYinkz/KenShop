# KenShop

Its a simple shop really!
A playground to show how beautiful Spring Boot is.

### Cool tools used:
1. Spring Boot 1.3.0, Spring Cache (not that this significantly matters now)
2. H2 - in memory database
3. Lombok (sparingly)
4. Rest
5. UI - Am thinking of using Angular...

### Installation
1. Pretty easy... clone the repo and pull down in your local machine
2. I used IntelliJ as STS gave me some weird maven issues.
3. Open the code in you IDE. You will need Lombok plugin (its okay to just install it, though there are more steps to get the best of out this plugin)
4. This is Spring boot...once your IDE finishes loading and building, give it a go! Run or debug the Application.java
 
### Let's get to the fun part!

This section will cover some basic steps to running this on your local machine

#### Spring Boot
- If you look at the pom file, you will notice the app runs using an embedded tomcat
- I had to set it as compile. Using scope `provided` will cause a runtime exception.
- Something to do with servlet issue. Though you can resolve that (not tried it yet) by adding necessary dependencies
- I am using H2 database.... to change to MySql should be pretty straightforward. Just add necessary propeties to the `application.properties` file
- Speaking of property file, have a look at the aforementioned file, remember to remove or change the logging levels. TRACE really ism't what you need.

#### Test
- I simply have an integration test in place. 
- You can provide extra test cases or even add unit tests in there.
- I also tried to use the `actuator`. Its quite powerful, but for the really simple task here I didn't go futher than mere setting it up
- What you might want to do later is to make sure its behind a secure endpoint.... right now, meh, `localhost:8080/health` is all you need to see if all is well

#### APIs

The guest, store and couple of items are hard coded - see the Application.java for more

1. Guest  - GET - `/guest`
2. Store - GET - `/store`
3. Items - GET - `/items`
4. Add to Cart - POST - `/cart`
5. View guest cart - GET - `/guest`
6. Add more items to cart - PUT - `/cart/{cartId}/items`
7. Update cart currency - GET - `/cart/{cartId}/{currency}`
7. Delete cart - GET - `/cart/{cartId}`

I will attach a Postman collection to make testing a whole lot easier.

#### Running the app

1. As you need the respective Ids for the objects and as, the database clears out after yhou shut the app down,
   always remember to run the *Guest*, *Store* APIs before trying to *Add to Cart*
2. Error messages are not spot on, so keep in mind that the way I have this set up is to avoid violating data constraints.
3. Like adding an invalid item to cart will fail and you will get a 400.


## Going Forward

1. Add Security
2. Add Oauth
3. Only access repositories within service. That way our controllers are only aware of the services
4. Alternatively fully utilise HATEOAS and change the repositories to `Rest Repositories`
5. Add expiry time to cart. Time to live enforced.