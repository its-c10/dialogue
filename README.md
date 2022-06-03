<p align="center">
  <img 
    width="400"
    height="400"
    src="https://user-images.githubusercontent.com/39465461/158046869-2d4b465c-99f0-4c88-ac26-63284f153adc.png"
  >
</p>

<!-- https://www.makeareadme.com/ -->

# Dialogue

![Release](https://jitpack.io/v/nthByte-LLC/dialogue.svg)

Dialogue is a Spigot API that completely revamps the Conversation API. <b><i>This is not a plugin you put on your
server</b></i>.

Want to know what's currently being worked on? Check out
the <a href="https://calebowens.youtrack.cloud/projects/9fdf6ba0-79d6-4879-9dfc-0fcf98a7e31a">task board</a> for this
project

## Installation

### Maven

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://www.jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.nthByte-LLC</groupId>
    <artifactId>dialogue</artifactId>
    <version>Tag</version>
</dependency>
```

Replace "Tag" with a release tag for Dialogue. You can see the latest
version <a href="https://github.com/nthByte-LLC/dialogue/releases">here</a>.

### Gradle

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```

```gradle
dependencies {
    implementation 'com.github.nthByte-LLC:dialogue:Tag'
}
```

Replace "Tag" with a release tag for Dialogue. You can see the latest
version <a href="https://github.com/nthByte-LLC/dialogue/releases">here</a>.

## Usage

Firstly, you want to hook into the API. Put this line in your main class.

```java
DialogueAPI.hook(this);
```

All this does is register the dialogue listener and initializes DialogueManager.

From this point on, you can either look at this <a href="https://github.com/nthByte-LLC/dialogue-example">example
plugin</a> that uses this API or refer to the <a href="https://github.com/nthByte-LLC/dialogue/wiki">wiki</a>

_I strongly encourage you to look at the example plugin_. The wiki may not always be up to date, but the example plugin
will always be up to date since I use it to test out the changes within this API.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Support

Need help or have questions? Join my <a href="https://discord.gg/ZP2xxC52An">discord</a>.




