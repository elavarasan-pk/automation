@tag
Feature: RBNZ App 
	Market quote processor

@tag1
Scenario: Get info wrapper data
When Get info wrapper data <out:infoWrappers> from feed processor <in:marketFeedFilePath> <in:casUploadkey>	
Then Get process eligible bond <out:marketQuoteWrappers> by RequestInfoWrapper <in:marketFeedFilePath> <in:infoWrappers>
Then Get CailKeyWrapperslist <out:cailKeyWrappers> from MarketFeedProcessor <in:infoWrappers>
Then Upload CailKeyWrapperslist <in:cailKeyWrappers> to MarketQuoter