plugins {
    java
    id ("com.google.cloud.tools.jib") version "3.3.2"
    id("org.springframework.boot") version "2.7.11-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

jib {
    from {
        image = "openjdk:19"
    }
    to {
        image = "hyunsu1768/mukgen"
        tags = setOf("latest")
    }
    container {
        jvmFlags = listOf("-Dspring.profiles.active=local", "-XX:+UseContainerSupport", "-Dserver.port=80", "-Dfile.encoding=UTF-8")
        ports = listOf("80")
    }
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io")}
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.3")
    }
}

dependencies {

    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    runtimeOnly("mysql:mysql-connector-java")

    // Security JWT
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.json:json:20230227")

    //S3
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.281")

    // Actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // Feign Client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // Mail
    implementation("org.springframework.boot:spring-boot-starter-mail")

}
