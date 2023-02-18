package com.group.libraryapp.repository.user.loanhistory

import com.group.libraryapp.domain.user.loanhositry.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhositry.UserLoanHistory
import com.group.libraryapp.domain.user.loanhositry.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.hibernate.annotations.Comment
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQueryRepository (
        private val queryFactory: JPAQueryFactory,
){

    fun find(bookName: String, status: UserLoanStatus? = null): UserLoanHistory? {
        return queryFactory.select(userLoanHistory)
                .from(userLoanHistory)
                .where(
                        userLoanHistory.bookName.eq(bookName),
                        status?.let { userLoanHistory.status.eq(status) }
                )
                .limit(1)
                .fetchOne()
    }

    fun count(status: UserLoanStatus): Long {
        return queryFactory.select(userLoanHistory.count())
                .from(userLoanHistory)
                .where(
                        userLoanHistory.status.eq(status)
                )
                .fetchOne() ?: 0L
    }
}