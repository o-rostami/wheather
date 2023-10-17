package com.digipay.merchantcredit.iranvenezuela.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.digipay.merchantcredit.config.fundprovider.FundProviderProperties;
import com.digipay.merchantcredit.util.FundProviderConstants;

@Getter
@Setter
@ConfigurationProperties(prefix = "iranvenezuela")
public class IranVenezuelaProperties extends FundProviderProperties {

	private String accountFilePathPattern;

	private AccountInfoSftp accountInfoSftp;

	private String apiUsername;

	private String apiPassword;

	private String loanPrefix;

	private String loanPostfix;

	private String landingPageUrl;

	private String profileId;

	@Override
	public String getFundProvider() {
		return FundProviderConstants.IRAN_VENEZUELA;
	}

}
