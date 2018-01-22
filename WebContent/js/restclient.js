// The root URL for the RESTful services
var rootURL = "http://localhost:2222/rest/oasisconsole";
var issueRootURL = "http://localhost:2222/rest/oasisissue/html";

function getIssuePolicy(env, policy) {
	var restUrl = rootURL + '/policy?env=' + env + '&policy=' + policy;
	console.log(restUrl);
	
	$.ajax({
	    url: restUrl,
	    dataType: "json",
	    success: function(response) {
	    	console.log(response);
	    	document.getElementById('policydata').innerHTML = response;
	    },
	    error: function(xhr) {
	    	console.log ("Failure occurred during table generation : " + data);
	    }
	});	
}

function getLink(env, product) {
	var restUrl = rootURL + '/link';
	
	$.ajax({
	    url: restUrl,
	    data: {"env": env,"product":product},
	    success: function(response) {
	    	console.log(response);
	    	document.getElementById(product).onclick = function() {
	    		document.getElementById(product).href=response; 
	    		return false;
	    	};
	    },
	    error: function(xhr) {
	    	console.log ("Failure occurred during processing link : " + data);
	    }
	});	
}

function getConfig(env, outputFormat) {
	var restUrl = '';
		
	if (outputFormat.trim() === "HTML")
		restUrl = rootURL + '/htmlconfig/' + env;
	else
		restUrl	= rootURL + '/config/' + env;
	
	console.log ("var env = " + env);
	console.log ("var format = " + outputFormat);	
	console.log ("var rest url : " + restUrl)
	$.ajax({
        type: 'GET',
        url: restUrl,
        dataType: outputFormat,
        success: function(data){
            console.log("rest response: " + data);
            //console.log("format =" + outputFormat);
            //$('#restresponse').show();
            $('#restresponse').empty();
            
            /*var response=jQuery.parseJSON(data);
            
            if (typeof response =='object') {
            	console.log ("is json");
            	$('#restresponse').append(
            		    $('<pre>').text(
            		        JSON.stringify(data, null, '  ')
            		    )
            	 );
            } else {
                var xml, xmlfound=false;
                var xmlDoc = $.parseXML( data );
                $data = $( xmlDoc );
                $xml.find("oasisConfigType").each(function(index,elem){
                    console.log ("is xml");
                	$('#restresponse').append($xml);
                });
            	
            	$('#restresponse').append(data);
            }*/
	        $('#restresponse').append(data);
            //$('#restresponse').append(JSON.stringify(data));
            //renderDetails(data);
        },
        fail: function(data){ console.log ("Failure occurred : " + data);}
    });
}

/*var currentWine;

// Retrieve wine list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#btnAdd').click(function() {
	newWine();
	return false;
});

$('#btnSave').click(function() {
	if ($('#wineId').val() == '')
		addWine();
	else
		updateWine();
	return false;
});

$('#btnDelete').click(function() {
	deleteWine();
	return false;
});

$('#wineList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic wine bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newWine() {
	$('#btnDelete').hide();
	currentWine = {};
	renderDetails(currentWine); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentWine = data;
			renderDetails(currentWine);
		}
	});
}

function addWine() {
	console.log('addWine');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Wine created successfully');
			$('#btnDelete').show();
			$('#wineId').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addWine error: ' + textStatus);
		}
	});
}

function updateWine() {
	console.log('updateWine');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#wineId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Wine updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateWine error: ' + textStatus);
		}
	});
}

function deleteWine() {
	console.log('deleteWine');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#wineId').val(),
		success: function(data, textStatus, jqXHR){
			alert('Wine deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteWine error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#wineList li').remove();
	$.each(list, function(index, wine) {
		$('#wineList').append('<li><a href="#" data-identity="' + wine.id + '">'+wine.name+'</a></li>');
	});
}

function renderDetails(wine) {
	$('#wineId').val(wine.id);
	$('#name').val(wine.name);
	$('#grapes').val(wine.grapes);
	$('#country').val(wine.country);
	$('#region').val(wine.region);
	$('#year').val(wine.year);
	$('#pic').attr('src', 'pics/' + wine.picture);
	$('#description').val(wine.description);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var wineId = $('#wineId').val();
	return JSON.stringify({
		"id": wineId == "" ? null : wineId, 
		"name": $('#name').val(), 
		"grapes": $('#grapes').val(),
		"country": $('#country').val(),
		"region": $('#region').val(),
		"year": $('#year').val(),
		"picture": currentWine.picture,
		"description": $('#description').val()
		});
}*/
