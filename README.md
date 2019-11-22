# notification-service
![build](https://travis-ci.org/rso-vaje-6315/notification-service.svg)


## Usage

Service consumes following data from Kafka:

```json
{
  "sms": {
    "phoneNumber": "+386xxXXXxxx",
    "content": "Hello world from notification!"
  },
  "email": {
    "email": "person@mail.com",
    "subject": "Hello world!",
    "htmlContent": "<h1>Hello world!</h1><p>Hello world from notification</p>",
    "attachment": {
      "url": "https://download.oracle.com/javaee-archive/jax-rs-spec.java.net/jsr339-experts/att-3593/spec.pdf",
      "name": "jax-rs-spec.pdf"
    }
  }
}
```

Library provides object `ChannelNotification` which matches the required schema. If you leave out fields `sms` or `email` then this channel won't be used.