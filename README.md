# noviflix


In order to <b>add</b> a movie use:<br/><br/>
    <b>POST</b> localhost:8085/api/v1/movies
    
  JsonMessage:<br/>
  {<br/>
	  "title":"Title Movie",<br/>
	  "director": "Director",<br/>
	  "plot":"Plot"<br/>
  }

<hr/>

In order to <b>update</b> a movie use:<br/><br/>
    <b>PUT</b> localhost:8085/api/v1/movies/{id}<br/>
    
  JsonMessage:<br/>
  {<br/>
	  "title":"Title Movie",<br/>
	  "director": "Director",<br/>
	  "plot":"Plot"<br/>
  }
  
  <hr/>
  
In order to <b>delete</b> a movie use:<br/><br/>
    <b>DELETE</b> localhost:8085/api/v1/movies/{id}<br/>

<hr/>

In order to <b>load some hardcoded</b> movies use:<br/><br/>
    <b>GET</b> localhost:8085/api/v1/movies/loadmovies<br/>

<hr/>

In order to <b>load a random movie</b> use:<br/><br/>
    <b>GET</b> localhost:8085/api/v1/movies/whatsnext<br/>

<hr/>

In order to <b>get all movies</b> use:<br/><br/>
    <b>GET</b> localhost:8085/api/v1/movies<br/>
 <hr/>   
    
In order to <b>get a specific movie</b> use:<br/><br/>
     <b>GET</b> localhost:8085/api/v1/movies/{id}<br/>
