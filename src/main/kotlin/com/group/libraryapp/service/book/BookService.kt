package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhositry.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhositry.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.repository.book.BookQuerydslRepository
import com.group.libraryapp.repository.user.loanhistory.UserLoanHistoryQueryRepository
import com.group.libraryapp.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService (
        private val bookRepository: BookRepository,
        private val bookQuerydslRepository: BookQuerydslRepository,
        private val userRepository: UserRepository,
        private val userLoanHistoryQueryRepository: UserLoanHistoryQueryRepository,
        //private val userLoanHistoryRepository: UserLoanHistoryRepository,
){

    @Transactional
    fun saveBook(request: BookRequest) {
        val book = Book(request.name, request.type)
        bookRepository.save(book)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest) {
        val book = bookRepository.findByName(request.bookName) ?: fail()
        if (userLoanHistoryQueryRepository.find(request.bookName, UserLoanStatus.LOANED) != null) {
            throw IllegalArgumentException("진작 대출되어 있는 책입니다")
        }

        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByName(request.userName) ?: fail()
        user.returnBook(request.bookName)
    }

    @Transactional(readOnly = true)
    fun countLoanedBook(): Int {
        return userLoanHistoryQueryRepository.count(UserLoanStatus.LOANED).toInt()
    }

    @Transactional(readOnly = true)
    fun getBookStatistics(): List<BookStatResponse> {

        // QueryDsl 사용
        return bookQuerydslRepository.getStats()

        /* jpql 사용
        return bookRepository.getStats()
         */

        /* 아래 코드는 정상 동작, 그러나 비효율
        return bookRepository.findAll() // List<Book>
                .groupBy { book -> book.type }  // Map<BookType, List<Book>>
                .map{(type, books) -> BookStatResponse(type, books.size)}   // List<BookStatResponse>

         */
    }

}