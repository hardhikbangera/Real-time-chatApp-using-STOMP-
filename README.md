# 💬 Real-Time Chat App using STOMP

A Java-based WebSocket STOMP client and server built using Spring WebSocket.  
This project demonstrates real-time chat communication using the STOMP protocol over WebSocket, allowing multiple users to exchange messages instantly.

***

## 🚀 Features

- Real-time chat using STOMP over WebSocket  
- Spring-based STOMP broker configuration  
- Java CLI client for message exchange  
- JSON serialization with `MappingJackson2MessageConverter`  
- Minimal and educational structure for WebSocket + STOMP learning  

***

## 🧱 Tech Stack

| Component | Description |
|------------|--------------|
| Java 17+ | Programming language |
| Spring WebSocket | WebSocket and STOMP support |
| Spring Messaging | STOMP message handling |
| Tyrus Client | Java WebSocket implementation |
| Maven | Build and dependency management |

***

## ⚙️ Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/hardhikbangera/Real-time-chatApp-using-STOMP-.git
cd Real-time-chatApp-using-STOMP-
```

### 2. Run the Server
Below is an example Spring WebSocket server configuration compatible with this client:

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOriginPatterns("*");
    }
}
```

### 3. Build and Run the Client
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.demo.clientSTOMP.App"
```

When you run the client:
- Enter your username when prompted  
- Type messages to send to the chat  
- Type `exit` to disconnect  

***

## 📦 Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-websocket</artifactId>
        <version>6.1.2</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-messaging</artifactId>
        <version>6.1.2</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.tyrus.bundles</groupId>
        <artifactId>tyrus-standalone-client</artifactId>
        <version>2.1.3</version>
    </dependency>
</dependencies>
```

***

## 📖 How It Works

1. The server exposes a STOMP WebSocket endpoint at `/chat`.  
2. Each client connects via `WebSocketStompClient`.  
3. Clients subscribe to `/topic/message`.  
4. Messages sent by one client are broadcast to all subscribers in real time.  

***

## 🧠 Learning Purpose

This project helps you understand:
- How STOMP extends basic WebSocket communication  
- Spring’s message brokering with `@EnableWebSocketMessageBroker`  
- Building a WebSocket client in Java using STOMP  

***

## 👨‍💻 Author

**[Hardhik Bangera](https://github.com/hardhikbangera)**  
Java Developer 

