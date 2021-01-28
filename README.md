#CityAutocomplete

<p>1. Request and Response Examples</p>
<br>1. /suggestions</br>
<br>
{
    "statusCode": 103,
    "message": "no required param q",
    "data": null
    }
    </br>
    <br>
    2. /suggestions?q=ottawa&latitude=55
    </br>
    <br>
    {
    "statusCode": 102,
    "message": "The location info(latitude and longitude) is not complete",
    "data": null
    }
    </br>
    <br>
    3. /suggestions?q=otta</br>
    <br>
    {
    "statusCode": 100,
    "message": "Request is successful",
    "data": [
   {
   "id": 6094817,
   "name": "Ottawa",
   "asciiname": "Ottawa",
   "alternatename": "Atava,Otava,Otavo,Otawa,Ottaba,Ottahua,Ottava,Ottavae,Ottawa,YOW,atawa,atoya,attava,awtawa,otava,otawa,ottava,wo tai hua,xxttawa,AA¬²±,Οττάβα,Οτταβα,Атава,Отава,Оттавæ,Оттава,Օտտավա,אוטווה,אטאווא,أوتاوا,ئۆتاوا,اتاوا,اوتآوآ,اوٹاوا,اٹاوہ,ओटावा,অটোয়া,ਓਟਾਵਾ,ଓଟାୱା,ஒட்டாவா,ಆಟ್ಟಾವಾ,ഓട്ടവ,ออตตาวา,ཨོ་ཏ་ཝ།,အော့တဝမြို့,ოტავა,ኦታዋ,ᎠᏔᏩ,ᐊᑐᕚ/atavaa,オタワ,渥太華,오타와",
   "population": 812129,
   "longitude": -75.69812,
   "latitude": 45.41117,
   "score": 0.8
   },
   {
   "id": 5928517,
   "name": "Cottam",
   "asciiname": "Cottam",
   "alternatename": "Kottam,Коттам",
   "population": 0,
   "longitude": -82.74982,
   "latitude": 42.13339,
   "score": 0.5
   },
   {
   "id": 6091497,
   "name": "Nottawa",
   "asciiname": "Nottawa",
   "alternatename": "",
   "population": 0,
   "longitude": -80.21638,
   "latitude": 44.4501,
   "score": 0.5
   },...
    ]
    }
    </br>
    <br>
    4./suggestions?q=otta&latitude=42&longitude=-82
    </br>
    <br>
       {
       "id": 6094817,
       "name": "Ottawa",
       "asciiname": "Ottawa",
       "alternatename": "Atava,Otava,Otavo,Otawa,Ottaba,Ottahua,Ottava,Ottavae,Ottawa,YOW,atawa,atoya,attava,awtawa,otava,otawa,ottava,wo tai hua,xxttawa,AA¬²±,Οττάβα,Οτταβα,Атава,Отава,Оттавæ,Оттава,Օտտավա,אוטווה,אטאווא,أوتاوا,ئۆتاوا,اتاوا,اوتآوآ,اوٹاوا,اٹاوہ,ओटावा,অটোয়া,ਓਟਾਵਾ,ଓଟାୱା,ஒட்டாவா,ಆಟ್ಟಾವಾ,ഓട്ടവ,ออตตาวา,ཨོ་ཏ་ཝ།,အော့တဝမြို့,ოტავა,ኦታዋ,ᎠᏔᏩ,ᐊᑐᕚ/atavaa,オタワ,渥太華,오타와",
       "population": 812129,
       "longitude": -75.69812,
       "latitude": 45.41117,
       "score": 0.53
       },
       {
       "id": 5889327,
       "name": "Bagnall",
       "asciiname": "Bagnall",
       "alternatename": "",
       "population": 0,
       "longitude": -82.36083,
       "latitude": 42.34694,
       "score": 0.4
       },
       {
       "id": 5920457,
       "name": "Chatham",
       "asciiname": "Chatham",
       "alternatename": "Chatem,Chatham,XCM,Чатем",
       "population": 43550,
       "longitude": -82.18494,
       "latitude": 42.41224,
       "score": 0.4
       },...
       ]
       }
        </br>
    
    
<p>2. Scoring Algorithm</p>
<p>Three criterion</p>
<p>2.1 length of the name/length of the query:   if the length of the name/query = 1, then score+=0.5, if name/query = 2, then score+=0.3, if name/query/>=3, then score+=0.1
</p>
<p>2.2 the population of the city: if the population is larger than 1 million, then score+=0.5, if the population is larger than 100k, then the score+=0.3;
</p>
<p>2.3 when latitude and longitude is present in the request params, the score also considers the distance between the query location and the response's actual location, the distance under 5km score+=0.5, under 10km score+=0.4, under 15km score+= 0.3, under 25km score+=0.2, under 50km score+=0.1.
In this case, the final score will be divided by 1.5 to keep the scale of 1.
</p>
<br>The distance is calculated by Haversine formula</br>
<p>3. data source: CA.txt geonames for Canada</p>

