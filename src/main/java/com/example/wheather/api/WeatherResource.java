package com.digipay.merchantcredit.api.web.settlement;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digipay.component.common.Constants;
import com.digipay.component.common.exception.BusinessException;
import com.digipay.component.common.response.ResponseService;
import com.digipay.component.common.util.validation.annotation.InRange;
import com.digipay.component.dynamicsearch.FieldInfoContext;
import com.digipay.component.dynamicsearch.SearchService;
import com.digipay.component.dynamicsearch.SearchableResource;
import com.digipay.component.dynamicsearch.request.SearchRequest;
import com.digipay.component.dynamicsearch.request.dto.restrictions.AndRestrictionDto;
import com.digipay.component.dynamicsearch.request.dto.restrictions.SimpleValueRestrictionDto;
import com.digipay.component.dynamicsearch.request.dto.restrictions.SimpleValueRestrictionDto.OperationType;
import com.digipay.merchantcredit.api.web.filter.RequestContext;
import com.digipay.merchantcredit.api.web.filter.RequestContextInterceptor;
import com.digipay.merchantcredit.api.web.settlement.mapper.SettlementResourceMapper;
import com.digipay.merchantcredit.api.web.settlement.mapper.SettlementResourceMapper.SettlementFieldInfoContext;
import com.digipay.merchantcredit.model.settlement.Settlement;
import com.digipay.merchantcredit.model.settlement.Settlement.Status;
import com.digipay.merchantcredit.service.settlement.SettlementService;
import com.digipay.merchantcredit.service.settlement.model.SettlementLandingConfigResult;
import com.digipay.merchantcredit.service.settlement.model.SettlementTicketResult;
import com.digipay.merchantcredit.service.settlement.model.response.SettlementDetailModel;
import com.digipay.merchantcredit.service.settlement.model.response.SettlementInitiationResult;
import com.digipay.merchantcredit.service.settlement.model.response.SettlementInquiryResult;
import com.digipay.merchantcredit.service.settlement.model.response.SettlementMinFeeResult;
import com.digipay.merchantcredit.spec.request.settlement.SettlementAmountReviseRequest;
import com.digipay.merchantcredit.spec.request.settlement.SettlementInitiateRequest;
import com.digipay.merchantcredit.spec.request.settlement.SettlementInquiryRequest;
import com.digipay.merchantcredit.spec.request.settlement.SettlementPreviewRequest;
import com.digipay.merchantcredit.spec.response.settlement.SettlementDetailResponse;
import com.digipay.merchantcredit.spec.response.settlement.SettlementFeeResponse;
import com.digipay.merchantcredit.spec.response.settlement.SettlementInquiryResponse;
import com.digipay.merchantcredit.spec.response.settlement.SettlementLandingConfigResponse;
import com.digipay.merchantcredit.spec.response.settlement.SettlementListResponse;
import com.digipay.merchantcredit.spec.response.settlement.SettlementResponse;
import com.digipay.merchantcredit.spec.response.settlement.SettlementTicketResponse;

@Slf4j
@RestController
@RequestMapping("/credit/merchants/settlements")
@RequiredArgsConstructor
public class SettlementResource implements SearchableResource<Settlement> {

	private final SettlementService service;

	private final SearchService searchService;

	private final SettlementResourceMapper mapper;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementResponse> initiate(@Valid @RequestBody SettlementInitiateRequest request,
			@RequestHeader(Constants.REQUEST_HEADER_USER_ID) String businessId) throws BusinessException {
		logger.info("got early settlement request with: {}", request);
		SettlementInitiationResult result = service.initiate(mapper.toSettlementInitiateModel(request, businessId));
		logger.info("early settlement registered successfully with: {}", result);
		return ResponseEntity.ok(mapper.toSettlementResponse(result));
	}

