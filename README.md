# Flight planner util - demo app

This is a demo app created for fully domenstrative purpose. Requirements:

> Your task is to implement application with two functionalities:
>
> For requested Flight Number and date will respond with following:
> * Cargo Weight for requested Flight
> * Baggage Weight for requested Flight
> * Total Weight for requested Flight
> 
> For requested IATA Airport Code and date will respond with following :
> * Number of flights departing from this airport,
> * Number of flights arriving to this airport,
> * Total number (pieces) of baggage arriving to this airport,
> * Total number (pieces) of baggage departing from this airport.
> 
> For generating test data use: https://www.json-generator.com/
>
> ```
> Flight Entity
> [
> 	'{{repeat(5)}}',
> 	{
> 		flightId: '{{index()}}',
> 		flightNumber: '{{integer(1000, 9999)}}',
> 		departureAirportIATACode: '{{random("SEA","YYZ","YYT","ANC","LAX")}}',
> 		arrivalAirportIATACode: '{{random("MIT","LEW","GDN","KRK","PPX")}}',
> 		departureDate: '{{date(new Date(2014, 0, 1), new Date(), "YYYY-MM-ddThh:mm:ss Z")}}'
> 	}
> ]
> 
> Cargo Entity
> [
> 	'{{repeat(5)}}',
> 	{
> 		flightId: '{{index()}}',
> 		baggage: [
> 		'{{repeat(3,8)}}',
> 			{
> 				id: '{{index()}}',
> 				weight: '{{integer(1, 999)}}',
> 				weightUnit: '{{random("kg","lb")}}',
> 				pieces: '{{integer(1, 999)}}'
> 			}
> 		],
> 		cargo: [
> 		'{{repeat(3,5)}}',
> 			{
> 				id: '{{index()}}',
> 				weight: '{{integer(1, 999)}}',
> 				weightUnit: '{{random("kg","lb")}}',
> 				pieces: '{{integer(1, 999)}}'
> 			}
> 		]
> 	}
> ]
> ```
