package com.chrynan.video.buildSrc

object Versions {

    const val KOTLIN = "1.3.72"
    const val KOTLIN_COROUTINES = "1.3.9"
    const val KOTLIN_COROUTINES_COMMON = "1.3.7"
    const val KOTLIN_SERIALIZATION = "0.20.0"

    const val ANDROID_LIFECYCLE = "2.2.0"
    const val ANDROID_COMPONENTS = "1.3.1"
    const val ANDROID_CONSTRAINT_LAYOUT = "2.0.1"
    const val ANDROID_MATERIAL = "1.3.0-alpha02"
    const val ANDROID_RECYCLER_VIEW = "1.1.0"
    const val ANDROID_CARD_VIEW = "1.0.0"
    const val ANDROID_SWIPE_REFRESH = "1.1.0"
    const val ANDROID_ROOM = "2.2.5"
    const val ANDROID_SQLIGHT = "2.1.0"

    const val DB_ENCRYPTION = "4.4.0"

    const val CHRYNAN_LOGGING = "1.2.1"
    const val CHRYNAN_AAAAH = "0.7.0"
    const val CHRYNAN_INJECT = "0.6.0"
    const val CHRYNAN_VALIDATOR = "0.1.0"

    const val UI_CIRCLE_VIEW = "3.0.1"

    const val COIL = "0.11.0"

    const val EXOPLAYER = "2.11.5"

    const val OKHTTP = "4.7.2"

    const val APOLLO = "2.3.0"

    const val DAGGER = "2.27"
    const val DAGGER_ANDROID = "2.19"
    const val DAGGER_COMPILER = "2.19"

    const val NOTIES = "4.4.0"

    const val FLOWBINDING = "1.0.0-alpha04"

    const val KTOR = "1.3.2"

    const val JAVAX_INJECT = "1"
}

object Deps {