	@PostMapping(path = "/{trackingCode}/preview", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementFeeResponse> preview(@Valid @RequestBody SettlementPreviewRequest request,
			@PathVariable String trackingCode) throws BusinessException {
		logger.info("got settlement preview result with request -> {}", request);
		SettlementMinFeeResult result = service.calculateFee(mapper.toSettlementPreviewModel(request, trackingCode));
		logger.debug("settlement preview result is: {}", result);
		return ResponseEntity.ok(mapper.toSettlementFeeResponse(result));
	}

	@PostMapping(path = "/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementListResponse> inquiry(@Valid @RequestBody SettlementInquiryRequest request,
			@RequestParam(name = "page", defaultValue = "0") final Integer page,
			@InRange(min = "settlement.inquiry.min-search-size", max = "settlement.inquiry.max-search-size",
					message = "{not-in-range-message}") @RequestParam(name = "size",
							defaultValue = "10") final Integer size) {
		logger.trace("got inquiry request: {}", request);
		List<SettlementInquiryResult> results = service.inquiry(request.getTrackingCodes(), page, size);
		logger.debug("settlement inquiry result -> {}", results);
		return ResponseEntity.ok(mapper.toSettlementInquiryListResponse(results));
	}

	@GetMapping(path = "/{trackingCode}/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementInquiryResponse> inquiry(@PathVariable String trackingCode)
			throws BusinessException {
		logger.info("got inquiry request with tracking code: {}", trackingCode);
		SettlementInquiryResult result = service.inquiry(mapper.toSettlementInquiryModel(trackingCode));
		logger.debug("settlement inquiry result -> {}", result);
		return ResponseEntity.ok(mapper.toSettlementInquiryResponse(result));
	}

	@GetMapping(path = "/{trackingCode}/inquiry", params = { "details" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementInquiryResponse> inquiryDetails(@PathVariable String trackingCode)
			throws BusinessException {
		logger.info("got get inquiry request with tracking code: {}", trackingCode);
		SettlementInquiryResult result = service.inquiry(mapper.toSettlementInquiryModel(trackingCode));
		logger.debug("settlement get inquiry result -> {}", result);
		return ResponseEntity.ok(mapper.toSettlementGetInquiriesResponse(result));
	}

	@PutMapping(path = "/{trackingCode}/fee/init", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementTicketResponse> initiateSettlementFee(@PathVariable String trackingCode,
			@RequestHeader(Constants.REQUEST_HEADER_TICKET) String ticket,
			@Valid @RequestBody SettlementAmountReviseRequest request) throws BusinessException {
		logger.info("got request to update amount for settlement -> [{}] with request -> [{}]", trackingCode, request);
		SettlementTicketResult result = service.initiateSettlementFee(
				mapper.toSettlementAmountReviseModel(trackingCode, ticket, request.getUpdatedAmount()));
		return ResponseEntity.ok(mapper.toSettlementTicketResponse(result));
	}

	@GetMapping(path = "/config/{trackingCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementLandingConfigResponse> getLandingConfig(@PathVariable String trackingCode)
			throws BusinessException {
		SettlementLandingConfigResult result = service.getLandingConfig(trackingCode);
		return ResponseEntity.ok(mapper.toSettlementLandingConfigResponse(result));
	}

	@GetMapping(path = "/{trackingCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SettlementDetailResponse> getDetails(
			@RequestHeader(Constants.REQUEST_HEADER_USER_ID) String userId, @PathVariable String trackingCode)
			throws BusinessException {
		SettlementDetailModel result = service.getDetails(trackingCode, userId);
		return ResponseEntity.ok(mapper.toSettlementDetailByTrackingCodeResponse(result));
	}

	@Override
	public void addCurrentUserRestriction(SearchRequest searchRequest) throws Exception {
		SimpleValueRestrictionDto userRestriction = new SimpleValueRestrictionDto();
		userRestriction.setField("userId");
		userRestriction.setOperation(OperationType.EQ);
		userRestriction.setValue(getCurrentUser());
		searchRequest.addToRestrictions(userRestriction);
	}

	@Override
	public void addCustomRestriction(SearchRequest searchRequest) throws Exception {
		SimpleValueRestrictionDto statusRestriction = new SimpleValueRestrictionDto();
		statusRestriction.setField("status");
		statusRestriction.setOperation(OperationType.NE);
		statusRestriction.setValue(Status.INIT.getValue());

		AndRestrictionDto andRestriction = new AndRestrictionDto();
		andRestriction.getRestrictions().add(statusRestriction);
		searchRequest.addToRestrictions(andRestriction);
	}

	private String getCurrentUser() {
		RequestContext currentContext = RequestContextInterceptor.getCurrentContext();
		if (currentContext == null) {
			throw new IllegalStateException("request context is null - no current user found");
		}
		return currentContext.getUserId();
	}

	@Override
	public ResponseService createSearchResponse(Page<Settlement> searchResult) {
		return mapper.toSettlementListResponse(searchResult);
	}

	@Override
	public FieldInfoContext getFieldInfoContext() {
		return new SettlementFieldInfoContext();
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public SearchService getSearchService() {
		return searchService;
	}

	@Override
	public Class<Settlement> getSearchTargetClass() {
		return Settlement.class;
	}

}
