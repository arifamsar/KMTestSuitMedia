package com.arfsar.kmtestsuitmedia.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class SingleUserResponse(

	@field:SerializedName("data")
	val data: Data,
)

data class Data(

	@field:SerializedName("last_name")
	val lastName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("avatar")
	val avatar: String,

	@field:SerializedName("first_name")
	val firstName: String,

	@field:SerializedName("email")
	val email: String
)

