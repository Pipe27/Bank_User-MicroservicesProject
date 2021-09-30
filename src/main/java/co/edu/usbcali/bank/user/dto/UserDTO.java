package co.edu.usbcali.bank.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@NotNull
	private String userEmail;

	@NotNull
	@Size(min = 1, max = 1)
	private String enable;

	@NotNull
	@Size(min = 4, max = 100)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String token;
	
	@NotNull
	private Integer ustyId;
	
}
