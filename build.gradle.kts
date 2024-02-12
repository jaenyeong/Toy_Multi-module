import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // 코틀린 코드를 Java 바이트코드로 컴파일할 때 사용
    kotlin("jvm") version "1.9.22" apply false
    // 코틀린 스프링 지원 (@Autowired 없는 생성자 주입, null-safety 등)
    kotlin("plugin.spring") version "1.9.22" apply false
    kotlin("plugin.jpa") version "1.9.22" apply false

    id("org.springframework.boot") version "3.2.2" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

allprojects {
    extra["kotestVersion"] = "5.8.0"

    repositories {
        mavenCentral()
    }
}

val javaVersion = JavaVersion.VERSION_17.toString()
subprojects {
    // Java 코드가 섞여 있거나 사용하는 Java 라이브러리가 특정 JVM 버전을 요구할 수 있기 때문에 설정
    // 순수 코틀린 프로젝트에서는 무관한 설정
    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs += "-Xjsr305=strict"
        }
    }

    // 각 `Test` 태스크가 실제로 필요한 시점에 설정이 적용됨
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
