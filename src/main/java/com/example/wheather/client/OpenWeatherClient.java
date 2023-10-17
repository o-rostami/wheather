package com.digipay.merchantcredit.client.creditscore;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.digipay.component.common.Constants;
import com.digipay.component.metric.annotation.Metric;
import com.digipay.creditscore.spec.request.bank.BankScoreReportRequest;
import com.digipay.creditscore.spec.response.bank.BankScoreReportResponse;
import com.digipay.merchantcredit.exception.ClientException;
import com.digipay.merchantcredit.metric.handler.CreditScoreMetricHandler;

@FeignClient(name = "creditScoreClient", url = "${credit.score.base.url}")
public interface CreditScoreClient {

	@Metric(handler = CreditScoreMetricHandler.NAME)
	@PostMapping(path = "/credit/score/bank/report", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	BankScoreReportResponse getBankScoreReport(@RequestBody BankScoreReportRequest request,
			@RequestHeader(Constants.REQUEST_HEADER_REQUESTER_APP_NAME) String initiator) throws ClientException;

}
