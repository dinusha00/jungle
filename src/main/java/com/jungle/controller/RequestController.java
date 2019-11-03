package com.jungle.controller;

import com.jungle.domain.entity.Request;
import com.jungle.domain.vo.Status;
import com.jungle.exception.JungleException;
import com.jungle.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RequestService requestService;

	@GetMapping
	public List<Request> readRequests() {
		try {
			logger.info("calling RequestController.readRequests");
			final List<Request> requests = requestService.readRequests();
			logger.info("returning from RequestController.readRequests requests.size():{}", requests.size());
			return requests;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}

	@GetMapping(value = "/{id}")
	public Request readRequest(@PathVariable final Long id) {
		try {
			logger.info("calling RequestController.readRequest id:" + id);
			final Request request = requestService.readRequest(id);
			logger.info("returning from RequestController.readRequest request:{}", request);
			return request;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Request createRequest(@RequestBody final Request request) {
		try {
			logger.info("calling RequestController.createRequest request:{}", request);
			final Request createdRequest = requestService.createRequest(request);
			logger.info("returning from RequestController.createRequest createdRequest:{}", createdRequest);
			return createdRequest;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}

	@PutMapping
	public Request updateRequest(@RequestBody final Request request) {
		try {
			logger.info("calling RequestController.updateRequest request:{}", request);
			final Request updatedRequest = requestService.updateRequest(request);
			logger.info("returning from RequestController.updateRequest updatedRequest:{}", request);
			return updatedRequest;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}

	@DeleteMapping(value = "/{id}")
	public void deleteRequest(@PathVariable final Long id) {
		try {
			logger.info("calling RequestController.deleteRequest id:{}", id);
			requestService.deleteRequest(id);
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}

	@GetMapping(value = "/{id}/validate")
	public Request validateRequest(@PathVariable final Long id) {
		try {
			logger.info("calling RequestController.validateRequest id:" + id);
			final Request validatedRequest = requestService.validateRequest(id);
			logger.info("returning from RequestController.validateRequest validatedRequest:{}", validatedRequest);
			return validatedRequest;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}

	@GetMapping(value = "/{id}/reset")
	public Request resetRequest(@PathVariable final Long id) {
		try {
			logger.info("calling RequestController.resetRequest id:" + id);
			final Request request = requestService.readRequest(id);
			final Request result = requestService.resetRequest(new Request(request.getId(), request.getSourceIp(), request.getDestinationIp(), Status.NEW.toString()));
			logger.info("returning from RequestController.resetRequest result:{}", result);
			return result;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}
}