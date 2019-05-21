package com.yousoff.rest.vm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter @ToString
public class SingleUserVM {

	@JsonProperty("data")
	private UserVM data;
}
