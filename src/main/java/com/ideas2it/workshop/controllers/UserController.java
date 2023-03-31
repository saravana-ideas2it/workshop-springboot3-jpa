
package com.ideas2it.workshop.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.ideas2it.workshop.commons.log.LogExecutionTime;
import com.ideas2it.workshop.constants.ApiSpec;
import com.ideas2it.workshop.dtos.GetRequestParamsDto;
import com.ideas2it.workshop.entities.SearchParameters;
import com.ideas2it.workshop.entities.User;
import com.ideas2it.workshop.dtos.UserDto;
import com.ideas2it.workshop.dtos.UserGetDto;
import com.ideas2it.workshop.mappers.UserMapperMapStruct;
import com.ideas2it.workshop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.ideas2it.workshop.constants.ApiSpec.*;
import static com.ideas2it.workshop.constants.ExceptionMessage.*;
import static com.ideas2it.workshop.constants.URLPath.API_PATH;
import static com.ideas2it.workshop.constants.URLPath.API_PATH_BY_ID;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	private UserService userService;
	private UserMapperMapStruct userMapper;
	@Value("${ideas2it.pagination.page-size.default:100}")
	public int defaultPageSize;
	@Value("${ideas2it.pagination.page-number.default:1}")
	public int defaultPageNumber;
	@Value("${ideas2it.sorting.default:id}")
	public String defaultPageSort;

	@Autowired
	public UserController(
			UserMapperMapStruct userMapper,
			UserService userService
	) {
		this.userMapper = userMapper;
		this.userService = userService;
	}

	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = DATA_FOUND,
					content =
							@Content(
								mediaType = MEDIA_TYPE,
								array = @ArraySchema(schema = @Schema(implementation = UserGetDto.class))
							)
			),
			@ApiResponse(responseCode ="400", description = BAD_REQUEST, content ={ @Content() }),
			@ApiResponse(responseCode ="404", description =NO_DATA_FOUND, content ={ @Content() })
		}
	)
	@RequestMapping(value = API_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@LogExecutionTime
	public ResponseEntity<List<UserGetDto>> findAll(GetRequestParamsDto getRequestParams) {
		SearchParameters params = new SearchParameters(
			getRequestParams.limit > 0 ?  getRequestParams.limit : defaultPageSize,
			getRequestParams.page >= 0 ? getRequestParams.page : defaultPageNumber,
			getRequestParams.sort != null ? getRequestParams.sort : defaultPageSort
		);

		List<UserGetDto> userList = userService.findAll(params).stream().map(user -> userMapper.userToUserGetDto(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(userList);
	}

	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = DATA_FOUND,
					content =
					@Content(
							mediaType = MEDIA_TYPE,
							schema = @Schema(implementation = UserGetDto.class)
					)
			),
			@ApiResponse(responseCode ="400", description = BAD_REQUEST, content ={ @Content() }),
			@ApiResponse(responseCode ="404", description =NO_DATA_FOUND, content ={ @Content() })
	}
	)
	@RequestMapping(
			value = API_PATH_BY_ID,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@LogExecutionTime
	public ResponseEntity<UserGetDto> findById(@PathVariable Long id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(userMapper.userToUserGetDto(obj));
	}

	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = CREATED,
					content =
					@Content(
							mediaType = MEDIA_TYPE,
							schema = @Schema(implementation = UserGetDto.class)
					)
			),
			@ApiResponse(responseCode ="400", description = BAD_REQUEST, content ={ @Content() }),
			@ApiResponse(responseCode ="404", description =NO_DATA_FOUND, content ={ @Content() })
	}
	)
	@RequestMapping(
			value = API_PATH,
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<UserGetDto> create(@Valid @RequestBody UserDto userDto) {
		User user = userService.insert(
				userMapper.userDtoToUser(
						userDto
				)
		);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(userMapper.userToUserGetDto(user));
	}

	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = DATA_FOUND,
					content = @Content()
			),
			@ApiResponse(responseCode ="400", description = BAD_REQUEST, content = { @Content() }),
			@ApiResponse(responseCode ="404", description =NO_DATA_FOUND, content = { @Content() })
	}
	)
	@RequestMapping(
			value = API_PATH_BY_ID,
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@LogExecutionTime
	public ResponseEntity<Void> delete(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
		User user = userService.update(id, userMapper.userDtoToUser(userDto));
		return ResponseEntity.ok().body(user);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<User> patch(@PathVariable Long id, @RequestBody UserDto userDto){
		User user = userService.patch(id, userMapper.userDtoToUser(userDto));
		return ResponseEntity.ok().body(user);
	}
}