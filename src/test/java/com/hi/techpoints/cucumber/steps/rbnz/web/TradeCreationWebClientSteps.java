package com.hi.techpoints.cucumber.steps.rbnz.web;

import com.hi.techpoints.cucumber.steps.AbstractCucumberTest;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TradeCreationWebClientSteps extends AbstractCucumberTest {
	/*
	 * 
	 * 
	 * private static final String REST_SERVICE_URI_TRADE =
	 * "http://192.168.130.132:8080/oneview/rest/v1/trade"; private static final
	 * String REST_SERVICE_URI_PRICING =
	 * "http://192.168.130.132:8080/oneview/rest/v1/trade/pricing"; private static
	 * final String REST_SERVICE_URI_VALUATION=
	 * "http://192.168.130.132:8080/oneview/rest/v1/valuation";
	 * 
	 * @When("^Create new trade (.+), amend trade and cancel trade (.+)") public
	 * void testCreatetrade(String inParam,String outTradeID) throws Throwable {
	 * String tradeData = (String) Storage.get(inParam); RestTemplate restTemplate =
	 * new RestTemplate(); OneViewTradeCaptureResponse response =
	 * restTemplate.postForObject(REST_SERVICE_URI_TRADE, getHttpEntity(tradeData),
	 * OneViewTradeCaptureResponse.class); //Set response in storage.
	 * log.info("testCreatetrade response data : {}",response.
	 * getTradeCaptureSuccesses()); List<String> tradeIdList = new
	 * ArrayList<String>(); List<TradeCaptureSuccess> tradeCaptureSuccesses =
	 * response.getTradeCaptureSuccesses();
	 * 
	 * if(tradeCaptureSuccesses != null) { for(TradeCaptureSuccess
	 * success:tradeCaptureSuccesses) {
	 * tradeIdList.add(success.getNxIdentifier().getId()); } Storage.put(outTradeID,
	 * tradeIdList); }else { Storage.put(outTradeID,
	 * response.getTradeCaptureErrors()); }
	 * 
	 * assertTrue("Trade id list should not be zero ",tradeIdList.size() > 0); }
	 * 
	 * @Then("^Get trade pricing results for the trade IDs (.+) (.+) (.+) (.+)$")
	 * public void testGetTradePricingResults(String inTradeIds,String
	 * inMktEnvType,String inValueDate,String inBaseCurrency) throws Throwable {
	 * List<String> tradeIdList = (List<String>) Storage.get(inTradeIds); String
	 * tradeIds = tradeIdList.toString().replace("[", "").replaceAll("\\]", "")
	 * .replaceAll(", ", ","); Map<String,Object> keyValue = new HashMap<>();
	 * keyValue.put("mktEnvType", Storage.get(inMktEnvType));
	 * keyValue.put("valueDate", "24-Jul-2016");//Storage.get(inValueDate));
	 * keyValue.put("baseCurrency", Storage.get(inBaseCurrency));
	 * keyValue.put("tradeIds", tradeIds); RestTemplate restTemplate = new
	 * RestTemplate(); List<TradeValuationDataBean> response =
	 * (List<TradeValuationDataBean>)
	 * restTemplate.getForObject(REST_SERVICE_URI_VALUATION, List.class, keyValue);
	 * 
	 * assertNotNull("GetTradePricingResults should not be null ",response);
	 * log.info(" GetTradePricingResults list size {} :",response.size());
	 * assertTrue("GetTradePricingResults list should not be zero ",response.size()
	 * > 0); }
	 * 
	 * @Then("^Do the pricing for the trade IDs (.+) (.+) (.+) (.+) (.+)$") public
	 * void testPriceTrades(String intradeIds,String inTaskName,String
	 * inMktEnvType,String inValueDate,String inBaseCurrency) throws Throwable {
	 * List<String> tradeIds = (List<String>) Storage.get(intradeIds);
	 * PricingRequest pricingRequest = new PricingRequest();
	 * pricingRequest.setTaskName((String)Storage.get(inTaskName));
	 * pricingRequest.setBaseCurrency((String)Storage.get(inBaseCurrency)); Date
	 * date = new SimpleDateFormat("dd-MMM-yyyy").parse("24-Jul-2016");
	 * pricingRequest.setValueDate(date);
	 * pricingRequest.setMarketEnvType((String)Storage.get(inMktEnvType));
	 * TradeFilter portfolio = new TradeFilter(); List<Long> tradeIdList = new
	 * ArrayList<>(); for (String tid : tradeIds) { tradeIdList.add(new Long(tid));
	 * } portfolio.setTradeIds(tradeIdList); pricingRequest.setPortfolio(portfolio);
	 * String request = JsonMapper.beanToStr(pricingRequest);
	 * 
	 * log.info("pricingRequest json data: {}",request); RestTemplate restTemplate =
	 * new RestTemplate(); List<TradeValuationDataBean> response =
	 * (List<TradeValuationDataBean>)
	 * restTemplate.postForObject(REST_SERVICE_URI_PRICING, getHttpEntity(request),
	 * List.class);
	 * assertNotNull("TradeValuationDataBean should not be null ",response);
	 * log.info(" TradeValuationDataBean list size {} :",response.size());
	 * assertTrue("TradeValuationDataBean list should not be zero ",response.size()
	 * > 0); }
	 * 
	 * 
	 * private HttpEntity<String> getHttpEntity(String data) { HttpHeaders
	 * requestHeaders = new HttpHeaders();
	 * requestHeaders.setContentType(MediaType.APPLICATION_JSON); return new
	 * HttpEntity<String>(data, requestHeaders); }
	 */}
