plugins {
    id("java")
}

group = "org.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.springframework.boot:spring-boot-starter:3.1.5")
    implementation("org.springframework.boot:spring-boot-starter-test:3.1.5")

    implementation("org.springframework.boot:spring-boot-starter-web:3.1.5")

    implementation("org.springframework.boot:spring-boot-starter-security:3.1.5")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.1.5")

    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.4.1")
    implementation("com.mysql:mysql-connector-j:8.2.0")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.13")
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.6")
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-core
    implementation("ch.qos.logback:logback-core:1.5.6")
}

tasks.test {
    useJUnitPlatform()
}