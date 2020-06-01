package com.victorrendina.data

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class Cache

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class Local

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class Remote
