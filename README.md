### Eat Club Deals API Tech Challenge

- Based on Spring boot to expose Rest API
- Can be deployed as AWS Lambda+API gateway using SAM. Template provided
- Java 17
------------
#### Tasks
##### Task 1: Deals at a specified time
- /deals (http://localhost:8080/deals?timeOfDay=10:00am)

##### Task 2: Peak Time
- /deals/peak (http://localhost:8080/deals/peak)

##### Task 3: DB Design & AWS Deployment

###### Database Schema
[![DBSchema](https://raw.githubusercontent.com/kensilva/eatclubdealschallenge/refs/heads/main/schema/schema.png "DBSchema")](https://raw.githubusercontent.com/kensilva/eatclubdealschallenge/refs/heads/main/schema/schema.png "DBSchema")

#### Tables
1. **Restaurant** -  The restaurant details like pk(objectId), name address etc
2. **Deal** - The deal details, it has a FK to restaurant to link it. This will serve as primary link between restaurant and deals, It has a "1(Mandatory)-to-Many(Optional) relation ship. which means Deal should exist without a restaurant
3. **Cuisine** - This will be the table for all cuisines
4. **RestaurantCuisineRel** - the relational table for Restaurant and Cuisine, this means a Cuisine has Many-to-Many relation againt the Restaurant, meaning a restaurant can have many cuisines and vice versa. E.G RestaurantA can have Indian Cuisine as well as RestaurantB

#### Database Selection
###### MySQL(Preference)
- Support for AWS
- Support for Time
- Given the app being deals of a restaurant within a given day, I would assume that it would not contain millions of records, and can easily be maintained by cleanup. Based on my experience, we migrate to more expensive options when data becomes quite large.
- Familiarity, personal preference. :)

###### AWS Deployment
- The project has template.yaml for deploying with SAM, with it, it can be deployed as a lambda function with API gateway through the template.
------------
##### Notes
- *The project is based on springboot, it can also be ran/tested locally by itself using the maven task "springboot-run"*
- *The project can also be package/build as docker and deploy with other preferred docker deployment e.g K8s or ECS etc...*
------------
##### Exposed API: Temporary Only
###### Deals for the day
https://46kyq2w496.execute-api.ap-southeast-2.amazonaws.com/Stage/deals?timeOfDay=10:00pm

###### Peak
https://46kyq2w496.execute-api.ap-southeast-2.amazonaws.com/Stage/deals/peak

