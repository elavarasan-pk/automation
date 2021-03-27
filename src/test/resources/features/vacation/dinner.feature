Feature: Today What I am going to do   
	
	
@tag1
Scenario: Going to have dinner
When I am going to have dinner at <in:dinnerTime> in the restaurant <in:restaurantName>
Then Hi Waiter, Can you get these items <in:dinnerItems>
Then Can you bring bill <out:totalAmount> for the items <in:dinnerItems>  
And Can I do payment <in:totalAmount>, <in:cardDetails> 