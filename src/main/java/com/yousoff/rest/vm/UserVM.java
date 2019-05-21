package com.yousoff.rest.vm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "email", "first_name", "last_name", "avatar" })
@ToString
public class UserVM {
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("email")
	private String email;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("avatar")
	private String avatar;
}
