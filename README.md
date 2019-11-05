# ClockWithAsync

시계를 Thread와 RxJava로 만들어보자 - 동아리 숙제



## Branch - use_thread

```kotlin
timeThread = Thread(Runnable {
            while (true) {
                val cal = Calendar.getInstance()
                val hour = cal.get(Calendar.HOUR)
                val minute = cal.get(Calendar.MINUTE)
                val second = cal.get(Calendar.SECOND)

                Handler(Looper.getMainLooper()).post {
                    // Text Change Logic
                }

                try {
                    Thread.sleep(1000)
                } catch (e : Exception) { }
            }
        })

        timeThread.start()
```

- Thread 안에 while 안에 1초마다 시, 분, 초 데이터를 불러오고, Thread.sleep(1000)를 이용해서 1초마다 Handler 안에 UI를 갱신해준다.



## Branch - use_rx

```kotlin
Observable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val cal = Calendar.getInstance()
                val hour = cal.get(Calendar.HOUR)
                val minute = cal.get(Calendar.MINUTE)
                val second = cal.get(Calendar.SECOND)
                
                // Text Change Logic
            }

```

- Rx를 이용해 개발, `interval`을 이용해 1초마다 반복시키고 변경 로직을 작성함.
