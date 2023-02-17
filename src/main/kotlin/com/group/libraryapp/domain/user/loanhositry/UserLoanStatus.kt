package com.group.libraryapp.domain.user.loanhositry

import com.group.libraryapp.domain.user.User
import javax.persistence.Entity
import javax.persistence.ManyToOne

enum class UserLoanStatus {
    RETURNED,
    LOANED,
}