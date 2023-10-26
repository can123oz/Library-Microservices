package com.library.libraryservice.dto

import org.apache.tomcat.jni.Library

data class LibraryDto @JvmOverloads constructor (
        val id: String,
        val userBookList: List<BookDto>? = ArrayList()
) {

}

