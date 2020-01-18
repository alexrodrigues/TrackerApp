package br.com.rodriguesalex.tracker.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RxMainThread

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RxIoThread