package com.group.libraryapp.domain.user.loanhositry

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

    /*
    fun findByBookName(bookName: String): UserLoanHistory?

    fun findByBookNameAndStatus(bookName: String, status: UserLoanStatus): UserLoanHistory?

    fun countByStatus(status: UserLoanStatus): Long


     */
}