FROM java:8
MAINTAINER Vincent Demeester <vincent.demeester@zenika.com>

COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/miniws-1.0.0.jar"]
