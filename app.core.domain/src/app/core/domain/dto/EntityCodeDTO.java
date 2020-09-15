package app.core.domain.dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
public class EntityCodeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	
	private String name;
	
	private Long sortOrder;	

}
