package com.book.bookservice.dto

import com.book.bookservice.model.Book

data class BookDto @JvmOverloads constructor(
        val id: BookIdDto? = null,
        val title: String,
        val bookYear: Int,
        var author: String,
        val pressName: String,
        var isbn: String
) {
    companion object {
        @JvmStatic
        fun convert(from: Book) : BookDto {
            return BookDto(
                    from.id?.let { BookIdDto.convert(it,from.isbn)},
                    from.title,
                    from.bookYear,
                    from.author,
                    from.pressName,
                    from.isbn
            )
        }
    }
}