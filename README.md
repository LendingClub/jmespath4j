# jmespath4j

[![CircleCI](https://circleci.com/gh/LendingClub/jmespath4j.svg?style=svg)](https://circleci.com/gh/LendingClub/jmespath4j)
[ ![Download](https://api.bintray.com/packages/robschoening/io-macgyver/jmespath4j/images/download.svg) ](https://bintray.com/robschoening/io-macgyver/jmespath4j/_latestVersion)

[JMESPath](http://jmespath.org/) is a fantastic expression language for JSON.  Oddly enough, a fully compliant java implementation has been slow to arrive.

jmespath4j is not a jmespath implementation, but rather a simple API that isolates applications from the underlying implementation.


## Support

| Implementation  |  jmespath4j provider artifact |  Comments |
|---|---|---|
| [jmespath-java](https://github.com/burtcorp/jmespath-java/blob/master/README.md)  |  jmespath4j-burt |   |


## Dependencies

Add the jmespath4j provider module that you would like to use from the table above.  For example, to use jmespath-java:

```xml
<dependency>
    <groupId>io.macgyver.jmespath4j</groupId>
    <artifactId>jmespath4j-burt</artifactId>
    <version>1.1.0</version>
</dependency>
```

or, with gradle:

```groovy
compile "io.macgyver.jmespath4j:jmespath4j-burt:1.1.0"
```

## Usage


Assuming that the following JSON is parsed into a Jackson JsonNode object named ```json```:

```json
{
    "foo" : "bar",
    "fizz" : "buzz"
}
```

Then the following will evaluate to "bar".
```java

String val = JMESPath.eval("foo",json).asText();
```




