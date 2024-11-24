# 2FA with Mailgun (Java/Android)

## Overview

This project is a **Two-Factor Authentication (2FA)** system implemented in **Java** for an Android application, leveraging **Mailgun** for email delivery. It enhances app security by sending a one-time verification code to the user's email during authentication.

## Features

- **Email-Based 2FA**: Adds an additional security layer by sending a verification code to the user's email address.
- **Mailgun Integration**: Utilizes Mailgun's API for efficient and reliable email delivery.
- **Android Compatibility**: Designed for seamless integration with Android applications built using **Android Studio**.

## Prerequisites

1. **Mailgun Account**: Register and verify a Mailgun account at [Mailgun](https://www.mailgun.com/).
2. **Verified Domain**: Set up and verify a domain in Mailgun for email delivery. (It is free for 3k emails)
3. **API Key**: Obtain your Mailgun private API key and the domain name for sending emails.
4. **Add email**: Add an email you want to sent emails for free. You will get an email to accept from Mailgun so they have permission to send you messages.
5. **Android Studio**: Installed and configured on your development machine.

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Shendrit-Hy/2FA-Mailgun-Android.git
cd 2FA-Mailgun-Android
```

### 2. Configure Mailgun

- Add your Mailgun **API key** and **domain** in the MailgunUtils.java:

```java

private static final String MAILGUN_API = "YOUR MAILGUN API";

private static final String MAILGUN_DOMAIN = "YOUR MAILGUN DOMAIN";

```

- Add your email you added on Mailgun to the **emailTest** and give a test password like 123 on **testPassword** in the LoginActivity.java:
```
private final String testEmail = "test@gmail.com";
private final String testPassword = "test123"; // this is the password you need when you run the app and test the 2FA
```


### 3. Add Dependencies

Include the necessary dependencies in your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.squareup.okhttp3:okhttp:4.9.3'
}
```
or
```gradle
dependencies {
        implementation("com.squareup.okhttp3:okhttp:4.9.3")
}
```


### 4. Generate and Verify OTP

- **Generate OTP**: Use a secure random number generator to create the OTP.
- **Send OTP**: Call the Mailgun API to send the OTP to the user's email.
- **Verify OTP**: Validate the user input against the generated OTP.

### 5. Test the Application

Run the app on an Android device or emulator to verify the 2FA process.

## Example Flow

1. **User Login**: The user logs in with their credentials (with the email you added on Mailgun Web). 
2. **Send OTP**: The app generates an OTP and sends it to the user's email using Mailgun.
3. **Enter OTP**: The user enters the OTP received in their email.
4. **Verify OTP**: The app validates the OTP and grants access if it matches.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

---

For further assistance, feel free to raise an issue on the GitHub repository or contact us!
