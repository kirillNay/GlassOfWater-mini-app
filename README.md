# GlassOfWater
A simple multiplatform app to track the number of glasses of water you drink.

## Telegram Mini App
This app is fully written in Kotlin Multiplatform and UI with Compose. For telegram app used js target and **tg-mini-app** implementation.
Guideline of implementation you can fide [here](https://github.com/kirillNay/tg-mini-app).

### Features
- Implementation of Telegram's cloud storage
- Support dynamic theme change based on Telegram App Colors
- Support both Android and mini app targets

### Android application

<img src="https://github.com/kirillNay/GlassOfWater-mini-app/assets/56832972/f8688ee9-eb2e-4b1e-8a97-6096e6f95962" width="162" heigth="318">&emsp;
<img src="https://github.com/kirillNay/GlassOfWater-mini-app/assets/56832972/9769daea-d055-444c-82a3-e53edcbb37e1" width="162" heigth="318">&emsp;
<img src="https://github.com/kirillNay/GlassOfWater-mini-app/assets/56832972/e96da025-7c53-4d8f-964f-df6ac990325b" width="162" heigth="318">

#### How to run
To build and run android application you should first connect device or emulator via adb and then call install task of gradle:
```
./gradlew app:installDebug
```

### Mini app

<img src="https://github.com/kirillNay/GlassOfWater-mini-app/assets/56832972/660e9179-f4e1-453c-96b5-174725cfdc4b" width="296" height="584"/>

#### How to run
To build js application you should call run task of gradle:
```
./gradlew app:jsRun
```

After that we need to register bot in Telegram via [@BotFather](https://t.me/BotFather). Check out [this tutorial](https://core.telegram.org/bots/webapps#implementing-mini-apps) for implementing web app bot.

Then we need to deploy our js application to web to get URL of it. To do so I use [ngrok](https://ngrok.com/) in development purposes. Ngrok allows you to forward any port on your computer so that other users can access it over the Internet. Follow [these simple steps](https://ngrok.com/docs/getting-started/) to get started with **ngrok**.

After you authorize with **ngrok** you will be able to deploy your js application deployed on your local device with this command:
```
ngrok http 8080 --host-header="localhost:8080"
```
As a result you will receive URL of your application available in the Internet.

![image](https://github.com/kirillNay/GlassOfWater-mini-app/assets/56832972/a887a4fc-05d3-4f04-973d-c61d3da0917a)

Provide this URL to [@BotFather](https://t.me/BotFather) and you will be able to launch mini app with direct link.
