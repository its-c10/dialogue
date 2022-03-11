<!-- https://www.makeareadme.com/ -->
# Dialogue
![Release](https://jitpack.io/v/nthByte-LLC/dialogue.svg)

Dialogue is a Spigot API that completely revamps the Conversations API

## Installation

### Maven
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.nthByte-LLC</groupId>
    <artifactId>dialogue</artifactId>
    <version>Tag</version>
</dependency>
```
Replace "Tag" with the latest JitPack build

## Usage
Firstly, you want to hook into the API. Put this line in your main class.
```java
DialogueAPI.hook(this);
```
All this does is register the dialogue listener and initializes DialogueManager

From this point on, you can either look at this <a href="https://github.com/its-c10/dialogue-example">example plugin</a> that uses this API or refer to the <a href="https://github.com/nthByte-LLC/dialogue/wiki">wiki</a>





