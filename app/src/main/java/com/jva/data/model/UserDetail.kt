package com.jva.data.model

data class UserDetail(
    val data: Data,
    val login_type: String,
    val message: String,
    val status: Int
)

data class Data(
    val added_date: String,
    val company_name: Any,
    val device_token: Any,
    val device_type: Any,
    val dob: String,
    val email: String,
    val email_2: Any,
    val email_access_token: Any,
    val email_verified: String,
    val gender: Any,
    val insurance_type: String,
    val lead_no: String,
    val martial_status: Any,
    val mobile: String,
    val mobile_verified: String,
    val modified_date: String,
    val mpin: String,
    val mpin_change_token: Any,
    val name: String,
    val salt: String,
    val user_id: String,
    val user_status: String
)