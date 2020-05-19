garmin-api
==========
Generic connector for Garmin Rest API written in Java.

Features
--------
- Supports UserSummary and HeartRates
- Allows you to persist Http Session to file so you can workaround Garmin API login amount limitations
- Generic JSON reponses - you can create your own models


Usage
-----
1. Basic usage:
```
// Login for GarminSession

LoginConnector loginConnector = new LoginConnector();
GarminSession garminSession = loginConnector.login("your.login@email.com", "password");

// Fetch data from API

DataConnector dataConnector = new DataConnector(garminSession);
Map<String,Object> userSummary = dataConnector.getUserSummary(LocalDate.now().minusDays(1));
Map<String,Object> heartRates = dataConnector.getHeartRates(LocalDate.now().minusDays(1));

System.out.println(userSummary);
System.out.println(heartRates);
System.out.println(garminSession);

```

2. You can also serialize (e.g. into file) GarminSession object and reuse it during next application execution:

```
String json = garminSession.toJson();
GarminSession reusedGarminSession = GarminSession.createFromJson(json);

// Login is not needed
DataConnector anotherDataConnector = new DataConnector(reusedGarminSession);
Map<String,Object> anotherUserSummary = dataConnector.getUserSummary(LocalDate.now().minusDays(1));
```

Todo
----
- More Datasets support
