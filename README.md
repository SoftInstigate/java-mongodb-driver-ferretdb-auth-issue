# Java MongoDbDriver FerretDb Auth Issue

This is a reproducer of an issue with the MongoDb Java Driver authentication with FerretDb 0.9.0.

## 1 - install sdkman

This helps installing java!

```bash
$ curl -s "https://get.sdkman.io" | bash
```

## 2 - install java 17

```bash
$ java install java 17.0.6-tem
```

## 3 - start FerretDb

```bash
$ docker compose up
```

## 4 - Run the test

```bash
$ ./mvnw clean test
```

## Error with MongoDatabase.createCollection()

`MongoDatabase.createCollection()` fails with following message (even if not creating a _capped_ collection)

```
Command failed with error 238 (NotImplemented): 'create: support for field "capped" is not implemented yet' on server localhost:27017. The full response is {"ok": 0.0, "errmsg": "create: support for field \"capped\" is not implemented yet", "code": 238, "codeName": "NotImplemented"}
```

## Error with authentication (FIXED)

The error comes from `SaslAuthenticator` class of the java driver that tries to get the `conversationId` (a BsonInt32) from the Sasl response but does not find it!:

```
com.mongodb.MongoSecurityException: Exception authenticating MongoCredential{mechanism=PLAIN, userName='username', source='$external', password=<hidden>, mechanismProperties=<hidden>}
....
Caused by: org.bson.BsonInvalidOperationException: Document does not contain key conversationId
```