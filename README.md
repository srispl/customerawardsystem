# Customer Reward System

Customer Rewards System is a restful API built with Spring boot to calculate the customer rewards based on following business logic

1. Customer and transaction information will be part of payload.
2. More than one customer data is supported.
3. More than 3 months Transaction information is not supported.
4. Data is not saved anywhere in the system. Once request sent, the system will calculate the monthly, total rewards and return in response.
5. Negative transaction amount is not supported.
6. Zero transaction amount is ignored.
7. Log4j is not enabled.
8. System supports docker with 8080 default port exposed.
9. All dependencies injected through Maven.


#### Steps

##### Clone source code from git
```
git clone https://github.com/srispl/customerawardsystem.git .
```
##### Install  maven dependencies
```
mvn clean install
```

##### Build Docker image
```
docker build -t="customer-rewards" .
```

##### Run Docker Container
```
docker run -p 8080:8080 -it customer-rewards
```

##### Test Health of application

```
curl localhost:8080/health
```

response should be:
```
Healthy
```

##### Test Customer rewards application

```
curl -X POST http://localhost:8080/calculateRewards -H "Content-Type: application/json" -d 'copy paste the request body below'
```

response should be:
```
response body is given in test data below.
```

#####  Stop Docker Container:
```
docker stop `docker container ls | grep " customer-rewards" | awk '{ print $1 }'`
```

### Run with docker-compose

Build and start the container by running

```
docker-compose up -d 
```

##### Test Health of application

```
curl localhost:8080/health
```

response should be:
```
Healthy
```

##### Test Customer rewards application

```
curl -X POST http://localhost:8080/calculateRewards -H "Content-Type: application/json" -d 'copy paste the request body below'
```

response should be:
```
response body is given in test data below.
```

##### Stop Docker Container:
```
docker-compose down
```


### Test Data

| Use Case | Response Body                                                                                                                                                                                               | Response Body                                                                                                                                                                                                                                          |
|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1        | {"customerInfoList":[{"name":"Bob","transactions":{"02-01-2023":20,"01-01-2023":51,"02-02-2023":100,"01-02-2023":51},"totalRewardPoints":52,"monthlyRewardPoints":{"1":2,"2":50}}]}                         | {"customerInfoList":[{"name":"Bob","transactions":{"02-01-2023":20,"01-01-2023":51,"02-02-2023":100,"01-02-2023":51},"totalRewardPoints":52,"monthlyRewardPoints":{"1":2,"2":50}}],"error":null}                                                       |                                                                                                                                                                                  |                                                                                                                                                                                                  |
| 2        | {"customerInfoList":[{"name":"Bob","transactions":{"02-01-2023":20,"01-01-2023":-51,"02-02-2023":100,"01-02-2023":51},"totalRewardPoints":0,"monthlyRewardPoints":null}]}                                   | {"customerInfoList":[{"name":"Bob","transactions":{"02-01-2023":20,"01-01-2023":-51,"02-02-2023":100,"01-02-2023":51},"totalRewardPoints":0,"monthlyRewardPoints":null}],"error":"Invalid transaction data"}                                           |                                                                                                                                                                                  |                                                                                                                                                                                                  |
| 3        | {"customerInfoList":[{"name":"Bob","transactions":{"02-01-2023":20,"01-01-2023":51,"02-02-2023":100,"04-27-2023":100,"03-02-2023":1000,"01-02-2023":51},"totalRewardPoints":0,"monthlyRewardPoints":null}]} | {"customerInfoList":[{"name":"Bob","transactions":{"02-01-2023":20,"01-01-2023":51,"02-02-2023":100,"04-27-2023":100,"03-02-2023":1000,"01-02-2023":51},"totalRewardPoints":0,"monthlyRewardPoints":null}],"error":"More than 3 months data provided"} |
| 4        | {"customerInfoList":[{"name":"Bob","transactions":null,"totalRewardPoints":0,"monthlyRewardPoints":null}]}                                                                                                  | {"customerInfoList":[{"name":"Bob","transactions":null,"totalRewardPoints":0,"monthlyRewardPoints":null}],"error":null}                                                                                                                                |
| 5        | {"customerInfoList":[{"name":"Bob","transactions":{},"totalRewardPoints":0,"monthlyRewardPoints":null}]}                                                                                                    | {"customerInfoList":[{"name":"Bob","transactions":{},"totalRewardPoints":0,"monthlyRewardPoints":null}],"error":null}                                                                                                                                  |
| 6        | {"customerInfoList":[]}                                                                                                                                                                                     | {"customerInfoList":null,"error":"Empty Customer info"}                                                                                                                                                                                                |
| 7        | {"customerInfoList":null}                                                                                                                                                                                   | {"customerInfoList":null,"error":"Empty Customer info"}                                                                                                                                                                                                |