    const val KOTLIN_COMMON = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.KOTLIN}"
    const val KOTLIN_JVM = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val KOTLIN_JS = "org.jetbrains.kotlin:kotlin-stdlib-js:${Versions.KOTLIN}"
    const val KOTLIN_COROUTINES_COMMON =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.KOTLIN_COROUTINES_COMMON}"
    const val KOTLIN_COROUTINES_JVM =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINES}"
    const val KOTLIN_COROUTINES_JS =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.KOTLIN_COROUTINES}"
    const val KOTLIN_COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLIN_COROUTINES}"
    const val KOTLIN_SERIALIZATION_COMMON =
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.KOTLIN_SERIALIZATION}"
    const val KOTLIN_SERIALIZATION_JVM =
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.KOTLIN_SERIALIZATION}"

    const val ANDROID_LIFECYCLE =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROID_LIFECYCLE}"
    const val ANDROID_COMPONENTS = "androidx.core:core:${Versions.ANDROID_COMPONENTS}"

    // When upgrading, verify that the overlay's work correctly. The last checked newer version doesn't work.
    const val ANDROID_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.ANDROID_CONSTRAINT_LAYOUT}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val ANDROID_RECYCLER_VIEW =
        "androidx.recyclerview:recyclerview:${Versions.ANDROID_RECYCLER_VIEW}"
    const val ANDROID_CARD_VIEW = "androidx.cardview:cardview:${Versions.ANDROID_CARD_VIEW}"
    const val ANDROID_SWIPE_REFRESH =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.ANDROID_SWIPE_REFRESH}"

    const val ANDROID_ROOM = "androidx.room:room-runtime:${Versions.ANDROID_ROOM}"
    const val ANDROID_ROOM_KTX = "androidx.room:room-ktx:${Versions.ANDROID_ROOM}"
    const val ANDROID_ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ANDROID_ROOM}"

    const val ANDROID_SQLIGHT = "androidx.sqlite:sqlite:${Versions.ANDROID_SQLIGHT}"

    const val DB_ENCRYPTION = "net.zetetic:android-database-sqlcipher:${Versions.DB_ENCRYPTION}"

    const val CHRYNAN_LOGGING_CORE = "com.chrynan.logger:logger-core:${Versions.CHRYNAN_LOGGING}"
    const val CHRYNAN_LOGGING_CORE_JVM =
        "com.chrynan.logger:logger-core-jvm:${Versions.CHRYNAN_LOGGING}"
    const val CHRYNAN_LOGGING_ANDROID =
        "com.chrynan.logger:logger-android:${Versions.CHRYNAN_LOGGING}"
    const val CHRYNAN_LOGGING_ANDROID_TIMBER =
        "com.chrynan.logger:logger-android-timber:${Versions.CHRYNAN_LOGGING}"
    const val CHRYNAN_AAAAH_CORE = "com.chrynan.aaaah:aaaah-core:${Versions.CHRYNAN_AAAAH}"
    const val CHRYNAN_AAAAH_CORE_JVM = "com.chrynan.aaaah:aaaah-core-jvm:${Versions.CHRYNAN_AAAAH}"
    const val CHRYNAN_AAAAH_ANDROID = "com.chrynan.aaaah:aaaah-libraryx:${Versions.CHRYNAN_AAAAH}"
    const val CHRYNAN_AAAAH_ANNOTATION =
        "com.chrynan.aaaah:aaaah-annotation:${Versions.CHRYNAN_AAAAH}"
    const val CHRYNAN_AAAAH_COMPILER = "com.chrynan.aaaah:aaaah-compiler:${Versions.CHRYNAN_AAAAH}"
    const val CHRYNAN_INJECT_COMMON = "com.chrynan.inject:inject-common:${Versions.CHRYNAN_INJECT}"
    const val CHRYNAN_INJECT_JVM = "com.chrynan.inject:inject-jvm:${Versions.CHRYNAN_INJECT}"
    const val CHRYNAN_INJECT_JS = "com.chrynan.inject:inject-js:${Versions.CHRYNAN_INJECT}"
    const val CHRYNAN_VALIDATOR_CORE_COMMON =
        "com.chrynan.validator:validator-core:${Versions.CHRYNAN_VALIDATOR}"
    const val CHRYNAN_VALIDATOR_CORE_JVM =
        "com.chrynan.validator:validator-core-jvm:${Versions.CHRYNAN_VALIDATOR}"
    const val CHRYNAN_VALIDATOR_WEB_COMMON =
        "com.chrynan.validator:validator-web:${Versions.CHRYNAN_VALIDATOR}"
    const val CHRYNAN_VALIDATOR_WEB_JVM =
        "com.chrynan.validator:validator-web-jvm:${Versions.CHRYNAN_VALIDATOR}"

    const val UI_CIRCLE_VIEW = "de.hdodenhof:circleimageview:${Versions.UI_CIRCLE_VIEW}"

    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
    const val COIL_GIF = "io.coil-kt:coil-gif:${Versions.COIL}"
    const val COIL_SVG = "io.coil-kt:coil-svg:${Versions.COIL}"
    const val COIL_VIDEO = "io.coil-kt:coil-video:${Versions.COIL}"

    const val EXOPLAYER = "com.google.android.exoplayer:exoplayer:${Versions.EXOPLAYER}"

    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"

    const val APOLLO_API_COMMON = "com.apollographql.apollo:apollo-api:${Versions.APOLLO}"
    const val APOLLO_RUNTIME_COMMON =
        "com.apollographql.apollo:apollo-runtime-kotlin:${Versions.APOLLO}"
    const val APOLLO_CACHE_SQLITE_COMMON =
        "com.apollographql.apollo:apollo-normalized-cache-sqlite:${Versions.APOLLO}"
    const val APOLLO_API_JVM = "com.apollographql.apollo:apollo-api-jvm:${Versions.APOLLO}"
    const val APOLLO_CACHE_HTTP = "com.apollographql.apollo:apollo-http-cache:${Versions.APOLLO}"

    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER_COMPILER}"
    const val DAGGER_ANDROID_COMPILER =
        "com.google.dagger:dagger-android-processor:${Versions.DAGGER_ANDROID}"
    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android-support:${Versions.DAGGER_ANDROID}"

    const val NOTIES_CORE = "io.noties.markwon:core:${Versions.NOTIES}"
    const val NOTIES_STRIKETHROUGH = "io.noties.markwon:ext-strikethrough:${Versions.NOTIES}"
    const val NOTIES_TABLE = "io.noties.markwon:ext-tables:${Versions.NOTIES}"
    const val NOTIES_TASK_LIST = "io.noties.markwon:ext-tasklist:${Versions.NOTIES}"
    const val NOTIES_HTML = "io.noties.markwon:html:${Versions.NOTIES}"
    const val NOTIES_IMAGE_COIL = "io.noties.markwon:image-coil:${Versions.NOTIES}"
    const val NOTIES_LINKIFY = "io.noties.markwon:linkify:${Versions.NOTIES}"
    const val NOTIES_SYNTAX_HIGHLIGHT = "io.noties.markwon:syntax-highlight:${Versions.NOTIES}"

    const val FLOWBINDING_CORE =
        "io.github.reactivecircus.flowbinding:flowbinding-core:${Versions.FLOWBINDING}"
    const val FLOWBINDING_ANDROID =
        "io.github.reactivecircus.flowbinding:flowbinding-android:${Versions.FLOWBINDING}"
    const val FLOWBINDING_ACTIVITY =
        "io.github.reactivecircus.flowbinding:flowbinding-activity:${Versions.FLOWBINDING}"
    const val FLOWBINDING_APPCOMPAT =
        "io.github.reactivecircus.flowbinding:flowbinding-appcompat:${Versions.FLOWBINDING}"
    const val FLOWBINDING_RECYCLERVIEW =
        "io.github.reactivecircus.flowbinding:flowbinding-recyclerview:${Versions.FLOWBINDING}"
    const val FLOWBINDING_SWIPEREFRESH =
        "io.github.reactivecircus.flowbinding:flowbinding-swiperefreshlayout:${Versions.FLOWBINDING}"

    const val KTOR_CORE_COMMON = "io.ktor:ktor-client-core:${Versions.KTOR}"
    const val KTOR_SERIALIZATION = "io.ktor:ktor-client-serialization:${Versions.KTOR}"
    const val KTOR_SERIALIZATION_JVM = "io.ktor:ktor-client-serialization-jvm:${Versions.KTOR}"
    const val KTOR_LOGGING = "io.ktor:ktor-client-logging:${Versions.KTOR}"
    const val KTOR_LOGGING_JVM = "io.ktor:ktor-client-logging-jvm:${Versions.KTOR}"
    const val KTOR_OKHTTP = "io.ktor:ktor-client-okhttp:${Versions.KTOR}"

    const val JAVAX_INJECT = "javax.inject:javax.inject:${Versions.JAVAX_INJECT}"
}