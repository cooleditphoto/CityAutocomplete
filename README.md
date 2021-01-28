#CityAutocomplete

<p>1. Request and Response Examples</p>

<p>2. Scoring Algorithm</p>
Three criterion
<p>length of the name/length of the query:   if the length of the name/query = 1, then score+=0.5, if name/query = 2, then score+=0.3, if name/query/>=3, then score+=0.1
</p>
<p>the population of the city: if the population is larger than 1 million, then score+=0.5, if the population is larger than 100k, then the score+=0.3;
</p>
<p>when latitude and longitude is present in the request params, the score also considers the distance between the query location and the response's actual location, the distance under 5km score+=0.5, under 10km score+=0.4, under 15km score+= 0.3, under 25km score+=0.2, under 50km score+=0.1.
In this case, the final score will be divided by 1.5 to keep the scale of 1.
</p>