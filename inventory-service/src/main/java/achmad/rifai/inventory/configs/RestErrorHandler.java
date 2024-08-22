package achmad.rifai.inventory.configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import achmad.rifai.inventory.exceptions.NoDataException;
import achmad.rifai.inventory.pojo.ErrorRes;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestErrorHandler {

	@ExceptionHandler(NoDataException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorRes noData(NoDataException e) {
		log.error(e.getMessage());
		return ErrorRes.builder()
				.message(e.getMessage())
				.build();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorRes illegalPathOrQuery(IllegalArgumentException e) {
		log.error(e.getLocalizedMessage());
		return ErrorRes.builder()
				.message(e.getMessage())
				.build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorRes methodArgumentNotValid(MethodArgumentNotValidException e) {
		log.error(e.getLocalizedMessage());
		return ErrorRes.builder()
				.message(e.getMessage())
				.messages(e.getFieldErrors()
						.stream()
						.map(error -> Map.of(error.getField(), error.getDefaultMessage()))
						.reduce(new HashMap<>(), (acc, map) -> {
							acc.putAll(map);
							return acc;
						}))
				.build();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorRes error(Exception e) {
		log.error(e.getLocalizedMessage(), e);
		return ErrorRes.builder()
				.message(e.getMessage())
				.build();
	}

}
