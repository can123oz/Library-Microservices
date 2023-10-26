package com.library.libraryservice.dto

data class BookDto @JvmOverloads constructor(val id: BookIdDto? = null,
                                             val title: String = "",
                                             val bookYear: Int? = 0,
                                             var author: String = "",
                                             val pressName: String = "",
                                             var isbn: String = "") {

}
