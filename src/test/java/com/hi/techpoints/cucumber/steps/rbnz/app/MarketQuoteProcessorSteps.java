package com.hi.techpoints.cucumber.steps.rbnz.app;

import org.springframework.test.context.ContextConfiguration;

import lombok.extern.slf4j.Slf4j;

@ContextConfiguration(locations = { "classpath:spring/rbnz-marketdata-app.xml" })
@Slf4j
public class MarketQuoteProcessorSteps {
	/*
	 * 
	 * @Value("${market.sheet.list}") private String SheetList;
	 * 
	 * @Produce(uri = "bean:additionalSheetService?method=processAdditionSheet")
	 * private ProducerTemplate processAdditionSheet;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier(value = "feedProcessorService") private FeedProcessorServiceImpl
	 * marketFeedProcessor;
	 * 
	 * @Produce(uri = "bean:feedProcessorService?method=processMarketFeed") private
	 * ProducerTemplate feedProcessorService;
	 * 
	 * @Produce(uri = "seda:queue:futureEligibleBond") private ProducerTemplate
	 * futureEligibleBond;
	 * 
	 * @Produce(uri = "seda:queue:csvGenerator") private ProducerTemplate
	 * csvGenerator;
	 * 
	 * @Autowired private MarketQuoteProcessorService marketQuoteProcessorService;
	 * 
	 * @Produce(uri =
	 * "bean:marketQuoteProcessorService?method=uploadToMarketQuoter") private
	 * ProducerTemplate uploadToMarketQuoter;
	 * 
	 * @When("^Get info wrapper data (.+) from feed processor (.+) (.+)$") public
	 * void testFeedProcessorService(String outWrapper,String
	 * inMarketFeedFilePath,String inCasUploadkey) throws Exception {
	 * 
	 * String marketFeedFilePath = (String) Storage.get(inMarketFeedFilePath);
	 * String casUploadkey = (String) Storage.get(inCasUploadkey);
	 * 
	 * File file = new File(marketFeedFilePath); AdditionalSheetService
	 * additionalsheet =
	 * ProxyHelper.createProxy(processAdditionSheet.getDefaultEndpoint(),
	 * AdditionalSheetService.class); additionalsheet.processAdditionSheet(file,
	 * casUploadkey); marketFeedProcessor.setSheetsToBeProcessed(SheetList);
	 * FeedProcessorService feedService =
	 * ProxyHelper.createProxy(feedProcessorService.getDefaultEndpoint(),
	 * FeedProcessorService.class); List<RequestInfoWrapper> requestInfoWrappers =
	 * feedService.processMarketFeed(file, casUploadkey); //Storing output
	 * 'outWrapper' param value. Storage.put(outWrapper, requestInfoWrappers);
	 * log.info("requestInfoWrappers list size : [{}]",requestInfoWrappers.size());
	 * assertNotNull("requestInfoWrappers should not be null : ",requestInfoWrappers
	 * ); assertTrue("requestInfoWrappers list size should be zero : "
	 * ,requestInfoWrappers.size() > 0); }
	 * 
	 * @Then("^Get process eligible bond (.+) by RequestInfoWrapper (.+) (.+)$")
	 * public void testProcessEligibleBond(String outQuoteWrappers,String
	 * inMarketFeedFilePath,String inWrapper) throws Exception { String filepath =
	 * (String) Storage.get(inMarketFeedFilePath); Instant start = Instant.now();
	 * log.info("Process Eligible Bond Start!!");
	 * log.info("originalFilePath"+filepath);
	 * 
	 * EligibleBondProcessorService eligibleBond =
	 * ProxyHelper.createProxy(futureEligibleBond.getDefaultEndpoint(),
	 * EligibleBondProcessorService.class); Instant end = Instant.now(); log.
	 * info("eligibleBondProcessorService?method=processEligibleBond execution Total time taken {} in ms"
	 * , Duration.between(start, end).toMillis()); List<RequestInfoWrapper>
	 * requestInfoWrappers = (List<RequestInfoWrapper>) Storage.get(inWrapper);
	 * log.info("testProcessEligibleBond requestInfoWrappers list size : [{}]"
	 * ,requestInfoWrappers.size()); Collection<List<MarketQuoteWrapper>>
	 * marketQuoteWrappers = eligibleBond.processEligibleBond(filepath,
	 * requestInfoWrappers); //Storing output 'outQuoteWrappers' param value.
	 * Storage.put(outQuoteWrappers, marketQuoteWrappers);
	 * log.info("MarketQuoteWrappers list size : [{}]",marketQuoteWrappers.size());
	 * assertNotNull("MarketQuoteWrappers list should not be null : "
	 * ,marketQuoteWrappers);
	 * assertTrue("MarketQuoteWrappers list size should not be zero : "
	 * ,marketQuoteWrappers.size() > 0); }
	 * 
	 * @Then("^Get CailKeyWrapperslist (.+) from MarketFeedProcessor (.+)$") public
	 * void testProcessRequestinfoWrapper(String outCailKeyWrappers,String
	 * inWrapper) throws Exception { List<RequestInfoWrapper> requestInfoWrappers =
	 * (List<RequestInfoWrapper>) Storage.get(inWrapper);
	 * log.info("testProcessRequestinfoWrapper requestInfoWrappers list size : [{}]"
	 * ,requestInfoWrappers.size()); Set<CailKeyWrapper> cailKeyWrappers =
	 * (Set<CailKeyWrapper>)
	 * marketFeedProcessor.processRequestinfoWrapper(requestInfoWrappers);
	 * 
	 * log.info("cailKeyWrapperslist size: {} ",cailKeyWrappers.size()); //Storing
	 * output 'outCailKeyWrappers' param value. Storage.put(outCailKeyWrappers, new
	 * ArrayList<CailKeyWrapper>(cailKeyWrappers));
	 * assertNotNull("CailKeyWrapperslist list should not be null : ",
	 * cailKeyWrappers);
	 * assertTrue("cailKeyWrapperslist list size should not be zero : "
	 * ,cailKeyWrappers.size() > 0); }
	 * 
	 * @Then("^Upload CailKeyWrapperslist (.+) to MarketQuoter$") public void
	 * testUploadToMarketQuoter(String inCailKeyWrappers) throws Exception {
	 * List<CailKeyWrapper> cailKeyWrapperslist = (List<CailKeyWrapper>)
	 * Storage.get(inCailKeyWrappers); FlatFileProcessor csvGenarator =
	 * ProxyHelper.createProxy(csvGenerator.getDefaultEndpoint(),
	 * FlatFileProcessor.class); Map <String,String> marketHeaders= new
	 * HashMap<String,String>(); marketHeaders.put("MarketName", "MarketName");
	 * csvGenarator.onCompleteWriteToCSV(cailKeyWrapperslist,marketHeaders);
	 * MarketQuoteProcessorService uploadToMarket =
	 * ProxyHelper.createProxy(uploadToMarketQuoter.getDefaultEndpoint(),
	 * MarketQuoteProcessorService.class); MktQuoteDataService mktQuoteDataService =
	 * new MktQuoteDataServiceImpl();
	 * 
	 * if(marketQuoteProcessorService instanceof MarketQuoteProcessorServiceImpl) {
	 * Field field =
	 * MarketQuoteProcessorServiceImpl.class.getDeclaredField("mktQuoteDataService")
	 * ; field.setAccessible(true); field.set(marketQuoteProcessorService,
	 * mktQuoteDataService); }
	 * 
	 * uploadToMarket.uploadToMarketQuote(cailKeyWrapperslist,
	 * OneviewMarketConstant.MKT_ENV_TYPE_NAME); List<MktQuote> mktQuotes =
	 * mktQuoteDataService.getMktQuoteByDateAndEnvId(new Date(), 10);
	 * 
	 * log.info("Uploaded MarketQuote list size: {}, cailKeyWrapperslist size: {}"
	 * ,mktQuotes.size(),cailKeyWrapperslist.size());
	 * assertEquals("Uploaded MarketQuote list size should be equal to cailKeyWrapperslist size : "
	 * ,mktQuotes.size(), cailKeyWrapperslist.size()); }
	 */}
