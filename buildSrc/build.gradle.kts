plugins {
    `kotlin-dsl`
}

apply<KotlinDslPlugin>()

repositories {
    jcenter()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
}