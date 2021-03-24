@tag
Feature: Trade Capture 
	To allow a user to find CailSchemaBean data by ID or Name

@tag1
Scenario: 1) Create Trade and Price Trade
When Create new trade <in:trade_1>, amend trade and cancel trade <out:trade_id>
Then Get trade pricing results for the trade IDs <in:trade_id> <in:mktEnvType> <in:valueDate> <in:baseCurrency>
Then Do the pricing for the trade IDs <in:trade_id> <in:taskName> <in:mktEnvType> <in:valueDate> <in:baseCurrency>