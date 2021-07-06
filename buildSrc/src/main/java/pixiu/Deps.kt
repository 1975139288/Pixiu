@file:Suppress("unused")

package pixiu

// 统一管理项目中使用的依赖库
object Deps {
    object Config {
        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.3"
        const val applicationId = "com.poker.pixiu"
        const val minSdkVersion = 23
        const val targetSdkVersion = 30
        const val versionCode = 1
        const val versionName = "0.0.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.0-alpha02"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.0.9"

    const val material = "com.google.android.material:material:1.3.0"

    object Kotlin {
        private const val version = "1.4.32"

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"

        object Coroutines {
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object Jetpack {
        object Hilt {
            private const val version = "2.36"

            const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
            const val hiltAndroid = "com.google.dagger:hilt-android:$version"
            const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
            const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
        }

        object Viewmodel {
            private const val version = "2.3.1"

            const val ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val hilt = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
        }

        object Livedata {
            private const val version = "2.3.1"

            const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val core = "androidx.lifecycle:lifecycle-livedata-core-ktx:$version"
        }

        object Room {
            private const val version = "2.3.0"

            const val ktx = "androidx.room:room-ktx:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.5.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    }

    object Okhttp {
        private const val version = "4.9.1"

        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converter_moshi = "com.squareup.retrofit2:converter-moshi:$version"
//        const val converter_gson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Json {
        const val moshi = "com.squareup.moshi:moshi:1.12.0"
        const val moshi_codegen = "com.squareup.moshi:moshi-kotlin-codegen:1.12.0"
    }

    object Image {
        const val coil = "io.coil-kt:coil:1.2.1"
    }

    object Util {
        const val autosize = "com.github.JessYanCoding:AndroidAutoSize:v1.2.1"
    }
}
