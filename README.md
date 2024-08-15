# Megaverse project

## Running the project
In order to run this code, the properties `candidate_id` and `base_url` need to be added to the properties file.


## Design and behaviour
I recommend starting reading this code starting in `StartupRunner.java`, since it's where the "main" method is.

This project reads the current map and the goal map from the exposed APIs, calculate the difference, then create a `task`. These tasks will be handled by a number of workers in concurrence (see `tasks/Worker.java`), that will end up calling the services that will update the current map. 

For each Astral object I created a service that handles addition or deletion of that object. The objects are mapped to the `Astral` hierarchy in the `astral` package. 

Some of the complexity of the project is the fact that the way the goal map and the current map represent the same entities differently, this meant that I had to either come to terms with hard coding everything or work hard to have everything well abstracted, as I would in a working situation. I opted for the second option.


## Technologies 
I used Java and Spring Boot with minimal dependencies added. The only one added was Apache for creating Delete HttpRequests with a content body (I wasn't able to make it work easily with Spring).

I used VS Code as framework and used ChatGPT for the entirety of the project. I also used Postman to do initial testing for the API endpoints I would need to use in the challenge. 

I decided not to add javadocs and documentation, or other support for the project. It appears to me that the challenge was looking for faster, simpler code and not production ready code. I feel like I did not succeed in this though, since my code looks closer to production ready than to fast and easy.

